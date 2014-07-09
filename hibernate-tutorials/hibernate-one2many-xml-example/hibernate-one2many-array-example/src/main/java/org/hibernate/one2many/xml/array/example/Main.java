package org.hibernate.one2many.xml.array.example;

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
		
		department.setEmployees(new Employee[] {emp1, emp2});
		
		session.save(department);

		session.getTransaction().commit();
		session.close();
	}
}
