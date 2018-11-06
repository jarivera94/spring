package com.helio4.bancol.avaluos.servicio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.helio4.bancol.avaluos.servicio.datos.AvaluoComercialService;
import com.helio4.bancol.avaluos.servicio.excepciones.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.AvaluoComercialDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;
import com.helio4.bancol.avaluos.ensamblador.AvaluoEnsamblador;
import com.helio4.bancol.avaluos.ensamblador.UsuarioEnsamblador;
import com.helio4.bancol.avaluos.exception.GarajeNotFoundException;
import com.helio4.bancol.avaluos.modelo.AvaluoComercial;
import com.helio4.bancol.avaluos.modelo.EstadoPorAsignar;
import com.helio4.bancol.avaluos.modelo.Usuario;
import com.helio4.bancol.avaluos.modelo.Usuario.DocumentoIdentificacion;
import com.helio4.bancol.avaluos.persistencia.AvaluoComercialRepository;
import com.helio4.bancol.avaluos.persistencia.EstadoAvaluoRepository;
import com.helio4.bancol.avaluos.persistencia.UsuarioRepository;

@Component(value="repositoryAvaluoComercialService")
@Transactional(readOnly = true)
public class RepositoryAvaluoComercialService implements AvaluoComercialService {

	private static Logger log = LoggerFactory.getLogger( RepositoryAvaluoComercialService.class );
	
	@Autowired
	private AvaluoComercialRepository avaluoComercialRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private AvaluoEnsamblador avaluoEnsamblador;
	
	@Autowired
	private UsuarioEnsamblador usuarioEnsamblador;
	
	@Autowired
	private EstadoAvaluoRepository estadoAvaluoRepository;

	@Transactional
	@Override
	public AvaluoComercialDTO crear(AvaluoComercialDTO avaluoComercialDTO, UsuarioDTO usuarioDTO) {
        log.debug("Creando la solicitud del avaluo hipotecario:  {}",
                avaluoComercialDTO);
		AvaluoComercial avaluoComercial;
		try {
            AvaluoComercial avaluo = avaluoEnsamblador
                .crearAvaluoComercial(avaluoComercialDTO);
            Usuario creador = usuarioRepository.findOne(
					new DocumentoIdentificacion(usuarioDTO.getTipoDocumentoIdentificacion(),
							usuarioDTO.getNumeroDocumento()));
            avaluo.setCreador(creador);
            avaluo.setFechaCreacion(avaluo.getEstado().getFechaEstado());
			avaluoComercial = avaluoComercialRepository.save(avaluo);
			EstadoPorAsignar estadoPorAsignar = (EstadoPorAsignar) avaluoComercial.getEstado();
			estadoPorAsignar.setUsuario(creador);
			estadoAvaluoRepository.save(estadoPorAsignar);
		} catch (ClienteNotFoundException e) {
            log.error("ClienteNotFoundException: No se encontro el cliente en el avaluo:  {}",
                    avaluoComercialDTO);
			return null;
		} catch (EntidadNotFoundException e) {
            log.error("EntidadNotFoundException: No se encontro la entidad en el avaluo:  {}",
                    avaluoComercialDTO);
			return null;
		} catch (UsuarioNotFoundException e) {
            log.error("UsuarioNotFoundException: No se encontro el Usuario en el avaluo:  {}",
                    avaluoComercialDTO);
			return null;
		} catch (FotografiaNotFoundException e) {
            log.error("FotografiaNotFoundException: No se encontro la fotografia en el avaluo:  {}",
                    avaluoComercialDTO);
			return null;
		} catch (AreaNotFoundException e) {
            log.error("AreaNotFoundException: No se encontro el area en el avaluo:  {}",
                    avaluoComercialDTO);
			return null;
		} catch (InmuebleNotFoundException e) {
            log.error("InmuebleNotFoundException: No se encontro el inmueble en el avaluo:  {}",
                    avaluoComercialDTO);
			return null;
		} catch (ServidumbreNotFoundException e) {
            log.error("ServidumbreNotFoundException: No se encontro la servidumbre en el avaluo:  {}",
                    avaluoComercialDTO);
			return null;
		} catch (ViaAccesoNotFoundException e) {
            log.error("ViaAccesoNotFoundException: No se encontro la via de acceso en el avaluo:  {}",
                    avaluoComercialDTO);
			return null;
		} catch (MetodoValuacionNotFoundException e) {
            log.error("MetodoValuacionNotFoundException: No se encontro el método de valuación en el avaluo:  {}",
                    avaluoComercialDTO);
			return null;
		} catch (AreaConstruccionNotFoundException e) {
            log.error("AreaConstruccionNotFoundException: No se encontro el area de construccion en el avaluo:  {}",
                    avaluoComercialDTO);
			return null;
		} catch (TipoAvaluoNotFoundException e) {
            log.error("AreaConstruccionNotFoundException: No se encontro el tipo de avaluo en el avaluo:  {}",
                    avaluoComercialDTO);
			return null;
		}catch (GarajeNotFoundException e) {
            log.error("GarajeNotFoundException: No se encontro un garaje en el avaluo:  {}",
                    avaluoComercialDTO);
			return null;
		}
		avaluoComercialDTO.setId(avaluoComercial.getId());
		return avaluoComercialDTO;
	}

