package com.equivida.homologacion.servicio;

import javax.ejb.Local;

import com.equivida.homologacion.modelo.DireccionElectronicaEquivida;
import com.saviasoft.persistence.util.service.GenericService;

@Local
public interface DireccionElectronicaEquividaServicio extends
		GenericService<DireccionElectronicaEquivida, Integer> {

	boolean existe(Integer secPersona, Integer secDireccionElectronicaEquivida,
			Integer idDireccionElectronicaSise, Integer idPersonaSise,
			Short sistemaOid);

}