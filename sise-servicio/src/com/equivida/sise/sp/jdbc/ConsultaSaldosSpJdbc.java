package com.equivida.sise.sp.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.sql.DataSource;

import com.equivida.sise.rs.RsConsultaSaldos;
import com.equivida.sise.sp.ConsultaSaldosSp;
import com.equivida.sise.sp.ConsultaSaldosSpRemoto;

@Stateless(name = "ConsultaSaldosSp")
public class ConsultaSaldosSpJdbc implements ConsultaSaldosSp,
		ConsultaSaldosSpRemoto {

	//@Resource(mappedName = "java:siseWEBDS")
	@Resource(mappedName = "java:sise4WEBDS")
	private DataSource dataSource;
	Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<RsConsultaSaldos> llamarConsultaSaldosSp(
			BigDecimal tipo_identificacion, String identificacion,
			BigDecimal tipo_identificacion_medico,
			String identificacion_medico, Integer sn_pagados)
			throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<RsConsultaSaldos> lista = new ArrayList<RsConsultaSaldos>();
		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		try {
			pstmt = conn.prepareStatement("{call sp_saldos_den(?,?,?,?,?)}");
			pstmt.setBigDecimal(1, tipo_identificacion);
			pstmt.setString(2, identificacion);
			pstmt.setBigDecimal(3, tipo_identificacion_medico);
			pstmt.setString(4, identificacion_medico);
			pstmt.setInt(5, sn_pagados);

			pstmt.setQueryTimeout(420);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				RsConsultaSaldos respuesta = new RsConsultaSaldos();
				respuesta.setSn_grabo(rs.getString(1));
				respuesta.setMensaje(rs.getString(2));
				respuesta.setPresupuesto(rs.getBigDecimal(3));
				respuesta.setPaciente(rs.getString(4));
				respuesta.setProcedimiento(rs.getString(5));
				respuesta.setImp_pvp(rs.getBigDecimal(6));
				respuesta.setImp_valor_eq(rs.getBigDecimal(7));
				respuesta.setImp_valor_cubierto(rs.getBigDecimal(8));
				respuesta.setImp_co_pago(rs.getBigDecimal(9));
				respuesta.setFec_atencion(rs.getString(10));
				respuesta.setFec_pago(rs.getString(11));
				respuesta.setCod_pres(rs.getBigDecimal(12));
				respuesta.setMedico(rs.getString(13));
				
				lista.add(respuesta);
			}

		} catch (SQLException e) {
			System.err.println("SQLException: " + e.getErrorCode()
					+ e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
			} finally {
				try {
					if (pstmt != null)
						pstmt.close();
				} finally {
					conn.close();
				}
			}
		}

		return lista;

	}
}