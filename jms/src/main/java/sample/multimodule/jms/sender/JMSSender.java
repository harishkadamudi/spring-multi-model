package sample.multimodule.jms.sender;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

//@Component
@Configuration
public class JMSSender {

	@Autowired
	private RabbitTemplate template;

	@Autowired
	private Queue queue;

	//	@Scheduled(fixedDelay = 1000, initialDelay = 500)
	public void send(String xmlMessage) {
		//String message = "Hello World!";
		this.template.convertAndSend(queue.getName(), xmlMessage);
		System.out.println(" [x] Sent '" + xmlMessage + "'");
	}
}
