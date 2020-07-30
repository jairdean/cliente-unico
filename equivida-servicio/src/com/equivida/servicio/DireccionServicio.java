package com.equivida.servicio;

import java.util.Collection;

import javax.ejb.Local;

import com.equivida.modelo.Direccion;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface DireccionServicio extends GenericService<Direccion, Integer> {
	/**
	 * Se persiste direccion.
	 * 
	 * @param direccion
	 */
	void persistir(Direccion direccion);

	/**
	 * Se persiste una lista de direcciones.
	 * 
	 * @param direccionList
	 */
	void persistir(Collection<Direccion> direccionList);
}
