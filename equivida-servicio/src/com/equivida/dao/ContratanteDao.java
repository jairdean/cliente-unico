package com.equivida.dao;

import java.util.List;

import javax.ejb.Local;

import com.equivida.dto.ContratanteListaDto;

/**
 * 
 * @author Juan Ochoa
 * 
 */
@Local
public interface ContratanteDao {
	/**
	 * Servicio apra buscar contratantes de manera paginada.
	 * 
	 * @param numeroDocumento
	 * @param apellidoPaterno
	 * @param apellidoMaterno
	 * @param primerNombre
	 * @param segundoNombre
	 * @param firstRows
	 * @param totalRows
	 * @return
	 */
	List<ContratanteListaDto> obtenerListadoPaginado(String numeroDocumento, String apellidoPaterno,
			String apellidoMaterno, String primerNombre, String segundoNombre, int firstRows, int totalRows);

	/**
	 * Cuenta registros para paginador.
	 * 
	 * @param numeroDocumento
	 * @param apellidoPaterno
	 * @param apellidoMaterno
	 * @param primerNombre
	 * @param segundoNombre
	 * @return
	 */
	Long obtenerTotalListadoPaginado(String numeroDocumento, String apellidoPaterno, String apellidoMaterno,
			String primerNombre, String segundoNombre);
}
