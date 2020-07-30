/**
 * 
 */
package com.equivida.buenviaje.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author juan
 *
 */
public class DateAdapter extends XmlAdapter<String, Date> {
	DateFormat f = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public String marshal(Date v) throws Exception {
		return f.format(v);
	}

	@Override
	public Date unmarshal(String v) throws Exception {
		if (v != null && v.trim().length() > 0) {
			return f.parse(v);
		}
		return null;
	}

}
