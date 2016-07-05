package sample.multimodule.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sample.multimodule.domain.entity.UserDetails;
import sample.multimodule.user.api.UserService;

@Controller
public class GreetingController {

	private static final Log LOG = LogFactory.getLog(GreetingController.class);
	
	@Autowired
	private UserService userService;
	
	  
	@RequestMapping(value = "/greeting", method = RequestMethod.GET)
	public String greetingForm(Model model) {

		LOG.debug("Into Get Greeting ");
//		model.addAttribute("greeting", new Greeting());
		model.addAttribute("user", new UserDetails());
		return "welcome/greeting";
	}

	@RequestMapping(value = "/greeting", method = RequestMethod.POST)
	//public String greetingSubmit(@ModelAttribute Greeting greeting, Model model) {
	public String greetingSubmit(@ModelAttribute UserDetails userDetails, Model model) {
		LOG.debug("Into Post Greeting " + userDetails.toString());
		
		UserDetails saveUser = userService.saveUser(userDetails);
		model.addAttribute("userDetails", saveUser);
		//Account account = new Account(new Long(1), "accountnumber");
		//accountService.save(account);
		return "welcome/result";
	}

}
