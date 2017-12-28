package Cy.WeChatSpringboot;

import org.apache.logging.log4j.Logger;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.cache.annotation.EnableCaching;

import Cy.WeChatSpringboot.utils.LogUtil;
@SpringBootApplication
@EnableAutoConfiguration
@EnableCaching
public class App {
	Logger logger=LogUtil.getLogger();
	public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class, args);
		
	}

}