package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.DetallePasaporte;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Daniel Cardenas
 * 
 */
@Local
public interface DetallePasaporteServicio extends
		GenericService<DetallePasaporte, Integer> {

	DetallePasaporte buscarPorSecPersonaNatural(Integer secPersonaNatural);
}
