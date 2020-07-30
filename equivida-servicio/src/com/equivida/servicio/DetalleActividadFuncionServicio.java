package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.DetalleActividadFuncion;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Daniel Cardenas
 * 
 */
@Local
public interface DetalleActividadFuncionServicio extends
		GenericService<DetalleActividadFuncion, Integer> {

	DetalleActividadFuncion buscarPorSecPersonaNatural(Integer secPersonaNatural);

}
