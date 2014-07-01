package net.aimeizi.javaconfig.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		/**
		 * Create a new ApplicationContext, deriving bean definition from the
		 * given annotated class and automatically refreshing the context.
		 */
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		/**
		 * Return an instance, which may be shared or independent, of the
		 * specified bean.
		 */
		HelloWorldService obj = (HelloWorldService) context.getBean("helloWorldBean");
		obj.setName("Spring 3.2.3");
		obj.sayHello();

	}

}
