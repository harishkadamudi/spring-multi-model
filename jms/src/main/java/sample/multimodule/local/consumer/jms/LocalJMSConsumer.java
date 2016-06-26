package sample.multimodule.local.consumer.jms;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import sample.multimodue.remote.sender.jms.RemoteJMSSender;

//@Component
public class LocalJMSConsumer {

	private static final Log LOG = LogFactory.getLog(LocalJMSConsumer.class);

	private String message;

/*	@Autowired
	RemoteJMSSender remoteJmsSender;*/

	@RabbitListener(queues = "MessageOutLocalQueue")
//	@SendTo(value = "MessageOutLocalQueueAck")
	public String receiveQueue(String text) {

		LOG.debug(
				"---- [ Inside ] ----" + this.getClass().getName() + " in method receiveQueue [with message] " + text);
		/*if (text.isEmpty() || text == "")
			return "failed";
		else {
			this.setMessage(text);*/
			return "success";
//		}
	}

	/*@RabbitListener(queues = "MessageOutLocalQueueAck")
	public void messageOutLocalQueueAck(String message) {
		LOG.debug(
				"---- [ Inside ] ----" + this.getClass().getName() + " in method messageOutLocalQueueAck [is Message Succesful] " + message);
//		if (message)
			LOG.debug("------ Sending Message -------" + getMessage());
			remoteJmsSender.send(getMessage());
	}*/

/*	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}*/

}
