package com.helio4.bancol.avaluos.ensamblador;

import com.google.common.collect.Iterables;
import com.helio4.bancol.avaluos.dto.AreaDTO;
import com.helio4.bancol.avaluos.dto.AvaluoHipotecarioDTO;
import com.helio4.bancol.avaluos.dto.FormatoInformeHipotecarioDTO;
import com.helio4.bancol.avaluos.dto.FormatoInformeHipotecarioDTO.ListaEstadoConservacion;
import com.helio4.bancol.avaluos.dto.GarajeDTO;
import com.helio4.bancol.avaluos.modelo.ArchivoBUA;
import com.helio4.bancol.avaluos.servicio.datos.DivipolaService;
import com.helio4.bancol.avaluos.servicio.util.Constantes;
import com.helio4.bancol.avaluos.servicio.util.InformacionPersonalUtils;
import com.helio4.bancol.avaluos.servicio.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

@Component
public class ArchivoBUAEnsamblador {

	private static Logger log = LoggerFactory
			.getLogger(ArchivoBUAEnsamblador.class);

	@Autowired
	@Qualifier("repositoryDivipolaService")
	private DivipolaService divipolaService;

	/**
	 * Caracteres que no so permitidos en BUA.
	 * */
	private final String[] CARACTERES_NO_PERMITIDOS = { "'",">","<","&","\n","\t","\\n","\\t","\\r","\\r\\n|\\r|\\n",System.getProperty("line.separator") };

	/**
	 * Retorna 1 si es verdadero y 0 si es falso
	 * @param bool
	 * @return
	 */
	public static int toInt(Boolean bool) {
		if (bool == null) return 0;
		return bool ? 1 : 0;
	}

