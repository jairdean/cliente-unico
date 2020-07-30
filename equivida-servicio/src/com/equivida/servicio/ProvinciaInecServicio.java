package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.ProvinciaInec;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface ProvinciaInecServicio extends
		GenericService<ProvinciaInec, Short> {

}
