package net.aimeizi.spring.data.example.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table
public class Emp {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int empid;
	
	@NotEmpty(message="please enter your emp empname.")
	@Size(min=6,max=16,message="empname长度在6~16个字符之间.")
	private String empname;
	
	@Email
	@NotEmpty
	private String empaddress;
	
	@ManyToOne(cascade={CascadeType.REFRESH,CascadeType.REMOVE,CascadeType.MERGE})
	@JoinColumn(name="deptid")
	private Dept dept;

	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public String getEmpaddress() {
		return empaddress;
	}

	public void setEmpaddress(String empaddress) {
		this.empaddress = empaddress;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}
	
	
}
