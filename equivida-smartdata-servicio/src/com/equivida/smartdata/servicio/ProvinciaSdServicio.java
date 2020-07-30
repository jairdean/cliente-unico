/**
*Provinciaservicio.java
*
*Tue Jan 26 12:44:41 ECT 2016
*/
package com.equivida.smartdata.servicio;

import javax.ejb.Local;

import com.equivida.smartdata.model.ProvinciaSd;
import com.saviasoft.persistence.util.service.GenericService;

@Local
public interface ProvinciaSdServicio extends GenericService<ProvinciaSd, Short> {

}