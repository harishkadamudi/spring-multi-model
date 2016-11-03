package sample.multimodule.foreign.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sample.multimodule.foreign.service.ForeignService;;

@RestController
@RequestMapping(value = "/publish")
public class Topic1Controller {

	private static final Log LOG = LogFactory.getLog(Topic1Controller.class);


	@Autowired
	ForeignService foreignService;

	@RequestMapping(method = RequestMethod.POST)
	public sample.multimodule.domain.entity.Message getGreetings(
			@RequestBody sample.multimodule.domain.entity.Message incomingMessage) {
		LOG.debug("----[Sending]-- Message ---- " + incomingMessage + "---- Message --[Sending]");
		foreignService.publishTopic(incomingMessage);
		return incomingMessage;
	}

}
