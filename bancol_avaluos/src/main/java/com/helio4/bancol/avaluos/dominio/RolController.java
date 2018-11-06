package com.helio4.bancol.avaluos.dominio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.servicio.excepciones.PaginaInicioNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.PermisoNotFoundException;
import com.helio4.bancol.avaluos.servicio.datos.PermisoService;
import com.helio4.bancol.avaluos.servicio.excepciones.RolNotFoundException;
import com.helio4.bancol.avaluos.servicio.datos.RolService;
import com.helio4.bancol.avaluos.dto.RolDTO;
import com.helio4.bancol.avaluos.dto.PermisoDTO;

@Component
public class RolController {

	@Autowired
	@Qualifier("repositoryRolService")
	private RolService rolService;

    @Autowired
	@Qualifier("repositoryPermisoService")
	private PermisoService permisoService;

	public boolean guardarRol(RolDTO rolDTO) {
		rolService.crear(rolDTO);
		return true;
	}

	public void actualizarRol(RolDTO rolDTO) throws RolNotFoundException,PaginaInicioNotFoundException, PermisoNotFoundException {
		rolService.actualizar(rolDTO);
	}

    public List<RolDTO> encontrarRolesCoordinador(boolean coordinadorAbogados) {
        List<RolDTO> resultado = new ArrayList<RolDTO>();
        if (coordinadorAbogados) {
            resultado.add(rolService.encontrarPorNombre("ABOGADO"));
        }else{
            resultado.add(rolService.encontrarPorNombre("CREADOR"));
        }
        return resultado;
    }

    public List<RolDTO> encontrarRoles(boolean esAdministrador, boolean esAbogado) {
    	List<RolDTO> roles = esAdministrador ? rolService.encontrarRoles()
            : encontrarRolesCoordinador(esAbogado);

    	for(RolDTO rol:roles) {
    		List<PermisoDTO> permisos = rolService.encontrarPermisosPorRol(rol.getId());
    		rol.setPermisos(permisos);
    	}
    	return roles;
    }

    public List<PermisoDTO> encontarPorRol(String nombreRol) {
        return permisoService.encontrarPorRol(nombreRol);
    }

}
