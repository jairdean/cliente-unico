/*
 * MailMDB.java
 * Created on 13/10/2008
 * Copyright saviaSoft. All Rights Reserved.
 * SAVIASOFT cia ltda 
 * Quito-Ecuador
 * www.saviasoft.com
 */
package com.equivida.listener;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.log4j.Logger;

import com.equivida.mensajeria.EmailServicio;
import com.equivida.util.MailMessage;

/**
 * @author Fernando Tamayo
 * @version $Revision: 1.1 $
 */

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/MailClienteUnico"),
		@ActivationConfigProperty(propertyName = "reconnectAttempts", propertyValue = "-1"),
		@ActivationConfigProperty(propertyName = "setupAttempts", propertyValue = "-1") })
public class MailMDB implements MessageListener {
	private static Logger log = Logger.getLogger(MailMDB.class);

	@EJB
	EmailServicio emailServicio;

	/**
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	public void onMessage(Message message) {
		System.out.println("se procesa la cola");
		if (message instanceof ObjectMessage) {
			try {
				MailMessage mm = (MailMessage) (((ObjectMessage) message)
						.getObject());
				emailServicio.enviarCorreo(mm);
			} catch (JMSException e) {
				log.error(e.getMessage(), e.getCause());
			}
		}

	}

}
