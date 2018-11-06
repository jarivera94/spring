package com.helio4.bancol.avaluos.dominio;

import java.util.List;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dominio.gmap.GLatLng;
import com.helio4.bancol.avaluos.dto.AvaluoDTO;
import com.helio4.bancol.avaluos.dto.MetodoValuacionDTO;
import com.helio4.bancol.avaluos.dto.TipoAvaluoDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.datos.AvaluoService;
import com.helio4.bancol.avaluos.servicio.datos.EstadoAvaluoService;
import com.helio4.bancol.avaluos.servicio.datos.MetodoValuacionService;
import com.helio4.bancol.avaluos.servicio.datos.PermisoService;
import com.helio4.bancol.avaluos.servicio.datos.RolService;
import com.helio4.bancol.avaluos.servicio.datos.SucursalService;
import com.helio4.bancol.avaluos.servicio.datos.UsuarioService;

@Component
public class ListadoAvaluosController {

	private static Logger log = LoggerFactory.getLogger( ListadoAvaluosController.class );

	@Autowired
	@Qualifier("repositoryAvaluoService")
	private AvaluoService avaluoService;

	@Autowired
	@Qualifier("repositoryUsuarioService")
	private UsuarioService usuarioService;

	@Autowired
	@Qualifier("repositorySucursalService")
	private SucursalService sucursalService;

	@Autowired
	@Qualifier("repositoryEstadoAvaluoService")
	private EstadoAvaluoService estadoAvaluoService;

	@Autowired
	@Qualifier("repositoryRolService")
	private RolService rolService;

	@Autowired
	@Qualifier("repositoryPermisoService")
	private PermisoService permisoService;

	@Autowired
	@Qualifier("repositoryMetodoValuacionService")
	private MetodoValuacionService metodoValiacionService;

	public List<MetodoValuacionDTO> buscarMetodologiasAvaluo(Long id){
		return  this.metodoValiacionService.encontrarPorAvaluoId(id);
	}

	public MapModel actualizarModeloMapa(GLatLng latLng, String direccion) {
		MapModel mapModel = new DefaultMapModel(); // TODO verificar null pointer en esta linea
		if(latLng!=null){
			Marker marker = new Marker(new LatLng(latLng.getLat(), latLng.getLng()), "Dirección: "+direccion);
			mapModel.addOverlay(marker);
		}
		return mapModel;
	}

	public boolean aceptarCaso(AvaluoDTO avaluo, UsuarioDTO usuario) {
		return avaluoService.aceptarCaso(avaluo, usuario);
	}

	public boolean rechazarCaso
			(AvaluoDTO avaluo, String justificacionRechazo,
			 UsuarioDTO usuario) {
		return avaluoService
				.rechazarCaso(avaluo, justificacionRechazo, usuario);
	}

	public boolean solicitarDevolucion
			(AvaluoDTO avaluo, String justificacion,
			 UsuarioDTO usuario) {
		return avaluoService
				.solicitarDevolucion(avaluo, justificacion, usuario);
	}

	public boolean devolver(AvaluoDTO avaluo, UsuarioDTO usuario) {
		return avaluoService.devolver(avaluo, usuario);
	}

	public boolean reactivar
			(AvaluoDTO avaluo, UsuarioDTO usuarioActivo) {
		return avaluoService.reactivar(avaluo, usuarioActivo);
	}

	public boolean confirmarVisita(AvaluoDTO avaluoDTO, UsuarioDTO usuario) {
		return avaluoService.confirmarVisita(avaluoDTO, usuario);
	}

	public boolean cancelarAvaluo(AvaluoDTO avaluoDTO, UsuarioDTO usuario) {
		return avaluoService.cancelarAvaluo(avaluoDTO, usuario);
	}
	
	public boolean enProcesoAvaluo(AvaluoDTO avaluoDTO, UsuarioDTO usuario) {
		return avaluoService.enProcesoAvaluo(avaluoDTO, usuario);
	}

	public void actualizarTipoAvaluo(AvaluoDTO avaluo, UsuarioDTO usuarioDTO,
	        TipoAvaluoDTO nuevoTipoAvaluo) {
		try {
            avaluoService.cambiarTipoAvaluo(avaluo.getId(), nuevoTipoAvaluo.getNombre());
		} catch (AvaluoNotFoundException e) {
			log.error("No se encontró el avaluo a eliminar");
		}
		avaluoService.crear(avaluo, usuarioDTO);
	}

}
