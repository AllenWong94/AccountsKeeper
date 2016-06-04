package com.FlappyTicket.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.FlappyTicket.Model.*;
import com.FlappyTicket.Dao.*;

@Service
public class CinemaService {
	@Autowired
	private CinemaDAO cinemaDAO;
	
	public Object findAll() {
		return cinemaDAO.findAll();

	}
	
}
