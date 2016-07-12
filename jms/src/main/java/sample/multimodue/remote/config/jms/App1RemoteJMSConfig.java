package sample.multimodue.remote.config.jms;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import sample.multimodue.remote.consumer.jms.RemoteJMSConsumer;

@Configuration
@Profile(value = "app1")
public class App1RemoteJMSConfig {

	@Value("${message.out.remote.queue}")
	private String messageOutRemoteQueue;

	@Bean(name = "remoteQueue")
	Queue queue() {
		return new Queue(getMessageOutRemoteQueue(), false);
	}

	@Bean
	MessageListenerAdapter listenerAdapter(RemoteJMSConsumer receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}

	@Bean
	RemoteJMSConsumer receiver() {
		return new RemoteJMSConsumer();
	}

	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory("127.0.0.1");
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");
		return connectionFactory;
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(getMessageOutRemoteQueue());
		container.setMessageListener(listenerAdapter);
		return container;
	}

	public String getMessageOutRemoteQueue() {
		return messageOutRemoteQueue;
	}

	public void setMessageOutRemoteQueue(String messageOutRemoteQueue) {
		this.messageOutRemoteQueue = messageOutRemoteQueue;
	}
}
