package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.TipoDireccion;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface TipoDireccionServicio extends
		GenericService<TipoDireccion, Short> {

}
