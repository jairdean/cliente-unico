package com.equivida.sise.sp;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import com.equivida.sise.rs.RsBeneficiarios;

@Remote
public interface BeneficiariosSpRemoto {

	List<RsBeneficiarios> llamarBeneficiariosSp(BigDecimal cod_suc,
			BigDecimal cod_ramo, BigDecimal nro_pol, BigDecimal cod_aseg,
			String campoin1, String campoin2, String campoin3, String campoin4,
			String campoin5) throws SQLException;
}