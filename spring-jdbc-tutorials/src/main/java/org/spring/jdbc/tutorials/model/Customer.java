package org.spring.jdbc.tutorials.model;

import java.util.HashSet;
import java.util.Set;

public class Customer {
	
	private int custId;
	private String name;
	private int age;
	
	private Set<Address> addresses = new HashSet<Address>();
	
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
	
	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}
	
	public void addAddress(Address address) {
		this.addresses.add(address);
	}

	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", name=" + name + ", age=" + age
				+ ", addresses=" + addresses + "]";
	}

	
	
}
