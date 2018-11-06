package com.helio4.bancol.avaluos.ensamblador;

import com.helio4.bancol.avaluos.dto.*;
import com.helio4.bancol.avaluos.dto.AvaluoDTO.MBR;
import com.helio4.bancol.avaluos.dto.FormatoInformeHipotecarioDTO.*;
import com.helio4.bancol.avaluos.modelo.*;
import com.helio4.bancol.avaluos.persistencia.*;
import com.helio4.bancol.avaluos.servicio.excepciones.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Component
public class FormatoInformeEnsamblador {

    private static Logger log = LoggerFactory
        .getLogger(FormatoInformeEnsamblador.class);

    @Autowired
    private FormatoInformeHipotecarioRepository formatoInformeHipotecarioRepository;

    @Autowired
    private FormatoInformeComercialRepository formatoInformeComercialRepository;

    @Autowired
    private AvaluoRepository avaluoRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private InmuebleRepository inmuebleRepository;

    @Autowired
    private ServidumbreRepository servidumbreRepository;

    @Autowired
    private ViaAccesoRepository viaAccesoRepository;

    @Autowired
    private MetodoValuacionRepository metodoValuacionRepository;

    @Autowired
    private AreaConstruccionRepository areaConstruccionRepository;

    @Autowired
    private DivipolaRepository divipolaRepository;

    @Autowired
    private AreaEnsamblador areaEnsamblador;

    @Autowired
    private InmuebleEnsamblador inmuebleEnsamblador;

    @Autowired
    private DivipolaEnsamblador divipolaEnsamblador;

    @Autowired
    private ExplotacionEconomicaEnsamblador explotacionEconomicaEnsamblador;

    @Autowired
    private ServidumbreEnsamblador servidumbreEnsamblador;

    @Autowired
    private ViaAccesoEnsamblador viaAccesoEnsamblador;

    @Autowired
    private MetodoValuacionEnsamblador metodoValuacionEnsamblador;

    @Autowired
    private AvaluoEnsamblador avaluoEnsamblador;

    public FormatoInformeHipotecario crear(Avaluo avaluo, FormatoInformeHipotecarioDTO formatoInformeDTO)
            throws DivipolaNotFoundException {
        log.debug("Ingresando a crear formatoInformeHipotecario {}",
            formatoInformeDTO);
        FormatoInformeHipotecario formatoInforme = new FormatoInformeHipotecario(avaluo);
        formatoInforme.setValorComercial(formatoInformeDTO.getValorComercial());
        formatoInforme.setValorCortesia(formatoInformeDTO.getValorCortesia());
        formatoInforme.setMetodoDeValuacion(formatoInformeDTO.getMetodoDeValuacion());
        formatoInforme.setJustificacionDeMetodologia(formatoInformeDTO.getJustificacionDeMetodologia());
        formatoInforme.setDocumentosConsultados(formatoInformeDTO.getDocumentosConsultados());
        formatoInforme.setAcueductoEnElSector(formatoInformeDTO.getAcueductoEnElSector());
        formatoInforme.setAcueductoEnElPredio(formatoInformeDTO.getAcueductoEnElPredio());
        formatoInforme.setAlcantarilladoEnElSector(formatoInformeDTO.getAlcantarilladoEnElSector());
        formatoInforme.setAlcantarilladoEnElPredio(formatoInformeDTO.getAlcantarilladoEnElPredio());
        formatoInforme.setEnergiaEnElSector(formatoInformeDTO.getEnergiaEnElSector());
        formatoInforme.setEnergiaEnElPredio(formatoInformeDTO.getEnergiaEnElPredio());
        formatoInforme.setGasEnElSector(formatoInformeDTO.getGasEnElSector());
        formatoInforme.setGasEnElPredio(formatoInformeDTO.getGasEnElPredio());
        formatoInforme.setTelefonoEnElSector(formatoInformeDTO.getTelefonoEnElSector());
        formatoInforme.setTelefonoEnElPredio(formatoInformeDTO.getTelefonoEnElPredio());
        formatoInforme.setEstrato(formatoInformeDTO.getEstrato());
        formatoInforme.setBarrioLegal(formatoInformeDTO.getBarrioLegal());
        if (formatoInformeDTO.getUsoPredominanteInmueble() != null) {
            formatoInforme.setUsoPredominanteInmueble(formatoInformeDTO.getUsoPredominanteInmueble().getKey());        	
        }
        formatoInforme.setDescUsoPredominanteInmuebleMixto(formatoInformeDTO.getDescripcionUsoPredominanteMixto());
        if (formatoInformeDTO.getTopografiaSector() !=null) {
        	formatoInforme.setTopografiaSector(formatoInformeDTO.getTopografiaSector().getKey());
        }
        if (formatoInformeDTO.getTransporte() != null) {
            formatoInforme.setTransporte(formatoInformeDTO.getTransporte().getKey());
        }
        if (formatoInformeDTO.getCondicionesDeSalubridad() != null ) {
            formatoInforme.setCondicionesDeSalubridad(formatoInformeDTO.getCondicionesDeSalubridad().getKey());
        }
        formatoInforme.setPerspectivasDeValorizacion(formatoInformeDTO.getPerspectivasDeValorizacion());
    
        formatoInforme.setImpactoAmbientalAire(formatoInformeDTO.getImpactoAmbientalAire());
        formatoInforme.setImpactoAmbientalBasura(formatoInformeDTO.getImpactoAmbientalBasura());
        formatoInforme.setImpactoAmbientalInseguridad(formatoInformeDTO.getImpactoAmbientalInseguridad());
        formatoInforme.setImpactoAmbientalRuido(formatoInformeDTO.getImpactoAmbientalRuido());
        formatoInforme.setImpactoAmbientalAguasServidas(formatoInformeDTO.getImpactoAmbientalAguasServidas());
        formatoInforme.setImpactoAmbientalOtro(formatoInformeDTO.getImpactoAmbientalOtro());
        formatoInforme.setExplicacionImpactoAmbientalAire(formatoInformeDTO.getExplicacionImpactoAmbientalAire());
        formatoInforme.setExplicacionImpactoAmbientalBasura(formatoInformeDTO.getExplicacionImpactoAmbientalBasura());
        formatoInforme.setExplicacionImpactoAmbientalInseguridad(formatoInformeDTO.getExplicacionImpactoAmbientalInseguridad());
        formatoInforme.setExplicacionImpactoAmbientalRuido(formatoInformeDTO.getExplicacionImpactoAmbientalRuido());
        formatoInforme.setExplicacionImpactoAmbientalAguasServidas(formatoInformeDTO.getExplicacionImpactoAmbientalAguasServidas());
        formatoInforme.setExplicacionOtro(formatoInformeDTO.getExplicacionOtro());
        
		if (formatoInformeDTO.getEstadoDeLasVias() != null) {
			formatoInforme.setEstadoDeLasVias(formatoInformeDTO.getEstadoDeLasVias().getKey());
		}
		
        formatoInforme.setViasPavimentadas(formatoInformeDTO.getViasPavimentadas());
        formatoInforme.setAndenesEnLasVias(formatoInformeDTO.getAndenesEnLasVias());
        formatoInforme.setSardinelesEnLasVias(formatoInformeDTO.getSardinelesEnLasVias());
        formatoInforme.setParques(formatoInformeDTO.getParques());
        formatoInforme.setParadero(formatoInformeDTO.getParadero());
        formatoInforme.setAlumbrado(formatoInformeDTO.getAlumbrado());
        formatoInforme.setZonasVerdesPublicas(formatoInformeDTO.getZonasVerdesPublicas());
        formatoInforme.setArborizacion(formatoInformeDTO.getArborizacion());
        formatoInforme.setAlamedas(formatoInformeDTO.getAlamedas());
        formatoInforme.setCicloRutas(formatoInformeDTO.getCicloRutas());
        if (formatoInformeDTO.getClaseInmueble() != null) {
            formatoInforme.setClaseInmueble(formatoInformeDTO.getClaseInmueble().getKey());
        }
        formatoInforme.setDescripcionOtrosTipoInmueble(formatoInformeDTO.getDescripcionOtrosTipoInmueble());
        if (formatoInformeDTO.getUsoActual() != null) {
        	   formatoInforme.setUsoActual(formatoInformeDTO.getUsoActual().getKey());
        }
        formatoInforme.setDescripcionOtrosUsoInmueble(formatoInformeDTO.getDescripcionOtrosUsoInmueble());
        if (formatoInformeDTO.getTipoDeVivienda() != null) {
            formatoInforme.setTipoDeVivienda(formatoInformeDTO.getTipoDeVivienda().getKey());
        }
        formatoInforme.setDescripcionOtrosTipoVivienda(formatoInformeDTO.getDescripcionOtrosTipoVivienda());
        formatoInforme.setChip(formatoInformeDTO.getChip());
        formatoInforme.setMatriculaInmobiliariaPrincipal2(formatoInformeDTO.getMatriculaInmobiliariaPrincipal2());
        formatoInforme.setNumeroDeEscritura(formatoInformeDTO.getNumeroDeEscritura());
        formatoInforme.setNumeroNotariaDeEscritura(formatoInformeDTO.getNumeroNotariaDeEscritura());
        formatoInforme.setFechaDeEscritura(formatoInformeDTO.getFechaDeEscritura());
        formatoInforme.setDepartamentoNotaria(formatoInformeDTO.getDepartamentoNotaria());
        if (formatoInformeDTO.getCiudadNotaria() != null) {
            Divipola ciudadNotaria = divipolaRepository.findOne(formatoInformeDTO.getCiudadNotaria().getId());
            if (ciudadNotaria == null) {
                throw new DivipolaNotFoundException();
            }
            formatoInforme.setCiudadNotaria(ciudadNotaria);
        }
//        formatoInforme.setMatriculaInmobiliariaGaraje1(formatoInformeDTO.getMatriculaInmobiliariaGaraje1());
//        formatoInforme.setMatriculaInmobiliariaGaraje2(formatoInformeDTO.getMatriculaInmobiliariaGaraje2());
//        formatoInforme.setMatriculaInmobiliariaGaraje3(formatoInformeDTO.getMatriculaInmobiliariaGaraje3());
//        formatoInforme.setMatriculaInmobiliariaGaraje4(formatoInformeDTO.getMatriculaInmobiliariaGaraje4());
//        formatoInforme.setMatriculaInmobiliariaGaraje5(formatoInformeDTO.getMatriculaInmobiliariaGaraje5());
//        formatoInforme.setGarajeParalelo1(formatoInformeDTO.getGarajeParalelo1());
//        formatoInforme.setGarajeParalelo2(formatoInformeDTO.getGarajeParalelo2());
//        formatoInforme.setGarajeParalelo3(formatoInformeDTO.getGarajeParalelo3());
//        formatoInforme.setGarajeParalelo4(formatoInformeDTO.getGarajeParalelo4());
//        formatoInforme.setGarajeParalelo5(formatoInformeDTO.getGarajeParalelo5());
//        formatoInforme.setGarajeCubierto1(formatoInformeDTO.getGarajeCubierto1());
//        formatoInforme.setGarajeCubierto2(formatoInformeDTO.getGarajeCubierto2());
//        formatoInforme.setGarajeCubierto3(formatoInformeDTO.getGarajeCubierto3());
//        formatoInforme.setGarajeCubierto4(formatoInformeDTO.getGarajeCubierto4());
//        formatoInforme.setGarajeCubierto5(formatoInformeDTO.getGarajeCubierto5());
//        formatoInforme.setNumeroParqueadero1(formatoInformeDTO.getNumeroParqueadero1());
//        formatoInforme.setNumeroParqueadero2(formatoInformeDTO.getNumeroParqueadero2());
//        formatoInforme.setNumeroParqueadero3(formatoInformeDTO.getNumeroParqueadero3());
//        formatoInforme.setNumeroParqueadero4(formatoInformeDTO.getNumeroParqueadero4());
//        formatoInforme.setNumeroParqueadero5(formatoInformeDTO.getNumeroParqueadero5());
//        formatoInforme.setNumeroParqueaderoExclusivo1(formatoInformeDTO.getNumeroParqueaderoExclusivo1());
//        formatoInforme.setNumeroParqueaderoExclusivo2(formatoInformeDTO.getNumeroParqueaderoExclusivo2());
//        formatoInforme.setNumeroParqueaderoExclusivo3(formatoInformeDTO.getNumeroParqueaderoExclusivo3());
//        formatoInforme.setNumeroParqueaderoExclusivo4(formatoInformeDTO.getNumeroParqueaderoExclusivo4());
//        formatoInforme.setNumeroParqueaderoExclusivo5(formatoInformeDTO.getNumeroParqueaderoExclusivo5());
//        formatoInforme.setGarajeServidumbre1(formatoInformeDTO.getGarajeServidumbre1());
//        formatoInforme.setGarajeServidumbre2(formatoInformeDTO.getGarajeServidumbre2());
//        formatoInforme.setGarajeServidumbre3(formatoInformeDTO.getGarajeServidumbre3());
//        formatoInforme.setGarajeServidumbre4(formatoInformeDTO.getGarajeServidumbre4());
//        formatoInforme.setGarajeServidumbre5(formatoInformeDTO.getGarajeServidumbre5());
        formatoInforme.setMatriculaInmobiliariaDeposito1(formatoInformeDTO.getMatriculaInmobiliariaDeposito1());
        formatoInforme.setMatriculaInmobiliariaDeposito2(formatoInformeDTO.getMatriculaInmobiliariaDeposito2());
        formatoInforme.setMatriculaInmobiliariaDeposito3(formatoInformeDTO.getMatriculaInmobiliariaDeposito3());
        formatoInforme.setMatriculaInmobiliariaDeposito4(formatoInformeDTO.getMatriculaInmobiliariaDeposito4());
        formatoInforme.setMatriculaInmobiliariaDeposito5(formatoInformeDTO.getMatriculaInmobiliariaDeposito5());
        formatoInforme.setNumeroDeposito1(formatoInformeDTO.getNumeroDeposito1());
        formatoInforme.setNumeroDeposito2(formatoInformeDTO.getNumeroDeposito2());
        formatoInforme.setNumeroDeposito3(formatoInformeDTO.getNumeroDeposito3());
        formatoInforme.setNumeroDeposito4(formatoInformeDTO.getNumeroDeposito4());
        formatoInforme.setNumeroDeposito5(formatoInformeDTO.getNumeroDeposito5());
        formatoInforme.setNumeroDepositoExclusivo1(formatoInformeDTO.getNumeroDepositoExclusivo1());
        formatoInforme.setNumeroDepositoExclusivo2(formatoInformeDTO.getNumeroDepositoExclusivo2());
        formatoInforme.setNumeroDepositoExclusivo3(formatoInformeDTO.getNumeroDepositoExclusivo3());
        formatoInforme.setNumeroDepositoExclusivo4(formatoInformeDTO.getNumeroDepositoExclusivo4());
        formatoInforme.setNumeroDepositoExclusivo5(formatoInformeDTO.getNumeroDepositoExclusivo5());
        formatoInforme.setNumeroDePisos(formatoInformeDTO.getNumeroDePisos());
        formatoInforme.setNumeroSotanos(formatoInformeDTO.getNumeroSotanos());
        formatoInforme.setVetustez(formatoInformeDTO.getVetustez());
        if (formatoInformeDTO.getEstadoDeConservacion() != null) {
        	 formatoInforme.setEstadoDeConservacion(formatoInformeDTO.getEstadoDeConservacion().getKey());
        }
        if (formatoInformeDTO.getEstadoDeConstruccion() != null) {
            formatoInforme.setEstadoDeConstruccion(formatoInformeDTO.getEstadoDeConstruccion().getKey());
        }
        if (formatoInformeDTO.getEstadoDeObra() != null) {
            formatoInforme.setEstadoDeObra(formatoInformeDTO.getEstadoDeObra() ? 1 : 0);
        }
        formatoInforme.setAnoDeConstruccion(formatoInformeDTO.getAnoDeConstruccion());
        formatoInforme.setPorcentajeAvance(formatoInformeDTO.getPorcentajeAvance());
        formatoInforme.setRemodelado(formatoInformeDTO.getRemodelado());
        formatoInforme.setFechaRemodelacion(formatoInformeDTO.getFechaRemodelacion());
        if (formatoInformeDTO.getEstructura() != null) {
            formatoInforme.setEstructura(formatoInformeDTO.getEstructura().getKey());	
        }
        if (formatoInformeDTO.getEstructuraReforzada()!=null) {
        	formatoInforme.setEstructuraReforzada(formatoInformeDTO.getEstructuraReforzada().getKey());
        }
        if (formatoInformeDTO.getFachada() != null) {
            formatoInforme.setFachada(formatoInformeDTO.getFachada().getKey());
        }
        if (formatoInformeDTO.getTipoFachada() != null) {
            formatoInforme.setTipoFachada(formatoInformeDTO.getTipoFachada().getKey());
        }
        if (formatoInformeDTO.getCubierta() != null) {
            formatoInforme.setCubierta(formatoInformeDTO.getCubierta().getKey());
        }
        if (formatoInformeDTO.getPisosBodega() !=null) {
            formatoInforme.setPisosBodega(formatoInformeDTO.getPisosBodega().getKey());        	
        } 
        formatoInforme.setTipologiaViviendaUnicaFamiliar(formatoInformeDTO.getTipologiaViviendaUnicaFamiliar());
        formatoInforme.setSala(formatoInformeDTO.getSala());
        formatoInforme.setDeposito(formatoInformeDTO.getDeposito());
        formatoInforme.setComedor(formatoInformeDTO.getComedor());
        formatoInforme.setEstudio(formatoInformeDTO.getEstudio());
        formatoInforme.setJardin(formatoInformeDTO.getJardin());
        formatoInforme.setBanoSocial(formatoInformeDTO.getBanoSocial());
        formatoInforme.setEstarHabitacion(formatoInformeDTO.getEstarHabitacion());
        formatoInforme.setHabitaciones(formatoInformeDTO.getHabitaciones());
        formatoInforme.setDepositosPrivados(formatoInformeDTO.getDepositosPrivados());
        formatoInforme.setDepositosExclusivos(formatoInformeDTO.getDepositosExclusivos());
        formatoInforme.setNumeroTotalDepositos(formatoInformeDTO.getNumeroTotalDepositos());
        formatoInforme.setBanoPrivado(formatoInformeDTO.getBanoPrivado());
        formatoInforme.setCocina(formatoInformeDTO.getCocina());
        formatoInforme.setCuartoServicio(formatoInformeDTO.getCuartoServicio());
        formatoInforme.setOficina(formatoInformeDTO.getOficina());
        formatoInforme.setBanoServicio(formatoInformeDTO.getBanoServicio());
        formatoInforme.setPatioInterior(formatoInformeDTO.getPatioInterior());
        formatoInforme.setTerraza(formatoInformeDTO.getTerraza());
        formatoInforme.setBodega(formatoInformeDTO.getBodega());
        formatoInforme.setZonaDeRopas(formatoInformeDTO.getZonaDeRopas());
        formatoInforme.setBalcon(formatoInformeDTO.getBalcon());
        formatoInforme.setCloset(formatoInformeDTO.getCloset());
        formatoInforme.setLocal(formatoInformeDTO.getLocal());
        formatoInforme.setZonaVerdePrivada(formatoInformeDTO.getZonaVerdePrivada());
        if (formatoInformeDTO.getIluminacion() !=null) {
        	formatoInforme.setIluminacion(formatoInformeDTO.getIluminacion().getKey());
        }
        if (formatoInformeDTO.getVentilacion() != null) {
        	 formatoInforme.setVentilacion(formatoInformeDTO.getVentilacion().getKey());
        }
       
//        formatoInforme.setGarajePrivado(formatoInformeDTO.getGarajePrivado());
//        formatoInforme.setGarajeExclusivo(formatoInformeDTO.getGarajeExclusivo());
//        formatoInforme.setBahiaComunal(formatoInformeDTO.getBahiaComunal());
//        formatoInforme.setGarajeDoble1(formatoInformeDTO.getGarajeDoble1());
//        formatoInforme.setGarajeDoble2(formatoInformeDTO.getGarajeDoble2());
//        formatoInforme.setGarajeDoble3(formatoInformeDTO.getGarajeDoble3());
//        formatoInforme.setGarajeDoble4(formatoInformeDTO.getGarajeDoble4());
//        formatoInforme.setGarajeDoble5(formatoInformeDTO.getGarajeDoble5());
//        formatoInforme.setGarajeParalelo(formatoInformeDTO.getGarajeParalelo());
        formatoInforme.setNumeroTotalDeGarajes(formatoInformeDTO.getNumeroTotalDeGarajes());
        formatoInforme.setTotalCuposParquedaro(formatoInformeDTO.getTotalCuposParquedaro());
        if (formatoInformeDTO.getEstadoAcabadosPisos() != null) {
            formatoInforme.setEstadoAcabadosPisos(formatoInformeDTO.getEstadoAcabadosPisos().getKey());
        }
        if (formatoInformeDTO.getEstadoAcabadosMuros() != null) {
        	formatoInforme.setEstadoAcabadosMuros(formatoInformeDTO.getEstadoAcabadosMuros().getKey());
        }
        if (formatoInformeDTO.getEstadoAcabadosTechos() != null) {
        	formatoInforme.setEstadoAcabadosTechos(formatoInformeDTO.getEstadoAcabadosTechos().getKey());
        }
        if (formatoInformeDTO.getEstadoAcabadosMadera() != null) {
        	formatoInforme.setEstadoAcabadosMadera(formatoInformeDTO.getEstadoAcabadosMadera().getKey());
        }
        if (formatoInformeDTO.getEstadoAcabadosMetal() != null) {
        	formatoInforme.setEstadoAcabadosMetal(formatoInformeDTO.getEstadoAcabadosMetal().getKey());
        }
        if (formatoInformeDTO.getEstadoAcabadosBanos() != null) {
        	formatoInforme.setEstadoAcabadosBanos(formatoInformeDTO.getEstadoAcabadosBanos().getKey());
        }
        if (formatoInformeDTO.getEstadoAcabadosCocina() != null) {
        	formatoInforme.setEstadoAcabadosCocina(formatoInformeDTO.getEstadoAcabadosCocina().getKey());
        }
        if (formatoInformeDTO.getCalidadAcabadosPisos() != null) {
            formatoInforme.setCalidadAcabadosPisos(formatoInformeDTO.getCalidadAcabadosPisos().getKey());
        }
        if (formatoInformeDTO.getCalidadAcabadosMuros() != null) {
        	formatoInforme.setCalidadAcabadosMuros(formatoInformeDTO.getCalidadAcabadosMuros().getKey());	
        }
        if (formatoInformeDTO.getCalidadAcabadosTechos() != null) {
        	formatoInforme.setCalidadAcabadosTechos(formatoInformeDTO.getCalidadAcabadosTechos().getKey());	
        }
        if (formatoInformeDTO.getCalidadAcabadosMadera() != null) {
        	formatoInforme.setCalidadAcabadosMadera(formatoInformeDTO.getCalidadAcabadosMadera().getKey());	
        }
        if (formatoInformeDTO.getCalidadAcabadosMetal() != null) {
        	formatoInforme.setCalidadAcabadosMetal(formatoInformeDTO.getCalidadAcabadosMetal().getKey());
        }
        if (formatoInformeDTO.getCalidadAcabadosBanos() != null) {
            formatoInforme.setCalidadAcabadosBanos(formatoInformeDTO.getCalidadAcabadosBanos().getKey());
        }
        if (formatoInformeDTO.getCalidadAcabadosCocina() != null) {
        	formatoInforme.setCalidadAcabadosCocina(formatoInformeDTO.getCalidadAcabadosCocina().getKey());
        }
        
        formatoInforme.setSometidoAPropiedadHorizontal(formatoInformeDTO.getSometidoAPropiedadHorizontal());
        formatoInforme.setConjuntoCerrado(formatoInformeDTO.getConjuntoCerrado());
        if (formatoInformeDTO.getUbicacionDelInmueble() != null) {
            formatoInforme.setUbicacionDelInmueble(formatoInformeDTO.getUbicacionDelInmueble().getKey());
        }
        formatoInforme.setNumeroDeEdificios(formatoInformeDTO.getNumeroDeEdificios());
        formatoInforme.setUnidadesPorPiso(formatoInformeDTO.getUnidadesPorPiso());
        formatoInforme.setTotalUnidades(formatoInformeDTO.getTotalUnidades());
        formatoInforme.setPorteria(formatoInformeDTO.getPorteria());
        formatoInforme.setCitofono(formatoInformeDTO.getCitofono());
        formatoInforme.setBicicletero(formatoInformeDTO.getBicicletero());
        formatoInforme.setPiscina(formatoInformeDTO.getPiscina());
        formatoInforme.setTanqueDeAgua(formatoInformeDTO.getTanqueDeAgua());
        formatoInforme.setClubHouse(formatoInformeDTO.getClubHouse());
        formatoInforme.setGarajeVisitantes(formatoInformeDTO.getGarajeVisitantes());
        formatoInforme.setJuegosNinos(formatoInformeDTO.getJuegosNinos());
        formatoInforme.setCanchaMultiple(formatoInformeDTO.getCanchaMultiple());
        formatoInforme.setBombaEyectora(formatoInformeDTO.getBombaEyectora());
        formatoInforme.setAireAcondicionadoCentral(formatoInformeDTO.getAireAcondicionadoCentral());
        formatoInforme.setCanchaSquash(formatoInformeDTO.getCanchaSquash());
        formatoInforme.setZonasVerdesComunales(formatoInformeDTO.getZonasVerdesComunales());
        formatoInforme.setGimnasio(formatoInformeDTO.getGimnasio());
        formatoInforme.setGolfito(formatoInformeDTO.getGolfito());
        formatoInforme.setSalonComunal(formatoInformeDTO.getSalonComunal());
        formatoInforme.setShutBasuras(formatoInformeDTO.getShutBasuras());
        formatoInforme.setEquipoDePresionConstante(formatoInformeDTO.getEquipoDePresionConstante());
        formatoInforme.setPlantaElectrica(formatoInformeDTO.getPlantaElectrica());
        formatoInforme.setAscensor(formatoInformeDTO.getAscensor());
        formatoInforme.setNumeroDeAscensores(formatoInformeDTO.getNumeroDeAscensores());
        formatoInforme.setOtros(formatoInformeDTO.getOtros());
        formatoInforme.setActualidadEdificadora(formatoInformeDTO.getActualidadEdificadora());
        formatoInforme.setEntregaDeDocumentos(formatoInformeDTO.getEntregaDeDocumentos());
        formatoInforme.setComportamientoOfertaDemanda(formatoInformeDTO.getComportamientoOfertaDemanda());
        formatoInforme.setTiempoEsperadoDeComercializacion(formatoInformeDTO.getTiempoEsperadoDeComercializacion());
        formatoInforme.setPorcentajeTerreno(formatoInformeDTO.getPorcentajeTerreno());
        formatoInforme.setValorProporcionalTerreno(formatoInformeDTO.getValorProporcionalTerreno());
        formatoInforme.setValorProporcionalConstruccion(formatoInformeDTO.getValorProporcionalConstruccion());
        formatoInforme.setValorIntegralTerreno(formatoInformeDTO.getValorIntegralTerreno());
        formatoInforme.setValorIntegralConstruccion(formatoInformeDTO.getValorIntegralConstruccion());
        formatoInforme.setObservaciones(formatoInformeDTO.getObservaciones());
        formatoInforme.setDireccionAnexos(formatoInformeDTO.getDireccionAnexos());
        formatoInforme.setOtrasDirecciones(formatoInformeDTO.getOtrasDirecciones());
        formatoInforme.setLicenciaConstruccion(formatoInformeDTO.getLicenciaConstruccion());
        if (formatoInformeDTO.getTipoLicencia() != null) {
            formatoInforme.setTipoLicencia(formatoInformeDTO.getTipoLicencia().getKey());
        }
        formatoInforme.setNumeroLicencia(formatoInformeDTO.getNumeroLicencia());
        if (formatoInformeDTO.getReparados() != null) {
            formatoInforme.setReparados(formatoInformeDTO.getReparados().getKey());
        }
        if (formatoInformeDTO.getDanosPrevios() != null ) {
            formatoInforme.setDanosPrevios(formatoInformeDTO.getDanosPrevios().getKey());
        }
        if (formatoInformeDTO.getMaterialConstructor() != null ) {
        	formatoInforme.setMaterialConstructor(formatoInformeDTO.getMaterialConstructor().getKey());
        }
        if (formatoInformeDTO.getDetalleMaterial() != null) {
            formatoInforme.setDetalleMaterial(formatoInformeDTO.getDetalleMaterial().getKey());
        }
        if (formatoInformeDTO.getIrregularidadPlanta() != null) {
            formatoInforme.setIrregularidadPlanta(formatoInformeDTO.getIrregularidadPlanta().getKey());
        }
        if (formatoInformeDTO.getIrregularidadAltura() != null ) {
            formatoInforme.setIrregularidadAltura(formatoInformeDTO.getIrregularidadAltura().getKey());
        }
        formatoInforme.setCoeficiente(formatoInformeDTO.getCoeficiente());
        formatoInforme.setAreaConstruida(formatoInformeDTO.getAreaConstruida());
        formatoInforme.setAreaPrivada(formatoInformeDTO.getAreaPrivada());
        formatoInforme.setObservacionesGarajes( formatoInformeDTO.getObservacionesGarajes() );
        formatoInforme.setNumeroPiso(formatoInformeDTO.getNumeroPiso());
        formatoInforme.setOcupante(formatoInformeDTO.getOcupante());
        formatoInforme.setCondicionPh(formatoInformeDTO.getCondicionPh());
        formatoInforme.setTeatrino(formatoInformeDTO.getTeatrino());
        formatoInforme.setSauna(formatoInformeDTO.getSauna());
        formatoInforme.setCalefaccion(formatoInformeDTO.getCalefaccion());
        formatoInforme.setTerrazaComunal(formatoInformeDTO.getTerrazaComunal());
        formatoInforme.setTurco(formatoInformeDTO.getTurco());
        formatoInforme.setBbq(formatoInformeDTO.getBbq());
        formatoInforme.setGuarderia(formatoInformeDTO.getGuarderia());
        formatoInforme.setJardinInfantil(formatoInformeDTO.getJardinInfantil());
        formatoInforme.setVigilanciaPrivada(Boolean.TRUE.equals(formatoInformeDTO.getEstadoVigilancia()) ? formatoInformeDTO.getVigilanciaPrivada(): null);
        formatoInforme.setAdministracion(formatoInformeDTO.getAdministracion());
        formatoInforme.setValorAdministracion(Boolean.TRUE.equals(formatoInformeDTO.getAdministracion()) ?formatoInformeDTO.getValorAdministracion() : null);
        formatoInforme.setDecretoAcuerdo(formatoInformeDTO.getDecretoAcuerdo());
        formatoInforme.setObservacionDecretoAcuerdo(formatoInformeDTO.getObservacionDecretoAcuerdo());
        formatoInforme.setAlturaPermitida(formatoInformeDTO.getAlturaPermitida());
        formatoInforme.setObservacionAlturaPermitida(formatoInformeDTO.getObservacionAlturaPermitida());
        formatoInforme.setUsoPrincipal(formatoInformeDTO.getUsoPrincipal());
        formatoInforme.setObservacionUsoPrincipal(formatoInformeDTO.getObservacionUsoPrincipal());
        formatoInforme.setAislamientoPosterior(formatoInformeDTO.getAislamientoPosterior());
        formatoInforme.setObservacionAislamientoPosterior(formatoInformeDTO.getObservacionAislamientoPosterior());
        formatoInforme.setAislamientoLateral(formatoInformeDTO.getAislamientoLateral());
        formatoInforme.setObservacionAislamientoLateral(formatoInformeDTO.getObservacionAislamientoLateral());
        formatoInforme.setAnteJardin(formatoInformeDTO.getAnteJardin());
        formatoInforme.setObservacionAnteJardin(formatoInformeDTO.getObservacionAnteJardin());
        formatoInforme.setIndiceOcupacion(formatoInformeDTO.getIndiceOcupacion());
        formatoInforme.setObservacionIndiceOcupacion(formatoInformeDTO.getObservacionIndiceOcupacion());
        formatoInforme.setIndiceConstruccion(formatoInformeDTO.getIndiceConstruccion());
        formatoInforme.setObservacionIndiceConstruccion(formatoInformeDTO.getObservacionIndiceConstruccion());
        formatoInforme.setPredioSubdivididoFisicamente(formatoInformeDTO.getPredioSubdivididoFisicamente());
        formatoInforme.setObservacionPredioSubdivididoFisicamente(formatoInformeDTO.getObservacionPredioSubdivididoFisicamente());
        formatoInforme.setEstadoConservacionAlcantarrillado(formatoInformeDTO.getEstadoConservacionAlcantarrillado());
        formatoInforme.setEstadoConservacionAcueducto(formatoInformeDTO.getEstadoConservacionAcueducto());
        formatoInforme.setEstadoConservacionGas(formatoInformeDTO.getEstadoConservacionGas());
        formatoInforme.setEstadoConservacionEnergia(formatoInformeDTO.getEstadoConservacionEnergia());
        formatoInforme.setEstadoConservacionTelefono(formatoInformeDTO.getEstadoConservacionTelefono());
        formatoInforme.setEstadoConservacionViasPavimentadas(formatoInformeDTO.getEstadoConservacionViasPavimentadas());
        formatoInforme.setEstadoConservacionSardinelesEnLasVias(formatoInformeDTO.getEstadoConservacionSardinelesEnLasVias());
        formatoInforme.setEstadoConservacionAndenesEnLasVias(formatoInformeDTO.getEstadoConservacionAndenesEnLasVias());
        formatoInforme.setDemandaInteres(formatoInformeDTO.getDemandaInteres());
        formatoInforme.setNivelEquipamientoComercial(formatoInformeDTO.getNivelEquipamientoComercial());
        formatoInforme.setNivelEquipamientoEscolar(formatoInformeDTO.getNivelEquipamientoEscolar());
        formatoInforme.setNivelEquipamientoAsistencial(formatoInformeDTO.getNivelEquipamientoAsistencial());
        formatoInforme.setNivelEquipamientoEstacionamiento(formatoInformeDTO.getNivelEquipamientoEstacionamiento());
        formatoInforme.setNivelEquipamientoAreasVerdes(formatoInformeDTO.getNivelEquipamientoAreasVerdes());
        formatoInforme.setNivelEquipamientoZonasRecreativas(formatoInformeDTO.getNivelEquipamientoZonasRecreativas());
        formatoInforme.setDistanciaAproximadaComercial(formatoInformeDTO.getDistanciaAproximadaComercial());
        formatoInforme.setDistanciaAproximadaEscolar(formatoInformeDTO.getDistanciaAproximadaEscolar());
        formatoInforme.setDistanciaAproximadaAsistencial(formatoInformeDTO.getDistanciaAproximadaAsistencial());
        formatoInforme.setDistanciaAproximadaEstacionamiento(formatoInformeDTO.getDistanciaAproximadaEstacionamiento());
        formatoInforme.setDistanciaAproximadaAreasVerdes(formatoInformeDTO.getDistanciaAproximadaAreasVerdes());
        formatoInforme.setDistanciaAproximadaZonasRecreativas(formatoInformeDTO.getDistanciaAproximadaZonasRecreativas());
        formatoInforme.setDescripcionGeneralSector(formatoInformeDTO.getDescripcionGeneralSector());
        formatoInforme.setObservacionesGeneralesConstruccion(formatoInformeDTO.getObservacionesGeneralesConstruccion());
        formatoInforme.setObservacionesGeneralesInmueble(formatoInformeDTO.getObservacionesGeneralesInmueble());
        formatoInforme.setObservacionesEstructura(formatoInformeDTO.getObservacionesEstructura());
        formatoInforme.setObservacionesDependencias(formatoInformeDTO.getObservacionesDependencias());
        formatoInforme.setObservacionesAcabados(formatoInformeDTO.getObservacionesAcabados());
        formatoInforme.setDescripcionGeneral(formatoInformeDTO.getDescripcionGeneral());
        formatoInforme.setAreaActividad(formatoInformeDTO.getAreaActividad());
        formatoInforme.setUsoPrincipalPh(formatoInformeDTO.getUsoPrincipalPh());
        formatoInforme.setRph(formatoInformeDTO.getRph());
        formatoInforme.setMostrarPredioSubdivididoFisicamente(formatoInformeDTO.getMostrarPredioSubdivididoFisicamente());
        formatoInforme.setUnidades(formatoInformeDTO.getUnidades());
        formatoInforme.setContadoresAgua(formatoInformeDTO.getContadoresAgua());
        formatoInforme.setContadoresLuz(formatoInformeDTO.getContadoresLuz());
        formatoInforme.setAccesos(formatoInformeDTO.getAccesos());
        
        formatoInforme.setTfc1(formatoInformeDTO.getTfc1());
        formatoInforme.setTfc2(formatoInformeDTO.getTfc2());
        formatoInforme.setTfc3(formatoInformeDTO.getTfc3());
        formatoInforme.setTfc4(formatoInformeDTO.getTfc4());
        formatoInforme.setTfc5(formatoInformeDTO.getTfc5());
        formatoInforme.setTfc6(formatoInformeDTO.getTfc6());
        formatoInforme.setTfc7(formatoInformeDTO.getTfc7());
        formatoInforme.setTfc8(formatoInformeDTO.getTfc8());
        formatoInforme.setTfc9(formatoInformeDTO.getTfc9());
        formatoInforme.setTfc10(formatoInformeDTO.getTfc10());
        
        return formatoInforme;
    }

