/**
*TipoIdentificacionservicio.java
*
*Tue Jan 26 12:49:53 ECT 2016
*/
package com.equivida.smartdata.servicio;

import javax.ejb.Local;

import com.equivida.smartdata.model.TipoIdentificacionSd;
import com.saviasoft.persistence.util.service.GenericService;

@Local
public interface TipoIdentificacionSdServicio extends GenericService<TipoIdentificacionSd, Character> {

}