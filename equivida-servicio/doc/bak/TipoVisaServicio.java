package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.TipoVisa;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface TipoVisaServicio extends GenericService<TipoVisa, Short> {

}
