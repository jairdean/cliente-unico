/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
import javax.persistence.Transient;

import com.equivida.constant.EstadoRcsEnum;
import com.equivida.constant.PersonaRechazoListaReservadaEnum;

/**
 * Entity que guarda el proceso si existe una persona en listas reservadas
 * 
 * @author Daniel Cardenas
 */
@Entity
@Table(name = "RCS")
public class Rcs implements Serializable {

	private static final long serialVersionUID = -3568904235315820659L;

	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SEC_PERSONA_CONTROL")
	private Integer secPersonaControl;

	@Basic(optional = false)
	@JoinColumn(name = "COD_TIPO_IDENTIFICACION", referencedColumnName = "COD_TIPO_IDENTIFICACION")
	@ManyToOne(optional = false)
	private TipoIdentificacion tipoIdentificacion;

	@Basic(optional = false)
	@Column(name = "IDENTIFICACION")
	private String identificacion;

	@Basic(optional = false)
	@Column(name = "ID_USUARIO")
	private String idUsuario;

	@Column(name = "TS_CREACION", updatable = false, insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date tsCreacion;// fecha de consulta del Servicio Web

	@Column(name = "CONTENIDO_XML", columnDefinition = "xml")
	private String contenidoXml;

	@Column(name = "COD_CONTROL", columnDefinition = "char(1)")
	private char codControl;// para saber si es P: persona C: conyuge

	@Column(name = "COD_ESTADO", columnDefinition = "char(1)")
	private char codEstado;// para determinar el estado N: nuevo M:
							// modificación.

	@Column(name = "DENOMINACION", length = 64)
	private String denominacion;// nombres y apellidos de la persona que se esta
								// ingresando en cliente unico (no puede ser el
								// conyuge)

	@Basic(optional = false)
	@Column(name = "TS_MODIFICACION", updatable = false, insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date tsModificacion;

	@Basic(optional = true)
	@JoinColumn(name = "COD_TIPO_IDENTIFICACION_C", referencedColumnName = "COD_TIPO_IDENTIFICACION")
	@ManyToOne(optional = true)
	private TipoIdentificacion tipoIdentificacionConyuge;

	@Basic(optional = true)
	@Column(name = "IDENTIFICACION_C")
	private String identificacionConyuge;

	@Column(name = "USR_CREACION")
	private String usrCreacion;

	@Column(name = "USR_MODIFICACION")
	private String usrModificacion;

	@Basic(optional = false)
	@JoinColumn(name = "COD_ESTADO_PERSONA", referencedColumnName = "COD_ESTADO")
	@ManyToOne(optional = false)
	private EstadoRcs estadoRcs;

	@Column(name = "COMENTARIO")
	private String comentario;

	@Transient
	private boolean mostrarRiesgoLista;

	@Transient
	private String fechaTransient;

	@Transient
	private PersonaNatural personaNaturalTransient;// para consulta de RCS

	@Transient
	private EstadoRcsEnum estadoRcsEnumTransient;

	@Transient
	private PersonaRechazoListaReservadaEnum controlRcsEnumTransient;

	public String getComentarioCorteTransient() {

		String comentarioCorte = "";

		if (comentario != null) {
			comentarioCorte = comentario.toString();
			if (comentarioCorte.length() > 20) {
				comentarioCorte = comentarioCorte.substring(0, 20);
				comentarioCorte += "...";
			}
		}
		return comentarioCorte;
	}

	public Rcs() {
	}

	public Rcs(Integer secPersonaControl) {
		this.secPersonaControl = secPersonaControl;
	}

	public Integer getSecPersonaControl() {
		return secPersonaControl;
	}

	public void setSecPersonaControl(Integer secPersonaControl) {
		this.secPersonaControl = secPersonaControl;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public TipoIdentificacion getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (secPersonaControl != null ? secPersonaControl.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Rcs)) {
			return false;
		}
		Rcs other = (Rcs) object;
		if ((this.secPersonaControl == null && other.secPersonaControl != null)
				|| (this.secPersonaControl != null && !this.secPersonaControl.equals(other.secPersonaControl))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.modelo.Rcs[secPersonaControl=" + secPersonaControl + "]";
	}

	public Date getTsCreacion() {
		return tsCreacion;
	}

	public void setTsCreacion(Date tsCreacion) {
		this.tsCreacion = tsCreacion;
	}

	public PersonaNatural getPersonaNaturalTransient() {
		return personaNaturalTransient;
	}

	public void setPersonaNaturalTransient(PersonaNatural personaNaturalTransient) {
		this.personaNaturalTransient = personaNaturalTransient;
	}

	public boolean isMostrarRiesgoLista() {
		return mostrarRiesgoLista;
	}

	public void setMostrarRiesgoLista(boolean mostrarRiesgoLista) {
		this.mostrarRiesgoLista = mostrarRiesgoLista;
	}

	public String getFechaTransient() {
		return fechaTransient;
	}

	public void setFechaTransient(String fechaTransient) {
		this.fechaTransient = fechaTransient;
	}

	public char getCodControl() {
		return codControl;
	}

	public void setCodControl(char codControl) {
		this.codControl = codControl;
	}

	public char getCodEstado() {
		return codEstado;
	}

	public void setCodEstado(char codEstado) {
		this.codEstado = codEstado;
	}

	public EstadoRcsEnum getEstadoRcsEnumTransient() {
		if (estadoRcsEnumTransient == null) {
			estadoRcsEnumTransient = EstadoRcsEnum.buscarPorCodigo(codEstado);
		}
		return estadoRcsEnumTransient;
	}

	public void setEstadoRcsEnumTransient(EstadoRcsEnum estadoRcsEnumTransient) {
		this.estadoRcsEnumTransient = estadoRcsEnumTransient;
	}

	public PersonaRechazoListaReservadaEnum getControlRcsEnumTransient() {
		if (controlRcsEnumTransient == null) {
			controlRcsEnumTransient = PersonaRechazoListaReservadaEnum.buscarPorCodigo(codControl);
		}
		return controlRcsEnumTransient;
	}

	public void setControlRcsEnumTransient(PersonaRechazoListaReservadaEnum controlRcsEnumTransient) {
		this.controlRcsEnumTransient = controlRcsEnumTransient;
	}

	public String getContenidoXml() {
		return contenidoXml;
	}

	public void setContenidoXml(String contenidoXml) {
		this.contenidoXml = contenidoXml;
	}

	public String getIdentificacionConyuge() {
		return identificacionConyuge;
	}

	public void setIdentificacionConyuge(String identificacionConyuge) {
		this.identificacionConyuge = identificacionConyuge;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public Date getTsModificacion() {
		return tsModificacion;
	}

	public void setTsModificacion(Date tsModificacion) {
		this.tsModificacion = tsModificacion;
	}

	public TipoIdentificacion getTipoIdentificacionConyuge() {
		return tipoIdentificacionConyuge;
	}

	public void setTipoIdentificacionConyuge(TipoIdentificacion tipoIdentificacionConyuge) {
		this.tipoIdentificacionConyuge = tipoIdentificacionConyuge;
	}

	public String getUsrCreacion() {
		return usrCreacion;
	}

	public void setUsrCreacion(String usrCreacion) {
		this.usrCreacion = usrCreacion;
	}

	public String getUsrModificacion() {
		return usrModificacion;
	}

	public void setUsrModificacion(String usrModificacion) {
		this.usrModificacion = usrModificacion;
	}

	public EstadoRcs getEstadoRcs() {
		return estadoRcs;
	}

	public void setEstadoRcs(EstadoRcs estadoRcs) {
		this.estadoRcs = estadoRcs;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
}