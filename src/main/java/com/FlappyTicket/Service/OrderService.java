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
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private SeatDAO seatDAO;
	
	@Autowired
	private SessionDAO sessionDAO;
	
	
	public Object findByUname(String name) {
		

		
		return orderDAO.findByUID(userDAO.findByUserName(name).getUid());
		
	}
	
	
	public Object newOrder(String Uname, int Sid, int SeatNum) {
		Map<String, Object>map = new HashMap<String, Object>();
		int Uid = userDAO.findByUserName(Uname).getUid();
		String SeatInfo = seatDAO.getSeatInfo(SeatNum, seatDAO.findById(sessionDAO.findById(Sid).getSeid()));
		int Oid = orderDAO.insert(new Order(1, Uid, Sid, SeatNum, SeatInfo, 0));
		map.put("Ordernum", Oid);
		return map;
	}
	
	public Object payOrder(int Oid) {
		Map<String, Object>map = new HashMap<String, Object>();
		Order tmp = orderDAO.findById(Oid);
		tmp.setoPayState(1);
		orderDAO.update(tmp);
		map.put("Status", "Success");
		return map;
	}
	
}
