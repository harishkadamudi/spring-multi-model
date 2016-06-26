package sample.multimodule.xml.api;

import sample.multimodule.domain.entity.Account;
import sample.multimodule.domain.entity.UserDetails;
import sample.multimodule.domain.xml.AccountXML;
import sample.multimodule.domain.xml.UserDetailsXML;

public interface XmlService {

	public AccountXML getXML(Account fromAccount, AccountXML toXml);
	public UserDetailsXML getXML(UserDetails fromUser, UserDetailsXML toXml);
}
