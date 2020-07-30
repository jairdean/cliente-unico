package com.equivida.sise.sp;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import com.equivida.sise.obj.DetalleRegistoPresupuesto;
import com.equivida.sise.rs.RsRegistroPresupuesto;

@Local
public interface RegistroPresupuestoSp {

	List<RsRegistroPresupuesto> llamarRegistroPresupuestoSp(
			List<DetalleRegistoPresupuesto> detalle) throws SQLException;
}