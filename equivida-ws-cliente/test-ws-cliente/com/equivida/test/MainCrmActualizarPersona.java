/*
 * @Copyright 2014. Saviasoft.
 * 
 */
package com.equivida.test;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.tempuri.ActualizacionesRegularesLocator;
import org.tempuri.ActualizacionesRegularesSoap;

public class MainCrmActualizarPersona {

	public static void main(String[] args) throws ServiceException,
			RemoteException {

		String xml = "<PERSONAS><PERS><TPODOC>C</TPODOC><NRODOC>1716502867</NRODOC>"
				+ "<SECPER>2312512</SECPER><NOM1>KATHERINE</NOM1><NOM2>JOHANNA</NOM2>"
				+ "<APEPAT>FLORES</APEPAT><APEMAT>MINA</APEMAT><FNAC>1984-12-28</FNAC>"
				+ "<ECIVIL>Soltero</ECIVIL><GEN>F</GEN><CODPROF>24</CODPROF>"
				+ "<NEDUC>A</NEDUC><CODOCUP>521</CODOCUP><CYGNOM1>null</CYGNOM1>"
				+ "<CYGNOM2>null</CYGNOM2><CYGAPEPAT>null</CYGAPEPAT>"
				+ "<CYGAPEMAT>null</CYGAPEMAT><NROHJOS>0</NROHJOS><HRINICTO>1800</HRINICTO>"
				+ "<HRFINCTO>1900</HRFINCTO><RELLAB>null</RELLAB><EMPRTRAB>null</EMPRTRAB>"
				+ "<CARGO>null</CARGO><MAIL1>kathytoflores@gmail.com</MAIL1><MAIL2>null</MAIL2>"
				+ "<TPODIR>DOMICILIO</TPODIR><CODPAIS>56</CODPAIS><REGION></REGION>"
				+ "<CODPROV>17</CODPROV><CODCANTON>178</CODCANTON><CIUDAD>Quito</CIUDAD>"
				+ "<CODPARR></CODPARR><BARRIO>LA VICENTINA BAJA</BARRIO>"
				+ "<DIRCALLE1>VERDE CRUZ</DIRCALLE1><DIRNRO>E17121</DIRNRO>"
				+ "<DIRCALLE2>N. VELA</DIRCALLE2><DIREDIF></DIREDIF><LONG>-78.48429108</LONG>"
				+ "<LAT>-.22007300</LAT><REFER>null</REFER><TPOTEL>DOMICILIO</TPOTEL>"
				+ "<CODTELPAIS>56</CODTELPAIS><CODAREA>09</CODAREA><TEL>9973266</TEL>"
				+ "<TELEXT>null</TELEXT><PCNOM>KATHERINE FLORES</PCNOM><PCTEL></PCTEL>"
				+ "<PCEMAIL>null</PCEMAIL><INGR>1870.00</INGR><SECBRK>1110</SECBRK>"
				+ "<SECPERBRK>1110</SECPERBRK><CANALVTA></CANALVTA><CODASECOM></CODASECOM>"
				+ "<CODASEPOST></CODASEPOST><CODDCTOR></CODDCTOR><CODASIST></CODASIST><SEGM></SEGM>"
				+ "<PEP></PEP><INFOSIN>No</INFOSIN><NVLRIESGO>No</NVLRIESGO><BENEXT> </BENEXT>"
				+ "<CLIDESDE>29/10/2012</CLIDESDE><COMENT></COMENT><RESERV></RESERV>"
				+ "<CLIIND>False</CLIIND><CLIASEG>False</CLIASEG><CTOEMP>False</CTOEMP>"
				+ "<CTOBKR>False</CTOBKR><INACT>False</INACT><PNVALOR>0.0</PNVALOR>"
				+ "<PNCAPAC>0.0</PNCAPAC></PERS></PERSONAS>";

		System.out.println(xml);
		
		ActualizacionesRegularesLocator locator = new ActualizacionesRegularesLocator();
		ActualizacionesRegularesSoap servicio = locator
				.getActualizacionesRegularesSoap();

		String respuesta = servicio.consultarPersonas(xml);
		System.out.println("RESPUESTA:");
		System.out.println(respuesta);

	}

}