    public FormatoInformeComercial crear(Avaluo avaluo, FormatoInformeComercialDTO
            formatoInformeComercialDTO) throws InmuebleNotFoundException,
            ServidumbreNotFoundException, ViaAccesoNotFoundException,
            MetodoValuacionNotFoundException {
        FormatoInformeComercial formatoInformeComercial = new FormatoInformeComercial(avaluo);
        formatoInformeComercial.setVereda(formatoInformeComercialDTO.getVereda());
        formatoInformeComercial.setNombrePredio(formatoInformeComercialDTO.getNombrePredio());
        formatoInformeComercial.setLocalizacion(formatoInformeComercialDTO.getLocalizacion());
        formatoInformeComercial.setDestinatarioDeLaValuacion(formatoInformeComercialDTO.getDestinatarioDeLaValuacion());
        formatoInformeComercial.setObservacionesTipoInmueble(formatoInformeComercialDTO.getObservacionesTipoInmueble());
        formatoInformeComercial.setUsoActualDelInmueble(formatoInformeComercialDTO.getUsoActualDelInmueble());
        formatoInformeComercial.setObservacionesUsoActualDelInmueble(formatoInformeComercialDTO.getObservacionesUsoActualDelInmueble());
        formatoInformeComercial.setUsoProyectadoDelInmueble(formatoInformeComercialDTO.getUsoProyectadoDelInmueble());
        formatoInformeComercial.setObservacionesUsoProyectadoDelInmueble(formatoInformeComercialDTO.getObservacionesUsoProyectadoDelInmueble());
        formatoInformeComercial.setPeriodoDeMercadeo(formatoInformeComercialDTO.getPeriodoDeMercadeo());
        formatoInformeComercial.setObservacionesPeriodoDeMercadeo(formatoInformeComercialDTO.getObservacionesPeriodoDeMercadeo());
        formatoInformeComercial.setDocumentosConsultados(formatoInformeComercialDTO.getDocumentosConsultados());
        formatoInformeComercial.setFechaAporteDeDocumentos(formatoInformeComercialDTO.getFechaAporteDeDocumentos());
        formatoInformeComercial.setOtrosDocumentos(formatoInformeComercialDTO.getOtrosDocumentos());
        formatoInformeComercial.setObservacionesDeTitulacion(formatoInformeComercialDTO.getObservacionesDeTitulacion());
        formatoInformeComercial.setDescripcionGeneralMunicipioOSector(formatoInformeComercialDTO.getDescripcionGeneralMunicipioOSector());
        formatoInformeComercial.setUbicacionDelSector(formatoInformeComercialDTO.getUbicacionDelSector());
        formatoInformeComercial.setLimiteNorte(formatoInformeComercialDTO.getLimiteNorte());
        formatoInformeComercial.setLimiteSur(formatoInformeComercialDTO.getLimiteSur());
        formatoInformeComercial.setLimiteOriente(formatoInformeComercialDTO.getLimiteOriente());
        formatoInformeComercial.setLimiteOccidente(formatoInformeComercialDTO.getLimiteOccidente());
        formatoInformeComercial.setBarriosCercanos(formatoInformeComercialDTO.getBarriosCercanos());
        formatoInformeComercial.setUsoPredominanteDelSector(formatoInformeComercialDTO.getUsoPredominanteDelSector());
        formatoInformeComercial.setComercializacion(formatoInformeComercialDTO.getComercializacion());
        formatoInformeComercial.setTiempoEsperadoDeComercializacion(formatoInformeComercialDTO.getTiempoEsperadoDeComercializacion());
        formatoInformeComercial.setEstrato(formatoInformeComercialDTO.getEstrato());
        formatoInformeComercial.setCaracteristicasSocioEconomicas(formatoInformeComercialDTO.getCaracteristicasSocioEconomicas());
        formatoInformeComercial.setOrdenPublico(formatoInformeComercialDTO.getOrdenPublico());
        formatoInformeComercial.setObservacionesViasDeAcceso(formatoInformeComercialDTO.getObservacionesViasDeAcceso());
        formatoInformeComercial.setEquipamientoDeRedVial(formatoInformeComercialDTO.getEquipamientoDeRedVial());
        formatoInformeComercial.setEstadoDeConservacion(formatoInformeComercialDTO.getEstadoDeConservacion());
        formatoInformeComercial.setUsoDelSuelo(formatoInformeComercialDTO.getUsoDelSuelo());
        formatoInformeComercial.setInfraestructuraUrbanistica(formatoInformeComercialDTO.getInfraestructuraUrbanistica());
        formatoInformeComercial.setInfraestructuraDotacional(formatoInformeComercialDTO.getInfraestructuraDotacional());
        formatoInformeComercial.setAmoblamientoUrbano(formatoInformeComercialDTO.getAmoblamientoUrbano());
        formatoInformeComercial.setAndenesYSardineles(formatoInformeComercialDTO.getAndenesYSardineles());
        formatoInformeComercial.setZonasVerdes(formatoInformeComercialDTO.getZonasVerdes());
        formatoInformeComercial.setTopografia(formatoInformeComercialDTO.getTopografia());
        formatoInformeComercial.setAlumbradoPublico(formatoInformeComercialDTO.getAlumbradoPublico());
        formatoInformeComercial.setAlcantarillado(formatoInformeComercialDTO.getAlcantarillado());
        formatoInformeComercial.setAcueducto(formatoInformeComercialDTO.getAcueducto());
        formatoInformeComercial.setEnergia(formatoInformeComercialDTO.getEnergia());
        formatoInformeComercial.setGas(formatoInformeComercialDTO.getGas());
        formatoInformeComercial.setTelefono(formatoInformeComercialDTO.getTelefono());
        formatoInformeComercial.setObservacionesServiciosPublicos(formatoInformeComercialDTO.getObservacionesServiciosPublicos());
        formatoInformeComercial.setTransportePublico(formatoInformeComercialDTO.getTransportePublico());
        formatoInformeComercial.setFrecuencia(formatoInformeComercialDTO.getFrecuencia());
        formatoInformeComercial.setCubrimiento(formatoInformeComercialDTO.getCubrimiento());
        if (formatoInformeComercialDTO.getPerspectivasDeValorizacion() != null) {
            formatoInformeComercial.setPerspectivasDeValorizacion(formatoInformeComercialDTO
                    .getPerspectivasDeValorizacion().getKey());
        }
        formatoInformeComercial.setObservacionesPerspectivasDeValorizacion(formatoInformeComercialDTO.getObservacionesPerspectivasDeValorizacion());
        formatoInformeComercial.setNormatividad(formatoInformeComercialDTO.getNormatividad());
        formatoInformeComercial.setDescripcionDelInmueble(formatoInformeComercialDTO.getDescripcionDelInmueble());
        formatoInformeComercial.setLocalizacionEspecifacaDelInmueble(formatoInformeComercialDTO.getLocalizacionEspecifacaDelInmueble());
        formatoInformeComercial.setDistaciaPartiendoDelCascoUrbano(formatoInformeComercialDTO.getDistaciaPartiendoDelCascoUrbano());
        formatoInformeComercial.setLinderoNorte(formatoInformeComercialDTO.getLinderoNorte());
        formatoInformeComercial.setLinderoSur(formatoInformeComercialDTO.getLinderoSur());
        formatoInformeComercial.setLinderoOriente(formatoInformeComercialDTO.getLinderoOriente());
        formatoInformeComercial.setLinderoOccidente(formatoInformeComercialDTO.getLinderoOccidente());
        formatoInformeComercial.setFuenteLinderos(formatoInformeComercialDTO.getFuenteLinderos());
        formatoInformeComercial.setDescripcionViasDeAccesoInternas(formatoInformeComercialDTO.getDescripcionViasDeAccesoInternas());
        formatoInformeComercial.setFrenteSobreLaVia(formatoInformeComercialDTO.getFrenteSobreLaVia());
        formatoInformeComercial.setMetros(formatoInformeComercialDTO.getMetros());
        formatoInformeComercial.setCercasPerimedales(formatoInformeComercialDTO.getCercasPerimedales());
        formatoInformeComercial.setServidumbres(formatoInformeComercialDTO.getServidumbres());
        formatoInformeComercial.setPropiedadHorizontal(formatoInformeComercialDTO.getPropiedadHorizontal());
        formatoInformeComercial.setDescripcionDeLaPropiedadHorizontal(formatoInformeComercialDTO.getDescripcionDeLaPropiedadHorizontal());
        formatoInformeComercial.setVidaUtil(formatoInformeComercialDTO.getVidaUtil());
        formatoInformeComercial.setVidaDelInmueble(formatoInformeComercialDTO.getVidaDelInmueble());
        formatoInformeComercial.setVidaRemanente(formatoInformeComercialDTO.getVidaRemanente());
        formatoInformeComercial.setJustificacionVidaUtil(formatoInformeComercialDTO.getJustificacionVidaUtil());
        formatoInformeComercial.setEstructuraEdificio(formatoInformeComercialDTO.getEstructuraEdificio());
        formatoInformeComercial.setDescripcionEstructura(formatoInformeComercialDTO.getDescripcionEstructura());
        formatoInformeComercial.setPlacasDeEntrepiso(formatoInformeComercialDTO.getPlacasDeEntrepiso());
        formatoInformeComercial.setObservacionesPlacasEntrePiso(formatoInformeComercialDTO.getObservacionesPlacasEntrePiso());
        formatoInformeComercial.setFachada(formatoInformeComercialDTO.getFachada());
        formatoInformeComercial.setDescripcionFachada(formatoInformeComercialDTO.getDescripcionFachada());
        formatoInformeComercial.setCubierta(formatoInformeComercialDTO.getCubierta());
        formatoInformeComercial.setDescripcionCubierta(formatoInformeComercialDTO.getDescripcionCubierta());
        formatoInformeComercial.setEscaleras(formatoInformeComercialDTO.getEscaleras());
        formatoInformeComercial.setDescripcionEscaleras(formatoInformeComercialDTO.getDescripcionEscaleras());
        formatoInformeComercial.setEstructura(formatoInformeComercialDTO.getEstructura());
        formatoInformeComercial.setCategoriaAcabados(formatoInformeComercialDTO.getCategoriaAcabados());
        formatoInformeComercial.setDistribucion(formatoInformeComercialDTO.getDistribucion());
        formatoInformeComercial.setEstadoDeConservacionSectorRural(formatoInformeComercialDTO.getEstadoDeConservacionSectorRural());
        formatoInformeComercial.setEquipamientoComunal(formatoInformeComercialDTO.getEquipamientoComunal());
        formatoInformeComercial.setDescripcionDelSuelo(formatoInformeComercialDTO.getDescripcionDelSuelo());
        formatoInformeComercial.setRelieve(formatoInformeComercialDTO.getRelieve());
        formatoInformeComercial.setErosion(formatoInformeComercialDTO.getErosion());
        formatoInformeComercial.setTemperatura(formatoInformeComercialDTO.getTemperatura());
        formatoInformeComercial.setPisoTermico(formatoInformeComercialDTO.getPisoTermico());
        formatoInformeComercial.setAlturaMsnm(formatoInformeComercialDTO.getAlturaMsnm());
        formatoInformeComercial.setPrecipitacionAnualMm(formatoInformeComercialDTO.getPrecipitacionAnualMm());
        formatoInformeComercial.setFormaGeometrica(formatoInformeComercialDTO.getFormaGeometrica());
        formatoInformeComercial.setRegular(formatoInformeComercialDTO.getRegular());
        formatoInformeComercial.setIrregular(formatoInformeComercialDTO.getIrregular());
        formatoInformeComercial.setInundabilidad(formatoInformeComercialDTO.getInundabilidad());
        formatoInformeComercial.setDistribucionDeLluvias(formatoInformeComercialDTO.getDistribucionDeLluvias());
        formatoInformeComercial.setPedregosidad(formatoInformeComercialDTO.getPedregosidad());
        formatoInformeComercial.setFertilidadNatural(formatoInformeComercialDTO.getFertilidadNatural());
        formatoInformeComercial.setNivelFreatico(formatoInformeComercialDTO.getNivelFreatico());
        formatoInformeComercial.setClaseAgrologica(formatoInformeComercialDTO.getClaseAgrologica());
        formatoInformeComercial.setValorPotencial(formatoInformeComercialDTO.getValorPotencial());
        formatoInformeComercial.setCapaVegetal(formatoInformeComercialDTO.getCapaVegetal());
        formatoInformeComercial.setCondicionesAgronomicas(formatoInformeComercialDTO.getCondicionesAgronomicas());
        formatoInformeComercial.setCondicionesAgrologicas(formatoInformeComercialDTO.getCondicionesAgrologicas());
        formatoInformeComercial.setDescripcionCultivos(formatoInformeComercialDTO.getDescripcionCultivos());
        formatoInformeComercial.setRecursosHidricos(formatoInformeComercialDTO.getRecursosHidricos());
        formatoInformeComercial.setIrrigacion(formatoInformeComercialDTO.getIrrigacion());
        formatoInformeComercial.setRestriccionesFisicas(formatoInformeComercialDTO.getRestriccionesFisicas());
        formatoInformeComercial.setOtrosCutivos(formatoInformeComercialDTO.getOtrosCutivos());
        formatoInformeComercial.setBosques(formatoInformeComercialDTO.getBosques());
        formatoInformeComercial.setCultivosComerciales(formatoInformeComercialDTO.getCultivosComerciales());
        formatoInformeComercial.setDeProteccion(formatoInformeComercialDTO.getDeProteccion());
        formatoInformeComercial.setImpactoAmbiental(formatoInformeComercialDTO.getImpactoAmbiental());
        formatoInformeComercial.setFrente(formatoInformeComercialDTO.getFrente());
        formatoInformeComercial.setFondo(formatoInformeComercialDTO.getFondo());
        formatoInformeComercial.setRelacionFrenteFondo(formatoInformeComercialDTO.getRelacionFrenteFondo());
        formatoInformeComercial.setObservacionDistribucionDeAreasNoConstruidas(formatoInformeComercialDTO.getObservacionDistribucionDeAreasNoConstruidas());
        formatoInformeComercial.setConstruccion(formatoInformeComercialDTO.getConstruccion());
        formatoInformeComercial.setVidaUtilConstruccion(formatoInformeComercialDTO.getVidaUtilConstruccion());
        formatoInformeComercial.setVidaDelInmuebleConstruccion(formatoInformeComercialDTO.getVidaDelInmuebleConstruccion());
        formatoInformeComercial.setVidaRemanenteConstruccion(formatoInformeComercialDTO.getVidaRemanenteConstruccion());
        formatoInformeComercial.setObservacionesEdad(formatoInformeComercialDTO.getObservacionesEdad());
        formatoInformeComercial.setRemodelado(formatoInformeComercialDTO.getRemodelado());
        formatoInformeComercial.setAlcantarilladoConstruccion(formatoInformeComercialDTO.getAlcantarilladoConstruccion());
        formatoInformeComercial.setAcueductoConstruccion(formatoInformeComercialDTO.getAcueductoConstruccion());
        formatoInformeComercial.setEnergiaConstruccion(formatoInformeComercialDTO.getEnergiaConstruccion());
        formatoInformeComercial.setGasConstruccion(formatoInformeComercialDTO.getGasConstruccion());
        formatoInformeComercial.setTelefonoConstruccion(formatoInformeComercialDTO.getTelefonoConstruccion());
        formatoInformeComercial.setObservacionesServiciosPublicosConstruccion(formatoInformeComercialDTO.getObservacionesServiciosPublicosConstruccion());
        formatoInformeComercial.setFrenteConstruido(formatoInformeComercialDTO.getFrenteConstruido());
        formatoInformeComercial.setFondoConstruido(formatoInformeComercialDTO.getFondoConstruido());
        formatoInformeComercial.setRelacionFrenteFondoConstruido(formatoInformeComercialDTO.getRelacionFrenteFondoConstruido());
        formatoInformeComercial.setAreaPrivadaConstruido(formatoInformeComercialDTO.getAreaPrivadaConstruido());
        formatoInformeComercial.setCoeficienteDeCopropiedadConstruido(formatoInformeComercialDTO.getCoeficienteDeCopropiedadConstruido());
        formatoInformeComercial.setAreaTotalConstruida(formatoInformeComercialDTO.getAreaTotalConstruida());
        formatoInformeComercial.setFuenteConstruido(formatoInformeComercialDTO.getFuenteConstruido());
        formatoInformeComercial.setObservacionesDistribucionAreasConstruidas(formatoInformeComercialDTO.getObservacionesDistribucionAreasConstruidas());
        formatoInformeComercial.setProblemasDeEstabilidadDeSuelos(formatoInformeComercialDTO.getProblemasDeEstabilidadDeSuelos());
        formatoInformeComercial.setImpactoAmbientalYCondicionesDeSalubridad(formatoInformeComercialDTO.getImpactoAmbientalYCondicionesDeSalubridad());
        formatoInformeComercial.setServidumbresCesionesYAfectacionesViales(formatoInformeComercialDTO.getServidumbresCesionesYAfectacionesViales());
        formatoInformeComercial.setSeguridad(formatoInformeComercialDTO.getSeguridad());
        formatoInformeComercial.setProblematicasSocioEconomicas(formatoInformeComercialDTO.getProblematicasSocioEconomicas());
        formatoInformeComercial.setDescripcionDeLasHipotesisEspecialesInusualesOExtraordinarias(formatoInformeComercialDTO.getDescripcionDeLasHipotesisEspecialesInusualesOExtraordinarias());
        formatoInformeComercial.setConsideracionesGenerales(formatoInformeComercialDTO.getConsideracionesGenerales());
        formatoInformeComercial.setConsideracionesGeneralesDeLocalizacion(formatoInformeComercialDTO.getConsideracionesGeneralesDeLocalizacion());
        formatoInformeComercial.setConsideracionesGeneralesDelSector(formatoInformeComercialDTO.getConsideracionesGeneralesDelSector());
        formatoInformeComercial.setConsideracionesGeneralesDeLaCapacidadDeTerreno(formatoInformeComercialDTO.getConsideracionesGeneralesDeLaCapacidadDeTerreno());
        formatoInformeComercial.setConsideracionesGeneralesDeLaInfraestructuraVialYCapacidadDeAgua(formatoInformeComercialDTO.getConsideracionesGeneralesDeLaInfraestructuraVialYCapacidadDeAgua());
        formatoInformeComercial.setConsideracionesGeneralesDeLaConstruccion(formatoInformeComercialDTO.getConsideracionesGeneralesDeLaConstruccion());
        formatoInformeComercial.setCasosEspecialesDeAfectacion(formatoInformeComercialDTO.getCasosEspecialesDeAfectacion());
        formatoInformeComercial.setInvestigacionEconomica(formatoInformeComercialDTO.getInvestigacionEconomica());
        formatoInformeComercial.setComportamientoDeOfertaYDemanda(formatoInformeComercialDTO.getComportamientoDeOfertaYDemanda());
        formatoInformeComercial.setConceptoDeGarantia(formatoInformeComercialDTO.getConceptoDeGarantia());
        formatoInformeComercial.setObservacionesConceptoGarantia(formatoInformeComercialDTO.getObservacionesConceptoGarantia());
        formatoInformeComercial.setValorRazonableDelInmueble(formatoInformeComercialDTO.getValorComercialDelInmueble());
        formatoInformeComercial.setValorIntegralSobreElTerreno(formatoInformeComercialDTO.getValorIntegralSobreElTerreno());
        formatoInformeComercial.setValorIntegralSobreElConstruccion(formatoInformeComercialDTO.getValorIntegralSobreElConstruccion());
        formatoInformeComercial.setPorcentajeFinal(formatoInformeComercialDTO.getPorcentajeFinal());
        formatoInformeComercial.setAreaConstruida(formatoInformeComercialDTO.getAreaConstruida());
        formatoInformeComercial.setAreaInfraestructura(formatoInformeComercialDTO.getAreaInfraestructura());
        formatoInformeComercial.setAreaCultivosAvaluables(formatoInformeComercialDTO.getAreaCultivosAvaluables());
        if (formatoInformeComercialDTO.getInmuebles() != null && !formatoInformeComercialDTO.getInmuebles().isEmpty()) {
            agregarInmuebles(formatoInformeComercialDTO.getInmuebles(), formatoInformeComercial);
        }
        if (formatoInformeComercialDTO.getServidumbrez() != null && !formatoInformeComercialDTO.getServidumbrez().isEmpty()) {
            agregarServidumbrez(formatoInformeComercialDTO.getServidumbrez(), formatoInformeComercial);
        }
        if (formatoInformeComercialDTO.getViasAcceso() != null && !formatoInformeComercialDTO.getViasAcceso().isEmpty()) {
            agregarViasAcceso(formatoInformeComercialDTO.getViasAcceso(), formatoInformeComercial);
        }
        if (formatoInformeComercialDTO.getMetodosValuacion() != null && !formatoInformeComercialDTO.getMetodosValuacion().isEmpty()) {
            agregarMetodosValuacion(formatoInformeComercialDTO.getMetodosValuacion(), formatoInformeComercial);
        }
        return formatoInformeComercial;
    }

