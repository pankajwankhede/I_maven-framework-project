package net.aimeizi.cxf.rest.ws.service;

import net.aimeizi.cxf.rest.ws.HashDB;
import net.aimeizi.cxf.rest.ws.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by 冯靖 on 2014/7/1 0001.
 */
public class BookService {

protected final Logger log = LoggerFactory.getLogger(BookService.class);


    @POST
    @Path("/getbook/{name}")
    @Produces({"application/xml","application/json"})
    @Consumes({"application/xml","application/json","application/x-www-form-urlencoded"})
    public Response getBucket(@PathParam("name") String name) {
        log.debug("name : " + name);
        Book book = null;
        try {
            book = HashDB.getBook(URLDecoder.decode(name, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        if(book == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }else{
            return Response.ok(book).build();
        }
    }

    @POST
    @Path("/addbook")
    @Produces({"application/xml","application/json"})
    @Consumes({"application/xml","application/json","application/x-www-form-urlencoded"})
    public Response addBook(@FormParam("name") String bookName,
                            @FormParam("author") String author) {
        log.debug("inside addBook");
        Book book = new Book();
        book.setBookName(bookName);
        book.setAuthor(author);
        HashDB.insertBook(book);
        if(HashDB.getBook(bookName) == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }else{
            return Response.ok(book).build();
        }

    }
}
