package com.equivida.dao.ejb;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.equivida.constant.Constantes;
import com.equivida.dao.RcsDao;
import com.equivida.modelo.EstadoRcs;
import com.equivida.modelo.PersonaNatural;
import com.equivida.modelo.Rcs;
import com.equivida.modelo.TipoIdentificacion;
import com.saviasoft.persistence.util.DateBetween;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "RcsDao")
public class RcsDaoEjb extends GenericDaoEjb<Rcs, Integer> implements RcsDao {

	public RcsDaoEjb() {
		super(Rcs.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rcs> obtenerPorFechaUsuarioId(Date fechaDesde, Date fechaHasta, String idUsuario) {

		StringBuilder sql = new StringBuilder();
		sql.append("select r.SEC_PERSONA_CONTROL, r.IDENTIFICACION, ");// 0,1
		sql.append(" r.ID_USUARIO, r.COD_TIPO_IDENTIFICACION, pn.APELLIDO_PATERNO, pn.APELLIDO_PATERNO, ");// 2,3,4,5
		sql.append(" pn.PRIMER_NOMBRE, pn.SEGUNDO_NOMBRE, r.ts_creacion ");// 6,7,8
		sql.append(" from EQUIVIDA.RCS r  ");
		sql.append(
				" inner join EQUIVIDA.PERSONA_NATURAL pn on (pn.identificacion=r.identificacion and pn.COD_TIPO_IDENTIFICACION = r.COD_TIPO_IDENTIFICACION) ");
		sql.append(" where  1=1 ");

		if (idUsuario != null && !idUsuario.trim().equals("")) {
			sql.append(" and r.ID_USUARIO ='" + idUsuario + "' ");
		}
		if (fechaDesde != null) {
			sql.append(" and r.TS_CREACION >= :fechaDesde ");
		}
		if (fechaHasta != null) {
			sql.append(" and r.TS_CREACION <= :fechaHasta ");
		}

		System.out.println(sql.toString());

		Query query = em.createNativeQuery(sql.toString());

		DateBetween db = new DateBetween(fechaDesde, fechaHasta);

		if (fechaDesde != null) {
			query.setParameter("fechaDesde", db.getFrom());
		}
		if (fechaHasta != null) {
			query.setParameter("fechaHasta", db.getTo());
		}

		List<Object[]> listaTmp = query.getResultList();

		List<Rcs> lista = new ArrayList<Rcs>();

		for (Object[] fila : listaTmp) {
			Rcs rcs = new Rcs();
			rcs.setSecPersonaControl((Integer) fila[0]);
			rcs.setIdentificacion(fila[1].toString());
			rcs.setIdUsuario(fila[2].toString());
			rcs.setTipoIdentificacion(new TipoIdentificacion((Character) fila[3]));
			PersonaNatural pn = new PersonaNatural();
			if (fila[4] != null) {
				pn.setApellidoPaterno(fila[4].toString());
			}
			if (fila[5] != null) {
				pn.setApellidoMaterno(fila[5].toString());
			}
			if (fila[6] != null) {
				pn.setPrimerNombre(fila[6].toString());
			}
			if (fila[7] != null) {
				pn.setSegundoNombre(fila[7].toString());
			}

			rcs.setPersonaNaturalTransient(pn);

			rcs.setTsCreacion((Date) fila[8]);

			lista.add(rcs);
		}

		return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> obtenerDistintosUsuarios() {

		StringBuilder sql = new StringBuilder();
		sql.append("select distinct r.id_usuario from EQUIVIDA.RCS r ");// 0,1

		Query query = em.createNativeQuery(sql.toString());

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rcs> obtenerPorFechaUsuarioIdPaginado(Date fechaDesde, Date fechaHasta, String idUsuario,
			Character codEstadoRcs, boolean contratante, int ini, int total) {
		StringBuilder sql = new StringBuilder();
		sql.append("select r.SEC_PERSONA_CONTROL, r.IDENTIFICACION, ");// 0,1
		sql.append(" r.USR_CREACION, r.COD_TIPO_IDENTIFICACION, r.denominacion, ");// 2,3,4
		sql.append(" r.ts_creacion, ");// 5
		sql.append(" r.cod_estado, r.cod_control, r.contenido_xml, ");// 6,7,8
		sql.append(" r.COD_ESTADO_PERSONA, estrcs.ESTADO, r.COMENTARIO, ");// 9,10,11
		sql.append(" r.IDENTIFICACION_C, r.USR_MODIFICACION, ");// 12,13
		sql.append(" r.TS_MODIFICACION ");// 14
		ponerFrom(sql);
		sql.append(" where  1=1 ");

		ponerFiltros(fechaDesde, fechaHasta, idUsuario, codEstadoRcs, contratante, sql);

		Query query = em.createNativeQuery(sql.toString());

		DateBetween db = new DateBetween(fechaDesde, fechaHasta);

		ponerFiltrosValores(fechaDesde, fechaHasta, codEstadoRcs, query, db);

		if (ini != -1 && total != -1) {
			query.setFirstResult(ini);
			query.setMaxResults(total);
		}

		List<Object[]> listaTmp = query.getResultList();

		List<Rcs> lista = new ArrayList<Rcs>();

		int posIni = 0;

		for (Object[] fila : listaTmp) {

			if (fila.length != 15) {
				posIni = 1;
			}

			Rcs rcs = new Rcs();
			rcs.setSecPersonaControl(((Integer) fila[posIni]).intValue());
			rcs.setIdentificacion(fila[posIni + 1].toString());
			rcs.setUsrCreacion(fila[posIni + 2].toString());
			String tipo = fila[posIni + 3].toString();
			Character ch = new Character(tipo.charAt(0));
			rcs.setTipoIdentificacion(new TipoIdentificacion(ch));
			if (fila[posIni + 4] != null) {
				rcs.setDenominacion(fila[posIni + 4].toString());
			}

			rcs.setTsCreacion((Date) fila[posIni + 5]);

			if (fila[posIni + 6] != null) {
				rcs.setCodEstado((Character) fila[posIni + 6]);
			}
			if (fila[posIni + 7] != null) {
				rcs.setCodControl((Character) fila[posIni + 7]);
			}

			rcs.setContenidoXml("");
			if (fila[posIni + 8] != null) {
				rcs.setContenidoXml(fila[posIni + 8].toString());
			}

			if (fila[posIni + 9] != null) {
				EstadoRcs estadoRcs = new EstadoRcs((Character) fila[posIni + 9]);
				estadoRcs.setEstado(fila[posIni + 10].toString());
				rcs.setEstadoRcs(estadoRcs);
			}

			if (fila[posIni + 11] != null) {
				rcs.setComentario(fila[posIni + 11].toString());
			}

			if (fila[posIni + 12] != null) {
				rcs.setIdentificacionConyuge(fila[posIni + 12].toString());
			}

			if (fila[posIni + 13] != null) {
				rcs.setUsrModificacion(fila[posIni + 13].toString());
			}

			if (fila[posIni + 14] != null) {
				rcs.setTsModificacion((Date) fila[posIni + 14]);
			}

			lista.add(rcs);
		}

		return lista;
	}

	private void ponerFrom(StringBuilder sql) {
		sql.append(" from EQUIVIDA.RCS r  ");
		sql.append(" inner join EQUIVIDA.ESTADO_RCS estrcs on (estrcs.COD_ESTADO = r.COD_ESTADO_PERSONA) ");
	}

	private void ponerFiltros(Date fechaDesde, Date fechaHasta, String idUsuario, Character codEstadoRcs,
			boolean contratante, StringBuilder sql) {
		if (idUsuario != null && !idUsuario.trim().equals("")) {
			sql.append(" and r.USR_CREACION ='" + idUsuario + "' ");
		}
		if (fechaDesde != null) {
			sql.append(" and r.TS_CREACION >= :fechaDesde ");
		}
		if (fechaHasta != null) {
			sql.append(" and r.TS_CREACION <= :fechaHasta ");
		}

		if (codEstadoRcs != null && codEstadoRcs != Constantes.CHAR_VACIO) {
			sql.append(" and r.COD_ESTADO_PERSONA = :codEstadoRcs ");
		}

		if (contratante) {
			sql.append(" and r.COD_TIPO_IDENTIFICACION = 'R' ");
		} else {
			sql.append(" and r.COD_TIPO_IDENTIFICACION != 'R' ");
		}
	}

	public static void main(String[] args) {
		System.out.println(Types.STRUCT);
	}

	@Override
	public Long obtenerPorFechaUsuarioIdPaginadoTotal(Date fechaDesde, Date fechaHasta, String idUsuario,
			Character codEstadoRcs, boolean contratante) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*) ");
		ponerFrom(sql);
		sql.append(" where  1=1 ");

		ponerFiltros(fechaDesde, fechaHasta, idUsuario, codEstadoRcs, contratante, sql);

		Query query = em.createNativeQuery(sql.toString());

		DateBetween db = new DateBetween(fechaDesde, fechaHasta);

		ponerFiltrosValores(fechaDesde, fechaHasta, codEstadoRcs, query, db);

		Integer total = (Integer) query.getSingleResult();

		return total.longValue();
	}

	private void ponerFiltrosValores(Date fechaDesde, Date fechaHasta, Character codEstadoRcs, Query query,
			DateBetween db) {
		if (fechaDesde != null) {
			query.setParameter("fechaDesde", db.getFrom());
		}
		if (fechaHasta != null) {
			query.setParameter("fechaHasta", db.getTo());
		}
		if (codEstadoRcs != null && codEstadoRcs != Constantes.CHAR_VACIO) {
			query.setParameter("codEstadoRcs", codEstadoRcs);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Rcs obtenerMasReciente(String identificacion) {

		StringBuilder sql = new StringBuilder();
		sql.append(
				"select r from Rcs r where (r.identificacion = :identificacion or r.identificacionConyuge = :identificacion) order by r.tsCreacion desc ");

		Query query = em.createQuery(sql.toString());
		query.setParameter("identificacion", identificacion);

		query.setMaxResults(1);

		List<Rcs> rcsLista = query.getResultList();

		if (rcsLista.size() > 0) {
			return rcsLista.get(0);
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> obtenerDistintosUsuariosCreacion() {
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct r.USR_CREACION from EQUIVIDA.RCS r ");// 0,1

		Query query = em.createNativeQuery(sql.toString());

		return query.getResultList();
	}
}
