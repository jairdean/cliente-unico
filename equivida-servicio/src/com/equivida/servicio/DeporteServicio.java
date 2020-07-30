package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.Deporte;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Daniel Cardenas
 * 
 */
@Local
public interface DeporteServicio extends GenericService<Deporte, Short> {

}
