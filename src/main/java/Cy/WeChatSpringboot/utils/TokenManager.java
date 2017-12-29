package Cy.WeChatSpringboot.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.redis.core.StringRedisTemplate;

@EnableAutoConfiguration
public class TokenManager {
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
}
