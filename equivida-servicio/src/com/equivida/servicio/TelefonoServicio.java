package com.equivida.servicio;

import java.util.Collection;

import javax.ejb.Local;

import com.equivida.modelo.Telefono;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface TelefonoServicio extends GenericService<Telefono, Integer> {
	/**
	 * Se persiste Telefono.
	 * 
	 * @param telefono
	 */
	void persistir(Telefono telefono);

	/**
	 * Se persiste una coleccion de telefonos.
	 * 
	 * @param telefonoCol
	 */
	void persistir(Collection<Telefono> telefonoCol);
}
