package com.helio4.bancol.avaluos.servicio;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.helio4.bancol.avaluos.dto.AvaluoDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;
import com.helio4.bancol.avaluos.modelo.Avaluo;
import com.helio4.bancol.avaluos.modelo.Permiso;
import com.helio4.bancol.avaluos.modelo.Usuario;
import com.helio4.bancol.avaluos.servicio.datos.ParametroService;
import com.helio4.bancol.avaluos.servicio.datos.UsuarioService;
import com.helio4.bancol.avaluos.servicio.util.Constantes;
import com.helio4.bancol.avaluos.servicio.util.DateUtils;
import com.helio4.bancol.avaluos.servicio.util.Mail;

@Service
public class NotificacionesService {

	@Autowired
	@Qualifier("repositoryParametroService")
	private ParametroService parametroService;

	@Autowired
	@Qualifier("repositoryUsuarioService")
	private UsuarioService usuarioService;

    private BigDecimal tarifaValorMinimo;
    private BigDecimal tarifaValorMaximo;
    private BigDecimal tarifaValorTarifa;
    
    /**
     *
     * @param avaluo
     * @param perito
     */
    public void notificarAsignacionPerito(Avaluo avaluo, Usuario perito) {
        if( !avaluo.getTipoAvaluo().getNombre().equals( Constantes.TIPO_AVALUO_REMATE) ){
            notificarCliente(avaluo, perito);
            notificarAsesor(avaluo, perito);
        }
        notificarPerito(avaluo, perito);
        
    }

	//notifiacarAsignacionPerito
	private void notificarPerito(Avaluo avaluo, Usuario perito){
		String asunto = "Asignación de avalúo "+avaluo.getTipoAvaluo().getNombre()+" "+avaluo.getEntidad().getPrefijo()+"-"+avaluo.getCodigoExterno();
		String nombrePerito = perito.getNombres().concat(perito.getApellidos() != null ?
				" " : "").concat(perito.getApellidos());
		String cuerpoMensaje = parametroService.encontrarPorNombre("notificacion_asignacion_perito_perito").getValor();

		String motivoDeCredito = (avaluo.getCompraCartera() != null
            && avaluo.getCompraCartera()) ? "Compra de cartera"
            : (avaluo.getCambioGarantia() != null && avaluo.getCambioGarantia() > 0)?
            "Cambio de garantía" : " Garantía ";
		cuerpoMensaje = cuerpoMensaje.replace("#nombrePerito", nombrePerito);
		cuerpoMensaje = cuerpoMensaje.replace("#codigoExterno", avaluo.getCodigoExterno().toString());
		cuerpoMensaje = cuerpoMensaje.replace("#entidad", avaluo.getEntidad().getNombre() );
		cuerpoMensaje = cuerpoMensaje.replace("#motivoDeCredito", motivoDeCredito);

		Mail mail = new Mail(asunto, cuerpoMensaje, perito.getEmail());
		mail.start();
	}

	//-----------------------------------------------------------------
	//notificarAsignacionCliente
	private void notificarCliente(Avaluo avaluo, Usuario perito){
		
		String asunto = "Asignación de avalúo "+avaluo.getTipoAvaluo().getNombre()+" "+avaluo.getEntidad().getPrefijo()+"-"+avaluo.getCodigoExterno();
		String cuerpoMensaje = parametroService.encontrarPorNombre("notificacion_crear_asignar_caso_asesor_cliente").getValor();
		parametroService.encontrarPorNombre("valor_iva").getValor();
		float iva = Float.parseFloat(parametroService.encontrarPorNombre("valor_iva").getValor()); 

		cuerpoMensaje = cuerpoMensaje.replace("#codigoExterno", avaluo.getCodigoExterno() + "");
		String nombreCliente = this.getNombreCliente(avaluo);
		cuerpoMensaje = cuerpoMensaje.replace("#nombreCliente", nombreCliente);
		String nombrePerito = perito.getNombres().concat(perito.getApellidos() != null ?
				" " : "").concat(perito.getApellidos());
		cuerpoMensaje = cuerpoMensaje.replace("#nombrePerito", nombrePerito);
		cuerpoMensaje = cuerpoMensaje.replace("#celularPerito", perito.getCelular());
		cuerpoMensaje = cuerpoMensaje.replace("#correoelectronicoPerito", perito.getEmail());
		

		cuerpoMensaje = getTarifasEnCuerpoMensaje(cuerpoMensaje, iva);
        String correoDestinatario = avaluo.getCliente().getCorreoElectronicoSolicitante();
		if (correoDestinatario != null) {
            Mail mail = new Mail(asunto, cuerpoMensaje, correoDestinatario);
    		mail.start();
        }
	}

