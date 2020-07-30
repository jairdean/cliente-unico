package com.equivida.jdbc;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import com.equivida.dto.portal.RsDatosPersonaJuridica;

@Local
public interface DatosPersonaJuridicaSp {

	List<RsDatosPersonaJuridica> llamarDatosPersonaJuridicaSp(
			String cod_tipo_identificacion, String identificacion)
			throws SQLException;
}