package net.aimeizi.cxf.soap.ws.service;

import net.aimeizi.cxf.soap.ws.model.Book;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by 冯靖 on 2014/7/1 0001.
 */

@WebService
public interface BookShelfService {

    @WebMethod
    public  String insertBook(Book book);

    @WebMethod
    public Book getBook(String title);
}
