package sample.multimodule.foreign.jms.receiver;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import sample.multimodule.domain.entity.Message;
import sample.multimodule.foreign.jms.sender.JMSForeignSender;

@Configuration
public class JMSForeignReceiver {

	private static final Log LOG = LogFactory.getLog(JMSForeignReceiver.class);
	@Autowired
	private JMSForeignSender jMSForeignSender;
	
	private @Value("${application.logger.name}") String appName;
	
	@RabbitListener(queues = "#{autoDeleteQueue1.name}")
	public void receive1(Message in) throws InterruptedException {
		//receive(in, 1);
		LOG.debug("[x -- "+appName+" -- x] Received Message From Topic Queue 1" );
	}

	@RabbitListener(queues = "#{autoDeleteQueue2.name}")
	public void receive2(Message in) throws InterruptedException {
		//receive(in, 2);
		LOG.debug("[x -- "+appName+" -- x] Received Message From Topic Queue 2 ----------[x]");
		jMSForeignSender.sendOtherDomain(in);
		LOG.debug("[x -- "+appName+" -- x] Sending Message to Local Queue --------[x]");
	}
}
