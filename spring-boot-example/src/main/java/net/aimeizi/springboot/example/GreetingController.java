package net.aimeizi.springboot.example;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * 使用spring-hateoas创建RESTful Web Service
 * 1.编写实体类继承org.springframework.hateoas.ResourceSupport
 * 2.编写一个返回类型为HttpEntity<T>的方法
 * 3.使用org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo方法
 * Created by 冯靖 on 14-3-7.
 */
@Controller
public class GreetingController {

    private static final String template = "Hello, %s!";
    //private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "/greeting",method = {RequestMethod.GET})
    @ResponseBody
    public HttpEntity<Greeting> greeting(
            @RequestParam(value="name", required=false, defaultValue="World") String name) {
        Greeting greeting = new Greeting(String.format(template, name));
        greeting.add(linkTo(methodOn(GreetingController.class).greeting(name)).withSelfRel());
        return new ResponseEntity<Greeting>(greeting, HttpStatus.OK);
    }




    @RequestMapping(value = "/print/{name}",method = {RequestMethod.GET})
    @ResponseBody
    public HttpEntity<Greeting> print(@PathVariable(value = "name")String name){

        Greeting greeting=new Greeting(String.format("%s,i will be back!",name));
        greeting.add(linkTo(methodOn(GreetingController.class).print(name)).withSelfRel());

        return new ResponseEntity<Greeting>(greeting,HttpStatus.OK);
    }




}
