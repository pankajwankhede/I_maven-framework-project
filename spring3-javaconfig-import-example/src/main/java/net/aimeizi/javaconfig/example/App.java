package net.aimeizi.javaconfig.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {

	ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

	HelloWorldService hello = (HelloWorldService) context.getBean("helloWorldBean");
	hello.sayHello("Spring 3.2.3");
	
	GoodbyeService bye = (GoodbyeService) context.getBean("goodbyeBean");
	bye.sayGoodbye("Spring 3.2.3");

	}
	
}
