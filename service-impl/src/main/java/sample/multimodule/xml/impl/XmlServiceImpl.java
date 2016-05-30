package sample.multimodule.xml.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.multimodule.domain.entity.Account;
import sample.multimodule.domain.entity.Message;
import sample.multimodule.domain.xml.AccountXML;
import sample.multimodule.jms.sender.JMSSender;
import sample.multimodule.repository.MessageRepository;
import sample.multimodule.utill.XMLConversion;
import sample.multimodule.xml.api.XmlService;

@Service
public class XmlServiceImpl implements XmlService {

	private static final Log LOG = LogFactory.getLog(XmlServiceImpl.class);

	@Autowired
	XMLConversion conversion;

	@Autowired
	MessageRepository messageRepository;

	@Autowired
	JMSSender jmsSender;

	@Override
	public AccountXML getXML(Account fromAccount, AccountXML toXml) {
		LOG.debug("Into Entity Conversion" + conversion.getAccountXML(fromAccount, toXml));

		toXml = conversion.getAccountXML(fromAccount, toXml);
		String convertedXml = conversion.convertToXML(toXml);

		Message in = new Message();
		in.setData(convertedXml.getBytes());
		in.setId(new Long(1));
		Message save = messageRepository.save(in);
		System.out.println(convertedXml + save);
		LOG.debug(convertedXml);

		jmsSender.send();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return conversion.getAccountXML(fromAccount, toXml);

	}

}
