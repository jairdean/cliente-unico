package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.CantonInec;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface CantonInecServicio extends GenericService<CantonInec, Short> {

}