	private void notificarAsesor(Avaluo avaluo, Usuario perito){
		String correoAsesor = avaluo.getCorreoElectronicoAsesor() == null ? "" : avaluo.getCorreoElectronicoAsesor();

		if( !correoAsesor.isEmpty() ){
			String asunto = "Asignación de avalúo "+avaluo.getTipoAvaluo().getNombre()+" "+avaluo.getEntidad().getPrefijo()+"-"+avaluo.getCodigoExterno();
			String cuerpoMensaje = parametroService.encontrarPorNombre("notificacion_crear_asignar_caso_asesor_cliente").getValor();
			float iva = Float.parseFloat(parametroService.encontrarPorNombre("valor_iva").getValor());

			cuerpoMensaje = cuerpoMensaje.replace("#codigoExterno", avaluo.getCodigoExterno() + "");
			String nombreCliente = this.getNombreCliente(avaluo);
			cuerpoMensaje = cuerpoMensaje.replace("#nombreCliente", nombreCliente);
			String nombrePerito = perito.getNombres().concat(perito.getApellidos() != null ?
					" " : "").concat(perito.getApellidos());
			cuerpoMensaje = cuerpoMensaje.replace("#nombrePerito", nombrePerito);
			cuerpoMensaje = cuerpoMensaje.replace("#celularPerito", perito.getCelular());
			cuerpoMensaje = cuerpoMensaje.replace("#correoelectronicoPerito", perito.getEmail());
			
			cuerpoMensaje = getTarifasEnCuerpoMensaje(cuerpoMensaje, iva);
			
			Mail mail = new Mail(asunto, cuerpoMensaje, correoAsesor);
	        mail.start();
		}
	}

    public void notificarRechazoSolicitud(Avaluo avaluo, Usuario perito) {
        String asunto = "Rechazo de avalúo "+avaluo.getTipoAvaluo()
            .getNombre()+" "+avaluo.getEntidad().getPrefijo()+"-"+avaluo
            .getCodigoExterno();
        String cuerpoMensaje = parametroService.encontrarPorNombre(
                "notificacion_rechazo_asignacion_asignador").getValor();

        String nombrePerito = perito.getNombres().concat(perito
                .getApellidos() != null ?
                " " : "").concat(perito.getApellidos());
        cuerpoMensaje = cuerpoMensaje.replace("#codigoExterno", avaluo
                .getCodigoExterno().toString());
        cuerpoMensaje = cuerpoMensaje.replace("#nombrePerito", nombrePerito);
        cuerpoMensaje = cuerpoMensaje.replace("#justificacionRechazo",
                avaluo.getEstado().getJustificacionRechazo());

        notificarRevisores(new Permiso(6L,
                Constantes.PERMISO_ASIGNAR_SOLICITUD),
                avaluo, asunto, cuerpoMensaje);
    }

    private void notificarRevisores(Permiso permiso, Avaluo avaluo,
            String asunto, String cuerpoMensaje) {
        for(Usuario usuario:avaluo.getDivipola().getRegional().getUsuarios()) {
            if(usuario.getRol().getPermisos()
                    .contains(permiso)
                    && (!usuario.getRol().getPermisos()
                        .contains(new Permiso(1L,
                                Constantes.PERMISO_CREAR_ROL)))
                    && usuario.getEntidades().contains(avaluo.getEntidad())
                    && usuario.getTipoAvaluos().contains(avaluo
                        .getTipoAvaluo())){
                Mail mail = new Mail(asunto, cuerpoMensaje, usuario.getEmail());
                mail.start();
            }
        }
    }

