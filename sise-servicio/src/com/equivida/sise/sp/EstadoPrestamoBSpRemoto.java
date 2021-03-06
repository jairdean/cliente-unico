package com.equivida.sise.sp;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ejb.Remote;

import com.equivida.sise.rs.RsEstadoPrestamo;

@Remote
public interface EstadoPrestamoBSpRemoto {

	ArrayList<RsEstadoPrestamo> llamarEstadoPrestamoSp(Integer cod_suc,
			Integer cod_ramo, BigDecimal nro_pol, Integer sn_activos,
			String campoin1, String campoin2, String campoin3, String campoin4,
			String campoin5) throws SQLException;
}