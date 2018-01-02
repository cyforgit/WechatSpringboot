package Cy.WeChatSpringboot.utils;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import Cy.WeChatSpringboot.dao.redis.IRedisDao;
import Cy.WeChatSpringboot.pojo.Token;

@Service
public class TokenManager {
	private Logger logger = LogUtil.getLogger();
	@Autowired
	private IRedisDao redisDao;
	@Autowired
	private HttpUtil httpUtil;

	public String getToken() {
		String tokenString = null;
		try {
			String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx47e2c85287f3f989&secret=d4624c36b6795d1d99dcf0547af5443d";
			Token tokenObj = (Token) httpUtil.getFromUrl(url, Token.class);
			if (tokenObj != null) {
				redisDao.setValue("token", tokenObj.getAccess_token(), ConstantUtil.TOKEN_EXPIRE_TIME);
				return tokenObj.getAccess_token().toString();
			}

		} catch (Exception e) {
			System.out.println(e);
			logger.warn("GET TOKEN FROM URL:");
			logger.warn(e.toString());
		}
		return tokenString;
	}
}
