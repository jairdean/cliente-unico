package com.equivida.sise.sp;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import com.equivida.sise.rs.RsCumulosDePago;

@Local
public interface CumulosDePagoSp {

	List<RsCumulosDePago> consultar(String numeroDocumento)
			throws SQLException;
}