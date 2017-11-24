package com.jiujichina;

import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;

import com.rabbitmq.client.ConnectionFactory;

public class RabbitmqConfig {
	
	@Autowired
	private org.springframework.amqp.rabbit.connection.ConnectionFactory connectionFactory;
	
	public RabbitAdmin rabbitAdmin(){
		return new RabbitAdmin(connectionFactory);
	}
}
