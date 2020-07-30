/*
 * MailMessage.java
 *
 * Created on 13/10/2008
 * Copyright saviaSoft. All Rights Reserved.
 * SAVIASOFT cia ltda 
 * Quito-Ecuador
 * www.saviasoft.com
 */
package com.equivida.util;

import java.io.Serializable;
import java.util.List;

/**
 * @author Fernando Tamayo
 * @version $Revision: 1.1 $
 */
public class MailMessage implements Serializable {

	private static final long serialVersionUID = 992514564899988845L;

	private String text;

	private List<String> to;

	private String subject;

	public MailMessage(List<String> to, String subject, String text) {
		super();
		this.to = to;
		this.subject = subject;
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<String> getTo() {
		return to;
	}

	public void setTo(List<String> to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}


}