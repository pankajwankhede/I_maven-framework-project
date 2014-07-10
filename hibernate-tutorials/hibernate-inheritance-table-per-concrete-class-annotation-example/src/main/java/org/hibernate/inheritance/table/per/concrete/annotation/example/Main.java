package org.hibernate.inheritance.table.per.concrete.annotation.example;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {

	public static void main(String[] args) {

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();

		
		Person person = new Person("Steve", "Balmer");
		person.setPersonId(1L);
		session.save(person);

		Employee employee = new Employee("James", "Gosling", "Marketing", new Date());
		employee.setPersonId(2L);
		session.save(employee);

		Owner owner = new Owner("Bill", "Gates", 300L, 20L);
		owner.setPersonId(3L);
		session.save(owner);

		
		session.getTransaction().commit();
		session.close();

	}
}
