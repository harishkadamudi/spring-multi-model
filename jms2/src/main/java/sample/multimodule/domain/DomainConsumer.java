
package sample.multimodule.domain;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sample.multimodule.local.Producer;

@Component
public class DomainConsumer {

	private static final Log LOG = LogFactory.getLog(DomainConsumer.class);

	private String message;
	
	@Autowired
	private Producer producer;
	@RabbitListener(queues = "queue.messageinlocal")
	public void receiveLocalQueue(String message) {
		System.out.println(" ------[x] Domain Queue ------[x] --" + this.getClass().getName() + " consuming Message \n "
				+ message);
		LOG.debug(" ------[x] Domain Queue ------[x] --" + this.getClass().getName() + " consuming Message \n "
				+ message);
		LOG.debug("[--Sending--] -- Message to Domain Message Out Local Queue ---[--Sending -- ]");
		this.setMessage(message);
		producer.send(getMessage());
	}

	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
