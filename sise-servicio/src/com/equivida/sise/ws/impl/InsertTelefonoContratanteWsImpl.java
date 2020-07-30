package com.equivida.sise.ws.impl;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.equivida.sise.rs.InsertTelefonoContratante;
import com.equivida.sise.sp.InsertTelefonoContratanteSp;
import com.equivida.sise.ws.InsertDireccionContratanteWs;
import com.equivida.sise.ws.InsertTelefonoContratanteWs;

@Stateless(name = "InsertTelefonoContratanteWs")
@WebService(endpointInterface = "com.equivida.sise.ws.InsertTelefonoContratanteWs")
@Remote(InsertDireccionContratanteWs.class)
public class InsertTelefonoContratanteWsImpl implements InsertTelefonoContratanteWs {

	@EJB
	private InsertTelefonoContratanteSp insertTelefonoContratanteSp;

	@Override
	@WebMethod
	public @WebResult(name = "InsertTelefonoContratante") InsertTelefonoContratante spi_mpersona_telef_wkf(
			BigDecimal id_persona_wkf, BigDecimal cod_tipo_telef, String txt_telefono) {
		InsertTelefonoContratante respuesta = null;
		try {
			respuesta = insertTelefonoContratanteSp.llamarInsertTelefonoPersonaSp(id_persona_wkf, cod_tipo_telef,
					txt_telefono);

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}

		System.out.println("ejecutro web service telefono");

		return respuesta;
	}
}
