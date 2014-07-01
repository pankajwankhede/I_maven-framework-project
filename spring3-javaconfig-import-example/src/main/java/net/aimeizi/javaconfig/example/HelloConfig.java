package net.aimeizi.javaconfig.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloConfig {
	
	@Bean(name="helloWorldBean")
    public HelloWorldService helloWorldService() {
        return new HelloWorldService();
    }
	
}
