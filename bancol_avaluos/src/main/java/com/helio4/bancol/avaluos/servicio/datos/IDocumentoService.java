package com.helio4.bancol.avaluos.servicio.datos;

import java.util.List;

import org.springframework.stereotype.Service;

import com.helio4.bancol.avaluos.dto.DocumentoDTO;
import com.helio4.bancol.avaluos.exception.DocumentoNotFoundException;

@Service
public interface IDocumentoService {

	public DocumentoDTO crear(DocumentoDTO documentoDTO);

	public DocumentoDTO eliminar(Long documentoId) throws DocumentoNotFoundException;

	public List<DocumentoDTO>  encontrarDocumentos(Long id);

	
}
