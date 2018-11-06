package com.helio4.bancol.avaluos.servicio;

import java.util.ArrayList;
import java.util.List;

import com.helio4.bancol.avaluos.servicio.datos.AvaluoRemateService;
import com.helio4.bancol.avaluos.servicio.excepciones.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.AvaluoRemateDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;
import com.helio4.bancol.avaluos.ensamblador.AvaluoEnsamblador;
import com.helio4.bancol.avaluos.exception.GarajeNotFoundException;
import com.helio4.bancol.avaluos.modelo.AvaluoRemate;
import com.helio4.bancol.avaluos.modelo.EstadoPorAsignar;
import com.helio4.bancol.avaluos.modelo.Usuario;
import com.helio4.bancol.avaluos.modelo.Usuario.DocumentoIdentificacion;
import com.helio4.bancol.avaluos.persistencia.AvaluoRemateRepository;
import com.helio4.bancol.avaluos.persistencia.EstadoAvaluoRepository;
import com.helio4.bancol.avaluos.persistencia.UsuarioRepository;

@Component(value = "repositoryAvaluoRemateService")
@Transactional(readOnly = true)
public class RepositoryAvaluoRemateService implements AvaluoRemateService {

	private static Logger log = LoggerFactory.getLogger( RepositoryAvaluoRemateService.class );

	@Autowired
	private AvaluoRemateRepository avaluoRemateRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private EstadoAvaluoRepository estadoAvaluoRepository;
	@Autowired
	private AvaluoEnsamblador avaluoEnsamblador;

    @Transactional
	@Override
	public AvaluoRemateDTO crear(AvaluoRemateDTO avaluoRemateDTO, UsuarioDTO usuarioDTO) {
		AvaluoRemate avaluoRemate;
		try {
            Usuario creador = usuarioRepository.findOne(
					new DocumentoIdentificacion(usuarioDTO.getTipoDocumentoIdentificacion(),
							usuarioDTO.getNumeroDocumento()));
            AvaluoRemate avaluo = avaluoEnsamblador
							.crearAvaluoRemate(avaluoRemateDTO);
            avaluo.setCreador(creador);
            avaluo.setFechaCreacion(avaluo.getEstado().getFechaEstado());
			avaluoRemate = avaluoRemateRepository.save(avaluo);
			EstadoPorAsignar estadoPorAsignar = (EstadoPorAsignar) avaluoRemate
					.getEstado();
			estadoPorAsignar.setUsuario(usuarioRepository
					.findOne(new DocumentoIdentificacion(usuarioDTO
							.getTipoDocumentoIdentificacion(), usuarioDTO
							.getNumeroDocumento())));
			estadoAvaluoRepository.save(estadoPorAsignar);
		} catch (ClienteNotFoundException e) {
			log.debug("ClienteNotFoundException: No se encontro el cliente en el avaluo: {}",
					avaluoRemateDTO);
			return null;
		} catch (EntidadNotFoundException e) {
			log.debug("EntidadNotFoundException: No se encontro la entidad en el avaluo: {}",
					avaluoRemateDTO);
			return null;
		} catch (UsuarioNotFoundException e) {
			log.debug("UsuarioNotFoundException: No se encontro el Usuario en el avaluo: {}",
					avaluoRemateDTO);
			return null;
		} catch (FotografiaNotFoundException e) {
			log.debug("FotografiaNotFoundException: No se encontro la fotografia en el avaluo: {}",
					avaluoRemateDTO);
			return null;
		} catch (AreaNotFoundException e) {
			log.debug("AreaNotFoundException: No se encontro el area en el avaluo: {}",
					avaluoRemateDTO);
			return null;
		} catch (TipoAvaluoNotFoundException e) {
			log.debug("TipoAvaluoNotFoundException: No se encontro el tipo de avaluo: {}",
					avaluoRemateDTO);
			return null;
		} catch (MetodoValuacionNotFoundException e) {
		    log.debug("MetodoValuacionNotFoundException: No se encontro un método de valuación: {}",
                    avaluoRemateDTO);
            return null;
        }catch (GarajeNotFoundException e) {
		    log.debug("GarajeNotFoundException: No se encontro un garaje en el valuo: {}",
                    avaluoRemateDTO);
            return null;
        }
		
		avaluoRemateDTO.setId(avaluoRemate.getId());
		return avaluoRemateDTO;
	}

    /**
     * Elimina el avaluo
     * @param avaluoId El identificador del avaluo que se va a eliminar.
     * @throws AvaluoNotFoundException Si el avaluo no existe.
     * @return El avaluo que se eliminó.
     */
    @Override
    @Transactional(rollbackFor = AvaluoNotFoundException.class)
    public AvaluoRemateDTO eliminar(Long avaluoId) throws AvaluoNotFoundException {
        AvaluoRemate avaluoRemate = avaluoRemateRepository.findOne(avaluoId);
        if (avaluoRemate == null) {
            throw new AvaluoNotFoundException();
        }
        avaluoRemateRepository.delete(avaluoRemate);
        return avaluoEnsamblador.escribirDTO(avaluoRemate);
    }

    /**
     * Encuentra todos los avaluos.
     * @return Una lista con todos los avaluos.
     */
    @Override
    @Transactional(readOnly = true)
    public List<AvaluoRemateDTO> encontrarTodos() {
        List<AvaluoRemateDTO> avaluoRemateTodos = new ArrayList<AvaluoRemateDTO>();
        for (AvaluoRemate avaluoRemate : avaluoRemateRepository.findAll()) {
            avaluoRemateTodos.add(avaluoEnsamblador.escribirDTO(avaluoRemate));
        }
        return avaluoRemateTodos;
    }

    /**
     * Encuentra el avaluo por el identificador.
     * @param id El identificador del avaluo que se quiere encontrar.
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public AvaluoRemateDTO encontrarPorId(Long id) {
        return avaluoEnsamblador.escribirDTO(avaluoRemateRepository.findOne(id));
    }

    /**
     * Actualiza la información de un avaluo.
     * @param actualizado La información del avaluo actualizado
     * @return El avaluuo actualizado
     * @throws AvaluoNotFoundException Si no hay un avaluo con el id dado.
     */
    @Transactional(rollbackFor = AvaluoNotFoundException.class)
    @Override
    public AvaluoRemateDTO actualizar(AvaluoRemateDTO actualizado)
        throws AvaluoNotFoundException {
        AvaluoRemate avaluoRemate = avaluoRemateRepository.findOne(actualizado.getId());
        if (avaluoRemate == null) {
            throw new AvaluoNotFoundException();
        }
        return actualizar(avaluoRemate, actualizado);
    }

    /**
     * Actualiza un avaluo hipotecario.
     * @param avaluoRemateDTO La información del nuevo avaluo.
     * @param usuarioDTO Usuario que crea el avaluo.
     * @param avaluoRemate El avaluo que se va a actualizar.
     * @return El valuo creado
     * @throws AvaluoNotFoundException
     */
    @Transactional(rollbackFor = AvaluoNotFoundException.class)
    @Override
    public AvaluoRemateDTO actualizar(AvaluoRemate avaluoRemate,
            AvaluoRemateDTO actualizado) throws AvaluoNotFoundException {
        try {
            avaluoEnsamblador.actualizarAvaluoRemate(avaluoRemate, actualizado);
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
        } catch (MetodoValuacionNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FormatoInformeNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DivipolaNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (GarajeNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return actualizado;
    }
}
