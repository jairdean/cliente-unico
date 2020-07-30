package com.equivida.sise.ws.impl;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.equivida.sise.rs.InsertDireccionPersona;
import com.equivida.sise.sp.InsertDireccionPersonaSp;
import com.equivida.sise.ws.InsertDireccionPersonaWs;

@Stateless(name = "InsertDireccionPersonaWs")
@WebService(endpointInterface = "com.equivida.sise.ws.InsertDireccionPersonaWs")
@Remote(InsertDireccionPersonaWs.class)
public class InsertDireccionPersonaWsImpl implements InsertDireccionPersonaWs {

	@EJB
	private InsertDireccionPersonaSp insertDireccionPersonaSp;

	@Override
	@WebMethod
	public @WebResult(name = "InsertDireccionPersona")
	InsertDireccionPersona ws_sise_insert_mpersonadir(
			BigDecimal id_persona, BigDecimal cod_tipo_dir,
			String txt_direccion, BigDecimal cod_municipio,
			BigDecimal cod_dpto, BigDecimal cod_pais, String txt_edificio,
			String txt_urbanizacion, String txt_sector, String campoin1,
			String campoin2, String campoin3, String campoin4, String campoin5) {

		InsertDireccionPersona respuesta = null;
		try {
			respuesta = insertDireccionPersonaSp
					.llamarInsertDireccionPersonaSp(id_persona, cod_tipo_dir,
							txt_direccion, cod_municipio, cod_dpto, cod_pais,
							txt_edificio, txt_urbanizacion, txt_sector,
							campoin1, campoin2, campoin3, campoin4, campoin5);

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}
		
		System.out.println("ejecutro web service direccion");

		return respuesta;
	}
}