    public void notificarCitaProgramada(Avaluo avaluo, Usuario perito, Date horaInicio) {
        if( !avaluo.getTipoAvaluo().getNombre().equals(Constantes.TIPO_AVALUO_REMATE) ){
            notificarCitaProgramadaAsesor(avaluo, perito, horaInicio);
            notificarCitaProgramadaCliente(avaluo, perito, horaInicio);
        }
        if( avaluo.getTipoAvaluo().getNombre().equals(Constantes.TIPO_AVALUO_REMATE) ){
            notificarCitaSecuestre(avaluo, perito, horaInicio);
        }
    }

    private void notificarCitaProgramadaCliente(Avaluo avaluo, Usuario perito, Date horaInicio) {
        String asunto = "Cita programada para el avalúo "+avaluo.getTipoAvaluo().getNombre()+" "+avaluo.getEntidad().getPrefijo()+"-"+avaluo.getCodigoExterno();
        String cuerpoMensaje = parametroService.encontrarPorNombre("notificacion_cita_cliente").getValor();
        float iva = Float.parseFloat(parametroService.encontrarPorNombre("valor_iva").getValor());

        String nombreCliente = this.getNombreCliente(avaluo);
        cuerpoMensaje = cuerpoMensaje.replace("#nombreCliente", nombreCliente);
        String fechaVisita = "";
        fechaVisita = DateUtils.getFechaFormateada(horaInicio, DateUtils.DATE_FORMAT_DD_MM_YYYY);
        cuerpoMensaje = cuerpoMensaje.replace( "#fechaVisita" , fechaVisita ) ;
       
        cuerpoMensaje = getTarifasEnCuerpoMensaje(cuerpoMensaje, iva);
       
        String correoDestinatario = avaluo.getCliente().getCorreoElectronicoSolicitante();

        if (correoDestinatario != null) {
            Mail mail = new Mail(asunto, cuerpoMensaje, correoDestinatario);
            mail.start();
            if (!avaluo.getCliente().getCorreoElectronicoSolicitante()
                    .equals(avaluo.getCorreoElectronicoRecibe())) {
                mail = new Mail(asunto, cuerpoMensaje,
                        avaluo.getCorreoElectronicoRecibe());
                mail.start();
            }
        }
    }
    
    private String getTarifasEnCuerpoMensaje(String cuerpoMensaje, float iva) {
    	
    	tarifaValorMinimo = new BigDecimal(parametroService.encontrarPorNombre("tarifa_general_valor_minimo").getValor());
        tarifaValorMaximo = new BigDecimal(parametroService.encontrarPorNombre("tarifa_general_valor_maximo").getValor());
        tarifaValorTarifa = new BigDecimal(parametroService.encontrarPorNombre("tarifa_general_tarifa").getValor());
    	
    	cuerpoMensaje = cuerpoMensaje.replace("#valorMinimo",   tarifaValorMinimo.toString());
        cuerpoMensaje = cuerpoMensaje.replace("#ivaMinimo",     (tarifaValorMinimo.intValue() * iva)+"");
        cuerpoMensaje = cuerpoMensaje.replace("#tarifa",        tarifaValorTarifa.toString());
        cuerpoMensaje = cuerpoMensaje.replace("#valorMaximo",   tarifaValorMaximo.toString());
        cuerpoMensaje = cuerpoMensaje.replace("#ivaMaximo",     (tarifaValorMaximo.intValue() * iva)+"");

        return cuerpoMensaje;
    	
    }
    

    private void notificarCitaProgramadaAsesor(Avaluo avaluo, Usuario perito, Date horaInicio) {
        String correoAsesor = avaluo.getCorreoElectronicoAsesor() == null ? "" : avaluo.getCorreoElectronicoAsesor();

        if( !correoAsesor.isEmpty() ){
            String asunto = "Cita programada para el avalúo "+avaluo.getTipoAvaluo().getNombre()+" "+avaluo.getEntidad().getPrefijo()+"-"+avaluo.getCodigoExterno();
            String cuerpoMensaje = parametroService.encontrarPorNombre("notificacion_cita_cliente").getValor();
            float iva = Float.parseFloat(parametroService.encontrarPorNombre("valor_iva").getValor());

            String nombreCliente = this.getNombreCliente(avaluo);
            cuerpoMensaje = cuerpoMensaje.replace("#nombreCliente", nombreCliente);

            String fechaVisita = "";
            fechaVisita = DateUtils.getFechaFormateada(horaInicio, DateUtils.DATE_FORMAT_DD_MM_YYYY);
            cuerpoMensaje = cuerpoMensaje.replace( "#fechaVisita" , fechaVisita ) ;

            cuerpoMensaje = getTarifasEnCuerpoMensaje(cuerpoMensaje, iva);
            
            Mail mail = new Mail(asunto, cuerpoMensaje, correoAsesor);
            mail.start();
        }
    }

