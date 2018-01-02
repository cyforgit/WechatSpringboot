package Cy.WeChatSpringboot.utils;

import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import Cy.WeChatSpringboot.pojo.users;

public class JsonUtil {
	private static Logger logger = LogUtil.getLogger();
	private static ObjectMapper mapper = new ObjectMapper();

	public static Object getObjectFromJson(String jsonString, Class<?> c) {
		try {
			return mapper.readValue(jsonString, c);
		} catch (Exception e) {
			logger.info("Get Object From Json Error:" + e.toString());
			return null;
		}
	}

	/**
	 * 将对象转为JSON字符串
	 * 
	 * @param object
	 * @return
	 */
	public static String getJsonFromObject(Object object) {
		String jsonString;
		ObjectMapper mapper = new ObjectMapper();
		// 美化输出，仅调试使用
		// mapper.enable(SerializationFeature.INDENT_OUTPUT);
		// 允许输出空对象
		mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		try {
			jsonString = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			jsonString = null;
			e.printStackTrace();
		}
		return jsonString;
	}

}
