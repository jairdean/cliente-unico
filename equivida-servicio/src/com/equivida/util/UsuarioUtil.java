package com.equivida.util;

import javax.faces.context.FacesContext;

/**
 * @author Daniel Cardenas
 * 
 */
public class UsuarioUtil {

	public static String getUsuarioLogueado() {
		String usuario = null;

		if (FacesContext.getCurrentInstance() != null) {
			if (FacesContext.getCurrentInstance().getExternalContext() != null) {
				usuario = FacesContext.getCurrentInstance()
						.getExternalContext().getRemoteUser();
			}
		}
		return usuario;
	}

}