    public void notificarDevolucion(Avaluo avaluo, Usuario perito) {
        if( ! avaluo.getTipoAvaluo().getNombre().equals( Constantes.TIPO_AVALUO_REMATE) ){
            notificarDevolucionAsesor(avaluo, perito);
        }
        notificarDevolucionPerito(avaluo, perito);

    }

    private void notificarDevolucionAsesor(Avaluo avaluo, Usuario perito) {
        String correoAsesor = avaluo.getCorreoElectronicoAsesor() == null ? "" : avaluo.getCorreoElectronicoAsesor();
        if( !correoAsesor.isEmpty() ){
            String asunto = "Devolución de avalúo "+avaluo.getTipoAvaluo().getNombre()+" "+avaluo.getEntidad().getPrefijo()+"-"+avaluo.getCodigoExterno();
            String cuerpoMensaje = parametroService.encontrarPorNombre("notificacion_devolucion_asesor").getValor();

            cuerpoMensaje = cuerpoMensaje.replace("#nombreAsesor", avaluo.getNombreAsesor());
            cuerpoMensaje = cuerpoMensaje.replace("#codigoExterno", avaluo.getCodigoExterno().toString());
            cuerpoMensaje = cuerpoMensaje.replace("#justificacion", avaluo.getEstado().getJustificacionRechazo());

            Mail mail = new Mail(asunto, cuerpoMensaje, correoAsesor );
            mail.start();
        }
    }

    public void notificarSolicitudDevolucion(Avaluo avaluo, Usuario perito) {
        String asunto = "Solicitud de devolución de avalúo "
            + avaluo.getTipoAvaluo().getNombre()
            + " " + avaluo.getEntidad().getPrefijo()
            + "-" + avaluo.getCodigoExterno();
        String cuerpoMensaje = parametroService
            .encontrarPorNombre("notificacion_solicitud_devolucion_seguimiento")
            .getValor();

        String nombrePerito = perito.getNombres()
            .concat(perito.getApellidos() != null ?
                " " : "").concat(perito.getApellidos());
        cuerpoMensaje = cuerpoMensaje.replace("#nombrePerito", nombrePerito);
        cuerpoMensaje = cuerpoMensaje
            .replace("#codigoExterno", avaluo.getCodigoExterno().toString());
        cuerpoMensaje = cuerpoMensaje
            .replace("#justificacion", avaluo.getEstado()
                    .getJustificacionRechazo());

        notificarRevisores(new Permiso(22L,
                Constantes.PERMISO_DEVOLVER_CASO),
                avaluo, asunto, cuerpoMensaje);
    }

    private void notificarDevolucionPerito(Avaluo avaluo, Usuario perito) {
        String asunto = "Devolución de avalúo "+avaluo.getTipoAvaluo().getNombre()+" "+avaluo.getEntidad().getPrefijo()+"-"+avaluo.getCodigoExterno();
        String cuerpoMensaje = parametroService.encontrarPorNombre("notificacion_devolucion_asesor").getValor();
        String nombrePerito = perito.getNombres().concat(perito.getApellidos() != null ?
                " " : "").concat(perito.getApellidos());
        cuerpoMensaje = cuerpoMensaje.replace("#nombreAsesor", nombrePerito);
        cuerpoMensaje = cuerpoMensaje.replace("#codigoExterno", avaluo.getCodigoExterno().toString());
        cuerpoMensaje = cuerpoMensaje.replace("#justificacion", avaluo.getEstado().getJustificacionRechazo());

        Mail mail = new Mail(asunto, cuerpoMensaje, perito.getEmail() );
        mail.start();
    }

