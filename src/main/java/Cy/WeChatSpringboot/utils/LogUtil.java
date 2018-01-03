package Cy.WeChatSpringboot.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtil {
	private static  Logger logger = LogManager.getLogger(LogUtil.class);
	public static Logger getLogger() {
		return logger;
	}

}