	public ArchivoBUA crearArchivoBUA(AvaluoHipotecarioDTO avaluoHipotecarioDTO) {
		DecimalFormatSymbols symbol = new DecimalFormatSymbols(Locale.getDefault());
		symbol.setDecimalSeparator(',');
		DecimalFormat formatter = new DecimalFormat("###.#######", symbol);
        FormatoInformeHipotecarioDTO formatoInforme = (FormatoInformeHipotecarioDTO) avaluoHipotecarioDTO.getFormatoInforme();
        String nombreSolicitante = InformacionPersonalUtils.concatenarNombreCliente(avaluoHipotecarioDTO.getCliente());
        nombreSolicitante = InformacionPersonalUtils.concatenarNombreCortoCliente(
                    avaluoHipotecarioDTO.getCliente());
		ArchivoBUA archivoBUA = new ArchivoBUA(
            (formatoInforme.getAndenesEnLasVias()) ? 1 : 2,
                    formatoInforme.getCiudadNotaria().getMunicipioBUA(),
                    formatoInforme.getCiudadNotaria().getDepartamentoBUA(),
			formatoInforme.getEstrato().intValue(),
			avaluoHipotecarioDTO.getDivipolaInforme().getMunicipioBUA(),
			avaluoHipotecarioDTO.getDivipolaInforme().getDepartamentoBUA(),
			formatoInforme.getMetodoDeValuacion().intValue(),
			avaluoHipotecarioDTO.getObjetoDelAvaluo().getId().intValue(),
			avaluoHipotecarioDTO.getCliente().getTipoDocumentoIdentificacion().intValue(),
            (formatoInforme.getBarrioLegal().booleanValue()) ? 1 : 2,
            (formatoInforme.getViasPavimentadas().booleanValue()) ? 1 : 2,
            (formatoInforme.getSometidoAPropiedadHorizontal().booleanValue()) ? 1 : 2,
            (formatoInforme.getSardinelesEnLasVias().booleanValue()) ? 1 : 2,
            formatoInforme.getTopografiaSector().getKey(),
            formatoInforme.getTransporte().getKey(),
            formatoInforme.getUsoActual().getKey(),
			avaluoHipotecarioDTO.getValorAvaluoEnUvr(),
			avaluoHipotecarioDTO.getId(),
			avaluoHipotecarioDTO.getCliente().getNumeroDocumento().intValue(),
			formatoInforme.getTiempoEsperadoDeComercializacion(),
			avaluoHipotecarioDTO.getValorTotalAvaluo(),
			avaluoHipotecarioDTO.getValorUvr(),
			avaluoHipotecarioDTO.getCalificacionGarantia().getKey(),
			formatoInforme.getActualidadEdificadora(),
			avaluoHipotecarioDTO.getBarrio() != null && avaluoHipotecarioDTO.getBarrio().length() > 30 ? avaluoHipotecarioDTO.getBarrio().substring(0, 30) : avaluoHipotecarioDTO.getBarrio(),
			formatoInforme.getComportamientoOfertaDemanda(),
			formatoInforme.getJustificacionDeMetodologia(),
			avaluoHipotecarioDTO.getMatriculaInmobiliariaPrincipal1() != null && avaluoHipotecarioDTO.getMatriculaInmobiliariaPrincipal1().length() > 19 ? avaluoHipotecarioDTO.getMatriculaInmobiliariaPrincipal1().substring(0, 19) : avaluoHipotecarioDTO.getMatriculaInmobiliariaPrincipal1(),
			nombreSolicitante,
			formatoInforme.getNumeroNotariaDeEscritura(),
			formatoInforme.getNumeroDeEscritura(),
			formatoInforme.getObservaciones(),
			formatoInforme.getPerspectivasDeValorizacion(),
			avaluoHipotecarioDTO.getTipoDeInmueble().getId().intValue()
			);
		if (formatoInforme.getCalidadAcabadosBanos() != null) {
			archivoBUA.setCalidadAcabadosBanos(formatoInforme.getCalidadAcabadosBanos().getKey());
		}
		if (formatoInforme.getCalidadAcabadosMadera() != null ) {
			archivoBUA.setCalidadAcabadosMadera(formatoInforme.getCalidadAcabadosMadera().getKey());	
		}
		if (formatoInforme.getCalidadAcabadosMetal() !=null ) {
			archivoBUA.setCalidadAcabadosMetal(formatoInforme.getCalidadAcabadosMetal().getKey());
		}
		if (formatoInforme.getCalidadAcabadosCocina() != null) {
			archivoBUA.setCalidadAcabadosCocina(formatoInforme.getCalidadAcabadosCocina().getKey());
		}
		if (formatoInforme.getCalidadAcabadosMuros() != null) {
			archivoBUA.setCalidadAcabadosMuros(formatoInforme.getCalidadAcabadosMuros().getKey());
		}
		if (formatoInforme.getCalidadAcabadosPisos() != null ) {
			archivoBUA.setCalidadAcabadosPisos(formatoInforme.getCalidadAcabadosPisos().getKey());
		}
		if (formatoInforme.getCalidadAcabadosTechos() != null) {
			archivoBUA.setCalidadAcabadosTechos(formatoInforme.getCalidadAcabadosTechos().getKey());
		}
		if (formatoInforme.getClaseInmueble() != null) {
			archivoBUA.setClaseInmueble(formatoInforme.getClaseInmueble().getKey());
		}
		archivoBUA.setConjuntoCerrado((formatoInforme.getConjuntoCerrado()) ? 1 : 2);
		if ( formatoInforme.getCubierta() != null ) {
			archivoBUA.setCubierta(formatoInforme.getCubierta().getKey());
		}
		if (formatoInforme.getEstadoAcabadosBanos() !=null ) {
			archivoBUA.setEstadoAcabadosBanos(formatoInforme.getEstadoAcabadosBanos().getKey());
		}
		if (formatoInforme.getEstadoAcabadosCocina() != null) {
			archivoBUA.setEstadoAcabadosCocina(formatoInforme.getEstadoAcabadosCocina().getKey());
		}
		
		if (formatoInforme.getEstadoDeConstruccion() !=null) {
			archivoBUA.setEstadoDeConservacion( 
					(formatoInforme.getEstadoDeConstruccion() !=null  && formatoInforme.getEstadoDeConstruccion().getValue().equals(Constantes.ESTADO_CONSTRUCCION_NUEVO) )
					? ListaEstadoConservacion.Optimo.getKey():formatoInforme.getEstadoDeConservacion().getKey());
		}
		if (formatoInforme.getEstadoAcabadosMadera() != null) {
			archivoBUA.setEstadoAcabadosMadera(formatoInforme.getEstadoAcabadosMadera().getKey());
		}
		if (formatoInforme.getEstadoAcabadosMetal() != null) {
			archivoBUA.setEstadoAcabadosMetal(formatoInforme.getEstadoAcabadosMetal().getKey());
		}
		if (formatoInforme.getEstadoAcabadosMuros() != null) {
			archivoBUA.setEstadoAcabadosMuros(formatoInforme.getEstadoAcabadosMuros().getKey());	
		}
		
		if (formatoInforme.getEstadoAcabadosPisos() != null) {
			archivoBUA.setEstadoAcabadosPisos(formatoInforme.getEstadoAcabadosPisos().getKey());
		}
		if (formatoInforme.getEstructura() != null) {
			archivoBUA.setEstructura(formatoInforme.getEstructura().getKey());
		}
		if (formatoInforme.getEstadoAcabadosTechos() != null) {
			archivoBUA.setEstadoAcabadosTechos(formatoInforme.getEstadoAcabadosTechos().getKey());
		}
		
		if (formatoInforme.getEstadoDeLasVias() != null) {
			archivoBUA.setEstadoDeLasVias(formatoInforme.getEstadoDeLasVias().getKey());
		}
		if (formatoInforme.getFachada() != null) {
			archivoBUA.setFachada(formatoInforme.getFachada().getKey());
		}
		if (formatoInforme.getIluminacion() != null) {
			archivoBUA.setIluminacion(formatoInforme.getIluminacion().getKey());
		}
		
		if (formatoInforme.getPisosBodega() != null) {
			archivoBUA.setPisosBodega(formatoInforme.getPisosBodega().getKey());
		}
		if (formatoInforme.getCondicionesDeSalubridad() != null ) {
			archivoBUA.setCondicionesDeSalubridad(formatoInforme.getCondicionesDeSalubridad().getKey());
		} 
		if (formatoInforme.getTipoFachada() != null) {
			archivoBUA.setTipoFachada(formatoInforme.getTipoFachada().getKey());
		}
		if (formatoInforme.getTipoDeVivienda() != null) {
			archivoBUA.setTipoDeVivienda(formatoInforme.getTipoDeVivienda().getKey());
		}
		if (formatoInforme.getUbicacionDelInmueble() !=null ) {
			archivoBUA.setUbicacionDelInmueble(formatoInforme.getUbicacionDelInmueble().getKey());
		}
		// C_UBICACION2 ignorado
		if (formatoInforme.getVentilacion() !=null ) {
			archivoBUA.setVentilacion(formatoInforme.getVentilacion().getKey());	
		}
		archivoBUA.setFechaAvaluo(avaluoHipotecarioDTO.getFechaEstado());
		archivoBUA.setFechaDeEscritura(formatoInforme.getFechaDeEscritura());
		archivoBUA.setAireAcondicionadoCentral(toInt(formatoInforme.getAireAcondicionadoCentral()));
		archivoBUA.setAcueductoEnElPredio(toInt(formatoInforme.getAcueductoEnElPredio()));
		archivoBUA.setAcueductoEnElSector(toInt(formatoInforme.getAcueductoEnElSector()));
		archivoBUA.setImpactoAmbientalAguasServidas(toInt(formatoInforme.getImpactoAmbientalAguasServidas()));
		archivoBUA.setAlamedas(toInt(formatoInforme.getAlamedas()));
		archivoBUA.setAlcantarilladoEnElPredio(toInt(formatoInforme.getAlcantarilladoEnElPredio()));
		archivoBUA.setAlcantarilladoEnElSector(toInt(formatoInforme.getAlcantarilladoEnElSector()));
		archivoBUA.setAmbientalAlumbrado(toInt(formatoInforme.getAlumbrado()));
		archivoBUA.setAmbientalArborizacion(toInt(formatoInforme.getArborizacion()));
		archivoBUA.setAmbientalParques(toInt(formatoInforme.getParques()));
		archivoBUA.setAmbientalZonasVerdes(toInt(formatoInforme.getZonasVerdesPublicas()));
		archivoBUA.setArborizacion(toInt(formatoInforme.getArborizacion()));
		archivoBUA.setAscensor(toInt(formatoInforme.getAscensor()));
		archivoBUA.setImpactoAmbientalBasura(toInt(formatoInforme.getImpactoAmbientalBasura()));
		archivoBUA.setBicicletero(toInt(formatoInforme.getBicicletero()));
		archivoBUA.setBombaEyectora(toInt(formatoInforme.getBombaEyectora()));
		archivoBUA.setCanchaMultiple(toInt(formatoInforme.getCanchaMultiple()));
		archivoBUA.setCicloRutas(toInt(formatoInforme.getCicloRutas()));
		archivoBUA.setCitofono(toInt(formatoInforme.getCitofono()));
		archivoBUA.setClubHouse(toInt(formatoInforme.getClubHouse()));
		archivoBUA.setEnergiaEnElPredio(toInt(formatoInforme.getEnergiaEnElPredio()));
		archivoBUA.setEnergiaEnElSector(toInt(formatoInforme.getEnergiaEnElSector()));
		archivoBUA.setEstadoDeObra(formatoInforme.getEstadoDeObra() ? 1 : 0);
		archivoBUA.setRemodelado(toInt(formatoInforme.getRemodelado()));
		archivoBUA.setGasEnElPredio(toInt(formatoInforme.getGasEnElPredio()));
		archivoBUA.setGasEnElSector(toInt(formatoInforme.getGasEnElSector()));
		archivoBUA.setGimnasio(toInt(formatoInforme.getGimnasio()));
		archivoBUA.setGarajeVisitantes(toInt(formatoInforme.getGarajeVisitantes()));
		archivoBUA.setGolfito(toInt(formatoInforme.getGolfito()));
		archivoBUA.setImpactoAmbientalInseguridad(toInt(formatoInforme.getImpactoAmbientalInseguridad()));
		archivoBUA.setJuegosNinos(toInt(formatoInforme.getJuegosNinos()));
		archivoBUA.setOtrosUsos(toInt(formatoInforme.getImpactoAmbientalOtro()));
		archivoBUA.setParadero(toInt(formatoInforme.getParadero()));
		archivoBUA.setParques(toInt(formatoInforme.getParques()));
		archivoBUA.setPiscina(toInt(formatoInforme.getPiscina()));
		archivoBUA.setPlantaElectrica(toInt(formatoInforme.getPlantaElectrica()));
		archivoBUA.setImpactoAmbientalAire(toInt(formatoInforme.getImpactoAmbientalAire()));
		archivoBUA.setPorteria(toInt(formatoInforme.getPorteria()));
		archivoBUA.setEquipoDePresionConstante(toInt(formatoInforme.getEquipoDePresionConstante()));
		archivoBUA.setImpactoAmbientalRuido(toInt(formatoInforme.getImpactoAmbientalRuido()));
		archivoBUA.setSalonComunal(toInt(formatoInforme.getSalonComunal()));
		archivoBUA.setShutBasuras(toInt(formatoInforme.getShutBasuras()));
		archivoBUA.setCanchaSquash(toInt(formatoInforme.getCanchaSquash()));
		archivoBUA.setTanqueDeAgua(toInt(formatoInforme.getTanqueDeAgua()));
		archivoBUA.setTelefonoEnElPredio(toInt(formatoInforme.getTelefonoEnElPredio()));
		archivoBUA.setTelefonoEnElSector(toInt(formatoInforme.getTelefonoEnElSector()));
		archivoBUA.setZonasVerdesPublicas(toInt(formatoInforme.getZonasVerdesPublicas()));
		archivoBUA.setZonasVerdesComunales(toInt(formatoInforme.getZonasVerdesComunales()));
		int cantPrivado = 0;
		int cantExclusivo = 0;
		int cantBahiaComunal = 0;
		int cantCubierto = 0;
		int cantDescubierto = 0;
		int cantDoble = 0;
		int cantSencillo = 0;
		int cantServidumbre = 0;
        for (int i = 0; i<avaluoHipotecarioDTO.getGarajes().size(); i++) {
            try {
                GarajeDTO garaje = Iterables.get(
                        avaluoHipotecarioDTO.getGarajes(), i);
                if(garaje.getTipo().equals(1)){
                    cantPrivado += 1;
                }
                if(garaje.getTipo().equals(2)){
                    cantExclusivo += 1;
                }
                if(garaje.getTipo().equals(3)){
                    cantBahiaComunal += 1;
                }
                if(garaje.isCubierto()){
                    cantCubierto += 1;
                }else{
                    cantDescubierto += 1;
                }
                if(garaje.isDoble()){
                    cantDoble += 1;
                }else{
                    cantSencillo += 1;
                }
                if(garaje.isServidumbre()){
                    cantServidumbre += 1;
                }

                extraerMatriculaInmobiliaria(i+1, garaje, archivoBUA);
            } catch (IndexOutOfBoundsException e) {
            }
        }
        archivoBUA.setPrivado(cantPrivado);
		archivoBUA.setUsoExclusivo(cantExclusivo);
		archivoBUA.setBahiaComunal(cantBahiaComunal);
		archivoBUA.setCubierto(cantCubierto);
		archivoBUA.setDescubierto(cantDescubierto);
		archivoBUA.setDoble(cantDoble);
		archivoBUA.setSencillo(cantSencillo);
		archivoBUA.setServidumbre(cantServidumbre); 

		if(avaluoHipotecarioDTO.getGarajes() != null){
			archivoBUA.setNumeroTotalDeGarajes(avaluoHipotecarioDTO.getGarajes().size());
		}
		
//		archivoBUA.setBahiaComunal(formatoInforme.getBahiaComunal() == null ? 0 : formatoInforme.getBahiaComunal());

		archivoBUA.setBalcon(formatoInforme.getBalcon() == null  ? 0 :formatoInforme.getBalcon());
		archivoBUA.setBanoPrivado(formatoInforme.getBanoPrivado() == null ? 0 : formatoInforme.getBanoPrivado());
		archivoBUA.setBanoServicio(formatoInforme.getBanoServicio() == null ? 0: formatoInforme.getBanoServicio()  );
		archivoBUA.setBanoSocial(formatoInforme.getBanoSocial() == null ? 0:formatoInforme.getBanoSocial());
		archivoBUA.setBodega(formatoInforme.getBodega() == null ? 0:formatoInforme.getBodega());
		archivoBUA.setCocina(formatoInforme.getCocina() == null ? 0:formatoInforme.getCocina());
		archivoBUA.setComedor(formatoInforme.getComedor() == null ? 0:formatoInforme.getComedor());
		archivoBUA.setCuartoServicio(formatoInforme.getCuartoServicio() == null ? 0:formatoInforme.getCuartoServicio());
//		archivoBUA.setCubierto(toInt(formatoInforme.getGarajeCubierto1())
//				+ toInt(formatoInforme.getGarajeCubierto2())
//				+ toInt(formatoInforme.getGarajeCubierto3())
//				+ toInt(formatoInforme.getGarajeCubierto4())
//				+ toInt(formatoInforme.getGarajeCubierto5())); 
		// Número de garaje cubierto
		//El campo es oblgatorio en BUA, pero se agregara cero cuando no se digite
		archivoBUA.setDeposito((formatoInforme.getNumeroTotalDepositos() == null) ? 0 : formatoInforme.getNumeroTotalDepositos());
		archivoBUA.setComercio((formatoInforme.getUsoPredominanteInmueble().getKey() == 2) ? 1 : 0);
		archivoBUA.setIndustria((formatoInforme.getUsoPredominanteInmueble().getKey() == 83) ? 1 : 0);
		archivoBUA.setVivienda((formatoInforme.getUsoPredominanteInmueble().getKey() == 1) ? 1 : 0);
		archivoBUA.setOtrosUsos((formatoInforme.getUsoPredominanteInmueble().getKey() == 6) ? 1 : 0);

//		int garajesDescubiertos =
//				  (formatoInforme.getGarajeCubierto1() == null ? 0 : toInt(!formatoInforme.getGarajeCubierto1()))
//				+ (formatoInforme.getGarajeCubierto2() == null ? 0 : toInt(!formatoInforme.getGarajeCubierto2()))
//				+ (formatoInforme.getGarajeCubierto3() == null ? 0 : toInt(!formatoInforme.getGarajeCubierto3()))
//				+ (formatoInforme.getGarajeCubierto4() == null ? 0 : toInt(!formatoInforme.getGarajeCubierto4()))
//				+ (formatoInforme.getGarajeCubierto5() == null ? 0 : toInt(!formatoInforme.getGarajeCubierto5()));
//
//		archivoBUA.setDescubierto(garajesDescubiertos); // Número de garaje descubierto
		//int totalGarajesDobles = informeHipotecarioController.calcularGarajesDobles(formatoInforme);
		//archivoBUA.setDoble(totalGarajesDobles);
		archivoBUA.setEstarHab(formatoInforme.getEstarHabitacion() == null ? 0:formatoInforme.getEstarHabitacion());
		archivoBUA.setEstudio(formatoInforme.getEstudio() == null ? 0:formatoInforme.getEstudio());
		archivoBUA.setHabitaciones(formatoInforme.getHabitaciones());
		archivoBUA.setJardin(formatoInforme.getJardin() == null ? 0:formatoInforme.getJardin());
		archivoBUA.setLocal(formatoInforme.getLocal()== null ? 0:formatoInforme.getLocal());
		archivoBUA.setNumeroDeAscensores(formatoInforme.getNumeroDeAscensores() == null ? 0 : formatoInforme.getNumeroDeAscensores());
		archivoBUA.setNumeroDeEdificios(formatoInforme.getNumeroDeEdificios() == null ? 0 : formatoInforme.getNumeroDeEdificios());
		archivoBUA.setOficina(formatoInforme.getOficina() == null ? 0:formatoInforme.getOficina());
		archivoBUA.setPatioInterior(formatoInforme.getPatioInterior() == null ? 0:formatoInforme.getPatioInterior());
		archivoBUA.setNumeroDePisos(formatoInforme.getNumeroDePisos());
//		archivoBUA.setPrivado(formatoInforme.getGarajePrivado());
		archivoBUA.setSala(formatoInforme.getSala() == null ? 0:formatoInforme.getSala());

		if(formatoInforme.getNumeroTotalDeGarajes()!=null){
			//archivoBUA.setSencillo(formatoInforme.getNumeroTotalDeGarajes() - totalGarajesDobles);
		}else{
			archivoBUA.setSencillo(0);
		}

//		archivoBUA.setServidumbre(toInt(formatoInforme.getGarajeServidumbre1())
//				+toInt(formatoInforme.getGarajeServidumbre2())
//				+toInt(formatoInforme.getGarajeServidumbre3())
//				+toInt(formatoInforme.getGarajeServidumbre4())
//				+toInt(formatoInforme.getGarajeServidumbre5())); 
		// Garaje servidumbre
		archivoBUA.setNumeroSotanos(formatoInforme.getNumeroSotanos() == null ? 0:formatoInforme.getNumeroSotanos());
		archivoBUA.setTerraza(formatoInforme.getTerraza() == null ? 0:formatoInforme.getTerraza());
		archivoBUA.setTiempoEsperadoDeComercializacion(formatoInforme.getTiempoEsperadoDeComercializacion());
		archivoBUA.setNumeroTotalDeGarajes(formatoInforme.getNumeroTotalDeGarajes()==null?0:formatoInforme.getNumeroTotalDeGarajes());
		archivoBUA.setTotalUnidades(formatoInforme.getTotalUnidades());
		archivoBUA.setUnidadesPorPiso(formatoInforme.getUnidadesPorPiso());
//		archivoBUA.setUsoExclusivo(formatoInforme.getGarajeExclusivo()==null?0:formatoInforme.getGarajeExclusivo());
		archivoBUA.setValorAsegurable(avaluoHipotecarioDTO.getValorAsegurable());
		archivoBUA.setVetustez(new BigDecimal(formatoInforme.getVetustez() == null ? 0:formatoInforme.getVetustez()));
		archivoBUA.setZonaVerdePrivada(formatoInforme.getZonaVerdePrivada() == null ? 0:formatoInforme.getZonaVerdePrivada());
		if (formatoInforme.getEstadoDeConstruccion() != null) {
			archivoBUA.setEstadoDeConstruccion(formatoInforme.getEstadoDeConstruccion().getKey());			
		}
		archivoBUA.setImpactoAmbientalOtro(formatoInforme.getExplicacionOtro());
		// T_AMBOTRO Ignorado
		archivoBUA.setPorcentajeAvance((formatoInforme.getPorcentajeAvance() == null || formatoInforme.getPorcentajeAvance() == 0) ? 0 : formatoInforme.getPorcentajeAvance());
		archivoBUA.setChip(formatoInforme.getChip());
		// T_CUBIERTA Ignorado
		archivoBUA.setDireccionAnexos(formatoInforme.getDireccionAnexos());
		archivoBUA.setDireccionInmueble(StringUtils.obtenerDireccion(avaluoHipotecarioDTO));
		// T_ESTRUCTURA Ignorado
		archivoBUA.setTipoFachada2("");
		archivoBUA.setMatriculaInmobiliariaPrincipal2(formatoInforme.getMatriculaInmobiliariaPrincipal2());
		archivoBUA.setMatriculaInmobiliariaDeposito1(formatoInforme.getMatriculaInmobiliariaDeposito1());
		archivoBUA.setMatriculaInmobiliariaDeposito2(formatoInforme.getMatriculaInmobiliariaDeposito2());
		
//		archivoBUA.setMatriculaInmobiliariaGaraje1(formatoInforme.getMatriculaInmobiliariaGaraje1());
//		archivoBUA.setMatriculaInmobiliariaGaraje2(formatoInforme.getMatriculaInmobiliariaGaraje2());
//		archivoBUA.setMatriculaInmobiliariaGaraje3(formatoInforme.getMatriculaInmobiliariaGaraje3());
//		archivoBUA.setMatriculaInmobiliariaGaraje4(formatoInforme.getMatriculaInmobiliariaGaraje4());
//		archivoBUA.setMatriculaInmobiliariaGaraje5(formatoInforme.getMatriculaInmobiliariaGaraje5());

		archivoBUA.setNombreDelConjunto((avaluoHipotecarioDTO.getConjunto() == null) || (avaluoHipotecarioDTO.getConjunto() != null && avaluoHipotecarioDTO.getConjunto().isEmpty())  ? Constantes.NO_APLICA : avaluoHipotecarioDTO.getConjunto());
		archivoBUA.setOtrasDirecciones(formatoInforme.getOtrasDirecciones());
		archivoBUA.setDescripcionClaseInmueble(formatoInforme.getDescripcionOtrosTipoInmueble());
		archivoBUA.setOtrosDotacion(formatoInforme.getOtros());
		// T_OTROSDOTACION2 Ignorado
		// T_OTROSUSOS Ignorado
		archivoBUA.setDescripcionOtrosUsoInmueble(formatoInforme.getDescripcionOtrosUsoInmueble()); 
		// C_ALTURA Ignorado
		// C_UBICACION3 Ignorado
		archivoBUA.setOtroSistemaDeCoordenadas(null); // Solo se usa Google Maps
		if (formatoInforme.getReparados() != null) {
			archivoBUA.setReparados(formatoInforme.getReparados().getKey());
		}
		if (formatoInforme.getIrregularidadAltura() != null)  {
			archivoBUA.setIrregularidadAltura(formatoInforme.getIrregularidadAltura().getKey());
		}
		if (formatoInforme.getIrregularidadPlanta()!=null) {
			archivoBUA.setIrregularidadPlanta(formatoInforme.getIrregularidadPlanta().getKey());
		}

		if (formatoInforme.getEstructuraReforzada()!= null) {
			archivoBUA.setEstructuraReforzada(formatoInforme.getEstructuraReforzada().getKey());
		}
		archivoBUA.setSistemaDeCoordenadas(ArchivoBUA.SISTEMA_COORDENADAS);
		if (formatoInforme.getDanosPrevios() != null) {
			archivoBUA.setDanosPrevios(formatoInforme.getDanosPrevios().getKey());
		}
		if (avaluoHipotecarioDTO.getLatitud() != null) {
			archivoBUA.setLatitud(formatter.format(avaluoHipotecarioDTO.getLatitud().setScale(6,RoundingMode.HALF_UP)));
		}
		archivoBUA.setAnoDeConstruccion(formatoInforme.getAnoDeConstruccion());
		if (formatoInforme.getMaterialConstructor() != null) {
			archivoBUA.setMaterialDeConstruccion(formatoInforme.getMaterialConstructor().getKey());
		}
	
		if (avaluoHipotecarioDTO.getLongitud() != null) {
			archivoBUA.setLongitud(formatter.format(avaluoHipotecarioDTO.getLongitud().setScale(6,RoundingMode.HALF_UP)));
		}
		
		/*Transformaciones a nulos de campos no seleccionados*/
		if (formatoInforme.getDetalleMaterial() != null) {
			archivoBUA.setTipoEstructural(formatoInforme.getDetalleMaterial().getKey() == 0 ? null : formatoInforme.getDetalleMaterial().getKey());
		}
		
		if (formatoInforme.getPisosBodega() != null) {
			archivoBUA.setPisosBodega(formatoInforme.getPisosBodega().getKey() == 0 ? null : formatoInforme.getPisosBodega().getKey());
		}
		
		if (formatoInforme.getCondicionesDeSalubridad() != null) {
			archivoBUA.setCondicionesDeSalubridad(formatoInforme.getCondicionesDeSalubridad().getKey() == 0 ? null : formatoInforme.getCondicionesDeSalubridad().getKey());
		}
		
		if (formatoInforme.getTipoFachada() != null) {
			archivoBUA.setTipoFachada(formatoInforme.getTipoFachada().getKey() == 0 ? null : formatoInforme.getTipoFachada().getKey());
		}
		
		if (formatoInforme.getEstructura() != null) {
			archivoBUA.setEstructura(formatoInforme.getEstructura().getKey() == 0 ? null : formatoInforme.getEstructura().getKey());
		}
		
		if (formatoInforme.getReparados() != null) {
			archivoBUA.setReparados(formatoInforme.getReparados().getKey() == 0 ? 1 : formatoInforme.getReparados().getKey());
		}
		
		/*Transformación de VIP a VIS*/
		if (formatoInforme.getTipoDeVivienda() != null) {
			archivoBUA.setTipoDeVivienda(formatoInforme.getTipoDeVivienda().getKey() == 3 ? 1 : formatoInforme.getTipoDeVivienda().getKey());
		}

		archivoBUA.setUsuario(ArchivoBUA.USUARIO_BUA);
		try {
			insertarAreas(avaluoHipotecarioDTO, archivoBUA);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		archivoBUA = this.eliminarCaracteresEspeciales(archivoBUA);
		return archivoBUA;
	}

	private  ArchivoBUA   eliminarCaracteresEspeciales(ArchivoBUA archivoBua) {
		try {
			Class buaClass =  archivoBua.getClass();
	        Method[]  buaMethods = buaClass.getMethods();
	        
	        for (int i = 0; i < buaMethods.length; i++) {
	             Method buaMethod = buaMethods[i];
	             if(buaMethod.getName().startsWith("get") 
	                        && ((buaMethod.getParameterTypes()).length == 0)
	                        && (!(buaMethod.getName().equals("getClass")))
	                  ){
	            	 Object value = buaMethod.invoke(archivoBua);
		             
		             if ( value instanceof String) {
		            	 String valueString = String.valueOf(value);
			             //se verifica si tiene algun caracter especial no permitido
			             //si es asi se borra. 
			             for (String caracterEspecial : CARACTERES_NO_PERMITIDOS) {
			            	String  originalValue  = valueString;
			            	valueString = valueString.replaceAll(caracterEspecial, "");
			            	if ( !originalValue.equals(valueString) ) {
			            		Object valueObject = (Object)valueString;
				            	String setMethodName =  buaMethod.getName().substring(3);
				            	setMethodName = setMethodName.substring(0,1).toLowerCase()  + setMethodName.substring(1);
				            	Field field = buaClass.getDeclaredField(setMethodName);
				                field.setAccessible(true);
				                field.set(archivoBua, valueObject);
			            	}
			             }
		             }
	             }
	        }
		}catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}	
         
		return archivoBua;
	}

