package com.equivida.databook.client.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import com.equivida.databook.client.DatabookService;
import com.equivida.databook.exception.DatabookException;
import com.equivida.databook.model.Registros;
import com.equivida.databook.model.Registros.Civil;
import com.equivida.databook.model.RegistrosEntity;

/**
 * 
 * @author juan
 *
 */
public class DatabookServiceImpl implements DatabookService {

	// URI de ejemplo
	// private static final String URL =
	// "http://playavip.com.ec/equividawebservices.php?ced=${NO_CEDULA_CONSULTA}&usr=EQUIVIDA";

	private String uri;
	private String cedulaConsulta;
	private String usuarioConsume;
	private String uriFinal;

	/**
	 * Constructor unico.
	 * 
	 * @param uri
	 * @param cedulaConsulta
	 * @param usuarioConsume
	 * @throws DatabookException
	 */
	public DatabookServiceImpl(String uri, String cedulaConsulta, String usuarioConsume) throws DatabookException {
		this.uri = uri;
		this.cedulaConsulta = cedulaConsulta;
		this.usuarioConsume = usuarioConsume;

		// Se valida que se envien todos los campos
		validaParametros();

		// Crea la URL final
		init();
	}

	private void init() {
		// this.uriFinal =
		// this.uri.concat("?ced=").concat(this.cedulaConsulta).concat("&usr=").concat(this.usuarioConsume);

		this.uriFinal = this.uri.concat(this.cedulaConsulta);
		// http://10.10.43.21:8080/cu-rest/api/dataBook/172139711
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.databook.client.DatabookService#consultaDatabook(java.lang.
	 * String)
	 */
	@Override
	public Registros consultaDatabook() throws DatabookException {
		return consume();
	}

	/**
	 * Consume el SW REST.
	 * 
	 * @param noCedula
	 * @return
	 * @throws DatabookException
	 */
	private Registros consume() throws DatabookException {
		try {
			URL url = new URL(uriFinal);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/xml");

			JAXBContext jc = JAXBContext.newInstance(RegistrosEntity.class);
			InputStream xml = connection.getInputStream();
			RegistrosEntity registrosEntity = (RegistrosEntity) jc.createUnmarshaller().unmarshal(xml);
			/*
			 * if (connection.getResponseCode() != 200) { throw new
			 * RuntimeException("Failed : HTTP Error code : " +
			 * connection.getResponseCode()); }
			 */
			/*
			 * InputStreamReader in = new InputStreamReader(connection.getInputStream());
			 * BufferedReader br = new BufferedReader(in); String output; while ((output =
			 * br.readLine()) != null) { System.out.println(output); }
			 */
			// Registros registros = (Registros) jc.createUnmarshaller().unmarshal(xml);
			connection.disconnect();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * URL url = new URL(uriFinal); HttpURLConnection connection =
		 * (HttpURLConnection) url.openConnection(); connection.setRequestMethod("GET");
		 * connection.setRequestProperty("Accept", "application/xml"); JAXBContext jc =
		 * JAXBContext.newInstance(Registros.class); InputStream xml =
		 * connection.getInputStream(); Registros registros = (Registros)
		 * jc.createUnmarshaller().unmarshal(xml); connection.disconnect(); if
		 * (registros.getCivil().getCedula() == null ||
		 * registros.getCivil().getCedula().trim().length() <= 0) { throw new
		 * DatabookException(
		 * "No se encuentra datos con identificacion: ".concat(cedulaConsulta).
		 * concat(" en Databook")); }
		 */
		Registros registros = new Registros();
		Civil objcivil = new Civil();
		objcivil.setCedula("1721397113");
		registros.setCivil(objcivil);

		return registros;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.equivida.databook.client.DatabookService#consultaDatabookRegistrosEntity(
	 * )
	 */
	@Override
	public RegistrosEntity consultaDatabookRegistrosEntity() throws DatabookException {
		return consumeRegistrosEntity();
	}

	/**
	 * Consume el SW REST.
	 * 
	 * @param noCedula
	 * @return RegistrosEntity
	 * @throws DatabookException
	 */
	private RegistrosEntity consumeRegistrosEntity() throws DatabookException {
		RegistrosEntity registrosEntity = null;
		try {
			URL url = new URL(uriFinal);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/xml");

			JAXBContext jc = JAXBContext.newInstance(RegistrosEntity.class);
			InputStream xml = connection.getInputStream();

			if (connection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP Error code : " + connection.getResponseCode());
			}

			registrosEntity = (RegistrosEntity) jc.createUnmarshaller().unmarshal(xml);

			/*
			 * InputStreamReader in = new InputStreamReader(connection.getInputStream());
			 * BufferedReader br = new BufferedReader(in); String output; while ((output =
			 * br.readLine()) != null) { System.out.println(output); }
			 */

			if (!VerificarStringVacio(registrosEntity.getConyuge().getError())) {
				registrosEntity.setConyuge(null);
			}

			connection.disconnect();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return registrosEntity;
	}

	/**
	 * Valida que se envien todos los campos.
	 * 
	 * @throws DatabookException
	 */
	private void validaParametros() throws DatabookException {
		if (this.uri == null || this.uri.length() <= 0) {
			throw new DatabookException("Se requiere URI");
		}

		if (this.usuarioConsume == null || this.usuarioConsume.length() <= 0) {
			throw new DatabookException("Se requiere Usuario que consume el WS");
		}

		if (this.cedulaConsulta == null || this.cedulaConsulta.length() <= 0) {
			throw new DatabookException("Se requiere Cedula a Consultar");
		}
	}

	public boolean VerificarStringVacio(String texto) {
		if (texto.trim().isEmpty() || texto == null)
			return true;
		return false;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getCedulaConsulta() {
		return cedulaConsulta;
	}

	public void setCedulaConsulta(String cedulaConsulta) {
		this.cedulaConsulta = cedulaConsulta;
	}

	public String getUsuarioConsume() {
		return usuarioConsume;
	}

	public void setUsuarioConsume(String usuarioConsume) {
		this.usuarioConsume = usuarioConsume;
	}

	public String getUriFinal() {
		return uriFinal;
	}

	public void setUriFinal(String uriFinal) {
		this.uriFinal = uriFinal;
	}

}
