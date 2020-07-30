package com.equivida.servicio;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.Local;
import javax.xml.rpc.ServiceException;

import com.equivida.dto.RespuestaSiseDto;
import com.equivida.exception.ContratanteException;
import com.equivida.exception.ErrorIngresoWsSiseException;
import com.equivida.exception.RazonSocialException;
import com.equivida.modelo.Persona;
import com.equivida.modelo.PersonaJuridica;
import com.equivida.modelo.PersonaNatural;
import com.equivida.sise.ws.client.ContratanteDto;
import com.equivida.sise.ws.client.RsConductoPago;
import com.equivida.sise.ws.client.RsContratante;
import com.equivida.sise.ws.client.RsCumulosDePago;

/**
 * @author daniel cardenas
 * 
 */
@Local
public interface SiseServicio {

	RespuestaSiseDto insertarWsSiseMpersona(PersonaNatural personaNatural, Persona persona, boolean nuevo,
			String usuario, boolean persisteDirecion)
			throws ServiceException, RemoteException, ErrorIngresoWsSiseException;

	RespuestaSiseDto insertarWsSiseMpersonaJuridica(PersonaJuridica personaJuridica, Persona persona, boolean nuevo,
			String usuario, boolean persisteDirecion)
			throws ServiceException, RemoteException, ErrorIngresoWsSiseException;

	List<RsConductoPago> consultarConductosDePago(long personaIdSise);

	List<RsCumulosDePago> consultar(String cedula);

	/**
	 * Para consumir el WS de contratante.
	 * 
	 * @param dtoIn
	 * @return
	 */
	RsContratante insertarContratante(ContratanteDto dtoIn) throws ContratanteException;

	/**
	 * Se ingresan direcciones y telefonos de una persona.
	 * 
	 * @param persona
	 * @param personaIdSise
	 * @param dto
	 * @throws RemoteException
	 * @throws ServiceException
	 * @throws ErrorIngresoWsSiseException
	 */
	void ingresarActualizarDireccionTelefono(Persona persona, Long personaIdSise, RespuestaSiseDto dto)
			throws RemoteException, ServiceException, ErrorIngresoWsSiseException;

	/**
	 * Se ingresa cabecera en SISE.
	 * 
	 * @param personaIdSise
	 * @param persona
	 * @param personaNatural
	 * @param nuevo
	 * @param usuario
	 * @param dto
	 */
	void insertarWsSiseMsHeader(Long personaIdSise, Persona persona, PersonaNatural personaNatural, boolean nuevo,
			String usuario, RespuestaSiseDto dto);

	String consultarRazonSocial(String documento) throws RazonSocialException;

	/**
	 * Se ingresan direcciones y telefonos de una persona contratante
	 * 
	 * @param persona
	 * @param personaIdSise
	 * @param dto
	 * @throws RemoteException
	 * @throws ServiceException
	 * @throws ErrorIngresoWsSiseException
	 */
	void ingresarActualizarDireccionTelefonoContratante(Persona persona, Long personaIdSise, RespuestaSiseDto dto)
			throws RemoteException, ServiceException, ErrorIngresoWsSiseException;

	/**
	 * Se ingresa cabecera en SISE.
	 * 
	 * @param personaIdSise
	 * @param persona
	 * @param personaNatural
	 * @param nuevo
	 * @param usuario
	 * @param dto
	 */
	void insertarWsSiseMsHeaderPJ(Long personaIdSise, Persona persona, PersonaJuridica personaJuridica, boolean nuevo,
			String usuario, RespuestaSiseDto dto);
}