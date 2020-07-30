package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.CategoriaPpe;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface CategoriaPpeServicio extends
		GenericService<CategoriaPpe, Short> {

}
