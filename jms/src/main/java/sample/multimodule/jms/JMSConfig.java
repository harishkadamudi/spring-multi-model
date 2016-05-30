package sample.multimodule.jms;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JMSConfig {

	@Bean
	public Queue queue() {
		return new Queue("jmsmessagequeue");
	}

}
