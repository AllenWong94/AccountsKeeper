package com.FlappyTicket.Controller;

import java.io.UnsupportedEncodingException;
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
@RequestMapping(value = "Search")

public class SearchController {
	
	@Autowired
	private SearchService SearchService;
	
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public Object findByName(@RequestParam("text") String text) throws UnsupportedEncodingException {
		
		String name = new String(text.getBytes("iso8859-1"),"utf-8");
		return SearchService.findByName(name);
		
	}

	
}