    private void agregarMetodosValuacion(
            Set<MetodoValuacionDTO> metodosValuacionDTO,
            FormatoInformeComercial formatoInformeComercial) throws MetodoValuacionNotFoundException {
        Set<MetodoValuacion> metodoValuacions = new HashSet<MetodoValuacion>();
        for (MetodoValuacionDTO metodoValuacionDTO:metodosValuacionDTO) {
            MetodoValuacion metodoValuacion = metodoValuacionRepository.findOne(metodoValuacionDTO.getId());
            if (metodoValuacion == null) {
                throw new MetodoValuacionNotFoundException();
            }
            metodoValuacions.add(metodoValuacion);
        }
        formatoInformeComercial.setMetodosValuacion(metodoValuacions);
    }

    private void agregarViasAcceso(Set<ViaAccesoDTO> viasAccesoDTO,
            FormatoInformeComercial formatoInformeComercial) throws ViaAccesoNotFoundException {
        Set<ViaAcceso> viaAccesos = new HashSet<ViaAcceso>();
        for (ViaAccesoDTO viaAccesoDTO:viasAccesoDTO) {
            ViaAcceso viaAcceso = viaAccesoRepository.findOne(viaAccesoDTO.getId());
            if (viaAcceso == null) {
                throw new ViaAccesoNotFoundException();
            }
            viaAccesos.add(viaAcceso);
        }
        formatoInformeComercial.setViasAcceso(viaAccesos);
    }

    private void agregarServidumbrez(Set<ServidumbreDTO> servidumbrezDTO,
            FormatoInformeComercial formatoInformeComercial) throws ServidumbreNotFoundException {
        Set<Servidumbre> servidumbres = new HashSet<Servidumbre>();
        for (ServidumbreDTO servidumbreDTO:servidumbrezDTO) {
            Servidumbre servidumbre = servidumbreRepository.findOne(servidumbreDTO.getId());
            if (servidumbre == null) {
                throw new ServidumbreNotFoundException();
            }
            servidumbres.add(servidumbre);
        }
        formatoInformeComercial.setServidumbrez(servidumbres);
    }

    private void agregarInmuebles(Set<InmuebleDTO> inmueblesDTO,
            FormatoInformeComercial formatoInformeComercial) throws InmuebleNotFoundException {
        Set<Inmueble> inmuebles = new HashSet<Inmueble>();
        for (InmuebleDTO inmuebleDTO:inmueblesDTO) {
            Inmueble inmueble = inmuebleRepository.findOne(inmuebleDTO.getId());
            if (inmueble == null) {
                throw new InmuebleNotFoundException();
            }
            inmuebles.add(inmueble);
        }
        formatoInformeComercial.setInmuebles(inmuebles);
    }

    public FormatoInformeDTO escribirDTO(FormatoInforme formatoInforme) {
        if (formatoInforme.getClass().equals(FormatoInformeHipotecario.class)) {
            return escribirDTO((FormatoInformeHipotecario) formatoInforme);
        }else if (formatoInforme.getClass().equals(FormatoInformeComercial.class)) {
            return escribirDTO((FormatoInformeComercial) formatoInforme);
        }
        return null;
    }

