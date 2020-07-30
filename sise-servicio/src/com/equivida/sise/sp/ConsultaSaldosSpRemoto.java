package com.equivida.sise.sp;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import com.equivida.sise.rs.RsConsultaSaldos;

@Remote
public interface ConsultaSaldosSpRemoto {

	List<RsConsultaSaldos> llamarConsultaSaldosSp(
			BigDecimal tipo_identificacion, String identificacion,
			BigDecimal tipo_identificacion_medico,
			String identificacion_medico, Integer sn_pagados)
			throws SQLException;
}