package com.helio4.bancol.avaluos.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

public class AvaluoHipotecarioDTO extends AvaluoDTO implements Serializable, Cloneable{

	private static final long serialVersionUID = 6931884791726351451L;

	public AvaluoHipotecarioDTO() {
	}

	public AvaluoHipotecarioDTO(AvaluoDTO avaluoDTO) {
		super(avaluoDTO.getId(), avaluoDTO.isSoloAvaluo(),
				avaluoDTO.getEntidad(), avaluoDTO.getCodigoExterno(),
				avaluoDTO.getTipoDeInmueble(),
				avaluoDTO.getTipoAvaluo(),	avaluoDTO.getObjetoDelAvaluo(),
				avaluoDTO.getMotivo(), avaluoDTO.getCambioGarantia(),
				avaluoDTO.getCambioGarantiaAvaluo(), avaluoDTO.getDivipola(),
				avaluoDTO.getSector(), avaluoDTO.getConjunto(), avaluoDTO.getBarrio(),
                avaluoDTO.getTipoVia(),	avaluoDTO.getNumeroVia(),
                avaluoDTO.getComplementoVia(),
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
				avaluoDTO.getVereda(), avaluoDTO.getNombrePredio(),
				avaluoDTO.getIva(),avaluoDTO.getAreaTotal(), avaluoDTO.getMatriculaInmobiliariaPrincipal1(),avaluoDTO.getMatriculaInmobiliariaPrincipal2(),
				avaluoDTO.getSemaforo(),avaluoDTO.getDescripcionMotivo(),
				avaluoDTO.getDireccionInmuebleInforme(), avaluoDTO.getDivipolaInforme(),
				avaluoDTO.getComplementoViaInforme(),  avaluoDTO.getComplementoViaGeneradoraInforme(),
				avaluoDTO.getTipoViaInforme(), avaluoDTO.getNumeroViaGeneradoraInforme(), 
				avaluoDTO.getPlacaInforme(),avaluoDTO.getNumeroViaInforme(), 
				avaluoDTO.getAdicionalDireccionInforme(),avaluoDTO.getTipoCredito(), avaluoDTO.getAvaluoSisgenId());
	}

    public AvaluoHipotecarioDTO(Long id, boolean soloAvaluo, EntidadDTO entidad, String codigoExterno,
            TipoInmuebleDTO tipoDeInmueble, TipoAvaluoDTO tipoAvaluo,
            ObjetoAvaluoDTO objetoDelAvaluo, Integer motivo,
            Integer cambioGarantia, DivipolaDTO divipola,
            Long cambioGarantiaAvaluo, ListaSector sector,
            String conjunto, String barrio, String tipoVia,
            String numeroVia, String complementoVia,
            String numeroViaGeneradora, String complementoViaGeneradora,
            String placa, String complementoPlaca, String adicionalDireccion,
            String direccionInmueble, String nombreRecibe,
            String telefonoRecibe, String correoElectronicoRecibe,
            String nombreAsesor, String sucursalAsesor, String celularAsesor,
            String correoElectronicoAsesor, String telefonoAsesor,
            String observacionesSolicitante, BigDecimal latitud,
            BigDecimal longitud, BigDecimal valorSolicitado,
            BigDecimal valorTotalAvaluo, BigDecimal valorUvr,
            BigDecimal valorAvaluoEnUvr, ListaCalificacionGarantia calificacionGarantia,
            BigDecimal valorAsegurable, BigDecimal valorLiquidacion,
            Set<AreaDTO> areas, Set<FotografiaDTO> fotografias,
            List<MetodoValuacionDTO> metodosValuacion,List<GarajeDTO> garajes, EstadoAvaluoDTO estado,
            Date fechaEstado, ClienteDTO cliente,
            UsuarioDTO perito, FormatoInformeDTO formatoInforme, 
            BigDecimal valorHonorarios,BigDecimal gastosTranslado,
            String vereda, String nombrePredio, BigDecimal iva,
            BigDecimal areaTotal, String matriculaInmobiliariaPrincipal1, String matriculaInmobiliariaPrincipal2, SemaforoDTO semaforo,
            String descripcionMotivo,
            String direccionInmuebleInforme, DivipolaDTO divipolaInforme, 
            String complementoViaInforme, String complementoViaGeneradoraInforme,
            String tipoViaInforme,String numeroViaGeneradoraInforme, String  placaInforme, 
            String numeroViaInforme,String adicionalDireccionInforme,String tipoCredito, Long avaluoSisgenId) {
		super(id, soloAvaluo, entidad, codigoExterno,
				tipoDeInmueble,
				tipoAvaluo,	objetoDelAvaluo,
				motivo, cambioGarantia, cambioGarantiaAvaluo,
                divipola, sector,
                conjunto, barrio,
                tipoVia,	numeroVia,
                complementoVia,
				numeroViaGeneradora,
				complementoViaGeneradora,
				placa, complementoPlaca,
				adicionalDireccion, direccionInmueble,
				nombreRecibe, telefonoRecibe,
				correoElectronicoRecibe, nombreAsesor,
				sucursalAsesor, celularAsesor,
				correoElectronicoAsesor,
				telefonoAsesor,
				observacionesSolicitante, latitud,
				longitud, valorSolicitado,
				valorTotalAvaluo, valorUvr,
				valorAvaluoEnUvr, calificacionGarantia,
				valorAsegurable, valorLiquidacion,
				areas, fotografias,
				metodosValuacion, garajes,estado,
				fechaEstado, cliente,
				perito, formatoInforme,valorHonorarios,gastosTranslado,
				vereda, nombrePredio, iva,
				areaTotal,matriculaInmobiliariaPrincipal1,matriculaInmobiliariaPrincipal2,semaforo,descripcionMotivo,
				direccionInmuebleInforme,divipolaInforme, 
	            complementoViaInforme,complementoViaGeneradoraInforme,
	            tipoViaInforme,numeroViaGeneradoraInforme,placaInforme, 
	            numeroViaInforme,adicionalDireccionInforme,tipoCredito, avaluoSisgenId);
    }

