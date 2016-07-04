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

import sample.multimodule.domain.entity.UserDetails;
import sample.multimodule.domain.xml.UserDetailsXML;

@Configuration
public class XMLConversion {

	private static final Log LOG = LogFactory.getLog(XMLConversion.class);

	@Autowired
	Jaxb2Marshaller marshaller;

	@Autowired
	private ModelMapper modelMapper;

	public Object getXml(UserDetails fromUser, UserDetailsXML toUser) {
		
		return  this.conversion(fromUser, toUser);
	}

	private Object conversion(Object fromUser, Object toUser) {
		LOG.debug("Object to convert from : " + fromUser.toString());
		 Object xml = modelMapper.map(fromUser, toUser.getClass());
		LOG.debug("Converted XML Oject " + xml.toString());
		return xml;
	}
	
	public Object getEntity(UserDetailsXML xml, UserDetails entity) {
		return conversion(xml, entity);
	}

	public String modelToxml(UserDetailsXML xml) {
		final StringWriter out = new StringWriter();
		marshaller.marshal(xml, new StreamResult(out));
		return out.toString();
	}

	public Object xmlToModel(String xml) throws Exception {
		try {
			return marshaller.unmarshal(new StreamSource(new StringReader(xml)));
		} catch (Exception e) {
			throw e;
		}
	}

}