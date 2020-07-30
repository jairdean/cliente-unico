package com.equivida.sise.sp.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.sql.DataSource;

import com.equivida.sise.rs.RsMetodoPago;
import com.equivida.sise.sp.MetodoPagoSp;
import com.equivida.sise.sp.MetodoPagoSpRemoto;

@Stateless(name = "MetodoPagoSp")
public class MetodoPagoSpJdbc implements MetodoPagoSp, MetodoPagoSpRemoto {

	@Resource(mappedName = "java:sise4WEBDS")
	private DataSource dataSource;
	Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public RsMetodoPago llamarMetodoPagoSp(BigDecimal i_modo,
			BigDecimal i_id_session, BigDecimal i_cod_procedimiento,
			BigDecimal i_nro_op, BigDecimal i_nro_factura,
			BigDecimal i_nro_pieza_dental) throws SQLException {
		PreparedStatement pstmt = null;
		RsMetodoPago respuesta = null;
		ResultSet rs = null;
		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		try {
			pstmt = conn
					.prepareStatement("{call sp_presupuesto_pago_den(?,?,?,?,?,?)}");
			pstmt.setBigDecimal(1, i_modo);
			pstmt.setBigDecimal(2, i_id_session);
			pstmt.setBigDecimal(3, i_cod_procedimiento);
			pstmt.setBigDecimal(4, i_nro_op);
			pstmt.setBigDecimal(5, i_nro_factura);
			pstmt.setBigDecimal(6, i_nro_pieza_dental);

			pstmt.setQueryTimeout(420);
			rs = pstmt.executeQuery();

			respuesta = new RsMetodoPago();
			System.out.println("in  JDBC " + rs);

			while (rs.next()) {
				respuesta.setGrabo(rs.getString(1));
				respuesta.setMessage(rs.getString(2));
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

		return respuesta;

	}
}