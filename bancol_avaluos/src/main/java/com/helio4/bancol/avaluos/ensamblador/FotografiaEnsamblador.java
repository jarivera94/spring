package com.helio4.bancol.avaluos.ensamblador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.FotografiaDTO;
import com.helio4.bancol.avaluos.modelo.Anexo;
import com.helio4.bancol.avaluos.modelo.Avaluo;
import com.helio4.bancol.avaluos.modelo.Croquis;
import com.helio4.bancol.avaluos.modelo.Fotografia;
import com.helio4.bancol.avaluos.persistencia.AnexoRepository;
import com.helio4.bancol.avaluos.persistencia.AvaluoRepository;
import com.helio4.bancol.avaluos.persistencia.CroquisRepository;
import com.helio4.bancol.avaluos.persistencia.FotografiaRepository;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.FotografiaNotFoundException;

@Component
public class FotografiaEnsamblador {
	
	@Autowired
	private FotografiaRepository fotografiaRepository;
	
	@Autowired
	private AnexoRepository anexoRepository;
	
	@Autowired
	private CroquisRepository croquisRepository;
	
	@Autowired
	private AvaluoRepository avaluoRepository;

	public Fotografia crearFotografia(FotografiaDTO fotografiaDTO) throws AvaluoNotFoundException {
		Fotografia fotografia = new Fotografia();
		fotografia.setRutaUbicacion(fotografiaDTO.getRutaUbicacion());
		fotografia.setDescripcion(fotografiaDTO.getDescripcion());
		fotografia.setOrden(fotografiaDTO.getOrden());
		Avaluo avaluo = avaluoRepository.findOne(fotografiaDTO.getAvaluoId());
		if (avaluo == null) {
			throw new AvaluoNotFoundException();
		}
		fotografia.setAvaluo(avaluo);
		return fotografia;
	}
	
	public Anexo crearAnexo(FotografiaDTO fotografiaDTO) throws AvaluoNotFoundException {
		Anexo fotografia = new Anexo();
		fotografia.setRutaUbicacion(fotografiaDTO.getRutaUbicacion());
		fotografia.setDescripcion(fotografiaDTO.getDescripcion());
		fotografia.setOrden(fotografiaDTO.getOrden());
		Avaluo avaluo = avaluoRepository.findOne(fotografiaDTO.getAvaluoId());
		if (avaluo == null) {
			throw new AvaluoNotFoundException();
		}
		fotografia.setAvaluo(avaluo);
		return fotografia;
	}
	
	public Croquis crearCroquis(FotografiaDTO fotografiaDTO) throws AvaluoNotFoundException {
		Croquis fotografia = new Croquis();
		fotografia.setRutaUbicacion(fotografiaDTO.getRutaUbicacion());
		fotografia.setDescripcion(fotografiaDTO.getDescripcion());
		fotografia.setOrden(fotografiaDTO.getOrden());
		Avaluo avaluo = avaluoRepository.findOne(fotografiaDTO.getAvaluoId());
		if (avaluo == null) {
			throw new AvaluoNotFoundException();
		}
		fotografia.setAvaluo(avaluo);
		return fotografia;
	}
	
	public void actualizarFotografia(Long fotografiaId, FotografiaDTO fotografiaDTO) throws FotografiaNotFoundException, AvaluoNotFoundException {
		Fotografia fotografia = fotografiaRepository.findOne(fotografiaId);
		if (fotografia == null) {
			throw new FotografiaNotFoundException();
		}
		fotografia.setDescripcion(fotografiaDTO.getDescripcion());
		fotografia.setOrden(fotografiaDTO.getOrden());
		Avaluo avaluo = avaluoRepository.findOne(fotografiaDTO.getAvaluoId());
		if (avaluo == null) {
			throw new AvaluoNotFoundException();
		}
		fotografia.setAvaluo(avaluo);
	}
	
