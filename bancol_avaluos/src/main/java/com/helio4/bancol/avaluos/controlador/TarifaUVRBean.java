package com.helio4.bancol.avaluos.controlador;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.helio4.bancol.avaluos.dominio.TarifaUVRController;
import com.helio4.bancol.avaluos.dto.UvrDTO;


/**
 *  Clase que permite subir la infomación de las tarifas de la UVR según 
 *  corresponda su fecha a partir de un archivo CSV.
 *  
 *  
 *  @author Cristian Chaparro <cristian.chaparro@helio4.com>
 *  
 * */

@Controller
@Scope("view")
@Qualifier("tarifaUVRBean")
public class TarifaUVRBean 
{
	/**
	 * Contenedor de primefaces del archivo que el usuario va a subir. 
	 * */
	private UploadedFile archivo;
	
	/**
	 * Listado de UVRS que se muestran como histórico de UVRS.
	 * */
	private List<UvrDTO> uvrs;
	
	/**
	 * Listado de UVRS que filtra el usuario.
	 * 
	 * */
	private List<UvrDTO> uvrsFiltrados;
	
	/**
	 * Controlador de Tarifa uvr.
	 * 
	 * */
	@Autowired
	private TarifaUVRController uvrController;
	
	/**
	 * Postconstructor del Back Bean que se encarga de cargar
	 * los valores predeterminados a mostrar en la vista.
	 * */
	@PostConstruct
	public void init(){
		this.uvrs = this.uvrController.getUvrs();
	}
	/**
	 * Función que se encarga de subir el archivo CSV seleccionado 
	 * por el usuario.
	 * 
	 * */
	public void subir(FileUploadEvent event){
		this.archivo = event.getFile();
        if( this.archivo != null){
        	try {
				List<UvrDTO> uvrs = this.uvrController.procesarCSV(event.getFile().getInputstream());
				Boolean  resultado = this.uvrController.guardarUVRs(uvrs); 
				if( resultado ){
					this.uvrs = this.uvrController.getUvrs();
					//update the datatable with id="tabla_uvrs"
					RequestContext.getCurrentInstance().update("tabla_uvrs");
		            FacesMessage message = new FacesMessage("El archivo ", this.archivo.getFileName() + " ha sido procesado correctamente.");
		            FacesContext.getCurrentInstance().addMessage(null, message);
				}else{
					 FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL,"El archivo ", this.archivo.getFileName() + " NO se pudo procesar.");
			         FacesContext.getCurrentInstance().addMessage(null, message);
				}					
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
	}
	public UploadedFile getFile() {
		return this.archivo;
	}
	public void setFile(UploadedFile file) {
		this.archivo = file;
	}
	public List<UvrDTO> getUvrs() {
		return uvrs;
	}
	public void setUvrs(List<UvrDTO> uvrs) {
		this.uvrs = uvrs;
	}
	public List<UvrDTO> getUvrsFiltrados() {
		return uvrsFiltrados;
	}
	public void setUvrsFiltrados(List<UvrDTO> uvrsFiltrados) {
		this.uvrsFiltrados = uvrsFiltrados;
	}

}
