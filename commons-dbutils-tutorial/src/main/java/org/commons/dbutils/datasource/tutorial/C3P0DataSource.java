package org.commons.dbutils.datasource.tutorial;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0DataSource {

	private DataSource dataSource;

	private static C3P0DataSource c3p0DataSource;

	private ComboPooledDataSource cpds;

	private C3P0DataSource() throws IOException, SQLException,
			PropertyVetoException {
		cpds = new ComboPooledDataSource();
		cpds.setDriverClass("org.h2.Driver"); // loads the jdbc driver
		cpds.setJdbcUrl("jdbc:h2:./target/test;AUTO_SERVER=TRUE");
		cpds.setUser("sa");
		cpds.setPassword("");

		// the settings below are optional -- c3p0 can work with defaults
		cpds.setMinPoolSize(5);
		cpds.setAcquireIncrement(5);
		cpds.setMaxPoolSize(20);
		cpds.setMaxStatements(180);

		this.setDataSource(cpds);

	}

	public static C3P0DataSource getInstance() throws IOException,
			SQLException, PropertyVetoException {
		if (c3p0DataSource == null) {
			c3p0DataSource = new C3P0DataSource();
			return c3p0DataSource;
		} else {
			return c3p0DataSource;
		}
	}

	public Connection getConnection() throws SQLException {
		return this.cpds.getConnection();
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
