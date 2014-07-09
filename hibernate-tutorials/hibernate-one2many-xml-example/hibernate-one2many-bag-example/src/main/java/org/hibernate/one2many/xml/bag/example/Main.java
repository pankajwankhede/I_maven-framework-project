package org.hibernate.one2many.xml.bag.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {

	public static void main(String[] args) {

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();

		
		Department department = new Department();
		department.setDepartmentName("Sales");
		session.save(department);
		
		Employee emp1 = new Employee("Nina", "Mayers", "111");
		Employee emp2 = new Employee("Tony", "Almeida", "222");
		
		emp1.setDepartment(department);
		emp2.setDepartment(department);
		
		department.getEmployees().add(emp1);
		department.getEmployees().add(emp2);
		
		session.save(emp1);
		session.save(emp2);
		session.save(emp1);

		session.getTransaction().commit();
		session.close();
	}

}
