package sample.multimodue.remote.sender.jms;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import sample.multimodule.foreign.jms.sender.JMSForeignSender;

@Configuration
public class RemoteJMSSender {

	private static final Log LOG = LogFactory.getLog(RemoteJMSSender.class);
	private @Value("${application.logger.name}") String appName;
	
	@Autowired
	private RabbitTemplate template;

	@Autowired
	@Qualifier(value = "remoteQueue")
	private Queue queue;

	@Autowired
	JMSForeignSender jmsForeignSender;
	
	public void send(String message) {

		LOG.debug(" ------[x -- "+ appName + "---x] --"+ this.getClass().getName() +" Remote queue Name " + queue.getName()+ ", Sending Message \n " + message);
//		String message = "Hello World!";
		this.template.convertAndSend(queue.getName(), message);
//		System.out.println(" [x] Sent to Remote Queue " + queue.getName() + " + message + ");
		jmsForeignSender.send(message);
	}

}
