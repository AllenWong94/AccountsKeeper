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
		return sessionDAO.findByMID(CID); //�ҳ���ӰԺ���г���    findByID����ΪfindByMID;
		
	}
	
	public List<Session> findByCID(int CID) {
		return sessionDAO.findByCID(CID); //�ҳ���ӰԺ���г���
		
	}
	
	public List<Session> findByCMID(int CID, int MID) {
		return sessionDAO.findByCMID(CID, MID); //�ҳ���ӰԺָ����Ӱ���г���
		
	}
	
	
}
