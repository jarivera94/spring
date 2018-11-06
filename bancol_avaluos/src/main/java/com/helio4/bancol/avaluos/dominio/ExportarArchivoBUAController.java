package com.helio4.bancol.avaluos.dominio;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.AvaluoHipotecarioDTO;
import com.helio4.bancol.avaluos.dto.EntidadDTO;
import com.helio4.bancol.avaluos.servicio.datos.AvaluoHipotecarioService;
import com.helio4.bancol.avaluos.servicio.BUAService;
import com.helio4.bancol.avaluos.servicio.datos.EntidadService;

@Component
public class ExportarArchivoBUAController {

	private String nombreArchivo;
	private String[] nombresArchivos;

	private final BUAService buaService;
	
	private final AvaluoHipotecarioService avaluoHipotecarioService;
	
	private final EntidadService entidadService;

	@Autowired
	public ExportarArchivoBUAController(@Qualifier("repositoryAvaluoHipotecarioService") AvaluoHipotecarioService avaluoHipotecarioService, @Qualifier("csvBUAService") BUAService buaService, @Qualifier("repositoryEntidadService") EntidadService entidadService) {
		this.avaluoHipotecarioService = avaluoHipotecarioService;
		this.buaService = buaService;
		this.entidadService = entidadService;
	}

	public String exportarArchivo(EntidadDTO entidadSeleccionada,Date fechaInicial, Date fechaFinal) {
		List<AvaluoHipotecarioDTO> listaResultado = avaluoHipotecarioService.encontrarPorFechaTerminacionHipotecarios(entidadSeleccionada,fechaInicial, fechaFinal);
		if (listaResultado!=null && !listaResultado.isEmpty() &&listaResultado.size() > 0) {
			nombreArchivo = buaService.exportartCsvBUA(listaResultado);
		}
		else if( listaResultado!=null && listaResultado.isEmpty() ){
			nombreArchivo = "";
		}
		else{
			nombreArchivo = null;
		}
		return nombreArchivo;
	}
	
	 public List<EntidadDTO> encontrarEntidadesConCodigoBUA() {
	        return entidadService.encontrarEntidadesConCodigoBUA();
	 }

	public String getNombreArchivo() {
		return nombreArchivo;
	}
	
	public String[] getNombresArchivos() {
		return nombresArchivos;
	}

}