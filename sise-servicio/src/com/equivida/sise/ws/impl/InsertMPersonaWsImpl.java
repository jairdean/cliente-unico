package com.equivida.sise.ws.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.equivida.sise.rs.RespuestaInsertMPersona;
import com.equivida.sise.sp.InsertMPersonaSp;
import com.equivida.sise.util.DateUtil;
import com.equivida.sise.ws.InsertMPersonaWs;

@Stateless(name = "InsertMPersonaWs")
@WebService(endpointInterface = "com.equivida.sise.ws.InsertMPersonaWs")
@Remote(InsertMPersonaWs.class)
public class InsertMPersonaWsImpl implements InsertMPersonaWs {

	@EJB
	private InsertMPersonaSp insertMPersonaSp;

	@Override
	@WebMethod
	public @WebResult(name = "RespuestaInsertMPersona") RespuestaInsertMPersona ws_sise_insert_mpersona(
			String txt_apellido1, String txt_apellido2, String txt_nombre, BigDecimal cod_tipo_doc, String nro_doc,
			BigDecimal cod_tipo_iva, String nro_nit, String fec_nac, String txt_lugar_nac, String txt_sexo,
			BigDecimal cod_est_civil, String cod_tipo_persona, String txt_origen, String txt_nombres_conyuge,
			String txt_apellidos_conyuge, BigDecimal cod_tipo_doc_conyuge, String nro_doc_conyuge, String campo_in_1,
			String campo_in_2, String campo_in_3, String campo_in_4, String campo_in_5) {

		RespuestaInsertMPersona respuesta = null;
		
		
		try {
			respuesta = insertMPersonaSp.llamarSp(txt_apellido1, txt_apellido2, txt_nombre, cod_tipo_doc, nro_doc,
					cod_tipo_iva, nro_nit, DateUtil.getFecha(fec_nac), txt_lugar_nac, txt_sexo, cod_est_civil,
					cod_tipo_persona, txt_origen, txt_nombres_conyuge, txt_apellidos_conyuge, cod_tipo_doc_conyuge,
					nro_doc_conyuge, campo_in_1, campo_in_2, campo_in_3, campo_in_4, campo_in_5);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ERROR:" + e);
		} catch (ParseException e) {
			e.printStackTrace();
			respuesta = new RespuestaInsertMPersona();
			respuesta.setTxtError("Formato de fecha incorrecto.");
		}
		return respuesta;
	}
}
