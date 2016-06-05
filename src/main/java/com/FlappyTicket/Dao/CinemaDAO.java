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

import com.FlappyTicket.Model.Cinema;

public class CinemaDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Cinema> findAll() {
		return jdbcTemplate.query("select * from cinema", new BeanPropertyRowMapper<Cinema>(Cinema.class));
	}
	
	public Cinema findById(int cid) {
		try {
			return jdbcTemplate.queryForObject("select * from cinema where CID = ?", new BeanPropertyRowMapper<Cinema>(Cinema.class), cid);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public Cinema findByCinemaName(String cname) {
		try {
			return jdbcTemplate.queryForObject("select * from cinema where CName = ?", new BeanPropertyRowMapper<Cinema>(Cinema.class), cname);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public List<Cinema> findByCinemaNameLike(String cname) {
		try {
			return jdbcTemplate.query("select * from cinema where CName like ?", new BeanPropertyRowMapper<Cinema>(Cinema.class), '%'+cname+'%');
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public int insert(final Cinema cinema) {
		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
				new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
						PreparedStatement st = conn.prepareStatement("insert into cinema(CName, CAddress, CTel, CEmail) values (?, ?, ?, ?)", new String[]{"CID"});
						st.setString(1, cinema.getcName());
						st.setString(2, cinema.getcAddress());
						st.setString(3, cinema.getcTel());
						st.setString(4, cinema.getcEmail());
						return st;
					}
				}, generatedKeyHolder
		);
		return generatedKeyHolder.getKey().intValue();
	}
	
	public int update(final Cinema cinema) {
		return jdbcTemplate.update("update cinema set CName = ?, CAddress = ?, CTel = ?, CEail = ? where CID = ?", 
				new Object[] {cinema.getcName(), cinema.getcAddress(), cinema.getcTel(), cinema.getcEmail(), cinema.getCid()});
	}
	
	public int delete(int cid) {
		return jdbcTemplate.update("delete from cinema where CID =" + cid + "");
	}
	
}
