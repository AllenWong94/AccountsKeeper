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

import com.FlappyTicket.Dao.SeatDAO;
import com.FlappyTicket.Dao.SessionDAO;
import com.FlappyTicket.Service.*;

@Controller
@RequestMapping(value = "Order")

public class OrderController {
	
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private SeatService seatService;
	
	@Autowired
	private SessionDAO sessionDAO;
	
	@RequestMapping(value = "FindByUname", method = RequestMethod.GET) 
	@ResponseBody
	public Object findByUname(@RequestParam("user") String name) throws UnsupportedEncodingException {
		
		String tmp = new String(name.getBytes("iso8859-1"),"utf-8");
		return orderService.findByUname(tmp); //返回某电影院某电影所有场次
		
	}
	
	
	@RequestMapping(value = "NewOrder", method = RequestMethod.GET)
	@ResponseBody
	public Object newOrder(@RequestParam("user") String name, @RequestParam("sid") int sid, @RequestParam("seatnum") int seatnum) {
		
		seatService.setSeatState(sessionDAO.findById(sid).getSeid(), seatnum);
		return orderService.newOrder(name, sid, seatnum); //返回某电影院某电影所有场次
		
	}
	
	@RequestMapping(value = "PayOrder", method = RequestMethod.GET)
	@ResponseBody
	public Object payOrder(@RequestParam("oid") int oid) {
		return orderService.payOrder(oid); //返回某电影院某电影所有场次
		
	}
	
}


