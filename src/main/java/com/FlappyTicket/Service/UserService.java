package com.FlappyTicket.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.FlappyTicket.Model.*;
import com.FlappyTicket.Dao.*;

@Service
public class UserService {
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private SessionService sessionservice;
	
	public Object login(String name, String password) {
		List<User> list = userDAO.login(name, password);
		Map<String, String> map = new HashMap<String, String>();
		if (list.size() == 0) {
			map.put("Status", "Fail");
		} else {
			map.put("Status", "Success");
		}
		return map;
	}
	
	public Object register(String name, String password, String tel) {

		List<User> userlist = userDAO.findAll();
		Map<String, String> map = new HashMap<String, String>();
		boolean flag = true;
		for (int i = 0; i < userlist.size(); i++) {
			if (userlist.get(i).getuName().equals(name)) {
				flag = false;
				break;
			}
		}
		if (flag == true) {
			userDAO.insert(new User(name, password, tel, 0));
			map.put("Status", "Success");
		} else {
			map.put("Status", "Fail");
		}
		return map;
	}
	
	public Object modifypass(String name, String pass, String newpass) {
		Map<String, String> status = (Map<String, String>) this.login(name, pass);		
		if (status.get("Status").equals("Success")) {
			User tmp = userDAO.findByUserName(name);
			tmp.setuPassword(newpass);
			userDAO.update(tmp);
		}
		
		return status;
	}
	
	public Object modifytel(String name, String pass, String newtel) {
		Map<String, String> status = (Map<String, String>) this.login(name, pass);		
		if (status.get("Status").equals("Success")) {
			User tmp = userDAO.findByUserName(name);
			tmp.setuTel(newtel);;
			userDAO.update(tmp);
		}
		return status;
	}
	
	
	
		
}