    public FormatoInformeHipotecarioDTO escribirDTO(FormatoInformeHipotecario formatoInforme) {
        log.debug("Escribiendo DTO de formatoInformeHipotecario:  {}",
                formatoInforme);
        FormatoInformeHipotecarioDTO formatoInformeDTO = new FormatoInformeHipotecarioDTO(formatoInforme.getAvaluo().getId());
        formatoInformeDTO.setId(formatoInforme.getId());
        formatoInformeDTO.setValorComercial(formatoInforme.getValorComercial());
        formatoInformeDTO.setValorCortesia(formatoInforme.getValorCortesia());
        formatoInformeDTO.setMetodoDeValuacion(formatoInforme.getMetodoDeValuacion());
        formatoInformeDTO.setJustificacionDeMetodologia(formatoInforme.getJustificacionDeMetodologia());
        formatoInformeDTO.setDocumentosConsultados(formatoInforme.getDocumentosConsultados());
        formatoInformeDTO.setAcueductoEnElSector(formatoInforme.getAcueductoEnElSector());
        formatoInformeDTO.setAcueductoEnElPredio(formatoInforme.getAcueductoEnElPredio());
        formatoInformeDTO.setAlcantarilladoEnElSector(formatoInforme.getAlcantarilladoEnElSector());
        formatoInformeDTO.setAlcantarilladoEnElPredio(formatoInforme.getAlcantarilladoEnElPredio());
        formatoInformeDTO.setEnergiaEnElSector(formatoInforme.getEnergiaEnElSector());
        formatoInformeDTO.setEnergiaEnElPredio(formatoInforme.getEnergiaEnElPredio());
        formatoInformeDTO.setGasEnElSector(formatoInforme.getGasEnElSector());
        formatoInformeDTO.setGasEnElPredio(formatoInforme.getGasEnElPredio());
        formatoInformeDTO.setTelefonoEnElSector(formatoInforme.getTelefonoEnElSector());
        formatoInformeDTO.setTelefonoEnElPredio(formatoInforme.getTelefonoEnElPredio());
        formatoInformeDTO.setEstrato(formatoInforme.getEstrato());
        formatoInformeDTO.setBarrioLegal(formatoInforme.getBarrioLegal());
        if (formatoInforme.getUsoPredominanteInmueble() != null) {
            formatoInformeDTO.setUsoPredominanteInmueble(ListaUsoPredominanteInmueble.fromKey(formatoInforme.getUsoPredominanteInmueble()));
        }

        formatoInformeDTO.setDescripcionUsoPredominanteMixto(formatoInforme.getDescUsoPredominanteInmuebleMixto());
        if ( formatoInforme.getTopografiaSector() != null) {
        	formatoInformeDTO.setTopografiaSector( ListaTopografiaSector.fromKey(formatoInforme.getTopografiaSector()));
        }
        if (formatoInforme.getTransporte() != null) {
        	formatoInformeDTO.setTransporte(ListaEstado.fromKey(formatoInforme.getTransporte()));
        }
        formatoInformeDTO.setPerspectivasDeValorizacion(formatoInforme.getPerspectivasDeValorizacion());
        if ( formatoInforme.getCondicionesDeSalubridad() != null) {
        	 formatoInformeDTO.setCondicionesDeSalubridad(ListaEstado.fromKey(formatoInforme.getCondicionesDeSalubridad()));
        }
       
        formatoInformeDTO.setImpactoAmbientalAire(formatoInforme.getImpactoAmbientalAire());
        formatoInformeDTO.setImpactoAmbientalBasura(formatoInforme.getImpactoAmbientalBasura());
        formatoInformeDTO.setImpactoAmbientalInseguridad(formatoInforme.getImpactoAmbientalInseguridad());
        formatoInformeDTO.setImpactoAmbientalRuido(formatoInforme.getImpactoAmbientalRuido());
        formatoInformeDTO.setImpactoAmbientalAguasServidas(formatoInforme.getImpactoAmbientalAguasServidas());
        formatoInformeDTO.setImpactoAmbientalOtro(formatoInforme.getImpactoAmbientalOtro());
        formatoInformeDTO.setExplicacionImpactoAmbientalAire(formatoInforme.getExplicacionImpactoAmbientalAire());
        formatoInformeDTO.setExplicacionImpactoAmbientalBasura(formatoInforme.getExplicacionImpactoAmbientalBasura());
        formatoInformeDTO.setExplicacionImpactoAmbientalInseguridad(formatoInforme.getExplicacionImpactoAmbientalInseguridad());
        formatoInformeDTO.setExplicacionImpactoAmbientalRuido(formatoInforme.getExplicacionImpactoAmbientalRuido());
        formatoInformeDTO.setExplicacionImpactoAmbientalAguasServidas(formatoInforme.getExplicacionImpactoAmbientalAguasServidas());
        formatoInformeDTO.setExplicacionOtro(formatoInforme.getExplicacionOtro());
    	if ( formatoInforme.getEstadoDeLasVias() !=null ) {
    		formatoInformeDTO.setEstadoDeLasVias(ListaEstado.fromKey(formatoInforme.getEstadoDeLasVias()));
    	}
        formatoInformeDTO.setViasPavimentadas(formatoInforme.getViasPavimentadas());
        formatoInformeDTO.setAndenesEnLasVias(formatoInforme.getAndenesEnLasVias());
        formatoInformeDTO.setSardinelesEnLasVias(formatoInforme.getSardinelesEnLasVias());
        formatoInformeDTO.setParques(formatoInforme.getParques());
        formatoInformeDTO.setParadero(formatoInforme.getParadero());
        formatoInformeDTO.setAlumbrado(formatoInforme.getAlumbrado());
        formatoInformeDTO.setZonasVerdesPublicas(formatoInforme.getZonasVerdesPublicas());
        formatoInformeDTO.setArborizacion(formatoInforme.getArborizacion());
        formatoInformeDTO.setAlamedas(formatoInforme.getAlamedas());
        formatoInformeDTO.setCicloRutas(formatoInforme.getCicloRutas());
        if (formatoInforme.getClaseInmueble() !=null) {
        	formatoInformeDTO.setClaseInmueble(ListaClaseInmueble.fromKey(formatoInforme.getClaseInmueble()));
        }
        formatoInformeDTO.setDescripcionOtrosTipoInmueble(formatoInforme.getDescripcionOtrosTipoInmueble());
        if (formatoInforme.getUsoActual() != null) {
        	formatoInformeDTO.setUsoActual(ListaUsoActual.fromKey(formatoInforme.getUsoActual()));
        }
        formatoInformeDTO.setDescripcionOtrosUsoInmueble(formatoInforme.getDescripcionOtrosUsoInmueble());
        if (formatoInforme.getTipoDeVivienda() != null) {
            formatoInformeDTO.setTipoDeVivienda(ListaTipoVivienda.fromKey(formatoInforme.getTipoDeVivienda()));
        }

        formatoInformeDTO.setDescripcionOtrosTipoVivienda(formatoInforme.getDescripcionOtrosTipoVivienda());
        formatoInformeDTO.setChip(formatoInforme.getChip());
        formatoInformeDTO.setMatriculaInmobiliariaPrincipal2(formatoInforme.getMatriculaInmobiliariaPrincipal2());
        formatoInformeDTO.setNumeroDeEscritura(formatoInforme.getNumeroDeEscritura());
        formatoInformeDTO.setNumeroNotariaDeEscritura(formatoInforme.getNumeroNotariaDeEscritura());
        formatoInformeDTO.setFechaDeEscritura(formatoInforme.getFechaDeEscritura());
        formatoInformeDTO.setDepartamentoNotaria(formatoInforme.getDepartamentoNotaria());
        if (formatoInforme.getCiudadNotaria() != null) {
            formatoInformeDTO.setCiudadNotaria(divipolaEnsamblador.escribirDTO(formatoInforme.getCiudadNotaria()));
        }
//        formatoInformeDTO.setMatriculaInmobiliariaGaraje1(formatoInforme.getMatriculaInmobiliariaGaraje1());
//        formatoInformeDTO.setMatriculaInmobiliariaGaraje2(formatoInforme.getMatriculaInmobiliariaGaraje2());
//        formatoInformeDTO.setMatriculaInmobiliariaGaraje3(formatoInforme.getMatriculaInmobiliariaGaraje3());
//        formatoInformeDTO.setMatriculaInmobiliariaGaraje4(formatoInforme.getMatriculaInmobiliariaGaraje4());
//        formatoInformeDTO.setMatriculaInmobiliariaGaraje5(formatoInforme.getMatriculaInmobiliariaGaraje5());
//        formatoInformeDTO.setGarajeParalelo1(formatoInforme.getGarajeParalelo1());
//        formatoInformeDTO.setGarajeParalelo2(formatoInforme.getGarajeParalelo2());
//        formatoInformeDTO.setGarajeParalelo3(formatoInforme.getGarajeParalelo3());
//        formatoInformeDTO.setGarajeParalelo4(formatoInforme.getGarajeParalelo4());
//        formatoInformeDTO.setGarajeParalelo5(formatoInforme.getGarajeParalelo5());
//        formatoInformeDTO.setGarajeCubierto1(formatoInforme.getGarajeCubierto1());
//        formatoInformeDTO.setGarajeCubierto2(formatoInforme.getGarajeCubierto2());
//        formatoInformeDTO.setGarajeCubierto3(formatoInforme.getGarajeCubierto3());
//        formatoInformeDTO.setGarajeCubierto4(formatoInforme.getGarajeCubierto4());
//        formatoInformeDTO.setGarajeCubierto5(formatoInforme.getGarajeCubierto5());
//        formatoInformeDTO.setNumeroParqueadero1(formatoInforme.getNumeroParqueadero1());
//        formatoInformeDTO.setNumeroParqueadero2(formatoInforme.getNumeroParqueadero2());
//        formatoInformeDTO.setNumeroParqueadero3(formatoInforme.getNumeroParqueadero3());
//        formatoInformeDTO.setNumeroParqueadero4(formatoInforme.getNumeroParqueadero4());
//        formatoInformeDTO.setNumeroParqueadero5(formatoInforme.getNumeroParqueadero5());
//        formatoInformeDTO.setNumeroParqueaderoExclusivo1(formatoInforme.getNumeroParqueaderoExclusivo1());
//        formatoInformeDTO.setNumeroParqueaderoExclusivo2(formatoInforme.getNumeroParqueaderoExclusivo2());
//        formatoInformeDTO.setNumeroParqueaderoExclusivo3(formatoInforme.getNumeroParqueaderoExclusivo3());
//        formatoInformeDTO.setNumeroParqueaderoExclusivo4(formatoInforme.getNumeroParqueaderoExclusivo4());
//        formatoInformeDTO.setNumeroParqueaderoExclusivo5(formatoInforme.getNumeroParqueaderoExclusivo5());
//        formatoInformeDTO.setGarajeServidumbre1(formatoInforme.getGarajeServidumbre1());
//        formatoInformeDTO.setGarajeServidumbre2(formatoInforme.getGarajeServidumbre2());
//        formatoInformeDTO.setGarajeServidumbre3(formatoInforme.getGarajeServidumbre3());
//        formatoInformeDTO.setGarajeServidumbre4(formatoInforme.getGarajeServidumbre4());
//        formatoInformeDTO.setGarajeServidumbre5(formatoInforme.getGarajeServidumbre5());
        formatoInformeDTO.setMatriculaInmobiliariaDeposito1(formatoInforme.getMatriculaInmobiliariaDeposito1());
        formatoInformeDTO.setMatriculaInmobiliariaDeposito2(formatoInforme.getMatriculaInmobiliariaDeposito2());
        formatoInformeDTO.setMatriculaInmobiliariaDeposito3(formatoInforme.getMatriculaInmobiliariaDeposito3());
        formatoInformeDTO.setMatriculaInmobiliariaDeposito4(formatoInforme.getMatriculaInmobiliariaDeposito4());
        formatoInformeDTO.setMatriculaInmobiliariaDeposito5(formatoInforme.getMatriculaInmobiliariaDeposito5());
        formatoInformeDTO.setNumeroDeposito1(formatoInforme.getNumeroDeposito1());
        formatoInformeDTO.setNumeroDeposito2(formatoInforme.getNumeroDeposito2());
        formatoInformeDTO.setNumeroDeposito3(formatoInforme.getNumeroDeposito3());
        formatoInformeDTO.setNumeroDeposito4(formatoInforme.getNumeroDeposito4());
        formatoInformeDTO.setNumeroDeposito5(formatoInforme.getNumeroDeposito5());
        formatoInformeDTO.setNumeroDepositoExclusivo1(formatoInforme.getNumeroDepositoExclusivo1());
        formatoInformeDTO.setNumeroDepositoExclusivo2(formatoInforme.getNumeroDepositoExclusivo2());
        formatoInformeDTO.setNumeroDepositoExclusivo3(formatoInforme.getNumeroDepositoExclusivo3());
        formatoInformeDTO.setNumeroDepositoExclusivo4(formatoInforme.getNumeroDepositoExclusivo4());
        formatoInformeDTO.setNumeroDepositoExclusivo5(formatoInforme.getNumeroDepositoExclusivo5());
        formatoInformeDTO.setNumeroDePisos(formatoInforme.getNumeroDePisos());
        formatoInformeDTO.setNumeroSotanos(formatoInforme.getNumeroSotanos());
        formatoInformeDTO.setVetustez(formatoInforme.getVetustez());
        if (formatoInforme.getEstadoDeConservacion() != null) {
        	formatoInformeDTO.setEstadoDeConservacion(ListaEstadoConservacion.fromKey(formatoInforme.getEstadoDeConservacion()));	
        }
        if (formatoInforme.getEstadoDeConstruccion() != null) {
            formatoInformeDTO.setEstadoDeConstruccion(ListaEstadoConstruccion.fromKey(formatoInforme.getEstadoDeConstruccion()));
        }
        formatoInformeDTO.setEstadoDeObra(formatoInforme.getEstadoDeObra() == null ? false : formatoInforme.getEstadoDeObra() == 1 ? true : false);
        formatoInformeDTO.setAnoDeConstruccion(formatoInforme.getAnoDeConstruccion());
        formatoInformeDTO.setPorcentajeAvance(formatoInforme.getPorcentajeAvance());
        formatoInformeDTO.setRemodelado(formatoInforme.getRemodelado());
        formatoInformeDTO.setFechaRemodelacion(formatoInforme.getFechaRemodelacion());
        if (formatoInforme.getEstructura() != null) {
            formatoInformeDTO.setEstructura(ListaEstructura.fromKey(formatoInforme.getEstructura()));
        }
        if (formatoInforme.getEstructuraReforzada() != null) {
            formatoInformeDTO.setEstructuraReforzada(ListaEstructuraReforzada.fromKey(formatoInforme.getEstructuraReforzada()));
        }
        if (formatoInforme.getFachada() != null) {
            formatoInformeDTO.setFachada(ListaFachada.fromKey(formatoInforme.getFachada()));
        }
        if (formatoInforme.getTipoFachada() != null) {
            formatoInformeDTO.setTipoFachada(ListaTipoFachada.fromKey(formatoInforme.getTipoFachada()));
        }
        if (formatoInforme.getCubierta() != null) {
            formatoInformeDTO.setCubierta(ListaCubierta.fromKey(formatoInforme.getCubierta()));
        }
        if (formatoInforme.getPisosBodega() != null) {
            formatoInformeDTO.setPisosBodega(ListaPisoBodega.fromKey(formatoInforme.getPisosBodega()));
        }

        formatoInformeDTO.setTipologiaViviendaUnicaFamiliar(formatoInforme.getTipologiaViviendaUnicaFamiliar());
        formatoInformeDTO.setSala(formatoInforme.getSala());
        formatoInformeDTO.setDeposito(formatoInforme.getDeposito());
        formatoInformeDTO.setComedor(formatoInforme.getComedor());
        formatoInformeDTO.setEstudio(formatoInforme.getEstudio());
        formatoInformeDTO.setJardin(formatoInforme.getJardin());
        formatoInformeDTO.setBanoSocial(formatoInforme.getBanoSocial());
        formatoInformeDTO.setEstarHabitacion(formatoInforme.getEstarHabitacion());
        formatoInformeDTO.setHabitaciones(formatoInforme.getHabitaciones());
        formatoInformeDTO.setDepositosPrivados(formatoInforme.getDepositosPrivados());
        formatoInformeDTO.setDepositosExclusivos(formatoInforme.getDepositosExclusivos());
        formatoInformeDTO.setNumeroTotalDepositos(formatoInforme.getNumeroTotalDepositos());
        formatoInformeDTO.setBanoPrivado(formatoInforme.getBanoPrivado());
        formatoInformeDTO.setCocina(formatoInforme.getCocina());
        formatoInformeDTO.setCuartoServicio(formatoInforme.getCuartoServicio());
        formatoInformeDTO.setOficina(formatoInforme.getOficina());
        formatoInformeDTO.setBanoServicio(formatoInforme.getBanoServicio());
        formatoInformeDTO.setPatioInterior(formatoInforme.getPatioInterior());
        formatoInformeDTO.setTerraza(formatoInforme.getTerraza());
        formatoInformeDTO.setBodega(formatoInforme.getBodega());
        formatoInformeDTO.setZonaDeRopas(formatoInforme.getZonaDeRopas());
        formatoInformeDTO.setBalcon(formatoInforme.getBalcon());
        formatoInformeDTO.setCloset(formatoInforme.getCloset());
        formatoInformeDTO.setLocal(formatoInforme.getLocal());
        formatoInformeDTO.setZonaVerdePrivada(formatoInforme.getZonaVerdePrivada());
        if (formatoInforme.getIluminacion() != null) {
        	formatoInformeDTO.setIluminacion(ListaEstado.fromKey(formatoInforme.getIluminacion()));
        } 
        if (formatoInforme.getVentilacion() !=null ) {
        	formatoInformeDTO.setVentilacion(ListaEstado.fromKey(formatoInforme.getVentilacion()));
        }
        
//        formatoInformeDTO.setGarajePrivado(formatoInforme.getGarajePrivado());
//        formatoInformeDTO.setGarajeExclusivo(formatoInforme.getGarajeExclusivo());
//        formatoInformeDTO.setBahiaComunal(formatoInforme.getBahiaComunal());
//        formatoInformeDTO.setGarajeParalelo(formatoInforme.getGarajeParalelo());
//        formatoInformeDTO.setGarajeDoble1(formatoInforme.getGarajeDoble1());
//        formatoInformeDTO.setGarajeDoble2(formatoInforme.getGarajeDoble2());
//        formatoInformeDTO.setGarajeDoble3(formatoInforme.getGarajeDoble3());
//        formatoInformeDTO.setGarajeDoble4(formatoInforme.getGarajeDoble4());
//        formatoInformeDTO.setGarajeDoble5(formatoInforme.getGarajeDoble5());
        formatoInformeDTO.setNumeroTotalDeGarajes(formatoInforme.getNumeroTotalDeGarajes());
        formatoInformeDTO.setTotalCuposParquedaro(formatoInforme.getTotalCuposParquedaro());
        if (formatoInforme.getEstadoAcabadosPisos()!=null) {
        	formatoInformeDTO.setEstadoAcabadosPisos(ListaEstadosAcabados.fromKey(formatoInforme.getEstadoAcabadosPisos()));
        }
        if (formatoInforme.getEstadoAcabadosMuros() != null) {
        	 formatoInformeDTO.setEstadoAcabadosMuros(ListaEstadosAcabados.fromKey(formatoInforme.getEstadoAcabadosMuros()));
        }
        if (formatoInforme.getEstadoAcabadosTechos() != null) {
        	formatoInformeDTO.setEstadoAcabadosTechos(ListaEstadosAcabados.fromKey(formatoInforme.getEstadoAcabadosTechos()));
        }
        if (formatoInforme.getEstadoAcabadosMadera() != null) {
        	formatoInformeDTO.setEstadoAcabadosMadera(ListaEstadosAcabados.fromKey(formatoInforme.getEstadoAcabadosMadera()));
        }
        if (formatoInforme.getEstadoAcabadosMetal() != null) {
        	formatoInformeDTO.setEstadoAcabadosMetal(ListaEstadosAcabados.fromKey(formatoInforme.getEstadoAcabadosMetal()));
        }
        if (formatoInforme.getEstadoAcabadosBanos() != null) {
        	formatoInformeDTO.setEstadoAcabadosBanos(ListaEstadosAcabados.fromKey(formatoInforme.getEstadoAcabadosBanos()));
        }
        if (formatoInforme.getEstadoAcabadosCocina() != null)  {
        	 formatoInformeDTO.setEstadoAcabadosCocina(ListaEstadosAcabados.fromKey(formatoInforme.getEstadoAcabadosCocina()));
        }
        if (formatoInforme.getCalidadAcabadosPisos() != null) {
        	 formatoInformeDTO.setCalidadAcabadosPisos(ListaCalidadAcabados.fromKey(formatoInforme.getCalidadAcabadosPisos()));
        }
        if (formatoInforme.getCalidadAcabadosMuros() !=null) {
        	formatoInformeDTO.setCalidadAcabadosMuros(ListaCalidadAcabados.fromKey(formatoInforme.getCalidadAcabadosMuros()));
        }
        if (formatoInforme.getCalidadAcabadosMuros() != null) {
        	formatoInformeDTO.setCalidadAcabadosMuros(ListaCalidadAcabados.fromKey(formatoInforme.getCalidadAcabadosMuros()));
        }
        if (formatoInforme.getCalidadAcabadosTechos() != null) {
        	 formatoInformeDTO.setCalidadAcabadosTechos(ListaCalidadAcabados.fromKey(formatoInforme.getCalidadAcabadosTechos()));
        }
        if (formatoInforme.getCalidadAcabadosMadera() != null) {
        	 formatoInformeDTO.setCalidadAcabadosMadera(ListaCalidadAcabados.fromKey(formatoInforme.getCalidadAcabadosMadera()));
        }
        if (formatoInforme.getCalidadAcabadosMetal() != null) {
        	formatoInformeDTO.setCalidadAcabadosMetal(ListaCalidadAcabados.fromKey(formatoInforme.getCalidadAcabadosMetal()));
        }
        if (formatoInforme.getCalidadAcabadosBanos() != null) {
        	formatoInformeDTO.setCalidadAcabadosBanos(ListaCalidadAcabados.fromKey(formatoInforme.getCalidadAcabadosBanos()));
        }
        if (formatoInforme.getCalidadAcabadosCocina() != null) {
            formatoInformeDTO.setCalidadAcabadosCocina(ListaCalidadAcabadosCocina.fromKey(formatoInforme.getCalidadAcabadosCocina()));
        }
        
        formatoInformeDTO.setSometidoAPropiedadHorizontal(formatoInforme.getSometidoAPropiedadHorizontal());
        formatoInformeDTO.setConjuntoCerrado(formatoInforme.getConjuntoCerrado());
        if (formatoInforme.getUbicacionDelInmueble() != null) {
            formatoInformeDTO.setUbicacionDelInmueble(ListaUbicacionInmueble.fromKey(formatoInforme.getUbicacionDelInmueble()));	
        }
        formatoInformeDTO.setNumeroDeEdificios(formatoInforme.getNumeroDeEdificios());
        formatoInformeDTO.setUnidadesPorPiso(formatoInforme.getUnidadesPorPiso());
        formatoInformeDTO.setTotalUnidades(formatoInforme.getTotalUnidades());
        formatoInformeDTO.setPorteria(formatoInforme.getPorteria());
        formatoInformeDTO.setCitofono(formatoInforme.getCitofono());
        formatoInformeDTO.setBicicletero(formatoInforme.getBicicletero());
        formatoInformeDTO.setPiscina(formatoInforme.getPiscina());
        formatoInformeDTO.setTanqueDeAgua(formatoInforme.getTanqueDeAgua());
        formatoInformeDTO.setClubHouse(formatoInforme.getClubHouse());
        formatoInformeDTO.setGarajeVisitantes(formatoInforme.getGarajeVisitantes());
        formatoInformeDTO.setJuegosNinos(formatoInforme.getJuegosNinos());
        formatoInformeDTO.setCanchaMultiple(formatoInforme.getCanchaMultiple());
        formatoInformeDTO.setBombaEyectora(formatoInforme.getBombaEyectora());
        formatoInformeDTO.setAireAcondicionadoCentral(formatoInforme.getAireAcondicionadoCentral());
        formatoInformeDTO.setCanchaSquash(formatoInforme.getCanchaSquash());
        formatoInformeDTO.setZonasVerdesComunales(formatoInforme.getZonasVerdesComunales());
        formatoInformeDTO.setGimnasio(formatoInforme.getGimnasio());
        formatoInformeDTO.setGolfito(formatoInforme.getGolfito());
        formatoInformeDTO.setSalonComunal(formatoInforme.getSalonComunal());
        formatoInformeDTO.setShutBasuras(formatoInforme.getShutBasuras());
        formatoInformeDTO.setEquipoDePresionConstante(formatoInforme.getEquipoDePresionConstante());
        formatoInformeDTO.setPlantaElectrica(formatoInforme.getPlantaElectrica());
        formatoInformeDTO.setAscensor(formatoInforme.getAscensor());
        formatoInformeDTO.setNumeroDeAscensores(formatoInforme.getNumeroDeAscensores());
        formatoInformeDTO.setOtros(formatoInforme.getOtros());
        formatoInformeDTO.setActualidadEdificadora(formatoInforme.getActualidadEdificadora());
        formatoInformeDTO.setEntregaDeDocumentos(formatoInforme.getEntregaDeDocumentos());
        formatoInformeDTO.setComportamientoOfertaDemanda(formatoInforme.getComportamientoOfertaDemanda());
        formatoInformeDTO.setTiempoEsperadoDeComercializacion(formatoInforme.getTiempoEsperadoDeComercializacion());
        formatoInformeDTO.setPorcentajeTerreno(formatoInforme.getPorcentajeTerreno() == null ? BigDecimal.ZERO : formatoInforme.getPorcentajeTerreno() );
        formatoInformeDTO.setValorProporcionalTerreno(formatoInforme.getValorProporcionalTerreno());
        formatoInformeDTO.setValorProporcionalConstruccion(formatoInforme.getValorProporcionalConstruccion());
        formatoInformeDTO.setValorIntegralTerreno(formatoInforme.getValorIntegralTerreno());
        formatoInformeDTO.setValorIntegralConstruccion(formatoInforme.getValorIntegralConstruccion());
        formatoInformeDTO.setObservaciones(formatoInforme.getObservaciones());
        formatoInformeDTO.setDireccionAnexos(formatoInforme.getDireccionAnexos());
        formatoInformeDTO.setOtrasDirecciones(formatoInforme.getOtrasDirecciones());
        formatoInformeDTO.setLicenciaConstruccion(formatoInforme.getLicenciaConstruccion());
        if (formatoInforme.getTipoLicencia() != null) {
            formatoInformeDTO.setTipoLicencia(ListaTipoLicencia.fromKey(formatoInforme.getTipoLicencia()));	
        }
        formatoInformeDTO.setNumeroLicencia(formatoInforme.getNumeroLicencia());
        if (formatoInforme.getReparados() != null) {
            formatoInformeDTO.setReparados(ListaReparado.fromKey(formatoInforme.getReparados()));
        }
        if (formatoInforme.getDanosPrevios() != null) {
            formatoInformeDTO.setDanosPrevios(ListaDanoPrevio.fromKey(formatoInforme.getDanosPrevios()));
        }
        if (formatoInforme.getMaterialConstructor() != null) {
        	formatoInformeDTO.setMaterialConstructor(ListaMaterialConstructor.fromKey(formatoInforme.getMaterialConstructor()));
        }
        if (formatoInforme.getDetalleMaterial() != null) {
        	formatoInformeDTO.setDetalleMaterial(ListaDetalleMaterial.fromKey(formatoInforme.getDetalleMaterial()));
        }
        if (formatoInforme.getIrregularidadPlanta()!=null) {
        	formatoInformeDTO.setIrregularidadPlanta(ListaIrregularidad.fromKey(formatoInforme.getIrregularidadPlanta()));
        }
        if (formatoInforme.getIrregularidadAltura() != null) {
            formatoInformeDTO.setIrregularidadAltura(ListaIrregularidad.fromKey(formatoInforme.getIrregularidadAltura()));
        }
        formatoInformeDTO.setCoeficiente(formatoInforme.getCoeficiente());
        formatoInformeDTO.setAreaConstruida(formatoInforme.getAreaConstruida());
        formatoInformeDTO.setAreaPrivada(formatoInforme.getAreaPrivada());
        formatoInformeDTO.setObservacionesGarajes( formatoInforme.getObservacionesGarajes() );
        formatoInformeDTO.setNumeroPiso(formatoInforme.getNumeroPiso());
        formatoInformeDTO.setOcupante(formatoInforme.getOcupante());
        formatoInformeDTO.setCondicionPh(formatoInforme.getCondicionPh());
        formatoInformeDTO.setTeatrino(formatoInforme.getTeatrino());
        formatoInformeDTO.setSauna(formatoInforme.getSauna());
        formatoInformeDTO.setCalefaccion(formatoInforme.getCalefaccion());
        formatoInformeDTO.setTerrazaComunal(formatoInforme.getTerrazaComunal());
        formatoInformeDTO.setTurco(formatoInforme.getTurco());
        formatoInformeDTO.setBbq(formatoInforme.getBbq());
        formatoInformeDTO.setGuarderia(formatoInforme.getGuarderia());
        formatoInformeDTO.setJardinInfantil(formatoInforme.getJardinInfantil());
        formatoInformeDTO.setVigilanciaPrivada(formatoInforme.getVigilanciaPrivada());
        //-Estado vigilancia depende Vigilancia Privada para poderlo mostrar en la interface
        formatoInformeDTO.setEstadoVigilancia(formatoInforme.getVigilanciaPrivada() == null ? Boolean.FALSE: Boolean.TRUE);
        formatoInformeDTO.setAdministracion(formatoInforme.getAdministracion());
        formatoInformeDTO.setValorAdministracion(formatoInforme.getValorAdministracion());
        formatoInformeDTO.setDecretoAcuerdo(formatoInforme.getDecretoAcuerdo());
        formatoInformeDTO.setObservacionDecretoAcuerdo(formatoInforme.getObservacionDecretoAcuerdo());
        formatoInformeDTO.setAlturaPermitida(formatoInforme.getAlturaPermitida());
        formatoInformeDTO.setObservacionAlturaPermitida(formatoInforme.getObservacionAlturaPermitida());
        formatoInformeDTO.setUsoPrincipal(formatoInforme.getUsoPrincipal());
        formatoInformeDTO.setObservacionUsoPrincipal(formatoInforme.getObservacionUsoPrincipal());
        formatoInformeDTO.setAislamientoPosterior(formatoInforme.getAislamientoPosterior());
        formatoInformeDTO.setObservacionAislamientoPosterior(formatoInforme.getObservacionAislamientoPosterior());
        formatoInformeDTO.setAislamientoLateral(formatoInforme.getAislamientoLateral());
        formatoInformeDTO.setObservacionAislamientoLateral(formatoInforme.getObservacionAislamientoLateral());
        formatoInformeDTO.setAnteJardin(formatoInforme.getAnteJardin());
        formatoInformeDTO.setObservacionAnteJardin(formatoInforme.getObservacionAnteJardin());
        formatoInformeDTO.setIndiceOcupacion(formatoInforme.getIndiceOcupacion());
        formatoInformeDTO.setObservacionIndiceOcupacion(formatoInforme.getObservacionIndiceOcupacion());
        formatoInformeDTO.setIndiceConstruccion(formatoInforme.getIndiceConstruccion());
        formatoInformeDTO.setObservacionIndiceConstruccion(formatoInforme.getObservacionIndiceConstruccion());
        formatoInformeDTO.setPredioSubdivididoFisicamente(formatoInforme.getPredioSubdivididoFisicamente());
        formatoInformeDTO.setObservacionPredioSubdivididoFisicamente(formatoInforme.getObservacionPredioSubdivididoFisicamente());
        formatoInformeDTO.setEstadoConservacionAlcantarrillado(formatoInforme.getEstadoConservacionAlcantarrillado());
        formatoInformeDTO.setEstadoConservacionAcueducto(formatoInforme.getEstadoConservacionAcueducto());
        formatoInformeDTO.setEstadoConservacionGas(formatoInforme.getEstadoConservacionGas());
        formatoInformeDTO.setEstadoConservacionEnergia(formatoInforme.getEstadoConservacionEnergia());
        formatoInformeDTO.setEstadoConservacionTelefono(formatoInforme.getEstadoConservacionTelefono());
        formatoInformeDTO.setEstadoConservacionViasPavimentadas(formatoInforme.getEstadoConservacionViasPavimentadas());
        formatoInformeDTO.setEstadoConservacionSardinelesEnLasVias(formatoInforme.getEstadoConservacionSardinelesEnLasVias());
        formatoInformeDTO.setEstadoConservacionAndenesEnLasVias(formatoInforme.getEstadoConservacionAndenesEnLasVias());
        formatoInformeDTO.setDemandaInteres(formatoInforme.getDemandaInteres());
        formatoInformeDTO.setNivelEquipamientoComercial(formatoInforme.getNivelEquipamientoComercial());
        formatoInformeDTO.setNivelEquipamientoEscolar(formatoInforme.getNivelEquipamientoEscolar());
        formatoInformeDTO.setNivelEquipamientoAsistencial(formatoInforme.getNivelEquipamientoAsistencial());
        formatoInformeDTO.setNivelEquipamientoEstacionamiento(formatoInforme.getNivelEquipamientoEstacionamiento());
        formatoInformeDTO.setNivelEquipamientoAreasVerdes(formatoInforme.getNivelEquipamientoAreasVerdes());
        formatoInformeDTO.setNivelEquipamientoZonasRecreativas(formatoInforme.getNivelEquipamientoZonasRecreativas());
        formatoInformeDTO.setDistanciaAproximadaComercial(formatoInforme.getDistanciaAproximadaComercial());
        formatoInformeDTO.setDistanciaAproximadaEscolar(formatoInforme.getDistanciaAproximadaEscolar());
        formatoInformeDTO.setDistanciaAproximadaAsistencial(formatoInforme.getDistanciaAproximadaAsistencial());
        formatoInformeDTO.setDistanciaAproximadaEstacionamiento(formatoInforme.getDistanciaAproximadaEstacionamiento());
        formatoInformeDTO.setDistanciaAproximadaAreasVerdes(formatoInforme.getDistanciaAproximadaAreasVerdes());
        formatoInformeDTO.setDistanciaAproximadaZonasRecreativas(formatoInforme.getDistanciaAproximadaZonasRecreativas());
        formatoInformeDTO.setDescripcionGeneralSector(formatoInforme.getDescripcionGeneralSector());
        formatoInformeDTO.setObservacionesGeneralesConstruccion(formatoInforme.getObservacionesGeneralesConstruccion());
        formatoInformeDTO.setObservacionesGeneralesInmueble(formatoInforme.getObservacionesGeneralesInmueble());
        formatoInformeDTO.setObservacionesEstructura(formatoInforme.getObservacionesEstructura());
        formatoInformeDTO.setObservacionesDependencias(formatoInforme.getObservacionesDependencias());
        formatoInformeDTO.setObservacionesAcabados(formatoInforme.getObservacionesAcabados());
        formatoInformeDTO.setDescripcionGeneral(formatoInforme.getDescripcionGeneral());
        formatoInformeDTO.setAreaActividad(formatoInforme.getAreaActividad());
        formatoInformeDTO.setUsoPrincipalPh(formatoInforme.getUsoPrincipalPh());
        formatoInformeDTO.setRph(formatoInforme.getRph());
        formatoInformeDTO.setMostrarPredioSubdivididoFisicamente(formatoInforme.getMostrarPredioSubdivididoFisicamente());
        formatoInformeDTO.setUnidades(formatoInforme.getUnidades());
        formatoInformeDTO.setContadoresAgua(formatoInforme.getContadoresAgua());
        formatoInformeDTO.setContadoresLuz(formatoInforme.getContadoresLuz());
        formatoInformeDTO.setAccesos(formatoInforme.getAccesos());
        
        formatoInformeDTO.setTfc1(formatoInforme.getTfc1());
        formatoInformeDTO.setTfc2(formatoInforme.getTfc2());
        formatoInformeDTO.setTfc3(formatoInforme.getTfc3());
        formatoInformeDTO.setTfc4(formatoInforme.getTfc4());
        formatoInformeDTO.setTfc5(formatoInforme.getTfc5());
        formatoInformeDTO.setTfc6(formatoInforme.getTfc6());
        formatoInformeDTO.setTfc7(formatoInforme.getTfc7());
        formatoInformeDTO.setTfc8(formatoInforme.getTfc8());
        formatoInformeDTO.setTfc9(formatoInforme.getTfc9());
        formatoInformeDTO.setTfc10(formatoInforme.getTfc10());
        
        return formatoInformeDTO;
    }

