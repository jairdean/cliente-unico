/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.equivida.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

import com.equivida.constant.EstadoEnum;
import com.equivida.constant.RespuestaEnum;
import com.equivida.util.StringUtil;

/**
 * 
 * @author saviasoft4
 */
@Entity
@Table(name = "DIRECCION")
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
		@NamedQuery(name = "Direccion.findAll", query = "SELECT d FROM Direccion d"),
		@NamedQuery(name = "Direccion.findBySecDireccion", query = "SELECT d FROM Direccion d WHERE d.secDireccion = :secDireccion"),
		@NamedQuery(name = "Direccion.findByPrincipal", query = "SELECT d FROM Direccion d WHERE d.principal = :principal"),
		@NamedQuery(name = "Direccion.findByNumero", query = "SELECT d FROM Direccion d WHERE d.numero = :numero"),
		@NamedQuery(name = "Direccion.findBySecundaria", query = "SELECT d FROM Direccion d WHERE d.secundaria = :secundaria"),
		@NamedQuery(name = "Direccion.findByBarrio", query = "SELECT d FROM Direccion d WHERE d.barrio = :barrio"),
		@NamedQuery(name = "Direccion.findByEdificio", query = "SELECT d FROM Direccion d WHERE d.edificio = :edificio"),
		@NamedQuery(name = "Direccion.findByPiso", query = "SELECT d FROM Direccion d WHERE d.piso = :piso"),
		@NamedQuery(name = "Direccion.findByOficina", query = "SELECT d FROM Direccion d WHERE d.oficina = :oficina"),
		@NamedQuery(name = "Direccion.findByEnvioCorrespondencia", query = "SELECT d FROM Direccion d WHERE d.envioCorrespondencia = :envioCorrespondencia"),
		@NamedQuery(name = "Direccion.findByVerificada", query = "SELECT d FROM Direccion d WHERE d.verificada = :verificada"),
		@NamedQuery(name = "Direccion.findByReferencia", query = "SELECT d FROM Direccion d WHERE d.referencia = :referencia"),
		@NamedQuery(name = "Direccion.findByLatitud", query = "SELECT d FROM Direccion d WHERE d.latitud = :latitud"),
		@NamedQuery(name = "Direccion.findByLongitud", query = "SELECT d FROM Direccion d WHERE d.longitud = :longitud"),
		@NamedQuery(name = "Direccion.findByTsCreacion", query = "SELECT d FROM Direccion d WHERE d.tsCreacion = :tsCreacion"),
		@NamedQuery(name = "Direccion.findByTsModificacion", query = "SELECT d FROM Direccion d WHERE d.tsModificacion = :tsModificacion") })
