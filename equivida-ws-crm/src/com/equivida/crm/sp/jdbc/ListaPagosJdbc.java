/**
 * 
 */
package com.equivida.crm.sp.jdbc;

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

import com.equivida.crm.rs.ListaPagosRs;
import com.equivida.crm.sp.ListaPagosSp;
import com.equivida.crm.sp.ListaPagosSpRemoto;

/**
 * @author saviasoft5
 * 
 */
@Stateless(name = "ListaPagosSp")
public class ListaPagosJdbc implements ListaPagosSp, ListaPagosSpRemoto {

	// @Resource(mappedName = "java:crm")
	// private DataSource dataSource;

	@Resource(mappedName = "java:crm-sql")
	private DataSource dataSource;

	private Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ListaPagosRs> listaPagosSp(Integer idPersona, Integer tipo,
			String numeroDocumento) throws SQLException {

		System.out.println("SP: sp_lista_ctas_pagos");

		PreparedStatement pstmt = null;
		List<ListaPagosRs> respuesta = null;
		ResultSet rs = null;

		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		try {
			String prmSql = "EXEC sp_lista_ctas_pagos ";
			if (idPersona != null) {
				prmSql = prmSql + "@id_persona = " + idPersona + ",";
			}
			if (tipo != null && numeroDocumento != null) {
				prmSql = prmSql + "@tipo_doc = " + tipo + ", @nro_doc = '"
						+ numeroDocumento + "'";
			} else {
				prmSql = prmSql.substring(0, prmSql.length() - 1);
			}

			pstmt = conn.prepareStatement(prmSql);

			pstmt.setQueryTimeout(600);
			int count = 0;

			boolean tieneResulset = pstmt.execute();
			System.out.println("tiene:" + tieneResulset);

			respuesta = new ArrayList<ListaPagosRs>();
			while (count >= 0) {
				if (tieneResulset) {
					rs = pstmt.getResultSet();

					while (rs.next()) {
						ListaPagosRs aux = new ListaPagosRs();

						aux.setSecuencial_persona(rs.getInt(1));
						aux.setNumero_poliza(rs.getBigDecimal(2));
						aux.setFecha_pago(rs.getString(3));
						aux.setValor_pago(rs.getBigDecimal(4));
						aux.setNo_transaccion(rs.getInt(5));
						aux.setMes(rs.getInt(6));
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
