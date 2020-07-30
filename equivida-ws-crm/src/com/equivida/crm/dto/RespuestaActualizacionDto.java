package com.equivida.crm.dto;

public class RespuestaActualizacionDto {

	private int cantidadRegistrosProcesados = 0;// los actualizados
												// correctamente
	private int cantidadRegistrosAceptados = 0;// los que se reciben en total
	private int cantidadRegistrosRechazados = 0;// los que por alguna razon no
												// se

	// pudieron actualizar

	public void anadirCantidadRegistrosProcesados() {
		this.cantidadRegistrosProcesados = this.cantidadRegistrosProcesados + 1;
	}

	public void anadirCantidadRegistrosAceptados() {
		this.cantidadRegistrosAceptados = this.cantidadRegistrosAceptados + 1;
	}

	public void anadirCantidadRegistrosRechazados() {
		this.cantidadRegistrosRechazados = this.cantidadRegistrosRechazados + 1;
	}

	public int getCantidadRegistrosProcesados() {
		return cantidadRegistrosProcesados;
	}

	public void setCantidadRegistrosProcesados(int cantidadRegistrosProcesados) {
		this.cantidadRegistrosProcesados = cantidadRegistrosProcesados;
	}

	public int getCantidadRegistrosAceptados() {
		return cantidadRegistrosAceptados;
	}

	public void setCantidadRegistrosAceptados(int cantidadRegistrosAceptados) {
		this.cantidadRegistrosAceptados = cantidadRegistrosAceptados;
	}

	public int getCantidadRegistrosRechazados() {
		return cantidadRegistrosRechazados;
	}

	public void setCantidadRegistrosRechazados(int cantidadRegistrosRechazados) {
		this.cantidadRegistrosRechazados = cantidadRegistrosRechazados;
	}
}