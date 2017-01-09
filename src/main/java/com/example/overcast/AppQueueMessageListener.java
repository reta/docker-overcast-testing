package com.example.overcast;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppQueueMessageListener {
	@Autowired private AppMessageRepository repository;
	
	@RabbitListener(
		queues = "app.queue", 
		containerFactory = "rabbitListenerContainerFactory", 
		admin = "amqpAdmin"
	)
	public void onMessage(final String message) {
		repository.persist(message);
	}
}
