package Cy.WeChatSpringboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import Cy.WeChatSpringboot.dao.usersMapper;

@MapperScan("Cy.WeChatSpringboot.Dao")
@Controller
@EnableAutoConfiguration
public class App {
	@Autowired
	private usersMapper users;

	@Value("${test.msg}")
	private String msg;

	@RequestMapping("/")
	String home() {
		System.out.println(users.selectByPrimaryKey(1).toString());
		return "Hello World!";
	}

	@RequestMapping("/hello")
	public String hello(Model model) {
		model.addAttribute("name", "world");

		return "/hello";
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class, args);
	}

}