package sample.multimodule.jms.sender;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JMSRemoteSender {

	private static final Log LOG = LogFactory.getLog(JMSRemoteSender.class);

	@Autowired
	private RabbitTemplate template;

	@Autowired
	@Qualifier(value = "remoteQueue")
	private Queue queue;

	public void send() {

		LOG.debug(" Remote queue Name " + queue.getName());
		String message = "Hello World!";
		this.template.convertAndSend(queue.getName(), message);
		System.out.println(" [x] Sent to Remote Queue " + queue.getName() + " + message + ");
	}
}
