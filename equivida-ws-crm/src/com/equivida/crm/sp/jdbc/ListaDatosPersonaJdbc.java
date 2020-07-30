/**
 * 
 */
package com.equivida.crm.sp.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.sql.DataSource;

import com.equivida.crm.rs.ListaDatosPersonaRs;
import com.equivida.crm.sp.ListaDatosPersonaSp;
import com.equivida.crm.sp.ListaDatosPersonaSpRemoto;

/**
 * @author saviasoft5
 * 
 */
@Stateless(name = "ListaDatosPersonaSp")
public class ListaDatosPersonaJdbc implements ListaDatosPersonaSp,
		ListaDatosPersonaSpRemoto {

	// @Resource(mappedName = "java:crm")
	// private DataSource dataSource;

	@Resource(mappedName = "java:crm-sql")
	private DataSource dataSource;

	private Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ListaDatosPersonaRs listaDatosPersonaSp(Integer idPersona,
			String numeroDocumento) throws SQLException {

		System.out.println("SP: sp_lista_datos_persona");

		PreparedStatement pstmt = null;
		ListaDatosPersonaRs respuesta = null;
		ResultSet rs = null;

		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}

		try {
			pstmt = conn.prepareStatement("{call sp_lista_datos_persona(?,?)}");

			pstmt.setInt(1, idPersona);
			pstmt.setString(2, numeroDocumento);
			pstmt.setQueryTimeout(600);
			int count = 0;

			boolean tieneResulset = pstmt.execute();
			System.out.println("tiene:" + tieneResulset);

			while (count >= 0) {
				if (tieneResulset) {
					rs = pstmt.getResultSet();

					while (rs.next()) {
						respuesta = new ListaDatosPersonaRs();
						System.out.println(rs.getObject(1).toString());
						System.out.println(rs.getObject(1).getClass());
						respuesta.setSecuencial_Persona(rs.getInt(1));
						respuesta.setBroker(rs.getInt(2));
						respuesta.setContacto_Broker(rs.getString(3));
						respuesta.setCanal_Venta(rs.getString(4));
						respuesta.setAsesor_Comercial(rs.getString(5));
						respuesta.setAsesor_Post_Venta(rs.getString(6));
						respuesta.setDirector(rs.getString(7));
						respuesta.setAsistente(rs.getString(8));
						respuesta.setSegmento_Cliente(rs.getString(9));
						respuesta.setInformacion_Siniestros(rs.getString(10));
						respuesta.setImp_stros(rs.getBigDecimal(11));
						respuesta.setNivel_Riesgo_Cliente(rs.getString(12));
						respuesta.setBeneficios_Externos(rs.getString(13));
						respuesta.setCliente_Desde(rs.getString(14));
						respuesta.setCliente_individual(rs.getString(15));
						respuesta.setAsegurado_de_empresa_cliente(rs
								.getString(16));
						respuesta.setContacto_empresa(rs.getString(17));
						respuesta.setContacto_Broker_2(rs.getString(18));
						respuesta.setInactivo(rs.getString(19));
						respuesta.setValor_Cliente(rs.getBigDecimal(20));
						break;
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
					if(conn!=null){
						conn.close();
					}
				}
			}
		}
		return respuesta;
	}

}
