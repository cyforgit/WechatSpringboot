package Cy.WeChatSpringboot.utils;


import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
<<<<<<< HEAD
import org.springframework.stereotype.Service;
@Service
=======
@Component
>>>>>>> ad480ab6e46e97a3396c8c7273de46d9046aab7a
public class HttpUtil {
	private static Logger logger = LogUtil.getLogger();
	
	@Autowired
<<<<<<< HEAD
	private   RestTemplateBuilder restTemplateBuilder;

	public Object getFromUrl(String url, Class<?> c) {
=======
	private  static RestTemplateBuilder restTemplateBuilder;

	public static Object getFromUrl(String url, Class<?> c) {
>>>>>>> ad480ab6e46e97a3396c8c7273de46d9046aab7a
		try {
			return restTemplateBuilder.build().getForObject(url, c);
		} catch (Exception e) {
			logger.warn("url:" + url);
			logger.warn("GET from url error:" + e.toString());
			return null;
		}
	}
}
