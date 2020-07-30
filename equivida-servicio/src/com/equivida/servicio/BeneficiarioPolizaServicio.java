package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.BeneficiarioPoliza;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface BeneficiarioPolizaServicio extends
		GenericService<BeneficiarioPoliza, Integer> {

}
