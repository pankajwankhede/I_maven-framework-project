package net.aimeizi.springboot.example;

import org.springframework.hateoas.ResourceSupport;

/**
 * 编写实体类继承org.springframework.hateoas.ResourceSupport
 * Created by 冯靖 on 14-3-7.
 */
public class Greeting extends ResourceSupport{
    private final String content;

    public Greeting(String content) {
        this.content = content;
    }


    public String getContent() {
        return content;
    }
}
