package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.Interviniente;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface IntervinienteServicio extends
		GenericService<Interviniente, Integer> {

}
