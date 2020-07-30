package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.SeguroVigente;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface SeguroVigenteServicio extends
		GenericService<SeguroVigente, Integer> {

}
