package com.equivida.sise.sp;

import javax.ejb.Local;

@Local
public interface PruebaSp {

	void llamarPrueba();
	
	void insertDatos(String dato);
	
}
