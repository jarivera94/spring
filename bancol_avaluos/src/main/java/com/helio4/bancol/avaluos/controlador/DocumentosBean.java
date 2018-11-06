package com.helio4.bancol.avaluos.controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.helio4.bancol.avaluos.dominio.DocumentosController;
import com.helio4.bancol.avaluos.dto.DocumentoDTO;

@Controller
@Scope("view")
@Qualifier("documentosBean")
public class DocumentosBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2323771603760334892L;

	private static Logger log = LoggerFactory.getLogger(DocumentosBean.class);

	/**
	 * Contenedor de primefaces del archivo que el usuario va a subir.
	 * */
	private UploadedFile archivo;

	@Autowired
	private DocumentosController documentosController;

	@Autowired
	private ListadoAvaluosBean listadoAvaluosBean;

	/**
	 * Documentos asociados al avaluo seleccionado.
	 * 
	 * */
	private List<DocumentoDTO> documentos;
	
	private StreamedContent file;

	@PostConstruct
	public void init() {
		log.debug("Inicializando DocumentosBean....");
		actualizarTabla();
	}

	public void subir(FileUploadEvent event) {
		this.archivo = event.getFile();
		if (this.archivo != null) {
			this.documentosController.subirArchivo(this.archivo,this.listadoAvaluosBean.getAvaluo());
			actualizarTabla();
			RequestContext.getCurrentInstance().update("form-documentos");
		}
	}
	
	public void borrarDocumento(int index){
		DocumentoDTO documentoDTO  = this.documentos.get(index);
		this.documentosController.eliminarDocumento(documentoDTO);
		actualizarTabla();
		RequestContext.getCurrentInstance().update("form-documentos");
	}
	
	public void actualizarTabla(){
		if(this.listadoAvaluosBean.getAvaluo() != null)
			this.documentos = this.documentosController.encontrarDocumentos(this.listadoAvaluosBean.getAvaluo().getId());
	}
	
	public void download(DocumentoDTO documentoDTO) {
		File initialFile = new File(documentoDTO.getRutaUbicacion());
	    InputStream stream;
		try {
			stream = new FileInputStream(initialFile);
			file = new DefaultStreamedContent(stream, documentoDTO.getTipoDocumento(), documentoDTO.getNombre());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
	
	public void cerrar(){
		String uri = FacesContext.getCurrentInstance()
	            .getExternalContext().encodeActionURL(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/pages/avaluos/listadoAvaluos.xhtml");
	        try {
	            FacesContext.getCurrentInstance().getExternalContext().redirect(uri);
	        } catch (IOException e) {
	            // TODO Reireccionar a página no encontrada
	            e.printStackTrace();
	        }
	}

	public List<DocumentoDTO> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<DocumentoDTO> documentos) {
		this.documentos = documentos;
	}

	public StreamedContent getFile() {
        return file;
    }
}
