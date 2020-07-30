/**
 *ActividadEconomicaservicio.java
 *
 *Tue Jan 26 12:18:50 ECT 2016
 */
package com.equivida.smartdata.servicio;

import javax.ejb.Local;

import com.equivida.smartdata.model.ActividadEconomicaSd;
import com.saviasoft.persistence.util.service.GenericService;

@Local
public interface ActividadEconomicaSdServicio extends
		GenericService<ActividadEconomicaSd, Short> {

	/**
	 * Busca Actividad por codigo
	 * 
	 * @param codigoSri
	 * @return
	 */
	ActividadEconomicaSd obtenerPorCodigoSri(String codigoSri);

	/**
	 * Obtiene actividad economica por defecto codigo: 4829
	 * 
	 * @return
	 */
	ActividadEconomicaSd obtenerPorDefecto();
}