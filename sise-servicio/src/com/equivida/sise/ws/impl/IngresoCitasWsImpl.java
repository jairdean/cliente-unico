package com.equivida.sise.ws.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.equivida.sise.rs.RsIngresoCitas;
import com.equivida.sise.sp.IngresoCitasSp;
import com.equivida.sise.ws.IngresoCitasWs;

@Stateless(name = "IngresoCitasWs")
@WebService(endpointInterface = "com.equivida.sise.ws.IngresoCitasWs")
@Remote(IngresoCitasWs.class)
public class IngresoCitasWsImpl implements IngresoCitasWs {

	@EJB
	private IngresoCitasSp ingresoCitasSp;

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	@Override
	@WebMethod
	public @WebResult(name = "IngresoCitas")
	List<RsIngresoCitas> llamarIngresoCitasSp(BigDecimal i_imp_modo,
			BigDecimal i_imp_tipo_doc, String i_txt_nro_ced,
			BigDecimal i_imp_cod_aseg, String i_txt_apellido1,
			String i_txt_apellido2) {
		List<RsIngresoCitas> lista = new ArrayList<RsIngresoCitas>();
		try {
			lista = ingresoCitasSp.llamarIngresoCitasSp(i_imp_modo,
					i_imp_tipo_doc, i_txt_nro_ced, i_imp_cod_aseg,
					i_txt_apellido1, i_txt_apellido2);

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}

		return lista;
	}
}