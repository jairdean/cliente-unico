package com.equivida.homologacion.servicio;

import javax.ejb.Local;

import com.equivida.homologacion.modelo.DireccionEquivida;
import com.saviasoft.persistence.util.service.GenericService;

@Local
public interface DireccionEquividaServicio extends
		GenericService<DireccionEquivida, Integer> {

	boolean existe(Integer secPersona, Integer secDireccionEquivida,
			Integer idDireccionSise, Integer idPersonaSise, Short sistemaOid);

}