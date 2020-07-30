package com.equivida.servicio;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.xml.rpc.ServiceException;

import com.equivida.constant.RespuestaEnum;
import com.equivida.exception.EmpleoDependienteException;
import com.equivida.exception.EmpleoIndependienteException;
import com.equivida.exception.ErrorIngresoWsSiseException;
import com.equivida.modelo.IngresoMensual;
import com.equivida.modelo.IngresoMensualAdicional;
import com.equivida.modelo.Persona;
import com.equivida.modelo.PersonaNatural;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * @author Daniel Cardenas
 * 
 */
@Local
public interface PersonaNaturalServicio extends GenericService<PersonaNatural, Integer> {

	BigDecimal calcularSaldoMensual(IngresoMensual ingresoMensualLista,
			Collection<IngresoMensualAdicional> ingresoMensualAdicionalLista);

	PersonaNatural findByPkParaFormularioWeb(Integer secPersonaNatural);

	PersonaNatural findByPkParaFormularioWeb(String noDocumento);

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
	void crearPersonaNaturalFormularioWeb(PersonaNatural personaNatural, String usuario, String ip)
			throws EmpleoDependienteException, EmpleoIndependienteException, ServiceException, RemoteException,
			ErrorIngresoWsSiseException;

	/**
	 * Retorna el codigo de sise
	 * 
	 * @param personaNatural
	 * @param usuario
	 * @param ip
	 * @return
	 * @throws EmpleoDependienteException
	 * @throws EmpleoIndependienteException
	 * @throws ServiceException
	 * @throws RemoteException
	 * @throws ErrorIngresoWsSiseException
	 */
	void actualizarPersonaNaturalFormularioWeb(PersonaNatural personaNatural, String usuario, String ip)
			throws EmpleoDependienteException, EmpleoIndependienteException, ServiceException, RemoteException,
			ErrorIngresoWsSiseException;

	Persona obtenerConyuge(Integer secPersona);

	PersonaNatural obtenerConyugePN(Integer secPersona);

	void inactivarRelacion(PersonaNatural personaPrincipal, Short parentezcoId);

	PersonaNatural obtenerPersonaNaturalByPersona(Integer secPersona);

	PersonaNatural obtenerPersonaNaturalByPersonaParaActualizacionCrm(Integer secPersona);

	List<PersonaNatural> obtenerListadoPaginado(String numeroDocumento, String apellidoPaterno, String apellidoMaterno,
			String primerNombre, String segundoNombre, RespuestaEnum cliente, String ordenadoPor, boolean asc,
			int firstRows, int totalRows);

	Long obtenerTotalListadoPaginado(String numeroDocumento, String apellidoPaterno, String apellidoMaterno,
			String primerNombre, String segundoNombre, RespuestaEnum cliente);

	PersonaNatural obtenerPersonaNaturalByDocumento(String numeroDocumento, boolean conRelaciones);

	Map<String, Integer> obtenerPersonaNaturalByDocumento(List<String> numeroDocumento);

	/**
	 * @param personaNatural
	 * @param usuario
	 * @param ip
	 * @throws ServiceException
	 * @throws RemoteException
	 */
	void actualizarPersonaNaturalCrm(PersonaNatural personaNatural, String usuario, String ip)
			throws ServiceException, RemoteException, ErrorIngresoWsSiseException;

	/**
	 * Crea persona natural desde buen viaje.
	 * 
	 * @param personaNatural
	 * @param usuario
	 * @param ip
	 * @throws EmpleoDependienteException
	 * @throws EmpleoIndependienteException
	 * @throws ServiceException
	 * @throws RemoteException
	 * @throws ErrorIngresoWsSiseException
	 */
	void crearPersonaNaturalBuenViaje(PersonaNatural personaNatural, String usuario, String ip)
			throws EmpleoDependienteException, EmpleoIndependienteException, ServiceException, RemoteException,
			ErrorIngresoWsSiseException;

	/**
	 * Se quita el enlace del objeto a la sesion.
	 * 
	 * @param personaNatural
	 */
	void detach(PersonaNatural personaNatural);

	/**
	 * Periste objeto PersonaNatural.
	 * 
	 * @param personaNatural
	 */
	void persitir(PersonaNatural personaNatural);
	
	
	PersonaNatural obtenerPersonaNaturalByPersonaFormulario(Integer secPersona);
	
	void ingresarActualizaConyuge(PersonaNatural personaNatural, boolean nuevaPersona);
}