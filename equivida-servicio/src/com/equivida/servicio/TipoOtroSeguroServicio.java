package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.TipoOtroSeguro;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface TipoOtroSeguroServicio extends
		GenericService<TipoOtroSeguro, Short> {

}
