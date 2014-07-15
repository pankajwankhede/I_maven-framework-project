package org.spring.jdbc.tutorials.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.spring.jdbc.tutorials.CustomerMappingSqlQuery;
import org.spring.jdbc.tutorials.CustomerRowMapper;
import org.spring.jdbc.tutorials.CustomerSqlUpdate;
import org.spring.jdbc.tutorials.dao.CustomerDAO;
import org.spring.jdbc.tutorials.model.Address;
import org.spring.jdbc.tutorials.model.Customer;
import org.spring.jdbc.tutorials.model.QAddress;
import org.spring.jdbc.tutorials.model.QCustomer;
import org.springframework.data.jdbc.core.OneToManyResultSetExtractor;
import org.springframework.data.jdbc.query.QueryDslJdbcTemplate;
import org.springframework.data.jdbc.query.SqlDeleteCallback;
import org.springframework.data.jdbc.query.SqlInsertWithKeyCallback;
import org.springframework.data.jdbc.query.SqlUpdateCallback;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLDeleteClause;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.types.Path;

@SuppressWarnings("deprecation")
public class JdbcCustomerDAO implements CustomerDAO {

	/**
	 * QueryDslJdbcTemplate
	 */
	private QueryDslJdbcTemplate queryDslJdbcTemplate;
	
	
	/**
	 * JdbcTemplate
	 */
	private JdbcTemplate jdbcTemplate;
	

	/**
	 * NamedParameterJdbcTemplate
	 */
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	
	/**
	 * SimpleJdbcInsert
	 */
	private SimpleJdbcInsert simpleJdbcInsert;
	
	
	/**
	 * SimpleJdbcCall
	 */
	private SimpleJdbcCall simpleJdbcCall;
	
	/**
	 * MappingSqlQuery
	 */
	private CustomerMappingSqlQuery customerMappingSqlQuery;
	
	/**
	 * SqlUpdate
	 */
	private CustomerSqlUpdate customerSqlUpdate; 
	
	
	
	/**
	 * 使用OneToManyResultSetExtractor 一对多查询
	 * @return
	 */
	public List<Customer> useOneToManyResultSetExtractor(){
		String sql = "SELECT customer.cust_id, customer.name, customer.age, address.id," +  
					" address.cust_id, address.street, address.city " +
					" FROM customer " + 
					" LEFT JOIN address ON customer.cust_id = address.cust_id " + 
					" ORDER BY customer.cust_id" ;
		
		List<Customer> list = jdbcTemplate.query(sql, new OneToManyResultSetExtractor<Customer,Address,Integer>(new CustomerMapper(),new AddressMapper()){

			protected Integer mapPrimaryKey(ResultSet rs) throws SQLException {
				return rs.getInt("customer.cust_id");
			}

			protected Integer mapForeignKey(ResultSet rs) throws SQLException {
				if(rs.getObject("address.cust_id") == null){
					return null;
				}else{
					return rs.getInt("address.cust_id");
				}
			}

			protected void addChild(Customer root, Address child) {
				root.addAddress(child);
			}
			
		});
		return list;
	}
	
	
	
	/**
	 * 该内部类主要用来处理OneToManyResultSetExtractor
	 * @author welcome
	 *
	 */
	class CustomerMapper implements RowMapper<Customer> {