    public void actualizar(Long id, FormatoInformeHipotecarioDTO actualizado)
            throws FormatoInformeNotFoundException, DivipolaNotFoundException {
        log.debug("Actualizando FormatoInformeHipotecarioDTO:  {}",
            actualizado);
        FormatoInformeHipotecario formatoInforme = formatoInformeHipotecarioRepository.findOne(id);
        if (formatoInforme == null) {
            throw new FormatoInformeNotFoundException();
        }
        formatoInforme.setValorComercial(actualizado.getValorComercial());
        formatoInforme.setValorCortesia(actualizado.getValorCortesia());
        formatoInforme.setMetodoDeValuacion(actualizado.getMetodoDeValuacion());
        formatoInforme.setJustificacionDeMetodologia(actualizado.getJustificacionDeMetodologia());
        formatoInforme.setDocumentosConsultados(actualizado.getDocumentosConsultados());
        formatoInforme.setAcueductoEnElSector(actualizado.getAcueductoEnElSector());
        formatoInforme.setAcueductoEnElPredio(actualizado.getAcueductoEnElPredio());
        formatoInforme.setAlcantarilladoEnElSector(actualizado.getAlcantarilladoEnElSector());
        formatoInforme.setAlcantarilladoEnElPredio(actualizado.getAlcantarilladoEnElPredio());
        formatoInforme.setEnergiaEnElSector(actualizado.getEnergiaEnElSector());
        formatoInforme.setEnergiaEnElPredio(actualizado.getEnergiaEnElPredio());
        formatoInforme.setGasEnElSector(actualizado.getGasEnElSector());
        formatoInforme.setGasEnElPredio(actualizado.getGasEnElPredio());
        formatoInforme.setTelefonoEnElSector(actualizado.getTelefonoEnElSector());
        formatoInforme.setTelefonoEnElPredio(actualizado.getTelefonoEnElPredio());
        formatoInforme.setEstrato(actualizado.getEstrato());
        formatoInforme.setBarrioLegal(actualizado.getBarrioLegal());
        if (actualizado.getUsoPredominanteInmueble() != null) {
            formatoInforme.setUsoPredominanteInmueble(actualizado.getUsoPredominanteInmueble().getKey());        	
        }
        formatoInforme.setDescUsoPredominanteInmuebleMixto(actualizado.getDescripcionUsoPredominanteMixto());
        if (actualizado.getTopografiaSector() != null) {
        	formatoInforme.setTopografiaSector( actualizado.getTopografiaSector().getKey());
        }
        if (actualizado.getTransporte() != null) {
        	formatoInforme.setTransporte(actualizado.getTransporte().getKey());
        }
        formatoInforme.setPerspectivasDeValorizacion(actualizado.getPerspectivasDeValorizacion());
        if ( actualizado.getCondicionesDeSalubridad() != null ) {
            formatoInforme.setCondicionesDeSalubridad(actualizado.getCondicionesDeSalubridad().getKey());
        }
        formatoInforme.setImpactoAmbientalAire(actualizado.getImpactoAmbientalAire());
        formatoInforme.setImpactoAmbientalBasura(actualizado.getImpactoAmbientalBasura());
        formatoInforme.setImpactoAmbientalInseguridad(actualizado.getImpactoAmbientalInseguridad());
        formatoInforme.setImpactoAmbientalRuido(actualizado.getImpactoAmbientalRuido());
        formatoInforme.setImpactoAmbientalAguasServidas(actualizado.getImpactoAmbientalAguasServidas());
        formatoInforme.setImpactoAmbientalOtro(actualizado.getImpactoAmbientalOtro());
        formatoInforme.setExplicacionImpactoAmbientalAire(actualizado.getExplicacionImpactoAmbientalAire());
        formatoInforme.setExplicacionImpactoAmbientalBasura(actualizado.getExplicacionImpactoAmbientalBasura());
        formatoInforme.setExplicacionImpactoAmbientalInseguridad(actualizado.getExplicacionImpactoAmbientalInseguridad());
        formatoInforme.setExplicacionImpactoAmbientalRuido(actualizado.getExplicacionImpactoAmbientalRuido());
        formatoInforme.setExplicacionImpactoAmbientalAguasServidas(actualizado.getExplicacionImpactoAmbientalAguasServidas());
        formatoInforme.setExplicacionOtro(actualizado.getExplicacionOtro());
        if (actualizado.getEstadoDeLasVias() != null) {
        	formatoInforme.setEstadoDeLasVias(actualizado.getEstadoDeLasVias().getKey());
        }
        formatoInforme.setViasPavimentadas(actualizado.getViasPavimentadas());
        formatoInforme.setAndenesEnLasVias(actualizado.getAndenesEnLasVias());
        formatoInforme.setSardinelesEnLasVias(actualizado.getSardinelesEnLasVias());
        formatoInforme.setParques(actualizado.getParques());
        formatoInforme.setParadero(actualizado.getParadero());
        formatoInforme.setAlumbrado(actualizado.getAlumbrado());
        formatoInforme.setZonasVerdesPublicas(actualizado.getZonasVerdesPublicas());
        formatoInforme.setArborizacion(actualizado.getArborizacion());
        formatoInforme.setAlamedas(actualizado.getAlamedas());
        formatoInforme.setCicloRutas(actualizado.getCicloRutas());
        if (actualizado.getClaseInmueble() != null) {
            formatoInforme.setClaseInmueble(actualizado.getClaseInmueble().getKey());
        }
        formatoInforme.setDescripcionOtrosTipoInmueble(actualizado.getDescripcionOtrosTipoInmueble());
        if (actualizado.getUsoActual() != null) {
            formatoInforme.setUsoActual(actualizado.getUsoActual().getKey());
        }
        formatoInforme.setDescripcionOtrosUsoInmueble(actualizado.getDescripcionOtrosUsoInmueble());
        if (actualizado.getTipoDeVivienda() != null) {
            formatoInforme.setTipoDeVivienda(actualizado.getTipoDeVivienda().getKey());
        }
        formatoInforme.setDescripcionOtrosTipoVivienda(actualizado.getDescripcionOtrosTipoVivienda());
        formatoInforme.setChip(actualizado.getChip());
        formatoInforme.setMatriculaInmobiliariaPrincipal2(actualizado.getMatriculaInmobiliariaPrincipal2());
        formatoInforme.setNumeroDeEscritura(actualizado.getNumeroDeEscritura());
        formatoInforme.setNumeroNotariaDeEscritura(actualizado.getNumeroNotariaDeEscritura());
        formatoInforme.setFechaDeEscritura(actualizado.getFechaDeEscritura());
        formatoInforme.setDepartamentoNotaria(actualizado.getDepartamentoNotaria());
        if (actualizado.getCiudadNotaria() != null) {
            Divipola ciudadNotaria = divipolaRepository.findOne(actualizado.getCiudadNotaria().getId());
            if (ciudadNotaria == null) {
                throw new DivipolaNotFoundException();
            }
            formatoInforme.setCiudadNotaria(ciudadNotaria);
        }else{
            formatoInforme.setCiudadNotaria(null);
        }
//        formatoInforme.setMatriculaInmobiliariaGaraje1(actualizado.getMatriculaInmobiliariaGaraje1());
//        formatoInforme.setMatriculaInmobiliariaGaraje2(actualizado.getMatriculaInmobiliariaGaraje2());
//        formatoInforme.setMatriculaInmobiliariaGaraje3(actualizado.getMatriculaInmobiliariaGaraje3());
//        formatoInforme.setMatriculaInmobiliariaGaraje4(actualizado.getMatriculaInmobiliariaGaraje4());
//        formatoInforme.setMatriculaInmobiliariaGaraje5(actualizado.getMatriculaInmobiliariaGaraje5());
//        formatoInforme.setGarajeParalelo1(actualizado.getGarajeParalelo1());
//        formatoInforme.setGarajeParalelo2(actualizado.getGarajeParalelo2());
//        formatoInforme.setGarajeParalelo3(actualizado.getGarajeParalelo3());
//        formatoInforme.setGarajeParalelo4(actualizado.getGarajeParalelo4());
//        formatoInforme.setGarajeParalelo5(actualizado.getGarajeParalelo5());
//        formatoInforme.setGarajeCubierto1(actualizado.getGarajeCubierto1());
//        formatoInforme.setGarajeCubierto2(actualizado.getGarajeCubierto2());
//        formatoInforme.setGarajeCubierto3(actualizado.getGarajeCubierto3());
//        formatoInforme.setGarajeCubierto4(actualizado.getGarajeCubierto4());
//        formatoInforme.setGarajeCubierto5(actualizado.getGarajeCubierto5());
//        formatoInforme.setNumeroParqueadero1(actualizado.getNumeroParqueadero1());
//        formatoInforme.setNumeroParqueadero2(actualizado.getNumeroParqueadero2());
//        formatoInforme.setNumeroParqueadero3(actualizado.getNumeroParqueadero3());
//        formatoInforme.setNumeroParqueadero4(actualizado.getNumeroParqueadero4());
//        formatoInforme.setNumeroParqueadero5(actualizado.getNumeroParqueadero5());
//        formatoInforme.setNumeroParqueaderoExclusivo1(actualizado.getNumeroParqueaderoExclusivo1());
//        formatoInforme.setNumeroParqueaderoExclusivo2(actualizado.getNumeroParqueaderoExclusivo2());
//        formatoInforme.setNumeroParqueaderoExclusivo3(actualizado.getNumeroParqueaderoExclusivo3());
//        formatoInforme.setNumeroParqueaderoExclusivo4(actualizado.getNumeroParqueaderoExclusivo4());
//        formatoInforme.setNumeroParqueaderoExclusivo5(actualizado.getNumeroParqueaderoExclusivo5());
//        formatoInforme.setGarajeServidumbre1(actualizado.getGarajeServidumbre1());
//        formatoInforme.setGarajeServidumbre2(actualizado.getGarajeServidumbre2());
//        formatoInforme.setGarajeServidumbre3(actualizado.getGarajeServidumbre3());
//        formatoInforme.setGarajeServidumbre4(actualizado.getGarajeServidumbre4());
//        formatoInforme.setGarajeServidumbre5(actualizado.getGarajeServidumbre5());
        formatoInforme.setMatriculaInmobiliariaDeposito1(actualizado.getMatriculaInmobiliariaDeposito1());
        formatoInforme.setMatriculaInmobiliariaDeposito2(actualizado.getMatriculaInmobiliariaDeposito2());
        formatoInforme.setMatriculaInmobiliariaDeposito3(actualizado.getMatriculaInmobiliariaDeposito3());
        formatoInforme.setMatriculaInmobiliariaDeposito4(actualizado.getMatriculaInmobiliariaDeposito4());
        formatoInforme.setMatriculaInmobiliariaDeposito5(actualizado.getMatriculaInmobiliariaDeposito5());
        formatoInforme.setNumeroDeposito1(actualizado.getNumeroDeposito1());
        formatoInforme.setNumeroDeposito2(actualizado.getNumeroDeposito2());
        formatoInforme.setNumeroDeposito3(actualizado.getNumeroDeposito3());
        formatoInforme.setNumeroDeposito4(actualizado.getNumeroDeposito4());
        formatoInforme.setNumeroDeposito5(actualizado.getNumeroDeposito5());
        formatoInforme.setNumeroDepositoExclusivo1(actualizado.getNumeroDepositoExclusivo1());
        formatoInforme.setNumeroDepositoExclusivo2(actualizado.getNumeroDepositoExclusivo2());
        formatoInforme.setNumeroDepositoExclusivo3(actualizado.getNumeroDepositoExclusivo3());
        formatoInforme.setNumeroDepositoExclusivo4(actualizado.getNumeroDepositoExclusivo4());
        formatoInforme.setNumeroDepositoExclusivo5(actualizado.getNumeroDepositoExclusivo5());
        formatoInforme.setNumeroDePisos(actualizado.getNumeroDePisos());
        formatoInforme.setNumeroSotanos(actualizado.getNumeroSotanos());
        formatoInforme.setVetustez(actualizado.getVetustez());
        if (actualizado.getEstadoDeConservacion() != null) {
            formatoInforme.setEstadoDeConservacion(actualizado.getEstadoDeConservacion().getKey());	
        }
        if (actualizado.getEstadoDeConstruccion() != null) {
            formatoInforme.setEstadoDeConstruccion(actualizado.getEstadoDeConstruccion().getKey());        	
        }
        formatoInforme.setEstadoDeObra(actualizado.getEstadoDeObra() ? 1 : 0);
        formatoInforme.setAnoDeConstruccion(actualizado.getAnoDeConstruccion());
        formatoInforme.setPorcentajeAvance(actualizado.getPorcentajeAvance());
        formatoInforme.setRemodelado(actualizado.getRemodelado());
        formatoInforme.setFechaRemodelacion(actualizado.getFechaRemodelacion());
        if (actualizado.getEstructura() != null) {
            formatoInforme.setEstructura(actualizado.getEstructura().getKey());	
        }
        if (actualizado.getEstructuraReforzada() != null) {
            formatoInforme.setEstructuraReforzada(actualizado.getEstructuraReforzada().getKey());
        }
        if (actualizado.getFachada() != null) {
            formatoInforme.setFachada(actualizado.getFachada().getKey());
        }
        if (actualizado.getTipoFachada() != null) {
            formatoInforme.setTipoFachada(actualizado.getTipoFachada().getKey());
        }
        if (actualizado.getCubierta() != null) {
            formatoInforme.setCubierta(actualizado.getCubierta().getKey());
        }
        if (actualizado.getPisosBodega() != null) {
            formatoInforme.setPisosBodega(actualizado.getPisosBodega().getKey());
        }
        formatoInforme.setTipologiaViviendaUnicaFamiliar(actualizado.getTipologiaViviendaUnicaFamiliar());
        formatoInforme.setSala(actualizado.getSala());
        formatoInforme.setDeposito(actualizado.getDeposito());
        formatoInforme.setComedor(actualizado.getComedor());
        formatoInforme.setEstudio(actualizado.getEstudio());
        formatoInforme.setJardin(actualizado.getJardin());
        formatoInforme.setBanoSocial(actualizado.getBanoSocial());
        formatoInforme.setEstarHabitacion(actualizado.getEstarHabitacion());
        formatoInforme.setHabitaciones(actualizado.getHabitaciones());
        formatoInforme.setDepositosPrivados(actualizado.getDepositosPrivados());
        formatoInforme.setDepositosExclusivos(actualizado.getDepositosExclusivos());
        formatoInforme.setNumeroTotalDepositos(actualizado.getNumeroTotalDepositos());
        formatoInforme.setBanoPrivado(actualizado.getBanoPrivado());
        formatoInforme.setCocina(actualizado.getCocina());
        formatoInforme.setCuartoServicio(actualizado.getCuartoServicio());
        formatoInforme.setOficina(actualizado.getOficina());
        formatoInforme.setBanoServicio(actualizado.getBanoServicio());
        formatoInforme.setPatioInterior(actualizado.getPatioInterior());
        formatoInforme.setTerraza(actualizado.getTerraza());
        formatoInforme.setBodega(actualizado.getBodega());
        formatoInforme.setZonaDeRopas(actualizado.getZonaDeRopas());
        formatoInforme.setBalcon(actualizado.getBalcon());
        formatoInforme.setCloset(actualizado.getCloset());
        formatoInforme.setLocal(actualizado.getLocal());
        formatoInforme.setZonaVerdePrivada(actualizado.getZonaVerdePrivada());
        if (actualizado.getIluminacion() != null) {
        	formatoInforme.setIluminacion(actualizado.getIluminacion().getKey());
        }
        if (actualizado.getVentilacion() != null) {
        	formatoInforme.setVentilacion(actualizado.getVentilacion().getKey());
        }
        
//        formatoInforme.setGarajePrivado(actualizado.getGarajePrivado());
//        formatoInforme.setGarajeExclusivo(actualizado.getGarajeExclusivo());
//        formatoInforme.setBahiaComunal(actualizado.getBahiaComunal());
//        formatoInforme.setGarajeDoble1(actualizado.getGarajeDoble1());
//        formatoInforme.setGarajeDoble2(actualizado.getGarajeDoble2());
//        formatoInforme.setGarajeDoble3(actualizado.getGarajeDoble3());
//        formatoInforme.setGarajeDoble4(actualizado.getGarajeDoble4());
//        formatoInforme.setGarajeDoble5(actualizado.getGarajeDoble5());
//        formatoInforme.setGarajeParalelo(actualizado.getGarajeParalelo());
        formatoInforme.setNumeroTotalDeGarajes(actualizado.getNumeroTotalDeGarajes());
        formatoInforme.setTotalCuposParquedaro(actualizado.getTotalCuposParquedaro());
        if (actualizado.getEstadoAcabadosPisos() != null ) {
        	formatoInforme.setEstadoAcabadosPisos(actualizado.getEstadoAcabadosPisos().getKey());
        }
        if (actualizado.getEstadoAcabadosMuros() != null) {
        	formatoInforme.setEstadoAcabadosMuros(actualizado.getEstadoAcabadosMuros().getKey());
        }
        if (actualizado.getEstadoAcabadosTechos() != null) {
        	formatoInforme.setEstadoAcabadosTechos(actualizado.getEstadoAcabadosTechos().getKey());
        }
        if (actualizado.getEstadoAcabadosMadera() != null) {
        	formatoInforme.setEstadoAcabadosMadera(actualizado.getEstadoAcabadosMadera().getKey());
        }
        if (actualizado.getEstadoAcabadosMetal() != null) {
        	formatoInforme.setEstadoAcabadosMetal(actualizado.getEstadoAcabadosMetal().getKey());
        }
        if (actualizado.getEstadoAcabadosBanos() != null) {
        	formatoInforme.setEstadoAcabadosBanos(actualizado.getEstadoAcabadosBanos().getKey());
        }
        if (actualizado.getEstadoAcabadosCocina() != null) {
        	formatoInforme.setEstadoAcabadosCocina(actualizado.getEstadoAcabadosCocina().getKey());
        }
        if (actualizado.getCalidadAcabadosPisos() != null) {
        	formatoInforme.setCalidadAcabadosPisos(actualizado.getCalidadAcabadosPisos().getKey());
        }
        if (actualizado.getCalidadAcabadosMuros() !=null ) {
        	formatoInforme.setCalidadAcabadosMuros(actualizado.getCalidadAcabadosMuros().getKey());
        }
        if (actualizado.getCalidadAcabadosTechos() != null ) {
        	 formatoInforme.setCalidadAcabadosTechos(actualizado.getCalidadAcabadosTechos().getKey());
        }
        if (actualizado.getCalidadAcabadosMadera() != null) {
        	formatoInforme.setCalidadAcabadosMadera(actualizado.getCalidadAcabadosMadera().getKey());
        }
        if (actualizado.getCalidadAcabadosMetal() != null ) {
        	 formatoInforme.setCalidadAcabadosMetal(actualizado.getCalidadAcabadosMetal().getKey());
        }
        if (actualizado.getCalidadAcabadosBanos() !=null) {
        	formatoInforme.setCalidadAcabadosBanos(actualizado.getCalidadAcabadosBanos().getKey());
        }
        if (actualizado.getCalidadAcabadosCocina()!=null) {
        	formatoInforme.setCalidadAcabadosCocina(actualizado.getCalidadAcabadosCocina().getKey());
        }
        
        formatoInforme.setSometidoAPropiedadHorizontal(actualizado.getSometidoAPropiedadHorizontal());
        formatoInforme.setConjuntoCerrado(actualizado.getConjuntoCerrado());
        if (actualizado.getUbicacionDelInmueble() != null) {
            formatoInforme.setUbicacionDelInmueble(actualizado.getUbicacionDelInmueble().getKey());
        }
        formatoInforme.setNumeroDeEdificios(actualizado.getNumeroDeEdificios());
        formatoInforme.setUnidadesPorPiso(actualizado.getUnidadesPorPiso());
        formatoInforme.setTotalUnidades(actualizado.getTotalUnidades());
        formatoInforme.setPorteria(actualizado.getPorteria());
        formatoInforme.setCitofono(actualizado.getCitofono());
        formatoInforme.setBicicletero(actualizado.getBicicletero());
        formatoInforme.setPiscina(actualizado.getPiscina());
        formatoInforme.setTanqueDeAgua(actualizado.getTanqueDeAgua());
        formatoInforme.setClubHouse(actualizado.getClubHouse());
        formatoInforme.setGarajeVisitantes(actualizado.getGarajeVisitantes());
        formatoInforme.setJuegosNinos(actualizado.getJuegosNinos());
        formatoInforme.setCanchaMultiple(actualizado.getCanchaMultiple());
        formatoInforme.setBombaEyectora(actualizado.getBombaEyectora());
        formatoInforme.setAireAcondicionadoCentral(actualizado.getAireAcondicionadoCentral());
        formatoInforme.setCanchaSquash(actualizado.getCanchaSquash());
        formatoInforme.setZonasVerdesComunales(actualizado.getZonasVerdesComunales());
        formatoInforme.setGimnasio(actualizado.getGimnasio());
        formatoInforme.setGolfito(actualizado.getGolfito());
        formatoInforme.setSalonComunal(actualizado.getSalonComunal());
        formatoInforme.setShutBasuras(actualizado.getShutBasuras());
        formatoInforme.setEquipoDePresionConstante(actualizado.getEquipoDePresionConstante());
        formatoInforme.setPlantaElectrica(actualizado.getPlantaElectrica());
        formatoInforme.setAscensor(actualizado.getAscensor());
        formatoInforme.setNumeroDeAscensores(actualizado.getNumeroDeAscensores());
        formatoInforme.setOtros(actualizado.getOtros());
        formatoInforme.setActualidadEdificadora(actualizado.getActualidadEdificadora());
        formatoInforme.setEntregaDeDocumentos(actualizado.getEntregaDeDocumentos());
        formatoInforme.setComportamientoOfertaDemanda(actualizado.getComportamientoOfertaDemanda());
        formatoInforme.setTiempoEsperadoDeComercializacion(actualizado.getTiempoEsperadoDeComercializacion());
        formatoInforme.setPorcentajeTerreno(actualizado.getPorcentajeTerreno());
        formatoInforme.setValorProporcionalTerreno(actualizado.getValorProporcionalTerreno());
        formatoInforme.setValorProporcionalConstruccion(actualizado.getValorProporcionalConstruccion());
        formatoInforme.setValorIntegralTerreno(actualizado.getValorIntegralTerreno());
        formatoInforme.setValorIntegralConstruccion(actualizado.getValorIntegralConstruccion());
        formatoInforme.setObservaciones(actualizado.getObservaciones());
        formatoInforme.setDireccionAnexos(actualizado.getDireccionAnexos());
        formatoInforme.setOtrasDirecciones(actualizado.getOtrasDirecciones());
        formatoInforme.setLicenciaConstruccion(actualizado.getLicenciaConstruccion());
        if (actualizado.getTipoLicencia() !=null) {
        	formatoInforme.setTipoLicencia(actualizado.getTipoLicencia().getKey());	
        }
        formatoInforme.setNumeroLicencia(actualizado.getNumeroLicencia());
        if (actualizado.getReparados() != null) {
            formatoInforme.setReparados(actualizado.getReparados().getKey());
        }
        if (actualizado.getDanosPrevios() != null) {
            formatoInforme.setDanosPrevios(actualizado.getDanosPrevios().getKey());        	
        } 
        if (actualizado.getMaterialConstructor() !=null ) {
        	formatoInforme.setMaterialConstructor(actualizado.getMaterialConstructor().getKey());
        }
        if (actualizado.getDetalleMaterial() !=null ) {
        	formatoInforme.setDetalleMaterial(actualizado.getDetalleMaterial().getKey());
        }
		if (actualizado.getIrregularidadPlanta() != null) {
			formatoInforme.setIrregularidadPlanta(actualizado.getIrregularidadPlanta().getKey());
		}
		if (actualizado.getIrregularidadAltura() !=null) {
			formatoInforme.setIrregularidadAltura(actualizado.getIrregularidadAltura().getKey());
		}
        
        formatoInforme.setCoeficiente(actualizado.getCoeficiente());
        formatoInforme.setAreaConstruida(actualizado.getAreaConstruida());
        formatoInforme.setAreaPrivada(actualizado.getAreaPrivada());
        formatoInforme.setObservacionesGarajes( actualizado.getObservacionesGarajes() );
        formatoInforme.setNumeroPiso(actualizado.getNumeroPiso());
        formatoInforme.setOcupante(actualizado.getOcupante());
        formatoInforme.setCondicionPh(actualizado.getCondicionPh());
        formatoInforme.setTeatrino(actualizado.getTeatrino());
        formatoInforme.setSauna(actualizado.getSauna());
        formatoInforme.setCalefaccion(actualizado.getCalefaccion());
        formatoInforme.setTerrazaComunal(actualizado.getTerrazaComunal());
        formatoInforme.setTurco(actualizado.getTurco());
        formatoInforme.setBbq(actualizado.getBbq());
        formatoInforme.setGuarderia(actualizado.getGuarderia());
        formatoInforme.setJardinInfantil(actualizado.getJardinInfantil());
        //-Estado vigilancia depende Vigilancia Privada para ser actualizado el modelo
        formatoInforme.setVigilanciaPrivada(Boolean.TRUE.equals(actualizado.getEstadoVigilancia()) ? actualizado.getVigilanciaPrivada(): null);
        formatoInforme.setAdministracion(actualizado.getAdministracion());
        formatoInforme.setValorAdministracion(Boolean.TRUE.equals(actualizado.getAdministracion()) ? actualizado.getValorAdministracion(): null);
        formatoInforme.setDecretoAcuerdo(actualizado.getDecretoAcuerdo());
        formatoInforme.setObservacionDecretoAcuerdo(actualizado.getObservacionDecretoAcuerdo());
        formatoInforme.setAlturaPermitida(actualizado.getAlturaPermitida());
        formatoInforme.setObservacionAlturaPermitida(actualizado.getObservacionAlturaPermitida());
        formatoInforme.setUsoPrincipal(actualizado.getUsoPrincipal());
        formatoInforme.setObservacionUsoPrincipal(actualizado.getObservacionUsoPrincipal());
        formatoInforme.setAislamientoPosterior(actualizado.getAislamientoPosterior());
        formatoInforme.setObservacionAislamientoPosterior(actualizado.getObservacionAislamientoPosterior());
        formatoInforme.setAislamientoLateral(actualizado.getAislamientoLateral());
        formatoInforme.setObservacionAislamientoLateral(actualizado.getObservacionAislamientoLateral());
        formatoInforme.setAnteJardin(actualizado.getAnteJardin());
        formatoInforme.setObservacionAnteJardin(actualizado.getObservacionAnteJardin());
        formatoInforme.setIndiceOcupacion(actualizado.getIndiceOcupacion());
        formatoInforme.setObservacionIndiceOcupacion(actualizado.getObservacionIndiceOcupacion());
        formatoInforme.setIndiceConstruccion(actualizado.getIndiceConstruccion());
        formatoInforme.setObservacionIndiceConstruccion(actualizado.getObservacionIndiceConstruccion());
        formatoInforme.setPredioSubdivididoFisicamente(actualizado.getPredioSubdivididoFisicamente());
        formatoInforme.setObservacionPredioSubdivididoFisicamente(actualizado.getObservacionPredioSubdivididoFisicamente());
        formatoInforme.setEstadoConservacionAlcantarrillado(actualizado.getEstadoConservacionAlcantarrillado());
        formatoInforme.setEstadoConservacionAcueducto(actualizado.getEstadoConservacionAcueducto());
        formatoInforme.setEstadoConservacionGas(actualizado.getEstadoConservacionGas());
        formatoInforme.setEstadoConservacionEnergia(actualizado.getEstadoConservacionEnergia());
        formatoInforme.setEstadoConservacionTelefono(actualizado.getEstadoConservacionTelefono());
        formatoInforme.setEstadoConservacionViasPavimentadas(actualizado.getEstadoConservacionViasPavimentadas());
        formatoInforme.setEstadoConservacionSardinelesEnLasVias(actualizado.getEstadoConservacionSardinelesEnLasVias());
        formatoInforme.setEstadoConservacionAndenesEnLasVias(actualizado.getEstadoConservacionAndenesEnLasVias());
        formatoInforme.setDemandaInteres(actualizado.getDemandaInteres());
        formatoInforme.setNivelEquipamientoComercial(actualizado.getNivelEquipamientoComercial());
        formatoInforme.setNivelEquipamientoEscolar(actualizado.getNivelEquipamientoEscolar());
        formatoInforme.setNivelEquipamientoAsistencial(actualizado.getNivelEquipamientoAsistencial());
        formatoInforme.setNivelEquipamientoEstacionamiento(actualizado.getNivelEquipamientoEstacionamiento());
        formatoInforme.setNivelEquipamientoAreasVerdes(actualizado.getNivelEquipamientoAreasVerdes());
        formatoInforme.setNivelEquipamientoZonasRecreativas(actualizado.getNivelEquipamientoZonasRecreativas());
        formatoInforme.setDistanciaAproximadaComercial(actualizado.getDistanciaAproximadaComercial());
        formatoInforme.setDistanciaAproximadaEscolar(actualizado.getDistanciaAproximadaEscolar());
        formatoInforme.setDistanciaAproximadaAsistencial(actualizado.getDistanciaAproximadaAsistencial());
        formatoInforme.setDistanciaAproximadaEstacionamiento(actualizado.getDistanciaAproximadaEstacionamiento());
        formatoInforme.setDistanciaAproximadaAreasVerdes(actualizado.getDistanciaAproximadaAreasVerdes());
        formatoInforme.setDistanciaAproximadaZonasRecreativas(actualizado.getDistanciaAproximadaZonasRecreativas());
        formatoInforme.setDescripcionGeneralSector(actualizado.getDescripcionGeneralSector());
        formatoInforme.setObservacionesGeneralesConstruccion(actualizado.getObservacionesGeneralesConstruccion());
        formatoInforme.setObservacionesGeneralesInmueble(actualizado.getObservacionesGeneralesInmueble());
        formatoInforme.setObservacionesEstructura(actualizado.getObservacionesEstructura());
        formatoInforme.setObservacionesDependencias(actualizado.getObservacionesDependencias());
        formatoInforme.setObservacionesAcabados(actualizado.getObservacionesAcabados());
        formatoInforme.setDescripcionGeneral(actualizado.getDescripcionGeneral());
        formatoInforme.setAreaActividad(actualizado.getAreaActividad());
        formatoInforme.setUsoPrincipalPh(actualizado.getUsoPrincipalPh());
        formatoInforme.setRph(actualizado.getRph());
        formatoInforme.setMostrarPredioSubdivididoFisicamente(actualizado.getMostrarPredioSubdivididoFisicamente());
        formatoInforme.setUnidades(actualizado.getUnidades());
        formatoInforme.setContadoresAgua(actualizado.getContadoresAgua());
        formatoInforme.setContadoresLuz(actualizado.getContadoresLuz());
        formatoInforme.setAccesos(actualizado.getAccesos());
        
        formatoInforme.setTfc1(actualizado.getTfc1());
        formatoInforme.setTfc2(actualizado.getTfc2());
        formatoInforme.setTfc3(actualizado.getTfc3());
        formatoInforme.setTfc4(actualizado.getTfc4());
        formatoInforme.setTfc5(actualizado.getTfc5());
        formatoInforme.setTfc6(actualizado.getTfc6());
        formatoInforme.setTfc7(actualizado.getTfc7());
        formatoInforme.setTfc8(actualizado.getTfc8());
        formatoInforme.setTfc9(actualizado.getTfc9());
        formatoInforme.setTfc10(actualizado.getTfc10());

    }

