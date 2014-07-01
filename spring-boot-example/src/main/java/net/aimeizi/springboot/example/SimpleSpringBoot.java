package net.aimeizi.springboot.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 冯靖 on 14-3-7.
 */
@Controller
public class SimpleSpringBoot {

    @RequestMapping("/hello/{name}/{age}")
    @ResponseBody
    String hello(@PathVariable String name,@PathVariable("age") int age) {
        return "Hello, "+name+"! You Are "+age+" years old!";
    }
}