	public enum MetodologiaValuatoria {
		Comparacion ("Comparación de  Mercado", 21),
		Capitalizacion ("Capitalización de ingresos", 22),
		MetodoReposicion ("Método Reposición", 23),
		MetodoResidual ("Metodo Residual", 24),
		ComparacionCapitalizacion ("Comparación - Capitalización", 25),
		ComparacionReposicion ("Comparación - Reposición", 26),
		ComparacionResidualRural ("Comparación - Residual Rural", 41),
		CapitalizacionReposicionUrbano ("Capitalización - Reposición Urbano", 42),
		CapitalizacionResidual ("Capitalización - Residual", 43),
		ReposicionResidual ("Reposición - Residual", 44),
		CompCapitRepos ("Comp.-Capit-Repos", 45),
		CompReposResidual ("Comp.-Repos.-Residual", 61),
		CompCapitResidual ("Comp.-Capit. - Residual", 62),
		CompCapitReposResidRural ("Comp.-Capit.-Repos.-Resid.l Rural", 63);

		private final String value;
		private final int key;

		private MetodologiaValuatoria(String value, int key) {
			this.value = value;
			this.key = key;
		}

		public String value() {
			return value;
		}

		public int key() {
			return key;
		}
	}

    @Override
    public Object clone() throws CloneNotSupportedException {
    	Set<AreaDTO> areas =   new HashSet<AreaDTO>();
    	for (AreaDTO area: this.getAreas()) {
    		AreaDTO areaClone = (AreaDTO) area.clone();
    		areas.add(areaClone);
    	}
    	List<GarajeDTO> garajes = new ArrayList<GarajeDTO>();
    	for (GarajeDTO garaje: this.getGarajes()) {
    		GarajeDTO garajeClone = (GarajeDTO) garaje.clone();
    		garajes.add(garajeClone);
    	}
    	List<MetodoValuacionDTO> metodos = null;
    	if(this.getMetodosValuacion() != null) {
    		metodos =  new ArrayList<MetodoValuacionDTO>();
    		for (MetodoValuacionDTO metodo:getMetodosValuacion()) {
        		MetodoValuacionDTO clone = null;
        		if (MetodoValuacionDTO.class.equals(metodo.getClass())) {
        			clone = (MetodoValuacionDTO) metodo.clone();
        		}
        		else if (ComparacionMercadoNOPHDTO.class.equals(metodo.getClass())) {
        			clone = (ComparacionMercadoNOPHDTO) metodo.clone();
        		}
        		else if (ComparacionMercadoPHDTO.class.equals(metodo.getClass())) {
        			clone = (ComparacionMercadoPHDTO) metodo.clone();
        		}
        		metodos.add(clone);
        	}
    	}
        TipoInmuebleDTO tipoDeInmuebleClone = getTipoDeInmueble() != null ?
            (TipoInmuebleDTO) getTipoDeInmueble().clone() : null;
    	
        AvaluoHipotecarioDTO clone = new AvaluoHipotecarioDTO(getId(), isSoloAvaluo(),
                (EntidadDTO) getEntidad().clone(), getCodigoExterno(),
                tipoDeInmuebleClone,
                (TipoAvaluoDTO) getTipoAvaluo().clone(),
                (ObjetoAvaluoDTO) getObjetoDelAvaluo().clone(), getMotivo(),
                getCambioGarantia(), (DivipolaDTO) getDivipola().clone(),
                getCambioGarantiaAvaluo(), getSector(), getConjunto(), getBarrio(), getTipoVia(),
                getNumeroVia(), getComplementoVia(), getNumeroViaGeneradora(),
                getComplementoViaGeneradora(), getPlaca(),
                getComplementoPlaca(), getAdicionalDireccion(),
                getDireccionInmueble(), getNombreRecibe(), getTelefonoRecibe(),
                getCorreoElectronicoRecibe(), getNombreAsesor(),
                getSucursalAsesor(), getCelularAsesor(),
                getCorreoElectronicoAsesor(), getTelefonoAsesor(),
                getObservacionesSolicitante(), getLatitud(), getLongitud(),
                getValorSolicitado(), getValorTotalAvaluo(), getValorUvr(),
                getValorAvaluoEnUvr(), getCalificacionGarantia(),
                getValorAsegurable(), getValorLiquidacion(),
                (areas !=null ? ImmutableSet.copyOf(areas) : null),
                getFotografias(), metodos, garajes, 
                getEstado(),getFechaEstado(), (ClienteDTO)getCliente().clone(), getPerito(),
                (FormatoInformeDTO) getFormatoInforme().clone(),  
                this.getValorHonorarios(), this.getGastosTranslado(), 
                this.getVereda(), this.getNombrePredio(), this.getIva() ,
                this.getAreaTotal() , this.getMatriculaInmobiliariaPrincipal1(),this.getMatriculaInmobiliariaPrincipal2(), this.getSemaforo(),
                this.getDescripcionMotivo(),
                this.getDireccionInmuebleInforme(), this.getDivipolaInforme(),this.getComplementoViaInforme(),
                this.getComplementoViaGeneradoraInforme(),this.getTipoViaInforme(), this.getNumeroViaGeneradoraInforme(), 
                this.getPlacaInforme(), this.getNumeroViaInforme(), this.getAdicionalDireccionInforme(), this.getTipoCredito(), this.getAvaluoSisgenId());
        return clone;
    }

}
