package com.equivida.smartdata.servicio;

import javax.ejb.Remote;

import com.equivida.smartdata.exception.SmartdataException;
import com.equivida.smartdata.model.PersonaSd;

@Remote
public interface SmartDataServicioSdRemote {
	/**
	 * 
	 * @param identificacion
	 * @return
	 * @throws SmartdataException
	 */
	PersonaSd consultaClienteSmartData(String identificacion,
			boolean conRelaciones) throws SmartdataException;

	/**
	 * Guarda una persona cuya informacion se llena con datos de cliente unico.
	 * 
	 * @param persona
	 * @throws SmartdataException
	 */
	void guardaEnSmartData(PersonaSd persona) throws SmartdataException;

	/**
	 * Consulta identificacion en databook y persite en smartata.
	 * 
	 * @param identificacion
	 * @param usuario
	 * @return
	 */
	PersonaSd consultaEnDataBookPersisteSmartData(String identificacion,
			String usuario) throws SmartdataException;

	/**
	 * 
	 * @param identificacion
	 * @param usuario
	 * @return
	 * @throws SmartdataException
	 */
	PersonaSd consultaDatabook(String identificacion, String usuario)
			throws SmartdataException;
}
