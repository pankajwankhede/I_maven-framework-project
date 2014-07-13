package org.commons.dbutils.user.tutorial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class DbUtilsBeanListHandler {

	public static void main(String[] args) {
		Connection conn = null;

		final String url = "jdbc:h2:./target/test;AUTO_SERVER=TRUE";
		final String driver = "org.h2.Driver";
		final String usr = "sa";
		final String pwd = "";

		List<User> users = null;

		try {
			DbUtils.loadDriver(driver);
			conn = DriverManager.getConnection(url, usr, pwd);
			QueryRunner query = new QueryRunner();
			users = (List<User>) query.query(conn, "select * from user",
					new BeanListHandler<User>(User.class));
			for (int i = 0; i < users.size(); i++) {
				User bean = users.get(i);
				System.out.println("User Objects::  " + bean.getUserId() + "\t"
						+ bean.getFirstName() + "\t" + bean.getLastName()
						+ "\t" + bean.getEmailId());
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
	}
}
