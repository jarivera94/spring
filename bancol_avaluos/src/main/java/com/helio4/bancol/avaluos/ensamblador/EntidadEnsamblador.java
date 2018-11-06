package com.helio4.bancol.avaluos.ensamblador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.EntidadDTO;
import com.helio4.bancol.avaluos.modelo.Entidad;
import com.helio4.bancol.avaluos.persistencia.EntidadRepository;
import com.helio4.bancol.avaluos.servicio.excepciones.EntidadNotFoundException;

@Component
public class EntidadEnsamblador {

	@Autowired
	private EntidadRepository entidadRepository;
	
	/**
	 * Metodo que asigna los valores para crear la Entidad
	 * @param entidadDTO Dto de Entidad
	 * @return Objeto Entidad
	 * @throws EntidadNotFoundException
	 */
	public Entidad createEntidad(EntidadDTO entidadDTO) throws EntidadNotFoundException {
		Entidad entidad = new Entidad();		
		entidad.setNit(entidadDTO.getNit());
		entidad.setNombre(entidadDTO.getNombre());
		entidad.setPrefijo(entidadDTO.getPrefijo().toUpperCase());
		entidad.setPorcentajeValorAsegurable(entidadDTO.getPorcentajeValorAsegurable() / 100);
		entidad.setDuracionSemaforoVerde(entidadDTO.getDuracionSemaforoVerde() * 3600000);
		entidad.setDuracionSemaforoAmarillo(entidadDTO.getDuracionSemaforoAmarillo() * 3600000);
		entidad.setDuracionSemaforoRojo(entidadDTO.getDuracionSemaforoRojo() * 3600000);
		entidad.setFechaCreacion(entidadDTO.getFechaCreacion());
		entidad.setCobradoPorBancol(entidadDTO.getCobradoPorBancol());
		entidad.setActivo(entidadDTO.getActivo());
		entidad.setCodigoBUA(entidadDTO.getCodigoBUA());
		return entidad;
	}

	/**
	 * Metodo que asigna los valores para actualizar la Entidad
	 * @param entidadDTO Dto de la Entidad a actualizar
	 * @return Objeto Entidad
	 * @throws EntidadNotFoundException
	 */
	public Entidad updateEntidad(EntidadDTO entidadDTO) throws EntidadNotFoundException {
		Entidad entidad = new Entidad();		
		entidad = entidadRepository.findOne(entidadDTO.getId());
		if (entidad == null) {
			throw new EntidadNotFoundException();
		}
		entidad.setNit(entidadDTO.getNit());
		entidad.setNombre(entidadDTO.getNombre());
		entidad.setPrefijo(entidadDTO.getPrefijo());
		// Si el valor digitado en Porcentaje es decimal mayor a 1 realiza la conversi�n.
		if (entidadDTO.getPorcentajeValorAsegurable() > 1) {
			entidadDTO.setPorcentajeValorAsegurable(entidadDTO.getPorcentajeValorAsegurable() / 100);
		}
		entidad.setDuracionSemaforoVerde(entidadDTO.getDuracionSemaforoVerde() * 3600000);
		entidad.setDuracionSemaforoAmarillo(entidadDTO.getDuracionSemaforoAmarillo() * 3600000);
		entidad.setDuracionSemaforoRojo(entidadDTO.getDuracionSemaforoRojo() * 3600000);
 		entidad.setPorcentajeValorAsegurable(entidadDTO.getPorcentajeValorAsegurable());
		entidad.setFechaCreacion(entidadDTO.getFechaCreacion());
		entidad.setCobradoPorBancol(entidadDTO.getCobradoPorBancol());
		entidad.setActivo(entidadDTO.getActivo());	
		entidad.setCodigoBUA(entidadDTO.getCodigoBUA());
		return entidad;
	}
	
	public EntidadDTO escribirDTO(Entidad entidad) {
		EntidadDTO entidadDTO = null;
		if(entidad!=null){
			entidadDTO = new EntidadDTO();
			entidadDTO.setId(entidad.getId());
			entidadDTO.setNombre(entidad.getNombre());
			entidadDTO.setNit(entidad.getNit());
			entidadDTO.setPrefijo(entidad.getPrefijo());
			entidadDTO.setPorcentajeValorAsegurable(entidad.getPorcentajeValorAsegurable());
			entidadDTO.setDuracionSemaforoVerde(entidad.getDuracionSemaforoVerde() / 3600000);
			entidadDTO.setDuracionSemaforoAmarillo(entidad.getDuracionSemaforoAmarillo() / 3600000);
			entidadDTO.setDuracionSemaforoRojo(entidad.getDuracionSemaforoRojo() / 3600000);
			entidadDTO.setFechaCreacion(entidad.getFechaCreacion());
			entidadDTO.setCobradoPorBancol(entidad.getCobradoPorBancol());
			entidadDTO.setActivo(entidad.getActivo());
			if(entidad.getCodigoBUA() != null){
				entidadDTO.setCodigoBUA(entidad.getCodigoBUA());
			}else{
				entidadDTO.setCodigoBUA(0);
			}
			
			/*if (entidad.getSucursales() != null && !entidad.getSucursales().isEmpty()) {
				escribirSucursales(entidadDTO, entidad.getSucursales());
			}
			if (entidad.getTarifas() != null && !entidad.getTarifas().isEmpty()) {
				escribirTarifas(entidadDTO, entidad.getTarifas());
			}
			if (entidad.getSegmentos() != null && !entidad.getSegmentos().isEmpty()) {
				escribirSegmentos(entidadDTO, entidad.getSegmentos());
			}*/
		}
		
		return entidadDTO;
	}
}
