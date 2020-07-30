package com.equivida.servicio;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.Collection;

import javax.ejb.Local;
import javax.xml.rpc.ServiceException;

import com.equivida.exception.EmpleoDependienteException;
import com.equivida.exception.EmpleoIndependienteException;
import com.equivida.exception.ErrorIngresoWsSiseException;
import com.equivida.modelo.IngresoMensual;
import com.equivida.modelo.IngresoMensualAdicional;
import com.equivida.modelo.PersonaNatural;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * @author Daniel Cardenas
 * 
 */
@Local
public interface PersonaNaturalServicioRemoto extends
		GenericService<PersonaNatural, Integer> {

	BigDecimal calcularSaldoMensual(IngresoMensual ingresoMensualLista,
			Collection<IngresoMensualAdicional> ingresoMensualAdicionalLista);

	PersonaNatural findByPk(Integer secPersonaNatural, boolean loadDirecciones,
			boolean loadDireccionesConTelefonos,
			boolean loadDireccionesElectronicas,
			boolean loadIngresoMensualAdicional, boolean loadIngresoAnuales,
			boolean loadReferencias, boolean loadTelefonosAdicionales,
			boolean loadEmpleos, boolean loadOtroEmpleo,
			boolean loadSegurosVigentes);

	/**
	 * Retorna el codigo de SISE
	 * 
	 * @param personaNatural
	 * @return
	 * @throws EmpleoDependienteException
	 * @throws EmpleoIndependienteException
	 * @throws ServiceException
	 * @throws RemoteException
	 * @throws ErrorIngresoWsSiseException
	 */
	Long crearPersonaNaturalFormularioWeb(PersonaNatural personaNatural)
			throws EmpleoDependienteException, EmpleoIndependienteException,
			ServiceException, RemoteException, ErrorIngresoWsSiseException;
}