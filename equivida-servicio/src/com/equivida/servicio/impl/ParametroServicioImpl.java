package com.equivida.servicio.impl;

import javax.ejb.Stateless;

import com.equivida.servicio.ParametroServicio;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "ParametroServicio")
public class ParametroServicioImpl implements ParametroServicio {

	public String prueba() {
		return "hola :)";
	}
}
