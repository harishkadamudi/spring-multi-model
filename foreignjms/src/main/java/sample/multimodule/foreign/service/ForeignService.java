package sample.multimodule.foreign.service;

import sample.multimodule.domain.entity.Message;

public interface ForeignService {

	public Message publishTopic(Message message);

	public Message publishToAnotherDomain(Message message);
}
