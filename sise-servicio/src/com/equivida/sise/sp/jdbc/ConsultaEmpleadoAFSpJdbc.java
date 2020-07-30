package com.equivida.sise.sp.jdbc;

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

import com.equivida.sise.rs.RsConsultaEmpleadoAF;
import com.equivida.sise.sp.ConsultaEmpleadoAFSp;
import com.equivida.sise.sp.ConsultaEmpleadoAFSpRemoto;

@Stateless(name = "ConsultaEmpleadoAFSp")
public class ConsultaEmpleadoAFSpJdbc implements ConsultaEmpleadoAFSp,
		ConsultaEmpleadoAFSpRemoto {

	// @Resource(mappedName = "java:buxisDS")
	// private DataSource dataSource;

	@Resource(mappedName = "java:crm-sql")
	private DataSource dataSource;

	private Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<RsConsultaEmpleadoAF> consultaEmpleadoAFSp(String cedula)
			throws SQLException {
		PreparedStatement pstmt = null;
		List<RsConsultaEmpleadoAF> respuesta = null;
		ResultSet rs = null;
		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		try {
			pstmt = conn.prepareStatement("{call dbo.consultaEmpleado(?)}");
			pstmt.setString(1, cedula);

			int count = 0;

			boolean tieneResulset = pstmt.execute();
			System.out.println("tiene:" + tieneResulset);

			respuesta = new ArrayList<RsConsultaEmpleadoAF>();
			while (count >= 0) {
				if (tieneResulset) {
					rs = pstmt.getResultSet();

					while (rs.next()) {
						RsConsultaEmpleadoAF aux = new RsConsultaEmpleadoAF();
						aux.setTipoDocumento(rs.getString(1));
						aux.setCedula(rs.getString(2));
						aux.setPrimerNombre(rs.getString(3));
						aux.setSegundoNombre(rs.getString(4));
						aux.setPrimerApellido(rs.getString(5));
						aux.setSegundoApellido(rs.getString(6));
						aux.setFechaIngreso(rs.getString(7));
						aux.setFechaEgreso(rs.getString(8));
						aux.setNombre(rs.getString(9));
						aux.setDireccion(rs.getString(10));
						aux.setEmail(rs.getString(11));
						aux.setTelefono(rs.getString(12));

						respuesta.add(aux);
					}
					rs.close();
				} else {
					count = pstmt.getUpdateCount();
				}// end if-else tieneResulset
				if (count >= 0) {
					// procesa de acuerdo al contador de actualización, lo
					// ignoramos...
				}
				tieneResulset = pstmt.getMoreResults();

			}// end while count

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