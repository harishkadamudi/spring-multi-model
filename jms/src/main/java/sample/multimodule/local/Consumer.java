package sample.multimodule.local;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import sample.multimodue.remote.sender.jms.RemoteJMSSender;

@Component
public class Consumer {

	private static final Log LOG = LogFactory.getLog(Consumer.class);
	
	@Autowired
	RemoteJMSSender remoteJmsSender;
	
	private @Value("${application.logger.name}") String appName;
	
	private String message ;
	
//	@RabbitListener(queues = "MessageOutLocalQueue")
	@RabbitListener(queues = "#{localQueue.name}")
	@SendTo(value = "#{localQueueAck.name}")
	public Boolean receiveLocalQueue(String message) {
		LOG.debug(" ------[x -- "+ appName + "---x] --" + this.getClass().getName() + " consuming Message \n " + message);
		if (message.isEmpty() || message.trim().length() == 0)
			return Boolean.FALSE;
		else
			this.setMessage(message);
			return Boolean.TRUE;
	}

	@RabbitListener(queues = "#{localQueueAck.name}")
	public void ackLocalQueue(Boolean isMsgReceived) {
		if(isMsgReceived){
			LOG.debug(" ------[x -- "+ appName + "---x] -- Acknowledgement Received Message from Local Queue [x---Message ---x ] - \n "+getMessage());
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
