/**
 * 
 */
package com.equivida.test;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.rpc.holders.IntHolder;
import javax.xml.rpc.holders.StringHolder;

import localhost.BusquedaIDVerificationResponseBusquedaIDVerificationResult;
import localhost.WsRCS_OFACLocator;
import localhost.WsRCS_OFACSoap;
import localhost.ResultadosRevisionOFAC_xsd.ResultadosRevisionOFACResultadosOFAC;

import org.apache.axis.message.MessageElement;

/**
 * @author daniel
 * 
 */
public class MainRiesgo {

	public static void main(String[] args) throws Exception {

		WsRCS_OFACLocator wsRCS_OFACLocator = new WsRCS_OFACLocator();

		URL url = new URL("http://10.10.52.86/RCS%20WS%20OFAC/service.asmx");

		System.out.println("url:"+url);
		
		WsRCS_OFACSoap service = wsRCS_OFACLocator.getwsRCS_OFACSoap(url);

		String licenciaEmpresa = "E777";
		String tipoOrganizacion = "I";
		String firstName = "TERESA";// RAUL
		// String firstName = "JOSE";
		// String firstName = "JORGE ANIBAL";
		String middleName = "ELIZABETH";
		// String lastName = "GARCIA SUAREZ";
		// String lastName = "BOLANOS";
		String lastName = "POZO VITERI";// REYES TORREs
		int tipoIdentificacion = 1;
		String identificacion = "1710126267";
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
		// String rutaLog = "C:\\Bridger\\COM\\logs\\";
		int proveedorIDV = 4;
		String nombreLog = "LogErrorWS_OFAC.txt";
		String pathProveedorIDV = "C:\\Bridger\\BridgerDataFiles\\";
		int identityVerificationSetupOption = 0;
		String metodoBusqueda = "AUTO";
		StringHolder primaryKey = new StringHolder();
		StringHolder descripcionError = new StringHolder();
		IntHolder codError = new IntHolder(1);

		BusquedaIDVerificationResponseBusquedaIDVerificationResult result = service
				.busquedaIDVerification(licenciaEmpresa, tipoOrganizacion,
						firstName, middleName, lastName, tipoIdentificacion,
						identificacion, direccion, ciudad, estado,
						codigoPostal, pais, fechaNacimiento, generacion,
						telfCasa, telfTrabajo, issuingCountry, expDate,
						idPolitica, userName, perfilUsuario, valoresDefault,
						rutaSetupDB, rutaReportes, rutaLog, nombreLog,
						proveedorIDV, pathProveedorIDV,
						identityVerificationSetupOption, metodoBusqueda,
						primaryKey, codError, descripcionError);

		System.out.println("OK");
		MessageElement[] messages = result.get_any();
		System.out.println("messages.length:" + messages.length);

		// System.out.println(messages[0].getAsString());// encabezado xml
		// System.out.println(messages[1].getAsString());// contenido xml
		// el contenido xml es como
		// <diffgram>
		// <ResultadosRevisionOFAC>
		// <ResultadosOFAC>
		// </ResultadosOFAC>
		// <ResultadosOFAC>
		// </ResultadosOFAC>
		// </ResultadosRevisionOFAC>
		// </diffgram>

		MessageElement diffgram = messages[1];
		System.out.println("-->" + diffgram.getName());

		List<ResultadosRevisionOFACResultadosOFAC> listR = new ArrayList<ResultadosRevisionOFACResultadosOFAC>();

		Iterator itDiff = diffgram.getChildElements();
		while (itDiff.hasNext()) {
			MessageElement resultadosRevisionOFACME = (MessageElement) itDiff
					.next();

			System.out.println("resultadosRevisionOFACME:"
					+ resultadosRevisionOFACME.getAsString());

			Iterator itRrofac = resultadosRevisionOFACME.getChildElements();
			while (itRrofac.hasNext()) {
				MessageElement me = (MessageElement) itRrofac.next();
				// System.out.println("\t" + me.getName());
				// ResultadosRevisionOFAC resultadosRevisionOFAC1 =
				// (ResultadosRevisionOFAC) resultadosRevisionME1
				// .getValueAsType(new javax.xml.namespace.QName(
				// "http://localhost/ResultadosRevisionOFAC.xsd",
				// ">ResultadosRevisionOFAC"));
				ResultadosRevisionOFACResultadosOFAC rofac = (ResultadosRevisionOFACResultadosOFAC) me
						.getValueAsType(new javax.xml.namespace.QName(
								"http://localhost/ResultadosRevisionOFAC.xsd",
								">>ResultadosRevisionOFAC>ResultadosOFAC"));

				System.out.println(rofac.getBestName() + " >> SCORE: "
						+ rofac.getScore() + " file:" + rofac.getFile());
				System.out.println(rofac.getListing());
				listR.add(rofac);
			}
		}

		System.out.println("LISTA SIZE:" + listR.size());

		// System.out.println(resultadosRevisionME.getName());
		// Iterator it = resultadosRevisionME.getChildElements();
		// s.s
		// while (it.hasNext()) {
		// MessageElement me2 = (MessageElement) it.next();
		// System.out.println("Name = " + me2.getName());
		// }
	}
}
