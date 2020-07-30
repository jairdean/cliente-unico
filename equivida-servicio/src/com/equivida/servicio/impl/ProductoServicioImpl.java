package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.ProductoDao;
import com.equivida.modelo.Producto;
import com.equivida.servicio.ProductoServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "ProductoServicio")
public class ProductoServicioImpl extends GenericServiceImpl<Producto, Short>
		implements ProductoServicio {

	@EJB
	private ProductoDao productoDao;

	public GenericDao<Producto, Short> getDao() {
		return productoDao;
	}
}
