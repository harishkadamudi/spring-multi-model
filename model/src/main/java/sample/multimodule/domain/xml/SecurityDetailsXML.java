package sample.multimodule.domain.xml;

import javax.xml.bind.annotation.XmlAttribute;

public class SecurityDetailsXML {

	@XmlAttribute(name = "Social-Security-Number")
	private String socialSecurityNumber;

	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}
	
}
