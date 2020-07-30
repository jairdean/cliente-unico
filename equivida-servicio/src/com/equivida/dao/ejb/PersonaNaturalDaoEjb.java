package com.equivida.dao.ejb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.equivida.constant.RespuestaEnum;
import com.equivida.dao.PersonaNaturalDao;
import com.equivida.modelo.PersonaNatural;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * @author Daniel Cardenas
 * 
 */
@Stateless(name = "PersonaNaturalDao")
public class PersonaNaturalDaoEjb extends GenericDaoEjb<PersonaNatural, Integer> implements PersonaNaturalDao {

	public PersonaNaturalDaoEjb() {
		super(PersonaNatural.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PersonaNatural> obtenerListadoPaginado(String numeroDocumento, String apellidoPaterno,
			String apellidoMaterno, String primerNombre, String segundoNombre, RespuestaEnum cliente,
			String ordenadoPor, boolean asc, int firstRows, int totalRows) {

		String sql = "select pn.SEC_PERSONA_NATURAL, pn.IDENTIFICACION, pn.APELLIDO_PATERNO, pn.APELLIDO_MATERNO, pn.PRIMER_NOMBRE, pn.SEGUNDO_NOMBRE "
				+ "from EQUIVIDA.PERSONA_NATURAL pn JOIN EQUIVIDA.PERSONA p on pn.sec_persona=p.sec_persona WHERE p.cliente = ? ";

		sql = completarFiltros(numeroDocumento, apellidoPaterno, apellidoMaterno, primerNombre, segundoNombre, sql);

		String asce = "asc";
		if (!asc) {
			asce = "desc";
		}

		sql += " order by " + ordenadoPor + " " + asce;

		Query query = em.createNativeQuery(sql);

		ponerParameters(numeroDocumento, apellidoPaterno, apellidoMaterno, primerNombre, segundoNombre, cliente, query);

		query.setFirstResult(firstRows);
		query.setMaxResults(totalRows);

		List<Object[]> listaTmp = query.getResultList();

		List<PersonaNatural> listaR = new ArrayList<PersonaNatural>();

		int pos = 0;

		for (Object[] arr : listaTmp) {

			if (arr.length != 6) {
				pos = 1;
			}

			PersonaNatural pn = new PersonaNatural();
			pn.setSecPersonaNatural(Integer.parseInt(arr[pos].toString()));
			pn.setIdentificacion(arr[pos + 1].toString());
			pn.setApellidoPaterno(arr[pos + 2].toString());
			pn.setApellidoMaterno(arr[pos + 3].toString());
			pn.setPrimerNombre(arr[pos + 4].toString());
			if (arr[pos + 5] != null) {
				pn.setSegundoNombre(arr[pos + 5].toString());
			}

			listaR.add(pn);
		}

		return listaR;
	}

	private void ponerParameters(String numeroDocumento, String apellidoPaterno, String apellidoMaterno,
			String primerNombre, String segundoNombre, RespuestaEnum cliente, Query query) {

		query.setParameter(1, cliente.getCodigo());
		int contadorParameter = 2;

		if (numeroDocumento != null && !numeroDocumento.equals("")) {
			query.setParameter(contadorParameter, numeroDocumento);
			contadorParameter++;
		}
		if (apellidoPaterno != null && !apellidoPaterno.equals("")) {
			query.setParameter(contadorParameter, apellidoPaterno.toLowerCase() + "%");
			contadorParameter++;
		}
		if (apellidoMaterno != null && !apellidoMaterno.equals("")) {
			query.setParameter(contadorParameter, apellidoMaterno.toLowerCase() + "%");
			contadorParameter++;
		}
		if (primerNombre != null && !primerNombre.equals("")) {
			query.setParameter(contadorParameter, primerNombre.toLowerCase() + "%");
			contadorParameter++;
		}
		if (segundoNombre != null && !segundoNombre.equals("")) {
			query.setParameter(contadorParameter, segundoNombre.toLowerCase() + "%");
			contadorParameter++;
		}
	}

	private String completarFiltros(String numeroDocumento, String apellidoPaterno, String apellidoMaterno,
			String primerNombre, String segundoNombre, String sql) {
		if (numeroDocumento != null && !numeroDocumento.equals("")) {
			sql += " and pn.IDENTIFICACION = ? ";
		}
		if (apellidoPaterno != null && !apellidoPaterno.equals("")) {
			sql += " and lower(pn.APELLIDO_PATERNO) like ? ";
		}
		if (apellidoMaterno != null && !apellidoMaterno.equals("")) {
			sql += " and lower(pn.APELLIDO_MATERNO) like ? ";
		}
		if (primerNombre != null && !primerNombre.equals("")) {
			sql += " and lower(pn.PRIMER_NOMBRE) like ? ";
		}
		if (segundoNombre != null && !segundoNombre.equals("")) {
			sql += " and lower(pn.SEGUNDO_NOMBRE) like ? ";
		}
		return sql;
	}

	@Override
	public Long obtenerTotalListadoPaginado(String numeroDocumento, String apellidoPaterno, String apellidoMaterno,
			String primerNombre, String segundoNombre, RespuestaEnum cliente) {
		Long total = null;

		String sql = "select count(pn.SEC_PERSONA_NATURAL) "
				+ "from EQUIVIDA.PERSONA_NATURAL pn JOIN EQUIVIDA.PERSONA p on pn.sec_persona=p.sec_persona WHERE p.cliente = ? ";

		sql = completarFiltros(numeroDocumento, apellidoPaterno, apellidoMaterno, primerNombre, segundoNombre, sql);

		Query query = em.createNativeQuery(sql);

		ponerParameters(numeroDocumento, apellidoPaterno, apellidoMaterno, primerNombre, segundoNombre, cliente, query);

		total = ((Integer) query.getSingleResult()).longValue();

		return total;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Integer> obtenerPersonaNaturalByDocumento(List<String> numeroDocumento) {
		String ejbql = "select new map(p.identificacion as cedula, p.secPersonaNatural as secuencial) from PersonaNatural p where p.identificacion  in :listaDocumentos ";
		Query query = em.createQuery(ejbql);
		query.setParameter("listaDocumentos", numeroDocumento);

		List<Map<String, Object>> ret = query.getResultList();

		System.out.println("encontro total:" + ret.size());

		Map<String, Integer> resp = new HashMap<String, Integer>();
		for (Map<String, Object> m : ret) {
			resp.put((String) m.get("cedula"), (Integer) m.get("secuencial"));
		}

		return resp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PersonaNatural obtenerPersonaNaturalByDocumento(String noDocumento) {
		StringBuffer hql = new StringBuffer(200);
		hql.append("select p from PersonaNatural p where p.identificacion = '").append(noDocumento).append("'");

		Query query = em.createQuery(hql.toString());

		List<PersonaNatural> respList = query.getResultList();

		if (respList != null && !respList.isEmpty()) {
			return respList.get(0);
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.equivida.dao.PersonaNaturalDao#detach(com.equivida.modelo.PersonaNatural)
	 */
	@Override
	public void detach(PersonaNatural personaNatural) {
		em.detach(personaNatural);
	}
}
