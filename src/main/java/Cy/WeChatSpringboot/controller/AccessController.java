package Cy.WeChatSpringboot.controller;

import java.security.MessageDigest;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import Cy.WeChatSpringboot.utils.LogUtil;
import Cy.WeChatSpringboot.utils.TokenManager;

@MapperScan("Cy.WeChatSpringboot.Dao")
@Controller
public class AccessController {
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Autowired
	private TokenManager tokenManager;
	Logger logger = LogUtil.getLogger();
	@Value("${test.msg}")
	private String msg;

	@RequestMapping("/restfultest")
	@ResponseBody
	public String restfultest() {
		return "restful string";
	}
	
	@RequestMapping("/tokentest")
	@ResponseBody
	public String tokentest() {
<<<<<<< HEAD
		return tokenManager.getToken();
=======
		return TokenManager.getToken();
>>>>>>> ad480ab6e46e97a3396c8c7273de46d9046aab7a
	}
	
	
	

	@RequestMapping("/redistest")
	@ResponseBody
	public String redistest() {
		return stringRedisTemplate.opsForValue().get("name");
	}

	@RequestMapping("/profiletest")
	@ResponseBody
	public String profiletest() {
		return msg;
	}

	@RequestMapping("/accessin")
	@ResponseBody
	public String accessIn(HttpServletRequest request) {
		logger.info("get request:" + request.getQueryString());

		if (request.getParameter("echostr") == null || request.getParameter("signature") == null
				|| request.getParameter("timestamp") == null || request.getParameter("nonce") == null) {
			logger.info("para error");
			return "para error";
		}
		if (checkAccessSignature(request.getParameter("signature"), request.getParameter("timestamp"),
				request.getParameter("nonce"))) {
			return request.getParameter("echostr");
		}
		return "server error";

	}

	private boolean checkAccessSignature(String signature, String timestamp, String nonce) {
		String token = "cytoken";
		String[] arr = new String[] { token, timestamp, nonce };
		Arrays.sort(arr);
		String sign = arr[0] + arr[1] + arr[2];
		MessageDigest md;
		String signatureResult = "";
		byte[] digest = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
			digest = md.digest(sign.getBytes("utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		signatureResult = byteToStr(digest);
		logger.info("signatureResult:" + signatureResult);
		return signature.toLowerCase().equals(signatureResult.toLowerCase());
	}

	private static String byteToStr(byte[] byteArray) {
		String strDigest = "";
		for (int i = 0; i < byteArray.length; i++) {
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}

	private static String byteToHexStr(byte mByte) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];
		String s = new String(tempArr);
		return s;
	}
}
