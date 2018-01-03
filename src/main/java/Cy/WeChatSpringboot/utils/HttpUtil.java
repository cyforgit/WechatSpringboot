package Cy.WeChatSpringboot.utils;


import javax.annotation.PostConstruct;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
@Component
public class HttpUtil {
	private static Logger logger = LogUtil.getLogger();
	private  static RestTemplateBuilder restTemplateBuilder;

	//静态注入
	@Autowired
	 public void setRestTemplateBuilder(RestTemplateBuilder restTemplateBuilder) {
		HttpUtil.restTemplateBuilder = restTemplateBuilder;
	}

	public static Object getFromUrl(String url, Class<?> c) {
		try {
			return restTemplateBuilder.build().getForObject(url, c);
		} catch (Exception e) {
			logger.warn("url:" + url);
			logger.warn("GET from url error:" + e.toString());
			return null;
		}
	}
}
