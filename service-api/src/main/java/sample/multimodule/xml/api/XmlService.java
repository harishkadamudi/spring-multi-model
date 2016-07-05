package sample.multimodule.xml.api;

import sample.multimodule.domain.entity.UserDetails;
import sample.multimodule.domain.xml.UserDetailsXML;

public interface XmlService {

	public UserDetailsXML getXML(UserDetails fromUser, UserDetailsXML toXml);
}
