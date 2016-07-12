package sample.multimodule.domain;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile(value="app1")
public class App1DomainConsumer {

	private static final Log LOG = LogFactory.getLog(App1DomainConsumer.class);
	
	private @Value("${application.logger.name}") String appName;
	
	private String message;
	
	@RabbitListener(queues = "${message.from.local.queue}")
	public void receiveLocalQueue(String message) {
		System.out.println(" ------[x -- Application1 -- x] Home Domain Queue ------[x -- Application1 -- x] --" + this.getClass().getName() + " consuming Message \n "
				+ message);
		LOG.debug(" ------[x -- "+ appName + "---x] -- " + this.getClass().getName() + " consuming Message \n "
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
