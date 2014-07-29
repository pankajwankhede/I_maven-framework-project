package org.spring.cache.example;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

	Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * @Cacheable：负责将方法的返回值加入到缓存中
	 * 
	 * @CacheEvict：负责清除缓存
	 * 
	 * @Cacheable 支持如下几个参数：
	 * 
	 *            value：缓存位置名称，不能为空，如果使用EHCache，就是ehcache.xml中声明的cache的name
	 * 
	 *            key：缓存的key，默认为空，既表示使用方法的参数类型及参数值作为key，支持SpEL
	 * 
	 *            condition：触发条件，只有满足条件的情况才会加入缓存，默认为空，既表示全部都加入缓存，支持SpEL
	 * 
	 * @CacheEvict 支持如下几个参数：
	 * 
	 *             value：缓存位置名称，不能为空，同上
	 * 
	 *             key：缓存的key，默认为空，同上
	 * 
	 *             condition：触发条件，只有满足条件的情况才会清除缓存，默认为空，支持SpEL
	 * 
	 *             allEntries：true表示清除value中的全部缓存，默认为false
	 */

	@Cacheable(value = "employeeCache"/*,condition="#empname.length() <= 4"*/)
	// 使用了一个缓存名叫 employeeCache
	public Employee getEmployeeByName(String empname) {
		// 方法内部实现不考虑缓存逻辑，直接实现业务
		return getFromDB(empname);
	}

	@Cacheable(value = "employeeCache", key = "#id")
	// 使用了一个缓存名为employeeCache的缓存
	public String getEmployee(Integer id) {
		logger.info("get employee called " + id);
		return "getEmployee" + id;
	}

	@Cacheable(value = "employeeCache", key = "#id", condition = "#id <= 10")
	// 使用了一个缓存名为employeeCache的缓存,缓存id小于等于10
	public String findEmployee(Integer id) {
		logger.info("get employee called");
		return "findEmployee" + id;
	}

	@CacheEvict(value = "employeeCache", allEntries = true)
	public List<Employee> listEmployee() {
		List<Employee> emps = new ArrayList<Employee>();
		return emps;
	}

	@CachePut(value = "employeeCache", key = "#employee.getEmpname()")
	// 清空 employeeCache 缓存
	public void updateEmployee(Employee employee) {
		updateDB(employee);
	}

	@CacheEvict(value = "employeeCache", allEntries = true)
	// 清空 employeeCache 缓存
	public void reload() {
		
	}

	private Employee getFromDB(String empname) {
		System.out.println("real querying db..." + empname);
		return new Employee(empname);
	}

	private void updateDB(Employee employee) {
		System.out.println("real update db..." + employee.getEmpname());
	}

}