		@Override
		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Customer customer = new Customer();
			customer.setCustId(rs.getInt("customer.cust_id"));
			customer.setName(rs.getString("customer.name"));
			customer.setAge(rs.getInt("customer.age"));
			return customer;
		}
		
	}
	
	/**
	 * 该内部类主要用来处理OneToManyResultSetExtractor
	 * @author welcome
	 *
	 */
	class AddressMapper implements RowMapper<Address> {

		@Override
		public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
			Address address = new Address();
			address.setId(rs.getInt("address.id"));
			address.setStreet(rs.getString("address.street"));
			address.setCity(rs.getString("address.city"));
			return address;
		}
		
	}
	
	
	/**
	 * 使用QueryDslJdbcTemplate查询
	 * @return
	 */
	public List<Customer> useQueryDslQueryAll(){
		
		final QCustomer qCustomer = new QCustomer("customer");
		final QAddress qAddress = new QAddress("address");
		
		SQLQuery sqlQuery = queryDslJdbcTemplate.newSqlQuery().from(qCustomer).leftJoin(qCustomer._addressCustomerRef,qAddress);
		
		List<Customer> list = queryDslJdbcTemplate.queryForObject(sqlQuery, new OneToManyResultSetExtractor<Customer,Address,Integer>(new CustomerMapper(),new AddressMapper()) {
			
			@Override
			protected Integer mapPrimaryKey(ResultSet rs) throws SQLException {
				return rs.getInt(qCustomer.custId.toString());
			}

			@Override
			protected Integer mapForeignKey(ResultSet rs) throws SQLException {
				String columnname = qAddress.addressCustomerRef.getLocalColumns().get(0).toString();
				if(rs.getObject(columnname) == null){
					return null;
				}else{
					return rs.getInt(columnname);
				}
			}

			@Override
			protected void addChild(Customer root, Address child) {
				root.addAddress(child);
			}

			
		}, new Path[]{qCustomer.custId,qCustomer.name,qCustomer.age,qAddress.id,qAddress.custId,qAddress.street,qAddress.city});
		
		return list;
	}
	
	
	/**
	 * 使用QueryDsl新增
	 */
	public int useQueryDslInsert(final Customer customer){
		
		final QCustomer qCustomer = new QCustomer("customer");
		
		int returnVal = queryDslJdbcTemplate.insertWithKey(qCustomer, new SqlInsertWithKeyCallback<Integer>() {

			@Override
			public Integer doInSqlInsertWithKeyClause(SQLInsertClause insert)
					throws SQLException {
				return insert.columns(qCustomer.name,qCustomer.age).values(customer.getName(),customer.getAge()).executeWithKey(qCustomer.custId);
			}
		});
		
		return returnVal;
		
	}
	
	
	/**
	 * 使用QueryDsl修改
	 */
	public long useQueryDslUpdate(final Customer customer){
		
		final QCustomer qCustomer = new QCustomer("customer");
		
		long returnVal = queryDslJdbcTemplate.update(qCustomer, new SqlUpdateCallback() {
			
			@Override
			public long doInSqlUpdateClause(SQLUpdateClause update) {
				return update.
						where(qCustomer.custId.eq(customer.getCustId())).
						set(qCustomer.name, customer.getName()).
						set(qCustomer.age, customer.getAge()).
						execute();
			}
		});
		
		return returnVal;
		
	}
	
	
	/**
	 * 使用QueryDsl删除
	 */
	public long useQueryDslDelete(final Customer customer){
		
		final QCustomer qCustomer = new QCustomer("customer");
		
		long returnVal = queryDslJdbcTemplate.delete(qCustomer, new SqlDeleteCallback() {
			
			@Override
			public long doInSqlDeleteClause(SQLDeleteClause delete) {
				return delete.where(qCustomer.custId.eq(customer.getCustId())).execute();
			}
		});
		
		return returnVal;
		
	}
	
	
	
	/**
	 * 调用存储过程
	 * DELIMITER //
	 * CREATE PROCEDURE read_customer ( 
	 * 	  IN in_id INT, 
	 *	  OUT out_name VARCHAR(100), 
	 *	  OUT out_age INT(10)
	 *	BEGIN 
	 *	  SELECT name, age 
	 *	  INTO out_name, out_age 
	 *	  FROM customer where CUST_ID = in_id;
	 *	END// 
	 * @return
	 */
	public Customer executeProcedure(int id){
		
		SqlParameterSource in = new MapSqlParameterSource().addValue("in_id", id);//存储过程的输入in_id
		
		//注意设置存储过程名
		Map<String,Object> out = simpleJdbcCall.withProcedureName("read_customer").execute(in);
		Customer comp = new Customer();
		comp.setCustId(id);
		comp.setName((String)out.get("out_name"));
		comp.setAge((Integer)out.get("out_age"));
		return comp;
		
	} 
	
	/**
	 * 调用函数
	 * DELIMITER //
	 * CREATE FUNCTION get_customer_name (in_id INT)
	 * RETURNS VARCHAR(50) READS SQL DATA 
	 * BEGIN
	 *   declare out_name varchar(50);
	 *   select name into out_name from customer where CUST_ID = in_id;
	 *   RETURN out_name;
	 * END//
	 * @return
	 */
	public String executeFunction(int id){
		SqlParameterSource in = new MapSqlParameterSource().addValue("in_id", id); 
		simpleJdbcCall.getJdbcTemplate().setResultsMapCaseInsensitive(true);
    	String name = simpleJdbcCall.withFunctionName("get_customer_name").executeFunction(String.class, in);
        return name;
	}
	
	
	
	/**
	 * Returning ResultSet/REF Cursor from a SimpleJdbcCall
	 * 
	 * DELIMITER //
	 * CREATE PROCEDURE read_all_customers()
	 * BEGIN
	 * SELECT a.CUST_ID, a.NAME, a.AGE FROM customer a;
	 * END//
	 * 
	 * 使用simpleJdbcCall调用read_all_customers存储过程返回一个ResultSet Cursor
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<Customer> getResultSetCursorUseSimpleJdbcCall(){
		Map<String, Object> map = simpleJdbcCall.withProcedureName("read_all_customers").
				returningResultSet("customers", ParameterizedBeanPropertyRowMapper.
						newInstance(Customer.class)).
				execute(new HashMap<String, Object>(0));
		return (List<Customer>) map.get("customers");
	}
	
	
	
	/**
	 * 使用MappingSqlQuery方式查询
	 * @return
	 */
	public Customer useMappingSqlQuery(int id){
		return customerMappingSqlQuery.findObject(id);
	}
	
	
	/**
	 * 使用SqlUpdate方式更新
	 * @return
	 */
	public int useSqlUpdate(int id,String name){
		return customerSqlUpdate.execute(id, name);
	}
	
	
	/**
	 * 使用SimpleJdbcInsert和BeanPropertySqlParameterSource方式 新增数据
	 * @param customer
	 */
	public void useBeanPropertySqlParameterSource(Customer customer){
		//封装BeanPropertySqlParameterSource
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(customer);
		//设置表名(withTableName)和设置自增长主键(usingGeneratedKeyColumns)
		Number key = simpleJdbcInsert.withTableName("CUSTOMER").usingGeneratedKeyColumns("CUST_ID").executeAndReturnKey(parameterSource);
		customer.setCustId(key.intValue());//返回新增后的主键
		System.out.println(customer.getCustId());
	}
	
	/**
	 * 使用SimpleJdbcInsert和MapSqlParameterSource方式 新增数据
	 * @param customer
	 */
	public void useMapSqlParameterSource(Customer customer){
		//封装MapSqlParameterSource
		SqlParameterSource parameterSource = new MapSqlParameterSource().
				addValue("NAME", customer.getName()).//为NAME列填充值
				addValue("AGE", customer.getAge());//为AGE列填充值
		//设置表名(withTableName)和设置自增长主键(usingGeneratedKeyColumns)
		Number key = simpleJdbcInsert.withTableName("CUSTOMER").usingGeneratedKeyColumns("CUST_ID").executeAndReturnKey(parameterSource);
		customer.setCustId(key.intValue());//返回新增后的主键
		System.out.println(customer.getCustId());
	}
	
	/**
	 * 返回新增后自动生成的主键
	 * @param customer
	 * @return
	 */
	public int getAutoGeneratedID(final Customer customer){
		
		final String sql = "insert into CUSTOMER (name,age) values(?,?)";
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
  				 //	autoGeneratedKeys
				 PreparedStatement pst = con.prepareStatement(sql, new String[] {"CUST_ID"});
				 pst.setString(1,customer.getName());//为第一个问号填充值
				 pst.setInt(2,customer.getAge());//为第二个问号填充值
				return pst;
			}
		},keyHolder);
		//返回插入后数据库生成的主键
		return keyHolder.getKey().intValue();
	}
	
	/**
	 * 使用NamedParameterJdbcTemplate和MapSqlParameterSource 条件查询
	 * @param age
	 * @return 返回符合条件的记录数
	 */
	public int getCustomerUseMapSqlParameterSource(int age) {

		String sql = "select count(*) from CUSTOMER where age = :age";

		SqlParameterSource namedParameters = new MapSqlParameterSource("age",age);

		return namedParameterJdbcTemplate.queryForInt(sql, namedParameters);
	}

	/**
	 * 使用simpleJdbcInsert新增
	 */
	public void useSimpleJdbcInsert() {

		Map<String, Object> params = new HashMap<String, Object>(2);

		params.put("NAME", "SimpleJdbcInsert");
		params.put("AGE", 26);
		
		//注意设置表名
		simpleJdbcInsert.withTableName("CUSTOMER").execute(params);
	}

	
	/**
	 * 使用jdbcTemplate和BatchPreparedStatementSetter批量插入
	 * @param customers
	 */
	public void insertBatchUseBatchPreparedStatementSetter(
			final List<Customer> customers) {

		String sql = "INSERT INTO CUSTOMER " + "(NAME, AGE) VALUES (?, ?)";

		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				Customer customer = customers.get(i);
				ps.setString(1, customer.getName());
				ps.setInt(2, customer.getAge());
			}

			@Override
			public int getBatchSize() {
				return customers.size();
			}
		});
	}
	
	
	/**
	 * 使用jdbcTemplate批量插入
	 * @param customers
	 */
	public void insertBatch(final List<Customer> customers) {
		String sql = "INSERT INTO CUSTOMER " + "(NAME, AGE) VALUES (?, ?)";

		List<Object[]> parameters = new ArrayList<Object[]>();

		for (Customer cust : customers) {
			parameters.add(new Object[] {cust.getName(),cust.getAge() });
		}
		jdbcTemplate.batchUpdate(sql, parameters);
	}

	/**
	 * 使用namedParameterJdbcTemplate插入NamedParameter
	 * @param customer
	 */
	public void insertNamedParameter(Customer customer) {

		String sql = "INSERT INTO CUSTOMER " + "(NAME, AGE) VALUES (:name, :age)";

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("name", customer.getName());
		parameters.put("age", customer.getAge());

		namedParameterJdbcTemplate.update(sql, parameters);

	}

	/**
	 * 使用namedParameterJdbcTemplate和BeanPropertySqlParameterSource批量插入
	 * @param customers
	 */
	public void insertBatchUseSqlParameterSource(final List<Customer> customers) {

		String sql = "INSERT INTO CUSTOMER " + "(NAME, AGE) VALUES (:name, :age)";

		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (Customer cust : customers) {
			parameters.add(new BeanPropertySqlParameterSource(cust));
		}

		namedParameterJdbcTemplate.batchUpdate(sql,parameters.toArray(new SqlParameterSource[0]));
	}

	
	/**
	 * 使用namedParameterJdbcTemplate和SqlParameterSourceUtils批量插入
	 * @param customers
	 */
	public void insertBatchUseSqlParameterSourceUtils(final List<Customer> customers) {

		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(customers.toArray());

		namedParameterJdbcTemplate.batchUpdate("INSERT INTO CUSTOMER (NAME, AGE) VALUES (:name, :age)",params);

	}
	
	/**
	 * 使用 jdbcTemplate插入
	 */
	@Override
	public void insert(Customer customer) {

		String sql = "INSERT INTO CUSTOMER " + "(NAME, AGE) VALUES (?, ?)";

		// Connection conn = null;

		// try {
		// conn = dataSource.getConnection();
		// PreparedStatement ps = conn.prepareStatement(sql);
		// ps.setString(1, customer.getName());
		// ps.setInt(2, customer.getAge());
		// ps.executeUpdate();
		// ps.close();
		//
		// } catch (SQLException e) {
		// throw new RuntimeException(e);
		//
		// } finally {
		// if (conn != null) {
		// try {
		// conn.close();
		// } catch (SQLException e) {}
		// }
		// }

		jdbcTemplate.update(sql,new Object[] { customer.getCustId(), customer.getName(),customer.getAge() });

	}

	/**
	 * 使用jdbcTemplate和CustomerRowMapper查询
	 */
	public Customer findCustomerIdUseCustomerRowMapper(int custId) {

		String sql = "SELECT * FROM CUSTOMER WHERE CUST_ID = ?";

		// Connection conn = null;
		//
		// try {
		// conn = dataSource.getConnection();
		// PreparedStatement ps = conn.prepareStatement(sql);
		// ps.setInt(1, custId);
		// Customer customer = null;
		// ResultSet rs = ps.executeQuery();
		// if (rs.next()) {
		// customer = new Customer(
		// rs.getInt("CUST_ID"),
		// rs.getString("NAME"),
		// rs.getInt("Age")
		// );
		// }
		// rs.close();
		// ps.close();
		// return customer;
		// } catch (SQLException e) {
		// throw new RuntimeException(e);
		// } finally {
		// if (conn != null) {
		// try {
		// conn.close();
		// } catch (SQLException e) {}
		// }
		// }

		Customer customer = jdbcTemplate.queryForObject(sql,new Object[] { custId }, new CustomerRowMapper());

		return customer;
	}

	/**
	 * 使用jdbcTemplate和BeanPropertyRowMapper查询
	 * 
	 * @param custId
	 * @return
	 */
	public Customer findCustomerIdUseBeanPropertyRowMapper(int custId) {

		String sql = "SELECT * FROM CUSTOMER WHERE CUST_ID = ?";

		Customer customer = jdbcTemplate.queryForObject(sql,
				new Object[] { custId }, new BeanPropertyRowMapper<Customer>(Customer.class));

		return customer;
	}

	/**
	 * 使用jdbcTemplate查询所有 返回List<Map<String, Object>>结果集
	 * @return
	 */
	public List<Customer> findAll() {

		String sql = "SELECT * FROM CUSTOMER";

		List<Customer> customers = new ArrayList<Customer>();

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

		for (Map<String, Object> row : rows) {
			Customer customer = new Customer();
			customer.setCustId((Integer) (row.get("CUST_ID")));
			customer.setName((String) row.get("NAME"));
			customer.setAge((Integer) row.get("AGE"));
			customers.add(customer);
		}

		return customers;
	}

	/**
	 * 使用jdbcTemplate和BeanPropertyRowMapper查询 返回List<T>结果集
	 * @return
	 */
	public List<Customer> findAllUseBeanPropertyRowMapper() {

		String sql = "SELECT * FROM CUSTOMER";

		List<Customer> customers = jdbcTemplate.query(sql,new BeanPropertyRowMapper<Customer>(Customer.class));

		return customers;
	}
	
	/**
	 * 使用jdbcTemplate返回单个值
	 * @param custId
	 * @return
	 */
	public String findCustomerNameById(int custId) {

		String sql = "SELECT NAME FROM CUSTOMER WHERE CUST_ID = ?";

		String name = (String) jdbcTemplate.queryForObject(sql,new Object[] { custId }, String.class);

		return name;

	}
	
	/**
	 * 使用jdbcTemplate查询
	 * @return
	 */
	public List<Customer> findCustomerNameAndAge() {

		String sql = " SELECT NAME , AGE  FROM CUSTOMER ";

		List<Customer> customers = new ArrayList<Customer>();

		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);

		for (Map<String, Object> row : list) {
			Customer customer = new Customer();
			customer.setName((String) row.get("NAME"));
			customer.setAge((Integer) row.get("AGE"));
			customers.add(customer);
		}

		return customers;

	}
	
	
	/**
	 * 查询总记录数
	 * @return
	 */
	public int findTotalCustomer() {

		String sql = "SELECT COUNT(*) FROM CUSTOMER";

		int total = jdbcTemplate.queryForInt(sql);

		return total;
	}

	/**
	 * 批量执行sql语句
	 * @param sql
	 */
	public void insertBatchSQL(String sql) {
		jdbcTemplate.batchUpdate(new String[] { sql });
	}

	
	public SimpleJdbcInsert getSimpleJdbcInsert() {
		return simpleJdbcInsert;
	}

	public void setSimpleJdbcInsert(SimpleJdbcInsert simpleJdbcInsert) {
		this.simpleJdbcInsert = simpleJdbcInsert;
	}

	public QueryDslJdbcTemplate getQueryDslJdbcTemplate() {
		return queryDslJdbcTemplate;
	}

	public void setQueryDslJdbcTemplate(QueryDslJdbcTemplate queryDslJdbcTemplate) {
		this.queryDslJdbcTemplate = queryDslJdbcTemplate;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}

	public void setNamedParameterJdbcTemplate(
			NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public SimpleJdbcCall getSimpleJdbcCall() {
		return simpleJdbcCall;
	}

	public void setSimpleJdbcCall(SimpleJdbcCall simpleJdbcCall) {
		this.simpleJdbcCall = simpleJdbcCall;
	}

	public CustomerMappingSqlQuery getCustomerMappingSqlQuery() {
		return customerMappingSqlQuery;
	}

	public void setCustomerMappingSqlQuery(
			CustomerMappingSqlQuery customerMappingSqlQuery) {
		this.customerMappingSqlQuery = customerMappingSqlQuery;
	}

	public CustomerSqlUpdate getCustomerSqlUpdate() {
		return customerSqlUpdate;
	}

	public void setCustomerSqlUpdate(CustomerSqlUpdate customerSqlUpdate) {
		this.customerSqlUpdate = customerSqlUpdate;
	}
	
}
