/*
 * Util.java
 */

package com.equivida.sise.test.util;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Clase utilizada para...
 * 
 * @author Juan Ochoa
 * @version $1.0 $
 */
public class Util {

	/**
	 * Crea una nueva instancia de Util.
	 */
	public Util() {
	}

	private static final String URL = "jnp://localhost:1099";
	private static final String FACTORY = "org.jnp.interfaces.NamingContextFactory";
	private static final String PKG_PREFIXES = "org.jboss.naming:org.jnp.interface";

	/**
	 * Metodo que devuelve el valor de la propiedad ServiceBean.
	 * 
	 * @param jndiName
	 *            el valor de jndi name
	 * 
	 * @return el valor de la propiedad ServiceBean
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getServiceBean(String jndiName) {
		System.out.println("----: " + jndiName);
		T serviceLocal = null;

		Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY, FACTORY);
		env.put(Context.PROVIDER_URL, URL);
		env.put(Context.URL_PKG_PREFIXES, PKG_PREFIXES);
		InitialContext ic;
		try {
			ic = new InitialContext(env);
			serviceLocal = (T) ic.lookup(jndiName);
		} catch (NamingException ex) {
			ex.printStackTrace();
		}
		return serviceLocal;
	}
}
