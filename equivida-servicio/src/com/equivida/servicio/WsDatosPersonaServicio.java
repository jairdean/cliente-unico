package com.equivida.servicio;

import java.rmi.RemoteException;
import java.util.Date;

import javax.ejb.Local;
import javax.xml.rpc.ServiceException;

import com.equivida.dto.RespuestaSiseDto;
import com.equivida.dto.ResultadoWebserviceListaReservada;
import com.equivida.dto.ResultadoWebserviceNombresDto;
import com.equivida.exception.ErrorIngresoWsSiseException;

/**
 * @author Daniel Cardenas
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface WsDatosPersonaServicio {

	ResultadoWebserviceNombresDto validarNombre(String nombre) throws ServiceException, RemoteException;

	Boolean validarEmail(String email) throws ServiceException, RemoteException;

	Boolean validarTelefono(String telefono) throws ServiceException, RemoteException;

	ResultadoWebserviceListaReservada validarRiesgo(String firstName, String middleName, String lastName,
			int tipoIdentification, String identification)
			throws ServiceException, RemoteException;

	void ingresarPersonaNaturalSise(String txt_apellido1, String txt_apellido2, String txt_nombre, int cod_tipo_doc,
			String nro_doc, Date fec_nac, String txt_lugar_nac, String txt_sexo, int cod_est_civil,
			String txt_nombres_conyuge, String txt_apellidos_conyuge, int cod_tipo_doc_conyuge, String nro_doc_conyugue,
			String campo_in_1, String campo_in_2, String campo_in_3, String campo_in_4, String campo_in_5,
			RespuestaSiseDto dto) throws ServiceException, RemoteException, ErrorIngresoWsSiseException;

	Long ingresarPersonaDireccionSise(Long id_persona, int cod_tipo_dir, String txt_direccion, int cod_municipio,
			int cod_dpto, int cod_pais, String txt_edificio, String txt_urbanizacion, String txt_sector,
			String campoin1, String campoin2, String campoin3, String campoin4, String campoin5, RespuestaSiseDto dto)
			throws ServiceException, RemoteException, ErrorIngresoWsSiseException;

	Long ingresarPersonaTelefonoSise(Long id_persona, int cod_tipo_telefono, String telefono, String campo_in_1,
			String campo_in_2, String campo_in_3, String campo_in_4, String campo_in_5, RespuestaSiseDto dto)
			throws ServiceException, RemoteException, ErrorIngresoWsSiseException;

	ResultadoWebserviceListaReservada armarEnBaseXml(String contenidoXml);

	Long ingresarContratanteTelefonoSise(Long id_persona, int cod_tipo_telefono, String telefono, RespuestaSiseDto dto)
			throws ServiceException, RemoteException, ErrorIngresoWsSiseException;
	
	Long ingresarContratanteDireccionSise(Long id_persona, int cod_tipo_dir, String txt_direccion, int cod_municipio,
			int cod_dpto, int cod_pais, RespuestaSiseDto dto)
			throws ServiceException, RemoteException, ErrorIngresoWsSiseException;


}
