/**
*TipoDireccionservicio.java
*
*Tue Jan 26 12:47:28 ECT 2016
*/
package com.equivida.smartdata.servicio;

import javax.ejb.Local;

import com.equivida.smartdata.model.TipoDireccionSd;
import com.saviasoft.persistence.util.service.GenericService;

@Local
public interface TipoDireccionSdServicio extends GenericService<TipoDireccionSd, Short> {

}