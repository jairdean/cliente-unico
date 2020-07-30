package com.equivida.sise.sp;

import java.sql.SQLException;
import java.util.List;
import javax.ejb.Remote;

import com.equivida.sise.rs.RsConsultaProveedores;

@Remote
public interface ConsultaProveedoresSpRemoto {

	List<RsConsultaProveedores> llamarConsultaProveedoresSp(
			String nroDoc, Integer snActivo) throws SQLException;

}