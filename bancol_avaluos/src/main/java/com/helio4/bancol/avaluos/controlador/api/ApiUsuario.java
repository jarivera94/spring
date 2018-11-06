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

import com.helio4.bancol.avaluos.dto.UsuarioDTO;
import com.helio4.bancol.avaluos.modelo.api.ResponseAvaluoApi;
import com.helio4.bancol.avaluos.modelo.api.UsuarioRequest;
import com.helio4.bancol.avaluos.servicio.datos.DivipolaService;
import com.helio4.bancol.avaluos.servicio.datos.EntidadService;
import com.helio4.bancol.avaluos.servicio.datos.SegmentoService;
import com.helio4.bancol.avaluos.servicio.datos.TipoAvaluoService;
import com.helio4.bancol.avaluos.servicio.datos.UsuarioService;
import com.helio4.bancol.avaluos.servicio.util.Utils;

@RestController
public class ApiUsuario extends ApiGeneral{

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

	@RequestMapping(value = "/validarUsuarioActivo", method = RequestMethod.POST)
	@ResponseBody
	public ResponseAvaluoApi getTest(HttpServletRequest request, @RequestBody UsuarioRequest usuarioRequest,
			@RequestHeader(value = "user-authentication") String userAuthentication) {

		ResponseAvaluoApi response = validarInformacionUsuario(userAuthentication);

		if (response != null) {
			return response;
		}

		if (Utils.isNull(usuarioRequest.getNumeroDocumento())) {
			return getErrorResponse(-1L, "El parametro de numero de Identificaciones obligatorio. ",
					usuarioRequest.toString());
		}

		UsuarioDTO usuario = usuarioService.buscarPorNumeroDocumento(usuarioRequest.getNumeroDocumento());

		if (usuario == null) {
			return getResponse(-2L, "El usuario no existe", "");
		}else if(Boolean.FALSE.equals(usuario.getActivoParaAsignacion())) {
			return getResponse(-2L, "El usuario esta inactivo", "");
		} else {
			return getResponse(200L, "Usuario activo", "");
		}

	}

}