public class Direccion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3370590699029039043L;
	@Id
	@Basic(optional = false)
	@Column(name = "SEC_DIRECCION")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer secDireccion;
	@Basic(optional = false)
	@Column(name = "PRINCIPAL")
	private String principal;
	@Basic(optional = false)
	@Column(name = "NUMERO")
	private String numero;
	@Column(name = "SECUNDARIA")
	private String secundaria;
	@Basic(optional = false)
	@Column(name = "BARRIO")
	private String barrio;
	@Column(name = "EDIFICIO")
	private String edificio;
	@Column(name = "PISO", length = 2)
	private String piso;
	@Column(name = "OFICINA")
	private String oficina;
	@Column(name = "ENVIO_CORRESPONDENCIA")
	@XmlTransient
	private Character envioCorrespondencia;
	@Basic(optional = false)
	@Column(name = "VERIFICADA")
	@XmlTransient
	private char verificada;
	@Column(name = "REFERENCIA")
	private String referencia;
	@Basic(optional = false)
	@Column(name = "LATITUD", precision = 19, scale = 2, columnDefinition = "decimal(19,2)")
	@XmlTransient
	private BigDecimal latitud;
	@Basic(optional = false)
	@Column(name = "LONGITUD", precision = 19, scale = 2, columnDefinition = "decimal(19,2)")
	@XmlTransient
	private BigDecimal longitud;
	@Basic(optional = false)
	@Column(name = "TS_CREACION", insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@XmlTransient
	private Date tsCreacion;
	@Basic(optional = false)
	@Column(name = "TS_MODIFICACION", insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@XmlTransient
	private Date tsModificacion;

	@JoinColumn(name = "COD_TIPO_DIRECCION", referencedColumnName = "COD_TIPO_DIRECCION")
	@ManyToOne(optional = false)
	private TipoDireccion tipoDireccion;
	@JoinColumn(name = "SEC_PERSONA", referencedColumnName = "SEC_PERSONA")
	@ManyToOne(optional = false)
	@XmlTransient
	private Persona persona;
	@JoinColumn(name = "SEC_CANTON", referencedColumnName = "SEC_CANTON")
	@ManyToOne(optional = false)
	private Canton canton;
	@Column(name = "CIUDAD", length = 32)
	private String ciudad;

	@Column(name = "ESTADO")
	@XmlTransient
	private char estado;// A-I

	// auditoria
	@Column(name = "USR_CREACION")
	@XmlTransient
	private String usrCreacion;

	@Column(name = "TTY_CREACION")
	@XmlTransient
	private String ttyCreacion;// terminal

	@Column(name = "PRG_CREACION")
	@XmlTransient
	private String prgCreacion;

	@Column(name = "CTA_CREACION")
	@XmlTransient
	private String ctaCreacion;

	@Column(name = "USR_MODIFICACION")
	@XmlTransient
	private String usrModificacion;

	@Column(name = "TTY_MODIFICACION")
	@XmlTransient
	private String ttyModificacion;

	@Column(name = "PRG_MODIFICACION")
	@XmlTransient
	private String prgModificacion;

	@Column(name = "CTA_MODIFICACION")
	@XmlTransient
	private String ctaModificacion;

	@Transient
	private Direccion original;

	@Transient
	private boolean mostrarNoCargoLW;

	@Transient
	private String direccionCompleta;

	@Transient
	private String parroquia;

	@Transient
	@XmlTransient
	private Collection<DireccionTelefono> direccionTelefonoCollection;

	@Transient
	private String estiloDireccion;

	@Transient
	private boolean tieneDireccionCompleta;

	@Transient
	@XmlTransient
	public Collection<DireccionTelefono> getDireccionTelefonoActivosCollection() {
		Collection<DireccionTelefono> activos = new ArrayList<DireccionTelefono>();

		if (direccionTelefonoCollection != null
				&& !direccionTelefonoCollection.isEmpty()) {
			for (DireccionTelefono dt : direccionTelefonoCollection) {
				if (dt.getTelefono().getActivo()) {
					activos.add(dt);
				}
			}
		}

		return activos;
	}

	@Transient
	public int getTotalActivosTelefono() {
		return getDireccionTelefonoActivosCollection().size();
	}

	public char getEstado() {
		return estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}

	@Transient
	private Boolean direccionPrincipal;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((barrio == null) ? 0 : barrio.hashCode());
		result = prime * result + ((canton == null) ? 0 : canton.hashCode());
		result = prime * result + ((ciudad == null) ? 0 : ciudad.hashCode());
		result = prime * result
				+ ((edificio == null) ? 0 : edificio.hashCode());
		result = prime
				* result
				+ ((envioCorrespondencia == null) ? 0 : envioCorrespondencia
						.hashCode());
		result = prime * result + estado;
		result = prime * result + ((latitud == null) ? 0 : latitud.hashCode());
		result = prime * result
				+ ((longitud == null) ? 0 : longitud.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((oficina == null) ? 0 : oficina.hashCode());
		result = prime * result + ((persona == null) ? 0 : persona.hashCode());
		result = prime * result + ((piso == null) ? 0 : piso.hashCode());
		result = prime * result
				+ ((principal == null) ? 0 : principal.hashCode());
		result = prime * result
				+ ((referencia == null) ? 0 : referencia.hashCode());
		result = prime * result
				+ ((secDireccion == null) ? 0 : secDireccion.hashCode());
		result = prime * result
				+ ((secundaria == null) ? 0 : secundaria.hashCode());
		result = prime * result
				+ ((tipoDireccion == null) ? 0 : tipoDireccion.hashCode());
		result = prime * result + verificada;
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
		Direccion other = (Direccion) obj;
		if (barrio == null) {
			if (other.barrio != null)
				return false;
		} else if (!barrio.equals(other.barrio))
			return false;
		if (canton == null) {
			if (other.canton != null)
				return false;
		} else if (!canton.equals(other.canton))
			return false;
		if (ciudad == null) {
			if (other.ciudad != null)
				return false;
		} else if (!ciudad.equals(other.ciudad))
			return false;
		if (edificio == null) {
			if (other.edificio != null)
				return false;
		} else if (!edificio.equals(other.edificio))
			return false;
		if (envioCorrespondencia == null) {
			if (other.envioCorrespondencia != null)
				return false;
		} else if (!envioCorrespondencia.equals(other.envioCorrespondencia))
			return false;
		if (estado != other.estado)
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (oficina == null) {
			if (other.oficina != null)
				return false;
		} else if (!oficina.equals(other.oficina))
			return false;
		if (piso == null) {
			if (other.piso != null)
				return false;
		} else if (!piso.equals(other.piso))
			return false;
		if (principal == null) {
			if (other.principal != null)
				return false;
		} else if (!principal.equals(other.principal))
			return false;
		if (referencia == null) {
			if (other.referencia != null)
				return false;
		} else if (!referencia.equals(other.referencia))
			return false;
		if (secDireccion == null) {
			if (other.secDireccion != null)
				return false;
		} else if (!secDireccion.equals(other.secDireccion))
			return false;
		if (secundaria == null) {
			if (other.secundaria != null)
				return false;
		} else if (!secundaria.equals(other.secundaria))
			return false;
		if (tipoDireccion == null) {
			if (other.tipoDireccion != null)
				return false;
		} else if (!tipoDireccion.equals(other.tipoDireccion))
			return false;
		if (verificada != other.verificada)
			return false;
		return true;
	}

	public Direccion() {
	}

	public Direccion(Integer secDireccion) {
		this.secDireccion = secDireccion;
	}

	public Direccion(Integer secDireccion, String principal, String numero,
			String barrio, char verificada, BigDecimal latitud,
			BigDecimal longitud, Date tsCreacion, Date tsModificacion) {
		this.secDireccion = secDireccion;
		this.principal = principal;
		this.numero = numero;
		this.barrio = barrio;
		this.verificada = verificada;
		this.latitud = latitud;
		this.longitud = longitud;
		this.tsCreacion = tsCreacion;
		this.tsModificacion = tsModificacion;
	}

	@Transient
	public boolean getActivo() {
		return this.estado == EstadoEnum.ACTIVO.getCodigo();
	}

	public Integer getSecDireccion() {
		return secDireccion;
	}

	public void setSecDireccion(Integer secDireccion) {
		this.secDireccion = secDireccion;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		if (principal != null) {
			this.principal = StringUtil.toUpper(principal).trim();
		}
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		if (numero != null) {
			this.numero = StringUtil.toUpper(numero).trim();
		}
	}

	public String getSecundaria() {
		return secundaria;
	}

	public void setSecundaria(String secundaria) {
		if (secundaria != null) {
			this.secundaria = StringUtil.toUpper(secundaria).trim();
		}
	}

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		if (barrio != null) {
			this.barrio = StringUtil.toUpper(barrio).trim();
		}
	}

	public String getEdificio() {
		return edificio;
	}

	public void setEdificio(String edificio) {
		if (edificio != null) {
			this.edificio = StringUtil.toUpper(edificio).trim();
		}
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getOficina() {
		return oficina;
	}

	public void setOficina(String oficina) {
		if (oficina != null) {
			this.oficina = StringUtil.toUpper(oficina).trim();
		}
	}

	public Character getEnvioCorrespondencia() {
		return envioCorrespondencia;
	}

	public void setEnvioCorrespondencia(Character envioCorrespondencia) {
		this.envioCorrespondencia = envioCorrespondencia;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		if (referencia != null) {
			this.referencia = StringUtil.toUpper(referencia).trim();
		}
	}

	public BigDecimal getLatitud() {
		return latitud;
	}

	public void setLatitud(BigDecimal latitud) {
		this.latitud = latitud;
	}

	public BigDecimal getLongitud() {
		return longitud;
	}

	public void setLongitud(BigDecimal longitud) {
		this.longitud = longitud;
	}

	public Date getTsCreacion() {
		return tsCreacion;
	}

	public void setTsCreacion(Date tsCreacion) {
		this.tsCreacion = tsCreacion;
	}

	public Date getTsModificacion() {
		return tsModificacion;
	}

	public void setTsModificacion(Date tsModificacion) {
		this.tsModificacion = tsModificacion;
	}

	public TipoDireccion getTipoDireccion() {
		return tipoDireccion;
	}

	public void setTipoDireccion(TipoDireccion tipoDireccion) {
		this.tipoDireccion = tipoDireccion;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	/**
	 * @return the direccionTelefonoCollection
	 */
	public Collection<DireccionTelefono> getDireccionTelefonoCollection() {
		return direccionTelefonoCollection;
	}

	/**
	 * @param direccionTelefonoCollection
	 *            the direccionTelefonoCollection to set
	 */
	public void setDireccionTelefonoCollection(
			Collection<DireccionTelefono> direccionTelefonoCollection) {
		this.direccionTelefonoCollection = direccionTelefonoCollection;
	}

	public String getDireccionParaSise() {

		String callePrinc = this.getPrincipal();
		if (callePrinc == null) {
			callePrinc = "";
		}

		String calleSec = this.getSecundaria();
		if (calleSec == null) {
			calleSec = "";
		}

		String numero = this.getNumero();
		if (numero == null) {
			numero = "";
		}

		String txt_direccion = callePrinc + " " + numero;

		// si existe calle secundaria pone 'y calle secundaria'
		if (!calleSec.equals("")) {
			txt_direccion = txt_direccion + " y " + calleSec;
		}

		return txt_direccion;
	}

	/**
	 * @return the direccionPrincipal
	 */
	public Boolean getDireccionPrincipal() {
		if (direccionPrincipal == null) {
			if (this.envioCorrespondencia == null) {
				this.envioCorrespondencia = 'N';
			}
			if (this.envioCorrespondencia.equals(RespuestaEnum.SI.getCodigo())) {
				direccionPrincipal = true;
			} else {
				direccionPrincipal = false;
			}
		}
		return direccionPrincipal;
	}

	/**
	 * @param direccionPrincipal
	 *            the direccionPrincipal to set
	 */
	public void setDireccionPrincipal(Boolean direccionPrincipal) {
		this.direccionPrincipal = direccionPrincipal;
	}

	/**
	 * @return the verificada
	 */
	public char getVerificada() {
		return verificada;
	}

	/**
	 * @param verificada
	 *            the verificada to set
	 */
	public void setVerificada(char verificada) {
		this.verificada = verificada;
	}

	public Canton getCanton() {
		return canton;
	}

	public void setCanton(Canton canton) {
		this.canton = canton;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getUsrCreacion() {
		return usrCreacion;
	}

	public void setUsrCreacion(String usrCreacion) {
		this.usrCreacion = usrCreacion;
	}

	public String getTtyCreacion() {
		return ttyCreacion;
	}

	public void setTtyCreacion(String ttyCreacion) {
		this.ttyCreacion = ttyCreacion;
	}

	public String getPrgCreacion() {
		return prgCreacion;
	}

	public void setPrgCreacion(String prgCreacion) {
		this.prgCreacion = prgCreacion;
	}

	public String getCtaCreacion() {
		return ctaCreacion;
	}

	public void setCtaCreacion(String ctaCreacion) {
		this.ctaCreacion = ctaCreacion;
	}

	public String getUsrModificacion() {
		return usrModificacion;
	}

	public void setUsrModificacion(String usrModificacion) {
		this.usrModificacion = usrModificacion;
	}

	public String getTtyModificacion() {
		return ttyModificacion;
	}

	public void setTtyModificacion(String ttyModificacion) {
		this.ttyModificacion = ttyModificacion;
	}

	public String getPrgModificacion() {
		return prgModificacion;
	}

	public void setPrgModificacion(String prgModificacion) {
		this.prgModificacion = prgModificacion;
	}

	public String getCtaModificacion() {
		return ctaModificacion;
	}

	public void setCtaModificacion(String ctaModificacion) {
		this.ctaModificacion = ctaModificacion;
	}

	public boolean isMostrarNoCargoLW() {
		return mostrarNoCargoLW;
	}

	public void setMostrarNoCargoLW(boolean mostrarNoCargoLW) {
		this.mostrarNoCargoLW = mostrarNoCargoLW;
	}

	public Direccion getOriginal() {
		return original;
	}

	public void setOriginal(Direccion original) {
		this.original = original;
	}

	/**
	 * @return the direccionCompleta
	 */
	public String getDireccionCompleta() {
		return direccionCompleta;
	}

	/**
	 * @param direccionCompleta
	 *            the direccionCompleta to set
	 */
	public void setDireccionCompleta(String direccionCompleta) {
		this.direccionCompleta = direccionCompleta;
	}

	@Override
	public String toString() {
		return "Direccion [secDireccion=" + secDireccion + ", principal="
				+ principal + ", numero=" + numero + ", secundaria="
				+ secundaria + ", barrio=" + barrio + ", edificio=" + edificio
				+ ", piso=" + piso + ", oficina=" + oficina
				+ ", envioCorrespondencia=" + envioCorrespondencia
				+ ", verificada=" + verificada + ", referencia=" + referencia
				+ ", latitud=" + latitud + ", longitud=" + longitud
				+ ", tipoDireccion=" + tipoDireccion + ", canton=" + canton
				+ ", ciudad=" + ciudad + ", estado=" + estado + "]";
	}

	/**
	 * @return the parroquia
	 */
	public String getParroquia() {
		return parroquia;
	}

	/**
	 * @param parroquia
	 *            the parroquia to set
	 */
	public void setParroquia(String parroquia) {
		this.parroquia = parroquia;
	}

	/**
	 * @return the estiloDireccion
	 */
	public String getEstiloDireccion() {
		return estiloDireccion;
	}

	/**
	 * @param estiloDireccion
	 *            the estiloDireccion to set
	 */
	public void setEstiloDireccion(String estiloDireccion) {
		this.estiloDireccion = estiloDireccion;
	}

	/**
	 * @return the tieneDireccionCompleta
	 */
	public boolean isTieneDireccionCompleta() {
		return tieneDireccionCompleta;
	}

	/**
	 * @param tieneDireccionCompleta
	 *            the tieneDireccionCompleta to set
	 */
	public void setTieneDireccionCompleta(boolean tieneDireccionCompleta) {
		this.tieneDireccionCompleta = tieneDireccionCompleta;
	}

}
