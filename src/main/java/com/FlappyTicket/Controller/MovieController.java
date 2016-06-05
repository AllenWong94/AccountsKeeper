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

public class MovieController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private MovieService movieService;
	
	@RequestMapping(value = "AllMovieList", method = RequestMethod.GET)
	@ResponseBody
	public Object getCurrentMovieList() {
		return movieService.findAll();
		
	}
	
	@RequestMapping(value = "FindByCID", method = RequestMethod.GET)
	@ResponseBody
	public Object getMovieByCID(@RequestParam("CID") int id) {
		
		logger.info("Welcome home! The client locale is {}.");
		
		return movieService.findByCID(id);
		
	}
	
	
	
}


