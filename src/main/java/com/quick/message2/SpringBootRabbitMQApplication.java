package com.quick.message2;

import com.quick.message2.listener.MessageListener;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootRabbitMQApplication {

	public final static String MESSAGE_QUEUE = "spring-boot-2";
	public final static String EXCHANGE = "spring-boot-exchange-new";
	public static boolean messageSent = true;
	public static String message = "";
    public static String messageHost = "quick-message-2";

	@Bean
	Queue queue() {
		return new Queue(MESSAGE_QUEUE, false);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(EXCHANGE);
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(MESSAGE_QUEUE);
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
	MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(MESSAGE_QUEUE);
		container.setMessageListener(listenerAdapter);
		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(MessageListener receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRabbitMQApplication.class, args);
	}
}
