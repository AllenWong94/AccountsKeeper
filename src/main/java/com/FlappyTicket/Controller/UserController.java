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
@RequestMapping(value = "User")

public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	@ResponseBody
	public Object login(@RequestParam("name") String name, @RequestParam("pass") String password) {
		
		return userService.login(name, password); //返回某电影院某电影所有场次
		
	}
	
	@RequestMapping(value = "register", method = RequestMethod.GET)
	@ResponseBody
	public Object register(@RequestParam("name") String name, @RequestParam("pass") String password, @RequestParam("tel") String tel) {
		
		return userService.register(name, password, tel); //返回某电影院某电影所有场次
		
	}
	
	@RequestMapping(value = "modifypass", method = RequestMethod.GET)
	@ResponseBody
	public Object modifypass(@RequestParam("name") String name, @RequestParam("pass") String pass, @RequestParam("newpass") String newpass) {
		
		return userService.modifypass(name, pass, newpass); //返回某电影院某电影所有场次
		
	}
	
	@RequestMapping(value = "modifytel", method = RequestMethod.GET)
	@ResponseBody
	public Object modifytel(@RequestParam("name") String name, @RequestParam("pass") String pass, @RequestParam("newtel") String newtel) {
		
		return userService.modifytel(name, pass, newtel); //返回某电影院某电影所有场次
		
	}
	
	
	
	
}


