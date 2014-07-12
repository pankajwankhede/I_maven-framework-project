package com.xdoclet.export;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class ExportTable {
	
	public static void main(String[] args) {
		Configuration configuration=new Configuration().configure("hibernate.cfg.xml");
		SchemaExport export=new SchemaExport(configuration);
		export.create(true, true);
	}
}
