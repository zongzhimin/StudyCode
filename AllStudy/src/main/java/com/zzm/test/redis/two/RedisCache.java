package com.zzm.test.redis.two;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisCache {
	@Resource
    private RedisTemplate<String, String> redisTemplate;
	
	
	//测试用
    public RedisTemplate<String, String> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	/**
     * 添加缓存数据
     * @param key
     * @param obj
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> boolean putCache(String key, T obj) throws Exception {
        final byte[] bkey = key.getBytes();
        final byte[] bvalue = SerializerUtil.serializeObj(obj);
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.setNX(bkey, bvalue);
            }
        });
        return result;
    }

    /**
     * 添加缓存数据，设定缓存失效时间
     * @param key
     * @param obj
     * @param expireTime
     * @param <T>
     * @throws Exception
     */
    public <T> void putCacheWithExpireTime(String key, T obj, final long expireTime) throws Exception {
        final byte[] bkey = key.getBytes();
        final byte[] bvalue = SerializerUtil.serializeObj(obj);
        redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                connection.setEx(bkey, expireTime, bvalue);
                return true;
            }
        });
    }

    /**
     * 根据key取缓存数据
     * @param key
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T getCache(final String key) throws Exception {
        byte[] result = redisTemplate.execute(new RedisCallback<byte[]>() {
            @Override
            public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.get(key.getBytes());
            }
        });
        if (result == null) {
            return null;
        }
        return (T) SerializerUtil.deserializeObj(result);
    }
}
