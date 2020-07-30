package com.equivida.crm.ws.impl;

import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.rpc.ServiceException;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.log4j.Logger;
import org.tempuri.ActualizacionesRegularesLocator;
import org.tempuri.ActualizacionesRegularesSoap;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.equivida.constant.Constantes;
import com.equivida.constant.EstadoEnum;
import com.equivida.crm.dto.RespuestaActualizacionDto;
import com.equivida.crm.exception.CrmException;
import com.equivida.crm.rs.CompromisosPagosRs;
import com.equivida.crm.rs.EmpleadosRs;
import com.equivida.crm.rs.ListaAseguradoCuponPendienteRs;
import com.equivida.crm.rs.ListaAseguradoPorAsesorRs;
import com.equivida.crm.rs.ListaBeneficiariosRs;
import com.equivida.crm.rs.ListaCoberturasRs;
import com.equivida.crm.rs.ListaCondicionConductoRs;
import com.equivida.crm.rs.ListaCondicionPagoDirectoRs;
import com.equivida.crm.rs.ListaCondicionPeriodoGraciaRs;
import com.equivida.crm.rs.ListaCondicionTarjetaExpiradaRs;
import com.equivida.crm.rs.ListaConductoAniversarioRs;
import com.equivida.crm.rs.ListaConductoPagoRs;
import com.equivida.crm.rs.ListaCuponesPendientesRs;
import com.equivida.crm.rs.ListaDatosCompaniaRs;
import com.equivida.crm.rs.ListaDatosPersonaRs;
import com.equivida.crm.rs.ListaEstadoCuentaRs;
import com.equivida.crm.rs.ListaPagosRs;
import com.equivida.crm.rs.ListaPolizasRs;
import com.equivida.crm.sp.CompromisosPagosSp;
import com.equivida.crm.sp.EmpleadosSp;
import com.equivida.crm.sp.ListaAseguradoCuponPendienteSp;
import com.equivida.crm.sp.ListaAseguradoPorAsesorSp;
import com.equivida.crm.sp.ListaBeneficiariosSp;
import com.equivida.crm.sp.ListaCoberturasSp;
import com.equivida.crm.sp.ListaCondicionConductoBcoEqSp;
import com.equivida.crm.sp.ListaCondicionConductoSp;
import com.equivida.crm.sp.ListaCondicionPagoDirectoSp;
import com.equivida.crm.sp.ListaCondicionPeriodoGraciaSp;
import com.equivida.crm.sp.ListaCondicionTarjetaExpiradaSp;
import com.equivida.crm.sp.ListaConductoAniversarioSp;
import com.equivida.crm.sp.ListaConductoPagoSp;
import com.equivida.crm.sp.ListaCuponesPendientesSp;
import com.equivida.crm.sp.ListaDatosCompaniaSp;
import com.equivida.crm.sp.ListaDatosPersonaSp;
import com.equivida.crm.sp.ListaEstadoCuentaSp;
import com.equivida.crm.sp.ListaPagosSp;
import com.equivida.crm.sp.ListaPolizasSp;
import com.equivida.crm.util.ActualizacionPersonaUtil;
import com.equivida.crm.ws.CrmWs;
import com.equivida.dto.CompaniaCrmDto;
import com.equivida.dto.PersonaCrmDto;
import com.equivida.exception.ErrorIngresoWsSiseException;
import com.equivida.homologacion.servicio.PersonaEquividaServicio;
import com.equivida.modelo.ContactoPreferido;
import com.equivida.modelo.Direccion;
import com.equivida.modelo.DireccionTelefono;
import com.equivida.modelo.Pais;
import com.equivida.modelo.Persona;
import com.equivida.modelo.PersonaNatural;
import com.equivida.modelo.Telefono;
import com.equivida.modelo.TipoTelefono;
import com.equivida.servicio.CantonServicio;
import com.equivida.servicio.PaisServicio;
import com.equivida.servicio.PersonaNaturalServicio;
import com.equivida.servicio.PersonaServicio;
import com.equivida.servicio.WsDatosPersonaServicio;
import com.equivida.util.CrmUtil;
import com.equivida.util.Parametros;

@Stateless(name = "CrmWs")
@WebService(endpointInterface = "com.equivida.crm.ws.CrmWs")
@Remote(CrmWs.class)
public class CrmWsImpl implements CrmWs {

	private static final Logger LOG = Logger.getLogger(CrmWsImpl.class.getName());

	@EJB
	private ListaBeneficiariosSp listaBeneficiariosSp;

	@EJB(mappedName = "PersonaEquividaServicio/local")
	private PersonaEquividaServicio personaEquividaServicio;

	@EJB(mappedName = "PersonaServicio/local")
	private PersonaServicio personaServicio;

	@EJB(mappedName = "PersonaNaturalServicio/local")
	private PersonaNaturalServicio personaNaturalServicio;

	@EJB(mappedName = "CantonServicio/local")
	private CantonServicio cantonServicio;

	@EJB(mappedName = "PaisServicio/local")
	private PaisServicio paisServicio;

	@EJB(mappedName = "WsDatosPersonaServicio/local")
	private WsDatosPersonaServicio wsDatosPersonaServicio;

	@EJB
	private ListaCoberturasSp listaCoberturasSp;

	@EJB
	private ListaCuponesPendientesSp listaCuponesPendientesSp;

	@EJB
	private CompromisosPagosSp compromisosPagosSp;

	@EJB
	private ListaPagosSp listaPagosSp;

	@EJB
	private ListaConductoAniversarioSp listaConductoAniversarioSp;

	@EJB
	private ListaConductoPagoSp listaConductoPagoSp;

	@EJB
	private ListaAseguradoPorAsesorSp listaAseguradoPorAsesorSp;

	@EJB
	private ListaAseguradoCuponPendienteSp listaAseguradoCuponPendienteSp;

	@EJB
	private ListaCondicionConductoSp listaCondicionConductoSp;

	@EJB
	private ListaCondicionPeriodoGraciaSp listaCondicionPeriodoGraciaSp;

	@EJB
	private ListaCondicionTarjetaExpiradaSp listaCondicionTarjetaExpiradaSp;

	@EJB
	private ListaCondicionPagoDirectoSp listaCondicionPagoDirectoSp;

	@EJB
	private ListaDatosCompaniaSp listaDatosCompaniaSp;

	@EJB
	private ListaDatosPersonaSp listaDatosPersonaSp;

	@EJB
	private ListaPolizasSp listaPolizasSp;

	@EJB
	private EmpleadosSp empleadosSp;

	@EJB
	private ListaEstadoCuentaSp listaEstadoCuentaSp;

	@EJB
	private ListaCondicionConductoBcoEqSp listaCondicionConductoBcoEqSp;

	@Resource
	private WebServiceContext wsContext;

