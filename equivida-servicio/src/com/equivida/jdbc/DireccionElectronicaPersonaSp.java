package com.equivida.jdbc;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import com.equivida.dto.portal.RsDireccionElectronicaPersona;

@Local
public interface DireccionElectronicaPersonaSp {

	List<RsDireccionElectronicaPersona> llamarDireccionElectronicaPersonaSp(
			String id_persona) throws SQLException;
}