/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.equivida.modelo;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.ParseException;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author saviasoft4
 */
@Entity
@Table(name = "CONTACTO_PREFERIDO")
@NamedQueries({
		@NamedQuery(name = "ContactoPreferido.findAll", query = "SELECT c FROM ContactoPreferido c"),
		@NamedQuery(name = "ContactoPreferido.findBySecContactoPreferido", query = "SELECT c FROM ContactoPreferido c WHERE c.secContactoPreferido = :secContactoPreferido") })
public class ContactoPreferido implements Serializable {

	private static final long serialVersionUID = -871032938940007819L;

	@Id
	@Basic(optional = false)
	@Column(name = "SEC_CONTACTO_PREFERIDO")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long secContactoPreferido;
	// @Column(name = "HORARIO_PREFERIDO")
	// private String horarioPreferido;
	@Column(name = "HORA_INICIO_CONTACTO")
	private Short horarioInicio;
	@Column(name = "HORA_FIN_CONTACTO")
	private Short horarioFin;

	@JoinColumn(name = "COD_TIPO_CONTACTO_PREFERIDO", referencedColumnName = "COD_TIPO_CONTACTO_PREFERIDO")
	@ManyToOne(optional = false)
	private TipoContactoPreferido tipoContactoPreferido;
	@JoinColumn(name = "SEC_TELEFONO", referencedColumnName = "SEC_TELEFONO")
	@ManyToOne
	private Telefono telefono;
	@JoinColumn(name = "SEC_PERSONA", referencedColumnName = "SEC_PERSONA")
	@OneToOne(optional = false)
	private Persona persona;
	@JoinColumn(name = "SEC_DIRECCION", referencedColumnName = "SEC_DIRECCION")
	@ManyToOne
	private Direccion direccion;

	@Transient
	private String horaDesde;

	@Transient
	private String minutoDesde;

	@Transient
	private String horaHasta;

	@Transient
	private String minutoHasta;

	public ContactoPreferido() {
	}

	public ContactoPreferido(Long secContactoPreferido) {
		this.secContactoPreferido = secContactoPreferido;
	}

	public Long getSecContactoPreferido() {
		return secContactoPreferido;
	}

	public void setSecContactoPreferido(Long secContactoPreferido) {
		this.secContactoPreferido = secContactoPreferido;
	}

	public TipoContactoPreferido getTipoContactoPreferido() {
		return tipoContactoPreferido;
	}

	public void setTipoContactoPreferido(
			TipoContactoPreferido tipoContactoPreferido) {
		this.tipoContactoPreferido = tipoContactoPreferido;
	}

	public Telefono getTelefono() {
		return telefono;
	}

	public void setTelefono(Telefono telefono) {
		this.telefono = telefono;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	@Transient
	public boolean ponerHorario() {// si es true es correcto, false es invalido

		boolean correcto = true;

		if (getHoraDesde() == null || getHoraDesde().equals("")
				|| getMinutoDesde() == null || getMinutoDesde().equals("")) {
			horarioInicio = null;
			correcto = false;
		} else {
			try {
				int hd = Integer.parseInt(horaDesde);
				String formatPattern = "00";
				DecimalFormat format = new DecimalFormat(formatPattern);
				String hdS = format.format(hd);

				int md = Integer.parseInt(minutoDesde);
				String mdS = format.format(md);

				setHorarioInicio(Short.parseShort(hdS + mdS));

			} catch (NumberFormatException e) {
				correcto = false;
			}

		}

		if (!correcto) {
			return correcto;
		}

		if (getHoraHasta() == null || getHoraHasta().equals("")
				|| getMinutoHasta() == null || getMinutoHasta().equals("")) {
			horarioFin = null;
			correcto = false;
		} else {
			try {
				int hd = Integer.parseInt(horaHasta);
				String formatPattern = "00";
				DecimalFormat format = new DecimalFormat(formatPattern);
				String hdS = format.format(hd);

				int md = Integer.parseInt(minutoHasta);
				String mdS = format.format(md);

				setHorarioFin(Short.parseShort(hdS + mdS));
			} catch (NumberFormatException e) {
				correcto = false;
			}
		}
		if (correcto) {
			if (horarioInicio > horarioFin) {
				System.out.println("hora ini mayor que hora fin");
				correcto = false;
			}
		}

		return correcto;
	}

	public static void main(String[] args) {
		ContactoPreferido cp = new ContactoPreferido();
		cp.setHoraDesde("1");
		cp.setMinutoDesde("5");
		cp.ponerHorario();
		cp.ponerHorarioEnHorasMinutos();
		System.out.println(cp.getHoraDesde() + ":" + cp.getMinutoDesde());
	}

	public static Short obtenerFormatoHoraShort(String hora)
			throws ParseException {
		String[] valores = hora.split(":");

		if (valores.length != 2) {
			throw new ParseException("No esta en formato HH:MM", 0);
		}

		hora = hora.replaceAll(":", "");
		return Short.valueOf(hora);
	}

	public static String obtenerFormatoHora(Short hora) {
		StringBuilder formato = new StringBuilder("");

		if (hora != null) {
			String formatPattern = "0000";
			DecimalFormat format = new DecimalFormat(formatPattern);
			formato.append(format.format(hora));
			formato.insert(2, ":");
		}
		return formato.toString();
	}

	@Transient
	public void ponerHorarioEnHorasMinutos() {
		if (getHorarioInicio() == null) {
			horaDesde = "";
			minutoDesde = "";
		} else {
			String formatPattern = "0000";
			DecimalFormat format = new DecimalFormat(formatPattern);
			String horarioInicioS = format.format(getHorarioInicio());
			horaDesde = horarioInicioS.substring(0, 2);
			minutoDesde = horarioInicioS.substring(2, 4);
		}
		if (getHorarioFin() == null) {
			horaHasta = "";
			minutoHasta = "";
		} else {
			String formatPattern = "0000";
			DecimalFormat format = new DecimalFormat(formatPattern);
			String horarioFinS = format.format(getHorarioFin());
			horaHasta = horarioFinS.substring(0, 2);
			minutoHasta = horarioFinS.substring(2, 4);
		}

	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (secContactoPreferido != null ? secContactoPreferido.hashCode()
				: 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof ContactoPreferido)) {
			return false;
		}
		ContactoPreferido other = (ContactoPreferido) object;
		if ((this.secContactoPreferido == null && other.secContactoPreferido != null)
				|| (this.secContactoPreferido != null && !this.secContactoPreferido
						.equals(other.secContactoPreferido))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.equivida.modelo.ContactoPreferido[secContactoPreferido="
				+ secContactoPreferido + "]";
	}

	public Short getHorarioInicio() {
		return horarioInicio;
	}

	public void setHorarioInicio(Short horarioInicio) {
		this.horarioInicio = horarioInicio;
	}

	public Short getHorarioFin() {
		return horarioFin;
	}

	public void setHorarioFin(Short horarioFin) {
		this.horarioFin = horarioFin;
	}

	public String getHoraDesde() {
		return horaDesde;
	}

	public void setHoraDesde(String horaDesde) {
		this.horaDesde = horaDesde;
	}

	public String getMinutoDesde() {
		return minutoDesde;
	}

	public void setMinutoDesde(String minutoDesde) {
		this.minutoDesde = minutoDesde;
	}

	public String getHoraHasta() {
		return horaHasta;
	}

	public void setHoraHasta(String horaHasta) {
		this.horaHasta = horaHasta;
	}

	public String getMinutoHasta() {
		return minutoHasta;
	}

	public void setMinutoHasta(String minutoHasta) {
		this.minutoHasta = minutoHasta;
	}
}