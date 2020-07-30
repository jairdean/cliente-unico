package com.equivida.mensajeria;

import java.util.List;

import javax.ejb.Local;
import javax.naming.NamingException;

import com.equivida.constant.PersonaRechazoListaReservadaEnum;
import com.equivida.dto.RiesgoDto;
import com.equivida.modelo.Rcs;
import com.equivida.util.MailMessage;

/**
 * @author Daniel Cardenas
 * @author Fernando Tamayo
 * @version $Revision: 1.1 $
 */
@Local
public interface EmailServicio {

	void enviarCorreo(MailMessage mailMessage);

	MailMessage prepararCorreoListasReservadas(String usuario, String ip,
			String apellido, String segundoApellido, String nombres,
			String tipoRelacion, boolean nacional, String listing);

	MailMessage prepararCorreoListasReservadas(String usuario, String ip,
			String apellido, String segundoApellido, String nombres,
			String identificacion,
			PersonaRechazoListaReservadaEnum tipoRelacion,
			List<RiesgoDto> riesgoDtoLista, boolean nuevo, String apellidoCon,
			String segundoApellidoCon, String nombresCon,
			String identificacionCon, boolean contratante);
	
	/**
	 * Prepara email cuando se aprueba un cliente de rcs
	 * 
	 * @param rcs
	 * @return
	 */
	MailMessage prepararCorreoListasReservadasAprobacion(Rcs rcs)
			throws NamingException;

	/**
	 * Prepara email cuando se rechaza un cliente
	 * 
	 * @param rcs
	 * @return
	 */
	MailMessage prepararCorreoListasReservadasRechazo(Rcs rcs)
			throws NamingException;
}
