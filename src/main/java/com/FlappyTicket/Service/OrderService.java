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
public class OrderService {
	@Autowired
	private OrderDAO orderDAO;
	
	
	public List<Order> findByUID(int UID) {
		List<Order> Olist = orderDAO.findByCID(UID);
		
		for (int i = 0; i < Slist.size(); i++) {
			set.add(new Integer(Slist.get(i).getMid()));
		}
		
		for (Integer value: set) {
			res.add(movieDAO.findById(value));
		}
		return res;
		
	}
	
}
