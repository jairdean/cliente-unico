package com.equivida.sise.sp;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import com.equivida.sise.obj.DetalleRegistoPresupuesto;
import com.equivida.sise.rs.RsRegistroPresupuesto;

@Remote
public interface RegistroPresupuestoSpRemoto {

	List<RsRegistroPresupuesto> llamarRegistroPresupuestoSp(
			List<DetalleRegistoPresupuesto> detalle) throws SQLException;
}