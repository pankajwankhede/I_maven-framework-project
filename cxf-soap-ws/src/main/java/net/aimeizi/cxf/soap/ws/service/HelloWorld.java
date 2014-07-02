package net.aimeizi.cxf.soap.ws.service;

import javax.jws.WebService;

/**
 * Created by 冯靖 on 2014/7/1 0001.
 */

@WebService
public interface HelloWorld {
    String sayHi(String text);
}
