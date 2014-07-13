package org.commons.dbutils.employee.tutorial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

public class ArrayListHandlerExample {

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
			List<Object[]> query = run.query(conn, "SELECT * FROM employee",
					new ArrayListHandler());
			for (Object[] objects : query) {
				System.out.println(Arrays.toString(objects));
			}
		} finally {
			DbUtils.close(conn);
		}

	}
}