	private void insertarAreas(AvaluoHipotecarioDTO avaluoHipotecarioDTO,
			ArchivoBUA archivoBUA) throws NoSuchMethodException,
			IllegalAccessException, InvocationTargetException {
		if(avaluoHipotecarioDTO.getAreas()!=null){
			AreaDTO[] areaDTOs = new AreaDTO[avaluoHipotecarioDTO.getAreas().size()];
			avaluoHipotecarioDTO.getAreas().toArray(areaDTOs);
			Class<ArchivoBUA> archivoBuaClass = ArchivoBUA.class;
			for (int i = 0;i < areaDTOs.length;i++) {
				AreaDTO area = areaDTOs[i];
				Method setDescripcion = archivoBuaClass.getDeclaredMethod("setDescripcion"+(i+1), new Class[] {Integer.class});
				if ( area.getDescripcionNumerica() != null && area.getDescripcionNumerica().getKey() == 5 ) {
					setDescripcion.invoke(archivoBUA, 22);
				}
				else {
					setDescripcion.invoke(archivoBUA, ( area.getDescripcionNumerica() == null ? null : area.getDescripcionNumerica().getKey()));
				}
				Method setArea = archivoBuaClass.getDeclaredMethod("setArea"+(i+1), new Class[] {BigDecimal.class});
				setArea.invoke(archivoBUA, area.getArea());
				Method setValorTotal = archivoBuaClass.getDeclaredMethod("setValorTotal"+(i+1), new Class[] {BigDecimal.class});
				setValorTotal.invoke(archivoBUA, area.getValorTotal());
				Method setValorUnitario = archivoBuaClass.getDeclaredMethod("setValorUnitario"+(i+1), new Class[] {BigDecimal.class});
				setValorUnitario.invoke(archivoBUA, area.getValorUnitario());
			}
		}
	}

