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
		List<Movie> list = userDAO.login(name, password);
		Map<String, String> map = new HashMap<String, String>();
		if (list.size() == 0) {
			map.put("Status", "Success");
		} else {
			map.put("Status", "Fail");
		}
		return map;
	}
	
	public Object register(String name, String password, String tel) {

		List<User> userlist = userDAO.findAll();
		Map<String, String> map = new HashMap<String, String>();
		boolean flag = true;
		for (int i = 0; i < userlist.size(); i++) {
			if (userlist.get(i).getName().isequal(name)) {
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
		
}
