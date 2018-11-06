package com.helio4.bancol.avaluos.ensamblador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.SegmentoDTO;
import com.helio4.bancol.avaluos.modelo.Entidad;
import com.helio4.bancol.avaluos.modelo.Segmento;
import com.helio4.bancol.avaluos.persistencia.EntidadRepository;
import com.helio4.bancol.avaluos.persistencia.SegmentoRepository;
import com.helio4.bancol.avaluos.servicio.excepciones.EntidadNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.SegmentoNotFoundException;
import com.helio4.bancol.avaluos.servicio.util.DateUtils;

@Component
public class SegmentoEnsamblador {

	@Autowired
	private SegmentoRepository segmentoRepository;

	@Autowired
	private EntidadRepository entidadRepository;

	/**
	 * Metodo que asigna los valores para crear la Segmento
	 * @param segmentoDTO Objeto
	 * @return Objeto
	 * @throws EntidadNotFoundException
	 */
	public Segmento createSegmento(SegmentoDTO segmentoDTO) throws EntidadNotFoundException {		
		Segmento segmento = null;
		if(segmentoDTO !=null){
			segmento = new Segmento();
			segmento.setId(segmentoDTO.getId());
			segmento.setNombre(segmentoDTO.getNombre());
			if( segmentoDTO.getFechaCreacion()!=null ){
				segmento.setFechaCreacion(DateUtils.getFecha(segmentoDTO.getFechaCreacion(), DateUtils.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS));
			}
			segmento.setAccesoCredito(segmentoDTO.getAccesoCredito());
			segmento.setActivo(segmentoDTO.getActivo());
			segmento.setCobradoPorBancol(segmentoDTO.getCobradoPorBancol());
            if (segmentoDTO.getEntidadId() != null) {
                Entidad entidad = entidadRepository.findOne(segmentoDTO.getEntidadId());
                if (entidad == null) {
                    throw new EntidadNotFoundException();
                }
                segmento.setEntidad(entidad);
            }
		}

		return segmento;
	}
	/**
	 * Metodo que asigna los valores para actualizar la Segmento
	 * @param segmentoDTO Objeto
	 * @return Objeto
	 * @throws SegmentoNotFoundException
	 * @throws EntidadNotFoundException
	 */
	
	public Segmento updateSegmento(SegmentoDTO segmentoDTO) throws SegmentoNotFoundException, EntidadNotFoundException {		
		Segmento segmento = new Segmento();
		segmento = segmentoRepository.findOne(segmentoDTO.getId());
		if (segmento == null) {
			throw new SegmentoNotFoundException();
		}
		segmento.setNombre(segmentoDTO.getNombre());
		segmento.setFechaCreacion(DateUtils.getFecha(segmentoDTO.getFechaCreacion(), DateUtils.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS));
		segmento.setAccesoCredito(segmentoDTO.getAccesoCredito());
		segmento.setActivo(segmentoDTO.getActivo());
		segmento.setCobradoPorBancol(segmentoDTO.getCobradoPorBancol());
		Entidad entidad = entidadRepository.findOne(segmentoDTO.getEntidadId());
		if (entidad == null) {
			throw new EntidadNotFoundException();
		}
		segmento.setEntidad(entidad);

		return segmento;
	}
	
	public SegmentoDTO escribirDTO(Segmento segmento){
		SegmentoDTO segmentoDTO = null;
		
		if(segmento!=null){
			segmentoDTO = new SegmentoDTO();
			segmentoDTO.setAccesoCredito(segmento.getAccesoCredito());
			segmentoDTO.setActivo(segmento.getActivo());
			segmentoDTO.setCobradoPorBancol(segmento.getCobradoPorBancol());
			if(segmento.getEntidad()!=null){
				segmentoDTO.setEntidadId(segmento.getEntidad().getId());
				segmentoDTO.setNombreEntidad(segmento.getEntidad().getNombre());
			}
			if(segmento.getFechaCreacion() != null)
				segmentoDTO.setFechaCreacion(DateUtils.getFechaFormateada(segmento.getFechaCreacion(), DateUtils.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS));
			segmentoDTO.setId(segmento.getId());
			segmentoDTO.setNombre(segmento.getNombre());
		}
		
		return segmentoDTO;
	}
}
