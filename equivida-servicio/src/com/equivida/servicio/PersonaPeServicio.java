package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.PersonaPe;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface PersonaPeServicio extends GenericService<PersonaPe, Integer> {

}
