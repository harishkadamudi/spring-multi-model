package sample.multimodule.foreign.jms.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JMSForeignReceiver {

	@RabbitListener(queues = "#{autoDeleteQueue1.name}")
	public void receive1(String in) throws InterruptedException {
		//receive(in, 1);
		System.out.println( "receive1 "  + in );
	}

	@RabbitListener(queues = "#{autoDeleteQueue2.name}")
	public void receive2(String in) throws InterruptedException {
		//receive(in, 2);
		System.out.println( "receive2" +in );
	}
}
