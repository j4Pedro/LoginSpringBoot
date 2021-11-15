package user.pedro.demo.web;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import user.pedro.demo.model.User;
import user.pedro.demo.service.UserService;
import user.pedro.demo.service.UserServiceImpl;
import user.pedro.demo.web.dto.UserRegistrationDto;

@Controller
@RequestMapping("/userregister")
public class UserCaptchaController {
	@Autowired
	private UserService userService;

	public UserCaptchaController(UserService userService) {
		super();
		this.userService = userService;
	}

	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}

	@GetMapping
	public String showRegistrationForm() {
		return "userregister";
	}

	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto, Model model)
			throws InterruptedException {

		final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(registrationDto.getEmail());
		if (!matcher.find()) {
			model.addAttribute("message", "uUer failed to register.");
			return "userregister";
		}
		 model.addAttribute("message", "User created.");
		userService.save(registrationDto);
		return "index";
	}

}
