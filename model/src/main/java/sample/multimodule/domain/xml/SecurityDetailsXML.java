package sample.multimodule.domain.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Security_Details")
@XmlAccessorType(XmlAccessType.FIELD)
public class SecurityDetailsXML {

	@XmlElement(name = "Social-Security-Number")
	private String socialSecurityNumber;
	@XmlElement(required= false)
	private long securityId;

	public long getSecurityId() {
		return securityId;
	}

	public void setSecurityId(long securityId) {
		this.securityId = securityId;
	}

	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}

}
