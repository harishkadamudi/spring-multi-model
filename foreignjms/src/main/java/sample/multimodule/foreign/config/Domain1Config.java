package sample.multimodule.foreign.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration("DomainConfig")
@Profile("app1")
public class Domain1Config {

	private @Value("${message.in.remote.topic}") String topicName;
	private @Value("${message.in.remote.topic.queue1}") String queueName1;
	private @Value("${message.in.remote.topic.queue2}") String queueName2;
	private @Value("${send.message.to.domain.context}") String contextPath;

	@Bean(name="topicConfig")
	public TopicConfig topicConfig() {
		return new TopicConfig(topicName, queueName1, queueName2,contextPath);
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getQueueName1() {
		return queueName1;
	}

	public void setQueueName1(String queueName1) {
		this.queueName1 = queueName1;
	}

	public String getQueueName2() {
		return queueName2;
	}

	public void setQueueName2(String queueName2) {
		this.queueName2 = queueName2;
	}

}
