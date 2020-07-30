package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.Profesion;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface ProfesionServicio extends GenericService<Profesion, Short> {

}
