package Cy.WeChatSpringboot.utils;



import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class HttpUtil {
	private static Logger logger = LogUtil.getLogger();
	private  static RestTemplateBuilder restTemplateBuilder;
	
	private static RestTemplate restTemplate;
	//静态注入
	@Autowired
	 public void setRestTemplateBuilder(RestTemplateBuilder restTemplateBuilder) {
		HttpUtil.restTemplateBuilder = restTemplateBuilder;
		HttpUtil.restTemplate=HttpUtil.restTemplateBuilder.build();
	}

	public static Object getFromUrl(String url, Class<?> c) {
		try {
			return restTemplate.getForObject(url, c);
		} catch (Exception e) {
			logger.warn("url:" + url);
			logger.warn("GET from url error:" + e.toString());
			return null;
		}
	}
}
