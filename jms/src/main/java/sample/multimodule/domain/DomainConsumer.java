package sample.multimodule.domain;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DomainConsumer {

	private static final Log LOG = LogFactory.getLog(DomainConsumer.class);

	private String message;
	
	@RabbitListener(queues = "queue.messageFromlocal")
	public void receiveLocalQueue(String message) {
		System.out.println(" ------[x] Home Domain Queue ------[x] --" + this.getClass().getName() + " consuming Message \n "
				+ message);
		LOG.debug(" ------[x] Home Domain Queue ------[x] --" + this.getClass().getName() + " consuming Message \n "
				+ message);
		LOG.debug("[--Sending--] -- Message to Domain Message Out Local Queue ---[--Sending -- ]");
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
