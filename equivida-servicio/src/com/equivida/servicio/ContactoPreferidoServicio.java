package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.ContactoPreferido;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface ContactoPreferidoServicio extends
		GenericService<ContactoPreferido, Long> {

	ContactoPreferido obtenerPorPersona(Integer secPersona);

	void updateLight(ContactoPreferido contactoPreferido);

}
