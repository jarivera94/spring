package com.helio4.bancol.avaluos.dto;

public class AvaluoComercialDTO extends AvaluoDTO {

    private static final long serialVersionUID = -6538974294110045650L;

    public AvaluoComercialDTO() {
        super();
	}

    public AvaluoComercialDTO(AvaluoDTO avaluoDTO) {
		super(avaluoDTO.getId(), avaluoDTO.isSoloAvaluo(), avaluoDTO.getEntidad(),
				avaluoDTO.getCodigoExterno(), avaluoDTO.getTipoDeInmueble(),
				avaluoDTO.getTipoAvaluo(),	avaluoDTO.getObjetoDelAvaluo(),
				avaluoDTO.getMotivo(), avaluoDTO.getCambioGarantia(),
				avaluoDTO.getCambioGarantiaAvaluo(), avaluoDTO.getDivipola(),
				avaluoDTO.getSector(),	avaluoDTO.getConjunto(),
				avaluoDTO.getBarrio(), avaluoDTO.getTipoVia(),
				avaluoDTO.getNumeroVia(), avaluoDTO.getComplementoVia(),
				avaluoDTO.getNumeroViaGeneradora(),
				avaluoDTO.getComplementoViaGeneradora(),
				avaluoDTO.getPlaca(), avaluoDTO.getComplementoPlaca(),
				avaluoDTO.getAdicionalDireccion(), avaluoDTO.getDireccionInmueble(),
				avaluoDTO.getNombreRecibe(), avaluoDTO.getTelefonoRecibe(),
				avaluoDTO.getCorreoElectronicoRecibe(), avaluoDTO.getNombreAsesor(),
				avaluoDTO.getSucursalAsesor(), avaluoDTO.getCelularAsesor(),
				avaluoDTO.getCorreoElectronicoAsesor(),
				avaluoDTO.getTelefonoAsesor(),
				avaluoDTO.getObservacionesSolicitante(), avaluoDTO.getLatitud(),
				avaluoDTO.getLongitud(), avaluoDTO.getValorSolicitado(),
				avaluoDTO.getValorTotalAvaluo(), avaluoDTO.getValorUvr(),
				avaluoDTO.getValorAvaluoEnUvr(), avaluoDTO.getCalificacionGarantia(),
				avaluoDTO.getValorAsegurable(), avaluoDTO.getValorLiquidacion(),
				avaluoDTO.getAreas(), avaluoDTO.getFotografias(),
				avaluoDTO.getMetodosValuacion(),avaluoDTO.getGarajes(), avaluoDTO.getEstado(),
				avaluoDTO.getFechaEstado(), avaluoDTO.getCliente(),
				avaluoDTO.getPerito(), avaluoDTO.getFormatoInforme(),
				avaluoDTO.getValorHonorarios(), avaluoDTO.getGastosTranslado(), 
				avaluoDTO.getVereda(), avaluoDTO.getNombrePredio(), avaluoDTO.getIva(),
				avaluoDTO.getAreaTotal(), avaluoDTO.getMatriculaInmobiliariaPrincipal1(),avaluoDTO.getMatriculaInmobiliariaPrincipal2(), 
				avaluoDTO.getSemaforo(),avaluoDTO.getDescripcionMotivo(),
				avaluoDTO.getDireccionInmuebleInforme(), avaluoDTO.getDivipolaInforme(),
				avaluoDTO.getComplementoViaInforme(),  avaluoDTO.getComplementoViaGeneradoraInforme(),
				avaluoDTO.getTipoViaInforme(), avaluoDTO.getNumeroViaGeneradoraInforme(), 
				avaluoDTO.getPlacaInforme(),avaluoDTO.getNumeroViaInforme(), 
				avaluoDTO.getAdicionalDireccionInforme(), avaluoDTO.getTipoCredito(), avaluoDTO.getAvaluoSisgenId());
	}
}
