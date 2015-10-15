package sample.mvc;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import sample.data.User;
import sample.security.CurrentUser;

@Controller
public class MessageController {

	@RequestMapping("/message")
	public String message(@CurrentUser User currentUser, Map<String,Object> model) {
		model.put("message", "Hello " + currentUser.getFirstName() + "!");
		return "message";
	}
}
