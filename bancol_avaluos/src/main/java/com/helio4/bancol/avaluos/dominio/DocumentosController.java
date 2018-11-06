package com.helio4.bancol.avaluos.dominio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.AvaluoDTO;
import com.helio4.bancol.avaluos.dto.DocumentoDTO;
import com.helio4.bancol.avaluos.exception.DocumentoNotFoundException;
import com.helio4.bancol.avaluos.servicio.datos.IDocumentoService;
import com.helio4.bancol.avaluos.servicio.datos.ParametroService;
import javax.ejb.PostActivate;

@Component
public class DocumentosController {
	
	private static Logger log = LoggerFactory.getLogger(DocumentosController.class);
	
	private String separator = "/";
	
	@Autowired
	@Qualifier("repositoryParametroService")
	private ParametroService parametroService;
	
	@Autowired
	@Qualifier("repositoryDocumentoService")
	private IDocumentoService documentoService;
	
	
	@PostActivate
	public void inicializar(){}
	/**
	 * Método que se encarga de subir archivos de un Avalúo remate.
	 * */
	
	public void subirArchivo(UploadedFile archivo, AvaluoDTO avaluo) {
		String rutaDocumentos = this.parametroService.encontrarPorNombre("ruta_documentos").getValor();
		
		if(rutaDocumentos.contains("\\")){
			separator = "\\";
		}
		
        try {
        	String path =String.valueOf(rutaDocumentos+separator+ avaluo.getCodigoExterno() ) ;
        	File directorio = new File( rutaDocumentos+separator+avaluo.getCodigoExterno() );
        	
        	boolean existe = directorio.exists();
        	if(!existe){
        		directorio.mkdirs();
        	}
            InputStream inputStream = archivo.getInputstream();
            OutputStream out = new FileOutputStream(new File(directorio,archivo.getFileName()));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            inputStream.close();
            out.flush();
            out.close();
            
            DocumentoDTO documentoDTO = new DocumentoDTO();
            documentoDTO.setRutaUbicacion(directorio+separator+archivo.getFileName());
            documentoDTO.setAvaluoId( avaluo.getId() ); 
            documentoDTO.setTipoDocumento( archivo.getContentType() );
            this.documentoService.crear(documentoDTO);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
	/**
	 * Documentos asociados a un avalúo
	 * 
	 * @param id identificador del avalúo.
	 * */
	public List<DocumentoDTO> encontrarDocumentos(Long id) {
		return this.documentoService.encontrarDocumentos(id);
		
	}

	/**
	 * Método que se encarga de borrar un documento en fisíco
	 * y del sistema de base de datos.
	 * 
	 * @param documentoDTO , documento a eliminar.
	 * */
	public void eliminarDocumento(DocumentoDTO documentoDTO) {
		String rutaDocumentos = this.parametroService.encontrarPorNombre("ruta_documentos").getValor();
		File file = new File( rutaDocumentos+"/"+documentoDTO.getRutaUbicacion() );

		try{
			if(file.delete()){
				log.debug("El archivo {} ha sido eliminado", file.getName());
			}else{
				log.error("NO se ha podido eliminar el archivo: {}",
                        documentoDTO.getRutaUbicacion());
			}
			try {
				this.documentoService.eliminar( documentoDTO.getId() );
			} catch (DocumentoNotFoundException e) {
				log.error( "No se encontro el documento con id: {}",
                        documentoDTO.getId(), e);
			}
		}catch(Exception e){
			log.error("error al intentar  eliminar el archivo: {}",
                    documentoDTO.getRutaUbicacion(), e);
		}
		
		
		
	}

}
