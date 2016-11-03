package sample.multimodule.domain;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import sample.multimodule.domain.entity.Message;

@Component
@Profile(value = "app2")
public class App2DomainSender {

	private static final Log LOG = LogFactory.getLog(App2DomainSender.class);

	@Value("${message.from.local.queue}")
	private String messageInLocalQueue;

	@Bean(name = "messageInQueue")
	public Queue queue() {
		return new Queue(getMessageInLocalQueue());
	}

	@Autowired
	private RabbitTemplate template;

	public String getMessageInLocalQueue() {
		return messageInLocalQueue;
	}

	public void setMessageInLocalQueue(String messageInLocalQueue) {
		this.messageInLocalQueue = messageInLocalQueue;
	}

	public void publicToLocalQueue(Message msg) {
		LOG.debug("---- Publishing to Internal Queue ------");
		template.convertAndSend(this.queue().getName(), msg);
	}
}
