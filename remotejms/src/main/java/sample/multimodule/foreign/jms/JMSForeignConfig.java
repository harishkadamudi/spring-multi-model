package sample.multimodule.foreign.jms;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JMSForeignConfig {

	@Value("${message.in.remote.topic}")
	private String messageInRemoteTopic;
	
	@Bean(name = "messageInQueue")
	public Queue queue() {
		return new Queue("queue.messageinlocal");
	}

	@Bean
	public TopicExchange topic() {
		return new TopicExchange(getMessageInRemoteTopic());
	}

	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory("127.0.0.1");
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");
		return connectionFactory;
	}

	@Bean(name = "foreignRabbitTemplate")
	public RabbitTemplate rabbitTemplate() {
		return new RabbitTemplate(connectionFactory());
	}

	public String getMessageInRemoteTopic() {
		return messageInRemoteTopic;
	}

	public void setMessageInRemoteTopic(String messageInRemoteTopic) {
		this.messageInRemoteTopic = messageInRemoteTopic;
	}

	@Bean
	public Queue autoDeleteQueue1() {
		return new Queue("T1Q1");
	}

	@Bean
	public Queue autoDeleteQueue2() {
		return new Queue("T1Q2");
	}

	@Bean
	public Binding binding1a(TopicExchange topic, Queue autoDeleteQueue1) {
		return BindingBuilder.bind(autoDeleteQueue1).to(topic).with("*.orange.*");
	}

	@Bean
	public Binding binding1b(TopicExchange topic, Queue autoDeleteQueue1) {
		return BindingBuilder.bind(autoDeleteQueue1).to(topic).with("*.*.rabbit");
	}

	@Bean
	public Binding binding2a(TopicExchange topic, Queue autoDeleteQueue2) {
		return BindingBuilder.bind(autoDeleteQueue2).to(topic).with("lazy.#");
	}

}
