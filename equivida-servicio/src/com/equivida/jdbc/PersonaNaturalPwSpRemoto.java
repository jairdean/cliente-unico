/**
 * 
 */
package com.equivida.jdbc;

import java.sql.SQLException;

import javax.ejb.Remote;

import com.equivida.dto.portal.RsPersonaNaturalPW;


/**
 * @author saviasoft5
 * 
 */
@Remote
public interface PersonaNaturalPwSpRemoto {

	RsPersonaNaturalPW consultaPersonaNatural(String codTipoId, String id)throws SQLException;

}
