package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.Biometrica;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface BiometricaServicio extends GenericService<Biometrica, Integer> {

	Biometrica buscarPorSecPersonaNatural(Integer secPersonaNatural);

}
