package net.aimeizi.rmi.server.starter;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RMIServerStarter {

	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("rmiServerAppContext.xml");
	}

}
