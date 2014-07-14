package org.spring.jdbc.tutorials;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.spring.jdbc.tutorials.model.Customer;
import org.springframework.jdbc.core.RowMapper;

public class CustomerRowMapper implements RowMapper<Customer> {

	@Override
	public Customer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Customer customer = new Customer();
		customer.setCustId(resultSet.getInt("CUST_ID"));
		customer.setName(resultSet.getString("NAME"));
		customer.setAge(resultSet.getInt("AGE"));
		return customer;
	}

}
