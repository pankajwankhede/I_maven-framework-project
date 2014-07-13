package org.commons.dbutils.employee.tutorial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

public class ResultSetHandlerExample {

	public static void main(String[] args) throws SQLException {

		final String url = "jdbc:h2:./target/test;AUTO_SERVER=TRUE";
		final String driver = "org.h2.Driver";
		final String usr = "sa";
		final String pwd = "";

		QueryRunner run = new QueryRunner();

		DbUtils.loadDriver(driver);
		Connection conn = DriverManager.getConnection(url, usr, pwd);
		// -----------------------------------------------------------------------------------

		ResultSetHandler<Object[]> handler = new ResultSetHandler<Object[]>() {
			public Object[] handle(ResultSet rs) throws SQLException {
				if (!rs.next()) {
					return null;
				}
				ResultSetMetaData meta = rs.getMetaData();
				int cols = meta.getColumnCount();
				Object[] result = new Object[cols];

				for (int i = 0; i < cols; i++) {
					result[i] = rs.getObject(i + 1);
				}
				return result;
			}
		};

		try {
			Object[] result = run.query(conn,
					"SELECT * FROM employee WHERE employeename=?", handler,
					"Rockey");
			System.out.println(Arrays.toString(result));

		} finally { // Use this helper method so we don't have to check for
			DbUtils.close(conn);
		}

	}
}
