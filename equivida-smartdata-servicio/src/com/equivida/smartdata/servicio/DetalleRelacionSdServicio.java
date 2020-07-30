/**
*DetalleRelacionservicio.java
*
*Tue Jan 26 12:32:38 ECT 2016
*/
package com.equivida.smartdata.servicio;

import javax.ejb.Local;

import com.equivida.smartdata.model.DetalleRelacionSd;
import com.saviasoft.persistence.util.service.GenericService;

@Local
public interface DetalleRelacionSdServicio extends GenericService<DetalleRelacionSd, Short> {

}