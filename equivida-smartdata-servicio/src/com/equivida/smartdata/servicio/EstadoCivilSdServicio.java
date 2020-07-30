/**
*EstadoCivilservicio.java
*
*Tue Jan 26 12:35:33 ECT 2016
*/
package com.equivida.smartdata.servicio;

import javax.ejb.Local;

import com.equivida.smartdata.model.EstadoCivilSd;
import com.saviasoft.persistence.util.service.GenericService;

@Local
public interface EstadoCivilSdServicio extends GenericService<EstadoCivilSd, Short> {

}