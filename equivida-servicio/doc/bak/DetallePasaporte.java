package com.equivida.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "detalle_pasaporte")
public class DetallePasaporte implements Serializable {

	private static final long serialVersionUID = 7290591891739396044L;

	@Id
	@Basic(optional = false)
	@Column(name = "sec_detalle_pasaporte")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer secDetallePasaporte;

	@Column(name = "SEC_PERSONA_NATURAL")
	private Integer secPersonaNatural;

	@JoinColumn(name = "sec_tipo_visa", referencedColumnName = "sec_tipo_visa")
	@ManyToOne(optional = false)
	private TipoVisa tipoVisa;

	@Temporal(TemporalType.DATE)
	@Column(name = "FCH_EXPEDICION")
	private Date fechaExpedicion;

	@Temporal(TemporalType.DATE)
	@Column(name = "FCH_CADUCIDAD")
	private Date fechaCaducidad;

	@Temporal(TemporalType.DATE)
	@Column(name = "FCH_ENTRADA")
	private Date fechaEntrada;

	@Column(name = "COD_EXTRANJERIA", length = 24)
	private String codExtranjeria;

	public TipoVisa getTipoVisa() {
		return tipoVisa;
	}

	public void setTipoVisa(TipoVisa tipoVisa) {
		this.tipoVisa = tipoVisa;
	}

	public Date getFechaExpedicion() {
		return fechaExpedicion;
	}

	public void setFechaExpedicion(Date fechaExpedicion) {
		this.fechaExpedicion = fechaExpedicion;
	}

	public Date getFechaCaducidad() {
		return fechaCaducidad;
	}

	public void setFechaCaducidad(Date fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Integer getSecDetallePasaporte() {
		return secDetallePasaporte;
	}

	public void setSecDetallePasaporte(Integer secDetallePasaporte) {
		this.secDetallePasaporte = secDetallePasaporte;
	}

	public Integer getSecPersonaNatural() {
		return secPersonaNatural;
	}

	public void setSecPersonaNatural(Integer secPersonaNatural) {
		this.secPersonaNatural = secPersonaNatural;
	}

	public String getCodExtranjeria() {
		return codExtranjeria;
	}

	public void setCodExtranjeria(String codExtranjeria) {
		this.codExtranjeria = codExtranjeria;
	}
}
