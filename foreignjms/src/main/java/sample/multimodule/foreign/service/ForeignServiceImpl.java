package sample.multimodule.foreign.service;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import sample.multimodule.domain.entity.Message;
import sample.multimodule.foreign.config.TopicConfig;

@Component
public class ForeignServiceImpl implements ForeignService {

	private static final Log LOG = LogFactory.getLog(ForeignServiceImpl.class);

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	TopicConfig topicConfig;

	@Override
	public Message publishTopic(Message incomingMessage) {
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

			javax.jms.Message message = session.createObjectMessage(incomingMessage);
			MessageProducer producer = session.createProducer(topic);
			producer.send(message);
			/*javax.jms.Message receive1 = sub1.receive();
			if (receive1 instanceof ObjectMessage) {
				Object object = ((ObjectMessage) receive1).getObject();
				Message msg = (Message) object;
				LOG.debug("----[Receiving ---1]----xxxxxxxx--[Message]----" + msg.getId());
			}*/
			javax.jms.Message receive2 = sub2.receive();
			if (receive2 instanceof ObjectMessage) {
				Object object2 = ((ObjectMessage) receive2).getObject();
				Message msg = (Message) object2;
				LOG.debug("----[Receiving ---2]----xxxxxxxx--[Message]----" + msg.getId());
				this.publishToAnotherDomain(msg);
			}

		} catch (JMSException e) {
			LOG.error("--- [Error] --- Error while closing JMS Session --- [Error] --- " + e.getMessage());
		} finally {
			try {
				session.close();
				createConnection.close();
			} catch (JMSException e) {
				LOG.error("--- [Error] --- Error while closing JMS Session --- [Error] --- " + e.getMessage());
			}
		}
		return incomingMessage;
	}

	@Override
	public Message publishToAnotherDomain(Message msg) {
		String url = "http://localhost:8080/" + topicConfig.getContextPath() + "/publish";
		RestTemplate template = new RestTemplate();
		Message object = template.postForObject(url, msg, Message.class);
		return msg;
	}

}
