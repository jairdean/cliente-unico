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

import com.equivida.sise.rs.RsCertificadoIndividual;
import com.equivida.sise.sp.CertificadoIndividualSp;
import com.equivida.sise.ws.CertificadoIndividualWs;
import com.equivida.sise.ws.ConsultaPolizasWs;

@Stateless(name = "CertificadoIndividualWs")
@WebService(endpointInterface = "com.equivida.sise.ws.CertificadoIndividualWs")
@Remote(ConsultaPolizasWs.class)
public class CertificadoIndividualWsImpl implements CertificadoIndividualWs {

	@EJB
	private CertificadoIndividualSp certificadoIndividualSp;

	@Override
	@WebMethod
	public @WebResult(name = "CertificadoIndividual")
	List<RsCertificadoIndividual> ws_sise_certificado_individual(
			BigDecimal cod_suc, BigDecimal cod_ramo, BigDecimal nro_pol,
			BigDecimal nro_aseg, String nro_doc, String campoin1,
			String campoin2, String campoin3, String campoin4, String campoin5) {
		List<RsCertificadoIndividual> respuesta = null;

		try {
			respuesta = certificadoIndividualSp.llamarCertificadoIndividualSp(
					cod_suc, cod_ramo, nro_pol, nro_aseg, nro_doc, campoin1,
					campoin2, campoin3, campoin4, campoin5);
		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}

		return respuesta;
	}
}