package com.equivida.sise.ws.impl;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.equivida.sise.rs.InsertTelefonoPersona;
import com.equivida.sise.sp.InsertTelefonoPersonaSp;
import com.equivida.sise.ws.InsertTelefonoPersonaWs;

@Stateless(name = "InsertTelefonoPersonaWs")
@WebService(endpointInterface = "com.equivida.sise.ws.InsertTelefonoPersonaWs")
@Remote(InsertTelefonoPersonaWs.class)
public class InsertTelefonoPersonaWsImpl implements InsertTelefonoPersonaWs {

	@EJB
	private InsertTelefonoPersonaSp inserttelefonoPersonaSp;

	@Override
	@WebMethod
	public @WebResult(name = "InsertTelefonoPersona")
	InsertTelefonoPersona ws_sise_insert_mpersona_telef(BigDecimal id_persona,
			BigDecimal cod_tipo_telef, String txt_telefono, String campoin1,
			String campoin2, String campoin3, String campoin4, String campoin5) {

		InsertTelefonoPersona respuesta = null;
		try {
			respuesta = inserttelefonoPersonaSp.llamarInsertTelefonoPersonaSp(
					id_persona, cod_tipo_telef, txt_telefono, campoin1,
					campoin2, campoin3, campoin4, campoin5);
		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}
		return respuesta;
	}
}
