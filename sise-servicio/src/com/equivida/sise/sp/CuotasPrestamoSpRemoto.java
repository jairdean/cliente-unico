package com.equivida.sise.sp;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ejb.Remote;

import com.equivida.sise.rs.RsCuotasPrestamo;

@Remote
public interface CuotasPrestamoSpRemoto {

	ArrayList<RsCuotasPrestamo> llamarCuotasPrestamoSp(Integer nro_perstamo,
			String campoin1, String campoin2, String campoin3, String campoin4,
			String campoin5) throws SQLException;
}