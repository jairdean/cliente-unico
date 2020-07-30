package com.equivida.sise.ws.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.equivida.sise.rs.RsBeneficiarios;
import com.equivida.sise.sp.BeneficiariosSp;
import com.equivida.sise.ws.BeneficiarioWs;

@Stateless(name = "BeneficiarioWs")
@WebService(endpointInterface = "com.equivida.sise.ws.BeneficiarioWs")
@Remote(BeneficiarioWs.class)
public class BeneficiariosWsImpl implements BeneficiarioWs {

	@EJB
	private BeneficiariosSp beneficiariosSp;

	@Override
	@WebMethod
	public @WebResult(name = "Beneficiarios")
	List<RsBeneficiarios> ws_sise_beneficiarios(BigDecimal cod_suc,
			BigDecimal cod_ramo, BigDecimal nro_pol, BigDecimal cod_aseg,
			String campoin1, String campoin2, String campoin3, String campoin4,
			String campoin5) {

		List<RsBeneficiarios> respuesta = new ArrayList<RsBeneficiarios>();
		try {
			respuesta = beneficiariosSp.llamarBeneficiariosSp(cod_suc,
					cod_ramo, nro_pol, cod_aseg, campoin1, campoin2, campoin3,
					campoin4, campoin5);

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}

		return respuesta;
	}
}