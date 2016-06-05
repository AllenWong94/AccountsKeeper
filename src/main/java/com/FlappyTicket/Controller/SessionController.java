package com.FlappyTicket.Controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.FlappyTicket.Service.*;

@Controller
@RequestMapping(value = "Movie")

public class SessionController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private SessionService sessionService;
	
	@RequestMapping(value = "Session", method = RequestMethod.GET)
	@ResponseBody
	public Object getSessionByID(@RequestParam("CID") int CID, @RequestParam("MID") int MID) {
		return sessionService.findByCMID(CID, MID); //返回某电影院某电影所有场次
		
	}
	
/*	@RequestMapping(value = "FindByCID", method = RequestMethod.GET)
	@ResponseBody
	public Object getMovieByCID(@RequestParam("CID") int id) {
		
		logger.info("Welcome home! The client locale is {}.");
		
		return movieService.findByCID(id);
		
	}
	*/
	
	
	
	
}


