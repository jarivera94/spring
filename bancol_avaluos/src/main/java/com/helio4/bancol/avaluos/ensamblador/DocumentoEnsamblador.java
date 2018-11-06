package com.helio4.bancol.avaluos.ensamblador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.DocumentoDTO;
import com.helio4.bancol.avaluos.modelo.Avaluo;
import com.helio4.bancol.avaluos.modelo.Documento;
import com.helio4.bancol.avaluos.persistencia.AvaluoRepository;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;

@Component
public class DocumentoEnsamblador {
	
	@Autowired
	private AvaluoRepository avaluoRepository;
	
	public DocumentoDTO escribirDTO(Documento documento){
		DocumentoDTO documentoDTO = new DocumentoDTO();
		documentoDTO.setId( documento.getId() );
		documentoDTO.setRutaUbicacion( documento.getRutaUbicacion() );
		
		int i = documento.getRutaUbicacion().lastIndexOf("\\");
		if(i<0){
			i = documento.getRutaUbicacion().lastIndexOf("/");
		}
		
		documentoDTO.setNombre(documento.getRutaUbicacion().substring(i+1));
		documentoDTO.setTipoDocumento( documento.getTipoDocumento() );
		if(documento.getAvaluo()!=null){
			documentoDTO.setAvaluoId( documento.getAvaluo().getId() ); 
		}
		return documentoDTO;
	}
	
	public Documento crearDocumento(DocumentoDTO documentoDTO) throws AvaluoNotFoundException{
		Documento documento = new Documento();
		documento.setRutaUbicacion( documentoDTO.getRutaUbicacion() );
		documento.setTipoDocumento( documentoDTO.getTipoDocumento() );
		Avaluo avaluo = this.avaluoRepository.findOne(documentoDTO.getAvaluoId());
		if (avaluo == null) {
			throw new AvaluoNotFoundException();
		}
		documento.setAvaluo(avaluo);
		return documento;
	}
	
	public List<DocumentoDTO> escribirDTO( List<Documento> documentos){
		List<DocumentoDTO> documentosDTO = new ArrayList<DocumentoDTO>();
		for( Documento documento: documentos){
			documentosDTO.add( this.escribirDTO(documento) );
		}
		return documentosDTO;
	}
}
