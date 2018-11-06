package com.helio4.bancol.avaluos.controlador;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.helio4.bancol.avaluos.dominio.EntidadesController;
import com.helio4.bancol.avaluos.dominio.TarifasController;
import com.helio4.bancol.avaluos.dominio.TiposAvaluoController;
import com.helio4.bancol.avaluos.dto.EntidadDTO;
import com.helio4.bancol.avaluos.dto.TarifaDTO;
import com.helio4.bancol.avaluos.dto.TipoAvaluoDTO;
import com.helio4.bancol.avaluos.servicio.excepciones.TarifaNotFoundException;

@Controller
@Scope("view")
@Qualifier("tarifasBean")
public class TarifasBean implements Serializable {

	private static Logger log = LoggerFactory.getLogger(TarifasBean.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 4938541946386462737L;
	
	/**
	 * Listas de Objetos
	 */
	private List<TarifaDTO> listTarifas;
	private List<EntidadDTO> listEntidades;
	private Long idEntidadSeleccionada;
	
	@Autowired
	private TiposAvaluoController tiposAvaluoController;
	@Autowired
	private EntidadesController entidadesController;
	@Autowired
	private TarifasController tarifasController;
	
	/**
	 * Cargue Inicial.
	 * @throws Exception
	 */
	@PostConstruct
	public void init() {
            try {
            this.listarEntidades();	
            } catch (Exception e) {
                log.error(e.getMessage());
            }
		
	}
	
	/**
	 * ------------------------ Metodos del SucursalesBean. -----------------------------------------------
	 */
	
	/**
	 * Mensaje que se muestra en el Grow
	 * @param mensaje a Mostrar
	 * @param tarifaDTO Objeto
	 */
	private void mensajeGrow(String mensaje, TarifaDTO tarifaDTO) {
		if (tarifaDTO == null) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, "");
			FacesContext.getCurrentInstance().addMessage("msgs", msg);
		} else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, mensaje, tarifaDTO.getNombreTipoAvaluo());
			FacesContext.getCurrentInstance().addMessage("msgs", msg);
		}
	}
	
	/**
	 * Metodo para Guardar Tarifa
	 * Si para la Entidad seleccionada no existen registros, los crea
	 * Si para la Entidad seleccionada ya existen registros, los actualiza
	 * @throws TarifaNotFoundException
	 */
	public void guardarTarifa() throws TarifaNotFoundException {
		//List<TarifaDTO> tarifas = tarifasController.encontrarPorIdEntidad(this.idEntidadSeleccionada);
		if ( this.idEntidadSeleccionada != null) 
		{
			if (this.listTarifas.isEmpty() && (this.idEntidadSeleccionada > 0)) {
				// No existen registros de Tarifas para la Entidad seleccionada
				if (validarValorMinMax()) {
					if( tarifasController.crearTarifas(this.listTarifas, this.idEntidadSeleccionada) ) {
							mensajeGrow("Tarifas Guardadas Satisfactoriamente Para La Entidad", null);
					}else{
							mensajeGrow("Tarifas No Fueron Guardadas", null);
					}
				}
			} else {
				// Si Existen registros de Tarifas para la Entidad seleccionada
				if (validarValorMinMax()) {
					if (tarifasController.actualizarTarifas( this.listTarifas )) {
						mensajeGrow("Tarifas Actualizadas Satisfactoriamente Para La Entidad", null);
					} else {
						mensajeGrow("Tarifas No Fueron Actualizadas", null);
					}
				}
			}
		}
	}
	
	/**
	 * Listado de las Tarifas por Entidades
	 */
	public void listarTarifas() {
		this.listTarifas =  new ArrayList<TarifaDTO>();
		List<TipoAvaluoDTO> listTipoAvaluos = new ArrayList<TipoAvaluoDTO>();
		List<TarifaDTO> listaTarifas = new ArrayList<TarifaDTO>();
		listTipoAvaluos = tiposAvaluoController.tiposAvaluo();
		if (this.idEntidadSeleccionada != null) {
			this.listTarifas.addAll(tarifasController.encontrarPorIdEntidad(getIdEntidadSeleccionada()));
			if (listTarifas.isEmpty() && ( this.idEntidadSeleccionada > 0)) {
				mensajeGrow("La Entidad no tiene Asignada Tarifas", null);
				for (TipoAvaluoDTO tipoAvaluoDTO : listTipoAvaluos) {
					TarifaDTO tarifaDTO = new TarifaDTO();
					tarifaDTO.setTipoAvaluoId(tipoAvaluoDTO.getId());
					tarifaDTO.setNombreTipoAvaluo(tipoAvaluoDTO.getNombre());
					tarifaDTO.setTarifa(null);
					tarifaDTO.setTarifaConComa("");
					tarifaDTO.setValorMinimo(null);
					tarifaDTO.setValorMaximo(null);
					tarifaDTO.setPorcentajePerito(null);
					listaTarifas.add(tarifaDTO);
				}
				listTarifas.addAll(listaTarifas);			
			} else {
				if(listTipoAvaluos.size() != listTarifas.size()){
					String tiposAvaluoExiste = "";
					for (TarifaDTO tarifaDTO : listTarifas) {
						tarifaDTO.setTarifaConComa(conversionTarifaAString(tarifaDTO));
						tiposAvaluoExiste += tarifaDTO.getTipoAvaluoId() + ",";
					}
					for (TipoAvaluoDTO tipoAvaluoDTO : listTipoAvaluos) {
						if(listTarifas.size() <= listTipoAvaluos.size()){
							if(!Arrays.asList(tiposAvaluoExiste.split(",")).contains(tipoAvaluoDTO.getId() + "")){
								TarifaDTO tarifaDTO = new TarifaDTO();
								tarifaDTO.setTipoAvaluoId(tipoAvaluoDTO.getId());
								tarifaDTO.setNombreTipoAvaluo(tipoAvaluoDTO.getNombre());
								tarifaDTO.setTarifa(null);
								tarifaDTO.setTarifaConComa("");
								tarifaDTO.setValorMinimo(null);
								tarifaDTO.setValorMaximo(null);
								tarifaDTO.setPorcentajePerito(null);
								tarifaDTO.setEntidadId( this.idEntidadSeleccionada ); 
								listTarifas.add(tarifaDTO);
							}
						}
					}
				}
				
			}
		}
	}
	
	/**
	 * listado de la Entidades.
	 */
	private void listarEntidades() {
		this.listEntidades = new ArrayList<EntidadDTO>();
		this.listEntidades.addAll(this.entidadesController.encontrarTodos());
	}
	
	/**
	 * Validacion de Valores Minimos y Maximos
	 * @return Boolean Confirmacion de la validacion
	 */
	public Boolean validarValorMinMax() {
		Boolean validarOk = true;
		for (TarifaDTO tarifaDTO : getListTarifas()) {
			//tarifaDTO.setTarifa(conversionTarifaABigdecimal(tarifaDTO));			
			if (tarifaDTO.getValorMinimo().compareTo(tarifaDTO.getValorMaximo()) == 1) { 
				mensajeGrow("El Valor Minimo es Mayor al Valor Maximo en el Tipo Avaluo: ", tarifaDTO);
				validarOk = false;
				break;
			} else {
				validarOk = true;
			}
		}
		return validarOk;
	}
	
	/**
	 * Valida los campos si se encuentran Nulos y asigna el valor Tarifa en su formato.
	 * @return Boolean Confirmacion de la validacion
	 */
	public Boolean validarCampos() {
		Boolean validarOk = true, valido = false;
		for (TarifaDTO tarifaDTO : getListTarifas()) {
			if ((tarifaDTO.getTarifaConComa() == null) || (tarifaDTO.getTarifaConComa().equals(""))) {
				tarifaDTO.setTarifa(BigDecimal.valueOf(0));
				validarOk = false;
			} else {
				// Conversi�n del campo TarifaConComa y asignarlo a el campo Tarifa
				tarifaDTO.setTarifa(conversionTarifaABigdecimal(tarifaDTO));
				// Se asigna el valor de la Entidad seleccionada
				tarifaDTO.setEntidadId(getIdEntidadSeleccionada());
			}
			if (tarifaDTO.getValorMinimo() == null || (tarifaDTO.getValorMinimo().compareTo(BigDecimal.valueOf(0)) == 0)) {			
				validarOk = false;
			} 
			if (tarifaDTO.getValorMaximo() == null || (tarifaDTO.getValorMaximo().compareTo(BigDecimal.valueOf(0)) == 0)) {
				validarOk = false;
			}
			if (tarifaDTO.getPorcentajePerito()== null || (tarifaDTO.getPorcentajePerito().compareTo(BigDecimal.valueOf(0)) == 0)) {
				validarOk = false;
			}
			
			// Permite guaradar si existe tipo de avaluos que no se llenen
			if (!tarifaDTO.getTarifaConComa().isEmpty() 
					&& tarifaDTO.getValorMinimo().compareTo(BigDecimal.valueOf(0)) != 0 
					&& tarifaDTO.getValorMaximo().compareTo(BigDecimal.valueOf(0)) != 0
					&& tarifaDTO.getPorcentajePerito().compareTo(BigDecimal.valueOf(0)) != 0) {
				valido = true;
			}
			
			if (valido) {
				validarOk = true;
			}
		}
		return validarOk;
	}
	
	/**
	 * Conversi�n de String Tarifa con coma a Bigdecimal
	 * @param tarifaDTO Ojeto
	 * @return BigDecimal Valor
	 */
	private BigDecimal conversionTarifaABigdecimal(TarifaDTO tarifaDTO) {
		BigDecimal tarifa = new BigDecimal(0.0);
		try {
			if (!"".equals(tarifaDTO.getTarifaConComa())) {
				// El Usuario digita el numero con Puntos se debe realizar la conversi�n
				tarifaDTO.setTarifaConComa(tarifaDTO.getTarifaConComa().replace(".", ","));
				tarifa = new BigDecimal(tarifaDTO.getTarifaConComa().replace(",", "."));
			}
			return tarifa;	
		} catch (Exception e) {
			mensajeGrow("Tarifa debe ser numerico", null);
		}
		return tarifa;
	}
	
	/**
	 * Conversi�n de Bigdecimal Tarifa a String con coma
	 * @param tarifaDTO Objeto
	 * @return String Valor
	 */
	private String conversionTarifaAString(TarifaDTO tarifaDTO) {
		String tarifa = null;
		if(tarifaDTO.getTarifa()!=null){
			tarifa = tarifaDTO.getTarifa().toString().replace(".", ",");
		}
		
		return tarifa;
	}

	/**
	 * ----------------- Getter and Setter --------------------------------
	 */
	
	public List<EntidadDTO> getListEntidades() {
		return listEntidades;
	}

	public void setListEntidades(List<EntidadDTO> listEntidades) {
		this.listEntidades = listEntidades;
	}

	public List<TarifaDTO> getListTarifas() {
		return listTarifas;
	}

	public void setListTarifas(List<TarifaDTO> listTarifas) {
		this.listTarifas = listTarifas;
	}

	public Long getIdEntidadSeleccionada() {
		return idEntidadSeleccionada;
	}

	public void setIdEntidadSeleccionada(Long idEntidadSeleccionada) {
		this.idEntidadSeleccionada = idEntidadSeleccionada;
	}
	
}
