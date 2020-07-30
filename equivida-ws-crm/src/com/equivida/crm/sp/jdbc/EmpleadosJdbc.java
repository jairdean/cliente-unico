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

import com.equivida.crm.rs.EmpleadosRs;
import com.equivida.crm.sp.EmpleadosSp;
import com.equivida.crm.sp.EmpleadosSpRemoto;

/**
 * @author saviasoft5
 * 
 */
@Stateless(name = "EmpleadosSp")
public class EmpleadosJdbc implements EmpleadosSp, EmpleadosSpRemoto {

	// @Resource(mappedName = "java:empleadoDS")
	// private DataSource dataSource;

	@Resource(mappedName = "java:crm-sql")
	private DataSource dataSource;

	private Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<EmpleadosRs> consultaEmpleadosSp() throws SQLException {

		System.out.println("SP: select ...");

		PreparedStatement pstmt = null;
		List<EmpleadosRs> respuesta = null;
		ResultSet rs = null;

		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}

		try {
			// pstmt = conn
			// .prepareStatement("select distinct( tr.nombre),pl.trabajador ,pu.descripcion,tr.sexo,gr.fecha_ingreso, gr.fecha_baja, tr.fecha_nacimiento"
			// +
			// ",tr.telefono_particular, tr.e_mail,su.salario, gr.compania, ic.lugar_nacimiento,ic.nacionalidad, tr.domicilio2, tr.estado_provincia"
			// +
			// ", tr.pais, gr.telefono_oficina, gr.fecha_antiguedad, gr.forma_pago, tr.codigo_postal, tr.calles_aledanas, tr.poblacion, tr.domicilio3, rt.cuenta_deposito"
			// +
			// ", ist.dato_01,ist.dato_05,ist.dato_06,ist.dato_11,ist.secuencia,va.agrupacion,va.dato,gr.clase_nomina from      trabajadores tr ,plazas pl,puestos pu"
			// +
			// ",trabajadores_grales gr,sueldos su,inf_complementaria ic,inf_soc_trabajador ist,rel_trab_ins_dep rt,vw_all_rel_trab_agr va where tr.trabajador = pl.trabajador "
			// +
			// " and pl.puesto = pu.puesto and tr.trabajador = gr.trabajador and tr.trabajador = su.trabajador and tr.trabajador = ic.trabajador and ist.trabajador = tr.trabajador"
			// +
			// " and rt.trabajador = tr.trabajador and va.trabajador = tr.trabajador and gr.fecha_baja is null and ist.indice_inf_soc = 'NIVESCOL' and gr.compania = 'EQUI' "
			// +
			// " and ist.secuencia = (Select max(secuencia) from inf_soc_trabajador a where a.trabajador = ist.trabajador and indice_inf_soc = 'NIVESCOL') and va.agrupacion IN ('0002')");

			String sql = "select tr.nombre "
					+ ",              pl.trabajador   codigo "
					+ ",              pu.descripcion  cargo "
					+ ",              ''  departamento "
					+ ",              tr.sexo  genero "
					+ ",              gr.fecha_ingreso  fecha_inicio "
					+ ",              gr.fecha_baja  fecha_fin "
					+ ",              tr.fecha_nacimiento  fecha_nac "
					+ ",              ''  jefe "
					+ ",              (case when (gr.fecha_baja = '') then 1 else 0 end) Activo "
					+ ",              ''  usr "
					+ ",              gr.telefono_oficina "
					+ ",              tr.telefono_particular "
					+ ",              tr.e_mail "
					+ "from      trabajadores tr "
					+ ",              plazas pl "
					+ ",              puestos pu "
					+ ",              trabajadores_grales gr "
					+ ",              sueldos su "
					+ ",              inf_complementaria ic "
					+ ",              inf_soc_trabajador ist "
					+ ",              rel_trab_ins_dep rt "
					+ ",              vw_all_rel_trab_agr va "
					+ "where "
					+ "tr.trabajador = pl.trabajador "
					+ "and pl.puesto = pu.puesto "
					+ "and tr.trabajador = gr.trabajador "
					+ "and tr.trabajador = su.trabajador "
					+ "and tr.trabajador = ic.trabajador "
					+ "and ist.trabajador = tr.trabajador "
					+ "and rt.trabajador = tr.trabajador "
					+ "and va.trabajador = tr.trabajador "
					+ "and gr.fecha_baja is null "
					+ "and ist.indice_inf_soc = 'NIVESCOL' "
					+ "and gr.compania = 'EQUI' "
					+ "and ist.secuencia = (Select max(secuencia) from inf_soc_trabajador a where a.trabajador = ist.trabajador and indice_inf_soc = 'NIVESCOL') "
					+ "and va.agrupacion IN ('0002')";

			System.out.println(sql);

			pstmt = conn.prepareStatement(sql);

			respuesta = new ArrayList<EmpleadosRs>();

			int count = 0;

			boolean tieneResulset = pstmt.execute();
			System.out.println("tiene:" + tieneResulset);

			respuesta = new ArrayList<EmpleadosRs>();
			while (count >= 0) {
				if (tieneResulset) {
					rs = pstmt.getResultSet();

					while (rs.next()) {
						EmpleadosRs aux = new EmpleadosRs();

						aux.setNombreCompleto(rs.getString(1));
						aux.setCodigo(rs.getString(2));
						aux.setCargo(rs.getString(3));
						aux.setDepartamento(rs.getString(4));
						String gen = rs.getString(5);
						if (gen.equals("2")) {
							aux.setGen("1");
						} else {
							aux.setGen("0");
						}
						aux.setFechaIngreso(rs.getString(6));
						aux.setFechaSalida(rs.getString(7));
						aux.setFechaNacimiento(rs.getString(8));
						aux.setJefe(rs.getString(9));
						String estado = rs.getString(10);
						if (estado.equals("0")) {
							aux.setAct("Verdadero");// rs.getString(10)
						} else {
							aux.setAct("Falso");// rs.getString(10)
						}
						aux.setUser(rs.getString(11));
						aux.setTelefono(rs.getString(12));
						aux.setMovil(rs.getString(13));// confirmar
						aux.setEmail(rs.getString(14));

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
