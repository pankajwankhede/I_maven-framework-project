package org.commons.dbutils.user.tutorial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class DbUtilsTest1 {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		final String url = "jdbc:h2:./target/test;AUTO_SERVER=TRUE";
		final String driver = "org.h2.Driver";
		final String usr = "sa";
		final String pwd = "";

		List<User> users = null;
		try {
			DbUtils.loadDriver(driver);
			conn = DriverManager.getConnection(url, usr, pwd);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM user");
			BeanListHandler<User> listHandler = new BeanListHandler<User>(
					User.class);
			users = listHandler.handle(rs);

			for (User user : users) {
				System.out.println("User Object:: " + user.getUserId() + "\t"
						+ user.getFirstName() + "\t" + user.getEmailId());
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
	}
}
