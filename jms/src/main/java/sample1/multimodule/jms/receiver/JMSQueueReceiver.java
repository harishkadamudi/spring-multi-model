package sample1.multimodule.jms.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

//@RabbitListener(queues = "jmsmessagequeue")
@Component
public class JMSQueueReceiver {

	@Value("${message.out.local.queue}")
	private String localMessageOutQueue;

	// @RabbitHandler
	@RabbitListener(queues = "MessageOutLocalQueue")
	@SendTo(value = "MessageOutLocalQueueAck")
	public String receive(String in) {
		System.out.println(" [x] Received '" + in + "'");
		return "success";
	}

	public String getLocalMessageOutQueue() {
		return localMessageOutQueue;
	}

	public void setLocalMessageOutQueue(String localMessageOutQueue) {
		this.localMessageOutQueue = localMessageOutQueue;
	}

	// @RabbitHandler
	@RabbitListener(queues = "MessageOutLocalQueueAck")
	public void messageOutLocalQueueAck(String message) {
		System.out.println(" [x] Acknowledge '" + message + "'");
	}

}
