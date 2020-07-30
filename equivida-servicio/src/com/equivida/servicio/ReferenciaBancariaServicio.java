package com.equivida.servicio;

import java.util.List;

import javax.ejb.Local;

import com.equivida.modelo.ReferenciaBancaria;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Daniel Cardenas
 * 
 */
@Local
public interface ReferenciaBancariaServicio extends
		GenericService<ReferenciaBancaria, Integer> {

	List<ReferenciaBancaria> obtenerPorPersonaNatural(Integer secPersonaNatural);
}
