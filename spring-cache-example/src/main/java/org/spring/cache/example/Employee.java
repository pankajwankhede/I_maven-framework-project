package org.spring.cache.example;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Employee implements Serializable{

	private int id;
	private String empname;
	private int age;
	private String phone;
	
	public Employee() {
	}
	
	public Employee(int id, String empname, int age, String phone) {
		super();
		this.id = id;
		this.empname = empname;
		this.age = age;
		this.phone = phone;
	}
	
	public Employee(String empname) {
		super();
		this.empname = empname;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", empname=" + empname + ", age=" + age
				+ ", phone=" + phone + "]";
	}
	
}
