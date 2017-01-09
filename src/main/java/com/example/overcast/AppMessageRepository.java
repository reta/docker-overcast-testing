package com.example.overcast;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AppMessageRepository {
	@Autowired private StringRedisTemplate redisTemplate;
	
	public void persist(final String message) {
		redisTemplate.opsForList().rightPush("messages", message);
	}
	
	public Collection<String> readAll() {
		return redisTemplate.opsForList().range("messages", 0, -1);
	}
	
	public long size() {
		return redisTemplate.opsForList().size("messages");
	}
}
