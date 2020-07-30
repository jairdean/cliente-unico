/**
 * 
 */
package com.equivida.jdbc;

import java.sql.SQLException;

import javax.ejb.Local;

import com.equivida.dto.portal.RsDesencriptacion;

/**
 * @author saviasoft5
 * 
 */
@Local
public interface DesencriptacionPwSp {

	RsDesencriptacion desencriptacion(String usuario) throws SQLException;

}