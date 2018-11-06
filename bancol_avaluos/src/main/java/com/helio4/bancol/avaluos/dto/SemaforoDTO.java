package com.helio4.bancol.avaluos.dto;

import java.util.Date;

import javax.resource.spi.IllegalStateException;

import com.helio4.bancol.avaluos.servicio.util.Constantes;
import com.helio4.bancol.avaluos.servicio.util.DiasHabilesUtils;

public class SemaforoDTO {

	private Date inicioSemaforo;
	private Long duracionPausa;
	private Date finSemaforo;
	private Long duracionVerde;
	private Long duracionAmarillo;
	private Long duraccionRojo;
	private String estadoSemaforo;

	public SemaforoDTO(){}
	/**
	 * Constructor usado cuando un avalúo NO esta aprobado.
	 * 
	 * @param inicioSemaforo
	 * @param duracionPausa
	 * @param duracionVerde
	 * @param duracionAmarillo
	 * @param duraccionRojo
	 * */
	public SemaforoDTO(Date inicioSemaforo, Long duracionPausa,
			Long duracionVerde, Long duracionAmarillo, Long duraccionRojo) {
		this.inicioSemaforo = inicioSemaforo;
		this.duracionVerde = duracionVerde;
		this.duracionAmarillo = duracionAmarillo;
		this.duraccionRojo = duraccionRojo;
		if (duracionPausa == null) {
			duracionPausa = 0l;
		}
		this.duracionPausa = duracionPausa;
		Date finalSemaforo =new Date(System.currentTimeMillis());
		Long diasNoHabiles = DiasHabilesUtils.getDiasNoHabilesMS(inicioSemaforo, finalSemaforo,Constantes.HORAS_LABORALES);
		Long tiempoBancolActual = System.currentTimeMillis()
				- inicioSemaforo.getTime() - duracionPausa -diasNoHabiles;
		
		estadoSemaforo = tiempoBancolActual <= duracionVerde ? "verde_color"
				: tiempoBancolActual <= (duracionAmarillo + duracionVerde) ? "amarillo_color"
						: "rojo_color";
	}
	/**
	 * Constructor usado cuando un avalúo esta aprobado
	 * 
	 * @param inicioSemaforo 
	 * @param finSemaforo
	 * @param duracionPausa
	 * @param duracionVerde
	 * @param duracionAmarillo
	 * @param duracionRojo
	 * */
	public SemaforoDTO(Date inicioSemaforo, Date finSemaforo,
			Long duracionPausa, Long duracionVerde, Long duracionAmarillo,
			Long duraccionRojo) {
		this.inicioSemaforo = inicioSemaforo;
		this.duracionVerde = duracionVerde;
		this.duracionAmarillo = duracionAmarillo;
		this.duraccionRojo = duraccionRojo;
		if (duracionPausa == null) {
			duracionPausa = 0l;
		} else {
			this.duracionPausa = duracionPausa;
		}
		//fechainicial = inicioSemaforo: se crea la solicitud
		//fecha final = esta aprobado ? fechaAprobado :  fecha actual 
		Long diasNoHabiles = DiasHabilesUtils.getDiasNoHabilesMS(inicioSemaforo, finSemaforo,Constantes.HORAS_LABORALES);
		Long tiempoBancol = finSemaforo.getTime() - inicioSemaforo.getTime()
				- duracionPausa - diasNoHabiles;
		estadoSemaforo = tiempoBancol <= duracionVerde ? "verde_color"
				: tiempoBancol <= (duracionAmarillo + duracionVerde) ? "amarillo_color"
						: "rojo_color";
	}

	public void actualizarEstado(Long duracionPausa)
			throws IllegalStateException {
		if (finSemaforo != null) {
			throw new IllegalStateException("El semaforo ya finalizo");
		}
		this.duracionPausa = duracionPausa;
		Long tiempoBancolActual = System.currentTimeMillis()
				- inicioSemaforo.getTime() - duracionPausa;
		estadoSemaforo = tiempoBancolActual <= duracionVerde ? "verde_color"
				: tiempoBancolActual <= duracionAmarillo ? "amarillo_color"
						: "rojo_color";
	}

	public void pausarSemaforo() {
		estadoSemaforo = "gris_no";
	}

	public Date getInicioSemaforo() {
		return inicioSemaforo;
	}

	public void setInicioSemaforo(Date inicioSemaforo) {
		this.inicioSemaforo = inicioSemaforo;
	}

	public long getDuracionPausa() {
		return duracionPausa;
	}

	public void setDuracionPausa(long duracionPausa) {
		this.duracionPausa = duracionPausa;
	}

	public Date getFinSemaforo() {
		return finSemaforo;
	}

	public void setFinSemaforo(Date finSemaforo) {
		this.finSemaforo = finSemaforo;
	}

	public Long getDuracionVerde() {
		return duracionVerde;
	}

	public void setDuracionVerde(Long duracionVerde) {
		this.duracionVerde = duracionVerde;
	}

	public Long getDuracionAmarillo() {
		return duracionAmarillo;
	}

	public void setDuracionAmarillo(Long duracionAmarillo) {
		this.duracionAmarillo = duracionAmarillo;
	}

	public Long getDuraccionRojo() {
		return duraccionRojo;
	}

	public void setDuraccionRojo(Long duraccionRojo) {
		this.duraccionRojo = duraccionRojo;
	}

	public void setDuracionPausa(Long duracionPausa) {
		this.duracionPausa = duracionPausa;
	}

	public String getEstadoSemaforo() {
		return estadoSemaforo;
	}

	public void setEstadoSemaforo(String estadoSemaforo) {
		this.estadoSemaforo = estadoSemaforo;
	}

	/**
	 * Calcula el tiempo restante de este semaforo en milisegundos
	 * 
	 * @return el tiempo restante de este semaforo en milisegundos
	 */
	public long tiempoRestante() {
		if (finSemaforo != null) {
			return 0;
		} else {
			return System.currentTimeMillis() - inicioSemaforo.getTime()
					+ duracionPausa;
		}
	}

}
