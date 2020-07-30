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

import com.equivida.sise.rs.RsConsultaProveedores;
import com.equivida.sise.sp.ConsultaProveedoresSp;
import com.equivida.sise.sp.ConsultaProveedoresSpRemoto;

@Stateless(name = "ConsultaProveedoresSp")
public class ConsultaProveedoresSpJdbc implements ConsultaProveedoresSp,
		ConsultaProveedoresSpRemoto {

	@Resource(mappedName = "java:siseAF")
	private DataSource dataSource;
	private Connection conn;

	//@Resource(mappedName = "java:crm-sql")
	//private DataSource dataSource;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<RsConsultaProveedores> llamarConsultaProveedoresSp(
			String nroDoc, Integer snActivo) throws SQLException {

		PreparedStatement pstmt = null;
		List<RsConsultaProveedores> respuesta = null;
		ResultSet rs = null;
		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		try {

			StringBuffer sql = new StringBuffer("EXEC sp_ws_cons_proveedores ");

			if (nroDoc != null && nroDoc.length() > 0) {
				sql.append(" @nro_doc = '" + nroDoc + "',");
			}
			if (snActivo != null) {
				sql.append(" @sn_activo = " + snActivo + ",");
			}

			sql = new StringBuffer(sql.toString()
					.substring(0, sql.length() - 1));

			pstmt = conn.prepareStatement(sql.toString());

			int count = 0;

			boolean tieneResulset = pstmt.execute();
			System.out.println("tiene:" + tieneResulset);

			respuesta = new ArrayList<RsConsultaProveedores>();
			while (count >= 0) {
				if (tieneResulset) {
					rs = pstmt.getResultSet();

					while (rs.next()) {
						RsConsultaProveedores aux = new RsConsultaProveedores();

						aux.setIdPersona(rs.getBigDecimal(1));
						aux.setNumNit(rs.getString(2));
						aux.setTxtApellido1(rs.getString(3));
						aux.setTxtApellido2(rs.getString(4));
						aux.setTxtNombre(rs.getString(5));
						aux.setCodTipoDoc(rs.getBigDecimal(6));
						aux.setNroDoc(rs.getString(7));
						aux.setCodTipoIva(rs.getBigDecimal(8));
						aux.setTxtCiaTra(rs.getString(9));
						aux.setTxtDptoTra(rs.getString(10));
						aux.setTxtPuestoTra(rs.getString(11));
						aux.setTxtAsistenteTra(rs.getString(12));
						aux.setFecNac(rs.getString(13));
						aux.setTxtLugarNac(rs.getString(14));
						aux.setTxtSexo(rs.getString(15));
						aux.setCodEstCivil(rs.getString(16));
						aux.setTxtNotas(rs.getString(17));
						aux.setCodTipoPersona(rs.getString(18));
						aux.setTxtOrigen(rs.getString(19));
						aux.setCodPaisOrigen(rs.getBigDecimal(20));
						aux.setCodGrupoAsoc(rs.getBigDecimal(21));
						aux.setFatCrm(rs.getString(22));

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