	public void actualizarAnexo(Long fotografiaId, FotografiaDTO fotografiaDTO) throws FotografiaNotFoundException, AvaluoNotFoundException {
		Anexo fotografia = anexoRepository.findOne(fotografiaId);
		if (fotografia == null) {
			throw new FotografiaNotFoundException();
		}
		fotografia.setDescripcion(fotografiaDTO.getDescripcion());
		fotografia.setOrden(fotografiaDTO.getOrden());
		Avaluo avaluo = avaluoRepository.findOne(fotografiaDTO.getAvaluoId());
		if (avaluo == null) {
			throw new AvaluoNotFoundException();
		}
		fotografia.setAvaluo(avaluo);
	}
	
	public void actualizarCroquis(Long fotografiaId, FotografiaDTO fotografiaDTO) throws FotografiaNotFoundException, AvaluoNotFoundException {
		Croquis fotografia = croquisRepository.findOne(fotografiaId);
		if (fotografia == null) {
			throw new FotografiaNotFoundException();
		}
		fotografia.setDescripcion(fotografiaDTO.getDescripcion());
		fotografia.setOrden(fotografiaDTO.getOrden());
		Avaluo avaluo = avaluoRepository.findOne(fotografiaDTO.getAvaluoId());
		if (avaluo == null) {
			throw new AvaluoNotFoundException();
		}
		fotografia.setAvaluo(avaluo);
	}
	
	public FotografiaDTO escribirDTO(Fotografia fotografia) {
		FotografiaDTO fotografiaDTO = new FotografiaDTO();
		fotografiaDTO.setId(fotografia.getId());
		fotografiaDTO.setRutaUbicacion(fotografia.getRutaUbicacion());;
		fotografiaDTO.setDescripcion(fotografia.getDescripcion());
		fotografiaDTO.setAvaluoId(fotografia.getAvaluo().getId());
		fotografiaDTO.setOrden(fotografia.getOrden());
		fotografiaDTO.setDirty(true);
		return fotografiaDTO;
	}
	
	public FotografiaDTO escribirDTO(Anexo fotografia) {
		FotografiaDTO fotografiaDTO = new FotografiaDTO();
		fotografiaDTO.setId(fotografia.getId());
		fotografiaDTO.setRutaUbicacion(fotografia.getRutaUbicacion());;
		fotografiaDTO.setDescripcion(fotografia.getDescripcion());
		fotografiaDTO.setAvaluoId(fotografia.getAvaluo().getId());
		fotografiaDTO.setOrden(fotografia.getOrden());
		fotografiaDTO.setDirty(true);
		return fotografiaDTO;
	}
	
	public FotografiaDTO escribirDTO(Croquis fotografia) {
		FotografiaDTO fotografiaDTO = new FotografiaDTO();
		fotografiaDTO.setId(fotografia.getId());
		fotografiaDTO.setRutaUbicacion(fotografia.getRutaUbicacion());;
		fotografiaDTO.setDescripcion(fotografia.getDescripcion());
		fotografiaDTO.setAvaluoId(fotografia.getAvaluo().getId());
		fotografiaDTO.setOrden(fotografia.getOrden());
		fotografiaDTO.setDirty(true);
		return fotografiaDTO;
	}
	
	public List<FotografiaDTO> escribirListaDTO(List<Fotografia> fotografias) {
		List<FotografiaDTO> fotografiaDTOs = new ArrayList<FotografiaDTO>();
		for (Fotografia fotografia:fotografias) {
			fotografiaDTOs.add(escribirDTO(fotografia));
		}
		return fotografiaDTOs;
	}
	
	public List<FotografiaDTO> escribirListaAnexosDTO(List<Anexo> fotografias) {
		List<FotografiaDTO> anexoDTOs = new ArrayList<FotografiaDTO>();
		for (Anexo fotografia:fotografias) {
			anexoDTOs.add(escribirDTO(fotografia));
		}
		return anexoDTOs;
	}
	
	public List<FotografiaDTO> escribirListaCroquisDTO(List<Croquis> fotografias) {
		List<FotografiaDTO> anexoDTOs = new ArrayList<FotografiaDTO>();
		for (Croquis fotografia:fotografias) {
			anexoDTOs.add(escribirDTO(fotografia));
		}
		return anexoDTOs;
	}

}
