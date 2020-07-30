package com.equivida.dialect;

import org.hibernate.Hibernate;

public class Db2EquividaDialect extends org.hibernate.dialect.DB2Dialect{

	public Db2EquividaDialect() {
		super();

		registerHibernateType(2009, Hibernate.TEXT.getName());
		registerColumnType(2009, Hibernate.TEXT.getName());
		registerHibernateType(1111, Hibernate.TEXT.getName());
		registerColumnType(1111, Hibernate.TEXT.getName());
	}

}
