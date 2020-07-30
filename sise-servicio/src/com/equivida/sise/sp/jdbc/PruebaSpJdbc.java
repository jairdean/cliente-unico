package com.equivida.sise.sp.jdbc;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import com.equivida.sise.sp.PruebaSp;
import com.equivida.sise.sp.PruebaSpRemoto;

@Stateless(name = "PruebaSp")
public class PruebaSpJdbc implements PruebaSp, PruebaSpRemoto {

	@Resource(mappedName = "java:siseDS")
	private DataSource dataSource;
	Connection conn;

	@Override
	public void llamarPrueba() {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertDatos(String dato) {

		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}
		System.out.println(dato);
		try {
			// String datoCod= new String (dato.getBytes(),"iso-8859-1");
			String datoCod = new String(dato.getBytes("iso-8859-1"), "CP850");
			System.out.println(datoCod);
			pstmt = conn
					.prepareStatement("insert into mpersona (id_persona,txt_apellido1,txt_origen,cod_tipo_iva,cod_tipo_doc,cod_tipo_persona) values (-18,?,'HOLA',1,1,'F')");
			pstmt.setString(1, datoCod);
			boolean ejecuta = pstmt.execute();
			System.out.println(ejecuta);
		} catch (SQLException e) {
			System.err.println("SQLException: " + e.getErrorCode()
					+ e.getMessage());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (pstmt != null)
					try {
						pstmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}
}