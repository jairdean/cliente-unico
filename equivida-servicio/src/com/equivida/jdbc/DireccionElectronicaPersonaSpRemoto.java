package com.equivida.jdbc;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import com.equivida.dto.portal.RsDireccionElectronicaPersona;

@Remote
public interface DireccionElectronicaPersonaSpRemoto {

	List<RsDireccionElectronicaPersona> llamarDireccionElectronicaPersonaSp(
			String id_persona) throws SQLException;
}