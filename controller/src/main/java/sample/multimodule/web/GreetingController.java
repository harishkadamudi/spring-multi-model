package sample.multimodule.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sample.multimodule.domain.entity.Account;
import sample.multimodule.service.api.AccountService;
import user.Greeting;

@Controller
public class GreetingController {

	private static final Log LOG = LogFactory.getLog(GreetingController.class);

	
	  @Autowired
	  protected AccountService accountService;
	  
	@RequestMapping(value = "/greeting", method = RequestMethod.GET)
	public String greetingForm(Model model) {

		LOG.debug("Into Get Greeting ");
		model.addAttribute("greeting", new Greeting());
		return "welcome/greeting";
	}

	@RequestMapping(value = "/greeting", method = RequestMethod.POST)
	public String greetingSubmit(@ModelAttribute Greeting greeting, Model model) {
		LOG.debug("Into Post Greeting " + greeting.toString());
		model.addAttribute("greeting", greeting);
		
		Account account = new Account(new Long(1), "accountnumber");
		accountService.save(account);
		return "welcome/result";
	}

}
