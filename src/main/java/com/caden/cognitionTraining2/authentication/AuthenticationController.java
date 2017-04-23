package com.caden.cognitionTraining2.authentication;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.caden.cognitionTraining2.model.User;

@Controller
public class AuthenticationController {
	@Autowired
	private UserService userService;

	@Autowired
	private UserValidator userValidator;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLogin() {
		System.out.println(
				new SimpleDateFormat("yyyy-MM-dd hh:mm;ss.SSS").format(new Date()) + "  DEBUG " + "/login:GET");
		return "login";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String getRegister(Model model) {
		System.out.println(
				new SimpleDateFormat("yyyy-MM-dd hh:mm;ss.SSS").format(new Date()) + "  DEBUG " + "/registration:GET");
		model.addAttribute("userForm", new User());
		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(@ModelAttribute("userForm") User userForm) {
//		userValidator.validate(userForm);
		System.out.println(
				new SimpleDateFormat("yyyy-MM-dd hh:mm;ss.SSS").format(new Date()) + "  DEBUG " + "/registration:POST");
		userService.save(userForm);
		System.out.println("registration:Success");
		return "login";
	}

//	@RequestMapping(value = "/registration", method = RequestMethod.POST)
//	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
//		userValidator.validate(userForm, bindingResult);
//
//		if (bindingResult.hasErrors()) {
//			return "registration";
//		}
//
//		userService.save(userForm);
//
//		securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());
//
//		return "redirect:/welcome";
//	}
}
