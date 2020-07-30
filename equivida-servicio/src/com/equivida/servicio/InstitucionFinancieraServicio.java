package com.equivida.servicio;

import java.util.List;

import javax.ejb.Local;

import com.equivida.modelo.InstitucionFinanciera;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface InstitucionFinancieraServicio extends
		GenericService<InstitucionFinanciera, Short> {

	List<InstitucionFinanciera> obtenerOrdenadoPorNombre();

}
