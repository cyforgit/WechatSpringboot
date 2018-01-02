package Cy.WeChatSpringboot.dao.redis;


public interface IRedisDao {
	public Object getValue(String key);

	public void setValue(String key, String value);

	public void setValue(String key, String value, long time);

	public boolean isExist(String key);

	public void delete(String key);

	public void expire(String key, long timeout);
}
