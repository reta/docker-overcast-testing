package com.example.overcast;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppQueueMessageSender {
	@Autowired private RabbitTemplate rabbitTemplate;
	
	public void send(final String message) {
		rabbitTemplate.convertAndSend("app.exchange", "app.queue", message);
	}
}
