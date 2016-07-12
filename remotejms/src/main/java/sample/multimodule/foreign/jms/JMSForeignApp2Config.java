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
import org.springframework.context.annotation.Profile;

@Configuration
@Profile(value = "app2")
public class JMSForeignApp2Config {

	@Value("${message.in.remote.topic}")
	private String messageInRemoteTopic;

	@Value("${message.in.local.queue}")
	private String messageInLocalQueue;

	@Value("${message.in.remote.topic.queue1}")
	private String messageInRemoteTopicQueue1;

	@Value("${message.in.remote.topic.queue2}")
	private String messageInRemoteTopicQueue2;

	@Bean(name = "messageInQueue")
	public Queue queue() {
		return new Queue(getMessageInLocalQueue());
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
		return new Queue(getMessageInRemoteTopicQueue1());
	}

	@Bean
	public Queue autoDeleteQueue2() {
		return new Queue(getMessageInRemoteTopicQueue2());
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

	public String getMessageInLocalQueue() {
		return messageInLocalQueue;
	}

	public void setMessageInLocalQueue(String messageInLocalQueue) {
		this.messageInLocalQueue = messageInLocalQueue;
	}

	public String getMessageInRemoteTopicQueue1() {
		return messageInRemoteTopicQueue1;
	}

	public void setMessageInRemoteTopicQueue1(String messageInRemoteTopicQueue1) {
		this.messageInRemoteTopicQueue1 = messageInRemoteTopicQueue1;
	}

	public String getMessageInRemoteTopicQueue2() {
		return messageInRemoteTopicQueue2;
	}

	public void setMessageInRemoteTopicQueue2(String messageInRemoteTopicQueue2) {
		this.messageInRemoteTopicQueue2 = messageInRemoteTopicQueue2;
	}

}
