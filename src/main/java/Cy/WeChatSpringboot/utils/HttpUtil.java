package Cy.WeChatSpringboot.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class HttpUtil {
	
	@Autowired
	RestTemplate restTemplate;
}
