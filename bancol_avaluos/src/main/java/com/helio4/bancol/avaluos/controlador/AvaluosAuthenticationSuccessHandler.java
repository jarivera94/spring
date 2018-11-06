package com.helio4.bancol.avaluos.controlador;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.helio4.bancol.avaluos.dto.RolDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;
import com.helio4.bancol.avaluos.servicio.util.Constantes;

public class AvaluosAuthenticationSuccessHandler implements
		AuthenticationSuccessHandler {
	
	private static Logger log = LoggerFactory.getLogger( AvaluosAuthenticationSuccessHandler.class );
	
	@Autowired
	private RedirectStrategy redirectStrategy;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		log.debug("Autenticación exitosa, redireccionando a página de inicio.");
		handle(request, response, authentication);
		clearAuthenticationAttributes(request);
	}

	protected String determineTargetUrl(Authentication authentication, HttpServletRequest request) {
		UsuarioDTO usuario = (UsuarioDTO) authentication.getPrincipal();
		RolDTO rol = usuario.getRol();
		if (rol == null 
				|| rol.getPaginaInicio() == null) {
			log.error("El usuario no tiene ningún rol asociado");
			throw new IllegalStateException();
		}
		
		String externalAccess = request.getParameter("externalAccess");
		
		if(externalAccess != null){
			
			String opc = request.getParameter("opc");
			System.out.println("trama de user:"+request.getHeader("user-authentication"));
			String url = "";
			
			if(opc != null && opc.equals(Constantes.OPC_HIPOTECARIO)){
				String avaluoId = request.getParameter("avaluoIdSisgen");
				
				if(avaluoId!=null){
					url = Constantes.OPC_HIPOTECARIO_URL+"?avaluoIdSisgen="+avaluoId;
				}
			}
			
			return url;
			
		}else{
			return usuario.getRol().getPaginaInicio().getUrl();
		}
		
	}

	protected void handle(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication) throws IOException {
		String targetUrl = determineTargetUrl(authentication, request);
		if (response.isCommitted()) {
            log.debug("La respuesta ya ha sido completada, no es posible redirecionar  a:  {}",
                    targetUrl);
			return;
		}
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}
	
	private void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession httpSession = request.getSession(false);
		if (httpSession == null) {
			return;
		}
		httpSession.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

}
