package com.equivida.dao.ejb;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.equivida.dao.ContratanteDao;
import com.equivida.dto.ContratanteListaDto;
import com.equivida.helper.ContratanteHelper;

/**
 * @author Juan Ochoa
 * 
 */
@Stateless(name = "ContratanteDao")
public class ContratanteDaoEjb implements ContratanteDao {

	@javax.persistence.PersistenceContext
	protected javax.persistence.EntityManager em;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.dao.ContratanteDao#obtenerListadoPaginado(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String, int,
	 * int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ContratanteListaDto> obtenerListadoPaginado(String numeroDocumento, String apellidoPaterno,
			String apellidoMaterno, String primerNombre, String segundoNombre, int firstRows, int totalRows) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * FROM ");
		sql.append(" ( ");
		sql.append(
				"(select pn.SEC_PERSONA_NATURAL AS SEC, RTRIM(CAST (rpn.IDENTIFICACION AS VARCHAR(15))) AS NO_DOC, pn.APELLIDO_PATERNO AS A_PATERNO, ");// 0
																																						// row,1,2,3
		sql.append("pn.APELLIDO_MATERNO AS A_MATERNO, pn.PRIMER_NOMBRE AS PRIMER_NOMBRE, ");// 4,5
		sql.append("pn.SEGUNDO_NOMBRE AS SEGUNDO_NOMBRE, 'NAT' AS tipoper ");// 6,7
		sql.append("from EQUIVIDA.RUC_PERSONA_NATURAL rpn ");
		sql.append("inner join EQUIVIDA.PERSONA_NATURAL pn on pn.SEC_PERSONA_NATURAL = rpn.SEC_PERSONA_NATURAL ");
		sql.append(" where 1 = 1 ");
		if (numeroDocumento != null && numeroDocumento.trim().length() > 0) {
			sql.append("and rpn.IDENTIFICACION = '").append(numeroDocumento).append("' ");
		}
		if (apellidoPaterno != null && apellidoPaterno.trim().length() > 0) {
			sql.append("and pn.APELLIDO_PATERNO like '%").append(apellidoPaterno.trim().toUpperCase()).append("%' ");
		}
		if (apellidoMaterno != null && apellidoMaterno.trim().length() > 0) {
			sql.append("and pn.APELLIDO_MATERNO like '%").append(apellidoMaterno.trim().toUpperCase()).append("%' ");
		}
		if (primerNombre != null && primerNombre.trim().length() > 0) {
			sql.append("and pn.PRIMER_NOMBRE like '%").append(primerNombre.trim().toUpperCase()).append("%' ");
		}
		if (segundoNombre != null && segundoNombre.trim().length() > 0) {
			sql.append("and pn.SEGUNDO_NOMBRE like '%").append(segundoNombre.trim().toUpperCase()).append("%' ");
		}
		sql.append(" ) ");
		sql.append(" UNION ");
		sql.append(" ( ");

		sql.append(
				"select pj.sec_persona_juridica as SEC, RTRIM(CAST (pj.IDENTIFICACION AS VARCHAR(15))) AS NO_DOC,  ");// 0
																														// row,
																														// 1,
																														// 2
		sql.append(
				" pj.RAZON_SOCIAL AS A_PATERNO, '  ' AS A_MATERNO, '  ' AS PRIMER_NOMBRE, '  ' AS SEGUNDO_NOMBRE, 'JUR' AS tipoper ");// 3,4,5,6,7
		sql.append(" from EQUIVIDA.PERSONA_JURIDICA pj ");
		sql.append(" where 1 = 1 ");
		// sql.append(" AND pj.sec_persona_juridica =45675 ");

		if (numeroDocumento != null && numeroDocumento.trim().length() > 0) {
			sql.append("and pj.IDENTIFICACION = '").append(numeroDocumento).append("' ");
		}
		if (apellidoPaterno != null && apellidoPaterno.trim().length() > 0) {
			sql.append("and pj.RAZON_SOCIAL like '%").append(apellidoPaterno.trim().toUpperCase()).append("%' ");
		}
		sql.append(" )  ");// fin union
		sql.append(" ) as temp ");

		sql.append("order by temp.A_PATERNO ");

		Query query = em.createNativeQuery(sql.toString());
		query.setFirstResult(firstRows);
		query.setMaxResults(totalRows);

		List<Object> objList = query.getResultList();

		// Listado de respuesta
		List<ContratanteListaDto> resp = new ArrayList<ContratanteListaDto>();

		if (objList != null && !objList.isEmpty()) {
			for (Iterator<Object> iterator = objList.iterator(); iterator.hasNext();) {
				Object[] arr = (Object[]) iterator.next();

				resp.add(ContratanteHelper.armarDtoListado(arr));

			}
		}

		return resp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.equivida.dao.ContratanteDao#obtenerTotalListadoPaginado(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Long obtenerTotalListadoPaginado(String numeroDocumento, String apellidoPaterno, String apellidoMaterno,
			String primerNombre, String segundoNombre) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*) FROM ");
		sql.append(" ( ");
		sql.append("(select pn.SEC_PERSONA_NATURAL AS SEC ");
		sql.append("from EQUIVIDA.RUC_PERSONA_NATURAL rpn ");
		sql.append("inner join EQUIVIDA.PERSONA_NATURAL pn on pn.SEC_PERSONA_NATURAL = rpn.SEC_PERSONA_NATURAL ");
		sql.append(" where 1 = 1 ");
		if (numeroDocumento != null && numeroDocumento.trim().length() > 0) {
			sql.append("and rpn.IDENTIFICACION = '").append(numeroDocumento).append("' ");
		}
		if (apellidoPaterno != null && apellidoPaterno.trim().length() > 0) {
			sql.append("and pn.APELLIDO_PATERNO like '%").append(apellidoPaterno.trim().toUpperCase()).append("%' ");
		}
		if (apellidoMaterno != null && apellidoMaterno.trim().length() > 0) {
			sql.append("and pn.APELLIDO_MATERNO like '%").append(apellidoMaterno.trim().toUpperCase()).append("%' ");
		}
		if (primerNombre != null && primerNombre.trim().length() > 0) {
			sql.append("and pn.PRIMER_NOMBRE like '%").append(primerNombre.trim().toUpperCase()).append("%' ");
		}
		if (segundoNombre != null && segundoNombre.trim().length() > 0) {
			sql.append("and pn.SEGUNDO_NOMBRE like '%").append(segundoNombre.trim().toUpperCase()).append("%' ");
		}
		sql.append(" ) ");
		sql.append(" UNION ");
		sql.append(" ( ");

		sql.append("select pj.sec_persona_juridica as SEC ");
		sql.append(" from EQUIVIDA.PERSONA_JURIDICA pj ");
		sql.append(" where 1 = 1 ");
		// sql.append(" AND pj.sec_persona_juridica =45675 ");

		if (numeroDocumento != null && numeroDocumento.trim().length() > 0) {
			sql.append("and pj.IDENTIFICACION = '").append(numeroDocumento).append("' ");
		}
		if (apellidoPaterno != null && apellidoPaterno.trim().length() > 0) {
			sql.append("and pj.RAZON_SOCIAL like '%").append(apellidoPaterno.trim().toUpperCase()).append("%' ");
		}
		sql.append(" )  ");// fin union
		sql.append(" ) as temp ");

		Query query = em.createNativeQuery(sql.toString());
		Object queryResp = query.getSingleResult();

		Long resp = new Long(0);
		if (queryResp != null) {
			resp = new Long((Integer) queryResp);
		}

		return resp;
	}

}
