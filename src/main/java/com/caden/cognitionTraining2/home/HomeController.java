package com.caden.cognitionTraining2.home;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm;ss.SSS").format(new Date()) + "  DEBUG " + "/home");
		return "home";
	}
}
