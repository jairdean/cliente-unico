package com.equivida.sise.sp.jdbc;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.sql.DataSource;

import com.equivida.sise.dto.ContratanteDto;
import com.equivida.sise.rs.RsContratante;
import com.equivida.sise.sp.ContratanteRemotoSp;
import com.equivida.sise.sp.ContratanteSp;

@Stateless(name = "ContratanteSp")
public class ContratanteSpJdbc implements ContratanteSp, ContratanteRemotoSp {

	private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");

//	@Resource(mappedName = "java:siseDS")
//	private DataSource dataSource;

	// @Resource(mappedName = "java:crm-sql")
	// private DataSource dataSource;

	@Resource(mappedName = "java:siseDS")
	private DataSource dataSource;
	Connection conn;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public RsContratante llamarContratanteSp(ContratanteDto contratante) throws SQLException {
		System.out.println("--------------------------llamarContratanteSp 1");

		return metodoCall(contratante);
		// return conExec(contratante);

	}

	protected RsContratante conExec(ContratanteDto contratante) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		RsContratante respuesta = new RsContratante();
		try {
			// Class.forName("sybase.asejdbc.ASEDriver");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			System.err.println(e.getMessage() + ":error in connection");
		}

		try {

			StringBuffer sql = new StringBuffer(1000);

			sql.append("SET dateformat dmy ");
			sql.append("BEGIN TRAN ");
			sql.append(" DECLARE @id_persona_wkf1 numeric(10,0), @txt_id_persona_wkf1 varchar(50), ");
			sql.append("  @id_proceso_wkf1 numeric(10,0), @message_wkf1 varchar(255), @cod_error1 INT ");
			sql.append(" EXEC spi_mpersona_contratante_wkf ");
			sql.append(" @txt_apellido1 = '").append(contratante.getTxt_apellido1()).append("', ");
			sql.append(" @txt_apellido2 = ").append(
					contratante.getTxt_apellido2() != null ? "'" + contratante.getTxt_apellido2() + "'" : "NULL")
					.append(", ");
			sql.append(" @txt_nombre =  ")
					.append(contratante.getTxt_nombre() != null ? "'" + contratante.getTxt_nombre() + "'" : "NULL")
					.append(", ");
			sql.append(" @cod_tipo_doc = ")
					.append(contratante.getCod_tipo_doc() != null ? "'" + contratante.getCod_tipo_doc() + "'" : "NULL")
					.append(", ");
			sql.append(" @nro_doc = ")
					.append(contratante.getNro_doc() != null ? "'" + contratante.getNro_doc() + "'" : "NULL")
					.append(", ");
			sql.append(" @cod_tipo_iva = ")
					.append(contratante.getCod_tipo_iva() != null ? contratante.getCod_tipo_iva() : "NULL")
					.append(", ");
			sql.append(" @nro_nit = ")
					.append(contratante.getNro_nit() != null ? "'" + contratante.getNro_nit() + "'" : "NULL")
					.append(", ");
			sql.append(" @fec_nac = ")
					.append(contratante.getFec_nac() != null ? "'" + contratante.getFec_nac() + "'" : "NULL")
					.append(", ");
			sql.append(" @txt_lugar_nac = ").append(
					contratante.getTxt_lugar_nac() != null ? "'" + contratante.getTxt_lugar_nac() + "'" : "NULL")
					.append(", ");
			sql.append(" @txt_sexo = ")
					.append(contratante.getTxt_sexo() != null ? "'" + contratante.getTxt_sexo() + "'" : "NULL")
					.append(", ");
			sql.append(" @cod_est_civil = ")
					.append(contratante.getCod_est_civil() != null ? contratante.getCod_est_civil() : "NULL")
					.append(", ");
			sql.append(" @cod_tipo_persona = ").append(
					contratante.getCod_tipo_persona() != null ? "'" + contratante.getCod_tipo_persona() + "'" : "NULL")
					.append(", ");
			sql.append(" @cod_tipo_soc = ")
					.append(contratante.getCod_tipo_soc() != null ? contratante.getCod_tipo_soc() : "NULL")
					.append(", ");
			sql.append(" @cod_sector_mercado = ")
					.append(contratante.getCod_sector_mercado() != null ? contratante.getCod_sector_mercado() : "NULL")
					.append(", ");
			sql.append(" @txt_nombre_contacto = ")
					.append(contratante.getTxt_nombre_contacto() != null
							? "'" + contratante.getTxt_nombre_contacto() + "'"
							: "NULL")
					.append(", ");
			sql.append(" @fec_contitucion = ").append(
					contratante.getFec_contitucion() != null ? "'" + contratante.getFec_contitucion() + "'" : "NULL")
					.append(", ");
			sql.append(" @txt_cuidad_const = ").append(
					contratante.getTxt_cuidad_const() != null ? "'" + contratante.getTxt_cuidad_const() + "'" : "NULL")
					.append(", ");
			sql.append(" @cnt_duracion_empresa = ").append(
					contratante.getCnt_duracion_empresa() != null ? contratante.getCnt_duracion_empresa() : "NULL")
					.append(", ");
			sql.append(" @imp_capital_social =  ")
					.append(contratante.getImp_capital_social() != null
							? "'" + contratante.getImp_capital_social() + "'"
							: "NULL")
					.append(", ");
			sql.append(" @cod_tipo_empresa = ")
					.append(contratante.getCod_tipo_empresa() != null ? contratante.getCod_tipo_empresa() : "NULL")
					.append(", ");
			sql.append(" @txt_nombre_gerente = ")
					.append(contratante.getTxt_nombre_gerente() != null
							? "'" + contratante.getTxt_nombre_gerente() + "'"
							: "NULL")
					.append(", ");
			sql.append(" @cnt_tiempo_mercado = ")
					.append(contratante.getCnt_tiempo_mercado() != null ? contratante.getCnt_tiempo_mercado() : "NULL")
					.append(", ");
			sql.append(" @txt_nombres_rep_legal = ")
					.append(contratante.getTxt_nombres_rep_legal() != null
							? "'" + contratante.getTxt_nombres_rep_legal() + "'"
							: "NULL")
					.append(", ");
			sql.append(" @txt_apellidos_rep_legal = ")
					.append(contratante.getTxt_apellidos_rep_legal() != null
							? "'" + contratante.getTxt_apellidos_rep_legal() + "'"
							: "NULL")
					.append(", ");
			sql.append(" @cod_tipo_doc_rep_legal = ").append(
					contratante.getCod_tipo_doc_rep_legal() != null ? contratante.getCod_tipo_doc_rep_legal() : "NULL")
					.append(", ");
			sql.append(" @nro_doc_rep_legal = ")
					.append(contratante.getNro_doc_rep_legal() != null ? "'" + contratante.getNro_doc_rep_legal() + "'"
							: "NULL")
					.append(", ");
			sql.append(" @ingresos_anuales = ")
					.append(contratante.getIngresos_anuales() != null ? contratante.getIngresos_anuales() : "NULL")
					.append(", ");
			sql.append(" @cod_moneda_ing =  ")
					.append(contratante.getCod_moneda_ing() != null ? contratante.getCod_moneda_ing() : "NULL")
					.append(", ");
			sql.append(" @fec_corte_ing = ").append(
					contratante.getFec_corte_ing() != null ? "'" + contratante.getFec_corte_ing() + "'" : "NULL")
					.append(", ");
			sql.append(" @imp_total_activos = ")
					.append(contratante.getImp_total_activos() != null ? contratante.getImp_total_activos() : "NULL")
					.append(", ");
			sql.append(" @imp_total_pasivos = ")
					.append(contratante.getImp_total_pasivos() != null ? contratante.getImp_total_pasivos() : "NULL")
					.append(", ");
			sql.append(" @sn_parteRel = ")
					.append(contratante.getSn_parteRel() != null ? "'" + contratante.getSn_parteRel() + "'" : "NULL")
					.append(", ");
			sql.append(" @txt_apellido_casada = ")
					.append(contratante.getTxt_apellido_casada() != null
							? "'" + contratante.getTxt_apellido_casada() + "'"
							: "NULL")
					.append(", ");
			sql.append(" @cod_clase_vivienda = ")
					.append(contratante.getCod_clase_vivienda() != null ? contratante.getCod_clase_vivienda() : "NULL")
					.append(", ");
			sql.append(" @cod_tipo_vivienda = ")
					.append(contratante.getCod_tipo_vivienda() != null ? contratante.getCod_tipo_vivienda() : "NULL")
					.append(", ");
			sql.append(" @sn_vehiculo = ")
					.append(contratante.getSn_vehiculo() != null ? "'" + contratante.getSn_vehiculo() + "'" : "NULL")
					.append(", ");
			sql.append(" @txt_desc_vehiculo = ")
					.append(contratante.getTxt_desc_vehiculo() != null ? "'" + contratante.getTxt_desc_vehiculo() + "'"
							: "NULL")
					.append(", ");
			sql.append(" @sn_seguro_anterior = ")
					.append(contratante.getSn_seguro_anterior() != null
							? "'" + contratante.getSn_seguro_anterior() + "'"
							: "NULL")
					.append(", ");
			sql.append(" @cod_cia_seguro_anterior = ")
					.append(contratante.getCod_cia_seguro_anterior() != null ? contratante.getCod_cia_seguro_anterior()
							: "NULL")
					.append(", ");
			sql.append(" @sn_asiste_club = ").append(
					contratante.getSn_asiste_club() != null ? "'" + contratante.getSn_asiste_club() + "'" : "NULL")
					.append(", ");
			sql.append(" @txt_desc_club = ").append(
					contratante.getTxt_desc_club() != null ? "'" + contratante.getTxt_desc_club() + "'" : "NULL")
					.append(", ");
			sql.append(" @txt_nombres_conyugue = ")
					.append(contratante.getTxt_nombres_conyugue() != null
							? "'" + contratante.getTxt_nombres_conyugue() + "'"
							: "NULL")
					.append(", ");
			sql.append(" @txt_apellidos_conyugue = ")
					.append(contratante.getTxt_apellidos_conyugue() != null
							? "'" + contratante.getTxt_apellidos_conyugue() + "'"
							: "NULL")
					.append(", ");
			sql.append(" @cod_tipo_doc_conyugue = ").append(
					contratante.getCod_tipo_doc_conyugue() != null ? contratante.getCod_tipo_doc_conyugue() : "NULL")
					.append(", ");
			sql.append(" @nro_doc_conyugue = ").append(
					contratante.getNro_doc_conyugue() != null ? "'" + contratante.getNro_doc_conyugue() + "'" : "NULL")
					.append(", ");
			sql.append(" @sn_relacionl = ")
					.append(contratante.getSn_relacionl() != null ? "'" + contratante.getSn_relacionl() + "'" : "NULL")
					.append(", ");
			sql.append(" @txt_edificio = ")
					.append(contratante.getTxt_edificio() != null ? "'" + contratante.getTxt_edificio() + "'" : "NULL")
					.append(", ");
			sql.append(" @txt_urbanizacion = ").append(
					contratante.getTxt_urbanizacion() != null ? "'" + contratante.getTxt_urbanizacion() + "'" : "NULL")
					.append(", ");
			sql.append(" @txt_sector = ")
					.append(contratante.getTxt_sector() != null ? "'" + contratante.getTxt_sector() + "'" : "NULL")
					.append(", ");
			sql.append(" @imp_prom_ing_mensual = ")
					.append(contratante.getImp_prom_ing_mensual() != null
							? "'" + contratante.getImp_prom_ing_mensual() + "'"
							: "NULL")
					.append(", ");
			sql.append(" @sn_pep =  ")
					.append(contratante.getSn_pep() != null ? "'" + contratante.getSn_pep() + "'" : "NULL")
					.append(", ");
			sql.append(" @sn_relacion_laboral_pep = ")
					.append(contratante.getSn_relacion_laboral_pep() != null
							? "'" + contratante.getSn_relacion_laboral_pep() + "'"
							: "NULL")
					.append(", ");
			sql.append(" @txt_nombre_institucion_pep = ")
					.append(contratante.getTxt_nombre_institucion_pep() != null
							? "'" + contratante.getTxt_nombre_institucion_pep() + "'"
							: "NULL")
					.append(", ");
			sql.append(" @cod_actividad =  ").append(
					contratante.getCod_actividad() != null ? "'" + contratante.getCod_actividad() + "'" : "NULL")
					.append(", ");
			sql.append(" @cod_ingreso_pm = ")
					.append(contratante.getCod_ingreso_pm() != null ? contratante.getCod_ingreso_pm() : "NULL")
					.append(", ");
			sql.append(" @id_persona_wkf = @id_persona_wkf1 OUTPUT, ");
			sql.append(" @txt_id_persona_wkf = @txt_id_persona_wkf1 OUTPUT, ");
			sql.append(" @id_proceso_wkf = @id_proceso_wkf1 OUTPUT, ");
			sql.append(" @message_wkf = @message_wkf1 OUTPUT, ");
			sql.append(" @cod_error = @cod_error1 OUTPUT ");

			sql.append(" SELECT @id_persona_wkf1 '@id_persona_wkf1', @txt_id_persona_wkf1 '@txt_id_persona_wkf1',");
			sql.append(
					" @id_proceso_wkf1 '@id_proceso_wkf1', @message_wkf1 '@message_wkf1',@cod_error1 '@cod_error1' ");

			String query = sql.toString().replace("''", "NULL");

			System.out.println("--------------------------sp contrtante: \n\n" + query);
			// sql = new StringBuffer(sql.toString().substring(0, sql.length() - 1));

			System.out.println("1: " + pstmt);
			System.out.println("con:" + conn);

			pstmt = conn.prepareStatement(query);

			System.out.println("2: " + pstmt);
			pstmt.setQueryTimeout(600);

			int count = 0;

			boolean tieneResulset = pstmt.execute();
			System.out.println("tiene:" + tieneResulset);

			while (count >= 0) {
				if (tieneResulset) {
					rs = pstmt.getResultSet();

					while (rs.next()) {

						respuesta.setId_persona_wkf(rs.getBigDecimal(1));
						respuesta.setTxt_id_persona_wkf(rs.getString(2));
						respuesta.setId_proceso_wkf(rs.getBigDecimal(3));
						respuesta.setMessage_wkf(rs.getString(4));
						respuesta.setCod_error(rs.getInt(5));
						System.out.println("entra while");

					}
					rs.close();
				} else {
					count = pstmt.getUpdateCount();
				} // end if-else tieneResulset
				if (count >= 0) {
					// procesa de acuerdo al contador de actualización, lo
					// ignoramos...
				}
				tieneResulset = pstmt.getMoreResults();

			} // end while count

		} catch (SQLException e) {

			respuesta.setMessage_wkf(e.getMessage());
			respuesta.setCod_error(e.getErrorCode());
			System.err.println("SQLException: " + e.getErrorCode() + e.getMessage());

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

	/**
	 * Llama al SP con CallableStatement.
	 * 
	 * @param contratante
	 * @return
	 * @throws SQLException
	 */
	private RsContratante metodoCall(ContratanteDto contratante) throws SQLException {
		// PreparedStatement pstmt = null;
		RsContratante respuesta = new RsContratante();
		// ResultSet rs = null;
		CallableStatement pstmt = null;

		System.out.println("*******SP spi_mpersona_contratante_wkf ");
		System.out.println("contratante.getNro_doc():" + contratante.getNro_doc());
		System.out.println("contratante.toString:");
		System.out.println(contratante.toString());

		try {
			conn = dataSource.getConnection();

			pstmt = conn.prepareCall(
					"{ call spi_mpersona_contratante_wkf(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			pstmt.setString(1, contratante.getTxt_apellido1());
			pstmt.setString(2, contratante.getTxt_apellido2());
			pstmt.setString(3, contratante.getTxt_nombre());
			pstmt.setBigDecimal(4, contratante.getCod_tipo_doc());
			pstmt.setString(5, contratante.getNro_doc());
			pstmt.setBigDecimal(6, contratante.getCod_tipo_iva());
			pstmt.setString(7, contratante.getNro_nit());
			if (contratante.getFec_nac() != null && contratante.getFec_nac().trim().length() > 0) {
				Date fec = SDF.parse(contratante.getFec_nac());
				Timestamp ts = new Timestamp(fec.getTime());
				pstmt.setTimestamp(8, ts);
			} else {
				pstmt.setTimestamp(8, null);
			}
			pstmt.setString(9, contratante.getTxt_lugar_nac());
			pstmt.setString(10, contratante.getTxt_sexo());
			pstmt.setBigDecimal(11, contratante.getCod_est_civil());
			pstmt.setString(12, contratante.getCod_tipo_persona());

			// Para personas juridicas
			pstmt.setBigDecimal(13, contratante.getCod_tipo_soc());
			pstmt.setBigDecimal(14, contratante.getCod_sector_mercado());
			pstmt.setString(15, contratante.getTxt_nombre_contacto());
			if (contratante.getFec_contitucion() != null && contratante.getFec_contitucion().trim().length() > 0) {
				Date fec = SDF.parse(contratante.getFec_contitucion());
				Timestamp ts = new Timestamp(fec.getTime());
				pstmt.setTimestamp(16, ts);
			} else {
				pstmt.setTimestamp(16, null);
			}
			pstmt.setString(17, contratante.getTxt_cuidad_const());
			pstmt.setBigDecimal(18, contratante.getCnt_duracion_empresa());
			pstmt.setBigDecimal(19,
					contratante.getImp_capital_social() != null ? new BigDecimal(contratante.getImp_capital_social())
							: null);
			pstmt.setBigDecimal(20, contratante.getCod_tipo_empresa());
			pstmt.setString(21, contratante.getTxt_nombre_gerente());
			pstmt.setBigDecimal(22, contratante.getCnt_tiempo_mercado());
			pstmt.setString(23, contratante.getTxt_nombres_rep_legal());
			pstmt.setString(24, contratante.getTxt_apellidos_rep_legal());
			pstmt.setBigDecimal(25, contratante.getCod_tipo_doc_rep_legal());
			pstmt.setString(26, contratante.getNro_doc_rep_legal());
			pstmt.setBigDecimal(27, contratante.getIngresos_anuales());
			pstmt.setBigDecimal(28, contratante.getCod_moneda_ing());
			if (contratante.getFec_corte_ing() != null && contratante.getFec_corte_ing().trim().length() > 0) {
				Date fec = SDF.parse(contratante.getFec_corte_ing());
				Timestamp ts = new Timestamp(fec.getTime());
				pstmt.setTimestamp(29, ts);
			} else {
				pstmt.setTimestamp(29, null);
			}
			pstmt.setBigDecimal(30, contratante.getImp_total_activos());
			pstmt.setBigDecimal(31, contratante.getImp_total_pasivos());
			pstmt.setString(32, contratante.getSn_parteRel());

			// Para persona natural
			pstmt.setString(33, contratante.getTxt_apellido_casada());
			pstmt.setBigDecimal(34, contratante.getCod_clase_vivienda());
			pstmt.setBigDecimal(35, contratante.getCod_tipo_vivienda());
			pstmt.setString(36, contratante.getSn_vehiculo());
			pstmt.setString(37, contratante.getTxt_desc_vehiculo());
			pstmt.setString(38, contratante.getSn_seguro_anterior());
			pstmt.setBigDecimal(39, contratante.getCod_cia_seguro_anterior());
			pstmt.setString(40, contratante.getSn_asiste_club());
			pstmt.setString(41, contratante.getTxt_desc_club());
			pstmt.setString(42, contratante.getTxt_nombres_conyugue());
			pstmt.setString(43, contratante.getTxt_apellidos_conyugue());
			pstmt.setBigDecimal(44, contratante.getCod_tipo_doc_conyugue());
			pstmt.setString(45, contratante.getNro_doc_conyugue());
			pstmt.setString(46, contratante.getSn_relacionl());

			// Para PN y PJ
			pstmt.setString(47, contratante.getTxt_edificio());
			pstmt.setString(48, contratante.getTxt_urbanizacion());
			pstmt.setString(49, contratante.getTxt_sector());
			pstmt.setBigDecimal(50,
					contratante.getImp_prom_ing_mensual() != null
							? new BigDecimal(contratante.getImp_prom_ing_mensual())
							: null);
			pstmt.setString(51, contratante.getSn_pep()); // error
			pstmt.setString(52, contratante.getSn_relacion_laboral_pep());
			pstmt.setString(53, contratante.getTxt_nombre_institucion_pep());
			pstmt.setString(54, contratante.getCod_actividad());
			pstmt.setBigDecimal(55, contratante.getCod_ingreso_pm());
			// output
			pstmt.registerOutParameter(56, Types.NUMERIC);
			pstmt.registerOutParameter(57, Types.VARCHAR);
			pstmt.registerOutParameter(58, Types.NUMERIC);
			pstmt.registerOutParameter(59, Types.VARCHAR);
			pstmt.registerOutParameter(60, Types.INTEGER);

			// Se ejecuta el procedimiento
			pstmt.executeUpdate();

			// Se arman los valores de respuesta
			if (pstmt.getInt(56) > 0) {
				respuesta.setId_persona_wkf(new BigDecimal(pstmt.getInt(56)));
			} else {
				respuesta.setId_persona_wkf(null);
			}
			respuesta.setTxt_id_persona_wkf(pstmt.getString(57));
			if (pstmt.getInt(58) > 0) {
				respuesta.setId_proceso_wkf(new BigDecimal(pstmt.getInt(58)));
			} else {
				respuesta.setId_proceso_wkf(null);
			}
			respuesta.setMessage_wkf(pstmt.getString(59));
			respuesta.setCod_error(pstmt.getInt(60));

		} catch (SQLException e) {
			System.err.println("SQLException: " + e.getErrorCode() + e.getMessage());
			respuesta.setCod_error(2);
			respuesta.setMessage_wkf("CODE: " + e.getErrorCode() + " Message:" + e.getMessage());
		} catch (ParseException e) {
			System.err.println("SQLException: " + e.getMessage());
			respuesta.setCod_error(2);
			respuesta.setMessage_wkf("Message:" + e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} finally {
				if (conn != null) {
					conn.close();
				}
			}
		}

		return respuesta;
	}

}