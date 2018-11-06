package com.helio4.bancol.avaluos.controlador.api;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import com.helio4.bancol.avaluos.dominio.ParametrosController;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;
import com.helio4.bancol.avaluos.modelo.api.ResponseAvaluoApi;
import com.helio4.bancol.avaluos.servicio.datos.UsuarioService;
import com.helio4.bancol.avaluos.servicio.util.Mail;

@RestController
public class ApiGeneral {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ParametrosController parametrosController;

	@Autowired
	@Qualifier("repositoryUsuarioService")
	private UsuarioService usuarioService;

	protected UsuarioDTO usuario;

	public ResponseAvaluoApi validarInformacionUsuario(String userAuthentication) {

		try {

			String passwordWs = parametrosController.obtenerValor("clave_ws_avaluo");
			String loginInformation = new String(Base64.getDecoder().decode(userAuthentication));

			String datos[] = loginInformation.split(";");

			if (datos.length == 2) {

				if (passwordEncoder.matches(datos[1], passwordWs)) {

					if (!StringUtils.isNumeric(datos[0])) {
						return getErrorResponse(-16L, "Número de documento Invalido.",
								"Numero de documento: " + datos[0]);
					}

					Long numeroDocumento = Long.parseLong(datos[0]);

					usuario = usuarioService.buscarPorNumeroDocumento(numeroDocumento);

					if (usuario == null) {
						return getErrorResponse(-34L, "El usuario con el que intenta registrar el avalúo no existe.",
								"Número de identificación: " + numeroDocumento);
					}

				} else {
					return getErrorResponse(-35L, "Clave incorrecta.", "Autenticacion: " + userAuthentication);

				}

			} else {
				return getErrorResponse(-36L, "Información de autenticación incompleta", "Autenticacion: " + userAuthentication);

			}
		} catch (Exception e) {
			return getErrorResponse(-37L, "Error al validar la información del usuario: " + e.getMessage(), "Autenticacion: " + userAuthentication);
		}

		return null;
	}

	public ResponseAvaluoApi getErrorResponse(Long codeResponse, String message, String detail) {
		
		String destinatariosEmail =  parametrosController.obtenerValor("destinatarios_reporte_bancolombia");
		
		String asunto = "Error "+codeResponse+": "+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
		
		String texto ="<html>"+
						"<body >"+
						"<div >"+
						"    <div>"+
						"        <b>Código de respuesta:</b>"+
						"      <br/>"+
						codeResponse+
						"<br/><br/>"+
						"<b>Mensaje:</b>"+
						"<br/>"+
						message+
						"<br/><br/>"+
						"<b>Detalle:</b>"+
						"<br/>"+
						detail+
						"    </div>"+
						"</div>"+
						"</body>"+
						"</html>";
		
		for (String destinatario: destinatariosEmail.split(";")) {
			Mail mail = new Mail(asunto, texto, destinatario);
			mail.start();
		}

		ResponseAvaluoApi response = new ResponseAvaluoApi();
		response.setCodeResponse(codeResponse);
		response.setMessage(message);
		response.setDetail(detail);

		return response;

	}

	public ResponseAvaluoApi getResponse(Long codeResponse, String message, String detail) {

		ResponseAvaluoApi response = new ResponseAvaluoApi();
		response.setCodeResponse(codeResponse);
		response.setMessage(message);
		response.setDetail(detail);

		return response;

	}

}
