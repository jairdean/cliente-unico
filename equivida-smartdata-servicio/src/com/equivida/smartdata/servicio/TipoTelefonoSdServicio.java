/**
*TipoTelefonoservicio.java
*
*Tue Jan 26 12:52:47 ECT 2016
*/
package com.equivida.smartdata.servicio;

import javax.ejb.Local;

import com.equivida.smartdata.model.TipoTelefonoSd;
import com.saviasoft.persistence.util.service.GenericService;

@Local
public interface TipoTelefonoSdServicio extends GenericService<TipoTelefonoSd, Short> {

}