    public FormatoInformeComercialDTO escribirDTO(
            FormatoInformeComercial formatoInformeComercial) {
        FormatoInformeComercialDTO formatoInformeComercialDTO = new FormatoInformeComercialDTO(formatoInformeComercial.getAvaluo().getId());
        formatoInformeComercialDTO.setId(formatoInformeComercial.getId());
        formatoInformeComercialDTO.setVereda(formatoInformeComercial.getVereda());
        formatoInformeComercialDTO.setNombrePredio(formatoInformeComercial.getNombrePredio());
        formatoInformeComercialDTO.setLocalizacion(formatoInformeComercial.getLocalizacion());
        formatoInformeComercialDTO.setDestinatarioDeLaValuacion(formatoInformeComercial.getDestinatarioDeLaValuacion());
        formatoInformeComercialDTO.setObservacionesTipoInmueble(formatoInformeComercial.getObservacionesTipoInmueble());
        formatoInformeComercialDTO.setUsoActualDelInmueble(formatoInformeComercial.getUsoActualDelInmueble());
        formatoInformeComercialDTO.setObservacionesUsoActualDelInmueble(formatoInformeComercial.getObservacionesUsoActualDelInmueble());
        formatoInformeComercialDTO.setUsoProyectadoDelInmueble(formatoInformeComercial.getUsoProyectadoDelInmueble());
        formatoInformeComercialDTO.setObservacionesUsoProyectadoDelInmueble(formatoInformeComercial.getObservacionesUsoProyectadoDelInmueble());
        formatoInformeComercialDTO.setPeriodoDeMercadeo(formatoInformeComercial.getPeriodoDeMercadeo());
        formatoInformeComercialDTO.setObservacionesPeriodoDeMercadeo(formatoInformeComercial.getObservacionesPeriodoDeMercadeo());
        formatoInformeComercialDTO.setDocumentosConsultados(formatoInformeComercial.getDocumentosConsultados());
        formatoInformeComercialDTO.setFechaAporteDeDocumentos(formatoInformeComercial.getFechaAporteDeDocumentos());
        formatoInformeComercialDTO.setOtrosDocumentos(formatoInformeComercial.getOtrosDocumentos());
        formatoInformeComercialDTO.setObservacionesDeTitulacion(formatoInformeComercial.getObservacionesDeTitulacion());
        formatoInformeComercialDTO.setDescripcionGeneralMunicipioOSector(formatoInformeComercial.getDescripcionGeneralMunicipioOSector());
        formatoInformeComercialDTO.setUbicacionDelSector(formatoInformeComercial.getUbicacionDelSector());
        formatoInformeComercialDTO.setLimiteNorte(formatoInformeComercial.getLimiteNorte());
        formatoInformeComercialDTO.setLimiteSur(formatoInformeComercial.getLimiteSur());
        formatoInformeComercialDTO.setLimiteOriente(formatoInformeComercial.getLimiteOriente());
        formatoInformeComercialDTO.setLimiteOccidente(formatoInformeComercial.getLimiteOccidente());
        formatoInformeComercialDTO.setBarriosCercanos(formatoInformeComercial.getBarriosCercanos());
        formatoInformeComercialDTO.setUsoPredominanteDelSector(formatoInformeComercial.getUsoPredominanteDelSector());
        formatoInformeComercialDTO.setComercializacion(formatoInformeComercial.getComercializacion());
        formatoInformeComercialDTO.setTiempoEsperadoDeComercializacion(formatoInformeComercial.getTiempoEsperadoDeComercializacion());
        formatoInformeComercialDTO.setEstrato(formatoInformeComercial.getEstrato());
        formatoInformeComercialDTO.setCaracteristicasSocioEconomicas(formatoInformeComercial.getCaracteristicasSocioEconomicas());
        formatoInformeComercialDTO.setOrdenPublico(formatoInformeComercial.getOrdenPublico());
        formatoInformeComercialDTO.setObservacionesViasDeAcceso(formatoInformeComercial.getObservacionesViasDeAcceso());
        formatoInformeComercialDTO.setEquipamientoDeRedVial(formatoInformeComercial.getEquipamientoDeRedVial());
        formatoInformeComercialDTO.setEstadoDeConservacion(formatoInformeComercial.getEstadoDeConservacion());
        formatoInformeComercialDTO.setUsoDelSuelo(formatoInformeComercial.getUsoDelSuelo());
        formatoInformeComercialDTO.setInfraestructuraUrbanistica(formatoInformeComercial.getInfraestructuraUrbanistica());
        formatoInformeComercialDTO.setInfraestructuraDotacional(formatoInformeComercial.getInfraestructuraDotacional());
        formatoInformeComercialDTO.setAmoblamientoUrbano(formatoInformeComercial.getAmoblamientoUrbano());
        formatoInformeComercialDTO.setAndenesYSardineles(formatoInformeComercial.getAndenesYSardineles());
        formatoInformeComercialDTO.setZonasVerdes(formatoInformeComercial.getZonasVerdes());
        formatoInformeComercialDTO.setTopografia(formatoInformeComercial.getTopografia());
        formatoInformeComercialDTO.setAlumbradoPublico(formatoInformeComercial.getAlumbradoPublico());
        formatoInformeComercialDTO.setAlcantarillado(formatoInformeComercial.getAlcantarillado());
        formatoInformeComercialDTO.setAcueducto(formatoInformeComercial.getAcueducto());
        formatoInformeComercialDTO.setEnergia(formatoInformeComercial.getEnergia());
        formatoInformeComercialDTO.setGas(formatoInformeComercial.getGas());
        formatoInformeComercialDTO.setTelefono(formatoInformeComercial.getTelefono());
        formatoInformeComercialDTO.setObservacionesServiciosPublicos(formatoInformeComercial.getObservacionesServiciosPublicos());
        formatoInformeComercialDTO.setTransportePublico(formatoInformeComercial.getTransportePublico());
        formatoInformeComercialDTO.setFrecuencia(formatoInformeComercial.getFrecuencia());
        formatoInformeComercialDTO.setCubrimiento(formatoInformeComercial.getCubrimiento());
        if (formatoInformeComercial.getPerspectivasDeValorizacion() != null && formatoInformeComercial.getPerspectivasDeValorizacion() > 0) {
            formatoInformeComercialDTO.setPerspectivasDeValorizacion(MBR
                    .fromKey(formatoInformeComercial.getPerspectivasDeValorizacion()));
        }
        formatoInformeComercialDTO.setObservacionesPerspectivasDeValorizacion(formatoInformeComercial.getObservacionesPerspectivasDeValorizacion());
        formatoInformeComercialDTO.setNormatividad(formatoInformeComercial.getNormatividad());
        formatoInformeComercialDTO.setDescripcionDelInmueble(formatoInformeComercial.getDescripcionDelInmueble());
        formatoInformeComercialDTO.setLocalizacionEspecifacaDelInmueble(formatoInformeComercial.getLocalizacionEspecifacaDelInmueble());
        formatoInformeComercialDTO.setDistaciaPartiendoDelCascoUrbano(formatoInformeComercial.getDistaciaPartiendoDelCascoUrbano());
        formatoInformeComercialDTO.setLinderoNorte(formatoInformeComercial.getLinderoNorte());
        formatoInformeComercialDTO.setLinderoSur(formatoInformeComercial.getLinderoSur());
        formatoInformeComercialDTO.setLinderoOriente(formatoInformeComercial.getLinderoOriente());
        formatoInformeComercialDTO.setLinderoOccidente(formatoInformeComercial.getLinderoOccidente());
        formatoInformeComercialDTO.setFuenteLinderos(formatoInformeComercial.getFuenteLinderos());
        formatoInformeComercialDTO.setDescripcionViasDeAccesoInternas(formatoInformeComercial.getDescripcionViasDeAccesoInternas());
        formatoInformeComercialDTO.setFrenteSobreLaVia(formatoInformeComercial.getFrenteSobreLaVia());
        formatoInformeComercialDTO.setMetros(formatoInformeComercial.getMetros());
        formatoInformeComercialDTO.setCercasPerimedales(formatoInformeComercial.getCercasPerimedales());
        formatoInformeComercialDTO.setServidumbres(formatoInformeComercial.getServidumbres());
        formatoInformeComercialDTO.setPropiedadHorizontal(formatoInformeComercial.getPropiedadHorizontal());
        formatoInformeComercialDTO.setDescripcionDeLaPropiedadHorizontal(formatoInformeComercial.getDescripcionDeLaPropiedadHorizontal());
        formatoInformeComercialDTO.setVidaUtil(formatoInformeComercial.getVidaUtil());
        formatoInformeComercialDTO.setVidaDelInmueble(formatoInformeComercial.getVidaDelInmueble());
        formatoInformeComercialDTO.setVidaRemanente(formatoInformeComercial.getVidaRemanente());
        formatoInformeComercialDTO.setJustificacionVidaUtil(formatoInformeComercial.getJustificacionVidaUtil());
        formatoInformeComercialDTO.setEstructuraEdificio(formatoInformeComercial.getEstructuraEdificio());
        formatoInformeComercialDTO.setDescripcionEstructura(formatoInformeComercial.getDescripcionEstructura());
        formatoInformeComercialDTO.setPlacasDeEntrepiso(formatoInformeComercial.getPlacasDeEntrepiso());
        formatoInformeComercialDTO.setObservacionesPlacasEntrePiso(formatoInformeComercial.getObservacionesPlacasEntrePiso());
        formatoInformeComercialDTO.setFachada(formatoInformeComercial.getFachada());
        formatoInformeComercialDTO.setDescripcionFachada(formatoInformeComercial.getDescripcionFachada());
        formatoInformeComercialDTO.setCubierta(formatoInformeComercial.getCubierta());
        formatoInformeComercialDTO.setDescripcionCubierta(formatoInformeComercial.getDescripcionCubierta());
        formatoInformeComercialDTO.setEscaleras(formatoInformeComercial.getEscaleras());
        formatoInformeComercialDTO.setDescripcionEscaleras(formatoInformeComercial.getDescripcionEscaleras());
        formatoInformeComercialDTO.setEstructura(formatoInformeComercial.getEstructura());
        formatoInformeComercialDTO.setCategoriaAcabados(formatoInformeComercial.getCategoriaAcabados());
        formatoInformeComercialDTO.setDistribucion(formatoInformeComercial.getDistribucion());
        formatoInformeComercialDTO.setEstadoDeConservacionSectorRural(formatoInformeComercial.getEstadoDeConservacionSectorRural());
        formatoInformeComercialDTO.setEquipamientoComunal(formatoInformeComercial.getEquipamientoComunal());
        formatoInformeComercialDTO.setDescripcionDelSuelo(formatoInformeComercial.getDescripcionDelSuelo());
        formatoInformeComercialDTO.setRelieve(formatoInformeComercial.getRelieve());
        formatoInformeComercialDTO.setErosion(formatoInformeComercial.getErosion());
        formatoInformeComercialDTO.setTemperatura(formatoInformeComercial.getTemperatura());
        formatoInformeComercialDTO.setPisoTermico(formatoInformeComercial.getPisoTermico());
        formatoInformeComercialDTO.setAlturaMsnm(formatoInformeComercial.getAlturaMsnm());
        formatoInformeComercialDTO.setPrecipitacionAnualMm(formatoInformeComercial.getPrecipitacionAnualMm());
        formatoInformeComercialDTO.setFormaGeometrica(formatoInformeComercial.getFormaGeometrica());
        formatoInformeComercialDTO.setRegular(formatoInformeComercial.getRegular());
        formatoInformeComercialDTO.setIrregular(formatoInformeComercial.getIrregular());
        formatoInformeComercialDTO.setInundabilidad(formatoInformeComercial.getInundabilidad());
        formatoInformeComercialDTO.setDistribucionDeLluvias(formatoInformeComercial.getDistribucionDeLluvias());
        formatoInformeComercialDTO.setPedregosidad(formatoInformeComercial.getPedregosidad());
        formatoInformeComercialDTO.setFertilidadNatural(formatoInformeComercial.getFertilidadNatural());
        formatoInformeComercialDTO.setNivelFreatico(formatoInformeComercial.getNivelFreatico());
        formatoInformeComercialDTO.setClaseAgrologica(formatoInformeComercial.getClaseAgrologica());
        formatoInformeComercialDTO.setValorPotencial(formatoInformeComercial.getValorPotencial());
        formatoInformeComercialDTO.setCapaVegetal(formatoInformeComercial.getCapaVegetal());
        formatoInformeComercialDTO.setCondicionesAgronomicas(formatoInformeComercial.getCondicionesAgronomicas());
        formatoInformeComercialDTO.setCondicionesAgrologicas(formatoInformeComercial.getCondicionesAgrologicas());
        formatoInformeComercialDTO.setDescripcionCultivos(formatoInformeComercial.getDescripcionCultivos());
        formatoInformeComercialDTO.setRecursosHidricos(formatoInformeComercial.getRecursosHidricos());
        formatoInformeComercialDTO.setIrrigacion(formatoInformeComercial.getIrrigacion());
        formatoInformeComercialDTO.setRestriccionesFisicas(formatoInformeComercial.getRestriccionesFisicas());
        formatoInformeComercialDTO.setOtrosCutivos(formatoInformeComercial.getOtrosCutivos());
        formatoInformeComercialDTO.setBosques(formatoInformeComercial.getBosques());
        formatoInformeComercialDTO.setCultivosComerciales(formatoInformeComercial.getCultivosComerciales());
        formatoInformeComercialDTO.setDeProteccion(formatoInformeComercial.getDeProteccion());
        formatoInformeComercialDTO.setImpactoAmbiental(formatoInformeComercial.getImpactoAmbiental());
        formatoInformeComercialDTO.setFrente(formatoInformeComercial.getFrente());
        formatoInformeComercialDTO.setFondo(formatoInformeComercial.getFondo());
        formatoInformeComercialDTO.setRelacionFrenteFondo(formatoInformeComercial.getRelacionFrenteFondo());
        formatoInformeComercialDTO.setObservacionDistribucionDeAreasNoConstruidas(formatoInformeComercial.getObservacionDistribucionDeAreasNoConstruidas());
        formatoInformeComercialDTO.setConstruccion(formatoInformeComercial.getConstruccion());
        formatoInformeComercialDTO.setVidaUtilConstruccion(formatoInformeComercial.getVidaUtilConstruccion());
        formatoInformeComercialDTO.setVidaDelInmuebleConstruccion(formatoInformeComercial.getVidaDelInmuebleConstruccion());
        formatoInformeComercialDTO.setVidaRemanenteConstruccion(formatoInformeComercial.getVidaRemanenteConstruccion());
        formatoInformeComercialDTO.setObservacionesEdad(formatoInformeComercial.getObservacionesEdad());
        formatoInformeComercialDTO.setRemodelado(formatoInformeComercial.getRemodelado());
        formatoInformeComercialDTO.setAlcantarilladoConstruccion(formatoInformeComercial.getAlcantarilladoConstruccion());
        formatoInformeComercialDTO.setAcueductoConstruccion(formatoInformeComercial.getAcueductoConstruccion());
        formatoInformeComercialDTO.setEnergiaConstruccion(formatoInformeComercial.getEnergiaConstruccion());
        formatoInformeComercialDTO.setGasConstruccion(formatoInformeComercial.getGasConstruccion());
        formatoInformeComercialDTO.setTelefonoConstruccion(formatoInformeComercial.getTelefonoConstruccion());
        formatoInformeComercialDTO.setObservacionesServiciosPublicosConstruccion(formatoInformeComercial.getObservacionesServiciosPublicosConstruccion());
        formatoInformeComercialDTO.setFrenteConstruido(formatoInformeComercial.getFrenteConstruido());
        formatoInformeComercialDTO.setFondoConstruido(formatoInformeComercial.getFondoConstruido());
        formatoInformeComercialDTO.setRelacionFrenteFondoConstruido(formatoInformeComercial.getRelacionFrenteFondoConstruido());
        formatoInformeComercialDTO.setAreaPrivadaConstruido(formatoInformeComercial.getAreaPrivadaConstruido());
        formatoInformeComercialDTO.setCoeficienteDeCopropiedadConstruido(formatoInformeComercial.getCoeficienteDeCopropiedadConstruido());
        formatoInformeComercialDTO.setAreaTotalConstruida(formatoInformeComercial.getAreaTotalConstruida());
        formatoInformeComercialDTO.setFuenteConstruido(formatoInformeComercial.getFuenteConstruido());
        formatoInformeComercialDTO.setObservacionesDistribucionAreasConstruidas(formatoInformeComercial.getObservacionesDistribucionAreasConstruidas());
        formatoInformeComercialDTO.setProblemasDeEstabilidadDeSuelos(formatoInformeComercial.getProblemasDeEstabilidadDeSuelos());
        formatoInformeComercialDTO.setImpactoAmbientalYCondicionesDeSalubridad(formatoInformeComercial.getImpactoAmbientalYCondicionesDeSalubridad());
        formatoInformeComercialDTO.setServidumbresCesionesYAfectacionesViales(formatoInformeComercial.getServidumbresCesionesYAfectacionesViales());
        formatoInformeComercialDTO.setSeguridad(formatoInformeComercial.getSeguridad());
        formatoInformeComercialDTO.setProblematicasSocioEconomicas(formatoInformeComercial.getProblematicasSocioEconomicas());
        formatoInformeComercialDTO.setDescripcionDeLasHipotesisEspecialesInusualesOExtraordinarias(formatoInformeComercial.getDescripcionDeLasHipotesisEspecialesInusualesOExtraordinarias());
        formatoInformeComercialDTO.setConsideracionesGenerales(formatoInformeComercial.getConsideracionesGenerales());
        formatoInformeComercialDTO.setConsideracionesGeneralesDeLocalizacion(formatoInformeComercial.getConsideracionesGeneralesDeLocalizacion());
        formatoInformeComercialDTO.setConsideracionesGeneralesDelSector(formatoInformeComercial.getConsideracionesGeneralesDelSector());
        formatoInformeComercialDTO.setConsideracionesGeneralesDeLaCapacidadDeTerreno(formatoInformeComercial.getConsideracionesGeneralesDeLaCapacidadDeTerreno());
        formatoInformeComercialDTO.setConsideracionesGeneralesDeLaInfraestructuraVialYCapacidadDeAgua(formatoInformeComercial.getConsideracionesGeneralesDeLaInfraestructuraVialYCapacidadDeAgua());
        formatoInformeComercialDTO.setConsideracionesGeneralesDeLaConstruccion(formatoInformeComercial.getConsideracionesGeneralesDeLaConstruccion());
        formatoInformeComercialDTO.setCasosEspecialesDeAfectacion(formatoInformeComercial.getCasosEspecialesDeAfectacion());
        formatoInformeComercialDTO.setInvestigacionEconomica(formatoInformeComercial.getInvestigacionEconomica());
        formatoInformeComercialDTO.setComportamientoDeOfertaYDemanda(formatoInformeComercial.getComportamientoDeOfertaYDemanda());
        formatoInformeComercialDTO.setConceptoDeGarantia(formatoInformeComercial.getConceptoDeGarantia());
        formatoInformeComercialDTO.setObservacionesConceptoGarantia(formatoInformeComercial.getObservacionesConceptoGarantia());
        formatoInformeComercialDTO.setValorComercialDelInmueble(formatoInformeComercial.getValorRazonableDelInmueble());
        formatoInformeComercialDTO.setValorIntegralSobreElTerreno(formatoInformeComercial.getValorIntegralSobreElTerreno());
        formatoInformeComercialDTO.setValorIntegralSobreElConstruccion(formatoInformeComercial.getValorIntegralSobreElConstruccion());
        formatoInformeComercialDTO.setPorcentajeFinal(formatoInformeComercial.getPorcentajeFinal());
        formatoInformeComercialDTO.setAreaConstruida(formatoInformeComercial.getAreaConstruida());
        formatoInformeComercialDTO.setAreaInfraestructura(formatoInformeComercial.getAreaInfraestructura());
        formatoInformeComercialDTO.setAreaCultivosAvaluables(formatoInformeComercial.getAreaCultivosAvaluables());
        if (formatoInformeComercial.getInmuebles() != null && !formatoInformeComercial.getInmuebles().isEmpty()) {
            formatoInformeComercialDTO.setInmuebles(inmuebleEnsamblador.escribirListaDTO(formatoInformeComercial.getInmuebles()));
        }
        if (formatoInformeComercial.getExplotacionesEconomicas() != null && !formatoInformeComercial.getExplotacionesEconomicas().isEmpty()) {
            formatoInformeComercialDTO.setExplotacionesEconomicas(
                    explotacionEconomicaEnsamblador.escribirListaDTO(
                        formatoInformeComercial.getExplotacionesEconomicas()));
        }
        if (formatoInformeComercial.getServidumbrez() != null && !formatoInformeComercial.getServidumbrez().isEmpty()) {
            formatoInformeComercialDTO.setServidumbrez(servidumbreEnsamblador.escribirListaDTO(formatoInformeComercial.getServidumbrez()));
        }
        if (formatoInformeComercial.getViasAcceso() != null && !formatoInformeComercial.getViasAcceso().isEmpty()) {
            formatoInformeComercialDTO.setViasAcceso(viaAccesoEnsamblador.escribirListaDTO(formatoInformeComercial.getViasAcceso()));
        }
        if (formatoInformeComercial.getMetodosValuacion() != null && !formatoInformeComercial.getMetodosValuacion().isEmpty()) {
            formatoInformeComercialDTO.setMetodosValuacion(metodoValuacionEnsamblador.escribirListaDTO(formatoInformeComercial.getMetodosValuacion()));
        }
        Set<AreaDTO> areas = avaluoEnsamblador.escribirAreas(formatoInformeComercial.getAvaluo().getAreas());
        if (areas != null && !areas.isEmpty()) {
            formatoInformeComercialDTO.setAreasConstruccion(new HashSet<AreaConstruccionDTO>());
            for (AreaDTO areaDTO:areas) {
                if (areaDTO.getClass().equals(AreaConstruccionDTO.class)) {
                    formatoInformeComercialDTO.getAreasConstruccion().add((AreaConstruccionDTO) areaDTO);
                }
            }
        }
        return formatoInformeComercialDTO;
    }

