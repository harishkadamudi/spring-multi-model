package sample.multimodule.foreign.jms.sender;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JMSForeignSender {

	private static final Log LOG = LogFactory.getLog(JMSForeignSender.class);
	private int index = 1;

	@Autowired
	@Qualifier(value = "foreignRabbitTemplate")
	private RabbitTemplate template;

	@Autowired
	private TopicExchange topic;

	private final String[] keys = { "quick.orange.rabbit", "lazy.orange.elephant", "quick.orange.fox", "lazy.brown.fox",
			"lazy.pink.rabbit", "quick.brown.fox" };

	public void send(String message) {
		String key = keys[this.index];
		template.convertAndSend(topic.getName(), key, message);
		LOG.debug(" [x] Sent '" + message + "'");
	}

}
