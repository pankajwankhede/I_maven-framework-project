package net.aimeizi.cxf.soap.ws.client;

import net.aimeizi.cxf.soap.ws.service.HelloWorld;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by 冯靖 on 2014/7/1 0001.
 */
public class HelloServiceClient {

    public static void main(String[] args){

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"client-beans.xml"});

        HelloWorld client = (HelloWorld)context.getBean("client");

        String response = client.sayHi("Joe");
        System.out.println("Response: " + response);
        System.exit(0);
    }
}