    public void notificarReactivacion(Avaluo avaluo, Usuario notificado, Usuario activador) {
        String asunto = "Reactivación del avalúo "+avaluo.getTipoAvaluo().getNombre()+" "+avaluo.getEntidad().getPrefijo()+"-"+avaluo.getCodigoExterno();
        String cuerpoMensaje = parametroService.encontrarPorNombre("notificacion_reactivacion").getValor();

        String nombreNotificado = notificado.getNombres().concat(notificado.getApellidos() != null ?
                " " : "").concat(notificado.getApellidos());
        String nombreActivador = activador.getNombres().concat(activador.getApellidos() != null ?
                " " : "").concat(activador.getApellidos());

        cuerpoMensaje = cuerpoMensaje.replace("#nombreNotificado", nombreNotificado);
        cuerpoMensaje = cuerpoMensaje.replace("#nombreActivador", nombreActivador);
        cuerpoMensaje = cuerpoMensaje.replace("#codigoExterno", avaluo.getCodigoExterno().toString());

        Mail mail = new Mail(asunto, cuerpoMensaje, notificado.getEmail());
        mail.start();
    }

    public void notificarHonorarios(Avaluo avaluo) {
        String correoCliente = avaluo.getCliente().getCorreoElectronicoSolicitante();

        String nombreCliente  = this.getNombreCliente(avaluo);
        if( correoCliente!=null && !correoCliente.isEmpty() &&
                !avaluo.getTipoAvaluo().getNombre().equals( Constantes.TIPO_AVALUO_REMATE) ){
            this.notificarHonorariosCliente(avaluo,nombreCliente);
        }
        else if (!avaluo.getTipoAvaluo().getNombre().equals( Constantes.TIPO_AVALUO_REMATE)) {
            this.notificarHonorariosPerito(avaluo,nombreCliente);
        }
    }

    private void notificarHonorariosCliente(Avaluo avaluo,String nombreCliente){
        String correoCliente = avaluo.getCliente().getCorreoElectronicoSolicitante();
        String asunto = "Notificación de honorarios :"
                + avaluo.getTipoAvaluo().getNombre()
                + " " + avaluo.getEntidad().getPrefijo()+ "-"
                + avaluo.getCodigoExterno();

        String direccionInmueble = avaluo.getDireccionInmuebleInforme();
        NumberFormat formateadorDinero = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("es-CO"));

        formateadorDinero.setMaximumFractionDigits(2);
        BigDecimal valorHonorariosMostrado = avaluo.getValorHonorarios().setScale(2, RoundingMode.HALF_EVEN);
        String valoresHonorariosTotal = formateadorDinero.format(valorHonorariosMostrado.doubleValue());

        BigDecimal gastosTranslado = (avaluo.getGastosTranslado() == null ) ? BigDecimal.ZERO : avaluo.getGastosTranslado().setScale(2, RoundingMode.HALF_EVEN);
        String gastosTransladoStr = formateadorDinero.format( gastosTranslado.doubleValue() );

        BigDecimal ivaBigDecimal = avaluo.getIva();
        String iva = formateadorDinero.format(ivaBigDecimal.setScale(2, RoundingMode.HALF_EVEN).doubleValue());

