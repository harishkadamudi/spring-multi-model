package sample.multimodue.remote.sender.jms;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

import sample.multimodule.foreign.jms.sender.JMSForeignSender;
import sample1.multimodule.jms.sender.JMSRemoteSender;

@Configuration
public class RemoteJMSSender {

	private static final Log LOG = LogFactory.getLog(JMSRemoteSender.class);

	@Autowired
	private RabbitTemplate template;

	@Autowired
	@Qualifier(value = "remoteQueue")
	private Queue queue;

	@Autowired
	JMSForeignSender jmsForeignSender;
	
	public void send(String message) {

		LOG.debug(" ------[x] --"+ this.getClass().getName() +" Remote queue Name " + queue.getName()+ " & Message \n " + message);
//		String message = "Hello World!";
		this.template.convertAndSend(queue.getName(), message);
//		System.out.println(" [x] Sent to Remote Queue " + queue.getName() + " + message + ");
		jmsForeignSender.send(message);
	}

}