    private void extraerMatriculaInmobiliaria(int i, GarajeDTO garaje, ArchivoBUA archivoBUA) {
		Class<ArchivoBUA> archivoBuaClass = ArchivoBUA.class;
        try {
            Method setMatriculaInmobiliariaGaraje  = archivoBuaClass
                .getDeclaredMethod(
                        "setMatriculaInmobiliariaGaraje" + Integer.toString(i),
                        new Class[] { String.class });
            setMatriculaInmobiliariaGaraje.invoke(archivoBUA,
                    garaje.getMatriculaInmobiliaria());
        } catch (NoSuchMethodException e) {
            log.error("NoSuchMethodException: Error al invocar metodos setMatriculaInmobiliariaGaraje{}",
                    Integer.toString(i));
        } catch (SecurityException e) {
            log.error("SecurityException: Error al invocar metodos setMatriculaInmobiliariaGaraje {}",
                    Integer.toString(i));
        } catch (IllegalAccessException e) {
            log.error("IllegalAccessException: Error al invocar metodos setMatriculaInmobiliariaGaraje{}",
                    Integer.toString(i));
        } catch (IllegalArgumentException e) {
            log.error("IllegalArgumentException: Error al invocar metodos setMatriculaInmobiliariaGaraje{}",
                    Integer.toString(i));
        } catch (InvocationTargetException e) {
            log.error("InvocationTargetException: Error al invocar metodos setMatriculaInmobiliariaGaraje{}",
                    Integer.toString(i));
        }
    }

}
