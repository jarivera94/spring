package com.helio4.bancol.avaluos.servicio;

import java.util.List;

import com.helio4.bancol.avaluos.servicio.datos.ClienteService;
import com.helio4.bancol.avaluos.servicio.excepciones.ClienteNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.DivipolaNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.EntidadNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.SegmentoNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.ClienteDTO;
import com.helio4.bancol.avaluos.ensamblador.ClienteEnsamblador;
import com.helio4.bancol.avaluos.modelo.Cliente;
import com.helio4.bancol.avaluos.modelo.Usuario.DocumentoIdentificacion;
import com.helio4.bancol.avaluos.persistencia.ClienteRepository;

@Component(value="repositoryClienteService")
@Transactional(readOnly = true)
public class RepositoryClienteService implements ClienteService {

	private static Logger log = LoggerFactory.getLogger( RepositoryClienteService.class );

	@Autowired
	private ClienteRepository clientRepository;

	@Autowired
	private ClienteEnsamblador clienteEnsamblador;

    @Transactional
    @Override
    public ClienteDTO crear(ClienteDTO clienteDTO)
            throws EntidadNotFoundException {
        Cliente cliente = null;
        try {
            cliente = clienteEnsamblador.crearCliente(clienteDTO);
        } catch (DivipolaNotFoundException e) {
            log.error("No se encontró el divipola del cliente, se debe crear antes del cliente",
                    e);
            return null;
        } catch (SegmentoNotFoundException e) {
            log.error("No se encontró el segmento del cliente, se debe crear antes del cliente",
                    e);
            return null;
        }
        clientRepository.save(cliente);
        return clienteDTO;
    }

	@Transactional(rollbackFor = ClienteNotFoundException.class)
	@Override
	public ClienteDTO eliminar(Integer tipoDocumentoIdentificacion, Long numeroDocumento) throws ClienteNotFoundException {
		DocumentoIdentificacion clienteId = new DocumentoIdentificacion(tipoDocumentoIdentificacion, numeroDocumento);
        log.debug("Borrando el cliente {}",
                clienteId);
		Cliente deleted = clientRepository.findOne(clienteId);
		if (deleted == null) {
			throw new ClienteNotFoundException();
		}
		clientRepository.delete(deleted);
		return clienteEnsamblador.escribirDTO(deleted);
	}

	@Transactional(readOnly = true)
	@Override
	public List<ClienteDTO> encontrarTodos() {
		return clienteEnsamblador.escribirListaDTO(clientRepository.findAll());
	}

	@Transactional(readOnly = true)
	@Override
	public ClienteDTO encontrarPorId(Integer tipoDocumentoIdentificacion,Long numeroDocumento) {
		return clienteEnsamblador.escribirDTO(
				clientRepository.findOne(
						new DocumentoIdentificacion(tipoDocumentoIdentificacion, numeroDocumento)));
	}

	@Transactional(readOnly = true)
	@Override
	public Long numeroAvaluosPorCliente(Integer tipoDocumentoIdentificacion, Long numeroDocumento) {
		DocumentoIdentificacion clienteId = new DocumentoIdentificacion(tipoDocumentoIdentificacion, numeroDocumento);
        log.debug("Encontrando el número de avaluos del cliente con id:  {}",
                clienteId);
		return clientRepository.numeroAvaluosPorUsuario(clienteId);
	}

	@Transactional(rollbackFor = ClienteNotFoundException.class)
	@Override
	public ClienteDTO actualizar(ClienteDTO actualizado)
			throws ClienteNotFoundException, EntidadNotFoundException {
		try {
			clienteEnsamblador.actualizarCliente(actualizado.getTipoDocumentoIdentificacion(), actualizado.getNumeroDocumento(), actualizado);
		} catch (DivipolaNotFoundException e) {
			log.error("No se encontró el divipola del cliente se debe crear antes del cliente",
                    e);
		} catch (SegmentoNotFoundException e) {
		    log.error("No se encontró el segmento del cliente se debe crear antes del cliente",
                    e);
        }
		return actualizado;
	}

	@Transactional(readOnly = true)
	@Override
	public ClienteDTO encontrarPorNumeroDocumento(Integer tipoDocumentoIdentificacion, Long numeroDocumento) {
		return clienteEnsamblador.escribirDTO(
				clientRepository.encontrarPorNumeroDocumento(
						new DocumentoIdentificacion(tipoDocumentoIdentificacion, numeroDocumento)));
	}
	
	@Transactional(readOnly = true)
	@Override
	public ClienteDTO encontrarPorNumeroDocumento(Long numeroDocumento) {
		return clienteEnsamblador.escribirDTO(
				clientRepository.encontrarPorNumeroDocumento(numeroDocumento));
	}

	@Transactional(readOnly = true)
	@Override
	public ClienteDTO encontrarClienteConAvaluos(Integer tipoDocumentoIdentificacion, Long numeroDocumento) {
		return clienteEnsamblador.escribirDTO(
				clientRepository.encontrarClienteConAvaluos(
						new DocumentoIdentificacion(tipoDocumentoIdentificacion, numeroDocumento)));
	}
	
	@Transactional(rollbackFor = ClienteNotFoundException.class)
	public void actualizar(int tipoDocumentoNuevo,Long numeroDocumentoNuevo,int tipoDocumentoAntiguo,Long numeroDocumentoAntiguo){
		this.clientRepository.actualizarCliente( numeroDocumentoNuevo, numeroDocumentoAntiguo, tipoDocumentoNuevo,tipoDocumentoAntiguo);
		
	}

}
