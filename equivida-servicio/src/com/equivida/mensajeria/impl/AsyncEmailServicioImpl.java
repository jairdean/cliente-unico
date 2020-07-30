/*
 * AsyncEmailServicioImpl.java.

 */
package com.equivida.mensajeria.impl;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.log4j.Logger;

import com.equivida.mensajeria.AsyncEmailServicio;
import com.equivida.util.MailMessage;

/**
 * Clase que implementa los metdos de la interfaz
 * ClienteQueueMailServiceLocal.java
 * 
 * @author Juan Ochoa
 * @version $1.0$
 */
@Stateless(name = "AsyncEmailServicio")
public class AsyncEmailServicioImpl implements AsyncEmailServicio {
	private static Logger log = Logger.getLogger(AsyncEmailServicioImpl.class);
	// @Resource(mappedName = "jms/ConnectionFactory")
	@Resource(mappedName = "java:/ConnectionFactory")
	// private static QueueConnectionFactory connectionFactory;
	private ConnectionFactory connectionFactory;

	@Resource(mappedName = "queue/MailClienteUnico")
	private Queue queue;

	public void encolarMail(MailMessage mmessage) {
		Connection connection = null;
		Session session = null;

		if (mmessage == null) {
			System.err
					.println("No se envia mail porque no se ha creado correctamente el mail message (null)");
			return;
		}

		try {
			connection = connectionFactory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			MessageProducer messageProducer = session.createProducer(queue);

			ObjectMessage objectMessage = session.createObjectMessage();
			objectMessage.setObject(mmessage);

			messageProducer.send(objectMessage);

			System.out.println("se ha encolado el mail");

		} catch (JMSException e) {
			log.error(e.getMessage(), e.getCause());
		} finally {
			if (session != null) {
				try {
					session.close();
					connection.close();
				} catch (JMSException e) {
					log.error(e.getMessage(), e.getCause());
				}
			}
		}
	}

}
