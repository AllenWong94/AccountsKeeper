package com.FlappyTicket.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.FlappyTicket.Model.Session;

public class SessionDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Session> findAll() {
		return jdbcTemplate.query("select * from Session", new BeanPropertyRowMapper<Session>(Session.class));
	}
	
	public Session findById(int mid) {
		try {
			return jdbcTemplate.queryForObject("select * from Session where SID = ?", new BeanPropertyRowMapper<Session>(Session.class), mid);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public Session findBySessionName(String mname) {
		try {
			return jdbcTemplate.queryForObject("select * from Session where SName = ?", new BeanPropertyRowMapper<Session>(Session.class), mname);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
}
