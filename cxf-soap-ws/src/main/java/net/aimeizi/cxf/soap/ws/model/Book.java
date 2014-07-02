package net.aimeizi.cxf.soap.ws.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by 冯靖 on 2014/7/1 0001.
 */
@XmlRootElement(name = "Book")
public class Book implements Serializable {

    private long bookId;
    private String bookName;
    private String author;

    public Book() {
    }

    public Book(long bookId, String bookName, String author) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bucketId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}