        BigDecimal totalHonorarios = avaluo.getValorHonorarios().add(gastosTranslado).add(ivaBigDecimal).setScale(2, RoundingMode.HALF_EVEN);
        String totalHonorariosStr = formateadorDinero.format( totalHonorarios.doubleValue() );
        String cuerpoMensaje = this.parametroService.encontrarPorNombre( "notificacion_honorarios" ).getValor();
        cuerpoMensaje = cuerpoMensaje.replace("#codigoExterno", avaluo.getCodigoExterno().toString());
        cuerpoMensaje = cuerpoMensaje.replace("#nombreCliente", nombreCliente);
        cuerpoMensaje = cuerpoMensaje.replace("#direccionInmueble", direccionInmueble);
        cuerpoMensaje = cuerpoMensaje.replace("#valorHonorarios", valoresHonorariosTotal);
        cuerpoMensaje = cuerpoMensaje.replace("#gastosDeTraslado", gastosTransladoStr);
        cuerpoMensaje = cuerpoMensaje.replace("#ivaHonorario", iva);
        cuerpoMensaje = cuerpoMensaje.replace("#totalHonorarios", totalHonorariosStr);
//      cuerpoMensaje = cuerpoMensaje.replace("#correoPerito", avaluo.getPerito().getEmail());
        if (avaluo.getPerito().getNombreBanco() != null) {
            cuerpoMensaje = cuerpoMensaje.replace("#bancoPerito", avaluo.getPerito().getNombreBanco());
        }
        if (avaluo.getPerito().getNumeroCuentaBanco() != null) {
            cuerpoMensaje = cuerpoMensaje.replace("#numeroCuentaPerito", avaluo
                    .getPerito().getNumeroCuentaBanco());
        }
        if (avaluo.getPerito().getTipoCuentaBanco() != null) {
            cuerpoMensaje = cuerpoMensaje.replace("#tipoCuentaPerito", avaluo
                    .getPerito().getTipoCuentaBanco());
        }
        if (avaluo.getPerito().getNombreTitular() != null) {
            cuerpoMensaje = cuerpoMensaje.replace("#nombreTitularCuentaPerito",
                    avaluo.getPerito().getNombreTitular());
        }
        if (avaluo.getPerito().getTipoIdentificacionTitular() != null) {
            String tipoIdentificacionTitularPerito = (avaluo.getPerito().getTipoIdentificacionTitular() == null) ? "" :
                avaluo.getPerito().getTipoIdentificacionTitular().equals("cc") ? " C.C " :
                avaluo.getPerito().getTipoIdentificacionTitular().equals("ce") ? " C.E " :
                avaluo.getPerito().getTipoIdentificacionTitular().equals("nit") ? " NIT " : " ";
            cuerpoMensaje = cuerpoMensaje.replace(
                    "#tipoIdentificacionTitularPerito",tipoIdentificacionTitularPerito );
        }
        if (avaluo.getPerito().getNumeroIdentificacionTitular() != null) {
            cuerpoMensaje = cuerpoMensaje.replace(
                    "#numeroIdentificacionTitularPerito", avaluo.getPerito()
                            .getNumeroIdentificacionTitular());
        }
        Mail mail = new Mail(asunto, cuerpoMensaje, correoCliente);
        mail.start();
    }

    /**
     * Notificación que se envía cuando el cliente no tiene un correo
     *
     * @param Avaluo avalúo sobre el cual se envía la notificación.
     * */
    private void notificarHonorariosPerito(Avaluo avaluo,String nombreCliente){
        String correoPerito = avaluo.getPerito().getEmail();
        String asunto = "Notificación de honorarios :"
                + avaluo.getTipoAvaluo().getNombre()
                + " " + avaluo.getEntidad().getPrefijo()+ "-"
                + avaluo.getCodigoExterno();
        String cuerpoMensaje = this.parametroService.encontrarPorNombre( "notificacion_honorarios_cliente_sin_correo" ).getValor();


        String nombrePerito = avaluo.getPerito().getNombres().concat(avaluo.getPerito().getApellidos() != null ?" " : "").concat(avaluo.getPerito().getApellidos());
        cuerpoMensaje = cuerpoMensaje.replace("#nombrePerito",nombrePerito  );
        cuerpoMensaje = cuerpoMensaje.replace("#nombreCliente",nombreCliente  );
        cuerpoMensaje = cuerpoMensaje.replace("#codigoExterno", avaluo.getCodigoExterno().toString()  );

        Mail mail = new Mail(asunto, cuerpoMensaje, correoPerito);
        mail.start();
    }

    public void notificarCorreciones(Avaluo avaluo, Usuario perito) {
        String asunto = "Correcciones del avalúo "+avaluo.getTipoAvaluo().getNombre()+" "+avaluo.getEntidad().getPrefijo()+"-"+avaluo.getCodigoExterno();
        String cuerpoMensaje = parametroService.encontrarPorNombre("notificacion_correccion_perito").getValor();

        String nombrePerito = perito.getNombres().concat(perito.getApellidos() != null ?
                " " : "").concat(perito.getApellidos());
        cuerpoMensaje = cuerpoMensaje.replace("#nombrePerito", nombrePerito);
        cuerpoMensaje = cuerpoMensaje.replace("#codigoExterno", avaluo.getCodigoExterno().toString());

        Mail mail = new Mail(asunto, cuerpoMensaje, perito.getEmail());
        mail.start();
    }

    /**
     * Método que notifica que el avalúo fue aprobado.
     *
     * @param avaluo
     * @param usuarioDTO
     * @param archivo ruta del pdf  a enviar.
     * */
    public void notifiacarAvaluoAprobado(Avaluo avaluo, UsuarioDTO  usuario, String archivo){
        if( ! avaluo.getTipoAvaluo().getNombre().equals( Constantes.TIPO_AVALUO_REMATE)){
           
            this.notifiacarAvaluoAprobadoAsesor(avaluo, usuario, archivo);
        }else{
            
        }
    }

    /**
     * Método que notifica al cliente que el avalúo fue aprobado.
     *
     * @param avaluo
     * @param usuarioDTO
     * @param archivo ruta del pdf  a enviar.
     * */
    @Deprecated
    private void notifiacarAvaluoAprobadoCliente(Avaluo avaluo, UsuarioDTO  usuario, String archivo){
        String asunto = " Avalúo aprobado "+avaluo.getTipoAvaluo().getNombre()+" "+avaluo.getEntidad().getPrefijo()+"-"+avaluo.getCodigoExterno();
        String cuerpoMensaje = parametroService.encontrarPorNombre("notificacion_avaluo_aprobado").getValor();
        String nombreCliente = this.getNombreCliente(avaluo);
        cuerpoMensaje = cuerpoMensaje.replace("#nombreCliente", nombreCliente);
        String direccionInmueble = avaluo.getDireccionInmueble();
        cuerpoMensaje = cuerpoMensaje.replace("#codigoExterno", avaluo.getCodigoExterno().toString());
        cuerpoMensaje = cuerpoMensaje.replace("#direccionInmueble",direccionInmueble);
        String correoDestinatario = avaluo.getCliente().getCorreoElectronicoSolicitante();
        if (correoDestinatario != null) {
            Mail mail = new Mail(asunto, cuerpoMensaje, correoDestinatario);
            String nombreArchivo = avaluo.getEntidad().getPrefijo()+"-"+avaluo.getCodigoExterno() + ".pdf";
            mail.adjuntarArchivo(archivo,nombreArchivo);
            mail.start();
        }
    }
    /**
     * Método que notifica al asesor  que un avalúo fue aprobado.
     *
     * @param avaluo
     * @param usuarioDTO
     * @param archivo ruta del pdf  a enviar.
     * */
    private void notifiacarAvaluoAprobadoAsesor(Avaluo avaluo, UsuarioDTO  usuario, String archivo){
        String correoAsesor = avaluo.getCorreoElectronicoAsesor() == null ?  "" :  avaluo.getCorreoElectronicoAsesor();
        if( !correoAsesor.isEmpty() ){
            String nombreAsesor = avaluo.getNombreAsesor();
            String direccionInmueble = avaluo.getDireccionInmueble();
            String asunto = " Avalúo aprobado "+avaluo.getTipoAvaluo().getNombre()+" "+avaluo.getEntidad().getPrefijo()+"-"+avaluo.getCodigoExterno();
            String cuerpoMensaje = parametroService.encontrarPorNombre("notificacion_avaluo_aprobado").getValor();
            cuerpoMensaje = cuerpoMensaje.replace("#nombreCliente", nombreAsesor );
            cuerpoMensaje = cuerpoMensaje.replace("#codigoExterno", avaluo.getCodigoExterno().toString());
            cuerpoMensaje = cuerpoMensaje.replace("#direccionInmueble",direccionInmueble);
            Mail mail = new Mail(asunto, cuerpoMensaje, correoAsesor);
            String nombreArchivo = avaluo.getEntidad().getPrefijo()+"-"+avaluo.getCodigoExterno() + ".pdf";
            mail.adjuntarArchivo(archivo,nombreArchivo);
            mail.start();
        }
    }

    //nuevas notificacion
    public void notificarCitaSecuestre(Avaluo avaluo, Usuario secuestre, Date horaInicio){
        String asunto = " Programación de Cita para Avaúo "+avaluo.getTipoAvaluo().getNombre()+" "+avaluo.getEntidad().getPrefijo()+"-"+avaluo.getCodigoExterno();
        String cuerpoMensaje = parametroService.encontrarPorNombre("notificacion_cita_secuestre").getValor();
        cuerpoMensaje = cuerpoMensaje.replace("#nombreSecuestre", secuestre.getNombres() + " " + secuestre.getApellidos());
        String nombreCliente = this.getNombreCliente(avaluo);
        cuerpoMensaje = cuerpoMensaje.replace("#nombreCliente", nombreCliente);
        String fechaVisita = "";
        fechaVisita = DateUtils.getFechaFormateada(horaInicio, DateUtils.DATE_FORMAT_DD_MM_YYYY);
        cuerpoMensaje = cuerpoMensaje.replace("#fechaCita", fechaVisita) ;

        Mail mail = new Mail(asunto, cuerpoMensaje, secuestre.getEmail());
        mail.start();
    }

