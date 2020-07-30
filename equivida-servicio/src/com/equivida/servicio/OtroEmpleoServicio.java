package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.OtroEmpleo;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface OtroEmpleoServicio extends GenericService<OtroEmpleo, Integer> {

}
