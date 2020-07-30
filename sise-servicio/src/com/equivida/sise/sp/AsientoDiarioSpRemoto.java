package com.equivida.sise.sp;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.Remote;

import com.equivida.sise.rs.RsAsientoDiario;

@Remote
public interface AsientoDiarioSpRemoto {

	List<RsAsientoDiario> llamarAsientoDiarioSp(Integer empCodigo,
			String nroFactura, Integer codigoProveedor, Integer tipo,
			Integer codSuc, String CodCtaCb, String codDebCred,
			BigDecimal impMo, BigDecimal impEq, BigDecimal codMoneda,
			BigDecimal impCambio, Integer codAnalisis,
			BigDecimal codConcepto, String codClaveConcepto,
			String txtDesc, String fecha, BigDecimal codValor,
			BigDecimal numValor, BigDecimal codCCosto, Integer usrCodigo,
			Integer secuencia, String codTipoRegistro) throws SQLException;
}