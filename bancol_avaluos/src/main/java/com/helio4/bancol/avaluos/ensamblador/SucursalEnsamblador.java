package com.helio4.bancol.avaluos.ensamblador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.SucursalDTO;
import com.helio4.bancol.avaluos.modelo.Entidad;
import com.helio4.bancol.avaluos.modelo.Sucursal;
import com.helio4.bancol.avaluos.persistencia.EntidadRepository;
import com.helio4.bancol.avaluos.persistencia.SucursalRepository;
import com.helio4.bancol.avaluos.servicio.excepciones.EntidadNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.SucursalNotFoundException;
import com.helio4.bancol.avaluos.servicio.util.DateUtils;

@Component
public class SucursalEnsamblador {

	@Autowired
	private SucursalRepository sucursalRepository;

	@Autowired
	private EntidadRepository entidadRepository;

	/**
	 * Metodo que asigna los valores para crear la Sucursales
	 * @param sucursalDTO Objeto
	 * @return Objeto 
	 * @throws EntidadNotFoundException
	 */
	public Sucursal createSucursal(SucursalDTO sucursalDTO) throws EntidadNotFoundException {
		Sucursal sucursal = new Sucursal();
        sucursal.setCodigo(sucursalDTO.getCodigo());
        sucursal.setNombre(sucursalDTO.getNombre());
        if(sucursalDTO.getFechaCreacion() != null)
            sucursal.setFechaCreacion(DateUtils.getFecha(sucursalDTO.getFechaCreacion(), DateUtils.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS));
        sucursal.setActivo(sucursalDTO.getActivo().booleanValue());
        Entidad entidad = entidadRepository.findOne(sucursalDTO.getEntidadId());
        if (entidad == null) {
            throw new EntidadNotFoundException();
        }
        sucursal.setEntidad(entidad);
        return sucursal;
	}
	
	/**
	 * Metodo que asigna los valores para actualizar la Sucursales
	 * @param sucursalDTO Objeto
	 * @return Objeto
	 * @throws SucursalNotFoundException
	 * @throws EntidadNotFoundException
	 */
	public Sucursal updateSucursal(SucursalDTO sucursalDTO) throws SucursalNotFoundException, EntidadNotFoundException {
		Sucursal sucursal = new Sucursal();		
		sucursal = sucursalRepository.findOne(sucursalDTO.getId());
		if (sucursal == null) {
			throw new SucursalNotFoundException();
		}
		sucursal.setCodigo(sucursalDTO.getCodigo());
		sucursal.setNombre(sucursalDTO.getNombre());
		if(sucursalDTO.getFechaCreacion() != null)
			sucursal.setFechaCreacion(DateUtils.getFecha(sucursalDTO.getFechaCreacion(), DateUtils.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS));
		sucursal.setActivo(sucursalDTO.getActivo().booleanValue());
		Entidad entidad = entidadRepository.findOne(sucursalDTO.getEntidadId());
		if (entidad == null) {
			throw new EntidadNotFoundException();
		}
		sucursal.setEntidad(entidad);

		return sucursal;
	}

}
