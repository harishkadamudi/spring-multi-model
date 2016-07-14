package sample.multimodule.user.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.multimodule.domain.entity.Message;
import sample.multimodule.domain.entity.UserDetails;
import sample.multimodule.domain.xml.UserDetailsXML;
import sample.multimodule.local.Producer;
import sample.multimodule.repository.MessageRepository;
import sample.multimodule.repository.UserRepository;
import sample.multimodule.user.api.UserService;
import sample.multimodule.utill.XMLConversion;
import sample.multimodule.xml.api.XmlService;

@Service
public class UserServiceImpl implements UserService {

	private static final Log LOG = LogFactory.getLog(UserServiceImpl.class);

	@Autowired
	UserRepository userRepository;
	@Autowired
	XMLConversion conversion;
	@Autowired
	MessageRepository messageRepository;
	@Autowired
	private XmlService xmlService;
	@Autowired
	Producer producer;

	@Override
	public UserDetails saveUser(UserDetails userInfo) {
		// UserDetails userDetails = userRepository.save(userInfo);
		UserDetailsXML userDetailsXML = xmlService.getXML(userInfo, new UserDetailsXML());
		UserDetails userDetails = (UserDetails) conversion.getEntity(userDetailsXML, new UserDetails());
		String modelToxml = conversion.modelToxml(userDetailsXML);
		Message in = new Message();
		in.setData(modelToxml.getBytes());
		// in.setId(new Long(1));
		Message save = messageRepository.save(in);
		LOG.debug("------ Message Saved to Message Out Table" + save.getId());
		producer.send(save);
		return userDetails;
	}

}