	private String getIpRemoto() {
		MessageContext mc = wsContext.getMessageContext();
		HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
		String ip = req.getRemoteAddr();
		System.out.println("Client IP = " + ip);
		return ip;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public String procesar(String xml) {

		System.out.println("xml:");
		System.out.println(xml);

		StringBuilder respuesta = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		dbf.setNamespaceAware(true);
		dbf.setIgnoringElementContentWhitespace(true);
		dbf.setIgnoringComments(true);
		dbf.setExpandEntityReferences(false);
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(new InputSource(new StringReader(xml)));

			Node stream = doc.getFirstChild();
			Node header = stream.getFirstChild();
			Node itransaccion = header.getFirstChild();
			String numeroTransaccion = itransaccion.getTextContent();

			// consulta persona
			if (numeroTransaccion.equals("009")) {

				consultaPersonas(respuesta, stream, numeroTransaccion);
			}

			// beneficiarios
			if (numeroTransaccion.equals("019")) {

				// parametros
				String numPol = null;
				String codigoSucursal = null;
				String ramo = null;

				NodeList lista = stream.getChildNodes();
				for (int i = 0; i < lista.getLength(); i++) {
					if (lista.item(i).getNodeName().equals("PARAMETROS")) {
						Node parametros = lista.item(i);
						NodeList listaParametros = parametros.getChildNodes();

						for (int x = 0; x < listaParametros.getLength(); x++) {
							if (listaParametros.item(x).getNodeName().equals("NROPOL")) {
								numPol = listaParametros.item(x).getTextContent();
							}
							if (listaParametros.item(x).getNodeName().equals("CODSUC")) {
								codigoSucursal = listaParametros.item(x).getTextContent();
							}
							if (listaParametros.item(x).getNodeName().equals("RAMO")) {
								ramo = listaParametros.item(x).getTextContent();
							}
						}
					}
				}

				Long numeroPoliza = Long.parseLong(numPol);
				Integer sucursal = Integer.parseInt(codigoSucursal);
				Integer ramoParam = Integer.parseInt(ramo);

				List<ListaBeneficiariosRs> listaR = listaBeneficiariosSp.listaBeneficiariosSp(sucursal, ramoParam,
						numeroPoliza);

				respuesta.append("<STREAM>");
				respuesta.append("<HEADER><ITRANSACCION>" + numeroTransaccion + "</ITRANSACCION></HEADER>");

				respuesta.append("<BENEFICIARIOS>");
				for (ListaBeneficiariosRs rs : listaR) {
					respuesta.append("<BENEFICIARIO>");
					respuesta.append("<NROPOL>" + rs.getNumero_Poliza() + "</NROPOL>");
					respuesta.append("<TPO>" + rs.getTipo() + "</TPO>");
					respuesta.append("<NOMB1>" + rs.getPrimer_Nombre() + "</NOMB1>");
					respuesta.append("<NOMB2>" + rs.getSegundo_Nombre() + "</NOMB2>");
					respuesta.append("<APEPAT>" + rs.getApellido_Paterno() + "</APEPAT>");
					respuesta.append("<APEMAT>" + rs.getApellido_Materno() + "</APEMAT>");
					respuesta.append("<PAREN>" + rs.getParentezco() + "</PAREN>");
					respuesta.append("<PORCEN>" + rs.getPorcentaje() + "</PORCEN>");
					respuesta.append("<RELASEG>" + rs.getRelacion_con_contratante() + "</RELASEG>");
					respuesta.append("</BENEFICIARIO>");
				}
				respuesta.append("</BENEFICIARIOS>");
				respuesta.append("</STREAM>");
			}

			// lista coberturas
			if (numeroTransaccion.equals("020")) {

				// parametros
				String numPol = null;
				String codigoSucursal = null;
				String ramo = null;

				NodeList lista = stream.getChildNodes();
				for (int i = 0; i < lista.getLength(); i++) {
					if (lista.item(i).getNodeName().equals("PARAMETROS")) {
						Node parametros = lista.item(i);

						NodeList listaParametros = parametros.getChildNodes();

						for (int x = 0; x < listaParametros.getLength(); x++) {

							if (listaParametros.item(x).getNodeName().equals("NROPOL")) {
								numPol = listaParametros.item(x).getTextContent();
							}
							if (listaParametros.item(x).getNodeName().equals("CODSUC")) {
								codigoSucursal = listaParametros.item(x).getTextContent();
							}
							if (listaParametros.item(x).getNodeName().equals("RAMO")) {
								ramo = listaParametros.item(x).getTextContent();
							}

						}
						System.out.println("numeroPoliza:" + numPol);
					}
				}
				Integer sucursal = Integer.parseInt(codigoSucursal);
				Integer ramoParam = Integer.parseInt(ramo);
				BigDecimal numeroPoliza = new BigDecimal(numPol);
				List<ListaCoberturasRs> listaR = listaCoberturasSp.listaCoberturasSp(sucursal, ramoParam, numeroPoliza);

				respuesta.append("<STREAM>");
				respuesta.append("<HEADER><ITRANSACCION>" + numeroTransaccion + "</ITRANSACCION></HEADER>");

				respuesta.append("<COBERTURAS>");
				for (ListaCoberturasRs rs : listaR) {
					respuesta.append("<COBERTURA>");
					respuesta.append("<NROPOL>" + rs.getNumero_poliza() + "</NROPOL>");
					respuesta.append("<COBER>" + rs.getCobertura() + "</COBER>");
					respuesta.append("<MONTO>" + rs.getMonto() + "</MONTO>");
					respuesta.append("</COBERTURA>");
				}
				respuesta.append("</COBERTURAS>");
				respuesta.append("</STREAM>");
			}

			// lista cupones pendientes
			if (numeroTransaccion.equals("021")) {

				// parametros
				String secuencial = null;

				NodeList lista = stream.getChildNodes();
				for (int i = 0; i < lista.getLength(); i++) {
					if (lista.item(i).getNodeName().equals("PARAMETROS")) {
						Node parametros = lista.item(i);

						NodeList listaParametros = parametros.getChildNodes();

						for (int x = 0; x < listaParametros.getLength(); x++) {

							if (listaParametros.item(x).getNodeName().equals("SECPER")) {
								secuencial = listaParametros.item(x).getTextContent();
							}
						}
					}
				}

				Integer secuencialPersona = Integer.parseInt(secuencial);

				Long idSise = personaEquividaServicio.obtenerIdSisePersona(secuencialPersona).get(0);

				System.out.println("SISIE:  " + idSise);

				System.out.println(">>>   " + idSise);
				List<ListaCuponesPendientesRs> listaR = listaCuponesPendientesSp
						.listaCuponesPendientesSp(idSise.intValue(), 1, "1");

				respuesta.append("<STREAM>");
				respuesta.append("<HEADER><ITRANSACCION>" + numeroTransaccion + "</ITRANSACCION></HEADER>");

				respuesta.append("<CUPONES>");
				for (ListaCuponesPendientesRs rs : listaR) {
					respuesta.append("<CUPON>");
					respuesta.append("<SECPER>" + secuencial + "</SECPER>");
					respuesta.append("<NROPOL>" + rs.getNumero_poliza() + "</NROPOL>");
					respuesta.append("<CUPPEND>" + rs.getNro_cupones_pendientes() + "</CUPPEND>");
					respuesta.append("<ESTCUPON>" + rs.getEstado_ult_cupon() + "</ESTCUPON>");
					respuesta.append("<DIASMORO>" + rs.getDias_morosidad() + "</DIASMORO>");
					respuesta.append("<MONTO>" + rs.getMonto() + "</MONTO>");
					respuesta.append("<PERIODG>" + rs.getPeriodo_gracia() + "</PERIODG>");
					respuesta.append("<INSTCOND>" + rs.getInstitucion_conducto() + "</INSTCOND>");
					respuesta.append("<NROCOND>" + rs.getNro_conducto() + "</NROCOND>");
					respuesta.append("<FVIGCOND>" + rs.getFecha_vigencia_conducto() + "</FVIGCOND>");

					respuesta.append("</CUPON>");
				}
				respuesta.append("</CUPONES>");
				respuesta.append("</STREAM>");
			}

			// lista pagos
			if (numeroTransaccion.equals("022")) {

				// parametros
				String secuencial = null;

				NodeList lista = stream.getChildNodes();
				for (int i = 0; i < lista.getLength(); i++) {
					if (lista.item(i).getNodeName().equals("PARAMETROS")) {
						Node parametros = lista.item(i);

						NodeList listaParametros = parametros.getChildNodes();

						for (int x = 0; x < listaParametros.getLength(); x++) {

							if (listaParametros.item(x).getNodeName().equals("SECPER")) {
								secuencial = listaParametros.item(x).getTextContent();
							}
						}
					}
				}

				Integer secuencialPersona = Integer.parseInt(secuencial);

				Long idSise = personaEquividaServicio.obtenerIdSisePersona(secuencialPersona).get(0);

				List<ListaPagosRs> listaR = listaPagosSp.listaPagosSp(idSise.intValue(), 1, "1");

				respuesta.append("<STREAM>");
				respuesta.append("<HEADER><ITRANSACCION>" + numeroTransaccion + "</ITRANSACCION></HEADER>");

				respuesta.append("<PAGOS>");
				for (ListaPagosRs rs : listaR) {
					respuesta.append("<PAGO>");
					respuesta.append("<SECPER>" + secuencial + "</SECPER>");
					respuesta.append("<NROPOL>" + rs.getNumero_poliza() + "</NROPOL>");
					respuesta.append("<FPAGO>" + rs.getFecha_pago() + "</FPAGO>");
					respuesta.append("<VALOR>" + rs.getValor_pago() + "</VALOR>");
					respuesta.append("<NROTRAN>" + rs.getNo_transaccion() + "</NROTRAN>");
					respuesta.append("<MES>" + rs.getMes() + "</MES>");
					respuesta.append("</PAGO>");
				}
				respuesta.append("</PAGOS>");
				respuesta.append("</STREAM>");
			}

			// lista condicion aniversario
			if (numeroTransaccion.equals("023")) {

				// parametros
				String meses = null;
				String codCobertura = null;

				NodeList lista = stream.getChildNodes();
				for (int i = 0; i < lista.getLength(); i++) {
					if (lista.item(i).getNodeName().equals("PARAMETROS")) {
						Node parametros = lista.item(i);

						NodeList listaParametros = parametros.getChildNodes();

						for (int x = 0; x < listaParametros.getLength(); x++) {

							if (listaParametros.item(x).getNodeName().equals("CANTMESES")) {
								meses = listaParametros.item(x).getTextContent();
							}

							if (listaParametros.item(x).getNodeName().equals("CODCOB")) {
								codCobertura = listaParametros.item(x).getTextContent();
							}
						}
					}
				}
				Integer mesesP = Integer.parseInt(meses);
				Integer codC = Integer.parseInt(codCobertura);
				List<ListaConductoAniversarioRs> listaR = listaConductoAniversarioSp.listaConductoAniversarioSp(mesesP,
						codC);

				respuesta.append("<STREAM>");
				respuesta.append("<HEADER><ITRANSACCION>" + numeroTransaccion + "</ITRANSACCION></HEADER>");

				respuesta.append("<PERSONAS>");
				for (ListaConductoAniversarioRs rs : listaR) {
					respuesta.append("<PERSONA>");
					respuesta.append("<SECPER>"
							+ personaEquividaServicio.obtenerSecuencialPersona(rs.getSecuencial_persona().longValue())
							+ "</SECPER>");
					respuesta.append("</PERSONA>");
				}
				respuesta.append("</PERSONAS>");
				respuesta.append("</STREAM>");
			}

			// lista condicion pago
			if (numeroTransaccion.equals("024")) {

				// parametros
				String fecha = null;
				String condPago = null;

				NodeList lista = stream.getChildNodes();
				for (int i = 0; i < lista.getLength(); i++) {
					if (lista.item(i).getNodeName().equals("PARAMETROS")) {
						Node parametros = lista.item(i);

						NodeList listaParametros = parametros.getChildNodes();

						for (int x = 0; x < listaParametros.getLength(); x++) {

							if (listaParametros.item(x).getNodeName().equals("CANTMESES")) {
								fecha = listaParametros.item(x).getTextContent();
							}

							if (listaParametros.item(x).getNodeName().equals("CODCOB")) {
								condPago = listaParametros.item(x).getTextContent();
							}
						}
					}
				}
				SimpleDateFormat formato = new SimpleDateFormat("yyyyddMM");
				Date fechaP = formato.parse(fecha);
				Integer codC = Integer.parseInt(condPago);
				List<ListaConductoPagoRs> listaR = listaConductoPagoSp.listaConductoPagoSp(fecha, codC);

				respuesta.append("<STREAM>");
				respuesta.append("<HEADER><ITRANSACCION>" + numeroTransaccion + "</ITRANSACCION></HEADER>");

				respuesta.append("<PERSONAS>");
				for (ListaConductoPagoRs rs : listaR) {
					respuesta.append("<PERSONA>");
					respuesta.append("<SECPER>"
							+ personaEquividaServicio.obtenerSecuencialPersona(rs.getSecuencial_persona().longValue())
							+ "</SECPER>");
					respuesta.append("</PERSONA>");
				}
				respuesta.append("</PERSONAS>");
				respuesta.append("</STREAM>");
			}

			// lista asegurado por asesor
			if (numeroTransaccion.equals("025")) {

				// parametros
				String snAsesores = null;

				NodeList lista = stream.getChildNodes();
				for (int i = 0; i < lista.getLength(); i++) {
					if (lista.item(i).getNodeName().equals("PARAMETROS")) {
						Node parametros = lista.item(i);
						NodeList listaParametros = parametros.getChildNodes();

						for (int x = 0; x < listaParametros.getLength(); x++) {

							if (listaParametros.item(x).getNodeName().equals("ESTASESOR")) {
								snAsesores = listaParametros.item(x).getTextContent();
							}
						}
					}
				}
				Integer snAsesoresP = Integer.parseInt(snAsesores);
				List<ListaAseguradoPorAsesorRs> listaR = listaAseguradoPorAsesorSp
						.listaAseguradoPorAsesorSp(snAsesoresP);

				respuesta.append("<STREAM>");
				respuesta.append("<HEADER><ITRANSACCION>" + numeroTransaccion + "</ITRANSACCION></HEADER>");

				respuesta.append("<PERSONAS>");
				for (ListaAseguradoPorAsesorRs rs : listaR) {
					respuesta.append("<PERSONA>");
					respuesta.append("<SECPER>"
							+ personaEquividaServicio.obtenerSecuencialPersona(rs.getSecuencial_persona().longValue())
							+ "</SECPER>");
					respuesta.append("</PERSONA>");
				}
				respuesta.append("</PERSONAS>");
				respuesta.append("</STREAM>");
			}

			// lista asegurados cupones pendientes
			if (numeroTransaccion.equals("027")) {

				// sin parametros
				List<ListaAseguradoCuponPendienteRs> listaR = listaAseguradoCuponPendienteSp
						.listaAseguradoCuponPendienteSp();

				respuesta.append("<STREAM>");
				respuesta.append("<HEADER><ITRANSACCION>" + numeroTransaccion + "</ITRANSACCION></HEADER>");

				respuesta.append("<PERSONAS>");
				for (ListaAseguradoCuponPendienteRs rs : listaR) {
					respuesta.append("<PERSONA>");
					respuesta.append("<SECPER>"
							+ personaEquividaServicio.obtenerSecuencialPersona(rs.getSecuencial_persona().longValue())
							+ "</SECPER>");
					respuesta.append("</PERSONA>");
				}
				respuesta.append("</PERSONAS>");
				respuesta.append("</STREAM>");
			}

			// lista condicion pago directo
			if (numeroTransaccion.equals("028")) {

				// sin parametros
				List<ListaCondicionPagoDirectoRs> listaR = listaCondicionPagoDirectoSp.listaCondicionPagoDirectoSp();

				respuesta.append("<STREAM>");
				respuesta.append("<HEADER><ITRANSACCION>" + numeroTransaccion + "</ITRANSACCION></HEADER>");

				respuesta.append("<PERSONAS>");
				for (ListaCondicionPagoDirectoRs rs : listaR) {
					respuesta.append("<PERSONA>");
					respuesta.append("<SECPER>"
							+ personaEquividaServicio.obtenerSecuencialPersona(rs.getSecuencial_persona().longValue())
							+ "</SECPER>");
					respuesta.append("</PERSONA>");
				}
				respuesta.append("</PERSONAS>");
				respuesta.append("</STREAM>");
			}

			// lista condicion conducto
			if (numeroTransaccion.equals("029")) {

				// sin parametros
				List<ListaCondicionConductoRs> listaR = listaCondicionConductoSp.listaCondicionConductoSp();

				respuesta.append("<STREAM>");
				respuesta.append("<HEADER><ITRANSACCION>" + numeroTransaccion + "</ITRANSACCION></HEADER>");

				respuesta.append("<PERSONAS>");
				for (ListaCondicionConductoRs rs : listaR) {
					respuesta.append("<PERSONA>");
					respuesta.append("<SECPER>"
							+ personaEquividaServicio.obtenerSecuencialPersona(rs.getSecuencial_persona().longValue())
							+ "</SECPER>");
					respuesta.append("</PERSONA>");
				}
				respuesta.append("</PERSONAS>");
				respuesta.append("</STREAM>");
			}

			// lista periodo gracia
			if (numeroTransaccion.equals("030")) {

				// sin parametros
				List<ListaCondicionPeriodoGraciaRs> listaR = listaCondicionPeriodoGraciaSp
						.listaCondicionPeriodoGraciaSp();

				respuesta.append("<STREAM>");
				respuesta.append("<HEADER><ITRANSACCION>" + numeroTransaccion + "</ITRANSACCION></HEADER>");

				respuesta.append("<PERSONAS>");
				for (ListaCondicionPeriodoGraciaRs rs : listaR) {
					respuesta.append("<PERSONA>");
					respuesta.append("<SECPER>"
							+ personaEquividaServicio.obtenerSecuencialPersona(rs.getSecuencial_persona().longValue())
							+ "</SECPER>");
					respuesta.append("</PERSONA>");
				}
				respuesta.append("</PERSONAS>");
				respuesta.append("</STREAM>");
			}

			// lista tarjeta expirada
			if (numeroTransaccion.equals("031")) {

				// verifica si viene con parametros

				Integer meses = null;

				NodeList lista = stream.getChildNodes();
				for (int i = 0; i < lista.getLength(); i++) {
					if (lista.item(i).getNodeName().equals("PARAMETROS")) {
						Node parametros = lista.item(i);
						NodeList listaParametros = parametros.getChildNodes();

						for (int x = 0; x < listaParametros.getLength(); x++) {

							if (listaParametros.item(x).getNodeName().equals("CNT_MESES")) {
								meses = Integer.parseInt(listaParametros.item(x).getTextContent());
							}
						}
					}
				}

				List<ListaCondicionTarjetaExpiradaRs> listaR = null;

				// si no envia parametro meses llama al sp sin parametrosy devuelve el
				// secuencial del depersona
				if (meses == null) {
					listaR = listaCondicionTarjetaExpiradaSp.listaCondicionTarjetaExpiradaSp();
					respuesta.append("<STREAM>");
					respuesta.append("<HEADER><ITRANSACCION>" + numeroTransaccion + "</ITRANSACCION></HEADER>");

					respuesta.append("<PERSONAS>");
					for (ListaCondicionTarjetaExpiradaRs rs : listaR) {
						respuesta.append("<PERSONA>");
						respuesta.append("<SECPER>" + personaEquividaServicio
								.obtenerSecuencialPersona(rs.getSecuencial_persona().longValue()) + "</SECPER>");
						respuesta.append("</PERSONA>");
					}
					respuesta.append("</PERSONAS>");
					respuesta.append("</STREAM>");
				} else {

					Calendar hoy = Calendar.getInstance();
					// se envia mes y anio actual
					listaR = listaCondicionTarjetaExpiradaSp.listaCondicionTarjetaExpiradaSp(meses,
							(hoy.get(Calendar.MONTH) + 1), hoy.get(Calendar.YEAR));

					respuesta.append("<STREAM>");
					respuesta.append("<HEADER><ITRANSACCION>" + numeroTransaccion + "</ITRANSACCION></HEADER>");

					respuesta.append("<PERSONAS>");
					for (ListaCondicionTarjetaExpiradaRs rs : listaR) {
						respuesta.append("<PERSONA>");
						respuesta.append("<SECPER>" + rs.getSecuencial_persona().longValue() + "</SECPER>");
						respuesta.append("<TPODOC>" + conversionTipoDocumentoInteger(rs.getTipo()) + "</TPODOC>");
						respuesta.append("<NRODOC>" + rs.getNumeroDocumento() + "</NRODOC>");
						respuesta.append("</PERSONA>");
					}
					respuesta.append("</PERSONAS>");
					respuesta.append("<ERROR>");
					respuesta.append("<CODIGO>0</CODIGO>");
					respuesta.append("<MENSAJE />");
					respuesta.append("</ERROR>");
					respuesta.append("</STREAM>");
				}
			}

			// lista tarjeta expirada con parametros
			if (numeroTransaccion.equals("032")) {
				// sin parametros
				List<ListaCondicionConductoRs> listaR = listaCondicionConductoBcoEqSp.listaCondicionConductoBcoEqSp();

				respuesta.append("<STREAM>");
				respuesta.append("<HEADER><ITRANSACCION>" + numeroTransaccion + "</ITRANSACCION></HEADER>");

				respuesta.append("<PERSONAS>");
				for (ListaCondicionConductoRs rs : listaR) {
					respuesta.append("<PERSONA>");
					respuesta.append("<SECPER>" + rs.getSecuencial_persona().longValue() + "</SECPER>");
					respuesta.append("<TPODOC>" + conversionTipoDocumentoInteger(rs.getTipo()) + "</TPODOC>");
					respuesta.append("<NRODOC>" + rs.getNumeroDocumento() + "</NRODOC>");
					respuesta.append("</PERSONA>");
				}
				respuesta.append("</PERSONAS>");
				respuesta.append("<ERROR>");
				respuesta.append("<CODIGO>0</CODIGO>");
				respuesta.append("<MENSAJE />");
				respuesta.append("</ERROR>");
				respuesta.append("</STREAM>");
			}

			// lista tarjeta expirada con parametros
			if (numeroTransaccion.equals("033")) {

				// sin parametros
				List<ListaCondicionPagoDirectoRs> listaR = listaCondicionPagoDirectoSp.listaCondicionPagoDirectoSp();

				respuesta.append("<STREAM>");
				respuesta.append("<HEADER><ITRANSACCION>" + numeroTransaccion + "</ITRANSACCION></HEADER>");

				respuesta.append("<PERSONAS>");
				for (ListaCondicionPagoDirectoRs rs : listaR) {
					respuesta.append("<PERSONA>");
					respuesta.append("<SECPER>" + rs.getSecuencial_persona().longValue() + "</SECPER>");
					respuesta.append("<TPODOC>" + conversionTipoDocumentoInteger(rs.getTipo()) + "</TPODOC>");
					respuesta.append("<NRODOC>" + rs.getNumeroDocumento() + "</NRODOC>");
					respuesta.append("</PERSONA>");
				}
				respuesta.append("</PERSONAS>");
				respuesta.append("<ERROR>");
				respuesta.append("<CODIGO>0</CODIGO>");
				respuesta.append("<MENSAJE />");
				respuesta.append("</ERROR>");
				respuesta.append("</STREAM>");

			}

			// lista datos compania
			if (numeroTransaccion.equals("013")) {

				// parametros
				String secuencial = null;

				NodeList lista = stream.getChildNodes();
				for (int i = 0; i < lista.getLength(); i++) {
					if (lista.item(i).getNodeName().equals("PARAMETROS")) {
						Node parametros = lista.item(i);
						NodeList listaParametros = parametros.getChildNodes();
						for (int x = 0; x < listaParametros.getLength(); x++) {
							if (listaParametros.item(x).getNodeName().equals("SECPER")) {
								secuencial = listaParametros.item(x).getTextContent();
							}
						}
					}
				}

				Integer secuencialPersona = Integer.parseInt(secuencial);
				Long idSise = personaEquividaServicio.obtenerIdSisePersona(secuencialPersona).get(0);
				List<ListaDatosCompaniaRs> listaR = listaDatosCompaniaSp.listaDatosCompaniaSp(secuencialPersona);

				respuesta.append("<STREAM>");
				respuesta.append("<HEADER><ITRANSACCION>" + numeroTransaccion + "</ITRANSACCION></HEADER>");

				respuesta.append("<CIAS>");
				for (ListaDatosCompaniaRs rs : listaR) {
					respuesta.append("<CIA>");
					respuesta.append("<TPODOC>" + rs.getTipoDocumento() + "</TPODOC>");
					respuesta.append("<NRODOC>" + rs.getNumeroPolizas() + "</NRODOC>");
					respuesta.append("<SECEMP>" + rs.getSecuencialEmpresa() + "</SECEMP>");
					respuesta.append("<RSOCIAL>" + rs.getRazonSocial() + "</RSOCIAL>");
					respuesta.append("<RCOMER>" + rs.getRazonComercial() + "</RCOMER>");
					respuesta.append("<SEGM>" + rs.getSegmento() + "</SEGM>");
					respuesta.append("<PRIMA>" + rs.getPrimaPromedioAnual() + "</PRIMA>");
					respuesta.append("<SINACUM>" + rs.getSiniestralidadAcumulada() + "</SINACUM>");
					respuesta.append("<ESTCZA>" + rs.getEstadoCobranza() + "</<ESTCZA>");
					respuesta.append("<EMPCLI>" + rs.getTipoCliente() + "</EMPCLI>");
					respuesta.append("<EMPBKR>" + rs.getTipoBroker() + "</EMPBKR>");
					// respuesta.append("<PEP>" + rs.getd + "</PEP>");
					respuesta.append("<CANTPOL>" + rs.getNumeroPolizas() + "</CANTPOL>");
					respuesta.append("</CIA>");
				}
				respuesta.append("</CIAS>");
				respuesta.append("</STREAM>");
			}

		} catch (CrmException e) {
			e.printStackTrace();
			respuesta.append("<STREAM><ERROR><CODIGO>1</CODIGO><MENSAJE>" + e.getLocalizedMessage()
					+ "</MENSAJE></ERROR></STREAM>");
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			respuesta.append("<STREAM><ERROR><CODIGO>1</CODIGO><MENSAJE>" + e.getLocalizedMessage()
					+ "</MENSAJE></ERROR></STREAM>");
		} catch (SAXException e) {
			e.printStackTrace();
			respuesta.append("<STREAM><ERROR><CODIGO>1</CODIGO><MENSAJE>" + e.getLocalizedMessage()
					+ "</MENSAJE></ERROR></STREAM>");
		} catch (IOException e) {
			e.printStackTrace();
			respuesta.append("<STREAM><ERROR><CODIGO>1</CODIGO><MENSAJE>" + e.getLocalizedMessage()
					+ "</MENSAJE></ERROR></STREAM>");
		} catch (SQLException e) {
			e.printStackTrace();
			respuesta.append("<STREAM><ERROR><CODIGO>1</CODIGO><MENSAJE>" + e.getLocalizedMessage()
					+ "</MENSAJE></ERROR></STREAM>");
		} catch (ParseException e) {
			e.printStackTrace();
			respuesta.append("<STREAM><ERROR><CODIGO>1</CODIGO><MENSAJE>" + e.getLocalizedMessage()
					+ "</MENSAJE></ERROR></STREAM>");
		}

		return respuesta.toString();
	}

