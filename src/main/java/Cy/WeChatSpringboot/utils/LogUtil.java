package Cy.WeChatSpringboot.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtil {
	private static final Logger logger = LogManager.getLogger("mylog");

	public static Logger getLogger() {
		return logger;
	}

}
