package com.equivida.dao;

import java.util.List;
import java.util.Map;

import com.equivida.constant.RespuestaEnum;
import com.equivida.modelo.PersonaNatural;
import com.saviasoft.persistence.util.dao.GenericDao;

/**
 * 
 * @author Gerardo Tuquerrez
 * @author Daniel Cardenas
 * 
 */
public interface PersonaNaturalDao extends GenericDao<PersonaNatural, Integer> {

	List<PersonaNatural> obtenerListadoPaginado(String numeroDocumento, String apellidoPaterno, String apellidoMaterno,
			String primerNombre, String segundoNombre, RespuestaEnum cliente, String ordenadoPor, boolean asc,
			int firstRows, int totalRows);

	Long obtenerTotalListadoPaginado(String numeroDocumento, String apellidoPaterno, String apellidoMaterno,
			String primerNombre, String segundoNombre, RespuestaEnum cliente);

	Map<String, Integer> obtenerPersonaNaturalByDocumento(List<String> numeroDocumento);

	PersonaNatural obtenerPersonaNaturalByDocumento(String noDocumento);

	/**
	 * Se quita el enlace del objeto a la sesion.
	 * 
	 * @param personaNatural
	 */
	void detach(PersonaNatural personaNatural);
}
