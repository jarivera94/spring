package com.helio4.bancol.avaluos.dominio;

import com.helio4.bancol.avaluos.dominio.gmap.GLatLng;
import com.helio4.bancol.avaluos.dominio.gmap.Geocoder;
import com.helio4.bancol.avaluos.dto.*;
import com.helio4.bancol.avaluos.dto.AreaDTO.UnidadDeMedida;
import com.helio4.bancol.avaluos.servicio.datos.*;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.ClienteNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.EntidadNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.TiempoComercializacionNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class AvaluoController {

	private static Logger log = LoggerFactory.getLogger( AvaluoController.class );

	private final ClienteService clienteService;

	private final EntidadService entidadService;

	private final SucursalService sucursalService;

	private final AsesorService asesorService;

	private final AvaluoService avaluoService;

	private final FormatoInformeService formatoInformeService;

	private final AreaService areaService;
	
	private final MatriculaService matriculaService;
	
	private final Geocoder geocoder;

	private final ParametrosController parametrosController;

	private final TiempoComercializacionService tiempoComercializacionService;

	private String rutaReportes;

	@Autowired
	public AvaluoController(@Qualifier("repositoryClienteService") ClienteService clienteService, @Qualifier("repositoryEntidadService") EntidadService entidadService, @Qualifier("repositorySucursalService") SucursalService sucursalService, @Qualifier("repositoryAsesorService") AsesorService asesorService, ParametrosController parametrosController, @Qualifier("repositoryAvaluoService") AvaluoService avaluoService, @Qualifier("repositoryFormatoInformeService") FormatoInformeService formatoInformeService, @Qualifier("repositoryAreaService") AreaService areaService, @Qualifier("repositoryMatriculaService") MatriculaService matriculaService, Geocoder geocoder, @Qualifier("tiempoComercializacionServiceImpl") TiempoComercializacionService tiempoComercializacionService) {
		this.clienteService = clienteService;
		this.entidadService = entidadService;
		this.sucursalService = sucursalService;
		this.asesorService = asesorService;
		this.parametrosController = parametrosController;
		this.avaluoService = avaluoService;
		this.formatoInformeService = formatoInformeService;
		this.areaService = areaService;
		this.matriculaService = matriculaService;
		this.geocoder = geocoder;
		this.tiempoComercializacionService = tiempoComercializacionService;
	}

	@PostConstruct
	public void inicializar() {
		rutaReportes = parametrosController.obtenerValor("rutaReportes");
	}

	public AvaluoDTO encontrarPorId(Long id){
		return avaluoService.encontrarPorId(id);
	}

    public FormatoInformeDTO cargarFormatoInforme(Long id) {
        return avaluoService.cargarFormatoInforme(id);
    }

    public AvaluoDTO cargarInformacionPersonaRecibeAvaluador(AvaluoDTO avaluoDTO) {
        return avaluoService.cargarInformacionPersonaRecibeAvaluador(avaluoDTO);
    }

	public boolean borrarPDF(Long avaluoId) {
		//windows environment
		String url = rutaReportes + "\\imagenes\\" + avaluoId + ".pdf";
//          unix environments
//			String url = path + "/" + avaluoHipotecarioDTO.getId() + ".pdf";
		File pdfPrevio = new File(url);
		if( pdfPrevio.exists() && !pdfPrevio.isDirectory()) {
			return pdfPrevio.delete();
		}
		return false;
	}

    public String cargarPrefijoEntidad(Long entidadId) {
        return entidadService.cargarPrefijo(entidadId);
    }

	public boolean avaluoEsPH (Long id){
		return avaluoService.avaluoEsPH(id);
	}

	public List<EntidadDTO> entidades() {
		return entidadService.encontrarEntidades();
	}

	public List<String> sucursales() {
		List<String> sucursales = new ArrayList<String>();
		for (SucursalDTO sucursal:sucursalService.encontrarTodos()) {
			sucursales.add(sucursal.getNombre());
		}
		return sucursales;
	}  

	public List<SucursalDTO> sucursalesEnEntidad(String texto, Long idEntidad) {
		List<SucursalDTO> sucursales = new ArrayList<SucursalDTO>();
		for (SucursalDTO sucursal:sucursalService.encontrarPorCodigo(texto, idEntidad)) {
			sucursales.add(sucursal);
		}
		for (SucursalDTO sucursal:sucursalService.encontrarPorNombre(texto, idEntidad)) {
			sucursales.add(sucursal);
		}
		return sucursales;
	}

	public AsesorDTO encontrarAsesor(String nombre, Long idEntidad, String codigoSucursal, String celular, String correo, String telefono){
		return asesorService.encontrarAsesor(nombre, idEntidad, codigoSucursal, celular, correo, telefono);
	}
	/**
	 *  Buscan un asesor con base en su correo electronico.
	 * */
	public AsesorDTO encontrarAsesor(String correo){
		return this.asesorService.encontrarPorCorreo(correo);
	}

	public List<AsesorDTO> asesoresEnEntidad(String texto, Long idEntidad) {
		return asesorService.encontrarPorNombre(texto, idEntidad);
	}

	public AsesorDTO guardarAsesor(AsesorDTO asesorDTO){
		try {
			asesorDTO = asesorService.guardar(asesorDTO);
		} catch (EntidadNotFoundException e) {
			e.printStackTrace();
		}
		return asesorDTO;
	}

	public void actualizarAsesor(AsesorDTO asesorDTO){
		try {
			asesorService.actualizar(asesorDTO);
		} catch (EntidadNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void actualizarCambioGarantia(AvaluoDTO avaluoCambioGarantia) {
	    avaluoService.actualizarCambioGarantia(avaluoCambioGarantia);
	}

	public String onDireccionChanged(String tipoVia, String numeroVia,
			String complementoVia, String complementoViaGeneradora,
			String numeroViaGeneradora, String placa,
			String complementoPlaca) {
		String direccion = "";
		if (numeroVia != null && tipoVia != null) {
			direccion = tipoVia.concat(" "+numeroVia)
					.concat(complementoVia == "" ? "" : " " + complementoVia)
					.concat(numeroViaGeneradora == null ? "" : " # " + numeroViaGeneradora)
					.concat(complementoViaGeneradora == "" ? "" : " " + complementoViaGeneradora)
					.concat(placa == null ? "" : " - " + placa)
					.concat(complementoPlaca == null ? "" : " " + complementoPlaca);
		}
		return direccion;
	}

	public GLatLng calcularCoordenadasAvaluo(AvaluoDTO avaluo, String departamento, boolean direccionInforme) {
		String direccion = direccionInforme ? construirDireccionInforme(avaluo, departamento) : construirDireccion(avaluo, departamento);
        if (direccion == null) {
            return null;
        }
		GLatLng gLatLng = null;
		try {
			gLatLng = geocoder.geocode(direccion);
		} catch (IOException e) {
			log.debug("IOException: La geocodificación falló debido a un error de comunicación.", e);
		} catch (Exception e) {
			log.debug("Exception: La geocodificación falló ", e);
		}
		return gLatLng;
	}

	public String construirDireccion(AvaluoDTO avaluo, String departamento) {
		String direccion = "";
		if (avaluo.getTipoVia() != null) {
			direccion = avaluo.getTipoVia().equals("CL") ? "Calle" : avaluo
					.getTipoVia().equals("KR") ? "Carrera" : avaluo
					.getTipoVia().equals("DG") ? "Diagonal" : avaluo
					.getTipoVia().equals("TV") ? "Transversal" : avaluo
					.getTipoVia().equals("AV") ? "Avenida" : avaluo
					.getTipoVia().equals("AC") ? "Calle" : avaluo.getTipoVia()
					.equals("AK") ? "Carrera" : avaluo.getTipoVia()
					.equals("AU") ? "Autopista" : avaluo.getTipoVia().equals(
					"CT") ? "Carretera"
					: avaluo.getTipoVia().equals("CQ") ? "Circular" : avaluo
							.getTipoVia().equals("CN") ? "Camino" : "";
		}
		if (avaluo.getNumeroVia() != null) {
			direccion = direccion.concat(" ").concat(avaluo.getNumeroVia());
		}
		if (avaluo.getComplementoVia() != null
				&& !avaluo.getComplementoVia().isEmpty()) {
			direccion = direccion.concat(
					avaluo.getComplementoVia().toLowerCase()).concat(" #");
		}else if (!direccion.isEmpty()){
			direccion = direccion.concat(" #");
		}
		if (avaluo.getNumeroViaGeneradora() != null) {
			direccion = direccion.concat(avaluo.getNumeroViaGeneradora());
		}
		if (avaluo.getComplementoViaGeneradora() != null
				&& !avaluo.getComplementoViaGeneradora().isEmpty()) {
			direccion = direccion.concat(
					avaluo.getComplementoViaGeneradora().toLowerCase())
					.concat("-");
		}else if (!direccion.isEmpty()){
			direccion = direccion.concat("-");
		}
		if (avaluo.getPlaca() != null && !avaluo.getPlaca().isEmpty()) {
			try {
				int placa = Integer.parseInt(avaluo.getPlaca());
				if (placa >= 2 && placa <= 100) {
					direccion = direccion.concat(avaluo.getPlaca()).concat(",");
				}else{
					direccion = direccion.concat(placa < 2 ? "2":"100").concat(",");
				}
			} catch (NumberFormatException e) {
				direccion = direccion.concat("2").concat(",");
			}
		}else if (!direccion.isEmpty()){
			direccion = direccion.concat(", ");
		}
		if (avaluo.getDivipola() == null
				|| !departamento.equals(avaluo.getDivipola().getDepartamento())) {
			direccion = departamento;
		}else{
			direccion = direccion.concat(avaluo.getDivipola().getCentroPoblado()).concat(" - ").concat(avaluo.getDivipola().getDepartamento());
		}
		return direccion;
	}

	public String construirDireccionInforme(AvaluoDTO avaluo, String departamento) {
		String direccion = "";
		if (avaluo.getTipoViaInforme() != null) {
			direccion = avaluo.getTipoViaInforme().equals("CL") ? "Calle" : avaluo
					.getTipoViaInforme().equals("KR") ? "Carrera" : avaluo
					.getTipoViaInforme().equals("DG") ? "Diagonal" : avaluo
					.getTipoViaInforme().equals("TV") ? "Transversal" : avaluo
					.getTipoViaInforme().equals("AV") ? "Avenida" : avaluo
					.getTipoViaInforme().equals("AC") ? "Calle" : avaluo.getTipoViaInforme()
					.equals("AK") ? "Carrera" : avaluo.getTipoViaInforme()
					.equals("AU") ? "Autopista" : avaluo.getTipoViaInforme().equals(
					"CT") ? "Carretera"
					: avaluo.getTipoViaInforme().equals("CQ") ? "Circular" : avaluo
							.getTipoViaInforme().equals("CN") ? "Camino" : "";
		}
		if (avaluo.getNumeroViaInforme() != null) {
			direccion = direccion.concat(" ").concat(avaluo.getNumeroViaInforme());
		}
		if (avaluo.getComplementoViaInforme() != null
				&& !avaluo.getComplementoViaInforme().isEmpty()) {
			direccion = direccion.concat(
					avaluo.getComplementoViaInforme().toLowerCase()).concat(" #");
		}else if (!direccion.isEmpty()){
			direccion = direccion.concat(" #");
		}
		if (avaluo.getNumeroViaGeneradoraInforme() != null) {
			direccion = direccion.concat(avaluo.getNumeroViaGeneradoraInforme());
		}
		if (avaluo.getComplementoViaGeneradoraInforme() != null
				&& !avaluo.getComplementoViaGeneradoraInforme().isEmpty()) {
			direccion = direccion.concat(
					avaluo.getComplementoViaGeneradoraInforme().toLowerCase())
					.concat("-");
		}else if (!direccion.isEmpty()){
			direccion = direccion.concat("-");
		}
		if (avaluo.getPlacaInforme() != null && !avaluo.getPlacaInforme().isEmpty()) {
			try {
				int placa = Integer.parseInt(avaluo.getPlacaInforme());
				if (placa >= 2 && placa <= 100) {
					direccion = direccion.concat(avaluo.getPlacaInforme()).concat(",");
				}else{
					direccion = direccion.concat(placa < 2 ? "2":"100").concat(",");
				}
			} catch (NumberFormatException e) {
				direccion = direccion.concat("2").concat(",");
			}
		}else if (!direccion.isEmpty()){
			direccion = direccion.concat(", ");
		}
		if (avaluo.getDivipolaInforme() == null
				|| !departamento.equals(avaluo.getDivipolaInforme().getDepartamento())) {
			direccion = departamento;
		}else{
			direccion = direccion.concat(avaluo.getDivipolaInforme().getCentroPoblado()).concat(" - ").concat(avaluo.getDivipolaInforme().getDepartamento());
		}
		return direccion;
	}

	public boolean verificarDuplicadoCodigoExterno(String codigoExterno, Long idEntidad) {
		return avaluoService.encontrarPorCodigoExterno(codigoExterno, idEntidad);
	}
	
	public AvaluoDTO encontrarAvaluoPorCodigoExterno(String codigoExterno, Long idEntidad) {
		return avaluoService.encontrarAvaluoPorCodigoExterno(codigoExterno, idEntidad);
	}
	
	public AvaluoDTO encontrarAvaluoPorCodigoExternoAndNumeroDocumentoCliente(String codigoExterno, Long idEntidad, Long numeroDocumentoCliente) {
		return avaluoService.encontrarAvaluoPorCodigoExternoAndNumeroDocumentoCliente(codigoExterno, idEntidad, numeroDocumentoCliente);
	}
	public AvaluoDTO encontrarPorIdTinsa(Long id) {
		return avaluoService.encontrarPorIdTinsa(id);
	}
	
	public boolean verificarDuplicadoIdSisgen(Long idSisgen) {
		return avaluoService.encontrarPorIdSisgen(idSisgen);
	}

    public List<AvaluoDTO> comprobarCambioGarantia(String codigoExterno, Long entidadId) {
        return avaluoService.comprobarCambioGarantia(codigoExterno, entidadId);
    }

	public List<AvaluoDTO> verificarDuplicadosDireccion(String direccion) {
		return avaluoService.encontrarAvaluosPorDireccion(direccion);
	}

	public ClienteDTO buscarCliente(Integer tipoDocumentoIdentificacion, Long numeroDocumentoCliente) {
		return clienteService.encontrarPorNumeroDocumento(tipoDocumentoIdentificacion, numeroDocumentoCliente);
	}

	public AvaluoDTO actualizar(AvaluoDTO avaluoDTO) {
		try {
			clienteService.actualizar(avaluoDTO.getCliente());
			return avaluoService.actualizar(avaluoDTO);
		} catch (AvaluoNotFoundException e) {
			return null;
		} catch (ClienteNotFoundException e) {
			return null;
		} catch (EntidadNotFoundException e) {
			return null;
		}
	}

	public AvaluoDTO crearSolicitud(AvaluoDTO avaluoDTO, UsuarioDTO usuarioDTO) {
		Integer tipoDocumentoIdentificacion = avaluoDTO.getCliente().getTipoDocumentoIdentificacion();
		Long numeroDocumento = avaluoDTO.getCliente().getNumeroDocumento();
		if (numeroDocumento != null) {
			if (clienteService.encontrarPorNumeroDocumento(tipoDocumentoIdentificacion, numeroDocumento) == null) {
				try {
					clienteService.crear(avaluoDTO.getCliente());
				} catch (EntidadNotFoundException e) {
					e.printStackTrace();
				}
			}else{
			    try {
                    clienteService.actualizar(avaluoDTO.getCliente());
                } catch (ClienteNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (EntidadNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
			}
		}
		AvaluoDTO avaluo = avaluoService.crear(avaluoDTO, usuarioDTO);
		if (avaluo == null) {
			return null;
		}
		return avaluo;
	}

	public boolean crearFormatoInforme(AvaluoDTO avaluo) {
	    return avaluoService.crearFormatoInforme(avaluo);
	}

	public Long encontrarFormatoInformeId(Long avaluoId) {
		return formatoInformeService.encontrarIdFormatoPorIdAvaluo(avaluoId);
	}

	public List<AvaluoDTO> obtenerAvaluosAnteriores(Integer tipoDocumentoIdentificacion, Long numeroDocumento, Long id) {
		return avaluoService.encontrarAvaluosAnteriores(tipoDocumentoIdentificacion, numeroDocumento, id);
	}

	public BigDecimal calcularValorTotalArea(AreaDTO area) {
		if(area.getValorUnitario() != null && area.getArea() != null){
			return area.getUnidadDeMedida().equals(UnidadDeMedida.Global) ?
					area.getValorUnitario()
					:area.getValorUnitario().multiply(area.getArea(), new MathContext(0, RoundingMode.UP));
		}else{
			return BigDecimal.ZERO;
		}
	}

	/**
	 * Calculates the percentage of the area
	 * @param area the area to calculate the percentage
	 * @param valorTotal total appraisal value
	 * @return the percentage of the area in the total appraisal value
	 */
	public BigDecimal calcularPorcentajeArea(AreaDTO area, BigDecimal valorTotal) {
		BigDecimal porcentaje = BigDecimal.ZERO;
		if (valorTotal.compareTo(BigDecimal.ZERO) > 0 && area.getValorTotal() != null) {
			porcentaje = area.getValorTotal().divide(valorTotal, 15, RoundingMode.HALF_UP);
		}
		return porcentaje;
	}

    /**
     * Sobreesribe las areas con las que se pasan como parametro
     * @param avaluodId
     * @param areas
     */
    void actualizarAreas(Long avaluodId, Set<AreaDTO> areas) {
        // TODO: Delete all areas in database for this avaluoId
        areaService.eliminarPor(avaluodId);
		for (AreaDTO area:areas) {
            areaService.crear(area);
		}
	}
    
	public void actualizarMatriculas(Long avaluodId, List<MatriculaDTO> matriculas) {

		for (MatriculaDTO matriculaDTO : matriculas) {
			if (matriculaDTO.getCodigo() != null && !matriculaDTO.getCodigo().equals("")) {
				matriculaService.crear(matriculaDTO);
			}
		}
	}
	
	public boolean solicitarCorreciones(AvaluoDTO avaluoDTO, UsuarioDTO usuario, String correcciones) {
		return avaluoService.solicitarCorreciones(avaluoDTO, correcciones, usuario);
	}

	public SucursalDTO encontrarSucursal(Long entidadId, String codigo){
		return sucursalService.encontrarSucursal(entidadId, codigo);
	}


	public List<AvaluoConsultaDTO> consultarAvaluosParaEstudios(AvaluoConsultaDTO avaluoConsultaDTO){
		List<AvaluoConsultaDTO> avaluosEncontrados = new ArrayList<AvaluoConsultaDTO>();
		avaluosEncontrados = avaluoService.encontrarAvaluosParaEstudios(avaluoConsultaDTO);
		return avaluosEncontrados;
	}

    public boolean esCobradoPorBancol(AvaluoDTO avaluoDTO) {
        return avaluoService.esCobradoPorBancol(avaluoDTO);
    }

    public boolean tieneMetodologiaFito(Long id) {
        return avaluoService.tieneMetodologiaFito(id);
    }

    public boolean tieneMetodologiaComparacion(Long id) {
        return avaluoService.tieneMetodologiaComparacion(id);
    }

    public List<Object> getHonorariosPerito(Long id){
    	return this.avaluoService.obtenerHonorariosPerito(id);
    }

	public ClienteDTO buscarCliente(Long id) {
		return this.avaluoService.buscarCliente(id);
	}

	public UsuarioDTO buscarAsesor(Long id) {
		return this.avaluoService.buscarAsesor(id);
	}

	public UsuarioDTO buscarPerito(Long id) {
		return this.avaluoService.buscarPerito(id);
	}

	public UsuarioDTO buscarPersonaRecibe(Long id) {
		return this.avaluoService.buscarPersonaRecibe(id);
	}

	public UsuarioDTO buscarAbogado(Long id) {
		return this.avaluoService.buscarAbogado(id);
	}

	/**
	 * Invokes service to query for the {@link Double value of tiempoComercializacion}
	 * @param divipolaId divipolaId of the location of the property
	 * @param tipoInmuebleId of the property
	 * @param estrato strata of the property
	 * @return the matching {@link Double tiempoComercializacion}
	 */
	public Double encontrarTiempoComercializacion(long divipolaId, long tipoInmuebleId, int estrato) throws TiempoComercializacionNotFoundException {
    	return this.tiempoComercializacionService.encontrarPor(divipolaId, tipoInmuebleId, estrato);
	}

}
