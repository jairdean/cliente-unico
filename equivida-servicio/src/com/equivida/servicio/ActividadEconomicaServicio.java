package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.ActividadEconomica;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * @author Daniel Cardenas
 * 
 */
@Local
public interface ActividadEconomicaServicio extends GenericService<ActividadEconomica, Short> {

	String obtenerCiiuParaSise(Short codActividadEconomica);

	ActividadEconomica obtenerPorCodigoCiiu(String codigo);

	void ponerNivel2Nivel1(ActividadEconomica act);

}
