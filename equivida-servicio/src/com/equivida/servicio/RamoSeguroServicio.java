package com.equivida.servicio;

import java.util.List;

import javax.ejb.Local;

import com.equivida.modelo.RamoSeguro;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Daniel Cardenas
 * 
 */
@Local
public interface RamoSeguroServicio extends
		GenericService<RamoSeguro, Short> {

	List<RamoSeguro> obtenerOrdenadoPorNombre();

}