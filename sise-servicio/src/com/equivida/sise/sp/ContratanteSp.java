package com.equivida.sise.sp;

import java.sql.SQLException;

import javax.ejb.Local;

import com.equivida.sise.dto.ContratanteDto;
import com.equivida.sise.rs.RsContratante;

@Local
public interface ContratanteSp {

	RsContratante llamarContratanteSp(ContratanteDto contratante) throws SQLException;

}