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

import com.equivida.sise.rs.RsIngresoSucursales;
import com.equivida.sise.sp.IngresoSucursalesSp;
import com.equivida.sise.sp.IngresoSucursalesSpRemoto;

@Stateless(name = "IngresoSucursalesSp")
public class IngresoSucuralesSpJdbc implements IngresoSucursalesSp,
		IngresoSucursalesSpRemoto {

	@Resource(mappedName = "java:sise4WEBDS")
	private DataSource dataSource;
	Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public RsIngresoSucursales llamarIngresSucuralesSp(BigDecimal codPres,
			String sucursal, String direccion, String telefono,
			BigDecimal ubicacion, BigDecimal municipio,
			BigDecimal departamento, BigDecimal modo, BigDecimal codSucursal,
			BigDecimal tipoIdPres, String nroIdPress) throws SQLException {
		PreparedStatement pstmt = null;
		RsIngresoSucursales respuesta = null;
		ResultSet rs = null;

		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		try {
			pstmt = conn
					.prepareStatement("{call sp_insertpresta_den(?,?,?,?,?,?,?,?,?,?,?)}");

			pstmt.setBigDecimal(1, codPres);
			pstmt.setString(2, sucursal);
			pstmt.setString(3, direccion);
			pstmt.setString(4, telefono);
			pstmt.setBigDecimal(5, ubicacion);
			pstmt.setBigDecimal(6, municipio);
			pstmt.setBigDecimal(7, departamento);
			pstmt.setBigDecimal(8, modo);
			pstmt.setBigDecimal(9, codSucursal);
			pstmt.setBigDecimal(10, tipoIdPres);
			pstmt.setString(11, nroIdPress);

			pstmt.setQueryTimeout(420);
			rs = pstmt.executeQuery();

			respuesta = new RsIngresoSucursales();

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