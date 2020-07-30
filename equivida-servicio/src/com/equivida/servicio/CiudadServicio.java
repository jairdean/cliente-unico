package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.Ciudad;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Daniel Cardenas
 * 
 */
@Local
public interface CiudadServicio extends GenericService<Ciudad, Short> {

}
