package com.equivida.sise.sp;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.Remote;

import com.equivida.sise.rs.RsAsientoDiarioMayorDepreciacionMensual;

@Remote
public interface AsientoDiarioMayorDepreciacionMensualSpRemoto {

	List<RsAsientoDiarioMayorDepreciacionMensual> llamarAsientoDiarioMayorDepreciacionMensualSp(
			Integer empCodigo,
			Integer anio,
			Integer perCodigo,
			Integer idAsientos,
			Integer secuencia,
			Integer snMcb,
			BigDecimal codSistema,
			BigDecimal codDestino,
			BigDecimal nroAsiento,
			BigDecimal codCtaCb,
			String codDebCred,
			BigDecimal codAnalisis,
			BigDecimal codConcepto,
			BigDecimal codMoneda,
			BigDecimal impMo,
			BigDecimal impEq,
			BigDecimal impCambio,
			BigDecimal codSuc,
			BigDecimal nroCorrelaAnalisis,
			BigDecimal codCCosto,
			String txtDesc,
			String fecAnticuacion,
			String codClaveConcepto,
			String fecMov,
			String codTipoRegistro ) throws SQLException;

	

}