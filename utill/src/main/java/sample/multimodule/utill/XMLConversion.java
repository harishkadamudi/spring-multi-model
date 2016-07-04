package sample.multimodule.utill;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import sample.multimodule.domain.entity.Account;
import sample.multimodule.domain.entity.UserDetails;
import sample.multimodule.domain.xml.AccountXML;
import sample.multimodule.domain.xml.UserDetailsXML;

@Configuration
public class XMLConversion {

	private static final Log LOG = LogFactory.getLog(XMLConversion.class);
	@Autowired
	Jaxb2Marshaller marshaller;

	@Autowired
	private ModelMapper modelMapper;

	public AccountXML getAccountXML(Account fromAccount, AccountXML toXml) {

		LOG.debug("Object to Convert   " + fromAccount.toString());

		AccountXML acountXMl = modelMapper.map(fromAccount, toXml.getClass());
		LOG.debug("Converted Object  " + acountXMl.toString());
		return acountXMl;
	}

	public String convertToXML(AccountXML xml) {
		final StringWriter out = new StringWriter();
		marshaller.marshal(xml, new StreamResult(out));
		return out.toString();
	}

	public UserDetailsXML getUserXml(UserDetails fromUser, UserDetailsXML toUser) {
		LOG.debug("Object to convert from : " + fromUser.toString());
		UserDetailsXML xml = modelMapper.map(fromUser, toUser.getClass());
		LOG.debug("Converted XML Oject " + xml.toString());
		return xml;
	}

	public UserDetails getUserEntity(UserDetailsXML xml, UserDetails entity) {
		LOG.debug(" XML converstion from : ");
		entity = modelMapper.map(xml, entity.getClass());
		LOG.debug("Converted Entity Oject " + xml.toString());
		return entity;
	}

	public String modelToxml(UserDetailsXML xml) {
		final StringWriter out = new StringWriter();
		marshaller.marshal(xml, new StreamResult(out));
		return out.toString();
	}

	public UserDetailsXML xmlToModel(String xml) {
		UserDetailsXML userDetails = null;
		try {
			userDetails = (UserDetailsXML) marshaller.unmarshal(new StreamSource(new StringReader(xml)));
		} catch (Exception e) {
			throw e;
		}
		return userDetails;
	}
}
