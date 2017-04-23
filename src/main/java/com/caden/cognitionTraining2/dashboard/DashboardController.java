package com.caden.cognitionTraining2.dashboard;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DashboardController {
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dashboard(Authentication authentication, Model model) {
		
		System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm;ss.SSS").format(new Date()) + "  DEBUG " + "/dashboard:GET");
		model.addAttribute("username", authentication.getName());
		return "dashboard";
	}
	
	@RequestMapping(value = "/charts", method = RequestMethod.GET)
	public String charts() {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm;ss.SSS").format(new Date()) + "  DEBUG " + "/charts");
		return "charts";
	}
	
	@RequestMapping(value = "/tables", method = RequestMethod.GET)
	public String tables() {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm;ss.SSS").format(new Date()) + "  DEBUG " + "/tables");
		return "tables";
	}
	
	@RequestMapping(value = "/forms", method = RequestMethod.GET)
	public String forms() {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm;ss.SSS").format(new Date()) + "  DEBUG " + "/forms");
		return "forms";
	}
	
	@RequestMapping(value = "/bootstrap-elements", method = RequestMethod.GET)
	public String bootstrapElements() {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm;ss.SSS").format(new Date()) + "  DEBUG " + "/bootstrap-elements");
		return "bootstrap-elements";
	}
	
	@RequestMapping(value = "/bootstrap-grid", method = RequestMethod.GET)
	public String bootstrapGrid() {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm;ss.SSS").format(new Date()) + "  DEBUG " + "/bootstrap-grid");
		return "bootstrap-grid";
	}
	
	@RequestMapping(value = "/blank-page", method = RequestMethod.GET)
	public String blankPage() {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm;ss.SSS").format(new Date()) + "  DEBUG " + "/blank-page");
		return "blank-page";
	}
}
