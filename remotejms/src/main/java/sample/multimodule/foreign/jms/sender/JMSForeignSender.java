package sample.multimodule.foreign.jms.sender;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import sample.multimodule.domain.entity.Message;

@Configuration
public class JMSForeignSender {

	private static final Log LOG = LogFactory.getLog(JMSForeignSender.class);
	private int index = 1;

	@Autowired
	@Qualifier(value = "foreignRabbitTemplate")
	private RabbitTemplate template;
	
	private @Value("${application.logger.name}") String appName;
	
	@Autowired
	private TopicExchange topic;

	@Autowired
	@Qualifier(value = "messageInQueue")
	private Queue queue;

	private final String[] keys = { "quick.orange.rabbit", "lazy.orange.elephant", "quick.orange.fox", "lazy.brown.fox",
			"lazy.pink.rabbit", "quick.brown.fox" };

	public void send(Message message) {
		String key = keys[this.index];
		template.convertAndSend(topic.getName(), key, message);
		LOG.debug(" [x -- "+appName +" -- x] Sent '" + message + "'");
	}

	public void sendOtherDomain(Message message) {
		System.out.println("[x] --- Sending to Queue----" + queue.getName() + " ---- [x]");
		template.setQueue(queue.getName());
		LOG.debug("[x -- "+appName+ " -- x] --- Sending to Queue----" + queue.getName() + " ---- [x]");
		template.convertAndSend(queue.getName(), message);
	}
}
