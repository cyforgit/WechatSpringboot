package Cy.WeChatSpringboot.utils;


import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Service
public class HttpUtil {
	private static Logger logger = LogUtil.getLogger();
	
	@Autowired
	private   RestTemplateBuilder restTemplateBuilder;

	public Object getFromUrl(String url, Class<?> c) {
		try {
			return restTemplateBuilder.build().getForObject(url, c);
		} catch (Exception e) {
			logger.warn("url:" + url);
			logger.warn("GET from url error:" + e.toString());
			return null;
		}
	}
}
