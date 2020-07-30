package com.equivida.sise.ws.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.equivida.sise.rs.RsAsientoDiario;
import com.equivida.sise.sp.AsientoDiarioSp;
import com.equivida.sise.ws.AsientoDiarioWs;

@Stateless(name = "AsientoDiarioWs")
@WebService(endpointInterface = "com.equivida.sise.ws.AsientoDiarioWs")
@Remote(AsientoDiarioWs.class)
public class AsientoDiarioWsImpl implements AsientoDiarioWs {

	@EJB
	private AsientoDiarioSp asientoDiarioSp;

	@Override
	@WebMethod
	public @WebResult(name = "AsientoDiario")
	List<RsAsientoDiario> llamarAsientoDiarioSp(Integer empCodigo,
			String nroFactura, Integer codigoProveedor, Integer tipo,
			Integer codSuc, String CodCtaCb, String codDebCred,
			BigDecimal impMo, BigDecimal impEq, BigDecimal codMoneda,
			BigDecimal impCambio, Integer codAnalisis,
			BigDecimal codConcepto, String codClaveConcepto,
			String txtDesc, String fecha, BigDecimal codValor,
			BigDecimal numValor, BigDecimal codCCosto, Integer usrCodigo,
			Integer secuencia, String codTipoRegistro) {

		List<RsAsientoDiario> respuesta = null;

		try {
			respuesta = asientoDiarioSp.llamarAsientoDiarioSp(empCodigo,
					nroFactura, codigoProveedor, tipo, codSuc, CodCtaCb,
					codDebCred, impMo, impEq, codMoneda, impCambio,
					codAnalisis, codConcepto, codClaveConcepto, txtDesc, fecha,
					codValor, numValor, codCCosto, usrCodigo, secuencia,
					codTipoRegistro);
		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}

		return respuesta;
	}
}