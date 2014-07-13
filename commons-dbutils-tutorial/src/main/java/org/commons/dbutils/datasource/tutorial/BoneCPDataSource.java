package org.commons.dbutils.datasource.tutorial;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public class BoneCPDataSource {

	private DataSource dataSource;

	private static BoneCPDataSource boneCPDataSource;

	private BoneCP connectionPool;

	private BoneCPDataSource() throws IOException, SQLException,
			PropertyVetoException {
		try {
			// load the database driver (make sure this is in your classpath!)
			Class.forName("org.h2.Driver");
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		try {
			// setup the connection pool using BoneCP Configuration
			BoneCPConfig config = new BoneCPConfig();
			// jdbc url specific to your database, eg
			// jdbc:mysql://127.0.0.1/yourdb
			config.setJdbcUrl("jdbc:h2:./target/test;AUTO_SERVER=TRUE");
			config.setUsername("sa");
			config.setPassword("");
			config.setMinConnectionsPerPartition(5);
			config.setMaxConnectionsPerPartition(10);
			config.setPartitionCount(1);
			// setup the connection pool
			connectionPool = new BoneCP(config);

		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

	}

	public static BoneCPDataSource getInstance() throws IOException,
			SQLException, PropertyVetoException {
		if (boneCPDataSource == null) {
			boneCPDataSource = new BoneCPDataSource();
			return boneCPDataSource;
		} else {
			return boneCPDataSource;
		}
	}

	public Connection getConnection() throws SQLException {
		return this.connectionPool.getConnection();
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
