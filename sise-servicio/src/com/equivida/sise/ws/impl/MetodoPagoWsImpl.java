package com.equivida.sise.ws.impl;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.equivida.sise.rs.RsMetodoPago;
import com.equivida.sise.sp.MetodoPagoSp;
import com.equivida.sise.ws.MetodoPagoWs;

@Stateless(name = "MetodoPagoWs")
@WebService(endpointInterface = "com.equivida.sise.ws.MetodoPagoWs")
@Remote(MetodoPagoWs.class)
public class MetodoPagoWsImpl implements MetodoPagoWs {

	@EJB
	private MetodoPagoSp metodoPagoSp;

	@Override
	@WebMethod
	public @WebResult(name = "MetodoPago")
	RsMetodoPago llamarMetodoPagoSp(BigDecimal i_modo, BigDecimal i_id_session,
			BigDecimal i_cod_procedimiento, BigDecimal i_nro_op,
			BigDecimal i_nro_factura, BigDecimal i_nro_pieza_dental) {

		RsMetodoPago respuesta = null;
		try {
			respuesta = metodoPagoSp.llamarMetodoPagoSp(i_modo, i_id_session,
					i_cod_procedimiento, i_nro_op, i_nro_factura,
					i_nro_pieza_dental);

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}

		return respuesta;
	}
}