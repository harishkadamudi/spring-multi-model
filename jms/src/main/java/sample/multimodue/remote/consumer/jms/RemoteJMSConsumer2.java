package sample.multimodue.remote.consumer.jms;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import sample.multimodule.domain.entity.Message;
//import sample.multimodule.foreign.jms.sender.JMSForeignSender;

@Component
@Profile(value = "app2")
public class RemoteJMSConsumer2 {

	private @Value("${application.logger.name}") String appName;

	private static final Log LOG = LogFactory.getLog(RemoteJMSConsumer2.class);

	// @Autowired
	// JMSForeignSender jmsForeignSender;

	public void receiveMessage(Message message) {
		try {
			LOG.debug(" ------[x -- " + appName + "---x] -- " + this.getClass().getName()
					+ " in method receiveMessage [with message] \n" + message);
			String url = "http://localhost:8080/foreignjms2/publish";
			RestTemplate template = new RestTemplate();
			Message object = template.postForObject(url, message, Message.class);
			System.out.println(object.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
