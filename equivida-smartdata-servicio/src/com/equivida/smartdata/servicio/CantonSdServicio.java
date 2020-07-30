/**
*Cantonservicio.java
*
*Tue Jan 26 12:31:00 ECT 2016
*/
package com.equivida.smartdata.servicio;

import javax.ejb.Local;

import com.saviasoft.persistence.util.service.GenericService;

import com.equivida.smartdata.model.CantonSd;

@Local
public interface CantonSdServicio extends GenericService<CantonSd, Short> {

}