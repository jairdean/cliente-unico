package com.equivida.smartdata.dao;

import javax.ejb.Local;

import com.equivida.smartdata.constante.ConsultaEnEnum;

/**
 * Dao para tablas que no tienen PK, se maneja SQL.
 * 
 * @author juan
 *
 */
@Local
public interface NoPkTablasSdDao {
	/**
	 * Obtiene el codigo de pais en smartdata dado el codigo pais de DataBook.
	 * 
	 * @param codigoPaisDB
	 * @return
	 */
	Short obtenerCodigoPaisPorCodigoDB(Short codigoPaisDB);

	/**
	 * Obtiene el codigo de Provincia en smartdata dado el codigo Provincia de
	 * DataBook.
	 * 
	 * @param codigoPaisDB
	 * @return
	 */
	Short obtenerCodigoProvinciaPorCodigoDB(String codigoProvinciaDB, ConsultaEnEnum consultarEn);

	/**
	 * Obtiene el codigo de Canton en smartdata dado el codigo Canton de
	 * DataBook.
	 * 
	 * @param codigoPaisDB
	 * @return
	 */
	Short obtenerCodigoCantonPorCodigoDB(String codigoCantonDB, ConsultaEnEnum consultarEn);

	/**
	 * Obtiene el codigo de Parroquia en smartdata dado el codigo Parroquia de
	 * DataBook.
	 * 
	 * @param codigoPaisDB
	 * @return
	 */
	Short obtenerCodigoParroquiaPorCodigoDB(String codigoParroquiaDB, ConsultaEnEnum consultarEn);
}
