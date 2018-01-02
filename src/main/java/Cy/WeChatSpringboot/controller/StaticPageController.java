package Cy.WeChatSpringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 映射静态页面，页面通过restful接口获取并展示数据。
 */
@Controller
public class StaticPageController {
	@RequestMapping("/index")
	public String getIndex() {
		return "/index";
	}
}