	private void consultaPersonas(StringBuilder respuesta, Node stream, String numeroTransaccion)
			throws SQLException, CrmException {
		// parametros (TIPOB,SECUENCIAL,DOCUMENTO)
		String secuencial = null;
		String documento = null;

		NodeList lista = stream.getChildNodes();
		for (int i = 0; i < lista.getLength(); i++) {
			if (lista.item(i).getNodeName().equals("PARAMETROS")) {
				Node parametros = lista.item(i);

				NodeList listaParametros = parametros.getChildNodes();

				for (int x = 0; x < listaParametros.getLength(); x++) {

					if (listaParametros.item(x).getNodeName().equals("SECPER")) {
						secuencial = listaParametros.item(x).getTextContent();
					}
					if (listaParametros.item(x).getNodeName().equals("NRODOC")) {
						documento = listaParametros.item(x).getTextContent();
					}
				}
				System.out.println("secuencial:" + secuencial);
				System.out.println("documento:" + documento);
			}
		}

		if (secuencial == null || secuencial.trim().equals("") || secuencial.trim().equals("null")) {
			List<String> cedulas = new ArrayList<String>();
			cedulas.add(documento);
			Map<String, Integer> mapa = personaServicio.obtenerPersonaByDocumento(cedulas);
			if (mapa.size() > 0) {
				secuencial = mapa.get(documento) + "";
			}
		}

		if (secuencial != null) {
			Long secPersona = Long.parseLong(secuencial);
			PersonaCrmDto personaCrmDto = personaServicio.obtenerPersonaCrm(secPersona);

			System.out.println("personaCrmDto:" + personaCrmDto);
			System.out.println("identificacion:" + personaCrmDto.getIdentificacion());

			System.out.println("personaEquividaServicio:" + personaEquividaServicio);

			List<Long> idsSise = personaEquividaServicio.obtenerIdSisePersona(secPersona.intValue());

			System.out.println("idsSise total:" + idsSise.size());

			List<ListaDatosPersonaRs> personaSiseLista = new ArrayList<ListaDatosPersonaRs>();

			for (Long id : idsSise) {
				System.out.println("id" + id);
				ListaDatosPersonaRs persona = listaDatosPersonaSp.listaDatosPersonaSp(id.intValue(), documento);
				if (persona == null) {
					throw new CrmException("ERROR: No existe el id " + id + " en el sise!");
				}
				personaSiseLista.add(persona);
			}

			respuesta.append("<STREAM>");
			respuesta.append("<HEADER><ITRANSACCION>" + numeroTransaccion + "</ITRANSACCION></HEADER>");
			respuesta.append("<PERSONAS>");
			respuesta.append("<PERS>");

			System.out.println("personaCrmDto.getCodTipoIdentificacion():" + personaCrmDto.getCodTipoIdentificacion());

			String tipoDocumento = conversionTipoDocumentoInteger(personaCrmDto.getSecTipoIdentificacion().intValue());

			respuesta.append("<TPODOC>" + tipoDocumento + "</TPODOC>");
			respuesta.append("<NRODOC>" + personaCrmDto.getIdentificacion() + "</NRODOC>");
			respuesta.append("<SECPER>" + personaCrmDto.getSecuencialPersona() + "</SECPER>");
			respuesta.append("<NOM1>" + personaCrmDto.getPrimerNombre() + "</NOM1>");
			respuesta.append("<NOM2>" + personaCrmDto.getSegundoNombre() + "</NOM2>");
			respuesta.append("<APEPAT>" + personaCrmDto.getApellidoPaterno() + "</APEPAT>");
			respuesta.append("<APEMAT>" + personaCrmDto.getApellidoMaterno() + "</APEMAT>");
			respuesta.append("<FNAC>" + personaCrmDto.getFechaNacimiento() + "</FNAC>");
			respuesta.append("<ECIVIL>" + personaCrmDto.getEstadoCivil() + "</ECIVIL>");
			respuesta.append("<GEN>" + personaCrmDto.getSexo() + "</GEN>");
			respuesta.append("<CODPROF>" + personaCrmDto.getSecuencialProfesion() + "</CODPROF>");
			respuesta.append("<NEDUC>" + personaCrmDto.getNivelEducacion() + "</NEDUC>");
			respuesta.append("<CODOCUP>" + personaCrmDto.getCodOcupacion() + "</CODOCUP>");

			// TODO verrificar
			respuesta.append("<CYGNOM1>" + personaCrmDto.getConyugeNombre1() + "</CYGNOM1>");
			respuesta.append("<CYGNOM2>" + personaCrmDto.getConyugeNombre2() + "</CYGNOM2>");
			respuesta.append("<CYGAPEPAT>" + personaCrmDto.getConyugeApePat() + "</CYGAPEPAT>");
			respuesta.append("<CYGAPEMAT>" + personaCrmDto.getConyugeApeMat() + "</CYGAPEMAT>");

			respuesta.append("<NROHJOS>" + personaCrmDto.getNumHijos() + "</NROHJOS>");
			respuesta.append("<HRINICTO>" + personaCrmDto.getHoraInicioContacto() + "</HRINICTO>");
			respuesta.append("<HRFINCTO>" + personaCrmDto.getHoraFinContacto() + "</HRFINCTO>");

			respuesta.append("<RELLAB>" + personaCrmDto.getRelacionLaboral() + "</RELLAB>");
			respuesta.append("<EMPRTRAB>" + personaCrmDto.getEmpresaTrabaja() + "</EMPRTRAB>");
			respuesta.append("<CARGO>" + personaCrmDto.getCargo() + "</CARGO>");

			respuesta.append("<MAIL1>" + personaCrmDto.getEmail() + "</MAIL1>");
			respuesta.append("<TPOMAIL>" + personaCrmDto.getTipoEmail() + "</TPOMAIL>");

			respuesta.append("<TPODIR>" + personaCrmDto.getTipoDireccion() + "</TPODIR>");

			respuesta.append("<CODPAIS>" + personaCrmDto.getCodPais() + "</CODPAIS>");
			respuesta.append("<REGION>" + personaCrmDto.getRegion() + "</REGION>");
			respuesta.append("<CODPROV>" + personaCrmDto.getCodProv() + "</CODPROV>");

			respuesta.append("<CODCANTON>" + personaCrmDto.getCodCanton() + "</CODCANTON>");
			respuesta.append("<CIUDAD>" + personaCrmDto.getCiudad() + "</CIUDAD>");
			respuesta.append("<CODPARR>" + personaCrmDto.getCodParr() + "</CODPARR>");
			respuesta.append("<BARRIO>" + personaCrmDto.getBarrio() + "</BARRIO>");
			respuesta.append("<DIRCALLE1>" + personaCrmDto.getDirCalle1() + "</DIRCALLE1>");
			respuesta.append("<DIRNRO>" + personaCrmDto.getDirNumero() + "</DIRNRO>");
			respuesta.append("<DIRCALLE2>" + personaCrmDto.getDirCalle2() + "</DIRCALLE2>");
			respuesta.append("<DIREDIF>" + personaCrmDto.getDirEdif() + "</DIREDIF>");
			respuesta.append("<LONG>" + personaCrmDto.getLongitud() + "</LONG>");
			respuesta.append("<LAT>" + personaCrmDto.getLatitud() + "</LAT>");
			respuesta.append("<REFER>" + personaCrmDto.getReferencia() + "</REFER>");

			respuesta.append("<TPOTEL>" + personaCrmDto.getTipoTelefono() + "</TPOTEL>");
			respuesta.append("<CODTELPAIS>" + personaCrmDto.getCodTelPais() + "</CODTELPAIS>");
			respuesta.append("<CODAREA>" + personaCrmDto.getCodArea() + "</CODAREA>");
			respuesta.append("<TEL>" + personaCrmDto.getTel() + "</TEL>");
			respuesta.append("<TELEXT>" + personaCrmDto.getTelExt() + "</TELEXT>");

			respuesta.append("<PCNOM>" + personaCrmDto.getContacto() + "</PCNOM>");
			respuesta.append("<PCTEL>" + "" + "</PCTEL>");
			respuesta.append("<PCEMAIL>" + personaCrmDto.getEmailContacto() + "</PCEMAIL>");
			respuesta.append("<INGR>" + personaCrmDto.getMontoIngresoMensual() + "</INGR>");

			// datos SISE

			// TODO preguntar estos dos
			Integer secBroker = null;
			Integer secPerBroker = null;
			String canalVentas = "";
			String asesorComercial = "";
			String asesorPostVenta = "";
			String director = "";
			String asistente = "";
			String segmentoCliente = "";
			String informacionSiniestros = "No";
			String nivelRiesgo = "No";
			List<String> beneficiosExternosLista = new ArrayList<String>();
			String beneficiosExternos = "";
			List<String> clienteDesdeLista = new ArrayList<String>();
			String clienteDesde = "";

			String clienteIndividual = "False";
			String aseguradoEmpresaCliente = "False";
			String contactoEmpresa = "False";
			String contactoBroker = "False";
			String inactivo = "False";
			// TODO
			BigDecimal valorCliente = new BigDecimal(0);
			// TODO
			BigDecimal capacidadPago = new BigDecimal(0);

			for (ListaDatosPersonaRs persona : personaSiseLista) {
				System.out.println("persona:" + persona);
				System.out.println("persona.getBroker:" + persona.getBroker());
				if (secBroker == null) {
					secBroker = persona.getBroker();
				}
				if (secPerBroker == null) {
					// TODO
					secPerBroker = persona.getBroker();
				}
				if (canalVentas == null) {
					canalVentas = persona.getCanal_Venta();
				}
				if (asesorComercial == null) {
					asesorComercial = persona.getAsesor_Comercial();
				}
				if (asesorPostVenta == null) {
					asesorPostVenta = persona.getAsesor_Post_Venta();
				}
				if (director == null) {
					director = persona.getDirector();
				}
				if (asistente == null) {
					asistente = persona.getAsistente();
				}
				if (segmentoCliente == null) {
					segmentoCliente = persona.getSegmento_Cliente();
				}
				if (persona.getInformacion_Siniestros().toLowerCase().startsWith("s")) {
					informacionSiniestros = "Si";
				}
				if (persona.getNivel_Riesgo_Cliente().toLowerCase().startsWith("s")) {
					nivelRiesgo = "Si";
				}
				if (!persona.getBeneficios_Externos().equals("")) {
					beneficiosExternosLista.add(persona.getBeneficios_Externos());
				}
				clienteDesdeLista.add(persona.getCliente_Desde());

				if (persona.getCliente_individual().toLowerCase().startsWith("s")) {
					clienteIndividual = "True";
				}

				if (persona.getAsegurado_de_empresa_cliente().toLowerCase().startsWith("s")) {
					aseguradoEmpresaCliente = "True";
				}

				if (persona.getContacto_empresa().toLowerCase().startsWith("s")) {
					contactoEmpresa = "True";
				}
				if (persona.getContacto_Broker_2().toLowerCase().startsWith("s")) {
					contactoBroker = "True";
				}
				if (persona.getInactivo().toLowerCase().startsWith("s")) {
					inactivo = "True";
				}
			}

			// beneficiosExternosLista.s
			if (beneficiosExternosLista.size() > 0) {
				Collections.sort(beneficiosExternosLista);
				beneficiosExternos = beneficiosExternosLista.get(0);// el
																	// primero
																	// (a,b,c...)
			}
			if (clienteDesdeLista.size() > 0) {
				Collections.sort(clienteDesdeLista);
				clienteDesde = clienteDesdeLista.get(0);// mas antiguo
			}

			respuesta.append("<SECBRK>" + secBroker + "</SECBRK>");
			respuesta.append("<SECPERBRK>" + secPerBroker + "</SECPERBRK>");
			respuesta.append("<CANALVTA>" + canalVentas + "</CANALVTA>");
			respuesta.append("<CODASECOM>" + asesorComercial + "</CODASECOM>");
			respuesta.append("<CODASEPOST>" + asesorPostVenta + "</CODASEPOST>");
			respuesta.append("<CODDCTOR>" + director + "</CODDCTOR>");
			respuesta.append("<CODASIST>" + asistente + "</CODASIST>");
			respuesta.append("<SEGM>" + segmentoCliente + "</SEGM>");

			// TODO
			respuesta.append("<PEP>" + "" + "</PEP>");

			respuesta.append("<INFOSIN>" + informacionSiniestros + "</INFOSIN>");
			respuesta.append("<NVLRIESGO>" + nivelRiesgo + "</NVLRIESGO>");

			respuesta.append("<BENEXT>" + beneficiosExternos + "</BENEXT>");
			respuesta.append("<CLIDESDE>" + clienteDesde + "</CLIDESDE>");
			// TODO
			respuesta.append("<COMENT>" + "" + "</COMENT>");
			// TODO
			respuesta.append("<RESERV>" + segmentoCliente + "</RESERV>");

			respuesta.append("<CLIIND>" + clienteIndividual + "</CLIIND>");
			respuesta.append("<CLIASEG>" + aseguradoEmpresaCliente + "</CLIASEG>");

			respuesta.append("<CTOEMP>" + contactoEmpresa + "</CTOEMP>");
			respuesta.append("<CTOBKR>" + contactoBroker + "</CTOBKR>");
			respuesta.append("<INACT>" + inactivo + "</INACT>");

			respuesta.append("<PNVALOR>" + valorCliente.doubleValue() + "</PNVALOR>");
			respuesta.append("<PNCAPAC>" + capacidadPago.doubleValue() + "</PNCAPAC>");

			respuesta.append("</PERS>");
			respuesta.append("</PERSONAS>");

			anadirNoError(respuesta);

			respuesta.append("</STREAM>");

		}

	}

	private void anadirNoError(StringBuilder respuesta) {
		respuesta.append("<ERROR><CODIGO>0</CODIGO><MENSAJE /></ERROR>");
	}

	public static void main(String[] args) {

		StringBuilder respuesta = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");

		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" + "<STREAM>" + "<HEADER>"
				+ "<ITRANSACCION>019</ITRANSACCION>" + "</HEADER>" + "<PARAMETROS>" + "<NROPOL>2</NROPOL>"
				+ "</PARAMETROS>" + "</STREAM>";

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		dbf.setNamespaceAware(false);
		dbf.setIgnoringComments(false);
		dbf.setIgnoringElementContentWhitespace(true);
		dbf.setExpandEntityReferences(false);
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(new InputSource(new StringReader(xml)));

			Node stream = doc.getFirstChild();
			Node header = stream.getFirstChild();
			Node itransaccion = header.getFirstChild();
			String numeroTransaccion = itransaccion.getTextContent();

			respuesta.append("<STREAM>");
			respuesta.append("<HEADER><ITRANSACCION>" + numeroTransaccion + "</ITRANSACCION></HEADER>");

			if (numeroTransaccion.equals("19")) {

			}

			// beneficiarios
			if (numeroTransaccion.equals("019")) {
				// parametros
				String nroPol = null;

				NodeList lista = stream.getChildNodes();
				for (int i = 0; i < lista.getLength(); i++) {
					if (lista.item(i).getNodeName().equals("PARAMETROS")) {
						Node parametros = lista.item(i);

						NodeList listaParametros = parametros.getChildNodes();

						for (int x = 0; x < listaParametros.getLength(); x++) {

							if (listaParametros.item(x).getNodeName().equals("NROPOL")) {

								nroPol = listaParametros.item(x).getTextContent();

							}
						}
						System.out.println("nroPol:" + nroPol);
					}
				}
				// Node parametros
			}
			respuesta.append("</STREAM>");

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public String ConsultarPolizas(String xml) {
		// System.out.println("ConsultarPolizas XML:" + xml);
		StringBuilder respuesta = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		dbf.setNamespaceAware(true);
		dbf.setIgnoringElementContentWhitespace(true);
		dbf.setIgnoringComments(true);
		dbf.setExpandEntityReferences(false);
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(new InputSource(new StringReader(xml)));

			Node stream = doc.getFirstChild();
			Node header = stream.getFirstChild();
			Node itransaccion = header.getFirstChild();
			String numeroTransaccion = itransaccion.getTextContent();

			// parametros
			String secuencial = null;
			String tipo = null;
			String nroDocumento = null;

			NodeList lista = stream.getChildNodes();
			for (int i = 0; i < lista.getLength(); i++) {
				if (lista.item(i).getNodeName().equals("PARAMETROS")) {
					Node parametros = lista.item(i);

					NodeList listaParametros = parametros.getChildNodes();

					for (int x = 0; x < listaParametros.getLength(); x++) {

						if (listaParametros.item(x).getNodeName().equals("SECPER")) {
							secuencial = listaParametros.item(x).getTextContent();
						}
						if (listaParametros.item(x).getNodeName().equals("TPODOC")) {
							tipo = listaParametros.item(x).getTextContent();
						}
						if (listaParametros.item(x).getNodeName().equals("NRODOC")) {
							nroDocumento = listaParametros.item(x).getTextContent();
						}
					}
				}
			}

			Integer secuencialPersona = null;
			if (secuencial != null && !secuencial.isEmpty()) {
				secuencialPersona = Integer.parseInt(secuencial);
			}
			Integer tipoInt = null;
			if (tipo != null && !tipo.isEmpty()) {
				tipoInt = conversionTipoDocumentoString(tipo);
			}

			respuesta.append("<STREAM>");
			respuesta.append("<HEADER><ITRANSACCION>" + numeroTransaccion + "</ITRANSACCION></HEADER>");

			respuesta.append("<POLIZAS>");
			List<Long> listaIdSise;
			if (secuencialPersona == null) {
				List<ListaPolizasRs> listaR = listaPolizasSp.listaPolizasSp(null, tipoInt, nroDocumento);
				respuesta.append(cargarPolizaXML(listaR));
			} else {
				listaIdSise = personaEquividaServicio.obtenerIdSisePersona(secuencialPersona);
				for (Long long1 : listaIdSise) {
					List<ListaPolizasRs> listaR = listaPolizasSp.listaPolizasSp(long1.intValue(), tipoInt,
							nroDocumento);
					respuesta.append(cargarPolizaXML(listaR));
				}
			}

			respuesta.append("</POLIZAS>");
			anadirNoError(respuesta);
			respuesta.append("</STREAM>");

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			respuesta.append("<STREAM><ERROR><CODIGO>1</CODIGO><MENSAJE>" + e.getLocalizedMessage()
					+ "</MENSAJE></ERROR></STREAM>");
		} catch (SAXException e) {
			e.printStackTrace();
			respuesta.append("<STREAM><ERROR><CODIGO>1</CODIGO><MENSAJE>" + e.getLocalizedMessage()
					+ "</MENSAJE></ERROR></STREAM>");
		} catch (IOException e) {
			e.printStackTrace();
			respuesta.append("<STREAM><ERROR><CODIGO>1</CODIGO><MENSAJE>" + e.getLocalizedMessage()
					+ "</MENSAJE></ERROR></STREAM>");
		} catch (SQLException e) {
			e.printStackTrace();
			respuesta.append("<STREAM><ERROR><CODIGO>1</CODIGO><MENSAJE>" + e.getLocalizedMessage()
					+ "</MENSAJE></ERROR></STREAM>");
		}

		return respuesta.toString();
	}

