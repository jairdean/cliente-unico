package com.equivida.sise.sp;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ejb.Remote;

import com.equivida.sise.rs.RsEstadoPrestamo;

@Remote
public interface EstadoPrestamoSpRemoto {

	ArrayList<RsEstadoPrestamo> llamarEstadoPrestamoSp(Integer cod_tipo_doc,
			String nro_doc, Integer sn_activos, String campoin1,
			String campoin2, String campoin3, String campoin4, String campoin5)
			throws SQLException;
}