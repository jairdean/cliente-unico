/**
*Canalservicio.java
*
*Tue Jan 26 12:28:45 ECT 2016
*/
package com.equivida.smartdata.servicio;

import javax.ejb.Local;

import com.equivida.smartdata.model.CanalSd;
import com.saviasoft.persistence.util.service.GenericService;

@Local
public interface CanalSdServicio extends GenericService<CanalSd, Short> {

}