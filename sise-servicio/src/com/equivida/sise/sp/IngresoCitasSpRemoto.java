package com.equivida.sise.sp;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import com.equivida.sise.rs.RsIngresoCitas;

@Remote
public interface IngresoCitasSpRemoto {

	List<RsIngresoCitas> llamarIngresoCitasSp(BigDecimal i_imp_modo,
			BigDecimal i_imp_tipo_doc, String i_txt_nro_ced,
			BigDecimal i_imp_cod_aseg, String i_txt_apellido1,
			String i_txt_apellido2) throws SQLException;
}