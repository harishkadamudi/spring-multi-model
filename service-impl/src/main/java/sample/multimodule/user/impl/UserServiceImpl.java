package sample.multimodule.user.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.multimodule.domain.entity.Account;
import sample.multimodule.domain.entity.Message;
import sample.multimodule.domain.entity.Status;
import sample.multimodule.domain.entity.UserDetails;
import sample.multimodule.domain.xml.UserDetailsXML;
import sample.multimodule.local.Producer;
import sample.multimodule.repository.MessageRepository;
import sample.multimodule.repository.StatusRepository;
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
	StatusRepository statusRepository;
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
		UserDetails userDetails = userRepository.save(userInfo);

		// Just registered
		Status status = new Status();
		status.setUserId(userDetails.getUserId());
		status.setStatus(StatusConstants.JUST_REGISTERED.getStatus());
		status = statusRepository.save(status);
		
		UserDetailsXML userDetailsXML = xmlService.getXML(userInfo, new UserDetailsXML());
		userDetails = (UserDetails) conversion.getEntity(userDetailsXML, new UserDetails());
		String modelToxml = conversion.modelToxml(userDetailsXML);
		Message in = new Message();
		in.setData(modelToxml.getBytes());
		in.setId(userDetails.getUserId());
		// in.setId(new Long(1));
		Message save = messageRepository.save(in);
		LOG.debug("------ Message Saved to Message Out Table" + save.getId());
		//status.setStatus(StatusConstants.USER_DETAILS_SENT.getStatus());
		status = statusRepository.findByuserId(userDetails.getUserId());
		//status
		status.setStatus(StatusConstants.USER_DETAILS_SENT.getStatus());
		//statusRepository.setStatusFor(status.getStatus(), userDetails.getUserId()); // update User Registration Status
		statusRepository.save(status);
		// statusRepository.save(status); // update
		producer.send(save);
		return userDetails;
	}

}
