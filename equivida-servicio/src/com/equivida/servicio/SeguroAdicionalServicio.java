package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.SeguroAdicional;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface SeguroAdicionalServicio extends
		GenericService<SeguroAdicional, Integer> {

}
