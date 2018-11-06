package com.helio4.bancol.avaluos.controlador.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.helio4.bancol.avaluos.dominio.AvaluoController;
import com.helio4.bancol.avaluos.dominio.ListadoAvaluosController;
import com.helio4.bancol.avaluos.dto.AvaluoDTO;
import com.helio4.bancol.avaluos.modelo.api.EstadoRequest;
import com.helio4.bancol.avaluos.modelo.api.ResponseAvaluoApi;
import com.helio4.bancol.avaluos.servicio.datos.DivipolaService;
import com.helio4.bancol.avaluos.servicio.datos.EntidadService;
import com.helio4.bancol.avaluos.servicio.datos.SegmentoService;
import com.helio4.bancol.avaluos.servicio.datos.TipoAvaluoService;
import com.helio4.bancol.avaluos.servicio.datos.UsuarioService;
import com.helio4.bancol.avaluos.servicio.util.Utils;

@RestController
public class ApiEstado extends ApiGeneral {

	public static final Long ESTADO_CANCELADO = 14L;

	@Autowired
	@Qualifier("repositoryEntidadService")
	private EntidadService entidadService;

	@Autowired
	@Qualifier("repositorySegmentoService")
	private SegmentoService segmentoService;

	@Autowired
	@Qualifier("repositoryTipoAvaluoService")
	private TipoAvaluoService tipoAvaluoService;

	@Autowired
	@Qualifier("repositoryDivipolaService")
	private DivipolaService divipolaService;

	@Autowired
	@Qualifier("repositoryUsuarioService")
	private UsuarioService usuarioService;

	@Autowired
	private AvaluoController avaluoController;

	@Autowired
	private ListadoAvaluosController listadoAvaluosController;

	@RequestMapping(value = "/actualizarEstado", method = RequestMethod.POST)
	@ResponseBody
	public ResponseAvaluoApi getTest(HttpServletRequest request, @RequestBody EstadoRequest estadoRequest,
			@RequestHeader(value = "user-authentication") String userAuthentication) {

		ResponseAvaluoApi response = validarInformacionUsuario(userAuthentication);

		if (response != null) {
			return response;
		}

		// validar id sisgen
		if (Utils.isNull(estadoRequest.getIdAvaluoSisgen())) {
			return getErrorResponse(-2L, "El ID SISGEN es obligatorio.", estadoRequest.toString());
		}

		AvaluoDTO avaluo = avaluoController.encontrarPorIdTinsa(estadoRequest.getIdAvaluoSisgen());

		if (avaluo != null) {

			// validar id sisgen
			if (Utils.isNull(estadoRequest.getEstado())) {
				return getErrorResponse(-3L, "El estado es obligatorio.", estadoRequest.toString());
			}

			if (ESTADO_CANCELADO.equals(estadoRequest.getEstado())) {
				listadoAvaluosController.cancelarAvaluo(avaluo, usuario);
			}

		}else {
			String estado = "";
			String idAvaluoSisgen = "";
			if(estadoRequest!=null && estadoRequest.getEstado()!=null){
				estado = estadoRequest.getEstado().toString();
			}
			if(estadoRequest!=null && estadoRequest.getIdAvaluoSisgen()!=null){
				idAvaluoSisgen = estadoRequest.getIdAvaluoSisgen().toString();
			}
			
			return getErrorResponse(4L, "Error actualizando estado, Avaluo con El ID SISGEN ingresado no existe.", "idAvaluoSisgen: "+idAvaluoSisgen+", Estado: "+estado);
		}

		return getResponse(200L, "Se actualizó estado para avaluo con id Sisgen: " + estadoRequest.getIdAvaluoSisgen(),
				"ID SISGEN: " + estadoRequest.getIdAvaluoSisgen());
	}

}
