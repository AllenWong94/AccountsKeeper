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
@RequestMapping(value = "Seat")

public class SeatController {

	
	@Autowired
	private SeatService seatService;
	
	@RequestMapping(value = "getstate", method = RequestMethod.GET)
	@ResponseBody
	public Object getSeatByID(@RequestParam("seid") int seid) {
		return seatService.getSeatState(seid);
		
	}
	
	@RequestMapping(value = "getrow", method = RequestMethod.GET)
	@ResponseBody
	public Object getRowByID(@RequestParam("seid") int seid) {
		return seatService.getrow(seid);
		
	}
	
	@RequestMapping(value = "getcol", method = RequestMethod.GET)
	@ResponseBody
	public Object getColByID(@RequestParam("seid") int seid) {
		return seatService.getcol(seid);
		
	}
	
	@RequestMapping(value = "setstate", method = RequestMethod.GET)
	@ResponseBody
	public Object setSeatByID(@RequestParam("seid") int seid, @RequestParam("bookseat") int bookseat) {
		return seatService.setSeatState(seid, bookseat);
		
	}
	
	
	
	
	
}


