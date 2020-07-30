package com.equivida.auth;

import java.io.IOException;
import java.security.Principal;
import java.security.acl.Group;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import org.apache.log4j.Logger;
import org.jboss.security.SimpleGroup;
import org.jboss.security.SimplePrincipal;

public class EquividaLdapLoginModule implements LoginModule {

	private Subject subject;
	private CallbackHandler callbackHandler;
	@SuppressWarnings("unused")
	private Map<String, ?> sharedState;
	private Map<String, ?> options;

	private String username;
	private Principal user;
	private Group[] roles;

	private String initialContextFactory;
	private String providerUrl;

	private String bindDN;
	private String credentialDN;

	private Logger log = Logger.getLogger(EquividaLdapLoginModule.class);

	@Override
	public void initialize(Subject arg0, CallbackHandler arg1, Map<String, ?> arg2, Map<String, ?> arg3) {
		log.info("initialize()");
		this.subject = arg0;
		this.callbackHandler = arg1;
		this.sharedState = arg2;
		this.options = arg3;

		initialContextFactory = (String) options.get("java.naming.factory.initial");
		providerUrl = (String) options.get("java.naming.provider.url");
		bindDN = (String) options.get("bindDN");
		credentialDN = (String) options.get("credentialDN");
		log.info("fin initialize()");
	}

	@Override
	public boolean abort() throws LoginException {
		log.info("abort()");
		return true;
	}

	@Override
	public boolean commit() throws LoginException {
		log.info("commit()");

		subject.getPrincipals().add(user);
		// jboss requires the name 'Roles'
		Group group = new SimpleGroup("Roles");
		for (Group role : roles) {
			group.addMember(role);
		}
		subject.getPrincipals().add(group);

		log.info("fin commit()");
		return true;
	}

	@Override
	public boolean login() throws LoginException {
		log.info("login()");
		NameCallback nameCallback = new NameCallback("Username");
		PasswordCallback passwordCallback = new PasswordCallback("Password", false);

		Callback[] callbacks = new Callback[] { nameCallback, passwordCallback };
		try {
			callbackHandler.handle(callbacks);
			username = nameCallback.getName();
			char[] password = passwordCallback.getPassword();
			passwordCallback.clearPassword();

			// aqui validar
			System.out.println("validando user:" + username);
			// System.out.println("pass:" + password[0] + password[1]);

			if (loginCorrecto(username, password)) {
				user = new SimplePrincipal(username);
				// roles = new Group[] { new SimpleGroup("CONEXION") // for
				// };
				log.info("fin login() BIEN");
				return true;
			}

		} catch (IOException e) {
			log.error(e);
		} catch (UnsupportedCallbackException e) {
			log.error(e);
		} catch (NamingException e) {
			log.error(e);
		}
		log.info("fin login() MAL");
		return false;
	}

	@Override
	public boolean logout() throws LoginException {
		log.info("logout()");
		return true;
	}

	private boolean loginCorrecto(String username, char[] password) throws NamingException {
		Hashtable<String, Object> env = new Hashtable<String, Object>();

		env.put(Context.INITIAL_CONTEXT_FACTORY, initialContextFactory);
		env.put(Context.PROVIDER_URL, providerUrl);
		env.put(Context.SECURITY_PRINCIPAL, bindDN);// el que obtiene la lista
		env.put(Context.SECURITY_CREDENTIALS, credentialDN);// el que obtiene la
															// lista
		DirContext ctx = new InitialDirContext(env);
		String cadena = obtenerCadenaConexion(ctx, username);
		ctx.close();

		env.put(Context.SECURITY_PRINCIPAL, cadena);// userf
		String passwordf = new String(password);
		env.put(Context.SECURITY_CREDENTIALS, passwordf);// passwordf
		ctx = new InitialDirContext(env);
		ctx.close();
		return true;
	}

	@SuppressWarnings("rawtypes")
	private String obtenerCadenaConexion(DirContext ctx, String usernameBuscar) throws NamingException {

		String cadena = null;

		SearchControls controls = new SearchControls();
		controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		NamingEnumeration results = ctx.search("", "(sAMAccountName=" + usernameBuscar + ")", controls);

		while (results.hasMore()) {
			SearchResult searchResult = (SearchResult) results.next();
			Attributes attributes = searchResult.getAttributes();

			Attribute attr = attributes.get("sAMAccountName");
			String valor = (String) attr.get();
			System.out.println("sAMAccountName=" + valor);

			// seteo los roles

			Attribute attrR = attributes.get("memberOf");
			NamingEnumeration valoresMemberOf = attrR.getAll();

			List<Group> groupList = new ArrayList<Group>();

			while (valoresMemberOf.hasMoreElements()) {
				String valorR = (String) valoresMemberOf.nextElement();
				String[] rolesMemberOf = valorR.split(",");

				for (int i = 0; i < rolesMemberOf.length; i++) {
					if (rolesMemberOf[i].startsWith("CN=")) {
						String g = rolesMemberOf[i].substring(3);
						System.out.println("rol:" + g + ":");
						groupList.add(new SimpleGroup(g));
					}
				}
			}

			if (groupList.size() > 0) {
				roles = (Group[]) groupList.toArray(new Group[groupList.size()]);
			} else {
				log.info("no posee roles que inicien con CN=");
			}
			// System.out.println("sAMAccountName=" + valor);

			Attribute attrdn = attributes.get("distinguishedName");
			String dn = (String) attrdn.get();
			cadena = dn;
			break;
		}
		return cadena;
	}
}
