package sample.multimodule.application2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/app2/")
    public String index() {
        return "Greetings from App2!";
    }

}
