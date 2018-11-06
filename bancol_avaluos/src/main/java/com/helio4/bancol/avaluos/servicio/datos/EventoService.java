package com.helio4.bancol.avaluos.servicio.datos;

import java.util.Date;
import java.util.List;

import com.helio4.bancol.avaluos.servicio.excepciones.EventoNotFoundException;
import org.springframework.stereotype.Service;

import com.helio4.bancol.avaluos.dto.AvaluoDTO;
import com.helio4.bancol.avaluos.dto.EventoDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;

@Service
public interface EventoService {

	/**
	 * Crea un nuevo evento.
	 * @param eventoDTO La información del nuevo evento.
	 * @return El valuo creado
	 */
	public EventoDTO crear(EventoDTO eventoDTO);
	
	/**
	 * Elimina el evento
	 * @param eventoId El identificador del evento que se va a eliminar.
	 * @throws EventoNotFoundException Si el evento no existe.
	 * @return El evento que se eliminó.
	 */
	public EventoDTO eliminar(Long eventoId) throws EventoNotFoundException;
	
	/**
	 * Encuentra todos los eventos.
	 * @return Una lista con todos los eventos.
	 */
	public List<EventoDTO> encontrarTodos();
	
	/**
	 * Encuentra el evento por el identificador.
	 * @param id El identificador del evento que se quiere encontrar.
	 * @return
	 */
	public EventoDTO encontrarPorId(Long id);
	
	public List<UsuarioDTO> buscarUsuariosEventosCercanos(Date fechaInicio, Date fechaFinal);
	
	/**
	 * Actualiza la información de un evento.
	 * @param actualizado La información del evento actualizado
	 * @return El avaluuo actualizado
	 * @throws EventoNotFoundException Si no hay un evento con el id dado.
	 */
	public EventoDTO actualizar(EventoDTO actualizado) throws EventoNotFoundException;
	
	public List<EventoDTO> encontrarEventosUsuario(UsuarioDTO usuarioDTO, AvaluoDTO avaluo);
	
	public List<EventoDTO> encontrarEventosUsuarios(List<UsuarioDTO> usuarios);

	public int contarEventosPorAvaluo(Long avaluoId,
            Integer tipoDocumentoPerito, Long numeroDocumentoPerito);

    EventoDTO encontrarCitaReprogramar(Long avaluoId,
            Integer tipoDocumentoPerito, Long numeroDocumentoPerito);
    
    EventoDTO encontrarUltimoEventoAvaluo(Long avaluoId);

    List<EventoDTO> encontrarEventosUsuario(UsuarioDTO usuarioDTO);

}
