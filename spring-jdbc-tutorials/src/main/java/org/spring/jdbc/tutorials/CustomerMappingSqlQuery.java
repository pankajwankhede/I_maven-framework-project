package org.spring.jdbc.tutorials;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.spring.jdbc.tutorials.model.Customer;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

public class CustomerMappingSqlQuery extends MappingSqlQuery<Customer> {
	
	public CustomerMappingSqlQuery(DataSource dataSource) {
		super(dataSource, "select CUST_ID, NAME, AGE from customer where CUST_ID = ?");
		super.declareParameter(new SqlParameter("id", Types.INTEGER));
        compile();
	}
	
	@Override
	protected Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Customer customer = new Customer();
		customer.setCustId(rs.getInt("CUST_ID"));
		customer.setName(rs.getString("NAME"));
		customer.setAge(rs.getInt("AGE"));
		return customer;
	}

}
