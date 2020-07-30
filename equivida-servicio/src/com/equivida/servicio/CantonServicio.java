package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.Canton;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface CantonServicio extends GenericService<Canton, Short> {

}
