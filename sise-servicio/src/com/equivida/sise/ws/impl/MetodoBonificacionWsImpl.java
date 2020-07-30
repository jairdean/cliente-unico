package com.equivida.sise.ws.impl;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.equivida.sise.rs.RsMetodoBonificacion;
import com.equivida.sise.sp.MetodoBonificacionSp;
import com.equivida.sise.ws.MetodoBonificacionWs;

@Stateless(name = "MetodoBonificacionWs")
@WebService(endpointInterface = "com.equivida.sise.ws.MetodoBonificacionWs")
@Remote(MetodoBonificacionWs.class)
public class MetodoBonificacionWsImpl implements MetodoBonificacionWs {

	@EJB
	private MetodoBonificacionSp metodoBonificacionSp;

	@Override
	@WebMethod
	public @WebResult(name = "MetodoBonificacion")
	RsMetodoBonificacion llamarMetodoBonificacionSp(BigDecimal i_modo,
			BigDecimal i_id_session, BigDecimal i_cod_procedimiento,
			BigDecimal i_cod_tipo_doc, String i_nro_doc, BigDecimal i_nro_op,
			BigDecimal i_nro_pieza_dental) {

		RsMetodoBonificacion respuesta = null;
		try {
			respuesta = metodoBonificacionSp.llamarMetodoBonificacionSp(i_modo,
					i_id_session, i_cod_procedimiento, i_cod_tipo_doc,
					i_nro_doc, i_nro_op, i_nro_pieza_dental);

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}

		return respuesta;
	}
}