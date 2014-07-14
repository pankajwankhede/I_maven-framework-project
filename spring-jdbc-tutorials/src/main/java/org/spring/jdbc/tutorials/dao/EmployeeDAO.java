package org.spring.jdbc.tutorials.dao;

import org.spring.jdbc.tutorials.model.Employee;

public interface EmployeeDAO {

		public void insert(Employee employee);
		public Employee findById(int id);
}
