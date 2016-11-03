package sample.multimodule.domain;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sample.multimodule.domain.entity.Message;

@RestController
@Profile(value = "app1")
@RequestMapping(value = "/publish")
public class App1DomainController {

	private static final Log LOG = LogFactory.getLog(App1DomainController.class);

	private @Value("${application.logger.name}") String appName;

	@Autowired
	App1DomainSender sender;
	
	@RequestMapping(method = RequestMethod.POST)
	public void getMessageFromDomain(@RequestBody Message msg) {
		LOG.debug("[ Message ------Received -------]" + appName + " ---- Succesfully ---- " + msg);
		sender.publicToLocalQueue(msg);
	}
}
