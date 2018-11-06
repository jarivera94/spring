package com.helio4.bancol.avaluos.servicio.datos;

import java.util.List;

import com.helio4.bancol.avaluos.servicio.excepciones.EntidadNotFoundException;
import org.springframework.stereotype.Service;

import com.helio4.bancol.avaluos.dto.AsesorDTO;

@Service
public interface AsesorService {
	
	public List<AsesorDTO> encontrarPorNombre(String texto, Long idEntidad);
	
	public AsesorDTO encontrarPorId(Long id);
	
	public AsesorDTO encontrarPorCorreo(String correo);
	
	public AsesorDTO guardar(AsesorDTO asesorDTO) throws EntidadNotFoundException;
	
	public AsesorDTO actualizar(AsesorDTO asesorDTO) throws EntidadNotFoundException;
	
	public AsesorDTO encontrarAsesor(String nombre, Long idEntidad, String codigoSucursal, String celular, String correo, String telefono);

}
