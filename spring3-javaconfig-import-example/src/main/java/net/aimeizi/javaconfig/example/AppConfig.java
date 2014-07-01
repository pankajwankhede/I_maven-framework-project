package net.aimeizi.javaconfig.example;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value={HelloConfig.class,GoodbyeConfig.class})
public class AppConfig {

}
