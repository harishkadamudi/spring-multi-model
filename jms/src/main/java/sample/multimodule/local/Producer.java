package sample.multimodule.local;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Producer {

	private static final Log LOG = LogFactory.getLog(Producer.class);
	
	@Autowired
	private RabbitTemplate template;

	@Autowired
	@Qualifier(value = "localQueue")
	private Queue queue;

	public void send(String message) {
		LOG.debug(" ------[x] --"+ this.getClass().getName() +" Queue Name " + queue.getName()+ " Producing Message \n " + message);
		this.template.convertAndSend(this.queue.getName(),message);
	}
}
