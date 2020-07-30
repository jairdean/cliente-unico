package com.equivida.sise.sp;

import java.sql.SQLException;
import java.util.List;
import javax.ejb.Local;
import com.equivida.sise.rs.RsConsultaProveedores;

@Local
public interface ConsultaProveedoresSp {

	List<RsConsultaProveedores> llamarConsultaProveedoresSp(
			String nroDoc, Integer snActivo) throws SQLException;

}