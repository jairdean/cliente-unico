package com.equivida.homologacion.ws.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebService;

import com.equivida.homologacion.servicio.PersonaEquividaServicio;
import com.equivida.homologacion.ws.HomologacionWs;

@Stateless(name = "HomologacionWs")
@WebService(endpointInterface = "com.equivida.homologacion.ws.HomologacionWs")
@Remote(HomologacionWs.class)
public class HomologacionWsImpl implements HomologacionWs {

	@EJB
	private PersonaEquividaServicio personaEquividaServicio;

	@Override
	public String[] obtenerIdSisePersona(String secuencialPersona) {

		Integer sec = Integer.parseInt(secuencialPersona);

		List<Long> idSiseLista = personaEquividaServicio
				.obtenerIdSisePersona(sec);

		String[] arr = new String[idSiseLista.size()];

		int a = 0;
		for (Long l : idSiseLista) {
			arr[a] = l.toString();
			a++;
		}

		return arr;
	}

	@Override
	public String obtenerSecuencialPersona(String idSise) {

		Long idSiseL = Long.parseLong(idSise);

		Integer secuencial = personaEquividaServicio
				.obtenerSecuencialPersona(idSiseL);

		String secRespuesta = "";

		if (secuencial != null) {
			secRespuesta = secuencial.toString();
		}

		return secRespuesta;
	}
}