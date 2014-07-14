package org.spring.jdbc.tutorials.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.spring.jdbc.tutorials.dao.JdbcEmployeeDAO;
import org.spring.jdbc.tutorials.model.Employee;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcEmployeeDAOImpl implements JdbcEmployeeDAO{
	
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
 
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
 
	public void insert(Employee employee){
 
		String sql = "INSERT INTO EMPLOYEE " +
			"(ID, NAME, AGE) VALUES (?, ?, ?)";
 
		jdbcTemplate = new JdbcTemplate(dataSource);
 
		jdbcTemplate.update(sql, new Object[] { employee.getId(),
				employee.getName(), employee.getAge()  
		});
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Employee findById(int id){
		 
		String sql = "SELECT * FROM EMPLOYEE WHERE ID = ?";

		jdbcTemplate = new JdbcTemplate(dataSource);
		Employee employee = (Employee) jdbcTemplate.queryForObject(
				sql, new Object[] { id }, new BeanPropertyRowMapper(Employee.class));
	 
		return employee;
	}

	@SuppressWarnings("rawtypes")
	public List<Employee> findAll(){
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT * FROM EMPLOYEE";
	 
		List<Employee> employees = new ArrayList<Employee>();
	 
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		for (Map row : rows) {
			Employee employee = new Employee();
			employee.setId(Integer.parseInt(String.valueOf(row.get("ID"))));
			employee.setName((String)row.get("NAME"));
			employee.setAge(Integer.parseInt(String.valueOf(row.get("AGE"))));
			employees.add(employee);
		}
	 
		return employees;
	}
	
	public String findNameById(int id){

		jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT NAME FROM EMPLOYEE WHERE ID = ?";
	 
		String name = (String)jdbcTemplate.queryForObject(
				sql, new Object[] { id }, String.class);
	 
		return name;
	}

	public void insertBatchSQL(final String sql){
		 
		jdbcTemplate.batchUpdate(new String[]{sql});
	 
	}
	
	public void insertBatch1(final List<Employee> employees){

		jdbcTemplate = new JdbcTemplate(dataSource);
		  String sql = "INSERT INTO EMPLOYEE " +
			"(ID, NAME, AGE) VALUES (?, ?, ?)";
		 
		  jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
		 
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Employee employee = employees.get(i);
				ps.setLong(1, employee.getId());
				ps.setString(2, employee.getName());
				ps.setInt(3, employee.getAge() );
			}
		 
			public int getBatchSize() {
				return employees.size();
			}
		  });
		}
	
	public void insertBatch2(final String sql){
		jdbcTemplate = new JdbcTemplate(dataSource); 
		jdbcTemplate.batchUpdate(new String[]{sql});
	}
	
}
