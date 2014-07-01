package net.aimeizi.javaconfig.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@Bean(name="helloWorldBean")
    public HelloWorldService helloWorldService() {
        return new HelloWorldService();
    }
	
}
