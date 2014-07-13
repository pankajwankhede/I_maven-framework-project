package org.commons.dbutils.employee.tutorial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

public class MapListHandlerExample {

	public static void main(String[] args) throws SQLException {

		final String url = "jdbc:h2:./target/test;AUTO_SERVER=TRUE";
		final String driver = "org.h2.Driver";
		final String usr = "sa";
		final String pwd = "";

		QueryRunner run = new QueryRunner();

		DbUtils.loadDriver(driver);
		Connection conn = DriverManager.getConnection(url, usr, pwd);
		// -----------------------------------------------------------------------------------

		try {
			List<Map<String, Object>> maps = run.query(conn,
					"SELECT * FROM employee", new MapListHandler());
			System.out.println(maps);
		} finally {
			DbUtils.close(conn);
		}

	}
}
