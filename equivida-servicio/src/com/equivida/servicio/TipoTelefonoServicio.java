package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.TipoTelefono;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface TipoTelefonoServicio extends
		GenericService<TipoTelefono, Short> {

}