//  public void notificarNoAceptaCasoAsignador(){
//
//  }

    public void notificarReprogramarCitaPerito(AvaluoDTO avaluo, UsuarioDTO  perito, Date horaInicio){
        String asunto = " Reprogramación de Cita para Avaúo "
            + avaluo.getTipoAvaluo().getNombre()
            + " " + avaluo.getEntidad().getPrefijo()
            + "-" + avaluo.getCodigoExterno();
        String cuerpoMensaje = parametroService.encontrarPorNombre("notificacion_reprogramar_cita_perito").getValor();
        String nombreCliente = avaluo.getCliente().getPrimerNombre() + " " + avaluo.getCliente().getPrimerApellido();
        cuerpoMensaje = cuerpoMensaje.replace("#nombreCliente", nombreCliente);
        String fechaVisita = "";
        fechaVisita = DateUtils.getFechaFormateada(horaInicio, DateUtils.DATE_FORMAT_DD_MM_YYYY);
        cuerpoMensaje = cuerpoMensaje.replace("#fechaCita", fechaVisita) ;

        String correoDestinatario = avaluo.getCliente().getCorreoElectronicoSolicitante();
        if (correoDestinatario != null) {
            Mail mail = new Mail(asunto, cuerpoMensaje, correoDestinatario);
            mail.start();
        }
    }

    public void notificarUsuarioCreado(UsuarioDTO usuario) {
        String asunto = "Bienvenido al Sistema Bancol";
        String cuerpoMensaje = parametroService.encontrarPorNombre("notificacion_usuario_creado").getValor();
        String nombresApellidosUsuario = usuario.getNombres() + " " + usuario.getApellidos();
        cuerpoMensaje = cuerpoMensaje.replace("#nombresApellidosUsuario", nombresApellidosUsuario);
        cuerpoMensaje = cuerpoMensaje.replace("#nombreUsuario", usuario.getNombreUsuario());
        cuerpoMensaje = cuerpoMensaje.replace("#contrasena", usuario.getContrasenaNoEncriptada());
        String emailDestinatario = usuario.getEmail();
        Mail mail = new Mail(asunto, cuerpoMensaje, emailDestinatario);
        mail.start();
    }

    public void notificarCambioContrasena(UsuarioDTO usuario) {
        String asunto = "Contraseña de Sistema Bancol Reestablecida";
        String cuerpoMensaje = parametroService
            .encontrarPorNombre("notificacion_cambio_contrasena").getValor();
        String nombresApellidosUsuario = usuario.getNombres() + " " + usuario.getApellidos();
        cuerpoMensaje = cuerpoMensaje.replace("#usuarioNombres", nombresApellidosUsuario);
        cuerpoMensaje = cuerpoMensaje.replace("#contrasena", usuario.getContrasenaNoEncriptada());
        String emailDestinatario = usuario.getEmail();
        Mail mail = new Mail(asunto, cuerpoMensaje, emailDestinatario);
        mail.start();
    }

    private String getNombreCliente(Avaluo avaluo){
        String nombreCliente = "";
        if( avaluo.getCliente() != null ) {
            if (new Integer(23).equals(avaluo.getCliente().getTipoDocumentoIdentificacion())) {
                nombreCliente = avaluo.getCliente().getRazonSocial();
            }else{
                nombreCliente = (avaluo.getCliente().getPrimerNombre()   != null ? avaluo.getCliente().getPrimerNombre()    : "")
                        .concat(avaluo.getCliente().getSegundoNombre()   != null ? avaluo.getCliente().getSegundoNombre()   : "")
                        .concat(avaluo.getCliente().getPrimerApellido()  != null ? avaluo.getCliente().getPrimerApellido()  : "")
                        .concat(avaluo.getCliente().getSegundoApellido() != null ? avaluo.getCliente().getSegundoApellido() : "");
            }
        }

        return nombreCliente;
    }



}
