package com.equivida.servicio;

import java.rmi.RemoteException;

import javax.ejb.Local;
import javax.xml.rpc.ServiceException;

import com.equivida.exception.EmpleoDependienteException;
import com.equivida.exception.EmpleoIndependienteException;
import com.equivida.exception.ErrorIngresoWsSiseException;
import com.equivida.modelo.PersonaNatural;

@Local
public interface PersistenciaGeneralServicio {
	/**
	 * Persiste Persona natural en CU y en otras BDD de ser el caso.
	 * 
	 * @param personaNatural
	 * @param usuario
	 * @param ip
	 * @throws RemoteException
	 * @throws EmpleoDependienteException
	 * @throws EmpleoIndependienteException
	 * @throws ServiceException
	 * @throws ErrorIngresoWsSiseException
	 */
	void persistirGeneral(PersonaNatural personaNatural, String usuario,
			String ip) throws RemoteException, EmpleoDependienteException,
			EmpleoIndependienteException, ServiceException,
			ErrorIngresoWsSiseException;
}
