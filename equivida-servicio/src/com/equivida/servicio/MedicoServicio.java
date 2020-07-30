package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.Medico;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface MedicoServicio extends GenericService<Medico, Integer> {

}
