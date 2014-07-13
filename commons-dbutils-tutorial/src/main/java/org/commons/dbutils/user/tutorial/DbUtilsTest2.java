package org.commons.dbutils.user.tutorial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.handlers.MapListHandler;

public class DbUtilsTest2 {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		final String url = "jdbc:h2:./target/test;AUTO_SERVER=TRUE";
		final String driver = "org.h2.Driver";
		final String usr = "sa";
		final String pwd = "";

		List<Map<String, Object>> userMaps = null;
		try {
			// Loading the Driver using DbUtils static method
			DbUtils.loadDriver(driver);
			conn = DriverManager.getConnection(url, usr, pwd);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM user");
			MapListHandler mapListHandler = new MapListHandler();
			userMaps = mapListHandler.handle(rs);

			for (Map<String, Object> mapObj : userMaps) {
				System.out.println("User Object::  " + mapObj.get("userId")
						+ "\t" + mapObj.get("lastName") + "\t"
						+ mapObj.get("phoneNo"));
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			// Closing the connection quietly, means it will handles the
			// SQLException
			DbUtils.closeQuietly(conn);
		}
	}
}
