package sample.multimodule.domain;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import sample.multimodule.domain.entity.Message;
import sample.multimodule.domain.entity.MessageIn;
import sample.multimodule.local.Producer;
import sample.multimodule.repository.MessageInRepository;
import sample.multimodule.repository.MessageRepository;

@Component
@Profile(value = "app2")
public class App2DomainConsumer {

	private static final Log LOG = LogFactory.getLog(App2DomainConsumer.class);

	private @Value("${application.logger.name}") String appName;

	private String message;

	@Autowired
	private MessageRepository messageRepository;
	@Autowired
	private MessageInRepository inMessageRepo;
	@Autowired
	private Producer producer;

	@RabbitListener(queues = "${message.from.local.queue}")
	public void receiveLocalQueue(Message message) {

		// Message attachedMessage = messageRepository.findOne(message.getId());

		MessageIn inMessage = new MessageIn();
		// attachedMessage.setData(message.getData());
		inMessage.setId(message.getId());
		inMessage.setData(message.getData());
		inMessageRepo.save(inMessage);
		System.out.println(" ------[x] Home Domain Queue ------[x] --" + this.getClass().getName()
				+ " consuming Message \n " + message + "In Message Created " + inMessage);
		LOG.debug(" ------[x -- " + appName + "---x] -- " + this.getClass().getName() + " consuming Message \n "
				+ message + "In Message Created " + inMessage);
		LOG.debug("[--Sending--] -- Message to Domain Message Out Local Queue ---[--Sending -- ]");
		producer.send(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
