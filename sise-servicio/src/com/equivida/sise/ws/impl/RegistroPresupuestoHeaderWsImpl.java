package com.equivida.sise.ws.impl;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.equivida.sise.rs.RsRegistroPresupuestoHeader;
import com.equivida.sise.sp.RegistroPresupuestoHeaderSp;
import com.equivida.sise.ws.RegistroPresupuestoHeaderWs;

@Stateless(name = "RegistroPresupuestoHeaderWs")
@WebService(endpointInterface = "com.equivida.sise.ws.RegistroPresupuestoHeaderWs")
@Remote(RegistroPresupuestoHeaderWs.class)
public class RegistroPresupuestoHeaderWsImpl implements
		RegistroPresupuestoHeaderWs {

	@EJB
	private RegistroPresupuestoHeaderSp registroPresupuestoHeaderSp;

	@Override
	@WebMethod
	public @WebResult(name = "RegistroPresupuestoHeader")
	RsRegistroPresupuestoHeader llamarRegistroPresupuestoHeaderSp(
			BigDecimal i_imp_id_sesion, BigDecimal i_imp_nro_pol,
			BigDecimal i_nro_aseg, BigDecimal i_nro_pariente,
			BigDecimal i_imp_tipo_identifaseg, String i_txt_cedula,
			BigDecimal i_imp_cod_plan, BigDecimal i_imp_cod_red,
			BigDecimal i_imp_tipo_identifpres, String i_txt_identifpres,
			BigDecimal i_imp_cod_suc, BigDecimal i_imp_tipo_identifmdiag,
			String i_txt_identifmdiag, String i_tipo_reg) {

		RsRegistroPresupuestoHeader respuesta = null;
		try {
			respuesta = registroPresupuestoHeaderSp
					.llamarRegistroPresupuestoHeaderSp(i_imp_id_sesion,
							i_imp_nro_pol, i_nro_aseg, i_nro_pariente,
							i_imp_tipo_identifaseg, i_txt_cedula,
							i_imp_cod_plan, i_imp_cod_red,
							i_imp_tipo_identifpres, i_txt_identifpres,
							i_imp_cod_suc, i_imp_tipo_identifmdiag,
							i_txt_identifmdiag, i_tipo_reg);

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}

		return respuesta;
	}
}