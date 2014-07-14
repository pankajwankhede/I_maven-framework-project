package org.spring.jdbc.tutorials.model;

public class Customer {
	
	private int custId;
	private String name;
	private int age;
	
	public Customer() {
	}
	
	public Customer(int custId, String name, int age) {
		super();
		this.custId = custId;
		this.name = name;
		this.age = age;
	}

	public Customer(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", name=" + name + ", age=" + age
				+ "]";
	}
	
}
