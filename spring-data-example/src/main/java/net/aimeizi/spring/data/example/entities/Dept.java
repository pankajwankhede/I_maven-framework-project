package net.aimeizi.spring.data.example.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Dept {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int deptid;
	
	private String deptname;
	
	private String deptphonenumber;
	
	@OneToMany(mappedBy="dept",cascade=CascadeType.ALL)
	private Set<Emp> emps = new HashSet<Emp>();

	public int getDeptid() {
		return deptid;
	}

	public void setDeptid(int deptid) {
		this.deptid = deptid;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getDeptphonenumber() {
		return deptphonenumber;
	}

	public void setDeptphonenumber(String deptphonenumber) {
		this.deptphonenumber = deptphonenumber;
	}

	public Set<Emp> getEmps() {
		return emps;
	}

	public void setEmps(Set<Emp> emps) {
		this.emps = emps;
	}
	
	
}
