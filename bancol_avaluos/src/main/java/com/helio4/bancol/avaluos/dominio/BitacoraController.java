package com.helio4.bancol.avaluos.dominio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.AvaluoDTO;
import com.helio4.bancol.avaluos.dto.BitacoraDTO;
import com.helio4.bancol.avaluos.dto.PermisoDTO;
import com.helio4.bancol.avaluos.dto.RegionalDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;
import com.helio4.bancol.avaluos.servicio.datos.AvaluoService;
import com.helio4.bancol.avaluos.servicio.datos.BitacoraService;
import com.helio4.bancol.avaluos.servicio.datos.PermisoService;
import com.helio4.bancol.avaluos.servicio.datos.UsuarioService;
import com.helio4.bancol.avaluos.servicio.util.Constantes;
import com.helio4.bancol.avaluos.servicio.util.Mail;

@Component
public class BitacoraController {
	
	@Autowired
	@Qualifier("repositoryBitacoraService")
	private BitacoraService bitacoraService;
	
	@Autowired
	@Qualifier("repositoryUsuarioService")
	private UsuarioService usuarioService;
	
	@Autowired
	@Qualifier("repositoryAvaluoService")
	private AvaluoService avaluoService;
	
	@Autowired
	@Qualifier("repositoryPermisoService")
	private PermisoService permisoService;
	
	public List<BitacoraDTO> encontrarBitacoraPorAvaluo(Long id){
		return bitacoraService.encontrarBitacoraPorAvaluo(id);
	}
	
	public BitacoraDTO guardar(BitacoraDTO bitacoraDTO, AvaluoDTO avaluoDTO){
		bitacoraDTO = bitacoraService.guardar(bitacoraDTO);
		String observaciones = this.formatoMensaje(bitacoraDTO);
		
		String prefijo = ( avaluoDTO.getEntidad()!=null && avaluoDTO.getEntidad().getPrefijo()!=null) ? avaluoDTO.getEntidad().getPrefijo() : this.avaluoService.encontrarPrefijo(avaluoDTO.getId());
		String asunto = "Observaciones avalúo "+avaluoDTO.getTipoAvaluo().getNombre()+" "+ (prefijo==null ? "" : prefijo )+"-"+avaluoDTO.getCodigoExterno();

        Mail mail;

        if (bitacoraDTO.getNotificacionAsesor() != null
                && bitacoraDTO.getNotificacionAsesor()) {
            String correoAsesor = avaluoService.cargarCorreoAsesor(avaluoDTO
                    .getId());
            if (correoAsesor != null && !correoAsesor.isEmpty()) {
                mail = new Mail(asunto, observaciones, correoAsesor);
                mail.start();
            }
        }

		if(bitacoraDTO.getNotificacionCliente()!=null && bitacoraDTO.getNotificacionCliente() && avaluoDTO.getCliente().getCorreoElectronicoSolicitante()!=null){
			mail = new Mail(asunto, observaciones, avaluoDTO.getCliente().getCorreoElectronicoSolicitante());
			mail.start();
		}
		
		if(bitacoraDTO.getNotificacionCreador()!=null && bitacoraDTO.getNotificacionCreador()){
			UsuarioDTO usuario = usuarioService.encontrarUsuarioAvaluoPorEstado(avaluoDTO.getId(), Constantes.Estado.PorAsignar.key());
			
			if(usuario!=null){
				mail = new Mail(asunto, observaciones, usuario.getEmail());
				mail.start();
			}
		}
		
		if(bitacoraDTO.getNotificacionPerito()!=null && bitacoraDTO.getNotificacionPerito() && avaluoDTO.getPerito()!=null && avaluoDTO.getPerito().getEmail()!=null){
			mail = new Mail(asunto, observaciones, avaluoDTO.getPerito().getEmail());
			mail.start();
		}
		
		if(bitacoraDTO.getNotificacionPersonaRecibePerito()!=null && bitacoraDTO.getNotificacionPersonaRecibePerito() && avaluoDTO.getCorreoElectronicoRecibe()!=null){
			mail = new Mail(asunto, observaciones, avaluoDTO.getCorreoElectronicoRecibe());
			mail.start();
		}
		
		if(bitacoraDTO.getNotificacionRevisor()!=null && bitacoraDTO.getNotificacionRevisor()){
			UsuarioDTO revisor = usuarioService.encontrarUsuarioAvaluoPorEstado(avaluoDTO.getId(), Constantes.Estado.Correciones.key());
			UsuarioDTO comite = usuarioService.encontrarUsuarioAvaluoPorEstado(avaluoDTO.getId(), Constantes.Estado.ParaComite.key());
			UsuarioDTO aprobador = usuarioService.encontrarUsuarioAvaluoPorEstado(avaluoDTO.getId(), Constantes.Estado.Aprobado.key());
			
			if(revisor!=null){
				mail = new Mail(asunto, observaciones, revisor.getEmail());
				mail.start();
			}
			if(comite!=null){
				mail = new Mail(asunto, observaciones, comite.getEmail());
				mail.start();
			}
			if(aprobador!=null){
				mail = new Mail(asunto, observaciones, aprobador.getEmail());
				mail.start();
			}
			
			if(revisor == null && comite == null  && aprobador == null){
				boolean enviar;
				List<UsuarioDTO> listUsuarioDtos = usuarioService.encontrarUsuariosPorPermiso(Constantes.PERMISO_REVISAR_GUARDAR_AVALUO);
				for(UsuarioDTO usuarioDTO : listUsuarioDtos){
					enviar = true;
					if(usuarioDTO.getRol().getPermisos().contains(new PermisoDTO(Constantes.PERMISO_CREAR_ROL))){
						enviar = false;
					}
					if(enviar){
						mail = new Mail(asunto, observaciones, usuarioDTO.getEmail());
						mail.start();
					}
				}
			}
		}

        if (bitacoraDTO.getNotificacionSeguidor() != null
                && bitacoraDTO.getNotificacionSeguidor()) {
            RegionalDTO regionalAvaluo = this.avaluoService
                    .buscarRegionalAvaluo(avaluoDTO.getId());
            List<UsuarioDTO> usuariosSeguidores = usuarioService
                    .encontrarUsuariosPorPermisoRegional(
                            Constantes.PERMISO_DEVOLVER_CASO,
                            regionalAvaluo.getId());
            for (UsuarioDTO usuarioDTO : usuariosSeguidores) {
                if (usuarioDTO.getRol().getPermisos()
                        .contains(new PermisoDTO(Constantes.PERMISO_CREAR_ROL))) {
                    continue;
                } else {
                    mail = new Mail(asunto, observaciones,
                            usuarioDTO.getEmail());
                    mail.start();
                }
            }
        }

		return bitacoraDTO;
	}
	
