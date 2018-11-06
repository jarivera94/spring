package com.helio4.bancol.avaluos.ensamblador;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.ClienteDTO;
import com.helio4.bancol.avaluos.modelo.Cliente;
import com.helio4.bancol.avaluos.modelo.Divipola;
import com.helio4.bancol.avaluos.modelo.Segmento;
import com.helio4.bancol.avaluos.modelo.Usuario.DocumentoIdentificacion;
import com.helio4.bancol.avaluos.persistencia.ClienteRepository;
import com.helio4.bancol.avaluos.persistencia.DivipolaRepository;
import com.helio4.bancol.avaluos.persistencia.SegmentoRepository;
import com.helio4.bancol.avaluos.servicio.excepciones.ClienteNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.DivipolaNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.EntidadNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.SegmentoNotFoundException;

@Component
public class ClienteEnsamblador {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private DivipolaRepository divipolaRepository;

	@Autowired
	private SegmentoRepository segmentoRepository;

	@Autowired
	private SegmentoEnsamblador segmentoEnsamblador;

	@Autowired
	private DivipolaEnsamblador divipolaEnsamblador;

    public Cliente crearCliente(ClienteDTO clienteDTO)
            throws EntidadNotFoundException, DivipolaNotFoundException,
            SegmentoNotFoundException {
        Cliente cliente = new Cliente();
        cliente.setId(new DocumentoIdentificacion(clienteDTO
                .getTipoDocumentoIdentificacion(), clienteDTO
                .getNumeroDocumento()));
        cliente.setPrimerNombre(clienteDTO.getPrimerNombre());
        cliente.setSegundoNombre(clienteDTO.getSegundoNombre());
        cliente.setPrimerApellido(clienteDTO.getPrimerApellido());
        cliente.setSegundoApellido(clienteDTO.getSegundoApellido());
        cliente.setPaisOrigen(clienteDTO.getPaisOrigen());
        cliente.setRazonSocial(clienteDTO.getRazonSocial());
        cliente.setRegimen(clienteDTO.getRegimen());
        cliente.setCodigoCIIU(clienteDTO.getCodigoCIIU());
        cliente.setClasificacionFiscal(clienteDTO.getClasificacionFiscal());
        cliente.setTelefonoSolicitante(clienteDTO.getTelefonoSolicitante());
        cliente.setCelularSolicitante(clienteDTO.getCelularSolicitante());
        cliente.setCorreoElectronicoSolicitante(clienteDTO
                .getCorreoElectronicoSolicitante());
        cliente.setDireccionDeContactoSolicitante(clienteDTO
                .getDireccionDeContactoSolicitante());
        Divipola divipola = null;
        if (clienteDTO.getDivipola() != null) {
            divipola = divipolaRepository.findOne(clienteDTO.getDivipola()
                    .getId());
        }
        cliente.setDivipola(divipola);
        cliente.setEmpleadoEntidad(clienteDTO.isEmpleadoEntidad());
        cliente.setClienteExterior(clienteDTO.isClienteExterior());
        if (clienteDTO.getSegmento() != null
                && clienteDTO.getSegmento().getId() != null) {
            Segmento segmento = segmentoRepository.findOne(clienteDTO
                    .getSegmento().getId());
            if (segmento == null) {
                throw new SegmentoNotFoundException();
            }
            cliente.setSegmento(segmento);
        }
        return cliente;
    }
    
