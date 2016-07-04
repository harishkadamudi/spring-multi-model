package sample.multimodule.local.config.jms;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

//@Configuration
public class LocalJMSConfig {

	private static final Log LOG = LogFactory.getLog(LocalJMSConfig.class);

	@Value("${message.out.local.queue}")
	private String localMessageOutQueue;

	@Value("${message.out.local.queue.ack}")
	private String localMessageOutQueueAck;

	// JNDI
	@Bean
	public Queue queue() {
		return new Queue("MessageOutLocalQueue");
	}

	/*
	 * @Bean public Queue ackqueue() { return new
	 * Queue(getLocalMessageOutQueueAck()); }
	 */

	public String getLocalMessageOutQueue() {
		return localMessageOutQueue;
	}

	public void setLocalMessageOutQueue(String localMessageOutQueue) {
		this.localMessageOutQueue = localMessageOutQueue;
	}

	public String getLocalMessageOutQueueAck() {
		return localMessageOutQueueAck;
	}

	public void setLocalMessageOutQueueAck(String localMessageOutQueueAck) {
		this.localMessageOutQueueAck = localMessageOutQueueAck;
	}

	@Bean(name = "jndiConnectionFactory")
	public ConnectionFactory getFactoryFromJNDI() {
		Context jndiContext = null;
		ConnectionFactory connectionFactory = null;
		try {
			jndiContext = new InitialContext();
			connectionFactory = (ConnectionFactory) jndiContext.lookup("java:/ConnectionFactory");
			if(connectionFactory == null)
				throw new Exception("Couln't Create Connection Factory");

		} catch (NamingException e) {
			LOG.error(" Exception in gettting JMS Lookup" + e.getMessage());
		} catch (Exception e) {
			LOG.error(" Exception in gettting JMS Lookup" + e.getMessage());
//			e.printStackTrace();
		}
		return connectionFactory;
	}

	@Bean(name = "jndiRabbitTemplate")
	public RabbitTemplate rabbitTemplate() {
		return new RabbitTemplate(getFactoryFromJNDI());
	}

}
