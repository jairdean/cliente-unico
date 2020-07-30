package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.PaisIso;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface PaisIsoServicio extends GenericService<PaisIso, Short> {

}
