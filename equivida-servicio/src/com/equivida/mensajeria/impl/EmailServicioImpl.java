/*
 * EmailServicioImpl.java
 *
 * Created on 13/10/2008
 * Copyright saviaSoft. All Rights Reserved.
 * SAVIASOFT cia ltda 
 * Quito-Ecuador
 * www.saviasoft.com
 */

package com.equivida.mensajeria.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import com.equivida.constant.Constantes;
import com.equivida.constant.PersonaRechazoListaReservadaEnum;
import com.equivida.dto.RiesgoDto;
import com.equivida.mensajeria.EmailServicio;
import com.equivida.modelo.Rcs;
import com.equivida.util.LdapUtil;
import com.equivida.util.MailMessage;
import com.equivida.util.Parametros;

/**
 * @author Fernando Tamayo
 * @author Daniel Cardenas
 * @version $Revision: 1.1 $
 */
@Stateless(name = "EmailServicio")
public class EmailServicioImpl implements EmailServicio {
	private static Logger log = Logger.getLogger(EmailServicioImpl.class);

	public void enviarCorreo(MailMessage mailMessage) {
		Context initCtx;
		try {
			initCtx = new InitialContext();
			Session session = (Session) initCtx.lookup("java:/MailEquivida");

			System.out.println("session:" + session);
			System.out.println(session.getTransport());

			session.setDebug(true);
			MimeMessage message = new MimeMessage(session);

			System.out.println(session.getDebug());

			List<String> emails = mailMessage.getTo();
			InternetAddress to[] = new InternetAddress[emails.size()];

			int c = 0;
			for (String email : emails) {
				to[c] = new InternetAddress(email);
				c++;
			}

			message.setRecipients(Message.RecipientType.TO, to);

			message.setSubject(mailMessage.getSubject(), "UTF-8");

			message.setText(mailMessage.getText(), "ISO-8859-1");

			// message.setText(mailMessage.getText());
			message.setContent(mailMessage.getText(), "text/html;charset=ISO-8859-1"); // text/html

			Transport.send(message);

			log.info("se envia mail");

		} catch (NamingException e) {
			e.printStackTrace();
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public MailMessage prepararCorreoListasReservadas(String usuario, String ip, String apellido,
			String segundoApellido, String nombres, String tipoRelacion, boolean nacional, String listing) {

		// to
		List<String> to = new ArrayList<String>();

		String correoParametro = Parametros.getString("correos.listas.reservadas");

		String[] correos = correoParametro.split(",");
		for (int i = 0; i < correos.length; i++) {
			if (!correos[i].equals("")) {
				to.add(correos[i]);
			}
		}

		// subject
		String subject = "ALERTA: Confidencial, cliente no cumple con el perfil , Registro de Cliente-Asegurado Seguro Individual"; //
		// html
		String html = "<table>";
		html += "<tr>";
		html += "<td>Funcionario ingresa:</td><td>" + usuario + "</td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td>M&aacute;quina:</td><td>" + ip + "</td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td>Relaci&oacute;n:</td><td>" + tipoRelacion + "</td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td>Apellido:</td><td>" + apellido + "</td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td>Segundo Apellido:</td><td>" + segundoApellido + "</td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td>Nombres:</td><td>" + nombres + "</td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td colspan='2'><div></div></td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td colspan='2'>DETALLE DE LISTAS RESERVADAS</td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td colspan='2'>" + apellido + " " + segundoApellido + " " + nombres + "</td>";
		html += "</tr>";
		html += "<tr>";

		if (nacional) {
			html += "<td colspan='2'>" + listing + "</td>";
		} else {
			html += "<td colspan='2'>" + listing + "</td>";
		}
		html += "</tr>";
		html += "</table>";

		MailMessage mailMessage = new MailMessage(to, subject, html);
		return mailMessage;
	}

	@Override
	public MailMessage prepararCorreoListasReservadas(String usuario, String ip, String apellido,
			String segundoApellido, String nombres, String identificacion,
			PersonaRechazoListaReservadaEnum tipoRelacion, List<RiesgoDto> riesgoDtoLista, boolean nuevo,
			String apellidoCon, String segundoApellidoCon, String nombresCon, String identificacionCon,
			boolean contratante) {

		// to
		List<String> to = new ArrayList<String>();

		String correoParametro = Parametros.getString("correos.listas.reservadas");

		String[] correos = correoParametro.split(",");
		for (int i = 0; i < correos.length; i++) {
			if (!correos[i].equals("")) {
				to.add(correos[i]);
			}
		}

		// subject
		String subject = "ALERTA: Confidencial, cliente no cumple con el perfil , Registro de Cliente-Asegurado Seguro Individual"; //
		if (contratante) {
			subject = "ALERTA: Confidencial, cliente no cumple con el perfil , Registro de Contratante"; //
		}

		if (!nuevo) {
			subject = "ALERTA: Confidencial, cliente no cumple con el perfil , Actualizaci\u00F3n de Cliente-Asegurado Seguro Individual";
			if (contratante) {
				subject = "ALERTA: Confidencial, cliente no cumple con el perfil , Actualizaci\u00F3n de Contratante";
			}
		}

		// html
		String html = "<table>";

		html += "<tr>";
		if (nuevo) {
			html += "<td colspan='2'>Ingreso de datos</td>";
		} else {
			html += "<td colspan='2'><b>Actualizaci&oacute;n</b> de datos</td>";
		}
		html += "</tr>";

		html += "<tr>";
		html += "<td>Relaci&oacute;n:</td><td>" + tipoRelacion + "</td>";
		html += "</tr>";

		html += "<tr>";
		html += "<td>Funcionario ingresa:</td><td>" + usuario + "</td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td>M&aacute;quina:</td><td>" + ip + "</td>";
		html += "</tr>";
		html += "<tr>";

		String cliente = " ASEGURADO";
		if (contratante) {
			cliente = " CONTRATANTE";
		}

		html += "<td colspan='2'>CLIENTE " + cliente + "</td>";
		html += "</tr>";
		// si no es contratante se ponen datos separados
		if (!contratante) {
			html += "<tr>";
			html += "<td>Apellido:</td><td>" + apellido + "</td>";
			html += "</tr>";
			html += "<tr>";
			html += "<td>Segundo Apellido:</td><td>" + segundoApellido + "</td>";
			html += "</tr>";
			html += "<tr>";
			html += "<td>Nombres:</td><td>" + nombres + "</td>";
			html += "</tr>";
		} else {
			String razonSocial = nombres;
			if (apellido != null) {
				if (segundoApellido != null) {
					razonSocial = apellido + " " + segundoApellido + " " + nombres;
				} else {
					razonSocial = apellido + " " + nombres;
				}
			}

			html += "<tr>";
			html += "<td>Raz&oacute;n Social:</td><td>" + razonSocial + "</td>";
			html += "</tr>";
		}

		html += "<tr>";
		html += "<td>identificaci&oacute;n:</td><td>" + ((identificacion == null) ? "" : identificacion) + "</td>";
		html += "</tr>";
		if (tipoRelacion.equals(PersonaRechazoListaReservadaEnum.EN_LISTA_CONYUGE_PERSONA)) {
			html += "<tr>";
			html += "<td colspan='2'>C&Oacute;NYUGE</td>";
			html += "</tr>";
			html = ponerDatosPersonaRelacion(apellidoCon, segundoApellidoCon, nombresCon, identificacionCon, html);
		}
		if (tipoRelacion.equals(PersonaRechazoListaReservadaEnum.EN_LISTA_REPRESENTANTE_LEGAL)) {
			html += "<tr>";
			html += "<td colspan='2'>REPRESENTANTE LEGAL</td>";
			html += "</tr>";
			html = ponerDatosPersonaRelacion(apellidoCon, segundoApellidoCon, nombresCon, identificacionCon, html);
		}
		if (tipoRelacion.equals(PersonaRechazoListaReservadaEnum.EN_LISTA_CONYUGE_REPRESENTANTE_LEGAL)) {
			html += "<tr>";
			html += "<td colspan='2'>C&Oacute;NYUGE REPRESENTANTE LEGAL</td>";
			html += "</tr>";
			html = ponerDatosPersonaRelacion(apellidoCon, segundoApellidoCon, nombresCon, identificacionCon, html);
		}
		if (tipoRelacion.equals(PersonaRechazoListaReservadaEnum.EN_LISTA_ACCIONISTA)) {
			html += "<tr>";
			html += "<td colspan='2'>ACCIONISTA</td>";
			html += "</tr>";
			html = ponerDatosPersonaRelacion(apellidoCon, segundoApellidoCon, nombresCon, identificacionCon, html);
		}

		html += "<tr>";
		html += "<td colspan='2'><div></div></td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td colspan='2'>DETALLE DE LISTAS RESERVADAS</td>";
		html += "</tr>";

		if (tipoRelacion.equals(PersonaRechazoListaReservadaEnum.EN_LISTA_CONYUGE_PERSONA)
				|| tipoRelacion.equals(PersonaRechazoListaReservadaEnum.EN_LISTA_REPRESENTANTE_LEGAL)
				|| tipoRelacion.equals(PersonaRechazoListaReservadaEnum.EN_LISTA_CONYUGE_REPRESENTANTE_LEGAL)
				|| tipoRelacion.equals(PersonaRechazoListaReservadaEnum.EN_LISTA_ACCIONISTA)) {
			html += "<tr>";
			html += "<td colspan='2'>" + apellidoCon + " " + segundoApellidoCon + " " + nombresCon + "</td>";
			html += "</tr>";

		} else {
			html += "<tr>";
			html += "<td colspan='2'>" + apellido + " " + segundoApellido + " " + nombres + "</td>";
			html += "</tr>";

		}

		for (RiesgoDto dto : riesgoDtoLista) {

			html += "<tr>";

			String listing = dto.getListing();
			if (dto.getFile() != null && !dto.getFile().equals("")) {
				listing = listing + " <b>(" + dto.getFile() + ")</b>";
			}

			if (dto.isNacional()) {
				html += "<td colspan='2'>" + listing + "</td>";
			} else {
				html += "<td colspan='2'>" + listing + "</td>";
			}
			html += "</tr>";
		}

		html += "</table>";

		MailMessage mailMessage = new MailMessage(to, subject, html);
		return mailMessage;
	}

	private String ponerDatosPersonaRelacion(String apellidoCon, String segundoApellidoCon, String nombresCon,
			String identificacionCon, String html) {
		html += "<tr>";
		html += "<td>Apellido:</td><td>" + apellidoCon + "</td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td>Segundo Apellido:</td><td>" + segundoApellidoCon + "</td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td>Nombres:</td><td>" + nombresCon + "</td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td>Identificaci&oacute;n:</td><td>" + ((identificacionCon == null) ? "" : identificacionCon)
				+ "</td>";
		html += "</tr>";
		return html;
	}

	/**
	 * @see com.equivida.mensajeria.EmailServicio#prepararCorreoListasReservadasAprobacion(com.equivida.modelo.Rcs)
	 */
	@Override
	public MailMessage prepararCorreoListasReservadasAprobacion(Rcs rcs) throws NamingException {

		// subject
		String subject = Constantes.MENSAJE_RCS_APROBADO;

		// body del email
		StringBuilder html = new StringBuilder("<table>");
		html.append("<tr>");
		html.append("<td>Se informa que el cliente " + rcs.getDenominacion() + " con identificaci&oacute;n "
				+ rcs.getIdentificacion() + " ha sido aprobado");
		html.append("</td>");
		html.append("</tr>");
		html.append("</table>");

		MailMessage mailMessage = crearMimeMessageAprobacionRechazo(rcs, subject, html);

		return mailMessage;

	}

	private MailMessage crearMimeMessageAprobacionRechazo(Rcs rcs, String subject, StringBuilder html)
			throws NamingException {

		List<String> to = obtenerEmail(rcs);

		MailMessage mailMessage = null;

		if (to.size() > 0) {
			mailMessage = new MailMessage(to, subject, html.toString());
		}

		ponerPieEmail(mailMessage);

		return mailMessage;
	}

	/**
	 * @see com.equivida.mensajeria.EmailServicio#prepararCorreoListasReservadasRechazo(com.equivida.modelo.Rcs)
	 */
	@Override
	public MailMessage prepararCorreoListasReservadasRechazo(Rcs rcs) throws NamingException {

		// subject
		String subject = Constantes.MENSAJE_RCS_NO_APROBADO;

		StringBuilder html = new StringBuilder("<table>");

		html.append("<br /><br />");
		html.append("<tr>");
		html.append("<td>Se informa que el cliente " + rcs.getDenominacion() + " con identificaci&oacute;n "
				+ rcs.getIdentificacion() + " ha sido no aprobado");
		html.append("</td>");
		html.append("</tr>");
		html.append("</table>");

		MailMessage mailMessage = crearMimeMessageAprobacionRechazo(rcs, subject, html);

		return mailMessage;
	}

	private void ponerPieEmail(MailMessage mailMessage) {

		StringBuilder html = new StringBuilder(mailMessage.getText());

		html.append("<table style='width:200px'>");
		html.append("<tr>");
		html.append("<td>Atentamente,</td>");
		html.append("</tr>");
		html.append("<tr>");
		html.append("<td style='color:#016299'>Sistema de Cliente &Uacute;nico</td>");
		html.append("</tr>");
		html.append("<tr>");
		html.append("<td style='color:#016299'><b>Equivida</b></td>");
		html.append("</tr>");
		html.append("</table>");

		mailMessage.setText(html.toString());

	}

	private List<String> obtenerEmail(Rcs rcs) throws NamingException {

		List<String> to = new ArrayList<String>();

		// se envia tb a los corros parametrizados para RCS
		String correoParametro = Parametros.getString("correos.listas.reservadas");

		String[] correos = correoParametro.split(",");
		for (int i = 0; i < correos.length; i++) {
			if (!correos[i].equals("")) {
				to.add(correos[i]);
			}
		}

		String email = LdapUtil.obtenerEmail(rcs.getIdUsuario());
		if (email != null) {
			to.add(email);
		}

		String emailUserCreacion = LdapUtil.obtenerEmail(rcs.getUsrCreacion());

		if (emailUserCreacion != null) {
			to.add(emailUserCreacion);
		}

		return to;
	}
}
