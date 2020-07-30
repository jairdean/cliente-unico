package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.Provincia;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Daniel Cardenas
 * 
 */
@Local
public interface ProvinciaServicio extends GenericService<Provincia, Short> {

}
