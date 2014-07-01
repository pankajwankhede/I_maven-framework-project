package net.aimeizi.spring.testng.example.common.exceptions;

/**
 * Created by 冯靖 on 2014/7/1 0001.
 */
public class NonExistentUserException extends Exception {

    private static final long serialVersionUID = 1L;

    public NonExistentUserException(String message) {
        super(message);
    }

}
