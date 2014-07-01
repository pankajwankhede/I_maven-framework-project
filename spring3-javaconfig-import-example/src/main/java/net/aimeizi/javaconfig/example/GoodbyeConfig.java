package net.aimeizi.javaconfig.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoodbyeConfig {

	@Bean(name="goodbyeBean")
    public GoodbyeService goodByeService() {
        return new GoodbyeService();
    }
	
}
