package com.example.overcast;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
@EnableAutoConfiguration
@EnableRabbit
@ComponentScan(basePackageClasses = AppConfiguration.class)
public class AppConfiguration {
	@Bean
	Queue queue() {
		return new Queue("app.queue", false);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange("app.exchange");
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(queue.getName());
	}
	
	@Bean
	StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
		return new StringRedisTemplate(connectionFactory);
	}
}
