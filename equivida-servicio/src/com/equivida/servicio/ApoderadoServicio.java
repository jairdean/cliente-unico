package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.Apoderado;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface ApoderadoServicio extends GenericService<Apoderado, Integer> {

}
