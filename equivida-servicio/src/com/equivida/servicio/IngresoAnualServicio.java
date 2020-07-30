package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.IngresoAnual;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface IngresoAnualServicio extends
		GenericService<IngresoAnual, Integer> {

}
