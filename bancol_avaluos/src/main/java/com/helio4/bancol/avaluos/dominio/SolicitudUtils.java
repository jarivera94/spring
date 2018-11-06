package com.helio4.bancol.avaluos.dominio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.AvaluoDTO;
import com.helio4.bancol.avaluos.dto.EntidadDTO;
import com.helio4.bancol.avaluos.exception.GeneralFacesMessageException;
import com.helio4.bancol.avaluos.servicio.util.Constantes;
import com.helio4.bancol.avaluos.servicio.util.Constantes.Estado;

@Component
public class SolicitudUtils {

	@Autowired
	private AvaluoController avaluoController;

	private AvaluoDTO avaluoCambioGarantia;
	private boolean mostrarRepetirDatosCliente;

	public boolean comprobarEntidadBancolombia(EntidadDTO entidad) {

		return "Bancolombia".equals(entidad.getNombre());
	}

	public void validarCodigoExterno(AvaluoDTO avaluoDTO) throws GeneralFacesMessageException {
		if (avaluoDTO.getCodigoExterno() != null) {
			if (Constantes.AVALUO_CAMBIO_GARANTIA.equals(avaluoDTO.getMotivo())) {
				validarCambioGarantia(avaluoDTO);
			} else {
				if (avaluoController.verificarDuplicadoCodigoExterno(avaluoDTO.getCodigoExterno(),
						avaluoDTO.getEntidad().getId())) {
					throw new GeneralFacesMessageException("Codigo externo",
							"Ya existe una solicitud con el código externo ");
				}
			}
		}
	}

	public void validarIdSisgem(Long idSisgen) throws GeneralFacesMessageException {
		if (idSisgen != null) {

			if (avaluoController.verificarDuplicadoIdSisgen(idSisgen)) {
				throw new GeneralFacesMessageException("Id Sisgen", "Ya existe una solicitud con el Id sisgen");
			}

		}
	}

	public void validarCambioGarantia(AvaluoDTO avaluoDTO) throws GeneralFacesMessageException {
		if (Constantes.AVALUO_CAMBIO_GARANTIA.equals(avaluoDTO.getMotivo())) {
			// comprobar avaluo aprobado en BD
			// comprobar mismo cliente
			List<AvaluoDTO> cambiosGarantia = avaluoController.comprobarCambioGarantia(avaluoDTO.getCodigoExterno(),
					avaluoDTO.getEntidad().getId());

			if (cambiosGarantia != null && !cambiosGarantia.isEmpty()) {
				this.avaluoCambioGarantia = cambiosGarantia.get(0);
				if (this.avaluoCambioGarantia != null
						&& this.avaluoCambioGarantia.getEstado().getEstado().equals(Constantes.Estado.Aprobado)) {
					if (this.avaluoCambioGarantia.getCambioGarantia() != null
							&& this.avaluoCambioGarantia.getCambioGarantia() == 4) {

						throw new GeneralFacesMessageException("No es posible hacer un cambio de garantía",
								"Ya existen 4 cambios de garantía: " + avaluoDTO.getCodigoExterno() + " en Entidad "
										+ avaluoDTO.getEntidad().getNombre());
					} else {
						avaluoDTO.setCambioGarantia(this.avaluoCambioGarantia.getCambioGarantia() != null
								? this.avaluoCambioGarantia.getCambioGarantia() + 1 : 1);
						avaluoDTO.setCambioGarantiaAvaluo(this.avaluoCambioGarantia.getId());

						if (avaluoDTO.getCliente().getTipoDocumentoIdentificacion() != null
								&& avaluoDTO.getCliente().getNumeroDocumento() != null) {
							if (this.avaluoCambioGarantia.getCliente().getTipoDocumentoIdentificacion()
									.equals(avaluoDTO.getCliente().getTipoDocumentoIdentificacion())
									&& this.avaluoCambioGarantia.getCliente().getNumeroDocumento()
											.equals(avaluoDTO.getCliente().getNumeroDocumento())) {
							} else {

								throw new GeneralFacesMessageException("El cliente no puede ser diferente",
										"El cliente es diferente del avaluo aprobado: " + avaluoDTO.getCodigoExterno()
												+ " en " + avaluoDTO.getEntidad().getNombre());
							}
						} else {
							avaluoDTO.setCliente(this.avaluoController.buscarCliente(
									this.avaluoCambioGarantia.getCliente().getTipoDocumentoIdentificacion(),
									this.avaluoCambioGarantia.getCliente().getNumeroDocumento()));
							cambioTipoDocumento(avaluoDTO);

						}
					}
				} else {
					throw new GeneralFacesMessageException("No es posible hacer un cambio de garantía",
							"No existe un avaluo terminado " + avaluoDTO.getCodigoExterno() + " en "
									+ avaluoDTO.getEntidad().getNombre());

				}
			} else {

				throw new GeneralFacesMessageException("No es posible hacer un cambio de garantía",
						"No existe un avaluo terminado " + avaluoDTO.getCodigoExterno() + " en "
								+ avaluoDTO.getEntidad().getNombre());
			}
		} else {
		}
	}

	public void cambioTipoDocumento(AvaluoDTO avaluoDTO) {
		if (avaluoDTO != null) {
			if (avaluoDTO.getCliente() != null && avaluoDTO.getCliente().getTipoDocumentoIdentificacion() != null) {
				// si se selecciono NIT
				this.mostrarRepetirDatosCliente = false;
				if (avaluoDTO.getCliente().getTipoDocumentoIdentificacion() == 23) {
				} else {
					mostrarRepetirDatosCliente = true;
				}
			}
		}
	}

	public AvaluoDTO getAvaluoCambioGarantia() {
		return avaluoCambioGarantia;
	}

	public void setAvaluoCambioGarantia(AvaluoDTO avaluoCambioGarantia) {
		this.avaluoCambioGarantia = avaluoCambioGarantia;
	}

	public boolean isMostrarRepetirDatosCliente() {
		return mostrarRepetirDatosCliente;
	}

	public void setMostrarRepetirDatosCliente(boolean mostrarRepetirDatosCliente) {
		this.mostrarRepetirDatosCliente = mostrarRepetirDatosCliente;
	}

}
