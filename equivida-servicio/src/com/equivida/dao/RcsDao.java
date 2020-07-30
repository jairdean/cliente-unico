package com.equivida.dao;

import java.util.Date;
import java.util.List;

import com.equivida.modelo.Rcs;
import com.saviasoft.persistence.util.dao.GenericDao;

/**
 * 
 * @author Gerardo Tuquerrez
 * @author Daniel Cardenas
 * 
 */
public interface RcsDao extends GenericDao<Rcs, Integer> {

	List<Rcs> obtenerPorFechaUsuarioId(Date fechaDesde, Date fechaHasta, String idUsuario);

	List<Rcs> obtenerPorFechaUsuarioIdPaginado(Date fechaDesde, Date fechaHasta, String idUsuario,
			Character codEstadoRcs, boolean contratante, int ini, int total);

	Long obtenerPorFechaUsuarioIdPaginadoTotal(Date fechaDesde, Date fechaHasta, String idUsuario,
			Character codEstadoRcs, boolean contratante);

	List<String> obtenerDistintosUsuarios();

	/**
	 * Consulta el ultimo registro con la cedula dada
	 * 
	 * @param identificacion
	 * @return
	 */
	Rcs obtenerMasReciente(String identificacion);

	List<String> obtenerDistintosUsuariosCreacion();

}
