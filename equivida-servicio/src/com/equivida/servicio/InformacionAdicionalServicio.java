package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.InformacionAdicional;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface InformacionAdicionalServicio extends
		GenericService<InformacionAdicional, Integer> {

}
