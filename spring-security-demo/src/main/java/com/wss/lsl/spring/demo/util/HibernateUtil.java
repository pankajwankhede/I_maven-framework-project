package com.wss.lsl.spring.demo.util;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class HibernateUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Configuration configuration = new Configuration().configure();
		
		SchemaExport export = new SchemaExport(configuration);
		
		export.create(true, true);
	}

}
