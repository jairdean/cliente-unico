package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.ParroquiaInec;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface ParroquiaInecServicio extends
		GenericService<ParroquiaInec, Short> {

}
