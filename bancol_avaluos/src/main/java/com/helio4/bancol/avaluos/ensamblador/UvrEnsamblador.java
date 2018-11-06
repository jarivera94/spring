package com.helio4.bancol.avaluos.ensamblador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.UvrDTO;
import com.helio4.bancol.avaluos.modelo.Uvr;

@Component
public class UvrEnsamblador {
	
	public UvrDTO escribirDTO(Uvr uvr) {
		UvrDTO uvrDTO = new UvrDTO();
		uvrDTO.setId(uvr.getId());
		uvrDTO.setFechaUvr(uvr.getFechaUvr());
		uvrDTO.setValor(uvr.getValor());
		return uvrDTO;
	}
	
	/**
	 * Genera una lista  UvrDTO a partir de una lista de Uvrs.
	 * 
	 * @param  Lista de Uvr
	 * @return Lista de UvrDTO
	 * */
	public List<UvrDTO> escribirDTOs(List<Uvr> uvrs){
		List<UvrDTO> uvrsdtos = new ArrayList<UvrDTO>();
		for( Uvr uvr: uvrs){
			uvrsdtos.add( this.escribirDTO(uvr) );
		}
		return uvrsdtos;
	}
	
	public Uvr crearUvr( UvrDTO uvrDTO){
		Uvr uvr =new Uvr();
		uvr.setFechaUvr( uvrDTO.getFechaUvr() );
		uvr.setValor( uvrDTO.getValor() );
		return uvr;
	}

}
