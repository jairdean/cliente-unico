package com.equivida.servicio;

import java.util.List;

import javax.ejb.Local;

import com.equivida.modelo.CompaniaSeguro;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Daniel Cardenas
 * 
 */
@Local
public interface CompaniaSeguroServicio extends
		GenericService<CompaniaSeguro, Short> {

	List<CompaniaSeguro> obtenerOrdenadoPorNombre();

}