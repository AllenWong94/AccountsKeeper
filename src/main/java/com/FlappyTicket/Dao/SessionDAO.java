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
		return jdbcTemplate.query("select * from session", new BeanPropertyRowMapper<Session>(Session.class));
	}
	
	public Session findById(int sid) {
		try {
			return jdbcTemplate.queryForObject("select * from session where SID = ?", new BeanPropertyRowMapper<Session>(Session.class), sid);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public List<Session> findByCID(int cid) {
		try {
			return jdbcTemplate.query("select * from session where CID = ?", new BeanPropertyRowMapper<Session>(Session.class), cid);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public List<Session> findByMID(int mid) {
		try {
			return jdbcTemplate.query("select * from session where MID = ?", new BeanPropertyRowMapper<Session>(Session.class), mid);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public List<Session> findByCMID(int cid, int mid) {
		try {
			return jdbcTemplate.query("select * from session where CID = ? and MID = ?", new BeanPropertyRowMapper<Session>(Session.class), cid, mid);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public int insert(final Session Session) {
		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
				new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
						PreparedStatement st = conn.prepareStatement("insert into session(CID, MID, SEID, SName, STimeThrough, SPrice, SDay) values (?, ?, ?, ?, ?, ?, ?)", new String[]{"SID"});
						st.setInt(1, Session.getCid());
						st.setInt(2, Session.getMid());
						st.setInt(3, Session.getSeid());
						st.setString(4, Session.getsName());
						st.setString(5, Session.getsTimeThrough());
						st.setInt(6, Session.getsPrice());
						st.setString(7, Session.getsDay());
						return st;
					}
				}, generatedKeyHolder
		);
		return generatedKeyHolder.getKey().intValue();
	}
	
	public int update(final Session Session) {
		return jdbcTemplate.update("update session set CID = ?, MID = ?, SEID = ?, SName = ?, STimeThrough = ?, SPrice = ?, SDay = ? where SID = ?", 
				new Object[] {Session.getCid(), Session.getMid(), Session.getSeid(), Session.getsName(), Session.getsTimeThrough(), Session.getsPrice(), Session.getsDay(), Session.getSid()});
	}
	
	public int delete(int sid) {
		return jdbcTemplate.update("delete from session where SID =" + sid + "");
	}
	
}
