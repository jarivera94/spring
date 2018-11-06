package com.helio4.bancol.avaluos.modelo;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.List;

import javax.persistence.*;

@Entity
@Cacheable(true)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "bancolEntityCache")
public class Divipola {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="divipola_id")
	private Long id;
	@Column(nullable=false)
	private String municipio;
	@Column(name="centro_poblado", nullable=false)
	private String centroPoblado;
	@Column(name="tipo_centro_poblado", nullable=false)
	private String tipoCentroPoblado;
	@Column(nullable=false)
	private String departamento;
	@Column(name="codigo_departamento", nullable=false)
	private int codigoDepartamento;
	@Column(name="codigo_municipio", nullable=false)
	private int codigoMunicipio;
	@Column(name="codigo_centro_poblado", nullable=false)
	private int codigoCentroPoblado;
	@Column(name="departamento_bua_id", nullable=false)
	private int departamentoBUA;
	@Column(name="municipio_bua_id", nullable=false)
	private int municipioBUA;
	@Column(name="capital")
	private Boolean capital;
	
	@Column(name="departamento_id_tinsa", nullable=false)
	private Integer departamentoIdTinsa;
	
	@Column(name="municipio_id_tinsa", nullable=false)
	private Integer municipioIdTinsa;
	

	@ManyToOne
	@JoinColumn(name = "regional", nullable = false)
	private Regional regional;
	
	@ManyToMany(mappedBy="divipolas", fetch=FetchType.LAZY)
	private List<Usuario> usuarios;

    public Divipola() {
        super();
    }

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getCentroPoblado() {
		return centroPoblado;
	}
	public void setCentroPoblado(String centroPoblado) {
		this.centroPoblado = centroPoblado;
	}
	public String getTipoCentroPoblado() {
		return tipoCentroPoblado;
	}
	public void setTipoCentroPoblado(String tipoCentroPoblado) {
		this.tipoCentroPoblado = tipoCentroPoblado;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public int getCodigoDepartamento() {
		return codigoDepartamento;
	}
	public void setCodigoDepartamento(int codigoDepartamento) {
		this.codigoDepartamento = codigoDepartamento;
	}
	public int getCodigoMunicipio() {
		return codigoMunicipio;
	}
	public void setCodigoMunicipio(int codigoMunicipio) {
		this.codigoMunicipio = codigoMunicipio;
	}
	public int getCodigoCentroPoblado() {
		return codigoCentroPoblado;
	}
	public void setCodigoCentroPoblado(int codigoCentroPoblado) {
		this.codigoCentroPoblado = codigoCentroPoblado;
	}
	public int getDepartamentoBUA() {
		return departamentoBUA;
	}
	public void setDepartamentoBUA(int departamentoBUA) {
		this.departamentoBUA = departamentoBUA;
	}
	public int getMunicipioBUA() {
		return municipioBUA;
	}
	public void setMunicipioBUA(int municipioBUA) {
		this.municipioBUA = municipioBUA;
	}
	public Regional getRegional() {
		return regional;
	}
	public void setRegional(Regional regional) {
		this.regional = regional;
	}
	public Boolean getCapital() {
		return capital;
	}
	public void setCapital(Boolean capital) {
		this.capital = capital;
	}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((capital == null) ? 0 : capital.hashCode());
        result = prime * result
                + ((centroPoblado == null) ? 0 : centroPoblado.hashCode());
        result = prime * result + codigoCentroPoblado;
        result = prime * result + codigoDepartamento;
        result = prime * result + codigoMunicipio;
        result = prime * result
                + ((departamento == null) ? 0 : departamento.hashCode());
        result = prime * result + departamentoBUA;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result
                + ((municipio == null) ? 0 : municipio.hashCode());
        result = prime * result + municipioBUA;
        result = prime * result
                + ((regional == null) ? 0 : regional.hashCode());
        result = prime
                * result
                + ((tipoCentroPoblado == null) ? 0 : tipoCentroPoblado
                        .hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Divipola other = (Divipola) obj;
        if (capital == null) {
            if (other.capital != null)
                return false;
        } else if (!capital.equals(other.capital))
            return false;
        if (centroPoblado == null) {
            if (other.centroPoblado != null)
                return false;
        } else if (!centroPoblado.equals(other.centroPoblado))
            return false;
        if (codigoCentroPoblado != other.codigoCentroPoblado)
            return false;
        if (codigoDepartamento != other.codigoDepartamento)
            return false;
        if (codigoMunicipio != other.codigoMunicipio)
            return false;
        if (departamento == null) {
            if (other.departamento != null)
                return false;
        } else if (!departamento.equals(other.departamento))
            return false;
        if (departamentoBUA != other.departamentoBUA)
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (municipio == null) {
            if (other.municipio != null)
                return false;
        } else if (!municipio.equals(other.municipio))
            return false;
        if (municipioBUA != other.municipioBUA)
            return false;
        if (regional == null) {
            if (other.regional != null)
                return false;
        } else if (!regional.equals(other.regional))
            return false;
        if (tipoCentroPoblado == null) {
            if (other.tipoCentroPoblado != null)
                return false;
        } else if (!tipoCentroPoblado.equals(other.tipoCentroPoblado))
            return false;
        return true;
    }

	/**
	 * @return the usuarios
	 */
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	/**
	 * @param usuarios the usuarios to set
	 */
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Integer getDepartamentoIdTinsa() {
		return departamentoIdTinsa;
	}

	public void setDepartamentoIdTinsa(Integer departamentoIdTinsa) {
		this.departamentoIdTinsa = departamentoIdTinsa;
	}

	public Integer getMunicipioIdTinsa() {
		return municipioIdTinsa;
	}

	public void setMunicipioIdTinsa(Integer municipioIdTinsa) {
		this.municipioIdTinsa = municipioIdTinsa;
	}
	
}
