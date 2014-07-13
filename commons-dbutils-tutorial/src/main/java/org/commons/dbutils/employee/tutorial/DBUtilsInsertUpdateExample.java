package org.commons.dbutils.employee.tutorial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;

public class DBUtilsInsertUpdateExample {

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
			// Execute the SQL update statement and return the number of
			// inserts that were made
			int inserts = run.update(conn,
					"INSERT INTO employee (employeename) VALUES (?)", "Arun");
			// The line before uses varargs and autoboxing to simplify the code
			System.out.println("inserts " + inserts);
			// Now it's time to rise to the occation...
			int updates = run.update(conn,
					"UPDATE employee SET employeename=? WHERE employeeid=?",
					"Simon", 1);
			System.out.println("updates " + updates);
		} catch (SQLException sqle) {
			// Handle it
			sqle.printStackTrace();
		}

	}
}
