/**
 * 
 */
package com.equivida.jdbc;

import java.sql.SQLException;

import javax.ejb.Remote;

import com.equivida.dto.portal.RsDesencriptacion;

/**
 * @author saviasoft5
 * 
 */
@Remote
public interface DesencriptacionPwSpRemoto {

	RsDesencriptacion desencriptacion(String usuario) throws SQLException;

}