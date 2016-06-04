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
	
	/*public int insert(final Session Session) {
		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
				new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
						PreparedStatement st = conn.prepareStatement("insert into Session(MName, MType, MDetail, MTime, MPicture) values (?, ?, ?, ?, ?)", new String[]{"id"});
						st.setString(1, Session.getmName());
						st.setString(2, Session.getmType());
						st.setString(3, Session.getmDetail());
						st.setString(4, Session.getmTime());
						st.setString(5, Session.getmPicture());
						return st;
					}
				}, generatedKeyHolder
		);
		return generatedKeyHolder.getKey().intValue();
	}
	
	public int update(final Session Session) {
		return jdbcTemplate.update("update Session set MName = ?, MType = ?, MDetail = ?, MTime = ?, MPicture = ? where MID = ?", 
				new Object[] {Session.getmName(), Session.getmType(), Session.getmDetail(), Session.getmTime(), Session.getmPicture(), Session.getMid()});
	}
	
	public int delete(int mid) {
		return jdbcTemplate.update("delete from Session where MID =" + mid + "");
	}*/
	
}