	private StringBuilder cargarPolizaXML(List<ListaPolizasRs> listaR) {
		StringBuilder respuesta = new StringBuilder("");
		for (ListaPolizasRs rs : listaR) {
			respuesta.append("<POLIZA>");
			respuesta.append("<NROPOL>" + rs.getNumero_Poliza() + "</NROPOL>");
			respuesta.append("<PORCPRIMA>" + rs.getPorc_Extraprima() + "</PORCPRIMA>");
			respuesta.append("<CONTRAT>" + rs.getContratante() + "</CONTRAT>");
			respuesta.append("<SUCUR>" + rs.getSucursal() + "</SUCUR>");
			respuesta.append("<RAMO>" + rs.getRamo() + "</RAMO>");
			String estEntrega = "";
			if (rs.getEstado_entrega_poliza() != null) {
				estEntrega = rs.getEstado_entrega_poliza().toString();
			}
			respuesta.append("<ESTENTREGA>" + estEntrega + "</ESTENTREGA>");
			respuesta.append("<EST>" + rs.getEstado_poliza() + "</EST>");
			respuesta.append("<PRIMA>" + rs.getPrima_mensual() + "</PRIMA>");
			respuesta.append("<PERIODPAGO>" + rs.getPeriodicidad_pago() + "</PERIODPAGO>");
			respuesta.append("<FINIVIG>" + rs.getFecha_inicio_vigencia() + "</FINIVIG>");
			respuesta.append("<ANTIG>" + rs.getAntiguedad_poliza() + "</ANTIG>");
			String estCuenta = "";
			if (rs.getEstado_Cuenta() != null) {
				estCuenta = rs.getEstado_Cuenta().toString();
			}

			respuesta.append("<ESTCTA>" + estCuenta + "</ESTCTA>");
			respuesta.append("<TIENEDEU>" + rs.getTiene_deuda() + "</TIENEDEU>");
			respuesta.append("<MONTODEU>" + rs.getMonto_Deuda() + "</MONTODEU>");
			respuesta.append("<FORMAPAGO>" + rs.getForma_Pago() + "</FORMAPAGO>");
			respuesta.append("<FCADUC>" + rs.getFecha_A_Caducar() + "</FCADUC>");
			respuesta.append("<PERIODG>" + rs.getPeriodo_Gracia() + "</PERIODG>");
			respuesta.append("<TPOCOND>" + rs.getTipo_Conducto() + "</TPOCOND>");
			respuesta.append("<NROCOND>" + rs.getNro_Conducto() + "</NROCOND>");
			respuesta.append("<ESTCOND>" + rs.getEstado_Conducto() + "</ESTCOND>");
			respuesta.append("<INSTCOND>" + rs.getInstitucion_Conducto() + "</INSTCOND>");
			respuesta.append("<FVIGCOND>" + rs.getFecha_Vigencia_Conducto() + "</FVIGCOND>");
			respuesta.append("<CUPPEND>" + rs.getNro_Cupones_Pendientes() + "</CUPPEND>");

			respuesta.append("</POLIZA>");
		}
		return respuesta;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public String ConsultarBeneficiariosPoliza(String xml) {
		StringBuilder respuesta = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");

		String tagVacio = "<BENEFICIARIO></BENEFICIARIO>";
		String numeroTransaccion = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		dbf.setNamespaceAware(true);
		dbf.setIgnoringElementContentWhitespace(true);
		dbf.setIgnoringComments(true);
		dbf.setExpandEntityReferences(false);
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(new InputSource(new StringReader(xml)));

			Node stream = doc.getFirstChild();
			Node header = stream.getFirstChild();
			Node itransaccion = header.getFirstChild();
			numeroTransaccion = itransaccion.getTextContent();

			// parametros
			String numPol = null;
			String codigoSucursal = null;
			String ramo = null;

			NodeList lista = stream.getChildNodes();
			for (int i = 0; i < lista.getLength(); i++) {
				if (lista.item(i).getNodeName().equals("PARAMETROS")) {
					Node parametros = lista.item(i);
					NodeList listaParametros = parametros.getChildNodes();

					for (int x = 0; x < listaParametros.getLength(); x++) {
						if (listaParametros.item(x).getNodeName().equals("NROPOL")) {
							numPol = listaParametros.item(x).getTextContent();
						}
						if (listaParametros.item(x).getNodeName().equals("SUCUR")) {
							codigoSucursal = listaParametros.item(x).getTextContent();
						}
						if (listaParametros.item(x).getNodeName().equals("RAMO")) {
							ramo = listaParametros.item(x).getTextContent();
						}

					}
				}
			}

			Long numeroPoliza = Long.parseLong(numPol);
			Integer sucursal = Integer.parseInt(codigoSucursal);
			Integer ramoParam = Integer.parseInt(ramo);

			List<ListaBeneficiariosRs> listaR = listaBeneficiariosSp.listaBeneficiariosSp(sucursal, ramoParam,
					numeroPoliza);

			respuesta.append("<STREAM>");
			respuesta.append("<HEADER><ITRANSACCION>" + numeroTransaccion + "</ITRANSACCION></HEADER>");

			respuesta.append("<BENEFICIARIOS>");
			for (ListaBeneficiariosRs rs : listaR) {
				respuesta.append("<BENEFICIARIO>");
				respuesta.append("<NROPOL>" + rs.getNumero_Poliza() + "</NROPOL>");
				respuesta.append("<TPO>" + rs.getTipo() + "</TPO>");
				respuesta.append("<NOMB1>" + rs.getPrimer_Nombre() + "</NOMB1>");
				respuesta.append("<NOMB2>" + rs.getSegundo_Nombre() + "</NOMB2>");
				respuesta.append("<APEPAT>" + rs.getApellido_Paterno() + "</APEPAT>");
				respuesta.append("<APEMAT>" + rs.getApellido_Materno() + "</APEMAT>");
				respuesta.append("<PAREN>" + rs.getParentezco() + "</PAREN>");
				respuesta.append("<PORCEN>" + rs.getPorcentaje() + "</PORCEN>");
				respuesta.append("<RELASEG>" + rs.getRelacion_con_contratante() + "</RELASEG>");
				respuesta.append("</BENEFICIARIO>");
			}
			respuesta.append("</BENEFICIARIOS>");
			anadirNoError(respuesta);
			respuesta.append("</STREAM>");
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			respuesta = construirXmlError(e.getLocalizedMessage(), numeroTransaccion, tagVacio);
		} catch (SAXException e) {
			e.printStackTrace();
			respuesta.append("<STREAM><ERROR><CODIGO>1</CODIGO><MENSAJE>" + e.getLocalizedMessage()
					+ "</MENSAJE></ERROR></STREAM>");
		} catch (IOException e) {
			e.printStackTrace();
			respuesta.append("<STREAM><ERROR><CODIGO>1</CODIGO><MENSAJE>" + e.getLocalizedMessage()
					+ "</MENSAJE></ERROR></STREAM>");
		} catch (SQLException e) {
			e.printStackTrace();
			respuesta.append("<STREAM><ERROR><CODIGO>1</CODIGO><MENSAJE>" + e.getLocalizedMessage()
					+ "</MENSAJE></ERROR></STREAM>");
		}

		return respuesta.toString();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public String ConsultarCoberturasPoliza(String xml) {
		StringBuilder respuesta = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");

		String tagVacio = "<COBERTURA></COBERTURA>";
		String numeroTransaccion = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		dbf.setNamespaceAware(true);
		dbf.setIgnoringElementContentWhitespace(true);
		dbf.setIgnoringComments(true);
		dbf.setExpandEntityReferences(false);
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(new InputSource(new StringReader(xml)));

			Node stream = doc.getFirstChild();
			Node header = stream.getFirstChild();
			Node itransaccion = header.getFirstChild();
			numeroTransaccion = itransaccion.getTextContent();

			// parametros
			String numPol = null;
			String codigoSucursal = null;
			String ramo = null;

			NodeList lista = stream.getChildNodes();
			for (int i = 0; i < lista.getLength(); i++) {
				if (lista.item(i).getNodeName().equals("PARAMETROS")) {
					Node parametros = lista.item(i);

					NodeList listaParametros = parametros.getChildNodes();

					for (int x = 0; x < listaParametros.getLength(); x++) {

						if (listaParametros.item(x).getNodeName().equals("NROPOL")) {
							numPol = listaParametros.item(x).getTextContent();
						}
						if (listaParametros.item(x).getNodeName().equals("SUCUR")) {
							codigoSucursal = listaParametros.item(x).getTextContent();
						}
						if (listaParametros.item(x).getNodeName().equals("RAMO")) {
							ramo = listaParametros.item(x).getTextContent();
						}

					}
					System.out.println("numeroPoliza:" + numPol);
				}
			}
			Integer sucursal = Integer.parseInt(codigoSucursal);
			Integer ramoParam = Integer.parseInt(ramo);
			BigDecimal numeroPoliza = new BigDecimal(numPol);
			List<ListaCoberturasRs> listaR = listaCoberturasSp.listaCoberturasSp(sucursal, ramoParam, numeroPoliza);

			respuesta.append("<STREAM>");
			respuesta.append("<HEADER><ITRANSACCION>" + numeroTransaccion + "</ITRANSACCION></HEADER>");

			respuesta.append("<COBERTURAS>");
			for (ListaCoberturasRs rs : listaR) {
				respuesta.append("<COBERTURA>");
				respuesta.append("<NROPOL>" + rs.getNumero_poliza() + "</NROPOL>");
				respuesta.append("<COBER>" + rs.getCobertura() + "</COBER>");
				respuesta.append("<MONTO>" + rs.getMonto() + "</MONTO>");
				respuesta.append("</COBERTURA>");
			}
			respuesta.append("</COBERTURAS>");
			anadirNoError(respuesta);
			respuesta.append("</STREAM>");

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			respuesta = construirXmlError(e.getLocalizedMessage(), numeroTransaccion, tagVacio);
		} catch (SAXException e) {
			e.printStackTrace();
			respuesta = construirXmlError(e.getLocalizedMessage(), numeroTransaccion, tagVacio);
		} catch (IOException e) {
			e.printStackTrace();
			respuesta = construirXmlError(e.getLocalizedMessage(), numeroTransaccion, tagVacio);
		} catch (SQLException e) {
			e.printStackTrace();
			respuesta = construirXmlError(e.getLocalizedMessage(), numeroTransaccion, tagVacio);
		}

		return respuesta.toString();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public String ConsultarCupones(String xml) {
		StringBuilder respuesta = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");

		String tagVacio = "<CUPONES></CUPONES>";
		String numeroTransaccion = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		dbf.setNamespaceAware(true);
		dbf.setIgnoringElementContentWhitespace(true);
		dbf.setIgnoringComments(true);
		dbf.setExpandEntityReferences(false);
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(new InputSource(new StringReader(xml)));

			Node stream = doc.getFirstChild();
			Node header = stream.getFirstChild();
			Node itransaccion = header.getFirstChild();
			numeroTransaccion = itransaccion.getTextContent();

			// parametros
			String secuencial = null;
			String tipo = null;
			String nroDocumento = null;

			NodeList lista = stream.getChildNodes();
			for (int i = 0; i < lista.getLength(); i++) {
				if (lista.item(i).getNodeName().equals("PARAMETROS")) {
					Node parametros = lista.item(i);

					NodeList listaParametros = parametros.getChildNodes();

					for (int x = 0; x < listaParametros.getLength(); x++) {

						if (listaParametros.item(x).getNodeName().equals("SECPER")) {
							secuencial = listaParametros.item(x).getTextContent();
						}
						if (listaParametros.item(x).getNodeName().equals("TPODOC")) {
							tipo = listaParametros.item(x).getTextContent();
						}
						if (listaParametros.item(x).getNodeName().equals("NRODOC")) {
							nroDocumento = listaParametros.item(x).getTextContent();
						}
					}
				}
			}
			Integer secuencialPersona = null;
			if (secuencial != null && !secuencial.isEmpty()) {
				secuencialPersona = Integer.parseInt(secuencial);
			}
			Integer tipoInt = null;
			if (tipo != null && !tipo.isEmpty()) {
				tipoInt = conversionTipoDocumentoString(tipo);
			}

			respuesta.append("<STREAM>");
			respuesta.append("<HEADER><ITRANSACCION>" + numeroTransaccion + "</ITRANSACCION></HEADER>");
			respuesta.append("<CUPONES>");

			List<Long> listaIdSise;
			if (secuencialPersona == null) {
				List<ListaCuponesPendientesRs> listaR = listaCuponesPendientesSp.listaCuponesPendientesSp(null, tipoInt,
						nroDocumento);
				respuesta.append(cargarCuponesXML(listaR, secuencialPersona));
			} else {
				listaIdSise = personaEquividaServicio.obtenerIdSisePersona(secuencialPersona);
				for (Long long1 : listaIdSise) {
					List<ListaCuponesPendientesRs> listaR = listaCuponesPendientesSp
							.listaCuponesPendientesSp(long1.intValue(), tipoInt, nroDocumento);
					respuesta.append(cargarCuponesXML(listaR, secuencialPersona));
				}
			}

			respuesta.append("</CUPONES>");
			anadirNoError(respuesta);
			respuesta.append("</STREAM>");

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			respuesta = construirXmlError(e.getLocalizedMessage(), numeroTransaccion, tagVacio);
		} catch (SAXException e) {
			e.printStackTrace();
			respuesta = construirXmlError(e.getLocalizedMessage(), numeroTransaccion, tagVacio);
		} catch (IOException e) {
			e.printStackTrace();
			respuesta = construirXmlError(e.getLocalizedMessage(), numeroTransaccion, tagVacio);
		} catch (SQLException e) {
			e.printStackTrace();
			respuesta = construirXmlError(e.getLocalizedMessage(), numeroTransaccion, tagVacio);
		}

		return respuesta.toString();
	}

	private StringBuilder cargarCuponesXML(List<ListaCuponesPendientesRs> listaR, Integer secuencial) {
		StringBuilder respuesta = new StringBuilder("");
		for (ListaCuponesPendientesRs rs : listaR) {
			respuesta.append("<CUPON>");
			respuesta.append("<SECPER>" + secuencial + "</SECPER>");
			respuesta.append("<NROPOL>" + rs.getNumero_poliza() + "</NROPOL>");
			respuesta.append("<CUPPEND>" + rs.getNro_cupones_pendientes() + "</CUPPEND>");
			respuesta.append("<ESTCUPON>" + rs.getEstado_ult_cupon() + "</ESTCUPON>");
			respuesta.append("<DIASMORO>" + rs.getDias_morosidad() + "</DIASMORO>");
			respuesta.append("<MONTO>" + rs.getMonto() + "</MONTO>");
			respuesta.append("<PERIODG>" + rs.getPeriodo_gracia() + "</PERIODG>");
			respuesta.append("<INSTCOND>" + rs.getInstitucion_conducto() + "</INSTCOND>");
			respuesta.append("<NROCOND>" + rs.getNro_conducto() + "</NROCOND>");
			respuesta.append("<FVIGCOND>" + rs.getFecha_vigencia_conducto() + "</FVIGCOND>");
			respuesta.append("</CUPON>");
		}
		return respuesta;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public String ConsultarPagos(String xml) {
		StringBuilder respuesta = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");

		String tagVacio = "<PAGO></PAGO>";
		String numeroTransaccion = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		dbf.setNamespaceAware(true);
		dbf.setIgnoringElementContentWhitespace(true);
		dbf.setIgnoringComments(true);
		dbf.setExpandEntityReferences(false);
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(new InputSource(new StringReader(xml)));

			Node stream = doc.getFirstChild();
			Node header = stream.getFirstChild();
			Node itransaccion = header.getFirstChild();
			numeroTransaccion = itransaccion.getTextContent();

			// parametros
			String secuencial = null;
			String tipo = null;
			String nroDocumento = null;

			NodeList lista = stream.getChildNodes();
			for (int i = 0; i < lista.getLength(); i++) {
				if (lista.item(i).getNodeName().equals("PARAMETROS")) {
					Node parametros = lista.item(i);

					NodeList listaParametros = parametros.getChildNodes();

					for (int x = 0; x < listaParametros.getLength(); x++) {

						if (listaParametros.item(x).getNodeName().equals("SECPER")) {
							secuencial = listaParametros.item(x).getTextContent();
						}
						if (listaParametros.item(x).getNodeName().equals("TPODOC")) {
							tipo = listaParametros.item(x).getTextContent();
						}
						if (listaParametros.item(x).getNodeName().equals("NRODOC")) {
							nroDocumento = listaParametros.item(x).getTextContent();
						}
					}
				}
			}

			Integer secuencialPersona = null;
			if (secuencial != null && !secuencial.isEmpty()) {
				secuencialPersona = Integer.parseInt(secuencial);
			}
			Integer tipoInt = null;
			if (tipo != null && !tipo.isEmpty()) {
				tipoInt = conversionTipoDocumentoString(tipo);
			}

			respuesta.append("<STREAM>");
			respuesta.append("<HEADER><ITRANSACCION>" + numeroTransaccion + "</ITRANSACCION></HEADER>");
			respuesta.append("<PAGOS>");

			if (secuencialPersona == null) {
				List<ListaPagosRs> listaR = listaPagosSp.listaPagosSp(null, tipoInt, nroDocumento);
				respuesta.append(cargarPagosXML(listaR, secuencialPersona));
			} else {

				List<Long> listaIdSise = personaEquividaServicio.obtenerIdSisePersona(secuencialPersona);

				listaIdSise = personaEquividaServicio.obtenerIdSisePersona(secuencialPersona);
				for (Long long1 : listaIdSise) {
					List<ListaPagosRs> listaR = listaPagosSp.listaPagosSp(long1.intValue(), tipoInt, nroDocumento);
					respuesta.append(cargarPagosXML(listaR, secuencialPersona));
				}
			}

			respuesta.append("</PAGOS>");

			anadirNoError(respuesta);
			respuesta.append("</STREAM>");

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			respuesta = construirXmlError(e.getLocalizedMessage(), numeroTransaccion, tagVacio);
		} catch (SAXException e) {
			e.printStackTrace();
			respuesta = construirXmlError(e.getLocalizedMessage(), numeroTransaccion, tagVacio);
		} catch (IOException e) {
			e.printStackTrace();
			respuesta = construirXmlError(e.getLocalizedMessage(), numeroTransaccion, tagVacio);
		} catch (SQLException e) {
			e.printStackTrace();
			respuesta = construirXmlError(e.getLocalizedMessage(), numeroTransaccion, tagVacio);
		}

		return respuesta.toString();
	}

