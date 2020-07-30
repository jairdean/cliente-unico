/* 
 * ContextUtil.java
 * Sep 4, 2009 
 * Copyright 2009 Saviasoft Cia. Ltda. 
 */
package com.equivida.sise.test.util;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * @author Daniel Cardenas
 * 
 */
public class ContextUtil {

	public static Object lookup(String jndiName) {

		String host = "localhost";
		String port = "1099";

		Context context;
		Properties properties = new Properties();
		properties.put("java.naming.factory.initial",
				"org.jnp.interfaces.NamingContextFactory");
		properties.put("java.naming.factory.url.pkgs",
				"=org.jboss.naming:org.jnp.interfaces");
		properties.put("java.naming.provider.url", host + ":" + port);
		try {
			context = new InitialContext(properties);
			return context.lookup(jndiName);
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}