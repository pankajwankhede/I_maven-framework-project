package org.spring.jdbc.tutorials.dao;

import java.util.List;

import org.spring.jdbc.tutorials.model.Employee;

public interface JdbcEmployeeDAO {

	public void insert(Employee employee);
	public Employee findById(int id);
	public List<Employee> findAll();
	public String findNameById(int id);
	public void insertBatch1(final List<Employee> employees);
	public void insertBatch2(final String sql);
}
