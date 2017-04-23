package com.caden.cognitionTraining2.game;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GameController {
	@RequestMapping(value = "/puzzle", method = RequestMethod.GET)
	public String puzzle() {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm;ss.SSS").format(new Date()) + "  DEBUG " + "/puzzle");
		return "games/puzzle";
	}
}
