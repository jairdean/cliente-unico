package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.exception.NoEncuentraDatosException;
import com.equivida.modelo.CantonTMunicipio;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Daniel Cardenas
 * 
 */
@Local
public interface CantonTMunicipioServicio extends
		GenericService<CantonTMunicipio, Short> {

	CantonTMunicipio obtenerPorSecCanton(Short secCanton) throws NoEncuentraDatosException;

}
