package com.equivida.sise.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;

import javax.sql.DataSource;

/**
 * Clase que retorna la conexion segun el datasource del horario establecido
 * 
 * @author Daniel Cardenas
 * 
 */
public class DataSourceUtil {

	public static Connection obtenerConexionSegunHorario(DataSource dataSource,
			DataSource dataSourceBak) throws SQLException {
		Connection c = null;

		Calendar hoy = Calendar.getInstance();

		int horaDesdeBak = 2;
		int minutoDesdeBak = 0;// 0 minutos
		int horaHastaBak = 4;
		int minutoHastaBak = 0;// 0 minutos

		int horaActual = hoy.get(Calendar.HOUR_OF_DAY);// 0-23
		int minutoActual = hoy.get(Calendar.MINUTE);// 0-23

		// verifica si esta en el rango de horas
		if (horaActual > horaDesdeBak && horaActual < horaHastaBak) {
			// entonces usamos el datasource bak
			c = dataSourceBak.getConnection();
		} else {
			if (horaActual == horaDesdeBak) {

				if (horaActual == horaHastaBak) {
					if (minutoActual >= minutoDesdeBak
							&& minutoActual <= minutoHastaBak) {
						c = dataSourceBak.getConnection();
					}
				} else {
					if (minutoActual >= minutoDesdeBak) {
						c = dataSourceBak.getConnection();
					}
				}

			} else {
				if (horaActual == horaHastaBak) {
					if (minutoActual <= minutoHastaBak) {
						c = dataSourceBak.getConnection();
					}
				}
			}

		}

		if (c == null) {
			System.out.println("fuera rango");
			// caso contrario utilizamos el datasource de siempre
			c = dataSource.getConnection();
		} else {
			System.out.println("dentro del rango....");
		}
		return c;
	}

}
