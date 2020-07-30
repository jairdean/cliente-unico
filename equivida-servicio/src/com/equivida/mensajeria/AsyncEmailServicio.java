/*
 * ClienteQueueMailServiceLocal.java
 * 
 * PFPonline 2009.
 */
package com.equivida.mensajeria;

import javax.ejb.Local;

import com.equivida.util.MailMessage;

/**
 * Interfaz que define los metodos para encolar un mensaje que utiliza la cola
 * de mail.
 * 
 * @author Juan Ochoa
 * @version $1.0$
 */
@Local
public interface AsyncEmailServicio {

	/**
	 * Pone el mail en la cola.
	 * 
	 * @param mmessage
	 */
	void encolarMail(MailMessage mmessage);
}