	@Transactional(rollbackFor = AvaluoNotFoundException.class)
	@Override
	public AvaluoComercialDTO eliminar(Long avaluoId) throws AvaluoNotFoundException {
		AvaluoComercial deleted = avaluoComercialRepository.findOne(avaluoId);
		if (deleted == null){
			throw new AvaluoNotFoundException();
		}
		avaluoComercialRepository.delete(deleted);
		return avaluoEnsamblador.escribirDTO(deleted);
	}

	@Transactional(readOnly = true)
	@Override
	public List<AvaluoComercialDTO> encontrarTodos() {
		List<AvaluoComercialDTO> avaluoComercialDTOs = new ArrayList<AvaluoComercialDTO>();
		for (AvaluoComercial avaluoComercial:avaluoComercialRepository.findAll()) {
			avaluoComercialDTOs.add(avaluoEnsamblador.escribirDTO(avaluoComercial));
		}
		return avaluoComercialDTOs;
	}

	@Transactional(readOnly = true)
	@Override
	public AvaluoComercialDTO encontrarPorId(Long id) {
		return avaluoEnsamblador.escribirDTO(avaluoComercialRepository.findOne(id));
	}

	@Transactional(rollbackFor = AvaluoNotFoundException.class, noRollbackFor = AreaNotFoundException.class)
	@Override
	public AvaluoComercialDTO actualizar(AvaluoComercialDTO actualizado)
			throws AvaluoNotFoundException {
        log.debug("Actualizando avaluo  {}",
            actualizado);
        AvaluoComercial avaluoComercial = avaluoComercialRepository
            .findOne(actualizado.getId());
        if (avaluoComercial == null) {
            throw new AvaluoNotFoundException();
        }
        return actualizar(avaluoComercial, actualizado);
	}

    @Override
    @Transactional(rollbackFor = AvaluoNotFoundException.class, noRollbackFor = AreaNotFoundException.class)
    public AvaluoComercialDTO actualizar(AvaluoComercial avaluoComercial, AvaluoComercialDTO actualizado)
        throws AvaluoNotFoundException {
        try {
			avaluoEnsamblador.actualizarAvaluoComercial(avaluoComercial, actualizado);
		} catch (InmuebleNotFoundException e) {
            log.error("InmuebleNotFoundException: No se encontro el inmueble en el avaluo:  {}",
                    actualizado);
			return null;
		} catch (ServidumbreNotFoundException e) {
            log.error("ServidumbreNotFoundException: No se encontro la servidumbre en el avaluo:  {}",
                    actualizado);
			return null;
		} catch (ViaAccesoNotFoundException e) {
            log.error("ViaAccesoNotFoundException: No se encontro la via de acceso en el avaluo:  {}",
                    actualizado);
			return null;
		} catch (MetodoValuacionNotFoundException e) {
            log.error("MetodoValuacionNotFoundException: No se encontro el método de valuación en el avaluo:  {}",
                    actualizado);
			return null;
		} catch (AreaConstruccionNotFoundException e) {
            log.error("AreaConstruccionNotFoundException: No se encontro el area de construccion en el avaluo:  {}",
                    actualizado);
			return null;
		} catch (FormatoInformeNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EntidadNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClienteNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UsuarioNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FotografiaNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (AreaNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TipoAvaluoNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (GarajeNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
		return actualizado;
    }

	
	public void setAvaluoRepository(AvaluoComercialRepository avaluoComercialRepository){
		this.avaluoComercialRepository = avaluoComercialRepository;
	}

    @Transactional(readOnly = true)
	@Override
	public AvaluoComercialDTO encontrarAvaluoConAreas(Long avaluoId) {
		return avaluoEnsamblador.escribirDTO(avaluoComercialRepository.encontrarAvaluoConAreas(avaluoId));
	}

    @Transactional(readOnly = true)
	@Override
	public List<AvaluoComercialDTO> encontrarPorFechaTerminacion(
			Date fechaInicial, Date fechaFinal) {
		List<AvaluoComercialDTO> avaluoComercialDTOs = new ArrayList<AvaluoComercialDTO>();
		for (AvaluoComercial avaluoComercial:avaluoComercialRepository.encontrarPorFechaTerminacion(fechaInicial, fechaFinal)) {
			avaluoComercialDTOs.add(avaluoEnsamblador.escribirDTO(avaluoComercial));
		}
		return avaluoComercialDTOs;
	}

}
