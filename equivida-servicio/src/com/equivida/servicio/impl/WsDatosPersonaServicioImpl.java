package com.equivida.servicio.impl;

import java.io.StringReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.holders.IntHolder;
import javax.xml.rpc.holders.StringHolder;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.axis.message.MessageElement;
import org.apache.log4j.Logger;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.equivida.dto.RespuestaSiseDto;
import com.equivida.dto.ResultadoWebserviceListaReservada;
import com.equivida.dto.ResultadoWebserviceNombresDto;
import com.equivida.dto.RiesgoDto;
import com.equivida.exception.ErrorIngresoWsSiseException;
import com.equivida.servicio.WsDatosPersonaServicio;
import com.equivida.sise.ws.client.InsertDireccionContratante;
import com.equivida.sise.ws.client.InsertDireccionContratanteWs;
import com.equivida.sise.ws.client.InsertDireccionPersona;
import com.equivida.sise.ws.client.InsertDireccionPersonaWs;
import com.equivida.sise.ws.client.InsertMPersonaWs;
import com.equivida.sise.ws.client.InsertTelefonoContratante;
import com.equivida.sise.ws.client.InsertTelefonoContratanteWs;
import com.equivida.sise.ws.client.InsertTelefonoPersona;
import com.equivida.sise.ws.client.InsertTelefonoPersonaWs;
import com.equivida.sise.ws.client.RespuestaInsertMPersona;
import com.equivida.sise.ws.client.impl.InsertDireccionContratanteWsImplServiceLocator;
import com.equivida.sise.ws.client.impl.InsertDireccionPersonaWsImplServiceLocator;
import com.equivida.sise.ws.client.impl.InsertMPersonaWsImplServiceLocator;
import com.equivida.sise.ws.client.impl.InsertTelefonoContratanteWsImplServiceLocator;
import com.equivida.sise.ws.client.impl.InsertTelefonoPersonaWsImplServiceLocator;
import com.equivida.util.Parametros;
import com.ibm.isd.QA_Equivida.SrvQaEmail.SrvQaEmailOutVar1;
import com.ibm.isd.QA_Equivida.SrvQaEmail.soapoverhttp.SrvQaEmail_PortType;
import com.ibm.isd.QA_Equivida.SrvQaEmail.soapoverhttp.SrvQaEmail_ServiceLocator;
import com.ibm.isd.QA_Equivida.SrvQaNombres.SrvQaNombresOutVar1;
import com.ibm.isd.QA_Equivida.SrvQaNombres.soapoverhttp.SrvQaNombres_PortType;
import com.ibm.isd.QA_Equivida.SrvQaNombres.soapoverhttp.SrvQaNombres_ServiceLocator;
import com.ibm.isd.QA_Equivida.SrvQaTelefonos.SrvQaTelefonosOutVar1;
import com.ibm.isd.QA_Equivida.SrvQaTelefonos.soapoverhttp.SrvQaTelefonos_PortType;
import com.ibm.isd.QA_Equivida.SrvQaTelefonos.soapoverhttp.SrvQaTelefonos_ServiceLocator;

import localhost.BusquedaIDVerificationResponseBusquedaIDVerificationResult;
import localhost.WsRCS_OFACLocator;
import localhost.WsRCS_OFACSoap;
import localhost.ResultadosRevisionOFAC_xsd.ResultadosRevisionOFACResultadosOFAC;

/**
 * @author Gerardo Tuquerrez
 */
@Stateless(name = "WsDatosPersonaServicio")
public class WsDatosPersonaServicioImpl implements WsDatosPersonaServicio {

	private Logger log = Logger.getLogger(WsDatosPersonaServicioImpl.class);

	@Resource
	SessionContext ctx;

