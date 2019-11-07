package com.example.demo;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Configuration
	public static class Config {

		@Bean
		RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
			RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
			rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
			rabbitTemplate.setChannelTransacted(true);
			return rabbitTemplate;
		}
	}

	@Service
	public static class MessageService {
		@Autowired
		private AmqpTemplate amqpTemplate;
		public void sendMessage(String content) {
			var message = new MessageContent();
			message.setContent(content);
			amqpTemplate.convertAndSend("output", "", message);
		}
		static class MessageContent {
			private String content;

			public void setContent(String content) {
				this.content = content;
			}

			public String getContent() {
				return content;
			}
		}
	}
}

