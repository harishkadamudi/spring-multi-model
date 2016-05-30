package sample.multimodule.xml.api;

import sample.multimodule.domain.entity.Account;
import sample.multimodule.domain.xml.AccountXML;

public interface XmlService {

	public AccountXML getXML(Account fromAccount, AccountXML toXml);
}
