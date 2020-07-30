package com.equivida.jdbc;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import com.equivida.dto.portal.RsDatosPersonaJuridica;

@Remote
public interface DatosPersonaJuridicaSpRemoto {

	List<RsDatosPersonaJuridica> llamarDatosPersonaJuridicaSp(
			String cod_tipo_identificacion, String identificacion)
			throws SQLException;
}