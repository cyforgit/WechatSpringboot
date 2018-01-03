package Cy.WeChatSpringboot.service;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import Cy.WeChatSpringboot.dao.redis.IRedisDao;
import Cy.WeChatSpringboot.pojo.ErrorInfo;
import Cy.WeChatSpringboot.pojo.Token;
import Cy.WeChatSpringboot.utils.ConstantUtil;
import Cy.WeChatSpringboot.utils.HttpUtil;
import Cy.WeChatSpringboot.utils.JsonUtil;
import Cy.WeChatSpringboot.utils.LogUtil;

@Component
public class TokenManager {
	private static Logger logger = LogUtil.getLogger();
	private static IRedisDao redisDao;

	// 静态注入
	@Autowired
	public void setRedisDao(IRedisDao redisDao) {
		TokenManager.redisDao = redisDao;
	}

	public static String getToken() {
		System.out.println("get token");
		String tokenString = null;
		Token tokenObj = null;
		try {
			if (redisDao.isExist("token")) {
				return redisDao.getValue("token").toString();
			}
			logger.info("get Token from url");
			String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=&secret=";
			String response = HttpUtil.getFromUrl(url, String.class).toString();
			tokenObj = (Token) JsonUtil.getObjectFromJson(response, Token.class);
			if (tokenObj != null) {
				redisDao.setValue("token", tokenObj.getAccess_token(), ConstantUtil.TOKEN_EXPIRE_TIME);
				return tokenObj.getAccess_token().toString();
			} else {
				logger.warn("Get Token Error:"+response);
			}

		} catch (Exception e) {
			logger.warn("GET TOKEN FROM URL:");
			logger.warn(e.toString());
		}
		return tokenString;
	}
}
