package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.Beneficiario;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface BeneficiarioServicio extends GenericService<Beneficiario, Integer> {

	/**
	 * Se busca beneficiario por numero de documento.
	 * 
	 * @param noDocumento
	 * @return
	 */
	Beneficiario buscarPorNoDocumento(String noDocumento);
}
