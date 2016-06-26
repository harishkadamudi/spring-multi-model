package sample.multimodule.local;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import sample.multimodue.remote.sender.jms.RemoteJMSSender;
import sample1.multimodule.jms.sender.JMSRemoteSender;
import scala.remote;

@Component
// @RabbitListener(queues="queue.name")
// @EnableRabbit
public class Consumer {

	private static final Log LOG = LogFactory.getLog(Consumer.class);
	
	@Autowired
	RemoteJMSSender remoteJmsSender;
	
	private String message ;
	
	@RabbitListener(queues = "queue.messageoutlocal")
	@SendTo(value = "queue.messageoutlocalAck")
	public Boolean receiveLocalQueue(String message) {
		LOG.debug(" ------[x] --" + this.getClass().getName() + " consuming Message \n " + message);
		if (message.isEmpty() || message.trim().length() == 0)
			return Boolean.FALSE;
		else
			this.setMessage(message);
			return Boolean.TRUE;
	}

	@RabbitListener(queues = "queue.messageoutlocalAck")
	public void ackLocalQueue(Boolean isMsgReceived) {
		if(isMsgReceived){
			LOG.debug(" ----------- Received Message from Local Queue [Message] - \n "+getMessage());
			remoteJmsSender.send(getMessage());
		}
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