	private StringBuilder cargarPagosXML(List<ListaPagosRs> listaR, Integer secuencial) {
		StringBuilder respuesta = new StringBuilder("");
		for (ListaPagosRs rs : listaR) {
			respuesta.append("<PAGO>");
			respuesta.append("<SECPER>" + secuencial + "</SECPER>");
			respuesta.append("<NROPOL>" + rs.getNumero_poliza() + "</NROPOL>");
			respuesta.append("<FPAGO>" + rs.getFecha_pago() + "</FPAGO>");
			respuesta.append("<VALOR>" + rs.getValor_pago() + "</VALOR>");
			respuesta.append("<NROTRAN>" + rs.getNo_transaccion() + "</NROTRAN>");
			respuesta.append("<MES>" + rs.getMes() + "</MES>");
			respuesta.append("</PAGO>");
		}
		return respuesta;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public String ClientesAniversariosPolizas(String xml) {
		StringBuilder respuesta = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");

		String tagVacio = "<PERSONAS></PERSONAS>";
		String numeroTransaccion = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		List<Long> listaIdSise = new ArrayList<Long>();
		List<String> listaNull = new ArrayList<String>();
		dbf.setValidating(false);
		dbf.setNamespaceAware(true);
		dbf.setIgnoringElementContentWhitespace(true);
		dbf.setIgnoringComments(true);
		dbf.setExpandEntityReferences(false);
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(new InputSource(new StringReader(xml)));

			Node stream = doc.getFirstChild();
			Node header = stream.getFirstChild();
			Node itransaccion = header.getFirstChild();
			numeroTransaccion = itransaccion.getTextContent();

			// parametros
			String meses = null;
			String codCobertura = null;

			NodeList lista = stream.getChildNodes();
			for (int i = 0; i < lista.getLength(); i++) {
				if (lista.item(i).getNodeName().equals("PARAMETROS")) {
					Node parametros = lista.item(i);

					NodeList listaParametros = parametros.getChildNodes();

					for (int x = 0; x < listaParametros.getLength(); x++) {

						if (listaParametros.item(x).getNodeName().equals("CANTMESES")) {
							meses = listaParametros.item(x).getTextContent();
						}

						if (listaParametros.item(x).getNodeName().equals("CODCOB")) {
							codCobertura = listaParametros.item(x).getTextContent();
						}
					}
				}
			}
			Integer mesesP = Integer.parseInt(meses);
			Integer codC = Integer.parseInt(codCobertura);
			List<ListaConductoAniversarioRs> listaR = listaConductoAniversarioSp.listaConductoAniversarioSp(mesesP,
					codC);

			respuesta.append("<STREAM>");
			respuesta.append("<HEADER><ITRANSACCION>" + numeroTransaccion + "</ITRANSACCION></HEADER>");

			respuesta.append("<PERSONAS>");

			if (listaR.size() > 0) {
				for (ListaConductoAniversarioRs listaConductoAniversarioRs : listaR) {
					listaIdSise.add(listaConductoAniversarioRs.getSecuencial_persona().longValue());
				}
				Map<Long, Long> mapaId = personaEquividaServicio.obtenerSecuencialPersona(listaIdSise);

				for (ListaConductoAniversarioRs listaConductoAniversarioRs : listaR) {
					Long idAux = mapaId.get(listaConductoAniversarioRs.getSecuencial_persona());

					if (idAux == null) {
						listaNull.add(listaConductoAniversarioRs.getNumeroDocumento());
					} else {
						respuesta.append("<PERSONA>");
						respuesta.append("<SECPER>" + idAux + "</SECPER>");
						respuesta.append("<TPODOC>"
								+ conversionTipoDocumentoInteger(listaConductoAniversarioRs.getTipo()) + "</TPODOC>");
						respuesta.append("<NRODOC>" + listaConductoAniversarioRs.getNumeroDocumento() + "</NRODOC>");
						respuesta.append("</PERSONA>");
					}
				}
				Map<String, Integer> mapaDocs = personaNaturalServicio.obtenerPersonaNaturalByDocumento(listaNull);

				for (ListaConductoAniversarioRs listaConductoAniversarioRs : listaR) {
					Integer docAux = mapaDocs.get(listaConductoAniversarioRs.getNumeroDocumento());
					respuesta.append("<PERSONA>");
					respuesta.append("<SECPER>" + docAux + "</SECPER>");
					respuesta.append("<TPODOC>" + conversionTipoDocumentoInteger(listaConductoAniversarioRs.getTipo())
							+ "</TPODOC>");
					respuesta.append("<NRODOC>" + listaConductoAniversarioRs.getNumeroDocumento() + "</NRODOC>");
					respuesta.append("</PERSONA>");

				}
			}

			respuesta.append("</PERSONAS>");
			anadirNoError(respuesta);
			respuesta.append("</STREAM>");

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			respuesta = construirXmlError(e.getLocalizedMessage(), numeroTransaccion, tagVacio);
		} catch (SAXException e) {
			e.printStackTrace();
			respuesta = construirXmlError(e.getLocalizedMessage(), numeroTransaccion, tagVacio);
		} catch (IOException e) {
			e.printStackTrace();
			respuesta = construirXmlError(e.getLocalizedMessage(), numeroTransaccion, tagVacio);
		} catch (SQLException e) {
			e.printStackTrace();
			respuesta = construirXmlError(e.getLocalizedMessage(), numeroTransaccion, tagVacio);
		}

		return respuesta.toString();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public String ClientesConPolizasConCondicionPago(String xml) {
		StringBuilder respuesta = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");

		String tagVacio = "<PERSONAS></PERSONAS>";
		String numeroTransaccion = null;
		List<Long> listaIdSise = new ArrayList<Long>();
		List<String> listaNull = new ArrayList<String>();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		dbf.setNamespaceAware(true);
		dbf.setIgnoringElementContentWhitespace(true);
		dbf.setIgnoringComments(true);
		dbf.setExpandEntityReferences(false);
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(new InputSource(new StringReader(xml)));

			Node stream = doc.getFirstChild();
			Node header = stream.getFirstChild();
			Node itransaccion = header.getFirstChild();
			numeroTransaccion = itransaccion.getTextContent();

			// parametros
			String fecha = null;
			String condPago = null;

			NodeList lista = stream.getChildNodes();
			for (int i = 0; i < lista.getLength(); i++) {
				if (lista.item(i).getNodeName().equals("PARAMETROS")) {
					Node parametros = lista.item(i);

					NodeList listaParametros = parametros.getChildNodes();

					for (int x = 0; x < listaParametros.getLength(); x++) {

						if (listaParametros.item(x).getNodeName().equals("FECHA")) {
							String fechaTmp = listaParametros.item(x).getTextContent();
							fecha = fechaTmp.substring(6, 10) + fechaTmp.substring(3, 5) + fechaTmp.substring(0, 2);
						}
						if (listaParametros.item(x).getNodeName().equals("CONDPAGO")) {
							condPago = listaParametros.item(x).getTextContent();
						}
					}
				}
			}
			Integer codC = Integer.parseInt(condPago);
			List<ListaConductoPagoRs> listaR = listaConductoPagoSp.listaConductoPagoSp(fecha, codC);
			respuesta.append("<STREAM>");
			respuesta.append("<HEADER><ITRANSACCION>" + numeroTransaccion + "</ITRANSACCION></HEADER>");

			respuesta.append("<PERSONAS>");
			if (listaR.size() > 0) {

				for (ListaConductoPagoRs listaRs : listaR) {
					listaIdSise.add(listaRs.getSecuencial_persona().longValue());
				}
				Map<Long, Long> mapaId = personaEquividaServicio.obtenerSecuencialPersona(listaIdSise);

				for (ListaConductoPagoRs listaRs : listaR) {
					Long idAux = mapaId.get(listaRs.getSecuencial_persona());

					if (idAux == null) {
						listaNull.add(listaRs.getNumeroDocumento());
					} else {
						respuesta.append("<PERSONA>");
						respuesta.append("<SECPER>" + idAux + "</SECPER>");
						respuesta.append("<TPODOC>" + conversionTipoDocumentoInteger(listaRs.getTipo()) + "</TPODOC>");
						respuesta.append("<NRODOC>" + listaRs.getNumeroDocumento() + "</NRODOC>");
						respuesta.append("</PERSONA>");
					}
				}

				Map<String, Integer> mapaDocs = personaNaturalServicio.obtenerPersonaNaturalByDocumento(listaNull);

				for (ListaConductoPagoRs listaAux : listaR) {
					Integer docAux = mapaDocs.get(listaAux.getNumeroDocumento());
					respuesta.append("<PERSONA>");
					respuesta.append("<SECPER>" + docAux + "</SECPER>");
					respuesta.append("<TPODOC>" + conversionTipoDocumentoInteger(listaAux.getTipo()) + "</TPODOC>");
					respuesta.append("<NRODOC>" + listaAux.getNumeroDocumento() + "</NRODOC>");
					respuesta.append("</PERSONA>");

				}
			}

			respuesta.append("</PERSONAS>");
			anadirNoError(respuesta);
			respuesta.append("</STREAM>");

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			respuesta = construirXmlError(e.getLocalizedMessage(), numeroTransaccion, tagVacio);
		} catch (SAXException e) {
			e.printStackTrace();
			respuesta = construirXmlError(e.getLocalizedMessage(), numeroTransaccion, tagVacio);
		} catch (IOException e) {
			e.printStackTrace();
			respuesta = construirXmlError(e.getLocalizedMessage(), numeroTransaccion, tagVacio);
		} catch (SQLException e) {
			e.printStackTrace();
			respuesta = construirXmlError(e.getLocalizedMessage(), numeroTransaccion, tagVacio);
		}

		return respuesta.toString();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public String ClientesConPolizasConAsesorActivoInactivo(String xml) {
		StringBuilder respuesta = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");

		String tagVacio = "<PERSONAS></PERSONAS>";
		String numeroTransaccion = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		List<Long> listaIdSise = new ArrayList<Long>();
		List<String> listaNull = new ArrayList<String>();
		dbf.setValidating(false);
		dbf.setNamespaceAware(true);
		dbf.setIgnoringElementContentWhitespace(true);
		dbf.setIgnoringComments(true);
		dbf.setExpandEntityReferences(false);
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(new InputSource(new StringReader(xml)));

			Node stream = doc.getFirstChild();
			Node header = stream.getFirstChild();
			Node itransaccion = header.getFirstChild();
			numeroTransaccion = itransaccion.getTextContent();

			// parametros
			String snAsesores = null;

			NodeList lista = stream.getChildNodes();
			for (int i = 0; i < lista.getLength(); i++) {
				if (lista.item(i).getNodeName().equals("PARAMETROS")) {
					Node parametros = lista.item(i);
					NodeList listaParametros = parametros.getChildNodes();

					for (int x = 0; x < listaParametros.getLength(); x++) {

						if (listaParametros.item(x).getNodeName().equals("ESTASESOR")) {
							snAsesores = listaParametros.item(x).getTextContent();
						}
					}
				}
			}
			int estado = -10;
			if (snAsesores.equals("Activo")) {
				estado = -1;
			}
			if (snAsesores.equals("Inactivo")) {
				estado = 0;
			}
			if (estado == -10) {
				respuesta = construirXmlError("Estado solo puede 'Activo' e 'Inactivo'", numeroTransaccion, tagVacio);
				return respuesta.toString();
			}
			respuesta.append("<STREAM>");
			respuesta.append("<HEADER><ITRANSACCION>" + numeroTransaccion + "</ITRANSACCION></HEADER>");

			respuesta.append("<PERSONAS>");

			List<ListaAseguradoPorAsesorRs> listaR = listaAseguradoPorAsesorSp.listaAseguradoPorAsesorSp(estado);

			if (listaR.size() > 0) {
				for (ListaAseguradoPorAsesorRs listaRs : listaR) {
					listaIdSise.add(listaRs.getSecuencial_persona().longValue());
				}
				Map<Long, Long> mapaId = personaEquividaServicio.obtenerSecuencialPersona(listaIdSise);

				for (ListaAseguradoPorAsesorRs listaRs : listaR) {
					Long idAux = mapaId.get(listaRs.getSecuencial_persona());

					if (idAux == null) {
						listaNull.add(listaRs.getNumeroDocumento());
					} else {
						respuesta.append("<PERSONA>");
						respuesta.append("<SECPER>" + idAux + "</SECPER>");
						respuesta.append("<TPODOC>" + conversionTipoDocumentoInteger(listaRs.getTipo()) + "</TPODOC>");
						respuesta.append("<NRODOC>" + listaRs.getNumeroDocumento() + "</NRODOC>");
						respuesta.append("</PERSONA>");
					}
				}
				Map<String, Integer> mapaDocs = personaNaturalServicio.obtenerPersonaNaturalByDocumento(listaNull);

				for (ListaAseguradoPorAsesorRs listaRs : listaR) {
					Integer docAux = mapaDocs.get(listaRs.getNumeroDocumento());
					respuesta.append("<PERSONA>");
					respuesta.append("<SECPER>" + docAux + "</SECPER>");
					respuesta.append("<TPODOC>" + conversionTipoDocumentoInteger(listaRs.getTipo()) + "</TPODOC>");
					respuesta.append("<NRODOC>" + listaRs.getNumeroDocumento() + "</NRODOC>");
					respuesta.append("</PERSONA>");

				}
			}

			respuesta.append("</PERSONAS>");
			anadirNoError(respuesta);
			respuesta.append("</STREAM>");

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			respuesta = construirXmlError(e.getLocalizedMessage(), numeroTransaccion, tagVacio);
		} catch (SAXException e) {
			e.printStackTrace();
			respuesta = construirXmlError(e.getLocalizedMessage(), numeroTransaccion, tagVacio);
		} catch (IOException e) {
			e.printStackTrace();
			respuesta = construirXmlError(e.getLocalizedMessage(), numeroTransaccion, tagVacio);
		} catch (SQLException e) {
			e.printStackTrace();
			respuesta = construirXmlError(e.getLocalizedMessage(), numeroTransaccion, tagVacio);
		}

		return respuesta.toString();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public String ClientesConCuponesPendientesInimputadosPagoNoDirecto() {
		StringBuilder respuesta = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");

		String tagVacio = "<PERSONAS></PERSONAS>";
		String numeroTransaccion = "027";
		List<Long> listaIdSise = new ArrayList<Long>();
		List<String> listaNull = new ArrayList<String>();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		dbf.setNamespaceAware(true);
		dbf.setIgnoringElementContentWhitespace(true);
		dbf.setIgnoringComments(true);
		dbf.setExpandEntityReferences(false);
		try {

			// sin parametros
			List<ListaAseguradoCuponPendienteRs> listaR = listaAseguradoCuponPendienteSp
					.listaAseguradoCuponPendienteSp();

			for (ListaAseguradoCuponPendienteRs listaRs : listaR) {
				listaIdSise.add(listaRs.getSecuencial_persona().longValue());
			}
			Map<Long, Long> mapaId = personaEquividaServicio.obtenerSecuencialPersona(listaIdSise);

			respuesta.append("<STREAM>");
			respuesta.append("<HEADER><ITRANSACCION>" + numeroTransaccion + "</ITRANSACCION></HEADER>");

			respuesta.append("<PERSONAS>");
			for (ListaAseguradoCuponPendienteRs listaRs : listaR) {
				Long idAux = mapaId.get(listaRs.getSecuencial_persona());

				if (idAux == null) {
					listaNull.add(listaRs.getNumeroDocumento());
				} else {
					respuesta.append("<PERSONA>");
					respuesta.append("<SECPER>" + idAux + "</SECPER>");
					respuesta.append("<TPODOC>" + (listaRs.getTipo()) + "</TPODOC>");
					respuesta.append("<NRODOC>" + listaRs.getNumeroDocumento() + "</NRODOC>");
					respuesta.append("</PERSONA>");
				}
			}
			Map<String, Integer> mapaDocs = personaNaturalServicio.obtenerPersonaNaturalByDocumento(listaNull);

			for (ListaAseguradoCuponPendienteRs listaRs : listaR) {
				Integer docAux = mapaDocs.get(listaRs.getNumeroDocumento());
				respuesta.append("<PERSONA>");
				respuesta.append("<SECPER>" + docAux + "</SECPER>");
				respuesta.append("<TPODOC>" + conversionTipoDocumentoInteger(listaRs.getTipo()) + "</TPODOC>");
				respuesta.append("<NRODOC>" + listaRs.getNumeroDocumento() + "</NRODOC>");
				respuesta.append("</PERSONA>");
			}
			respuesta.append("</PERSONAS>");
			anadirNoError(respuesta);
			respuesta.append("</STREAM>");

		} catch (SQLException e) {
			e.printStackTrace();
			respuesta = construirXmlError(e.getLocalizedMessage(), numeroTransaccion, tagVacio);
		}

		return respuesta.toString();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public String ClientesConConductoBancoEquivida() {
		StringBuilder respuesta = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");

		String tagVacio = "<PERSONAS></PERSONAS>";
		String numeroTransaccion = "029";
		List<Long> listaIdSise = new ArrayList<Long>();
		List<String> listaNull = new ArrayList<String>();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		dbf.setNamespaceAware(true);
		dbf.setIgnoringElementContentWhitespace(true);
		dbf.setIgnoringComments(true);
		dbf.setExpandEntityReferences(false);
		try {

			// sin parametros
			List<ListaCondicionConductoRs> listaR = listaCondicionConductoSp.listaCondicionConductoSp();

			for (ListaCondicionConductoRs listaRs : listaR) {
				listaIdSise.add(listaRs.getSecuencial_persona().longValue());
			}
			Map<Long, Long> mapaId = personaEquividaServicio.obtenerSecuencialPersona(listaIdSise);

			respuesta.append("<STREAM>");
			respuesta.append("<HEADER><ITRANSACCION>" + numeroTransaccion + "</ITRANSACCION></HEADER>");

			respuesta.append("<PERSONAS>");
			for (ListaCondicionConductoRs listaRs : listaR) {
				Long idAux = mapaId.get(listaRs.getSecuencial_persona());

				if (idAux == null) {
					listaNull.add(listaRs.getNumeroDocumento());
				} else {
					respuesta.append("<PERSONA>");
					respuesta.append("<SECPER>" + idAux + "</SECPER>");
					respuesta.append("<TPODOC>" + conversionTipoDocumentoInteger(listaRs.getTipo()) + "</TPODOC>");
					respuesta.append("<NRODOC>" + listaRs.getNumeroDocumento() + "</NRODOC>");
					respuesta.append("</PERSONA>");
				}
			}
			Map<String, Integer> mapaDocs = personaNaturalServicio.obtenerPersonaNaturalByDocumento(listaNull);

			for (ListaCondicionConductoRs listaRs : listaR) {
				Integer docAux = mapaDocs.get(listaRs.getNumeroDocumento());
				respuesta.append("<PERSONA>");
				respuesta.append("<SECPER>" + docAux + "</SECPER>");
				respuesta.append("<TPODOC>" + conversionTipoDocumentoInteger(listaRs.getTipo()) + "</TPODOC>");
				respuesta.append("<NRODOC>" + listaRs.getNumeroDocumento() + "</NRODOC>");
				respuesta.append("</PERSONA>");

			}
			respuesta.append("</PERSONAS>");
			anadirNoError(respuesta);
			respuesta.append("</STREAM>");

		} catch (SQLException e) {
			e.printStackTrace();
			respuesta = construirXmlError(e.getLocalizedMessage(), numeroTransaccion, tagVacio);
		}

		return respuesta.toString();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public String ClientesEnPeriodoDeGracia() {
		StringBuilder respuesta = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");

		String tagVacio = "<PERSONAS></PERSONAS>";
		String numeroTransaccion = "030";
		List<Long> listaIdSise = new ArrayList<Long>();
		List<String> listaNull = new ArrayList<String>();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		dbf.setNamespaceAware(true);
		dbf.setIgnoringElementContentWhitespace(true);
		dbf.setIgnoringComments(true);
		dbf.setExpandEntityReferences(false);
		try {
			// sin parametros
			List<ListaCondicionPeriodoGraciaRs> listaR = listaCondicionPeriodoGraciaSp.listaCondicionPeriodoGraciaSp();

			for (ListaCondicionPeriodoGraciaRs listaRs : listaR) {
				listaIdSise.add(listaRs.getSecuencial_persona().longValue());
			}
			Map<Long, Long> mapaId = personaEquividaServicio.obtenerSecuencialPersona(listaIdSise);

			respuesta.append("<STREAM>");
			respuesta.append("<HEADER><ITRANSACCION>" + numeroTransaccion + "</ITRANSACCION></HEADER>");

			respuesta.append("<PERSONAS>");
			for (ListaCondicionPeriodoGraciaRs listaRs : listaR) {
				Long idAux = mapaId.get(listaRs.getSecuencial_persona());

				if (idAux == null) {
					listaNull.add(listaRs.getNumeroDocumento());
				} else {
					respuesta.append("<PERSONA>");
					respuesta.append("<SECPER>" + idAux + "</SECPER>");
					respuesta.append("<TPODOC>" + conversionTipoDocumentoInteger(listaRs.getTipo()) + "</TPODOC>");
					respuesta.append("<NRODOC>" + listaRs.getNumeroDocumento() + "</NRODOC>");
					respuesta.append("</PERSONA>");
				}
			}
			Map<String, Integer> mapaDocs = personaNaturalServicio.obtenerPersonaNaturalByDocumento(listaNull);

			for (ListaCondicionPeriodoGraciaRs listaRs : listaR) {
				Integer docAux = mapaDocs.get(listaRs.getNumeroDocumento());
				respuesta.append("<PERSONA>");
				respuesta.append("<SECPER>" + docAux + "</SECPER>");
				respuesta.append("<TPODOC>" + conversionTipoDocumentoInteger(listaRs.getTipo()) + "</TPODOC>");
				respuesta.append("<NRODOC>" + listaRs.getNumeroDocumento() + "</NRODOC>");
				respuesta.append("</PERSONA>");

			}
			respuesta.append("</PERSONAS>");
			anadirNoError(respuesta);
			respuesta.append("</STREAM>");

		} catch (SQLException e) {
			e.printStackTrace();
			respuesta = construirXmlError(e.getLocalizedMessage(), numeroTransaccion, tagVacio);
		}

		return respuesta.toString();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public String ClientesConTarjetasExpiracion2meses() {
		StringBuilder respuesta = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");

		String tagVacio = "<PERSONAS></PERSONAS>";
		String numeroTransaccion = "031";
		List<Long> listaIdSise = new ArrayList<Long>();
		List<String> listaNull = new ArrayList<String>();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		dbf.setNamespaceAware(true);
		dbf.setIgnoringElementContentWhitespace(true);
		dbf.setIgnoringComments(true);
		dbf.setExpandEntityReferences(false);
		try {

			// sin parametros
			List<ListaCondicionTarjetaExpiradaRs> listaR = listaCondicionTarjetaExpiradaSp
					.listaCondicionTarjetaExpiradaSp();

			LOG.info("listaR.size: " + ((listaR != null) ? listaR.size() : null));
			
			if(listaR==null||listaR.size()==0) {
				respuesta = construirXmlError("No se devuelven datos en el procedimiento", numeroTransaccion, tagVacio);
				return respuesta.toString();
			}

			for (ListaCondicionTarjetaExpiradaRs listaRs : listaR) {
				listaIdSise.add(listaRs.getSecuencial_persona().longValue());
			}
			Map<Long, Long> mapaId = personaEquividaServicio.obtenerSecuencialPersona(listaIdSise);

			respuesta.append("<STREAM>");
			respuesta.append("<HEADER><ITRANSACCION>" + numeroTransaccion + "</ITRANSACCION></HEADER>");

			respuesta.append("<PERSONAS>");
			for (ListaCondicionTarjetaExpiradaRs listaRs : listaR) {
				Long idAux = mapaId.get(listaRs.getSecuencial_persona());

				if (idAux == null) {
					listaNull.add(listaRs.getNumeroDocumento());
				} else {
					respuesta.append("<PERSONA>");
					respuesta.append("<SECPER>" + idAux + "</SECPER>");
					respuesta.append("<TPODOC>" + conversionTipoDocumentoInteger(listaRs.getTipo()) + "</TPODOC>");
					respuesta.append("<NRODOC>" + listaRs.getNumeroDocumento() + "</NRODOC>");
					respuesta.append("</PERSONA>");
				}
			}
			Map<String, Integer> mapaDocs = personaNaturalServicio.obtenerPersonaNaturalByDocumento(listaNull);

			for (ListaCondicionTarjetaExpiradaRs listaRs : listaR) {
				Integer docAux = mapaDocs.get(listaRs.getNumeroDocumento());
				respuesta.append("<PERSONA>");
				respuesta.append("<SECPER>" + docAux + "</SECPER>");
				respuesta.append("<TPODOC>" + conversionTipoDocumentoInteger(listaRs.getTipo()) + "</TPODOC>");
				respuesta.append("<NRODOC>" + listaRs.getNumeroDocumento() + "</NRODOC>");
				respuesta.append("</PERSONA>");

			}

			respuesta.append("</PERSONAS>");
			anadirNoError(respuesta);
			respuesta.append("</STREAM>");

		} catch (SQLException e) {
			e.printStackTrace();
			respuesta = construirXmlError(e.getLocalizedMessage(), numeroTransaccion, tagVacio);
		}

		return respuesta.toString();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public String ClientesConCuponesConPagoDirecto() {
		StringBuilder respuesta = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");

		String tagVacio = "<PERSONAS></PERSONAS>";
		String numeroTransaccion = "028";
		List<Long> listaIdSise = new ArrayList<Long>();
		List<String> listaNull = new ArrayList<String>();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		dbf.setNamespaceAware(true);
		dbf.setIgnoringElementContentWhitespace(true);
		dbf.setIgnoringComments(true);
		dbf.setExpandEntityReferences(false);
		try {

			// sin parametros
			List<ListaCondicionPagoDirectoRs> listaR = listaCondicionPagoDirectoSp.listaCondicionPagoDirectoSp();

			for (ListaCondicionPagoDirectoRs listaRs : listaR) {
				listaIdSise.add(listaRs.getSecuencial_persona().longValue());
			}
			Map<Long, Long> mapaId = personaEquividaServicio.obtenerSecuencialPersona(listaIdSise);

			respuesta.append("<STREAM>");
			respuesta.append("<HEADER><ITRANSACCION>" + numeroTransaccion + "</ITRANSACCION></HEADER>");

			respuesta.append("<PERSONAS>");
			for (ListaCondicionPagoDirectoRs listaRs : listaR) {
				Long idAux = mapaId.get(listaRs.getSecuencial_persona());

				if (idAux == null) {
					listaNull.add(listaRs.getNumeroDocumento());
				} else {
					respuesta.append("<PERSONA>");
					respuesta.append("<SECPER>" + idAux + "</SECPER>");
					respuesta.append("<TPODOC>" + conversionTipoDocumentoInteger(listaRs.getTipo()) + "</TPODOC>");
					respuesta.append("<NRODOC>" + listaRs.getNumeroDocumento() + "</NRODOC>");
					respuesta.append("</PERSONA>");
				}
			}
			Map<String, Integer> mapaDocs = personaNaturalServicio.obtenerPersonaNaturalByDocumento(listaNull);

			for (ListaCondicionPagoDirectoRs listaRs : listaR) {
				Integer docAux = mapaDocs.get(listaRs.getNumeroDocumento());
				respuesta.append("<PERSONA>");
				respuesta.append("<SECPER>" + docAux + "</SECPER>");
				respuesta.append("<TPODOC>" + conversionTipoDocumentoInteger(listaRs.getTipo()) + "</TPODOC>");
				respuesta.append("<NRODOC>" + listaRs.getNumeroDocumento() + "</NRODOC>");
				respuesta.append("</PERSONA>");

			}
			respuesta.append("</PERSONAS>");
			anadirNoError(respuesta);
			respuesta.append("</STREAM>");

		} catch (SQLException e) {
			e.printStackTrace();
			respuesta = construirXmlError(e.getLocalizedMessage(), numeroTransaccion, tagVacio);
		}

		return respuesta.toString();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public String ConsultarPersonas(String xml) {
		StringBuilder respuesta = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");

		String tagVacio = "<PERSONAS></PERSONAS>";

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		dbf.setNamespaceAware(true);
		dbf.setIgnoringElementContentWhitespace(true);
		dbf.setIgnoringComments(true);
		dbf.setExpandEntityReferences(false);
		DocumentBuilder db;
		String numeroTransaccion = null;
		try {
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(new InputSource(new StringReader(xml)));

			Node stream = doc.getFirstChild();
			Node header = stream.getFirstChild();
			Node itransaccion = header.getFirstChild();
			numeroTransaccion = itransaccion.getTextContent();

			consultaPersonas(respuesta, stream, numeroTransaccion);

		} catch (CrmException e) {
			e.printStackTrace();
			respuesta = construirXmlError(e.getLocalizedMessage(), numeroTransaccion, tagVacio);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			respuesta = construirXmlError(e.getLocalizedMessage(), numeroTransaccion, tagVacio);
		} catch (SAXException e) {
			e.printStackTrace();
			respuesta = construirXmlError(e.getLocalizedMessage(), numeroTransaccion, tagVacio);
		} catch (IOException e) {
			e.printStackTrace();
			respuesta = construirXmlError(e.getLocalizedMessage(), numeroTransaccion, tagVacio);
		} catch (SQLException e) {
			e.printStackTrace();
			respuesta = construirXmlError(e.getLocalizedMessage(), numeroTransaccion, tagVacio);
		}

		return respuesta.toString();
	}

	private StringBuilder construirXmlError(String error, String transaccion, String tagVacio) {

		StringBuilder xml = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");

		xml.append("<STREAM>" + "<HEADER><ITRANSACCION>" + transaccion + "</ITRANSACCION></HEADER>");
		xml.append(tagVacio);// Ej <POLIZAS></POLIZAS>
		xml.append("<ERROR><CODIGO>1</CODIGO><MENSAJE>" + error + "</MENSAJE></ERROR></STREAM>");

		return xml;

	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public String ActualizarDatosPersona(String xml) {

		System.out.println("XML para actualizar:");
		System.out.println(xml);

		StringBuilder respuesta = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");

		String tagVacio = "<DATOSPERSONAS></DATOSPERSONAS>";

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		dbf.setNamespaceAware(true);
		dbf.setIgnoringElementContentWhitespace(true);
		dbf.setIgnoringComments(true);
		dbf.setExpandEntityReferences(false);
		DocumentBuilder db;
		String numeroTransaccion = null;
		try {
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(new InputSource(new StringReader(xml)));

			Node stream = doc.getFirstChild();
			Node header = stream.getFirstChild();
			Node itransaccion = header.getFirstChild();
			numeroTransaccion = itransaccion.getTextContent();

			// guarda en DB2
			actualizaPersonas(respuesta, stream, numeroTransaccion);

			String rechazados = CrmUtil.obtenerXmlPorTag(respuesta.toString(), "<RECHAZADOS>", "</RECHAZADOS>");
			System.out.println("rechazados:" + rechazados);
			if (rechazados != null) {
				int rechazadosInt = Integer.parseInt(rechazados);
				// si no hay rechazados entonces manda a guardar al crm
				if (rechazadosInt == 0) {
					String secPer = CrmUtil.obtenerXmlPorTag(xml, "<SECPER>", "</SECPER>");

					String nroDoc = CrmUtil.obtenerXmlPorTag(xml, "<NRODOC>", "</NRODOC>");

					System.out.println("secPer:" + secPer);
					System.out.println("nroDoc:" + nroDoc);

					String xmlEnvioConsulta = "<STREAM>" + "<HEADER><ITRANSACCION>009</ITRANSACCION></HEADER>"
							+ "<PARAMETROS>" + "<SECPER>" + secPer + "</SECPER>" + "<NRODOC>" + nroDoc + "</NRODOC>"
							+ "</PARAMETROS>" + "</STREAM>";

					System.out.println("xmlEnvioConsulta:" + xmlEnvioConsulta);

					String respuestaConsulta = ConsultarPersonas(xmlEnvioConsulta);

					System.out.println("respuestaConsulta:");
					System.out.println(respuestaConsulta);

					String xmlPersona = CrmUtil.obtenerXmlSoloDePersona(respuestaConsulta);

					String paraEnviarACrm = "<PERSONAS>" + xmlPersona + "</PERSONAS>";
					System.out.println("paraEnviarACrm:");
					System.out.println(paraEnviarACrm);

					String address = Parametros.getString("location.web.service.actualizacion.crm");
					URL url = new URL(address);

					ActualizacionesRegularesLocator locator = new ActualizacionesRegularesLocator();
					ActualizacionesRegularesSoap servicio = locator.getActualizacionesRegularesSoap(url);

					String respuestaCrm = servicio.consultarPersonas(paraEnviarACrm);
					System.out.println("RESPUESTA CRM:");
					System.out.println(respuestaCrm);

				}
			}

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			respuesta = construirXmlError(e.getLocalizedMessage(), numeroTransaccion, tagVacio);
		} catch (SAXException e) {
			e.printStackTrace();
			respuesta = construirXmlError(e.getLocalizedMessage(), numeroTransaccion, tagVacio);
		} catch (IOException e) {
			e.printStackTrace();
			respuesta = construirXmlError(e.getLocalizedMessage(), numeroTransaccion, tagVacio);
		} catch (SQLException e) {
			e.printStackTrace();
			respuesta = construirXmlError(e.getLocalizedMessage(), numeroTransaccion, tagVacio);
		} catch (ServiceException e) {
			e.printStackTrace();
			respuesta = construirXmlError(e.getLocalizedMessage(), numeroTransaccion, tagVacio);
		} catch (ErrorIngresoWsSiseException e) {
			e.printStackTrace();
			respuesta = construirXmlError(e.getLocalizedMessage(), numeroTransaccion, tagVacio);
		}

		System.out.println("respuesta actualizacion:");
		System.out.println(respuesta.toString());

		return respuesta.toString();

	}

	@Override
	public String ConsultarCompanias(String xml) {
		StringBuilder respuesta = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");

		String tagVacio = "<CIAS></CIAS>";

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		dbf.setNamespaceAware(true);
		dbf.setIgnoringElementContentWhitespace(true);
		dbf.setIgnoringComments(true);
		dbf.setExpandEntityReferences(false);
		DocumentBuilder db;
		String numeroTransaccion = null;
		try {
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(new InputSource(new StringReader(xml)));

			Node stream = doc.getFirstChild();
			Node header = stream.getFirstChild();
			Node itransaccion = header.getFirstChild();
			numeroTransaccion = itransaccion.getTextContent();

			consultaCompanias(respuesta, stream, numeroTransaccion);

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			respuesta = construirXmlError(e.getLocalizedMessage(), numeroTransaccion, tagVacio);
		} catch (SAXException e) {
			e.printStackTrace();
			respuesta = construirXmlError(e.getLocalizedMessage(), numeroTransaccion, tagVacio);
		} catch (IOException e) {
			e.printStackTrace();
			respuesta = construirXmlError(e.getLocalizedMessage(), numeroTransaccion, tagVacio);
		} catch (SQLException e) {
			e.printStackTrace();
			respuesta = construirXmlError(e.getLocalizedMessage(), numeroTransaccion, tagVacio);
		}

		return respuesta.toString();
	}

	private void consultaCompanias(StringBuilder respuesta, Node stream, String numeroTransaccion) throws SQLException {
		// parametros (TIPOB,SECUENCIAL,DOCUMENTO)
		String secuencial = null;
		String documento = null;

		NodeList lista = stream.getChildNodes();
		for (int i = 0; i < lista.getLength(); i++) {
			if (lista.item(i).getNodeName().equals("PARAMETROS")) {
				Node parametros = lista.item(i);

				NodeList listaParametros = parametros.getChildNodes();

				for (int x = 0; x < listaParametros.getLength(); x++) {

					if (listaParametros.item(x).getNodeName().equals("SECPER")) {
						secuencial = listaParametros.item(x).getTextContent();
					}
					if (listaParametros.item(x).getNodeName().equals("NRODOC")) {
						documento = listaParametros.item(x).getTextContent();
					}
				}
				System.out.println("secuencial:" + secuencial);
				System.out.println("documento:" + documento);
			}
		}

		if (secuencial != null) {
			Long secCompania = Long.parseLong(secuencial);
			CompaniaCrmDto companiaCrmDto = personaServicio.obtenerCompaniaCrm(secCompania);

			System.out.println("personaCrmDto:" + companiaCrmDto);
			System.out.println("identificacion:" + companiaCrmDto.getIdentificacion());

			System.out.println("personaEquividaServicio:" + personaEquividaServicio);

			List<Long> idsSise = personaEquividaServicio.obtenerIdSisePersona(secCompania.intValue());

			System.out.println("idsSise total:" + idsSise.size());

			List<ListaDatosPersonaRs> personaSiseLista = new ArrayList<ListaDatosPersonaRs>();

			for (Long id : idsSise) {
				System.out.println("id" + id);
				ListaDatosPersonaRs persona = listaDatosPersonaSp.listaDatosPersonaSp(id.intValue(), documento);
				personaSiseLista.add(persona);
			}

			respuesta.append("<STREAM>");
			respuesta.append("<HEADER><ITRANSACCION>" + numeroTransaccion + "</ITRANSACCION></HEADER>");
			respuesta.append("<CIAS>");
			respuesta.append("<CIA>");

			System.out.println("personaCrmDto.getCodTipoIdentificacion():" + companiaCrmDto.getCodTipoIdentificacion());

			respuesta.append("<TPODOC>" + companiaCrmDto.getCodTipoIdentificacion() + "</TPODOC>");
			respuesta.append("<NRODOC>" + companiaCrmDto.getIdentificacion() + "</NRODOC>");
			respuesta.append("<SECPER>" + companiaCrmDto.getSecuencialPersona() + "</SECPER>");
			respuesta.append("<NOM1>" + companiaCrmDto.getPrimerNombre() + "</NOM1>");
			respuesta.append("<NOM2>" + companiaCrmDto.getSegundoNombre() + "</NOM2>");
			respuesta.append("<APEPAT>" + companiaCrmDto.getApellidoPaterno() + "</APEPAT>");
			respuesta.append("<APEMAT>" + companiaCrmDto.getApellidoMaterno() + "</APEMAT>");
			respuesta.append("<FNAC>" + companiaCrmDto.getFechaNacimiento() + "</FNAC>");
			respuesta.append("<ECIVIL>" + companiaCrmDto.getEstadoCivil() + "</ECIVIL>");
			respuesta.append("<GEN>" + companiaCrmDto.getSexo() + "</GEN>");
			respuesta.append("<CODPROF>" + companiaCrmDto.getSecuencialProfesion() + "</CODPROF>");
			respuesta.append("<NEDUC>" + companiaCrmDto.getNivelEducacion() + "</NEDUC>");
			respuesta.append("<CODOCUP>" + companiaCrmDto.getCodOcupacion() + "</CODOCUP>");

			// TODO verrificar
			respuesta.append("<CYGNOM1>" + companiaCrmDto.getConyuge() + "</CYGNOM1>");
			respuesta.append("<CYGNOM2>" + companiaCrmDto.getConyuge() + "</CYGNOM2>");
			respuesta.append("<CYGAPEPAT>" + companiaCrmDto.getConyuge() + "</CYGAPEPAT>");
			respuesta.append("<CYGAPEMAT>" + companiaCrmDto.getConyuge() + "</CYGAPEMAT>");

			respuesta.append("<NROHJOS>" + companiaCrmDto.getNumHijos() + "</NROHJOS>");
			respuesta.append("<HRINICTO>" + companiaCrmDto.getHoraInicioContacto() + "</HRINICTO>");
			respuesta.append("<HRFINCTO>" + companiaCrmDto.getHoraFinContacto() + "</HRFINCTO>");

			respuesta.append("<RELLAB>" + companiaCrmDto.getRelacionLaboral() + "</RELLAB>");
			respuesta.append("<EMPRTRAB>" + companiaCrmDto.getEmpresaTrabaja() + "</EMPRTRAB>");
			respuesta.append("<CARGO>" + companiaCrmDto.getCargo() + "</CARGO>");

			respuesta.append("<MAIL1>" + companiaCrmDto.getEmail() + "</MAIL1>");
			respuesta.append("<MAIL2>" + companiaCrmDto.getEmailAlterno() + "</MAIL2>");

			respuesta.append("<TPODIR>" + companiaCrmDto.getTipoDireccion() + "</TPODIR>");

			respuesta.append("<CODPAIS>" + companiaCrmDto.getCodPais() + "</CODPAIS>");
			respuesta.append("<REGION>" + companiaCrmDto.getRegion() + "</REGION>");
			respuesta.append("<CODPROV>" + companiaCrmDto.getCodProv() + "</CODPROV>");

			respuesta.append("<CODCANTON>" + companiaCrmDto.getCodCanton() + "</CODCANTON>");
			respuesta.append("<CIUDAD>" + companiaCrmDto.getCiudad() + "</CIUDAD>");
			respuesta.append("<CODPARR>" + companiaCrmDto.getCodParr() + "</CODPARR>");
			respuesta.append("<BARRIO>" + companiaCrmDto.getBarrio() + "</BARRIO>");
			respuesta.append("<DIRCALLE1>" + companiaCrmDto.getDirCalle1() + "</DIRCALLE1>");
			respuesta.append("<DIRNRO>" + companiaCrmDto.getDirNumero() + "</DIRNRO>");
			respuesta.append("<DIRCALLE2>" + companiaCrmDto.getDirCalle2() + "</DIRCALLE2>");
			respuesta.append("<DIREDIF>" + companiaCrmDto.getDirEdif() + "</DIREDIF>");
			respuesta.append("<LONG>" + companiaCrmDto.getLongitud() + "</LONG>");
			respuesta.append("<LAT>" + companiaCrmDto.getLatitud() + "</LAT>");
			respuesta.append("<REFER>" + companiaCrmDto.getReferencia() + "</REFER>");

			respuesta.append("<TPOTEL>" + companiaCrmDto.getTipoTelefono() + "</TPOTEL>");
			respuesta.append("<CODTELPAIS>" + companiaCrmDto.getCodTelPais() + "</CODTELPAIS>");
			respuesta.append("<CODAREA>" + companiaCrmDto.getCodArea() + "</CODAREA>");
			respuesta.append("<TEL>" + companiaCrmDto.getTel() + "</TEL>");
			respuesta.append("<TELEXT>" + companiaCrmDto.getTelExt() + "</TELEXT>");

			respuesta.append("<PCNOM>" + companiaCrmDto.getContacto() + "</PCNOM>");
			respuesta.append("<PCTEL>" + "" + "</PCTEL>");
			respuesta.append("<PCEMAIL>" + companiaCrmDto.getEmailContacto() + "</PCEMAIL>");
			respuesta.append("<INGR>" + companiaCrmDto.getMontoIngresoMensual() + "</INGR>");

			// datos SISE

			// TODO preguntar estos dos
			Integer secBroker = null;
			Integer secPerBroker = null;
			String canalVentas = "";
			String asesorComercial = "";
			String asesorPostVenta = "";
			String director = "";
			String asistente = "";
			String segmentoCliente = "";
			String informacionSiniestros = "No";
			String nivelRiesgo = "No";
			List<String> beneficiosExternosLista = new ArrayList<String>();
			String beneficiosExternos = "";
			List<String> clienteDesdeLista = new ArrayList<String>();
			String clienteDesde = "";

			String clienteIndividual = "False";
			String aseguradoEmpresaCliente = "False";
			String contactoEmpresa = "False";
			String contactoBroker = "False";
			String inactivo = "False";
			// TODO
			BigDecimal valorCliente = new BigDecimal(0);
			// TODO
			BigDecimal capacidadPago = new BigDecimal(0);

			for (ListaDatosPersonaRs persona : personaSiseLista) {
				if (secBroker == null) {
					secBroker = persona.getBroker();
				}
				if (secPerBroker == null) {
					// TODO
					secPerBroker = persona.getBroker();
				}
				if (canalVentas == null) {
					canalVentas = persona.getCanal_Venta();
				}
				if (asesorComercial == null) {
					asesorComercial = persona.getAsesor_Comercial();
				}
				if (asesorPostVenta == null) {
					asesorPostVenta = persona.getAsesor_Post_Venta();
				}
				if (director == null) {
					director = persona.getDirector();
				}
				if (asistente == null) {
					asistente = persona.getAsistente();
				}
				if (segmentoCliente == null) {
					segmentoCliente = persona.getSegmento_Cliente();
				}
				if (persona.getInformacion_Siniestros().toLowerCase().startsWith("s")) {
					informacionSiniestros = "Si";
				}
				if (persona.getNivel_Riesgo_Cliente().toLowerCase().startsWith("s")) {
					nivelRiesgo = "Si";
				}
				if (!persona.getBeneficios_Externos().equals("")) {
					beneficiosExternosLista.add(persona.getBeneficios_Externos());
				}
				clienteDesdeLista.add(persona.getCliente_Desde());

				if (persona.getCliente_individual().toLowerCase().startsWith("s")) {
					clienteIndividual = "True";
				}

				if (persona.getAsegurado_de_empresa_cliente().toLowerCase().startsWith("s")) {
					aseguradoEmpresaCliente = "True";
				}

				if (persona.getContacto_empresa().toLowerCase().startsWith("s")) {
					contactoEmpresa = "True";
				}
				if (persona.getContacto_Broker_2().toLowerCase().startsWith("s")) {
					contactoBroker = "True";
				}
				if (persona.getInactivo().toLowerCase().startsWith("s")) {
					inactivo = "True";
				}
			}

			// beneficiosExternosLista.s
			if (beneficiosExternosLista.size() > 0) {
				Collections.sort(beneficiosExternosLista);
				beneficiosExternos = beneficiosExternosLista.get(0);// el
																	// primero
																	// (a,b,c...)
			}
			if (clienteDesdeLista.size() > 0) {
				Collections.sort(clienteDesdeLista);
				clienteDesde = clienteDesdeLista.get(0);// mas antiguo
			}

			respuesta.append("<SECBRK>" + secBroker + "</SECBRK>");
			respuesta.append("<SECPERBRK>" + secPerBroker + "</SECPERBRK>");
			respuesta.append("<CANALVTA>" + canalVentas + "</CANALVTA>");
			respuesta.append("<CODASECOM>" + asesorComercial + "</CODASECOM>");
			respuesta.append("<CODASEPOST>" + asesorPostVenta + "</CODASEPOST>");
			respuesta.append("<CODDCTOR>" + director + "</CODDCTOR>");
			respuesta.append("<CODASIST>" + asistente + "</CODASIST>");
			respuesta.append("<SEGM>" + segmentoCliente + "</SEGM>");

			// TODO
			respuesta.append("<PEP>" + "" + "</PEP>");

			respuesta.append("<INFOSIN>" + informacionSiniestros + "</INFOSIN>");
			respuesta.append("<NVLRIESGO>" + nivelRiesgo + "</NVLRIESGO>");

			respuesta.append("<BENEXT>" + beneficiosExternos + "</BENEXT>");
			respuesta.append("<CLIDESDE>" + clienteDesde + "</CLIDESDE>");
			// TODO
			respuesta.append("<COMENT>" + "" + "</COMENT>");
			// TODO
			respuesta.append("<RESERV>" + segmentoCliente + "</RESERV>");

			respuesta.append("<CLIIND>" + clienteIndividual + "</CLIIND>");
			respuesta.append("<CLIASEG>" + aseguradoEmpresaCliente + "</CLIASEG>");

			respuesta.append("<CTOEMP>" + contactoEmpresa + "</CTOEMP>");
			respuesta.append("<CTOBKR>" + contactoBroker + "</CTOBKR>");
			respuesta.append("<INACT>" + inactivo + "</INACT>");

			respuesta.append("<PNVALOR>" + valorCliente.doubleValue() + "</PNVALOR>");
			respuesta.append("<PNCAPAC>" + capacidadPago.doubleValue() + "</PNCAPAC>");

			respuesta.append("</CIA>");
			respuesta.append("</CIAS>");

			anadirNoError(respuesta);

			respuesta.append("</STREAM>");

		}

	}

	private synchronized void actualizaPersonas(StringBuilder respuesta, Node stream, String numeroTransaccion)
			throws SQLException, RemoteException, ServiceException, ErrorIngresoWsSiseException {
		// parametros (TIPOB,SECUENCIAL,DOCUMENTO)

		NodeList lista = stream.getChildNodes();
		Node datos = lista.item(1);
		NodeList personas = datos.getChildNodes();

		// itera una por una las personas
		RespuestaActualizacionDto res = new RespuestaActualizacionDto();

		StringBuilder log = new StringBuilder();

		forPersonas: for (int i = 0; i < personas.getLength(); i++) {

			int registro = i + 1;

			if (personas.item(i).getNodeName().equals("PERSONA")) {

				Node datosPersona = personas.item(i);
				NodeList persona = datosPersona.getChildNodes();

				String secuencial = null;
				String nroDoc = null;
				String mail1 = null;
				String mail2 = null;
				String tipoDireccion = null;
				String codigoPais = null;
				String region = null;
				String codProvincia = null;
				String codCanton = null;
				String ciudad = null;
				String codParroquia = null;
				String barrio = null;
				String direccionCalle1 = null;
				String direccionNumero = null;
				String direccionCalle2 = null;
				String direccionEdificio = null;
				String referencia = null;
				String tipoTelefono = null;
				String codTelefonoPais = null;
				String codArea = null;
				String telefono = null;
				String telefonoExtension = null;
				String hrIniCto = null;
				String hrFinCto = null;

				for (int x = 0; x < persona.getLength(); x++) {

					if (persona.item(x).getNodeName().equals("SECPER")) {
						secuencial = persona.item(x).getTextContent();
					}
					if (persona.item(x).getNodeName().equals("NRODOC")) {
						nroDoc = persona.item(x).getTextContent();
					}
					if (persona.item(x).getNodeName().equals("MAIL1")) {
						mail1 = persona.item(x).getTextContent();
					}
					if (persona.item(x).getNodeName().equals("MAIL2")) {
						mail2 = persona.item(x).getTextContent();
					}
					if (persona.item(x).getNodeName().equals("TPODIR")) {
						tipoDireccion = persona.item(x).getTextContent();
					}
					if (persona.item(x).getNodeName().equals("CODPAIS")) {
						codigoPais = persona.item(x).getTextContent();
					}
					if (persona.item(x).getNodeName().equals("REGION")) {
						region = persona.item(x).getTextContent();
					}
					if (persona.item(x).getNodeName().equals("CODPROV")) {
						codProvincia = persona.item(x).getTextContent();
					}
					if (persona.item(x).getNodeName().equals("CODCANTON")) {
						codCanton = persona.item(x).getTextContent();
					}
					if (persona.item(x).getNodeName().equals("CIUDAD")) {
						ciudad = persona.item(x).getTextContent();
					}
					if (persona.item(x).getNodeName().equals("CODPARR")) {
						codParroquia = persona.item(x).getTextContent();
					}
					if (persona.item(x).getNodeName().equals("BARRIO")) {
						barrio = persona.item(x).getTextContent();
					}
					if (persona.item(x).getNodeName().equals("DIRCALLE1")) {
						direccionCalle1 = persona.item(x).getTextContent();
					}
					if (persona.item(x).getNodeName().equals("DIRNRO")) {
						direccionNumero = persona.item(x).getTextContent();
					}
					if (persona.item(x).getNodeName().equals("DIRCALLE2")) {
						direccionCalle2 = persona.item(x).getTextContent();
					}
					if (persona.item(x).getNodeName().equals("DIREDIF")) {
						direccionEdificio = persona.item(x).getTextContent();
					}
					if (persona.item(x).getNodeName().equals("REFER")) {
						referencia = persona.item(x).getTextContent();
					}
					if (persona.item(x).getNodeName().equals("TPOTEL")) {
						tipoTelefono = persona.item(x).getTextContent();
					}
					if (persona.item(x).getNodeName().equals("CODTELPAIS")) {
						codTelefonoPais = persona.item(x).getTextContent();
					}
					if (persona.item(x).getNodeName().equals("CODAREA")) {
						codArea = persona.item(x).getTextContent();
					}
					if (persona.item(x).getNodeName().equals("TEL")) {
						telefono = persona.item(x).getTextContent();
					}
					if (persona.item(x).getNodeName().equals("TELEXT")) {
						telefonoExtension = persona.item(x).getTextContent();
					}
					if (persona.item(x).getNodeName().equals("HRINICTO")) {
						hrIniCto = persona.item(x).getTextContent();
					}
					if (persona.item(x).getNodeName().equals("HRFINCTO")) {
						hrFinCto = persona.item(x).getTextContent();
					}
				} // fin for datos de una persona

				System.out.println("secuencial:" + secuencial);

				if (secuencial == null || secuencial.trim().equals("")) {
					System.out.println("No envio secuencial, entonces busca por cedula " + nroDoc);
					List<String> cedulas = new ArrayList<String>();
					cedulas.add(nroDoc);
					Map<String, Integer> mapa = personaServicio.obtenerPersonaByDocumento(cedulas);
					if (mapa.size() > 0) {
						secuencial = mapa.get(nroDoc) + "";
					}
					System.out.println("secuencial encontrado:" + secuencial);
				}

				if (secuencial != null && !secuencial.trim().equals("")) {

					// entonces actualiza en db2

					// se obtiene de db2
					PersonaNatural pn = personaNaturalServicio
							.obtenerPersonaNaturalByPersonaParaActualizacionCrm(Integer.valueOf(secuencial));

					if (pn == null) {
						log.append("En el registro " + registro + " el SECPER " + secuencial
								+ " no se ha encontrado en la base de datos, ");
						res.anadirCantidadRegistrosRechazados();
						continue;
					}
					Persona pp = pn.getPersona();

					if (pp == null) {
						log.append("En el registro " + registro + " el SECPER " + secuencial
								+ " no tiene tabla Persona relacionado en la base de datos, ");
						res.anadirCantidadRegistrosRechazados();
						continue;
					}

					// mail1
					if (!ActualizacionPersonaUtil.analizarMail1(mail1, wsDatosPersonaServicio, log, registro, res,
							pp)) {
						continue;
					}

					// mail2
					if (!ActualizacionPersonaUtil.analizarMail2(mail2, wsDatosPersonaServicio, log, registro, res,
							pp)) {
						continue;
					}

					// direcciones
					if (!ActualizacionPersonaUtil.analizarDireccion(tipoDireccion, cantonServicio, paisServicio,
							codCanton, codTelefonoPais, ciudad, direccionCalle1, direccionCalle2, codArea, barrio,
							direccionEdificio, direccionNumero, referencia, tipoTelefono, telefono, telefonoExtension,
							log, registro, secuencial, res, pp)) {
						continue;
					}

					// prepara tabla telefono

					List<Telefono> guardarListaTel = new ArrayList<Telefono>();

					// para los moviles
					Collection<Telefono> telfSinDireccion = pp.getTelefonoSinDireccionCollection();

					Pais paisTelf = null;

					try {

						paisTelf = paisServicio.findByPk(Short.parseShort(codTelefonoPais));

					} catch (NumberFormatException e) {

						log.append("En el registro " + registro + " el SECPER " + secuencial
								+ " tiene un identificador de pais (telefono) incorrecto , ");
						res.anadirCantidadRegistrosRechazados();
						continue;
					}

					// celular oficina
					boolean encontroCelularTrabajo = false;
					for (Telefono telfAdicional : telfSinDireccion) {
						if (tipoDireccion.toLowerCase().equals("trabajo")
								&& tipoTelefono.toLowerCase().equals("celular")) {
							if (telfAdicional.getTipoTelefono().getCodTipoTelefono()
									.shortValue() == Constantes.TIPO_TELEFONO_MOVIL_OFICINA) {
								System.out.println("encuentra celular oficina...");
								telfAdicional.setCodArea(codArea);
								telfAdicional.setExtTelefono(telefonoExtension);
								telfAdicional.setNroTelefono(telefono);
								telfAdicional.setPais(paisTelf);
								guardarListaTel.add(telfAdicional);
								encontroCelularTrabajo = true;
							}
						}
					}
					if (!encontroCelularTrabajo && tipoDireccion.toLowerCase().equals("trabajo")
							&& tipoTelefono.toLowerCase().equals("celular")) {
						System.out.println("crea celular oficina...");
						Telefono t = new Telefono();
						t.setPrincipal(false);
						t.setCodArea(codArea);
						t.setPais(paisTelf);
						t.setEstado(EstadoEnum.ACTIVO.getCodigo());
						t.setExtTelefono(telefonoExtension);
						t.setNroTelefono(telefono);
						t.setTipoTelefono(new TipoTelefono(Constantes.TIPO_TELEFONO_MOVIL_OFICINA));
						t.setPersona(pp);
						guardarListaTel.add(t);
					}
					// celular personal
					boolean encontroCelularDomicilio = false;
					for (Telefono telfAdicional : telfSinDireccion) {
						if (tipoDireccion.toLowerCase().equals("domicilio")
								&& tipoTelefono.toLowerCase().equals("celular")) {
							if (telfAdicional.getTipoTelefono().getCodTipoTelefono()
									.shortValue() == Constantes.TIPO_TELEFONO_MOVIL_PERSONAL) {
								System.out.println("encuentra celular personal...");
								telfAdicional.setCodArea(codArea);
								telfAdicional.setExtTelefono(telefonoExtension);
								telfAdicional.setNroTelefono(telefono);
								telfAdicional.setPais(paisTelf);
								guardarListaTel.add(telfAdicional);
								encontroCelularDomicilio = true;
							}
						}
					}
					if (!encontroCelularDomicilio && tipoDireccion.toLowerCase().equals("domicilio")
							&& tipoTelefono.toLowerCase().equals("celular")) {
						System.out.println("crea celular personal...");
						Telefono t = new Telefono();
						t.setPrincipal(false);
						t.setCodArea(codArea);
						t.setPais(paisTelf);
						t.setEstado(EstadoEnum.ACTIVO.getCodigo());
						t.setExtTelefono(telefonoExtension);
						t.setNroTelefono(telefono);
						t.setTipoTelefono(new TipoTelefono(Constantes.TIPO_TELEFONO_MOVIL_PERSONAL));
						t.setPersona(pp);
						guardarListaTel.add(t);
					}

					// fin moviles

					// COMENTADO 11 - FEB - 2015
					Collection<Direccion> direcciones = pn.getPersona().getDireccionCollection();

					for (Direccion dir : direcciones) {
						if (dir.getActivo()) {
							if (dir.getDireccionTelefonoCollection() == null) {
								dir.setDireccionTelefonoCollection(new ArrayList<DireccionTelefono>());
							}
							for (DireccionTelefono dt : dir.getDireccionTelefonoCollection()) {
								if (dt.getTelefono().getActivo()) {
									guardarListaTel.add(dt.getTelefono());
								} else {
									if (dt.getTelefono().getSecTelefono() != null) {
										guardarListaTel.add(dt.getTelefono());
									}
								}
							}
						}
					}
					// FIN COMENTADO 11 - FEB - 2015

					pn.getPersona().setTelefonoCollection(guardarListaTel);

					if (hrIniCto != null && !hrIniCto.trim().equals("")) {

						if (hrIniCto.length() == 4) {
							hrIniCto = "0" + hrIniCto;
						}

						if (hrFinCto != null && !hrFinCto.trim().equals("")) {

							if (hrFinCto.length() == 4) {
								hrFinCto = "0" + hrFinCto;
							}

							try {

								if (hrIniCto.trim().length() != 5 || hrFinCto.trim().length() != 5) {
									log.append("En el registro " + registro + " el SECPER " + secuencial
											+ " tiene hora inicio u hora fin incorrecto, debe ser de 5 caracteres (se acepta ej 08:30 para 8 horas 30 minutos),  ");
									res.anadirCantidadRegistrosRechazados();
									continue;
								} else {
									pp.getContactoPreferidoTransient()
											.setHorarioInicio(ContactoPreferido.obtenerFormatoHoraShort(hrIniCto));
									pp.getContactoPreferidoTransient()
											.setHorarioFin(ContactoPreferido.obtenerFormatoHoraShort(hrFinCto));
								}

							} catch (NumberFormatException e) {
								log.append("En el registro " + registro + " el SECPER " + secuencial
										+ " tiene hora inicio Ou hora fin incorrecto (se acepta ej 08:30 para 8 horas 30 minutos),  ");
								res.anadirCantidadRegistrosRechazados();
								continue;
							} catch (ParseException e) {
								log.append("En el registro " + registro + " el SECPER " + secuencial
										+ " tiene hora inicio Ou hora fin incorrecto (se acepta ej 08:30 para 8 horas 30 minutos),  ");
								res.anadirCantidadRegistrosRechazados();
								continue;
							}
						}

					}

					personaNaturalServicio.actualizarPersonaNaturalCrm(pn, "crm", getIpRemoto());

					res.anadirCantidadRegistrosProcesados();

				} else {

					// entonces igresa uno nuevo en db2, realizar las
					// respectivas
					// validaciones, pero en el alcance no estaba esto de
					// ingresar o no?
					res.anadirCantidadRegistrosRechazados();
					log.append("En el registro " + registro + " no se ha enviado SECPER, ");
				}

			} // end if si es persona

		} // fin for de personas

		res.setCantidadRegistrosAceptados(res.getCantidadRegistrosProcesados() + res.getCantidadRegistrosRechazados());

		respuesta.append("<STREAM>");
		respuesta.append("<HEADER>");
		respuesta.append("<ITRANSACCION>" + numeroTransaccion + "</ITRANSACCION>");
		respuesta.append("</HEADER>");
		respuesta.append("<DATOSPERSONAS>");
		respuesta.append("<PROCESADOS>" + res.getCantidadRegistrosProcesados() + "</PROCESADOS>");
		respuesta.append("<ACEPTADOS>" + res.getCantidadRegistrosAceptados() + "</ACEPTADOS>");
		respuesta.append("<RECHAZADOS>" + res.getCantidadRegistrosRechazados() + "</RECHAZADOS>");
		respuesta.append("<LOG>" + log + "</LOG>");
		respuesta.append("</DATOSPERSONAS>");

		if (res.getCantidadRegistrosRechazados() > 0) {
			respuesta.append("<ERROR><CODIGO>1</CODIGO><MENSAJE>" + log + "</MENSAJE></ERROR></STREAM>");
		} else {
			anadirNoError(respuesta);
			respuesta.append("</STREAM>");
		}

	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public String ConsultarEstadoCuenta(String xml) {
		StringBuilder respuesta = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");

		String tagVacio = "<CUENTAS></CUENTAS>";
		String numeroTransaccion = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		dbf.setNamespaceAware(true);
		dbf.setIgnoringElementContentWhitespace(true);
		dbf.setIgnoringComments(true);
		dbf.setExpandEntityReferences(false);
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(new InputSource(new StringReader(xml)));

			Node stream = doc.getFirstChild();
			Node header = stream.getFirstChild();
			Node itransaccion = header.getFirstChild();
			numeroTransaccion = itransaccion.getTextContent();

			final Integer RAMO59 = 59;
			final Integer RAMO55 = 55;
			final Integer RAMO58 = 58;

			// parametros
			String numPol = null;
			String codigoSucursal = null;
			String ramo = null;
			String fechaDesde = "";
			String fechaHasta = "";

			NodeList lista = stream.getChildNodes();
			for (int i = 0; i < lista.getLength(); i++) {
				if (lista.item(i).getNodeName().equals("PARAMETROS")) {
					Node parametros = lista.item(i);
					NodeList listaParametros = parametros.getChildNodes();

					for (int x = 0; x < listaParametros.getLength(); x++) {
						if (listaParametros.item(x).getNodeName().equals("NRO_POLIZA")) {
							numPol = listaParametros.item(x).getTextContent();
						}
						if (listaParametros.item(x).getNodeName().equals("SUCUR")) {
							codigoSucursal = listaParametros.item(x).getTextContent();
						}
						if (listaParametros.item(x).getNodeName().equals("RAMO")) {
							ramo = listaParametros.item(x).getTextContent();
						}
					}
				}
			}

			BigDecimal numeroPoliza = new BigDecimal(numPol);
			Integer sucursal = Integer.parseInt(codigoSucursal);
			Integer ramoParam = Integer.parseInt(ramo);

			List<ListaEstadoCuentaRs> listaR = listaEstadoCuentaSp.listaEstadoCuentaSp(sucursal, ramoParam,
					numeroPoliza, fechaDesde, fechaHasta);

			respuesta.append("<STREAM>");
			respuesta.append("<HEADER><ITRANSACCION>" + numeroTransaccion + "</ITRANSACCION></HEADER>");
			respuesta.append("<CUENTAS>");
			for (ListaEstadoCuentaRs rs : listaR) {
				respuesta.append("<CUENTA>");
				if (rs.getMoneda() != null) {
					respuesta.append("<txt_desc_moneda>" + rs.getMoneda() + "</txt_desc_moneda>");
				} else {
					respuesta.append("<txt_desc_moneda></txt_desc_moneda>");
				}
				if (rs.getNroPol() != null) {
					respuesta.append("<nro_pol>" + rs.getNroPol() + "</nro_pol>");
				} else {
					respuesta.append("<nro_pol></nro_pol>");
				}
				if (rs.getTxtAsegurado() != null) {
					respuesta.append("<txt_asegurado>" + rs.getTxtAsegurado() + "</txt_asegurado>");
				} else {
					respuesta.append("<txt_asegurado></txt_asegurado>");
				}
				if (rs.getTxtContratante() != null) {
					respuesta.append("<txt_contratante>" + rs.getTxtContratante() + "</txt_contratante>");
				} else {
					respuesta.append("<txt_contratante></txt_contratante>");
				}
				if (rs.getTxtDireccion() != null) {
					respuesta.append("<txt_direccion>" + rs.getTxtDireccion() + "</txt_direccion>");
				} else {
					respuesta.append("<txt_direccion></txt_direccion>");
				}
				if (rs.getTxtAgente() != null) {
					respuesta.append("<txt_agente>" + rs.getTxtAgente() + "</txt_agente>");
				} else {
					respuesta.append("<txt_agente></txt_agente>");
				}
				if (rs.getFechaVigenDesde() != null) {
					respuesta.append("<fec_vig_desde>" + rs.getFechaVigenDesde() + "</fec_vig_desde>");
				} else {
					respuesta.append("<fec_vig_desde></fec_vig_desde>");
				}
				if (rs.getEdadIniVig() != null) {
					respuesta.append("<edad_ini_vig>" + rs.getEdadIniVig() + "</edad_ini_vig>");
				} else {
					respuesta.append("<edad_ini_vig></edad_ini_vig>");
				}
				if (rs.getFecNac() != null) {
					respuesta.append("<fec_nac>" + rs.getFecNac() + "</fec_nac>");
				} else {
					respuesta.append("<fec_nac></fec_nac>");
				}
				if (rs.getTxtSexo() != null) {
					respuesta.append("<txt_sexo>" + rs.getTxtSexo() + "</txt_sexo>");
				} else {
					respuesta.append("<txt_sexo></txt_sexo>");
				}
				if (rs.getTxtDescCond() != null) {
					respuesta.append("<txt_desc_cond>" + rs.getTxtDescCond() + "</txt_desc_cond>");
				} else {
					respuesta.append("<txt_desc_cond></txt_desc_cond>");
				}
				if (rs.getTxtProducto() != null) {
					respuesta.append("<txt_producto>" + rs.getTxtProducto() + "</txt_producto>");
				} else {
					respuesta.append("<txt_producto></txt_producto>");
				}
				if (rs.getImpSumaAseg() != null) {
					respuesta.append("<imp_suma_aseg>" + rs.getImpSumaAseg() + "</imp_suma_aseg>");
				} else {
					respuesta.append("<imp_suma_aseg></imp_suma_aseg>");
				}
				if (rs.getTxtOpcion() != null) {
					respuesta.append("<txt_opcion>" + rs.getTxtOpcion() + "</txt_opcion>");
				} else {
					respuesta.append("<txt_opcion></txt_opcion>");
				}
				if (rs.getTxtFormaPago() != null) {
					respuesta.append("<txt_forma_pago>" + rs.getTxtFormaPago() + "</txt_forma_pago>");
				} else {
					respuesta.append("<txt_forma_pago></txt_forma_pago>");
				}
				if (rs.getTxtConductoPago() != null) {
					respuesta.append("<txt_conducto_pago>" + rs.getTxtConductoPago() + "</txt_conducto_pago>");
				} else {
					respuesta.append("<txt_conducto_pago></txt_conducto_pago>");
				}
				if (rs.getImpSaldoInicial() != null) {
					respuesta.append("<imp_saldo_inicial>" + rs.getImpSaldoInicial() + "</imp_saldo_inicial>");
				} else {
					respuesta.append("<imp_saldo_inicial></imp_saldo_inicial>");
				}
				if (rs.getImpSaldoFinal() != null) {
					respuesta.append("<imp_saldo_final>" + rs.getImpSaldoFinal() + "</imp_saldo_final>");
				} else {
					respuesta.append("<imp_saldo_final></imp_saldo_final>");
				}
				if (rs.getImpSaldoPrestamos() != null) {
					respuesta.append("<imp_saldo_prestamo>" + rs.getImpSaldoPrestamos() + "</imp_saldo_prestamo>");
				} else {
					respuesta.append("<imp_saldo_prestamo></imp_saldo_prestamo>");
				}
				if (rs.getImpRescateCobBasica() != null) {
					respuesta.append(
							"<imp_rescate_cob_basica>" + rs.getImpRescateCobBasica() + "</imp_rescate_cob_basica>");
				} else {
					respuesta.append("<imp_rescate_cob_basica></imp_rescate_cob_basica>");
				}
				if (rs.getImpRescateTotal() != null) {
					respuesta.append("<imp_rescate_total>" + rs.getImpRescateTotal() + "</imp_rescate_total>");
				} else {
					respuesta.append("<imp_rescate_total></imp_rescate_total>");
				}
				if (rs.getImpBF() != null) {
					respuesta.append("<imp_BF>" + rs.getImpBF() + "</imp_BF>");
				} else {
					respuesta.append("<imp_BF></imp_BF>");
				}
				if (rs.getFecDesde() != null) {
					respuesta.append("<fec_desde>" + rs.getFecDesde() + "</fec_desde>");
				} else {
					respuesta.append("<fec_desde></fec_desde>");
				}
				if (rs.getFecHasta() != null) {
					respuesta.append("<fec_hasta>" + rs.getFecHasta() + "</fec_hasta>");
				} else {
					respuesta.append("<fec_hasta></fec_hasta>");
				}
				if (rs.getAaaa_proceso() != null) {
					respuesta.append("<aaaa_proceso>" + rs.getAaaa_proceso() + "</aaaa_proceso>");
				} else {
					respuesta.append("<aaaa_proceso></aaaa_proceso>");
				}
				if (rs.getMm_proceso() != null) {
					respuesta.append("<mm_proceso>" + rs.getMm_proceso() + "</mm_proceso>");
				} else {
					respuesta.append("<mm_proceso></mm_proceso>");
				}
				if (rs.getImpReserva() != null) {
					respuesta.append("<imp_reserva>" + rs.getImpReserva() + "</imp_reserva>");
				} else {
					respuesta.append("<imp_reserva></imp_reserva>");
				}
				if (rs.getImpGastos() != null) {
					respuesta.append("<imp_gastos>" + rs.getImpGastos() + "</imp_gastos>");
				} else {
					respuesta.append("<imp_gastos></imp_gastos>");
				}
				if (rs.getImpDM() != null) {
					respuesta.append("<imp_DM>" + rs.getImpDM() + "</imp_DM>");
				} else {
					respuesta.append("<imp_DM></imp_DM>");
				}
				if (rs.getImpIE() != null) {
					respuesta.append("<imp_ie>" + rs.getImpIE() + "</imp_ie>");
				} else {
					respuesta.append("<imp_ie></imp_ie>");
				}
				if (rs.getPjeTasaInteres() != null) {
					respuesta.append("<pje_tasa_interes>" + rs.getPjeTasaInteres() + "</pje_tasa_interes>");
				} else {
					respuesta.append("<pje_tasa_interes></pje_tasa_interes>");
				}
				if (rs.getImpRetiro() != null) {
					respuesta.append("<imp_retiro>" + rs.getImpRetiro() + "</imp_retiro>");
				} else {
					respuesta.append("<imp_retiro></imp_retiro>");
				}
				if (rs.getImpAjustes() != null) {
					respuesta.append("<imp_ajustes>" + rs.getImpAjustes() + "</imp_ajustes>");
				} else {
					respuesta.append("<imp_ajustes></imp_ajustes>");
				}
				if (rs.getImpDisponible() != null) {
					respuesta.append("<imp_disponible>" + rs.getImpDisponible() + "</imp_disponible>");
				} else {
					respuesta.append("<imp_disponible></imp_disponible>");
				}
				if (rs.getImpPrestamo() != null) {
					respuesta.append("<imp_prestamo>" + rs.getImpPrestamo() + "</imp_prestamo>");
				} else {
					respuesta.append("<imp_prestamo></imp_prestamo>");
				}

				respuesta.append("</CUENTA>");

			}
			respuesta.append("</CUENTAS>");
			anadirNoError(respuesta);
			respuesta.append("</STREAM>");
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			respuesta = construirXmlError(e.getLocalizedMessage(), numeroTransaccion, tagVacio);
		} catch (SAXException e) {
			e.printStackTrace();
			respuesta.append("<STREAM><ERROR><CODIGO>1</CODIGO><MENSAJE>" + e.getLocalizedMessage()
					+ "</MENSAJE></ERROR></STREAM>");
		} catch (IOException e) {
			e.printStackTrace();
			respuesta.append("<STREAM><ERROR><CODIGO>1</CODIGO><MENSAJE>" + e.getLocalizedMessage()
					+ "</MENSAJE></ERROR></STREAM>");
		} catch (SQLException e) {
			e.printStackTrace();
			respuesta.append("<STREAM><ERROR><CODIGO>1</CODIGO><MENSAJE>" + e.getLocalizedMessage()
					+ "</MENSAJE></ERROR></STREAM>");
		}

		return respuesta.toString();
	}

	private Integer conversionTipoDocumentoString(String tipo) {
		if ("CEDULA DE IDENTIDAD".equals(tipo)) {
			return 1;
		}
		if ("REGISTRO UNICO DE CONTRIBUYENTE".equals(tipo)) {
			return 2;
		}
		if ("PASAPORTE".equals(tipo)) {
			return 3;
		}
		if ("OTRO".equals(tipo)) {
			return 5;
		}
		System.out.println("WARN: No existe tipo documento: " + tipo);
		return null;
	}

	private String conversionTipoDocumentoInteger(Integer tipo) {
		if (tipo.equals(1)) {
			return "CEDULA DE IDENTIDAD";
		}
		if (tipo.equals(2)) {
			return "REGISTRO UNICO DE CONTRIBUYENTE";
		}
		if (tipo.equals(3)) {
			return "PASAPORTE";
		}
		if (tipo.equals(5)) {
			return "OTRO";
		}
		System.out.println("WARN: No existe tipo documento (Integer): " + tipo);
		return null;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public String ConsultarEmpleados() {
		StringBuilder respuesta = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");

		String tagVacio = "<EMPLEADOS></EMPLEADOS>";
		String numeroTransaccion = "006";
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		dbf.setNamespaceAware(true);
		dbf.setIgnoringElementContentWhitespace(true);
		dbf.setIgnoringComments(true);
		dbf.setExpandEntityReferences(false);
		try {

			// sin parametros
			List<EmpleadosRs> listaR = empleadosSp.consultaEmpleadosSp();

			respuesta.append("<STREAM>");
			respuesta.append("<HEADER><ITRANSACCION>" + numeroTransaccion + "</ITRANSACCION></HEADER>");

			respuesta.append("<EMPLEADOS>");
			for (EmpleadosRs listaRs : listaR) {
				String nombre = listaRs.getNombreCompleto();
				respuesta.append("<EMPLEADO>");
				respuesta.append("<NOM>" + nombre.split("/")[2] + "</NOM>");
				respuesta.append("<APEPAT>" + nombre.split("/")[0] + "</APEPAT>");
				respuesta.append("<APEMAT>" + nombre.split("/")[1] + "</APEMAT>");
				respuesta.append("<COD>" + listaRs.getCodigo() + "</COD>");
				respuesta.append("<CARGO>" + listaRs.getCargo() + "</CARGO>");
				respuesta.append("<DEPTO>" + listaRs.getDepartamento() + "</DEPTO>");
				respuesta.append("<GEN>" + listaRs.getGen() + "</GEN>");
				respuesta.append("<FINI>" + listaRs.getFechaIngreso() + "</FINI>");
				respuesta.append("<FFIN>" + listaRs.getFechaSalida() + "</FFIN>");
				respuesta.append("<FNAC>" + listaRs.getFechaNacimiento() + "</FNAC>");
				respuesta.append("<JEFE>" + listaRs.getJefe() + "</JEFE>");
				respuesta.append("<ACT>" + listaRs.getAct() + "</ACT>");
				respuesta.append("<USR>" + listaRs.getUser() + "</USR>");
				respuesta.append("<TEL>" + listaRs.getTelefono() + "</TEL>");
				respuesta.append("<MOVIL>" + listaRs.getMovil() + "</MOVIL>");
				respuesta.append("<EMAIL>" + listaRs.getEmail() + "</EMAIL>");

				respuesta.append("</EMPLEADO>");

			}

			respuesta.append("</EMPLEADOS>");
			anadirNoError(respuesta);
			respuesta.append("</STREAM>");

		} catch (SQLException e) {
			e.printStackTrace();
			respuesta = construirXmlError(e.getLocalizedMessage(), numeroTransaccion, tagVacio);
		}

		return respuesta.toString();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public String ConsultarCompromisosPagos(String xml) {
		StringBuilder respuesta = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");

		String tagVacio = "<PAGO></PAGO>";
		String numeroTransaccion = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		dbf.setNamespaceAware(true);
		dbf.setIgnoringElementContentWhitespace(true);
		dbf.setIgnoringComments(true);
		dbf.setExpandEntityReferences(false);
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(new InputSource(new StringReader(xml)));

			Node stream = doc.getFirstChild();
			Node header = stream.getFirstChild();
			Node itransaccion = header.getFirstChild();
			numeroTransaccion = itransaccion.getTextContent();

			// parametros
			String numPol = null;
			String codigoSucursal = null;
			String ramo = null;

			NodeList lista = stream.getChildNodes();
			for (int i = 0; i < lista.getLength(); i++) {
				if (lista.item(i).getNodeName().equals("PARAMETROS")) {
					Node parametros = lista.item(i);
					NodeList listaParametros = parametros.getChildNodes();

					for (int x = 0; x < listaParametros.getLength(); x++) {
						if (listaParametros.item(x).getNodeName().equals("NROPOL")) {
							numPol = listaParametros.item(x).getTextContent();
						}
						if (listaParametros.item(x).getNodeName().equals("SUCUR")) {
							codigoSucursal = listaParametros.item(x).getTextContent();
						}
						if (listaParametros.item(x).getNodeName().equals("RAMO")) {
							ramo = listaParametros.item(x).getTextContent();
						}

					}
				}
			}

			Integer sucursal = Integer.parseInt(codigoSucursal);
			Integer ramoParam = Integer.parseInt(ramo);

			List<CompromisosPagosRs> listaR = compromisosPagosSp.compromisosPagosSp(numPol, sucursal, ramoParam);

			respuesta.append("<STREAM>");
			respuesta.append("<HEADER><ITRANSACCION>" + numeroTransaccion + "</ITRANSACCION></HEADER>");

			respuesta.append("<PAGOS>");
			for (CompromisosPagosRs rs : listaR) {
				respuesta.append("<PAGO>");
				respuesta.append("<NROPOL>" + rs.getPoliza() + "</NROPOL>");
				respuesta.append("<FPAGO>" + rs.getFechaPago() + "</FPAGO>");
				respuesta.append("<MONTO>" + rs.getMonto() + "</MONTO>");
				respuesta.append("</PAGO>");
			}
			respuesta.append("</PAGOS>");
			anadirNoError(respuesta);
			respuesta.append("</STREAM>");
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			respuesta = construirXmlError(e.getLocalizedMessage(), numeroTransaccion, tagVacio);
		} catch (SAXException e) {
			e.printStackTrace();
			respuesta.append("<STREAM><ERROR><CODIGO>1</CODIGO><MENSAJE>" + e.getLocalizedMessage()
					+ "</MENSAJE></ERROR></STREAM>");
		} catch (IOException e) {
			e.printStackTrace();
			respuesta.append("<STREAM><ERROR><CODIGO>1</CODIGO><MENSAJE>" + e.getLocalizedMessage()
					+ "</MENSAJE></ERROR></STREAM>");
		} catch (SQLException e) {
			e.printStackTrace();
			respuesta.append("<STREAM><ERROR><CODIGO>1</CODIGO><MENSAJE>" + e.getLocalizedMessage()
					+ "</MENSAJE></ERROR></STREAM>");
		}

		return respuesta.toString();
	}
}
