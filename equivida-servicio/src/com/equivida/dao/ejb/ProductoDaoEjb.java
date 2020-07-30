package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.ProductoDao;
import com.equivida.modelo.Producto;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "ProductoDao")
public class ProductoDaoEjb extends GenericDaoEjb<Producto, Short> implements
		ProductoDao {

	public ProductoDaoEjb() {
		super(Producto.class);
	}

}
