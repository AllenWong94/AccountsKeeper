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
public class SeatService {
	@Autowired
	private SeatDAO seatDAO;

	public Object getSeatState(int seid) {
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("SeatState", seatDAO.findById(seid).getSeState());
		return map;
	}
	
	public Object setSeatState(int seid, int bookseat) {
		Map<String, Object>map = new HashMap<String, Object>();
		
		Seat oldseat = seatDAO.findById(seid);
		String tmp = oldseat.getSeState();
		String newstate = tmp.substring(0, bookseat - 1) + '1' + tmp.substring(bookseat, tmp.length());
		oldseat.setSeState(newstate);
		seatDAO.update(oldseat);
		
		map.put("Status", "Success");
		return map;
	}
	
	public Object getrow(int seid){
		Map<String, Object>map = new HashMap<String, Object>();
		
		Seat oldseat = seatDAO.findById(seid);
		
		
		map.put("Row", oldseat.getSeRowNumber());
		return map;
	}
	public Object getcol(int seid){
		Map<String, Object>map = new HashMap<String, Object>();
		Seat oldseat = seatDAO.findById(seid);
		map.put("Col", oldseat.getSeColNumber());
		return map;
	}
		
}
