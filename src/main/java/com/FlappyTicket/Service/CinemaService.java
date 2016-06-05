package com.FlappyTicket.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.FlappyTicket.Model.*;
import com.FlappyTicket.Dao.*;

@Service
public class CinemaService {
	@Autowired
	private CinemaDAO cinemaDAO;
	@Autowired
	private SessionService sessionservice;
	
	public List<Cinema> findAll() {
		return cinemaDAO.findAll();

	}
	
	public List<Cinema> findByMID(int mid) {
	
	
		List<Cinema> res = new ArrayList<Cinema>();
		Set<Integer> set = new HashSet<Integer>();
		
		List<Session> Slist = sessionservice.findByMID(mid);
		
		for (int i = 0; i < Slist.size(); i++) {
			set.add(new Integer(Slist.get(i).getCid()));
		}
		
		for (Integer value: set) {
			res.add(cinemaDAO.findById(value));
		}
		return res;
	}
	
}
