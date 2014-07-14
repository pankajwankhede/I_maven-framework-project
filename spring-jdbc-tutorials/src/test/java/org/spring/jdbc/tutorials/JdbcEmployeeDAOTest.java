package org.spring.jdbc.tutorials;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.spring.jdbc.tutorials.dao.impl.JdbcEmployeeDAOImpl;
import org.spring.jdbc.tutorials.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class JdbcEmployeeDAOTest {
	
	@Autowired
	private JdbcEmployeeDAOImpl jdbcEmployeeDAOImpl;
	
	
	@Test
	public void insert(){
		Employee employee1 = new Employee(123, "javacodegeeks", 30);
		jdbcEmployeeDAOImpl.insert(employee1);
        Employee employee2 = jdbcEmployeeDAOImpl.findById(123);
        System.out.println(employee2);
	}
	
	
	@Test
	public void insertBatch1(){
		Employee emplNew1 = new Employee(23, "John", 23);
        Employee emplNew2 = new Employee(223, "Mark", 43);
        List<Employee> employeesN = new ArrayList<Employee>();
        employeesN.add(emplNew1);
        employeesN.add(emplNew2);
        jdbcEmployeeDAOImpl.insertBatch1(employeesN);
        System.out.println("inserted rows: " + employeesN);
	}
	
	@Test
	public void findAll(){
        System.out.println(jdbcEmployeeDAOImpl.findAll());
	}
	
	@Test
	public void insertBatch2(){
		jdbcEmployeeDAOImpl.insertBatch2("UPDATE EMPLOYEE SET NAME ='Mary'");
		System.out.println(jdbcEmployeeDAOImpl.findAll());
	}
	
	
	
}
