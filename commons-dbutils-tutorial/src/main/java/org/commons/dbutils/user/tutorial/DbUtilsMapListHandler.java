package org.commons.dbutils.user.tutorial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

public class DbUtilsMapListHandler {

	public static void main(String[] args) {
		Connection conn = null;

		final String url = "jdbc:h2:./target/test;AUTO_SERVER=TRUE";
		final String driver = "org.h2.Driver";
		final String usr = "sa";
		final String pwd = "";

		try {
			DbUtils.loadDriver(driver);
			conn = DriverManager.getConnection(url, usr, pwd);
			QueryRunner query = new QueryRunner();
			List<Map<String, Object>> mapList = (List<Map<String, Object>>) query
					.query(conn, "select * from user", new MapListHandler());
			for (int i = 0; i < mapList.size(); i++) {
				Map<String, Object> map = (Map<String, Object>) mapList.get(i);
				System.out.println("------> " + map.get("userId") + "\t"
						+ map.get("firstName") + "\t" + map.get("emailId"));
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
	}
}
