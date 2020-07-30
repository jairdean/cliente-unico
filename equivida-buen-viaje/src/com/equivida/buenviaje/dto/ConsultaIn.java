package com.equivida.buenviaje.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.equivida.buenviaje.constante.TipoPersonaConsultaEnum;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultaIn")
public class ConsultaIn implements Serializable {

	private static final long serialVersionUID = 6016466199130031912L;

	@XmlElement(name = "personaConsulta", required = true)
	private List<PersonaConsulta> personaConsultaLista;

	/**
	 * Obtiene los datos que no son repetidos.
	 * 
	 * @return
	 */
	public List<PersonaConsulta> getPersonaConsultaListaNoRepetidos() {
		List<PersonaConsulta> resp = new ArrayList<PersonaConsulta>();

		PersonaConsulta solicitante = getPersonaConsultaPorTipo(TipoPersonaConsultaEnum.SOLICITANTE);
		PersonaConsulta factura = getPersonaConsultaPorTipo(TipoPersonaConsultaEnum.FACTURA);
		List<PersonaConsulta> asegurados = getTodosAsegurados();

		// Si el solicitante es el mimsmo que el facturado, se toman los datos
		// del facturado.
		if (solicitante.getNoDocumento().equals(factura.getNoDocumento())) {
			resp.add(factura);
		} else {
			// Se pregunta si alguno de los asegurados es igual que el
			// facturado, en ese caso se toman los datos del facturado
			for (PersonaConsulta p : asegurados) {
				if (p.getNoDocumento().equals(factura.getNoDocumento())) {
					resp.add(factura);
					break;
				}
			}
		}

		// Se ponen el resto de personas en la lista final sin repetir ninguna
		for (PersonaConsulta pa : personaConsultaLista) {
			boolean existe = false;
			for (PersonaConsulta r : resp) {
				if (pa.getNoDocumento().equals(r.getNoDocumento())) {
					existe = true;
					break;
				}
			}

			if (!existe) {
				resp.add(pa);
			}
		}

		return resp;
	}

	/**
	 * Obtiene personas por tipo.
	 * 
	 * @param tipo
	 * @return
	 */
	public PersonaConsulta getPersonaConsultaPorTipo(
			TipoPersonaConsultaEnum tipo) {
		PersonaConsulta resp = new PersonaConsulta();

		for (PersonaConsulta p : personaConsultaLista) {
			if (p.getTipoPersonaConsulta().equals(tipo)) {
				resp = p;
				break;
			}
		}

		return resp;
	}

	/**
	 * Obtiene todos los asegurados.
	 * 
	 * @return
	 */
	public List<PersonaConsulta> getTodosAsegurados() {
		List<PersonaConsulta> resp = new ArrayList<PersonaConsulta>();

		for (PersonaConsulta p : personaConsultaLista) {
			if (TipoPersonaConsultaEnum.ASEGURADO.equals(p
					.getTipoPersonaConsulta())) {
				resp.add(p);
			}
		}

		return resp;
	}

	/**
	 * @return the personaConsultaLista
	 */
	public List<PersonaConsulta> getPersonaConsultaLista() {
		return personaConsultaLista;
	}

	/**
	 * @param personaConsultaLista
	 *            the personaConsultaLista to set
	 */
	public void setPersonaConsultaLista(
			List<PersonaConsulta> personaConsultaLista) {
		this.personaConsultaLista = personaConsultaLista;
	}

	@Override
	public String toString() {

		StringBuilder cadena = new StringBuilder("ConsultaIn: ");

		if (personaConsultaLista != null) {

			int cont = 0;

			for (PersonaConsulta p : personaConsultaLista) {
				cadena.append(p.toString());
				cont++;
				if (cont != personaConsultaLista.size()) {
					cadena.append(", ");
				}
			}

		}

		return cadena.toString();
	}
}