	public String formatoMensaje (BitacoraDTO bitacoraDTO){
		return "<html>"
				+ "<header>"
				+ "<title></title>"
				+ "</header>"
				+ "<body style=\"background-color: #303030;\">"
				+ "<div id=\"contenedor\" style=\"background-color: #0DB3C8;\">"
				+ "<div id=\"encabezado\" style=\"background-color: #0C96CE;\">"
				+ "<center>"
				+ "<img src=\"http://www.ibancol.com.co:8080/bancol_avaluos/javax.faces.resource/logo_blanco_trans_peq.png?ln=images\" width=\"180px\" height=\"110px\"/>"
				+ "</center>"
				+ "</div>"
				+ "<div id=\"cuerpo\" style=\"background-color: #F6F9FB;\">"
				+ "<div id=\"parrafo\" style=\"font-size: 100%;font-family: Calibri, Sans-Serif, Helvetica; width:90%;margin: 5px auto;\">"
				+ "<br/>"
				+ "<H3 style=\"font-family: Calibri, Sans-Serif;color: #515151;\" >Observaci&oacute;n</H3>"
				+ "<p style=\"font-family: Calibri, Sans-Serif;"
				+ "color: #515151;"
				+ "font-size: 100%;"
				+ "text-align: justify;\">"
				+ bitacoraDTO.getObservaciones()
				+ "</p><br/>"
				+ "<p style=\"font-family: Calibri, Sans-Serif;"
				+ "color: #515151;"
				+ "font-size: 80%;"
				+ "text-align: justify;\">El contenido de este correo electrónico puede contener información confidencial, en propiedad o legalmente protegida y está dirigido exclusivamente a nombre del destinatario (s)."
				+ "<br/><br/>"
				+ "Gracias!"
				+ "<br/>"
				+ "Equipo Bancol"
				+ "</p>"
				+ "</div>"
				+ "<br/>"
				+ "</div>"
				+ "<div id=\"pie\" style=\"background: #0DB3C8;height: 70px;\">"
				+ "<img src=\"http://www.ibancol.com.co:8080/bancol_avaluos/javax.faces.resource/logo_gia_blanco_trans.png?ln=images\" width=\"100px\" height=\"70px\" style=\"float: right;\"/>"
				+ "</div></div></body></html>";
	}

}
