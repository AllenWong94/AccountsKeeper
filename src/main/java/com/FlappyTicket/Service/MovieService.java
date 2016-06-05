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
public class MovieService {
	@Autowired
	private MovieDAO movieDAO;
	private SessionService sessionservice;
	
	public List<Movie> findAll() {
		return movieDAO.findAll();

	}
	

	public List<Movie> findByCID(int CID) {
		List<Movie> res = new ArrayList<Movie>();
		Set<Integer> set = new HashSet<Integer>();
		List<Session> Slist = sessionservice.findByCID(CID);
		
		for (int i = 0; i < Slist.size(); i++) {
			set.add(Slist.get(i).getMid());
		}
		
		for (Integer value: set) {
			res.add(movieDAO.findById(value));
		}
		return res;
		
	}
	
	
}
