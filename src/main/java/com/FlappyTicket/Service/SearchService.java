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
public class SearchService {
	@Autowired
	private MovieDAO movieDAO;
	
	@Autowired
	private CinemaDAO cinemaDAO;
		
	public Object findByName(String name) {
		Object Mlist = movieDAO.findByMovieNameLike(name);
		Object Clist = cinemaDAO.findByCinemaNameLike(name);
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("Movie", Mlist);
		map.put("Cinema", Clist);
		return map;	
	}
}
