/**
*Relacionservicio.java
*
*Thu Feb 18 10:46:12 ECT 2016
*/
package com.equivida.smartdata.servicio;

import javax.ejb.Local;

import com.equivida.smartdata.model.RelacionSd;
import com.saviasoft.persistence.util.service.GenericService;

@Local
public interface RelacionSdServicio extends GenericService<RelacionSd, Integer> {

	/**
	 * 
	 * @param idPersonaP
	 * @param idPersonaR
	 * @param tipoPArentesco
	 * @return
	 */
	boolean existeParentesco(Integer idPersonaP, Integer idPersonaR, Short tipoPArentesco);
}