package com.equivida.auth;

import java.security.acl.Group;

import javax.security.auth.login.LoginException;

import org.jboss.security.SimpleGroup;
import org.jboss.security.SimplePrincipal;
import org.jboss.security.auth.spi.LdapLoginModule;

/**
 * @author Daniel Cardenas
 * 
 */
public class EquividaLoginModule extends LdapLoginModule {

	@Override
	protected Group[] getRoleSets() throws LoginException {

		Group[] groups = { new SimpleGroup("Roles") };

		SimplePrincipal rolConexion = new SimplePrincipal("CONEXION");

		groups[0].addMember(rolConexion);

		return groups;
	}

}
