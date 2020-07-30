/**
 * 
 */
package com.equivida.jdbc;

import java.sql.SQLException;

import javax.ejb.Local;

import com.equivida.dto.portal.RsPersonaNaturalPW;

/**
 * @author saviasoft5
 * 
 */
@Local
public interface PersonaNaturalPwSp {

	RsPersonaNaturalPW consultaPersonaNatural(String codTipoId, String id)
			throws SQLException;

	RsPersonaNaturalPW consultarDatosPersona(String cedula) throws SQLException;

}