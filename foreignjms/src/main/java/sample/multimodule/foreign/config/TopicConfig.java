package sample.multimodule.foreign.config;

public class TopicConfig {

	private String topicName;
	private String queueName1;
	private String queueName2;
	private String contextPath;
	
	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
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

	public TopicConfig(String topicName, String queueName1, String queueName2,String contextPath) {
		super();
		this.topicName = topicName;
		this.queueName1 = queueName1;
		this.queueName2 = queueName2;
		this.contextPath = contextPath;
	}

}
