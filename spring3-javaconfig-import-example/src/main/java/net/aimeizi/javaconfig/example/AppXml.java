package net.aimeizi.javaconfig.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppXml {

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		HelloWorldService hello = (HelloWorldService) context.getBean("helloWorldBean");
		hello.sayHello("Spring 3.2.3");

		GoodbyeService bye = (GoodbyeService) context.getBean("goodbyeBean");
		bye.sayGoodbye("Spring 3.2.3");

	}

}
