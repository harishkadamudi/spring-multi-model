package sample.multimodule.local;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SampleJMS {
	
	/*@Value("#{message.out.local.queue}")
	private String localMessageOutQueue;*/
	
	@Value("${message.out.local.queue}")
	private String localMessageOutQueue;
	
	@Bean(name="localQueue")
	public Queue queue() {
		return new Queue(getLocalMessageOutQueue());
	}

	@Bean
	public Queue ackQueue(){
		return new Queue("queue.messageoutlocalAck");
	}

	public String getLocalMessageOutQueue() {
		return localMessageOutQueue;
	}

	public void setLocalMessageOutQueue(String localMessageOutQueue) {
		this.localMessageOutQueue = localMessageOutQueue;
	}
	
}
