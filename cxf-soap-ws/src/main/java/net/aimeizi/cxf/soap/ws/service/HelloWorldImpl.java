package net.aimeizi.cxf.soap.ws.service;

import javax.jws.WebService;
/**
 * Created by Administrator on 2014/7/1 0001.
 */
@WebService(endpointInterface = "net.aimeizi.cxf.soap.ws.service.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

    public String sayHi(String text) {
        System.out.println("sayHi called");
        return "Hello " + text;
    }
}