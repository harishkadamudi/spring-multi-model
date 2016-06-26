package sample.multimodule.local.sender.jms;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class LocalJMSSender {

	private static final Log LOG = LogFactory.getLog(LocalJMSSender.class);
	
	@Autowired
	private RabbitTemplate template;

	@Autowired
	private Queue queue;

	// @Scheduled(fixedDelay = 1000, initialDelay = 500)
	public void send(String xmlMessage) {
		// String message = "Hello World!";
		LOG.debug(" ------[x] --"+ this.getClass().getCanonicalName() +" Queue Name " + queue.getName()+ " & Message \n " + xmlMessage);
		 this.template.convertAndSend(queue.getName(), xmlMessage);
		//System.out.println(" [x] Sent '" + xmlMessage + "'");
	}

}
