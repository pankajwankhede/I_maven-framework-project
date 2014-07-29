package org.spring.cache.example;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

	@Autowired
	EmployeeService employeeService;

	@RequestMapping("/index.htm")
	public String homePage(@RequestParam(required = false) Integer id,
			HashMap<String, String> map) {
		map.put("message", employeeService.getEmployee(id));
		return "index";
	}
}
