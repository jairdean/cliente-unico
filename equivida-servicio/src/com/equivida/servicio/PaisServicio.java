package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.Pais;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface PaisServicio extends GenericService<Pais, Short> {

	Pais buscarPorPais(String nombre);
	
}
