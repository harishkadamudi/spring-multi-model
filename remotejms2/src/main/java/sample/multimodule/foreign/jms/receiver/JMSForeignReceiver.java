package sample.multimodule.foreign.jms.receiver;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import sample.multimodule.domain.entity.UserDetails;
import sample.multimodule.domain.xml.UserDetailsXML;
import sample.multimodule.utill.XMLConversion;

@Configuration
public class JMSForeignReceiver {

	private static final Log LOG = LogFactory.getLog(JMSForeignReceiver.class);

	@Autowired
	private XMLConversion xmlconversion;

//	@RabbitListener(queues = "T2Q1")
	 @RabbitListener(queues = "#{autoDeleteQueue1.name}")
	public void receive1(String in) throws InterruptedException {
		// receive(in, 1);
		 LOG.debug("[x] -----------Received Message ---- with Queue T2Q1 " );
		sample.multimodule.domain.xml.UserDetailsXML userDetails = null;
		UserDetails userEntity = null;
		try {
			userDetails = (UserDetailsXML) xmlconversion.xmlToModel(in);
			userEntity = (UserDetails) xmlconversion.getEntity(userDetails, new UserDetails());
			System.out.println(userEntity);
		} catch (Exception e) {
			LOG.error(" Exception In Receiving converting to Object" + e.getMessage());
		}
		System.out.println("receive1 \n in other Domain" + userEntity);

	}

//	@RabbitListener(queues = "T2Q2")
	 @RabbitListener(queues = "#{autoDeleteQueue2.name}")
	public void receive2(String in) throws InterruptedException {
		System.out.println("receive2 in other Domain \n" + in);
		LOG.debug("[x] -----------Received Message ---- with Queue T2Q2 " );
//		jMSForeignSender.sendOtherDomain(in);
		LOG.debug("[x] -----------Sent Message to another Domain ----------[x]" );
	}
}
