package Cy.WeChatSpringboot.dao.redis;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class RedisDaoImpl implements IRedisDao {
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Override
	public Object getValue(String key) {
		return stringRedisTemplate.opsForValue().get(key);
	}

	@Override
	public void setValue(String key, String value) {
		stringRedisTemplate.opsForValue().set(key, value);
	}

	@Override
	public void setValue(String key, String value, long timeout) {
		stringRedisTemplate.opsForValue().set(key, value, timeout, TimeUnit.MILLISECONDS);
	}

	@Override
	public boolean isExist(String key) {
		return stringRedisTemplate.hasKey(key);
	}

	@Override
	public void delete(String key) {
		stringRedisTemplate.delete(key);
	}

	@Override
	public void expire(String key, long timeout) {
		stringRedisTemplate.expire(key, timeout, TimeUnit.MILLISECONDS);

	}

}
