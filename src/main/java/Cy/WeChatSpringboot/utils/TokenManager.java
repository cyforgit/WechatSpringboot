package Cy.WeChatSpringboot.utils;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import Cy.WeChatSpringboot.dao.redis.IRedisDao;
import Cy.WeChatSpringboot.pojo.Token;
<<<<<<< HEAD

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
=======
@Service
public class TokenManager {
	private  Logger logger = LogUtil.getLogger();
	@Autowired
	private IRedisDao redisDao;

	public  String getToken() {
		String tokenString = null;
		System.out.println("2222");
		System.out.println(this.redisDao);
		try {
			System.out.println(redisDao.toString());
			System.out.println("****");
			System.out.println(redisDao.isExist("token"));
//			if (redisDao.isExist("token")) {
//				tokenString = (String) redisDao.getValue("token");
//				if (!StringUtils.isEmpty(tokenString)) {
//					return tokenString;
//				}
//			}

		} catch (Exception e) {
			logger.warn("GET TOKEN FROM REDIS ERROR:");
			logger.warn(e.toString());
		}
		try {
			String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx47e2c85287f3f989&secret=d4624c36b6795d1d99dcf0547af5443d";
			Token tokenObj = (Token) HttpUtil.getFromUrl(url, Token.class);
>>>>>>> ad480ab6e46e97a3396c8c7273de46d9046aab7a
			if (tokenObj != null) {
				redisDao.setValue("token", tokenObj.getAccess_token(), ConstantUtil.TOKEN_EXPIRE_TIME);
				return tokenObj.getAccess_token().toString();
			}

		} catch (Exception e) {
<<<<<<< HEAD
			System.out.println(e);
=======
>>>>>>> ad480ab6e46e97a3396c8c7273de46d9046aab7a
			logger.warn("GET TOKEN FROM URL:");
			logger.warn(e.toString());
		}
		return tokenString;
	}
}
