package com.equivida.sise.sp;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import com.equivida.sise.rs.RsConsultaSaldos;

@Local
public interface ConsultaSaldosSp {

	List<RsConsultaSaldos> llamarConsultaSaldosSp(
			BigDecimal tipo_identificacion, String identificacion,
			BigDecimal tipo_identificacion_medico,
			String identificacion_medico, Integer sn_pagados)
			throws SQLException;
}