package Cy.WeChatSpringboot;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableAutoConfiguration
@EnableCaching
public class App {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class, args);
		
	}

}