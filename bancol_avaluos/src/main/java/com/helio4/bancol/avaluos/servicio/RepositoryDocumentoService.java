package com.helio4.bancol.avaluos.servicio;

import java.util.List;

import com.helio4.bancol.avaluos.servicio.datos.IDocumentoService;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.DocumentoDTO;
import com.helio4.bancol.avaluos.ensamblador.DocumentoEnsamblador;
import com.helio4.bancol.avaluos.exception.DocumentoNotFoundException;
import com.helio4.bancol.avaluos.modelo.Documento;
import com.helio4.bancol.avaluos.persistencia.DocumentoRepository;

@Component(value="repositoryDocumentoService")
@Transactional(readOnly = true)
public class RepositoryDocumentoService implements IDocumentoService {
	
	private static Logger log = LoggerFactory.getLogger(RepositoryDocumentoService.class);
	
	@Autowired
	private DocumentoEnsamblador documentoEnsamblador;
	
	@Autowired
	private DocumentoRepository documentoRepository;
	
	@Transactional
	@Override
	public DocumentoDTO crear( DocumentoDTO documentoDTO){
		Documento documento;
		try {
			documento = this.documentoEnsamblador.crearDocumento(documentoDTO);
			this.documentoRepository.save(documento);
			documentoDTO.setId( documento.getId() ); 
		} catch (AvaluoNotFoundException e) {
			log.error( "El documento  a crear no tiene asociado un Avaluo", e );
		}
		return documentoDTO;
	}
	
	@Transactional(rollbackFor = DocumentoNotFoundException.class)
	@Override
	public DocumentoDTO eliminar(Long documentoId ) throws DocumentoNotFoundException{
		log.debug( "Eliminando el documento con id: {}",
                documentoId );
		Documento documento = this.documentoRepository.findOne(documentoId);
		if(documento == null ){
			throw new DocumentoNotFoundException();
		}
		this.documentoRepository.delete(documento); 
		return this.documentoEnsamblador.escribirDTO(documento);
	}

	/**
	 * @param avaluoId id del avalúo al cual hace referencia los documentos buscados. 
	 * */
	@Transactional(readOnly = true)
	@Override
	public List<DocumentoDTO> encontrarDocumentos(Long avaluoId) {
		List<Documento> documentos  = this.documentoRepository.encontrarDocumentos(avaluoId);
		List<DocumentoDTO> documentosDTO = this.documentoEnsamblador.escribirDTO(documentos);
		return documentosDTO;
		
	}
}
