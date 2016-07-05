package sample.multimodule.xml.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.multimodule.domain.entity.UserDetails;
import sample.multimodule.domain.xml.UserDetailsXML;
import sample.multimodule.utill.XMLConversion;
import sample.multimodule.xml.api.XmlService;

@Service
public class XmlServiceImpl implements XmlService {

	private static final Log LOG = LogFactory.getLog(XmlServiceImpl.class);

	@Autowired
	XMLConversion conversion;
	
	@Override
	public UserDetailsXML getXML(UserDetails fromUser, UserDetailsXML toXml) {
		LOG.debug("<--------------[x] ---------- Into --" + getClass().getName() +"---into getXML Method --------");
		UserDetailsXML userXml = (UserDetailsXML) conversion.getXml(fromUser, toXml);
		return userXml;
	}

}