	@Override
	public ResultadoWebserviceNombresDto validarNombre(String nombre) throws ServiceException, RemoteException {

		ResultadoWebserviceNombresDto dto = new ResultadoWebserviceNombresDto();
		dto.setCambio(false);
		dto.setValido(false);

		final String TEXTO_NOMBRE_IGUAL = "NOMBRE ES IGUAL";
		final int CORRECT_STATUS = 1;

		String address = Parametros.getString("location.web.service.qa.nombres");
		URL url;
		try {
			url = new URL(address);
			SrvQaNombres_ServiceLocator locator = new SrvQaNombres_ServiceLocator();

			SrvQaNombres_PortType service = locator.getSrvQaNombresSOAP(url);

			SrvQaNombresOutVar1 result = service.QANombresSrv(nombre);

			System.out.println("result.getStatus()" + result.getStatus());
			System.out.println("result.getUnhandledpattern_nombres():" + result.getUnhandledpattern_nombres());
			System.out.println("result.getValidacion():" + result.getValidacion());
			System.out.println("result.getGenero_nombres():" + result.getGenero_nombres());

			if (result.getStatus() == CORRECT_STATUS && result.getUnhandledpattern_nombres() == null) {
				dto.setValido(true);
			}

			if (!result.getValidacion().equals(TEXTO_NOMBRE_IGUAL)) {
				// entonces hay sugerecia de nombre
				dto.setCambio(true);
				dto.setSugerenciaNombre1(result.getFirstname_nombres());
				dto.setSugerenciaNombre2(result.getSecondname_nombres());
				dto.setSugerenciaApellido1(result.getFirstlastname_nombres());
				dto.setSugerenciaApellido2(result.getSecondlastname_nombres());
				dto.setSexoSugerido(result.getGenero_nombres());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			dto.setError(true);
		} catch (Exception e) {
			e.printStackTrace();
			dto.setError(true);
		}

		return dto;
	}

	@Override
	public Boolean validarEmail(String email) throws ServiceException, RemoteException {

		boolean valid = false;

		String address = Parametros.getString("location.web.service.qa.emails");
		URL url;
		try {
			url = new URL(address);

			SrvQaEmail_ServiceLocator locator = new SrvQaEmail_ServiceLocator();

			SrvQaEmail_PortType service = locator.getSrvQaEmailSOAP(url);

			SrvQaEmailOutVar1 result = service.QAEmailSrv(email);

			if (result.getUnhandledpattern_emails() == null && result.getMensaje() == null) {
				valid = true;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return valid;
	}

	@Override
	public Boolean validarTelefono(String telefono) throws ServiceException, RemoteException {

		// if (true) {
		// return true;
		// }

		final int CORRECT_STATUS = 1;

		boolean valid = false;

		String address = Parametros.getString("location.web.service.qa.telefonos");
		URL url;
		try {
			url = new URL(address);

			SrvQaTelefonos_ServiceLocator locator = new SrvQaTelefonos_ServiceLocator();

			SrvQaTelefonos_PortType service = locator.getSrvQaTelefonosSOAP(url);

			SrvQaTelefonosOutVar1 result = service.qaTelefonosSrv(telefono);

			System.out.println("result.getUnhandledpattern_telefonos():" + result.getUnhandledpattern_telefonos());
			System.out.println("result.getStatus():" + result.getStatus());
			System.out.println("result.getMessage():" + result.getMessage());

			if (result.getUnhandledpattern_telefonos() == null && result.getStatus() == CORRECT_STATUS) {
				valid = true;
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return valid;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResultadoWebserviceListaReservada validarRiesgo(String firstName, String middleName, String lastName,
			int tipoIdentification, String identification) throws ServiceException, RemoteException {

		System.out.println("firstName:" + firstName);
		System.out.println("middleName:" + middleName);
		if (middleName == null) {
			middleName = "";
		}
		System.out.println("lastName:" + lastName);
		System.out.println("identification:" + identification);
		System.out.println("tipoIdentification:" + tipoIdentification);

		String licenciaEmpresa = "E777";
		String tipoOrganizacion = "I";
		// String firstName = "Hugo";
		// String middleName = "";
		// String lastName = "Reyes";
		// int tipoIdentificacion = 1;
		// String identificacion = "1010101010";
		String direccion = "";
		String ciudad = "";
		String estado = "";
		String codigoPostal = "";
		String pais = "";
		String fechaNacimiento = "";
		String generacion = "";
		String telfCasa = "";
		String telfTrabajo = "";
		String issuingCountry = "";
		String expDate = "";
		String idPolitica = "20110926091154";
		String userName = "CLIUNI";
		String perfilUsuario = "administrador";
		String valoresDefault = "";
		String rutaSetupDB = "C:\\Bridger\\COM\\";
		String rutaReportes = "C:\\Bridger\\COM\\";
		String rutaLog = "logs\\";
		int proveedorIDV = 4;
		String nombreLog = "LogErrorWS_OFAC.txt";
		String pathProveedorIDV = "C:\\Bridger\\BridgerDataFiles\\";
		int identityVerificationSetupOption = 0;
		String metodoBusqueda = "AUTO";
		StringHolder primaryKey = new StringHolder();
		StringHolder descripcionError = new StringHolder();
		IntHolder codError = new IntHolder(1);

		ResultadoWebserviceListaReservada resultado = new ResultadoWebserviceListaReservada();
		StringBuilder contenidoXml = new StringBuilder("<rcs>");

		String address = Parametros.getString("location.web.service.listas.reservadas");
		URL url;
		try {
			url = new URL(address);

			WsRCS_OFACLocator wsRCS_OFACLocator = new WsRCS_OFACLocator();

			WsRCS_OFACSoap service = wsRCS_OFACLocator.getwsRCS_OFACSoap(url);

			BusquedaIDVerificationResponseBusquedaIDVerificationResult result = service.busquedaIDVerification(
					licenciaEmpresa, tipoOrganizacion, firstName, middleName, lastName, tipoIdentification,
					identification, direccion, ciudad, estado, codigoPostal, pais, fechaNacimiento, generacion,
					telfCasa, telfTrabajo, issuingCountry, expDate, idPolitica, userName, perfilUsuario, valoresDefault,
					rutaSetupDB, rutaReportes, rutaLog, nombreLog, proveedorIDV, pathProveedorIDV,
					identityVerificationSetupOption, metodoBusqueda, primaryKey, codError, descripcionError);

			log.info("OK");
			MessageElement[] messages = result.get_any();
			System.out.println("messages.length:" + messages.length);

			MessageElement diffgram = messages[1];

			System.out.println("-->" + diffgram.getName());

			List<ResultadosRevisionOFACResultadosOFAC> listR = new ArrayList<ResultadosRevisionOFACResultadosOFAC>();
			List<RiesgoDto> riesgoDtoLista = new ArrayList<RiesgoDto>();

			// int score = 0;

			Iterator itDiff = diffgram.getChildElements();
			while (itDiff.hasNext()) {
				MessageElement resultadosRevisionOFACME = (MessageElement) itDiff.next();

				// System.out.println("resultadosRevisionOFACME:"
				// + resultadosRevisionOFACME.getName());

				Iterator itRrofac = resultadosRevisionOFACME.getChildElements();

				contenidoXml.append(resultadosRevisionOFACME.getAsString());

				while (itRrofac.hasNext()) {
					MessageElement me = (MessageElement) itRrofac.next();
					ResultadosRevisionOFACResultadosOFAC rofac;

					try {
						rofac = (ResultadosRevisionOFACResultadosOFAC) me.getValueAsType(
								new javax.xml.namespace.QName("http://localhost/ResultadosRevisionOFAC.xsd",
										">>ResultadosRevisionOFAC>ResultadosOFAC"));

						// rofac = (ResultadosRevisionOFACResultadosOFAC) me
						// .getValueAsType(new javax.xml.namespace.QName(
						// "http://localhost/ResultadosRevisionOFAC.xsd",
						// ">>ResultadosRevisionOFAC>ResultadosOFAC"));
						RiesgoDto dto = new RiesgoDto();
						// int scoreIter = Integer.parseInt(rofac.getScore());

						// log.info(rofac.getBestName() + " >> SCORE: " +
						// scoreIter
						// + " file:" + rofac.getFile());

						// if (scoreIter > score) {

						// System.out
						// .println(rofac.getBestName() + " >> SCORE: "
						// + rofac.getScore() + " file:"
						// + rofac.getFile());
						// System.out.println(rofac.getListing());

						dto.setListing(rofac.getListing());
						if (rofac.getFile() != null) {
							dto.setFile(rofac.getFile());
							if (rofac.getFile().startsWith("DENY")) {
								dto.setNacional(true);
							}
						}
						// score = scoreIter;
						// }
						listR.add(rofac);
						riesgoDtoLista.add(dto);
					} catch (Exception e) {
						System.out.println("Errror:" + e);
						e.printStackTrace();
						throw new ServiceException("Error:" + e);
					}

				}
			}

			log.info("LISTA SIZE:" + listR.size());

			if (listR.size() > 0) {

				resultado.setConRiesgo(true);
				resultado.setRiesgoDtoLista(riesgoDtoLista);

			}

			contenidoXml.append("</rcs>");
			resultado.setContenidoXml(contenidoXml.toString());

		} catch (MalformedURLException e) {
			System.out.println("ERROR al consumir servicio web de listas reservadas: " + e.getLocalizedMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("ERROR al consumir servicio web de listas reservadas: " + e.getLocalizedMessage());
			e.printStackTrace();
		}

		return resultado;
	}

	@Override
	public void ingresarPersonaNaturalSise(String txt_apellido1, String txt_apellido2, String txt_nombre,
			int cod_tipo_doc, String nro_doc, Date fec_nac_d, String txt_lugar_nac, String txt_sexo, int cod_est_civil,
			String txt_nombres_conyuge, String txt_apellidos_conyuge, int cod_tipo_doc_conyuge, String nro_doc_conyugue,
			String campo_in_1, String campo_in_2, String campo_in_3, String campo_in_4, String campo_in_5,
			RespuestaSiseDto dto) throws ServiceException, RemoteException, ErrorIngresoWsSiseException {

		log.info("En ingresarPersonaNaturalSise ...");
		// if (true) {
		// return -1;
		// }

		final int COD_TIPO_IVA = 1;

		String TXT_ORIGEN = "A";

		String NRO_NIT = "";// solo para cuando es ruc

		String COD_TIPO_PERSONA = "F";// para naturales

		final String FORMATO_FECHA = "yyyy/MM/dd";// para naturales

		String fec_nac = null;

		if (fec_nac_d != null) {
			SimpleDateFormat format = new SimpleDateFormat(FORMATO_FECHA);
			fec_nac = format.format(fec_nac_d);
		}

		// cuando es ruc, se queman estos valores por pedido de Kathy
		if (nro_doc.trim().length() > 10 && cod_tipo_doc != 1) {
			NRO_NIT = new String(nro_doc);
			COD_TIPO_PERSONA = "J";
			TXT_ORIGEN = null;
			nro_doc = null;
			fec_nac = null;
			txt_sexo = "M";
			cod_est_civil = 1;
		} else if (cod_tipo_doc == 1 && nro_doc.trim().length() > 10
				&& !(nro_doc.charAt(2) == '9' || nro_doc.charAt(2) == '6')) {
			nro_doc = nro_doc.substring(0, 10);
		}

		InsertMPersonaWsImplServiceLocator locator = new InsertMPersonaWsImplServiceLocator();

		String address = Parametros.getString("location.web.service.sise.mpersona");

		System.out.println("se consume el siguiente servicio: " + address);

		URL url;

		BigDecimal idPersona = null;

		try {
			url = new URL(address);

			InsertMPersonaWs servicio = locator.getInsertMPersonaWsImplPort(url);

			System.out.println("CODIGO_TIPO_DOC -------------" + cod_tipo_doc);
			System.out.println("NRO_DOC -------------" + nro_doc);
			System.out.println("NRO_NIT -------------" + NRO_NIT);

			RespuestaInsertMPersona response = servicio.ws_sise_insert_mpersona(txt_apellido1, txt_apellido2,
					txt_nombre, BigDecimal.valueOf(cod_tipo_doc), nro_doc, BigDecimal.valueOf(COD_TIPO_IVA), NRO_NIT,
					fec_nac, txt_lugar_nac, txt_sexo, BigDecimal.valueOf(cod_est_civil), COD_TIPO_PERSONA, TXT_ORIGEN,
					txt_nombres_conyuge, txt_apellidos_conyuge, BigDecimal.valueOf(cod_tipo_doc_conyuge),
					nro_doc_conyugue, campo_in_1, campo_in_2, campo_in_3, campo_in_4, campo_in_5);

			// Response response = servicio.invoke(request);

			idPersona = response.getIdPersona();

			System.out.println("Response:" + response.getCampoOut1());
			System.out.println("Id persona:" + idPersona);
			System.out.println("Error:" + response.getTxtError());
			if (response.getTxtError() != null) {
				System.out.println("El tamano: " + response.getTxtError().trim().length());
			}

			if (response.getTxtError() != null && response.getTxtError().trim().length() > 0) {
				dto.getErrorLista().add(response.getTxtError());
			}

			// produccion
			// si es null o devuelve 0 no tiene error
			if (idPersona != null) {
				// eontces no hay error y obtenemos
				dto.setIdPersonaSise(idPersona.longValue());
			}

		} catch (MalformedURLException e) {
			System.out.println("URL de web service de mpersona esta mal");
			dto.getErrorLista().add(e.getMessage());
			// throw new RuntimeException(e);
		}
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Long ingresarPersonaDireccionSise(Long id_persona, int cod_tipo_dir, String txt_direccion, int cod_municipio,
			int cod_dpto, int cod_pais, String txt_edificio, String txt_urbanizacion, String txt_sector,
			String campoin1, String campoin2, String campoin3, String campoin4, String campoin5, RespuestaSiseDto dto)
			throws ServiceException, RemoteException, ErrorIngresoWsSiseException {

		log.info("En ingresarPersonaDireccionSise ...");

		Long r = null;

		InsertDireccionPersonaWsImplServiceLocator locator = new InsertDireccionPersonaWsImplServiceLocator();

		String address = Parametros.getString("location.web.service.sise.direccion");

		URL url;

		try {
			url = new URL(address);

			InsertDireccionPersonaWs servicio = locator.getInsertDireccionPersonaWsImplPort(url);

			System.out.println("URL: SERVICIO - DIRECCION " + url);

			System.out.println("1. " + id_persona);
			System.out.println("2. " + cod_tipo_dir);
			System.out.println("3. " + txt_direccion);
			System.out.println("4. " + cod_municipio);
			System.out.println("5. " + cod_dpto);
			System.out.println("6. " + cod_pais);
			System.out.println("7. " + txt_edificio);
			System.out.println("8. " + txt_urbanizacion);
			System.out.println("9. " + txt_sector);
			System.out.println("10. " + campoin1);
			System.out.println("11. " + campoin2);
			System.out.println("12. " + campoin3);
			System.out.println("13. " + campoin4);
			System.out.println("14. " + campoin5);

			InsertDireccionPersona repuesta = servicio.ws_sise_insert_mpersonadir(BigDecimal.valueOf(id_persona),
					BigDecimal.valueOf(cod_tipo_dir), txt_direccion, BigDecimal.valueOf(cod_municipio),
					BigDecimal.valueOf(cod_dpto), BigDecimal.valueOf(cod_pais), txt_edificio, txt_urbanizacion,
					txt_sector, campoin1, campoin2, campoin3, campoin4, campoin5);

			System.out.println("ID:" + repuesta.getIdDireccion());
			System.out.println("error:" + repuesta.getCodError());
			System.out.println("merror:" + repuesta.getMsgError());

			if (repuesta.getMsgError() != null) {
				dto.getErrorLista().add(repuesta.getMsgError());
			}

			BigDecimal rBG = repuesta.getIdDireccion();

			if (rBG != null) {
				r = rBG.toBigInteger().longValue();
			}

		} catch (MalformedURLException e) {
			System.out.println("URL de web service de mpersona direccion esta mal");
			dto.getErrorLista().add(e.getMessage());
			// throw new RuntimeException(e);
		}

		// retorna id sise
		return r;
	}

	@Override
	public Long ingresarPersonaTelefonoSise(Long id_persona_l, int cod_tipo_telefono, String telefono,
			String campo_in_1, String campo_in_2, String campo_in_3, String campo_in_4, String campo_in_5,
			RespuestaSiseDto dto) throws ServiceException, RemoteException, ErrorIngresoWsSiseException {

		Long r = null;

		log.info("En ingresarPersonaTelefonoSise ...");

		BigDecimal id_persona = BigDecimal.valueOf(id_persona_l);

		String address = Parametros.getString("location.web.service.sise.telefono");

		URL url;
		try {
			url = new URL(address);

			InsertTelefonoPersonaWsImplServiceLocator locator = new InsertTelefonoPersonaWsImplServiceLocator();

			InsertTelefonoPersonaWs servicio = locator.getInsertTelefonoPersonaWsImplPort(url);

			InsertTelefonoPersona repuesta = servicio.ws_sise_insert_mpersona_telef(id_persona,
					BigDecimal.valueOf(cod_tipo_telefono), telefono, campo_in_1, campo_in_2, campo_in_3, campo_in_4,
					campo_in_5);

			System.out.println("ID:" + repuesta.getIdTelefono());
			System.out.println("Num filas:" + repuesta.getNumFilas());
			System.out.println("error:" + repuesta.getCodError());
			System.out.println("merror:" + repuesta.getMsgError());

			if (repuesta.getMsgError() != null) {
				dto.getErrorLista().add(repuesta.getMsgError());
			}

			BigDecimal rBG = repuesta.getIdTelefono();

			if (rBG != null) {
				r = rBG.toBigInteger().longValue();
			}

		} catch (MalformedURLException e) {
			System.out.println("URL de web service de mpersona esta mal");
			dto.getErrorLista().add(e.getMessage());
			// throw new RuntimeException(e);
		}

		// retorna id de sise
		return r;
	}

	@Override
	public ResultadoWebserviceListaReservada armarEnBaseXml(String contenidoXml) {

		ResultadoWebserviceListaReservada resultado = new ResultadoWebserviceListaReservada();

		if (contenidoXml.trim().length() == 0) {
			return resultado;
		}

		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();

		InputSource source = new InputSource(new StringReader(contenidoXml));

		try {
			NodeList nodeList = (NodeList) xpath.evaluate("/rcs/*/*", source, XPathConstants.NODESET);

			// source.getByteStream().reset();

			// System.out.println(" AAAAA " + nodeList.getLength());

			List<RiesgoDto> riesgoDtoLista = new ArrayList<RiesgoDto>();

			for (int i = 0; i < nodeList.getLength(); i++) {
				Node nodeRofac = nodeList.item(i);

				// System.out.println("nodeRofac:" + nodeRofac.getNodeName());

				RiesgoDto dto = new RiesgoDto();

				if (nodeRofac.hasChildNodes()) {
					NodeList listaHijosRofac = nodeRofac.getChildNodes();
					// System.out.println("hijos listaHijosRofac lenght:"
					// + listaHijosRofac.getLength());

					for (int j = 0; j < listaHijosRofac.getLength(); j++) {
						Node nodo = listaHijosRofac.item(j);
						// System.out.println("nodo:" + nodo.getNodeName());

						if (nodo.getNodeName().equals("Listing")) {
							dto.setListing(nodo.getTextContent());
						}
						if (nodo.getNodeName().equals("Score")) {
							dto.setScore(nodo.getTextContent());
						}
						if (nodo.getNodeName().equals("File")) {
							dto.setFile(nodo.getTextContent());
							if (dto.getFile().startsWith("DENY")) {
								dto.setNacional(true);
							}
						}
					}
				}

				riesgoDtoLista.add(dto);

				resultado.setConRiesgo(true);
				resultado.setRiesgoDtoLista(riesgoDtoLista);

			}

		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultado;
	}

	@Override
	public Long ingresarContratanteTelefonoSise(Long id_persona_l, int cod_tipo_telefono, String telefono,
			RespuestaSiseDto dto) throws ServiceException, RemoteException, ErrorIngresoWsSiseException {

		Long r = null;

		log.info("En ingresarContratanteTelefonoSise ...");

		BigDecimal id_persona = BigDecimal.valueOf(id_persona_l);

		String address = Parametros.getString("location.web.service.sise.contratante.telefono");

		URL url;
		try {
			url = new URL(address);

			InsertTelefonoContratanteWsImplServiceLocator locator = new InsertTelefonoContratanteWsImplServiceLocator();

			InsertTelefonoContratanteWs servicio = locator.getInsertTelefonoContratanteWsImplPort(url);

			InsertTelefonoContratante repuesta = servicio.spi_mpersona_telef_wkf(id_persona,
					BigDecimal.valueOf(cod_tipo_telefono), telefono);

			System.out.println("Id_mper_telef_cod_out:" + repuesta.getId_mper_telef_cod_out());
			System.out.println("error:" + repuesta.getCod_error());
			System.out.println("merror:" + repuesta.getMessage_wkf());

			if (repuesta.getMessage_wkf() != null) {
				dto.getErrorLista().add(repuesta.getMessage_wkf());
			}

			BigDecimal rBG = repuesta.getId_mper_telef_cod_out();

			if (rBG != null) {
				r = rBG.toBigInteger().longValue();
			}

		} catch (MalformedURLException e) {
			System.out.println("URL de web service de contratante telefono esta mal");
			dto.getErrorLista().add(e.getMessage());
			// throw new RuntimeException(e);
		}

		// retorna id de sise
		return r;
	}

	@Override
	public Long ingresarContratanteDireccionSise(Long id_persona, int cod_tipo_dir, String txt_direccion,
			int cod_municipio, int cod_dpto, int cod_pais, RespuestaSiseDto dto)
			throws ServiceException, RemoteException, ErrorIngresoWsSiseException {

		log.info("En ingresarContratanteDireccionSise ...");

		Long r = null;

		InsertDireccionContratanteWsImplServiceLocator locator = new InsertDireccionContratanteWsImplServiceLocator();

		String address = Parametros.getString("location.web.service.sise.contratante.direccion");

		URL url;

		try {
			url = new URL(address);

			InsertDireccionContratanteWs servicio = locator.getInsertDireccionContratanteWsImplPort(url);

			System.out.println("URL: SERVICIO - DIRECCION " + url);

			System.out.println("1. " + id_persona);
			System.out.println("2. " + cod_tipo_dir);
			System.out.println("3. " + txt_direccion);
			System.out.println("4. " + cod_municipio);
			System.out.println("5. " + cod_dpto);
			System.out.println("6. " + cod_pais);

			InsertDireccionContratante repuesta = servicio.spi_mpersona_dir_wkf(BigDecimal.valueOf(id_persona),
					BigDecimal.valueOf(cod_tipo_dir), txt_direccion, BigDecimal.valueOf(cod_municipio),
					BigDecimal.valueOf(cod_dpto), BigDecimal.valueOf(cod_pais));

			System.out.println("ID:" + repuesta.getId_mpersona_dir_out());
			System.out.println("error:" + repuesta.getCod_error());
			System.out.println("merror:" + repuesta.getMessage_wkf());

			if (repuesta.getMessage_wkf() != null) {
				dto.getErrorLista().add(repuesta.getMessage_wkf());
			}

			BigDecimal rBG = repuesta.getId_mpersona_dir_out();

			if (rBG != null) {
				r = rBG.toBigInteger().longValue();
			}

		} catch (MalformedURLException e) {
			System.out.println("URL de web service de contratante direccion esta mal");
			dto.getErrorLista().add(e.getMessage());
			// throw new RuntimeException(e);
		}

		// retorna id sise
		return r;
	}

}