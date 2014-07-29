package org.spring.cache.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeServiceTest {

	@Autowired
	EmployeeService employeeService;

	@Test
	public void getEmployee() {
		employeeService.getEmployee(1);
		employeeService.getEmployee(1);
		employeeService.getEmployee(2);
		employeeService.getEmployee(2);
		employeeService.getEmployee(3);
		employeeService.getEmployee(3);
	}

	@SuppressWarnings("unused")
	@Test
	public void testCache() {
		// 第一次查询，应该走数据库
		System.out.print("first query...");
		employeeService.getEmployeeByName("somebody");
		// 第二次查询，应该不查数据库，直接返回缓存的值
		System.out.print("second query...");
		employeeService.getEmployeeByName("somebody");
		System.out.println();

		System.out.println("start testing clear cache..."); // 更新某个记录的缓存，首先构造两个账号记录，然后记录到缓存中
		Employee employee1 = employeeService.getEmployeeByName("somebody1");
		Employee employee2 = employeeService.getEmployeeByName("somebody2");
		// 开始更新其中一个
		employee1.setId(1212);
		employeeService.updateEmployee(employee1);
		employeeService.getEmployeeByName("somebody1");// 因为被更新了，所以会查询数据库
		employeeService.getEmployeeByName("somebody2");// 没有更新过，应该走缓存
		employeeService.getEmployeeByName("somebody1");// 再次查询，应该走缓存 // 更新所有缓存
		employeeService.reload();
		employeeService.getEmployeeByName("somebody1");// 应该会查询数据库
		employeeService.getEmployeeByName("somebody2");// 应该会查询数据库
		employeeService.getEmployeeByName("somebody1");// 应该走缓存
		employeeService.getEmployeeByName("somebody2");// 应该走缓存
	}

	@Test
	public void testCacheCondition() {
		employeeService.getEmployeeByName("somebody");// 长度大于 4，不会被缓存
		employeeService.getEmployeeByName("sbd");// 长度小于 4，会被缓存
		employeeService.getEmployeeByName("somebody");// 还是查询数据库
		employeeService.getEmployeeByName("sbd");// 会从缓存返回
	}

	@Test
	public void testCachePut() {
		Employee employee = employeeService.getEmployeeByName("someone");
		employee.setAge(18);
		employeeService.updateEmployee(employee);
		employee.setAge(28);
		employeeService.updateEmployee(employee);
		employee = employeeService.getEmployeeByName("someone");
		System.out.println(employee.getEmpname());
	}

}
