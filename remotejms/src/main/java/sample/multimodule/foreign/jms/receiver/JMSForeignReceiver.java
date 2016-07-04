package sample.multimodule.foreign.jms.receiver;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import sample.multimodule.domain.entity.UserDetails;
import sample.multimodule.repository.UserRepository;
import sample.multimodule.utill.XMLConversion;

@Configuration
public class JMSForeignReceiver {

	private static final Log LOG = LogFactory.getLog(JMSForeignReceiver.class);

	@Autowired
	private XMLConversion xmlconversion;
	@Autowired
	UserRepository userRepository;

	@RabbitListener(queues = "T1Q1")
	// @RabbitListener(queues = "#{autoDeleteQueue1.name}")
	public void receive1(String in) throws InterruptedException {
		// receive(in, 1);
		sample.multimodule.domain.xml.UserDetailsXML userDetails = null;
		UserDetails userEntity = null;
		try {
			userDetails = xmlconversion.xmlToModel(in);
			userEntity = xmlconversion.getUserEntity(userDetails, new UserDetails());
			userEntity = userRepository.save(userEntity);
			System.out.println(userEntity);
		} catch (Exception e) {
			LOG.error(" Exception In Receiving converting to Object" + e.getMessage());
		}
		System.out.println("receive1 " + userEntity);

	}

	@RabbitListener(queues = "T1Q2")
	// @RabbitListener(queues = "#{autoDeleteQueue2.name}")
	public void receive2(String in) throws InterruptedException {
		// receive(in, 2);
		System.out.println("receive2" + in);

	}
}
