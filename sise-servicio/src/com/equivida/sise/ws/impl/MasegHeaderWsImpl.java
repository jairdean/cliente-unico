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

import com.equivida.sise.rs.MasegHeader;
import com.equivida.sise.sp.MasegHeaderSp;
import com.equivida.sise.util.DateUtil;
import com.equivida.sise.ws.MasegHeaderWs;

@Stateless(name = "MasegHeaderWs")
@WebService(endpointInterface = "com.equivida.sise.ws.MasegHeaderWs")
@Remote(MasegHeaderWs.class)
public class MasegHeaderWsImpl implements MasegHeaderWs {

	@EJB
	private MasegHeaderSp masegHeaderSp;

	@Override
	@WebMethod
	public @WebResult(name = "MasegHeader")
	MasegHeader ws_sise_insert_maseg_header_edm(BigDecimal id_persona,
			BigDecimal cod_figura_aseg, BigDecimal cod_tipo_aseg,
			BigDecimal cod_imp_aseg, BigDecimal cod_tipo_agente,
			BigDecimal cod_agente, String fec_alta, String fec_baja,
			BigDecimal cod_ocupacion, Integer sn_aviso_vto,
			String cod_aseg_vinc, String fec_ult_modif, String cod_usuario,
			String txt_nom_factura, BigDecimal pje_tasa_fianzas,
			Integer sn_consorcio, BigDecimal cod_moneda, BigDecimal imp_sueldo,
			BigDecimal cod_deporte, String txt_edificio,
			String txt_urbanizacion, String txt_sector,
			String txt_nombres_conyugue, String txt_apellido_conyugue,
			BigDecimal cod_tipo_doc_conyugue, String nro_doc_conyugue,
			String campoin1, String campoin2, String campoin3, String campoin4,
			String campoin5) {

		MasegHeader respuesta = null;
		try {
			respuesta = masegHeaderSp.llamarMasegHeaderSp(id_persona,
					cod_figura_aseg, cod_tipo_aseg, cod_imp_aseg,
					cod_tipo_agente, cod_agente, DateUtil.getFecha(fec_alta),
					DateUtil.getFecha(fec_baja), cod_ocupacion, sn_aviso_vto,
					cod_aseg_vinc, DateUtil.getFecha(fec_ult_modif),
					cod_usuario, txt_nom_factura, pje_tasa_fianzas,
					sn_consorcio, cod_moneda, imp_sueldo, cod_deporte,
					txt_edificio, txt_urbanizacion, txt_edificio,
					txt_nombres_conyugue, txt_apellido_conyugue,
					cod_tipo_doc_conyugue, nro_doc_conyugue, campoin1,
					campoin2, campoin3, campoin4, campoin5);

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		} catch (ParseException e) {
			respuesta = new MasegHeader();
			respuesta.setMsgError("Formato de fecha incorrecto");
		}
		return respuesta;
	}
}
