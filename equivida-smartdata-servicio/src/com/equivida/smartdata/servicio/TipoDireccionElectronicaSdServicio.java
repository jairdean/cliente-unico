/**
*TipoDireccionElectronicaservicio.java
*
*Tue Jan 26 12:48:34 ECT 2016
*/
package com.equivida.smartdata.servicio;

import javax.ejb.Local;

import com.equivida.smartdata.model.TipoDireccionElectronicaSd;
import com.saviasoft.persistence.util.service.GenericService;

@Local
public interface TipoDireccionElectronicaSdServicio extends GenericService<TipoDireccionElectronicaSd, Short> {

}