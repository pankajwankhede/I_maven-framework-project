package org.commons.dbutils.datasource.tutorial;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

public class DBCPDataSource {

	private DataSource dataSource;

	private static DBCPDataSource dbcpDataSource;

	private BasicDataSource ds;

	private DBCPDataSource() throws IOException, SQLException,
			PropertyVetoException {

		ds = new BasicDataSource();
		ds.setDriverClassName("org.h2.Driver");
		ds.setUsername("sa");
		ds.setPassword("");
		ds.setUrl("jdbc:h2:./target/test;AUTO_SERVER=TRUE");

		// the settings below are optional -- dbcp can work with defaults
		ds.setMinIdle(5);
		ds.setMaxIdle(20);
		ds.setMaxOpenPreparedStatements(180);

		this.setDataSource(ds);

	}

	public static DBCPDataSource getInstance() throws IOException,
			SQLException, PropertyVetoException {
		if (dbcpDataSource == null) {
			dbcpDataSource = new DBCPDataSource();
			return dbcpDataSource;
		} else {
			return dbcpDataSource;
		}
	}

	public DataSource getDataSource() {
		return this.dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Connection getConnection() throws SQLException {
		return this.ds.getConnection();
	}

}
