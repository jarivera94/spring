package com.helio4.bancol.avaluos.dominio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.controlador.TarifaUVRBean;
import com.helio4.bancol.avaluos.dto.UvrDTO;
import com.helio4.bancol.avaluos.servicio.datos.UvrService;
import com.helio4.bancol.avaluos.servicio.util.DateUtils;
import com.opencsv.CSVReader;

/**
 * 
 * */
@Component
public class TarifaUVRController {

	private static Logger log = LoggerFactory.getLogger(TarifaUVRBean.class);

	@Autowired
	@Qualifier("repositoryUvrService")
	private UvrService uvrService;

	/**
	 * Función que se encarga de procesar el archivo csv.
	 * 
	 * @param InputStream
	 *            del archivo a procesar.
	 * @return List<UvrDTO> con los uvr cargados en una lista.
	 * 
	 * */
	public List<UvrDTO> procesarCSV(InputStream in) {
		log.debug("Procesando uvrs desde el CSV");
		List<UvrDTO> uvrs = new ArrayList<UvrDTO>();
		try {
			CSVReader reader = null;
			reader = new CSVReader(new InputStreamReader(in), '\t','\"', 1);
			String[] nextLine;
			if (reader != null) {
				while ((nextLine = reader.readNext()) != null) {
					if (nextLine.length > 1) {
						String fecha = nextLine[0];
						String valor = nextLine[1];
						byte[] bytesFecha = fecha.getBytes(StandardCharsets.UTF_16BE);
						byte[] bytesValor = valor.getBytes(StandardCharsets.UTF_16BE);
						fecha = this.removerCaracteresSpeciales(bytesFecha);
						valor = this.removerCaracteresSpeciales(bytesValor);
						UvrDTO uvr = new UvrDTO();
						uvr.setValor(this.convertirValorUvr(valor));
						Date date = DateUtils.getFecha(fecha,DateUtils.DATE_FORMAT_YYYY_MM_DD);
						uvr.setFechaUvr(date);
						uvrs.add(uvr);
					}
				}
			}
			reader.close();
			return uvrs;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * El archivo de carga .csv  viene con caracteres basura ocultos
	 * por lo cual se debe procesar.
	 * */
	private String removerCaracteresSpeciales(byte[] value) {
	
		ArrayList<Byte> bytes = new ArrayList<Byte>();
		for ( byte val : value) {
			if ( val!=0) {
				bytes.add(val);
			}
		}
		byte[] data = new byte[bytes.size()];
		for (int i = 0; i < data.length; i++) {
		    data[i] = (byte) bytes.get(i);
		}
		return  new String(data);
	}
	/**
	 * Convierte una el valor de una uvr en formato ###,### donde la coma separa
	 * el valor entero y la parte fraccional de la uvr.
	 * 
	 * @param uvr
	 *            a convertir
	 * @return valor Bigdecimal de la uvr.
	 * */
	public BigDecimal convertirValorUvr(String str) {
		//Locale in_ID = new Locale("es", "CO");
		Locale in_ID = Locale.getDefault();
		DecimalFormat nf = (DecimalFormat) NumberFormat.getInstance(in_ID);
		nf.setParseBigDecimal(true);
		BigDecimal bd = (BigDecimal) nf.parse(str, new ParsePosition(0));
		return bd;
	}
	/**
	 * Guarda en la base de datos una lista de uvrs
	 * 
	 * @param List
	 *            <UvrDTO> lista de uvrs a guardar.
	 * @return Variable booleana que identifica si el proceso se llevo a cabo
	 *         satisfactoriamente.
	 * */
	public boolean guardarUVRs(List<UvrDTO> uvrs) {
		List<UvrDTO> uvrsProcesadas = new ArrayList<UvrDTO>();
		/*
		 * verifico si existe una uvr cargada anteriormente con la misma fecha.
		 * Si es así, entonces actualizo el valor.
		 */
		for (UvrDTO uvrDTO : uvrs) {
			// uvr resultado de la búsqueda
			// uvrDTO uvr que viene del archivo csv procesado.
			UvrDTO uvr = this.uvrService.encontrarPor(uvrDTO.getFechaUvr());
			// ya existe una uvr con la misma fecha.
			if (uvr != null &&  uvr.getId() != null) {
				log.debug(" Actualizando UVR ");
				uvrDTO.setId(uvr.getId());
				uvrsProcesadas.add(this.uvrService.actualizar(uvrDTO));
			} else {
				log.debug(" Creando UVR ");
				uvrsProcesadas.add(this.uvrService.crear(uvrDTO));
			}
		}
		return (uvrsProcesadas != null && uvrsProcesadas.size() > 0);
	}

	/**
	 * Lista todas las uvrs en el sistema.
	 * 
	 * @return Lista de las UVR.
	 * */
	public List<UvrDTO> getUvrs() {
		return this.uvrService.encontrarTodos();
	}
	
	public UvrDTO encontrarPorFecha(Date fecha){
		return uvrService.encontrarPor(fecha);
	}
}
