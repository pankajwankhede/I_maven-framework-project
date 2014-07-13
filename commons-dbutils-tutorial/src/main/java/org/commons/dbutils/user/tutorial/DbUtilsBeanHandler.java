package org.commons.dbutils.user.tutorial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

public class DbUtilsBeanHandler {
	public static void main(String[] args) {
		Connection conn = null;

		final String url = "jdbc:h2:./target/test;AUTO_SERVER=TRUE";
		final String driver = "org.h2.Driver";
		final String usr = "sa";
		final String pwd = "";

		User user = null;

		try {
			// Loading the Driver using DbUtils static method
			DbUtils.loadDriver(driver);
			conn = DriverManager.getConnection(url, usr, pwd);
			QueryRunner query = new QueryRunner();
			user = (User) query.query(conn,
					"select * from user where userId=3", new BeanHandler<User>(
							User.class));

			System.out.println("User Object::  " + user.getUserId() + "\t"
					+ user.getFirstName() + "\t" + user.getLastName() + "\t"
					+ user.getEmailId());

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
	}
}
