package com.equivida.homologacion.servicio;

import javax.ejb.Local;

import com.equivida.homologacion.modelo.TelefonoEquivida;
import com.saviasoft.persistence.util.service.GenericService;

@Local
public interface TelefonoEquividaServicio extends
		GenericService<TelefonoEquivida, Integer> {

	boolean existe(Integer secPersona, Integer secTelefonoEquivida,
			Integer idTelefonoSise, Integer idPersonaSise, Short sistemaOid);

}