package uk.co.jemos.experiments.jmx;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MemTest {
	public static void main(String[] args) {

		new ClassPathXmlApplicationContext("classpath:jemos-jmx-appCtx.xml");

	}
}