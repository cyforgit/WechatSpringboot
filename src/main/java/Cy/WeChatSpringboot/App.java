package Cy.WeChatSpringboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@MapperScan("Cy.WeChatSpringboot.Dao")
@RestController
@EnableAutoConfiguration
public class App {
	@Value("${test.msg}")
	private String msg;
	

	@RequestMapping("/")
	String home() {
		System.out.println(msg);
		return "Hello World!";
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class, args);
	}

}