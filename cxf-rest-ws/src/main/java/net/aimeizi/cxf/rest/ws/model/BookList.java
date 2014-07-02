package net.aimeizi.cxf.rest.ws.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 冯靖 on 2014/7/1 0001.
 */
@XmlRootElement(name="BookList")
public class BookList {
    private List<Book> bookList;

    public List<Book> getBookList() {
        if(bookList == null){
        bookList = new ArrayList<Book>();
        }
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
