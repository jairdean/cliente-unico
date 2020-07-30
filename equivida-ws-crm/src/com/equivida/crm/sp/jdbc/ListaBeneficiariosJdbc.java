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

import com.equivida.crm.rs.ListaBeneficiariosRs;
import com.equivida.crm.sp.ListaBeneficiariosSp;
import com.equivida.crm.sp.ListaBeneficiariosSpRemoto;
import com.equivida.crm.util.EncodingUtil;

/**
 * @author saviasoft5
 * 
 */
@Stateless(name = "ListaBeneficiariosSp")
public class ListaBeneficiariosJdbc implements ListaBeneficiariosSp,
		ListaBeneficiariosSpRemoto {

	// @Resource(mappedName = "java:crm")
	// private DataSource dataSource;

	@Resource(mappedName = "java:crm-sql")
	private DataSource dataSource;

	private Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ListaBeneficiariosRs> listaBeneficiariosSp(
			Integer codigoSucursal, Integer ramo, Long numeroPoliza)
			throws SQLException {

		System.out.println("SP: sp_lista_benef_pol");

		PreparedStatement pstmt = null;
		List<ListaBeneficiariosRs> respuesta = null;
		ResultSet rs = null;

		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		try {
			pstmt = conn.prepareStatement("{call sp_lista_benef_pol(?,?,?)}");

			pstmt.setInt(1, codigoSucursal);
			pstmt.setInt(2, ramo);
			pstmt.setLong(3, numeroPoliza);
			pstmt.setQueryTimeout(600);
			int count = 0;

			boolean tieneResulset = pstmt.execute();
			System.out.println("tiene:" + tieneResulset);

			respuesta = new ArrayList<ListaBeneficiariosRs>();
			while (count >= 0) {
				if (tieneResulset) {
					rs = pstmt.getResultSet();

					while (rs.next()) {
						ListaBeneficiariosRs aux = new ListaBeneficiariosRs();

						aux.setNumero_Poliza(rs.getBigDecimal(1));
						aux.setTipo(rs.getString(2));
						aux.setPrimer_Nombre(EncodingUtil.encodeCP850toUTF8(rs
								.getString(3)));
						aux.setSegundo_Nombre(rs.getString(4));
						aux.setApellido_Paterno(rs.getString(5));
						aux.setApellido_Materno(rs.getString(6));
						aux.setParentezco(rs.getString(7));
						aux.setPorcentaje(rs.getBigDecimal(8));
						aux.setRelacion_con_contratante(rs.getString(9));
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
