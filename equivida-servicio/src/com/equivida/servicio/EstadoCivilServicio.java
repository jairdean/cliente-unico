package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.EstadoCivil;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface EstadoCivilServicio extends GenericService<EstadoCivil, Short> {

}
