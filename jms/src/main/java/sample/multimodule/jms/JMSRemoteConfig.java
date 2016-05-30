package sample.multimodule.jms;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JMSRemoteConfig {

	@Value("${message.out.remote.queue}")
	private String messageOutRemoteQueue;

	@Bean(name = "remoteQueue")
	Queue queue() {
		return new Queue(getMessageOutRemoteQueue(), false);
	}

	@Bean
	MessageListenerAdapter listenerAdapter(Receiver receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}

	@Bean
	Receiver receiver() {
		return new Receiver();
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
