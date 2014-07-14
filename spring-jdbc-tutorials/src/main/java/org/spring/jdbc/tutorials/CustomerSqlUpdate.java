package org.spring.jdbc.tutorials;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class CustomerSqlUpdate extends SqlUpdate {
	
	public CustomerSqlUpdate(DataSource dataSource) {
		setDataSource(dataSource);
		setSql("UPDATE customer set name = ? where cust_id = ? ");
		declareParameter(new SqlParameter("name", Types.VARCHAR));
        declareParameter(new SqlParameter("cust_id", Types.INTEGER));
        compile();
	}
	
	public int execute(int id, String name) {
        return update(name, id);
    }
	
}
