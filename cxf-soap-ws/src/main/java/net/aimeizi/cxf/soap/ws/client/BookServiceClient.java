package net.aimeizi.cxf.soap.ws.client;

import net.aimeizi.cxf.soap.ws.model.Book;
import net.aimeizi.cxf.soap.ws.service.BookShelfService;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;


/**
 * Created by 冯靖 on 2014/7/1 0001.
 */
public class BookServiceClient {


    public static void main(String[] args){
        String serviceUrl = "http://localhost:8080/bookshelfservice";
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(BookShelfService.class);
        factory.setAddress(serviceUrl);
        BookShelfService bookService = (BookShelfService) factory.create();

        //insert book
        Book book = new Book();
        book.setAuthor("Issac Asimov");
        book.setBookName("Foundation and Earth");

        String result = bookService.insertBook(book);

        System.out.println("result : " + result);

        book = new Book();
        book.setAuthor("Issac Asimov");
        book.setBookName("Foundation and Empire");

        result = bookService.insertBook(book);

        System.out.println("result : " + result);

        book = new Book();
        book.setAuthor("Arthur C Clarke");
        book.setBookName("Rama Revealed");

        result = bookService.insertBook(book);

        System.out.println("result : " + result);

        //retrieve book

        book = bookService.getBook("Foundation and Earth");

        System.out.println("book name : " + book.getBookName());
        System.out.println("book author : " + book.getAuthor());
    }

}
