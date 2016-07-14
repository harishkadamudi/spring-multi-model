package sample.multimodue.remote.consumer.jms;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import sample.multimodule.domain.entity.Message;
import sample.multimodule.foreign.jms.sender.JMSForeignSender;

@Component
public class RemoteJMSConsumer {

	private @Value("${application.logger.name}") String appName;

	private static final Log LOG = LogFactory.getLog(RemoteJMSConsumer.class);

	@Autowired
	JMSForeignSender jmsForeignSender;

	public void receiveMessage(Message message) {
		LOG.debug(" ------[x -- " + appName + "---x] -- " + this.getClass().getName()
				+ " in method receiveMessage [with message] \n" + message);
		// System.out.println("Received <" + message + ">");
		jmsForeignSender.send(message);
	}

}
