package com.example.overcast;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

import com.xebialabs.overcast.host.CloudHost;
import com.xebialabs.overcast.host.CloudHostFactory;

@Configuration
public class OvercastConfiguration {
	@Autowired private ConfigurableEnvironment env;

	@Bean(initMethod = "setup", destroyMethod = "teardown")
	@Qualifier("rabbitmq")
	public CloudHost rabbitmq() {
		return CloudHostFactory.getCloudHost("rabbitmq");
	}

	@Bean(initMethod = "setup", destroyMethod = "teardown")
	@Qualifier("redis")
	public CloudHost redis() {
		return CloudHostFactory.getCloudHost("redis");
	}

	@PostConstruct
	public void init() throws TimeoutException {
		final CloudHost redis = redis();
		final CloudHost rabbitmq = rabbitmq();

		final Map<String, Object> properties = new HashMap<>();
		properties.put("spring.rabbitmq.host", rabbitmq.getHostName());
		properties.put("spring.rabbitmq.port", rabbitmq.getPort(5672));

		properties.put("spring.redis.host", redis.getHostName());
		properties.put("spring.redis.port", redis.getPort(6379));

		final PropertySource<?> source = new MapPropertySource("overcast", properties);
		env.getPropertySources().addFirst(source);
	}
}
