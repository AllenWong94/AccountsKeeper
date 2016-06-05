package com.FlappyTicket.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.FlappyTicket.Model.*;
import com.FlappyTicket.Dao.*;

@Service
public class SessionService {
	@Autowired
	private SessionDAO sessionDAO;
	
	public Object findAll() {
		return sessionDAO.findAll();

	}
	
	public List<Session> findByMID(int CID) {
		return sessionDAO.findByMID(CID); //找出电影院所有场次    findByID改名为findByMID;
		
	}
	
	public List<Session> findByCID(int CID) {
		return sessionDAO.findByCID(CID); //找出电影院所有场次
		
	}
	
	public List<Session> findByCMID(int CID, int MID) {
		return sessionDAO.findByCMID(CID, MID); //找出电影院指定电影所有场次
		
	}
	
	
}
