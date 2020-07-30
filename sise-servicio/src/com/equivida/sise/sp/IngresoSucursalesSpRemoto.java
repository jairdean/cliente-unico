package com.equivida.sise.sp;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.ejb.Remote;

import com.equivida.sise.rs.RsIngresoSucursales;

@Remote
public interface IngresoSucursalesSpRemoto {

	RsIngresoSucursales llamarIngresSucuralesSp(BigDecimal codPres,
			String sucursal, String direccion, String telefono,
			BigDecimal ubicacion, BigDecimal municipio,
			BigDecimal departamento, BigDecimal modo, BigDecimal codSucursal,
			BigDecimal tipoIdPres, String nroIdPress) throws SQLException;
}