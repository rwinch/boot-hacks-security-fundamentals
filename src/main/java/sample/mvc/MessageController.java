package sample.mvc;

import java.security.Principal;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MessageController {

	@RequestMapping("/message")
	public String message(Principal principal, Map<String,Object> model) {
		model.put("message", "Hello " + principal.getName() + "!");
		return "message";
	}
}
