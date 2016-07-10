package sample.multimodue.remote.consumer.jms;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class RemoteJMSConsumer {

	private static final Log LOG = LogFactory.getLog(RemoteJMSConsumer.class);
	
	public void receiveMessage(String message) {
		LOG.debug("---- [ Inside ] ----" + this.getClass().getName() +" in method receiveMessage [with message] \n" + message);
		
	}

}
