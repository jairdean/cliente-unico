package com.equivida.ws.producer;

import javax.ejb.Local;

/**
 * Interfaz que define los metodos de consulta a los ambientes: cliente unico,
 * smartdata, databook.
 * 
 * @author juan
 *
 */
@Local
public interface ConsultaGenericaServicio {
	/**
	 * Consulta persona en Cliente unico por noDocumento.
	 * 
	 * @param noDocumento
	 * @return
	 */
	com.equivida.modelo.PersonaNatural consultaPorDocumentoClienteUnico(
			String noDocumento);

	/**
	 * Consulta persona en smartdata por noDocumento.
	 * 
	 * @param noDocumento
	 * @return
	 */
	com.equivida.smartdata.model.PersonaSd consultaPorDocumentoSmartdata(
			String noDocumento);

	/**
	 * Consulta persona en databook por noDocumento.
	 * 
	 * @param noDocumento
	 * @return
	 */
	com.equivida.smartdata.model.PersonaSd consultaPorDocumentoDataBook(
			String noDocumento);

	/**
	 * Consulta por no de documento y hace el proceso de enriquecimiento.
	 * 
	 * @param noDocumento
	 * @param usuario
	 * @return
	 */
	com.equivida.modelo.PersonaNatural consultaEnriquecida(String noDocumento,
			String usuario);
}
