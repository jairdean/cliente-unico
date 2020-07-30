package com.equivida.sise.ws.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.equivida.sise.rs.RsAsientoDiarioMayorDepreciacionMensual;
import com.equivida.sise.sp.AsientoDiarioMayorDepreciacionMensualSp;
import com.equivida.sise.ws.AsientoDiarioMayorDepreciacionMensualWs;

@Stateless(name = "AsientoDiarioMayorDepreciacionMensualWs")
@WebService(endpointInterface = "com.equivida.sise.ws.AsientoDiarioMayorDepreciacionMensualWs")
@Remote(AsientoDiarioMayorDepreciacionMensualWs.class)
public class AsientoDiarioMayorDepreciacionMensualWsImpl implements
		AsientoDiarioMayorDepreciacionMensualWs {

	@EJB
	private AsientoDiarioMayorDepreciacionMensualSp asientoDiarioMayorDepreciacionMensualSp;

	@Override
	@WebMethod
	public @WebResult(name = "AsientoDiarioMayorDepreciacionMensual")
	List<RsAsientoDiarioMayorDepreciacionMensual> llamarAsientoDiarioMayorDepreciacionMensualSp(
			String usuario, String clave, Integer empCodigo, Integer anio,
			Integer perCodigo, Integer idAsientos, Integer secuencia,
			Integer snMcb, BigDecimal codSistema, BigDecimal codDestino,
			BigDecimal nroAsiento, BigDecimal codCtaCb, String codDebCred,
			BigDecimal codAnalisis, BigDecimal codConcepto,
			BigDecimal codMoneda, BigDecimal impMo, BigDecimal impEq,
			BigDecimal impCambio, BigDecimal codSuc,
			BigDecimal nroCorrelaAnalisis, BigDecimal codCCosto,
			String txtDesc, String fecAnticuacion, String codClaveConcepto,
			String fecMov, String codTipoRegistro) {

		List<RsAsientoDiarioMayorDepreciacionMensual> respuesta = null;
		String usuarioServer = "";
		String claveServer = "";

		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(
					"../server/equivida/conf/wsActFijoUsers.properties"));

			usuarioServer = prop.getProperty("usuario");
			claveServer = prop.getProperty("clave");

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		try {
			if (usuario.equals(usuarioServer) && clave.equals(claveServer)) {

				respuesta = asientoDiarioMayorDepreciacionMensualSp
						.llamarAsientoDiarioMayorDepreciacionMensualSp(
								empCodigo, anio, perCodigo, idAsientos,
								secuencia, snMcb, codSistema, codDestino,
								nroAsiento, codCtaCb, codDebCred, codAnalisis,
								codConcepto, codMoneda, impMo, impEq,
								impCambio, codSuc, nroCorrelaAnalisis,
								codCCosto, txtDesc, fecAnticuacion,
								codClaveConcepto, fecMov, codTipoRegistro);
			} else {
				RsAsientoDiarioMayorDepreciacionMensual r = new RsAsientoDiarioMayorDepreciacionMensual();
				r.setTxtError("Credenciales Incorrrectas");
				respuesta = new ArrayList<RsAsientoDiarioMayorDepreciacionMensual>();
				respuesta.add(r);
			}
		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}

		return respuesta;
	}
}