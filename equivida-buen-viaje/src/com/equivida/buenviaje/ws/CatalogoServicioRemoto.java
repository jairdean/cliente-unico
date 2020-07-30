/**
 * 
 */
package com.equivida.buenviaje.ws;

import javax.ejb.Remote;

import com.equivida.buenviaje.dto.RespuestaCatalogo;

/**
 * @author juan
 *
 */
@Remote
public interface CatalogoServicioRemoto {
	/**
	 * Metodo que devuelve todos los paises de la BDD de CU.
	 * 
	 * @return
	 */
	RespuestaCatalogo consultarPaises();

	/**
	 * Metodo que devuelve todas las provincias de un Pais de la BDD de CU.
	 * 
	 * @param codigoPais
	 * @return
	 */
	RespuestaCatalogo consultarProvincias(String codigoPais);

	/**
	 * Metodo que devuelve todos los cantones de una Provincia de la BDD de CU.
	 * 
	 * @param codigoProvincia
	 * @return
	 */
	RespuestaCatalogo consultarCantones(String codigoProvincia);

	/**
	 * Metodo que devuelve todas las parroquias de un Canton de la BDD de CU.
	 * 
	 * @param codigoCanton
	 * @return
	 */
	RespuestaCatalogo consultarParroquias(String codigoCanton);

	/**
	 * Metodo que devuelve todos los Estados Civiles de la BDD de CU.
	 * 
	 * @return
	 */
	RespuestaCatalogo consultarEstadosCiviles();

	/**
	 * Metodo que devuelve todos los tipos de Identificacion de la BDD de CU.
	 * 
	 * @return
	 */
	RespuestaCatalogo consultarTiposIdentificacion();
}
