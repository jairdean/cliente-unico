package com.equivida.sise.ws.impl;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.equivida.sise.rs.InsertDireccionContratante;
import com.equivida.sise.sp.InsertDireccionContratanteSp;
import com.equivida.sise.ws.InsertDireccionContratanteWs;

@Stateless(name = "InsertDireccionContratanteWs")
@WebService(endpointInterface = "com.equivida.sise.ws.InsertDireccionContratanteWs")
@Remote(InsertDireccionContratanteWs.class)
public class InsertDireccionContratanteWsImpl implements InsertDireccionContratanteWs {

	@EJB
	private InsertDireccionContratanteSp insertDireccionContratanteSp;

	@Override
	@WebMethod
	public @WebResult(name = "InsertDireccionContratante") InsertDireccionContratante spi_mpersona_dir_wkf(
			BigDecimal id_persona_wkf, BigDecimal cod_tipo_dir, String txt_direccion, BigDecimal cod_municipio,
			BigDecimal cod_dpto, BigDecimal cod_pais) {
		InsertDireccionContratante respuesta = null;
		try {
			respuesta = insertDireccionContratanteSp.llamarInsertDireccionContratanteSp(id_persona_wkf, cod_tipo_dir,
					txt_direccion, cod_municipio, cod_dpto, cod_pais);

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}

		System.out.println("ejecutro web service direccion");

		return respuesta;
	}
}