    public void actualizar(Long id, FormatoInformeComercialDTO actualizado)
            throws FormatoInformeNotFoundException, InmuebleNotFoundException, ServidumbreNotFoundException, ViaAccesoNotFoundException, MetodoValuacionNotFoundException {
        FormatoInformeComercial formatoInformeComercial = formatoInformeComercialRepository.findOne(id);
        if (formatoInformeComercial == null) {
           throw new FormatoInformeNotFoundException();
        }
        formatoInformeComercial.setVereda(actualizado.getVereda());
        formatoInformeComercial.setNombrePredio(actualizado.getNombrePredio());
        formatoInformeComercial.setLocalizacion(actualizado.getLocalizacion());
        formatoInformeComercial.setDestinatarioDeLaValuacion(actualizado.getDestinatarioDeLaValuacion());
        formatoInformeComercial.setObservacionesTipoInmueble(actualizado.getObservacionesTipoInmueble());
        formatoInformeComercial.setUsoActualDelInmueble(actualizado.getUsoActualDelInmueble());
        formatoInformeComercial.setObservacionesUsoActualDelInmueble(actualizado.getObservacionesUsoActualDelInmueble());
        formatoInformeComercial.setUsoProyectadoDelInmueble(actualizado.getUsoProyectadoDelInmueble());
        formatoInformeComercial.setObservacionesUsoProyectadoDelInmueble(actualizado.getObservacionesUsoProyectadoDelInmueble());
        formatoInformeComercial.setPeriodoDeMercadeo(actualizado.getPeriodoDeMercadeo());
        formatoInformeComercial.setObservacionesPeriodoDeMercadeo(actualizado.getObservacionesPeriodoDeMercadeo());
        formatoInformeComercial.setDocumentosConsultados(actualizado.getDocumentosConsultados());
        formatoInformeComercial.setFechaAporteDeDocumentos(actualizado.getFechaAporteDeDocumentos());
        formatoInformeComercial.setOtrosDocumentos(actualizado.getOtrosDocumentos());
        formatoInformeComercial.setObservacionesDeTitulacion(actualizado.getObservacionesDeTitulacion());
        formatoInformeComercial.setDescripcionGeneralMunicipioOSector(actualizado.getDescripcionGeneralMunicipioOSector());
        formatoInformeComercial.setUbicacionDelSector(actualizado.getUbicacionDelSector());
        formatoInformeComercial.setLimiteNorte(actualizado.getLimiteNorte());
        formatoInformeComercial.setLimiteSur(actualizado.getLimiteSur());
        formatoInformeComercial.setLimiteOriente(actualizado.getLimiteOriente());
        formatoInformeComercial.setLimiteOccidente(actualizado.getLimiteOccidente());
        formatoInformeComercial.setBarriosCercanos(actualizado.getBarriosCercanos());
        formatoInformeComercial.setUsoPredominanteDelSector(actualizado.getUsoPredominanteDelSector());
        formatoInformeComercial.setComercializacion(actualizado.getComercializacion());
        formatoInformeComercial.setTiempoEsperadoDeComercializacion(actualizado.getTiempoEsperadoDeComercializacion());
        formatoInformeComercial.setEstrato(actualizado.getEstrato());
        formatoInformeComercial.setCaracteristicasSocioEconomicas(actualizado.getCaracteristicasSocioEconomicas());
        formatoInformeComercial.setOrdenPublico(actualizado.getOrdenPublico());
        formatoInformeComercial.setObservacionesViasDeAcceso(actualizado.getObservacionesViasDeAcceso());
        formatoInformeComercial.setEquipamientoDeRedVial(actualizado.getEquipamientoDeRedVial());
        formatoInformeComercial.setEstadoDeConservacion(actualizado.getEstadoDeConservacion());
        formatoInformeComercial.setUsoDelSuelo(actualizado.getUsoDelSuelo());
        formatoInformeComercial.setInfraestructuraUrbanistica(actualizado.getInfraestructuraUrbanistica());
        formatoInformeComercial.setInfraestructuraDotacional(actualizado.getInfraestructuraDotacional());
        formatoInformeComercial.setAmoblamientoUrbano(actualizado.getAmoblamientoUrbano());
        formatoInformeComercial.setAndenesYSardineles(actualizado.getAndenesYSardineles());
        formatoInformeComercial.setZonasVerdes(actualizado.getZonasVerdes());
        formatoInformeComercial.setTopografia(actualizado.getTopografia());
        formatoInformeComercial.setAlumbradoPublico(actualizado.getAlumbradoPublico());
        formatoInformeComercial.setAlcantarillado(actualizado.getAlcantarillado());
        formatoInformeComercial.setAcueducto(actualizado.getAcueducto());
        formatoInformeComercial.setEnergia(actualizado.getEnergia());
        formatoInformeComercial.setGas(actualizado.getGas());
        formatoInformeComercial.setTelefono(actualizado.getTelefono());
        formatoInformeComercial.setObservacionesServiciosPublicos(actualizado.getObservacionesServiciosPublicos());
        formatoInformeComercial.setTransportePublico(actualizado.getTransportePublico());
        formatoInformeComercial.setFrecuencia(actualizado.getFrecuencia());
        formatoInformeComercial.setCubrimiento(actualizado.getCubrimiento());
        if (actualizado.getPerspectivasDeValorizacion() != null && actualizado.getPerspectivasDeValorizacion() != MBR.NoSelectOption) {
            formatoInformeComercial.setPerspectivasDeValorizacion(actualizado
                    .getPerspectivasDeValorizacion().getKey());
        }
        formatoInformeComercial.setObservacionesPerspectivasDeValorizacion(actualizado.getObservacionesPerspectivasDeValorizacion());
        formatoInformeComercial.setNormatividad(actualizado.getNormatividad());
        formatoInformeComercial.setDescripcionDelInmueble(actualizado.getDescripcionDelInmueble());
        formatoInformeComercial.setLocalizacionEspecifacaDelInmueble(actualizado.getLocalizacionEspecifacaDelInmueble());
        formatoInformeComercial.setDistaciaPartiendoDelCascoUrbano(actualizado.getDistaciaPartiendoDelCascoUrbano());
        formatoInformeComercial.setLinderoNorte(actualizado.getLinderoNorte());
        formatoInformeComercial.setLinderoSur(actualizado.getLinderoSur());
        formatoInformeComercial.setLinderoOriente(actualizado.getLinderoOriente());
        formatoInformeComercial.setLinderoOccidente(actualizado.getLinderoOccidente());
        formatoInformeComercial.setFuenteLinderos(actualizado.getFuenteLinderos());
        formatoInformeComercial.setDescripcionViasDeAccesoInternas(actualizado.getDescripcionViasDeAccesoInternas());
        formatoInformeComercial.setFrenteSobreLaVia(actualizado.getFrenteSobreLaVia());
        formatoInformeComercial.setMetros(actualizado.getMetros());
        formatoInformeComercial.setCercasPerimedales(actualizado.getCercasPerimedales());
        formatoInformeComercial.setServidumbres(actualizado.getServidumbres());
        formatoInformeComercial.setPropiedadHorizontal(actualizado.getPropiedadHorizontal());
        formatoInformeComercial.setDescripcionDeLaPropiedadHorizontal(actualizado.getDescripcionDeLaPropiedadHorizontal());
        formatoInformeComercial.setVidaUtil(actualizado.getVidaUtil());
        formatoInformeComercial.setVidaDelInmueble(actualizado.getVidaDelInmueble());
        formatoInformeComercial.setVidaRemanente(actualizado.getVidaRemanente());
        formatoInformeComercial.setJustificacionVidaUtil(actualizado.getJustificacionVidaUtil());
        formatoInformeComercial.setEstructuraEdificio(actualizado.getEstructuraEdificio());
        formatoInformeComercial.setDescripcionEstructura(actualizado.getDescripcionEstructura());
        formatoInformeComercial.setPlacasDeEntrepiso(actualizado.getPlacasDeEntrepiso());
        formatoInformeComercial.setObservacionesPlacasEntrePiso(actualizado.getObservacionesPlacasEntrePiso());
        formatoInformeComercial.setFachada(actualizado.getFachada());
        formatoInformeComercial.setDescripcionFachada(actualizado.getDescripcionFachada());
        formatoInformeComercial.setCubierta(actualizado.getCubierta());
        formatoInformeComercial.setDescripcionCubierta(actualizado.getDescripcionCubierta());
        formatoInformeComercial.setEscaleras(actualizado.getEscaleras());
        formatoInformeComercial.setDescripcionEscaleras(actualizado.getDescripcionEscaleras());
        formatoInformeComercial.setEstructura(actualizado.getEstructura());
        formatoInformeComercial.setCategoriaAcabados(actualizado.getCategoriaAcabados());
        formatoInformeComercial.setDistribucion(actualizado.getDistribucion());
        formatoInformeComercial.setEstadoDeConservacionSectorRural(actualizado.getEstadoDeConservacionSectorRural());
        formatoInformeComercial.setEquipamientoComunal(actualizado.getEquipamientoComunal());
        formatoInformeComercial.setDescripcionDelSuelo(actualizado.getDescripcionDelSuelo());
        formatoInformeComercial.setRelieve(actualizado.getRelieve());
        formatoInformeComercial.setErosion(actualizado.getErosion());
        formatoInformeComercial.setTemperatura(actualizado.getTemperatura());
        formatoInformeComercial.setPisoTermico(actualizado.getPisoTermico());
        formatoInformeComercial.setAlturaMsnm(actualizado.getAlturaMsnm());
        formatoInformeComercial.setPrecipitacionAnualMm(actualizado.getPrecipitacionAnualMm());
        formatoInformeComercial.setFormaGeometrica(actualizado.getFormaGeometrica());
        formatoInformeComercial.setRegular(actualizado.getRegular());
        formatoInformeComercial.setIrregular(actualizado.getIrregular());
        formatoInformeComercial.setInundabilidad(actualizado.getInundabilidad());
        formatoInformeComercial.setDistribucionDeLluvias(actualizado.getDistribucionDeLluvias());
        formatoInformeComercial.setPedregosidad(actualizado.getPedregosidad());
        formatoInformeComercial.setFertilidadNatural(actualizado.getFertilidadNatural());
        formatoInformeComercial.setNivelFreatico(actualizado.getNivelFreatico());
        formatoInformeComercial.setClaseAgrologica(actualizado.getClaseAgrologica());
        formatoInformeComercial.setValorPotencial(actualizado.getValorPotencial());
        formatoInformeComercial.setCapaVegetal(actualizado.getCapaVegetal());
        formatoInformeComercial.setCondicionesAgronomicas(actualizado.getCondicionesAgronomicas());
        formatoInformeComercial.setCondicionesAgrologicas(actualizado.getCondicionesAgrologicas());
        formatoInformeComercial.setDescripcionCultivos(actualizado.getDescripcionCultivos());
        formatoInformeComercial.setRecursosHidricos(actualizado.getRecursosHidricos());
        formatoInformeComercial.setIrrigacion(actualizado.getIrrigacion());
        formatoInformeComercial.setRestriccionesFisicas(actualizado.getRestriccionesFisicas());
        formatoInformeComercial.setOtrosCutivos(actualizado.getOtrosCutivos());
        formatoInformeComercial.setBosques(actualizado.getBosques());
        formatoInformeComercial.setCultivosComerciales(actualizado.getCultivosComerciales());
        formatoInformeComercial.setDeProteccion(actualizado.getDeProteccion());
        formatoInformeComercial.setImpactoAmbiental(actualizado.getImpactoAmbiental());
        formatoInformeComercial.setFrente(actualizado.getFrente());
        formatoInformeComercial.setFondo(actualizado.getFondo());
        formatoInformeComercial.setRelacionFrenteFondo(actualizado.getRelacionFrenteFondo());
        formatoInformeComercial.setObservacionDistribucionDeAreasNoConstruidas(actualizado.getObservacionDistribucionDeAreasNoConstruidas());
        formatoInformeComercial.setConstruccion(actualizado.getConstruccion());
        formatoInformeComercial.setVidaUtilConstruccion(actualizado.getVidaUtilConstruccion());
        formatoInformeComercial.setVidaDelInmuebleConstruccion(actualizado.getVidaDelInmuebleConstruccion());
        formatoInformeComercial.setVidaRemanenteConstruccion(actualizado.getVidaRemanenteConstruccion());
        formatoInformeComercial.setObservacionesEdad(actualizado.getObservacionesEdad());
        formatoInformeComercial.setRemodelado(actualizado.getRemodelado());
        formatoInformeComercial.setAlcantarilladoConstruccion(actualizado.getAlcantarilladoConstruccion());
        formatoInformeComercial.setAcueductoConstruccion(actualizado.getAcueductoConstruccion());
        formatoInformeComercial.setEnergiaConstruccion(actualizado.getEnergiaConstruccion());
        formatoInformeComercial.setGasConstruccion(actualizado.getGasConstruccion());
        formatoInformeComercial.setTelefonoConstruccion(actualizado.getTelefonoConstruccion());
        formatoInformeComercial.setObservacionesServiciosPublicosConstruccion(actualizado.getObservacionesServiciosPublicosConstruccion());
        formatoInformeComercial.setFrenteConstruido(actualizado.getFrenteConstruido());
        formatoInformeComercial.setFondoConstruido(actualizado.getFondoConstruido());
        formatoInformeComercial.setRelacionFrenteFondoConstruido(actualizado.getRelacionFrenteFondoConstruido());
        formatoInformeComercial.setAreaPrivadaConstruido(actualizado.getAreaPrivadaConstruido());
        formatoInformeComercial.setCoeficienteDeCopropiedadConstruido(actualizado.getCoeficienteDeCopropiedadConstruido());
        formatoInformeComercial.setAreaTotalConstruida(actualizado.getAreaTotalConstruida());
        formatoInformeComercial.setFuenteConstruido(actualizado.getFuenteConstruido());
        formatoInformeComercial.setObservacionesDistribucionAreasConstruidas(actualizado.getObservacionesDistribucionAreasConstruidas());
        formatoInformeComercial.setProblemasDeEstabilidadDeSuelos(actualizado.getProblemasDeEstabilidadDeSuelos());
        formatoInformeComercial.setImpactoAmbientalYCondicionesDeSalubridad(actualizado.getImpactoAmbientalYCondicionesDeSalubridad());
        formatoInformeComercial.setServidumbresCesionesYAfectacionesViales(actualizado.getServidumbresCesionesYAfectacionesViales());
        formatoInformeComercial.setSeguridad(actualizado.getSeguridad());
        formatoInformeComercial.setProblematicasSocioEconomicas(actualizado.getProblematicasSocioEconomicas());
        formatoInformeComercial.setDescripcionDeLasHipotesisEspecialesInusualesOExtraordinarias(actualizado.getDescripcionDeLasHipotesisEspecialesInusualesOExtraordinarias());
        formatoInformeComercial.setConsideracionesGenerales(actualizado.getConsideracionesGenerales());
        formatoInformeComercial.setConsideracionesGeneralesDeLocalizacion(actualizado.getConsideracionesGeneralesDeLocalizacion());
        formatoInformeComercial.setConsideracionesGeneralesDelSector(actualizado.getConsideracionesGeneralesDelSector());
        formatoInformeComercial.setConsideracionesGeneralesDeLaCapacidadDeTerreno(actualizado.getConsideracionesGeneralesDeLaCapacidadDeTerreno());
        formatoInformeComercial.setConsideracionesGeneralesDeLaInfraestructuraVialYCapacidadDeAgua(actualizado.getConsideracionesGeneralesDeLaInfraestructuraVialYCapacidadDeAgua());
        formatoInformeComercial.setConsideracionesGeneralesDeLaConstruccion(actualizado.getConsideracionesGeneralesDeLaConstruccion());
        formatoInformeComercial.setCasosEspecialesDeAfectacion(actualizado.getCasosEspecialesDeAfectacion());
        formatoInformeComercial.setInvestigacionEconomica(actualizado.getInvestigacionEconomica());
        formatoInformeComercial.setComportamientoDeOfertaYDemanda(actualizado.getComportamientoDeOfertaYDemanda());
        formatoInformeComercial.setConceptoDeGarantia(actualizado.getConceptoDeGarantia());
        formatoInformeComercial.setObservacionesConceptoGarantia(actualizado.getObservacionesConceptoGarantia());
        formatoInformeComercial.setValorRazonableDelInmueble(actualizado.getValorComercialDelInmueble());
        formatoInformeComercial.setValorIntegralSobreElTerreno(actualizado.getValorIntegralSobreElTerreno());
        formatoInformeComercial.setValorIntegralSobreElConstruccion(actualizado.getValorIntegralSobreElConstruccion());
        formatoInformeComercial.setPorcentajeFinal(actualizado.getPorcentajeFinal());
        formatoInformeComercial.setAreaConstruida(actualizado.getAreaConstruida());
        formatoInformeComercial.setAreaInfraestructura(actualizado.getAreaInfraestructura());
        formatoInformeComercial.setAreaCultivosAvaluables(actualizado.getAreaCultivosAvaluables());
        if (actualizado.getInmuebles() != null && !actualizado.getInmuebles().isEmpty()) {
            agregarInmuebles(actualizado.getInmuebles(), formatoInformeComercial);
        }
        if (actualizado.getServidumbrez() != null && !actualizado.getServidumbrez().isEmpty()) {
            agregarServidumbrez(actualizado.getServidumbrez(), formatoInformeComercial);
        }
        if (actualizado.getViasAcceso() != null && !actualizado.getViasAcceso().isEmpty()) {
            agregarViasAcceso(actualizado.getViasAcceso(), formatoInformeComercial);
        }
        if (actualizado.getMetodosValuacion() != null && !actualizado.getMetodosValuacion().isEmpty()) {
            agregarMetodosValuacion(actualizado.getMetodosValuacion(), formatoInformeComercial);
        }
    }

}
