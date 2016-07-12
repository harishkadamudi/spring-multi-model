package sample.multimodule.local;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile(value = "app1")
public class LocalJMSApp1Config {

	/*
	 * @Value("#{message.out.local.queue}") private String localMessageOutQueue;
	 */

	@Value("${message.out.local.queue}")
	private String localMessageOutQueue;

	@Value("${message.out.local.queue.ack}")
	private String localMessageOutQueueAck;

	@Bean(name = "localQueue")
	public Queue queue() {
		return new Queue(getLocalMessageOutQueue());
	}

	@Bean(name = "localQueueAck")
	public Queue queueAck() {
		return new Queue(getLocalMessageOutQueueAck());
	}

	public String getLocalMessageOutQueue() {
		return localMessageOutQueue;
	}

	public void setLocalMessageOutQueue(String localMessageOutQueue) {
		this.localMessageOutQueue = localMessageOutQueue;
	}

	public String getLocalMessageOutQueueAck() {
		return localMessageOutQueueAck;
	}

	public void setLocalMessageOutQueueAck(String localMessageOutQueueAck) {
		this.localMessageOutQueueAck = localMessageOutQueueAck;
	}

}