    @Transactional
	public Cliente actualizarCliente(Integer tipoDocumentoIdentificacion,Long numeroDocumento, ClienteDTO clienteDTO)
			throws ClienteNotFoundException, EntidadNotFoundException, DivipolaNotFoundException, SegmentoNotFoundException {
		Cliente cliente = clienteRepository.findOne(new DocumentoIdentificacion(tipoDocumentoIdentificacion, numeroDocumento));
		if (cliente ==  null) {
			throw new ClienteNotFoundException();
		}
		cliente.setId(new DocumentoIdentificacion(clienteDTO.getTipoDocumentoIdentificacion(),clienteDTO.getNumeroDocumento()));
		cliente.setPrimerNombre(clienteDTO.getPrimerNombre());
		cliente.setSegundoNombre(clienteDTO.getSegundoNombre());
		cliente.setPrimerApellido(clienteDTO.getPrimerApellido());
		cliente.setSegundoApellido(clienteDTO.getSegundoApellido());
		cliente.setPaisOrigen(clienteDTO.getPaisOrigen());
		cliente.setClasificacionFiscal(clienteDTO.getClasificacionFiscal());
		cliente.setRazonSocial(clienteDTO.getRazonSocial());
		cliente.setRegimen(clienteDTO.getRegimen());
		cliente.setCodigoCIIU(clienteDTO.getCodigoCIIU());
		cliente.setTelefonoSolicitante(clienteDTO.getTelefonoSolicitante());
		cliente.setCelularSolicitante(clienteDTO.getCelularSolicitante());
		cliente.setCorreoElectronicoSolicitante(clienteDTO.getCorreoElectronicoSolicitante());
		cliente.setDireccionDeContactoSolicitante(clienteDTO.getDireccionDeContactoSolicitante());

		if (clienteDTO.getDivipola() != null) {
			Divipola divipola = divipolaRepository.findOne(clienteDTO
					.getDivipola().getId());
			if (divipola == null) {
				throw new DivipolaNotFoundException();
			}
			cliente.setDivipola(divipola);
		}
		cliente.setEmpleadoEntidad(clienteDTO.isEmpleadoEntidad());
		cliente.setClienteExterior(clienteDTO.isClienteExterior());
		if (cliente.getSegmento() != null) {
            Segmento segmento = segmentoRepository.findOne(cliente
                    .getSegmento().getId());
            if (segmento == null) {
                throw new SegmentoNotFoundException();
            }
            cliente.setSegmento(segmento);
        }
		if( clienteDTO.getSegmento()!=null){
			cliente.setSegmento( this.segmentoEnsamblador.createSegmento( clienteDTO.getSegmento() ));
		}
		return cliente;
	}

	public ClienteDTO escribirDTO(Cliente cliente) {
		if (cliente ==  null) {
			return null;
		}
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setTipoDocumentoIdentificacion(cliente.getTipoDocumentoIdentificacion());
		clienteDTO.setNumeroDocumento(cliente.getNumeroDocumento());
		clienteDTO.setPrimerNombre(cliente.getPrimerNombre());
		clienteDTO.setSegundoNombre(cliente.getSegundoNombre());
		clienteDTO.setPrimerApellido(cliente.getPrimerApellido());
		clienteDTO.setSegundoApellido(cliente.getSegundoApellido());
		clienteDTO.setPaisOrigen(cliente.getPaisOrigen());
		clienteDTO.setClasificacionFiscal(cliente.getClasificacionFiscal());
		clienteDTO.setRazonSocial(cliente.getRazonSocial());
		clienteDTO.setRegimen(cliente.getRegimen());
		clienteDTO.setCodigoCIIU(cliente.getCodigoCIIU());
		clienteDTO.setTelefonoSolicitante(cliente.getTelefonoSolicitante());
		clienteDTO.setCelularSolicitante(cliente.getCelularSolicitante());
		clienteDTO.setCorreoElectronicoSolicitante(cliente.getCorreoElectronicoSolicitante());
		clienteDTO.setDireccionDeContactoSolicitante(cliente.getDireccionDeContactoSolicitante());
		if (cliente.getDivipola() != null) {
            clienteDTO.setDivipola(divipolaEnsamblador
                    .escribirDTO(cliente.getDivipola()));
        }
        clienteDTO.setEmpleadoEntidad(cliente.isEmpleadoEntidad());
		clienteDTO.setClienteExterior(cliente.isClienteExterior());
		clienteDTO.setSegmento(segmentoEnsamblador.escribirDTO(cliente.getSegmento()));
		return clienteDTO;
	}

	public List<ClienteDTO> escribirListaDTO(List<Cliente> clientes) {
		List<ClienteDTO> clienteDTOs = new ArrayList<ClienteDTO>();
		for (Cliente cliente:clientes) {
			clienteDTOs.add(escribirDTO(cliente));
		}
		return clienteDTOs;
	}

}
