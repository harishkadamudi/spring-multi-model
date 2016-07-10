package sample.multimodule.foreign.jms.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import sample.multimodule.foreign.jms.sender.JMSForeignSender;

@Configuration
public class JMSForeignReceiver {
	
	@Autowired
	private JMSForeignSender jMSForeignSender;
	
	@RabbitListener(queues = "#{autoDeleteQueue1.name}")
	public void receive1(String in) throws InterruptedException {
		// receive(in, 1);
		System.out.println("In Another Domain receive1 \n" + in);
	}

	@RabbitListener(queues = "#{autoDeleteQueue2.name}")
	public void receive2(String in) throws InterruptedException {
		// receive(in, 2);
		System.out.println("In Another Domain receive2 \n" + in);
		System.out.println("Sending Message Back to Domain");
		jMSForeignSender.sendOtherDomain(in);
	}
}
