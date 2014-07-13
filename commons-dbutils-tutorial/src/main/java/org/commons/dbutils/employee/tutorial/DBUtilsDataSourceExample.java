package org.commons.dbutils.employee.tutorial;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.commons.dbutils.datasource.tutorial.DBCPDataSource;

public class DBUtilsDataSourceExample {

	public static void main(String[] args) throws SQLException, IOException,
			PropertyVetoException {

		QueryRunner run = new QueryRunner(DBCPDataSource.getInstance()
				.getDataSource());

		// -----------------------------------------------------------------------------------
		ResultSetHandler<Employee> resultHandler = new BeanHandler<Employee>(
				Employee.class);
		ResultSetHandler<List<Employee>> resultListHandler = new BeanListHandler<Employee>(
				Employee.class);

		Employee emp = run.query("SELECT * FROM employee WHERE employeename=?",
				resultHandler, "Jose");
		System.out.println(emp.getEmployeeId());
		// -----------------------------------------------------------------------------------

		List<Employee> empList = run.query("SELECT * FROM employee",
				resultListHandler);
		System.out.println(empList);
		// -----------------------------------------------------------------------------------

		List<Map<String, Object>> maps = run.query("SELECT * FROM employee",
				new MapListHandler());
		System.out.println(maps);
		// -----------------------------------------------------------------------------------

		List<Object[]> query = run.query("SELECT * FROM employee",
				new ArrayListHandler());
		for (Object[] objects : query) {
			System.out.println(Arrays.toString(objects));
		}
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

		Object[] result = run.query(
				"SELECT * FROM employee WHERE employeename=?", handler, "Jose");
		System.out.println(Arrays.toString(result));

		// -----------------------------------------------------------------------------------

		try {
			// Execute the SQL update statement and return the number of
			// inserts that were made
			int inserts = run.update(
					"INSERT INTO employee (employeename) VALUES (?)", "Arun");
			// The line before uses varargs and autoboxing to simplify the code
			System.out.println("inserts " + inserts);
			// Now it's time to rise to the occation...
			int updates = run.update(
					"UPDATE employee SET employeename=? WHERE employeeid=?",
					"Simon", 1);
			System.out.println("updates " + updates);
		} catch (SQLException sqle) {
			// Handle it
			sqle.printStackTrace();
		}

	}
}
