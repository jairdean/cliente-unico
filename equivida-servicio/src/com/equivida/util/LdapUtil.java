package com.equivida.util;

import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

/**
 * Clase para conectarse al ldap
 * 
 * @author Daniel Cardenas
 *
 */
public class LdapUtil {

	private static final Logger logger = Logger.getLogger(LdapUtil.class
			.getName());

	/**
	 * Obtiene la conexion con los datos que estan en el archivo ldap.properties
	 * dentro de este jar
	 * 
	 * @return
	 */
	public static DirContext obtenerConexionProperties() {

		String host = Parametros.getString("ldap.ip");
		String port = Parametros.getString("ldap.port");
		String principal = Parametros.getString("ldap.principal");
		String passPrincipal = Parametros.getString("ldap.passwd");

		DirContext dirContext = obtenerConexion(host, port, principal,
				passPrincipal);
		return dirContext;

	}

	/**
	 * Obtiene la conexion con los datos que se envien como parametros, devuelve
	 * DirContext
	 * 
	 * @return
	 */
	private static DirContext obtenerConexion(String host, String port,
			String principal, String passPrincipal) {

		Hashtable<String, Object> env = new Hashtable<String, Object>();

		String hostLdap = host + ":" + port;

		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://" + hostLdap);

		env.put(Context.SECURITY_PRINCIPAL, principal);// adminsitrator

		env.put(Context.SECURITY_CREDENTIALS, passPrincipal);

		DirContext ctx;
		try {
			ctx = new InitialDirContext(env);
			return ctx;

		} catch (NamingException e) {
			logger.log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
		}

		return null;

	}

	@SuppressWarnings("rawtypes")
	public static String obtenerEmail(String usernameBuscar)
			throws NamingException {

		SearchControls controls = new SearchControls();
		controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		String[] attrIDs = { "sAMAccountName", "mail" };
		controls.setReturningAttributes(attrIDs);

		DirContext con = obtenerConexionProperties();
		NamingEnumeration results = con.search("DC=equivida,DC=local",
				"(sAMAccountName=" + usernameBuscar + ")", controls);

		String mail = null;

		while (results.hasMore()) {
			SearchResult searchResult = (SearchResult) results.next();
			Attributes attributes = searchResult.getAttributes();

			Attribute attr = attributes.get("sAMAccountName");
			String valorAccountName = (String) attr.get();
			System.out.println("sAMAccountName=" + valorAccountName);

			// mail
			Attribute attrMail = attributes.get("mail");
			// si es null
			if (attrMail == null) {
				break;
			}
			mail = (String) attrMail.get();
			break;
		}
		con.close();
		return mail;
	}

	/**
	 * Prueba
	 * 
	 * @param args
	 * @throws NamingException
	 */
	public static void main(String[] args) throws NamingException {

		// System.out.println(obtenerEmail("kflores"));
		for (String u : new String[] { "kflores", "vcaiza", "pmora" }) {
			System.out.println(obtenerEmail(u));
		}
	}
}
