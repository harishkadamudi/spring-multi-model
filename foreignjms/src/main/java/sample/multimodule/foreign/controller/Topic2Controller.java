package sample.multimodule.foreign.controller;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import sample.multimodule.foreign.config.TopicConfig;

@RestController
@RequestMapping(value = "/publish111")
public class Topic2Controller {

	private static final Log LOG = LogFactory.getLog(Topic2Controller.class);

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	TopicConfig topicConfig;

	/*
	 * private @Value("${message.in.remote.topic}") String topicName;
	 * private @Value("${message.in.remote.topic.queue1}") String queueName1;
	 * private @Value("${message.in.remote.topic.queue2}") String queueName2;
	 */
	@RequestMapping(method = RequestMethod.POST)
	public sample.multimodule.domain.entity.Message getGreetings(
			@RequestBody sample.multimodule.domain.entity.Message incomingMessage) {
		/*
		 * public String getGreetings(
		 * 
		 * @PathVariable("message") String incomingMessage) {
		 */
		Session session = null;
		Connection createConnection = null;
		try {
			LOG.debug("----[Sending]-- Message ---- " + incomingMessage + "---- Message --[Sending]");
			LOG.debug("----[-configuration-]Topic Configuration  ---" + topicConfig.getTopicName() + "\t"
					+ topicConfig.getQueueName1() + "\t" + topicConfig.getQueueName2() + "[-configuration-]");
			ConnectionFactory connectionFactory = jmsTemplate.getConnectionFactory();
			createConnection = connectionFactory.createConnection();
			createConnection.setClientID("TopicQ1");
			session = createConnection.createSession();
			Topic topic = session.createTopic(topicConfig.getTopicName());
			TopicSubscriber sub1 = session.createDurableSubscriber(topic, topicConfig.getQueueName1());
			TopicSubscriber sub2 = session.createDurableSubscriber(topic, topicConfig.getQueueName2());
			createConnection.start();

			Message message = session.createObjectMessage(incomingMessage);
			MessageProducer producer = session.createProducer(topic);
			producer.send(message);
			Message receive1 = sub1.receive();
			if (receive1 instanceof ObjectMessage) {
				Object object = ((ObjectMessage) receive1).getObject();
				sample.multimodule.domain.entity.Message msg = (sample.multimodule.domain.entity.Message) object;
				LOG.debug("----[Receiving ---1]----xxxxxxxx--[Message]----" + msg.getId());
			}
			Message receive2 = sub2.receive();
			if (receive2 instanceof ObjectMessage) {
				Object object2 = ((ObjectMessage) receive2).getObject();
				sample.multimodule.domain.entity.Message msg = (sample.multimodule.domain.entity.Message) object2;
				LOG.debug("----[Receiving ---2]----xxxxxxxx--[Message]----" + msg.getId());
				String url = "http://localhost:8080/"+topicConfig.getContextPath()+"/publish";
				RestTemplate template = new RestTemplate();
				Message object = template.postForObject(url, msg, Message.class);

			}
		} catch (JMSException e) {
			e.printStackTrace();
		} finally {
			try {
				session.close();
				createConnection.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
		return incomingMessage;
	}

}
