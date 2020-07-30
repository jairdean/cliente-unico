/**
*Paisservicio.java
*
*Tue Jan 26 12:37:15 ECT 2016
*/
package com.equivida.smartdata.servicio;

import javax.ejb.Local;

import com.equivida.smartdata.model.PaisSd;
import com.saviasoft.persistence.util.service.GenericService;

@Local
public interface PaisSdServicio extends GenericService<PaisSd, Short> {

}