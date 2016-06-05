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

import com.FlappyTicket.Model.User;

public class UserDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<User> findAll() {
		return jdbcTemplate.query("select * from user", new BeanPropertyRowMapper<User>(User.class));
	}
	
	public User findById(int uid) {
		try {
			return jdbcTemplate.queryForObject("select * from user where UID = ?", new BeanPropertyRowMapper<User>(User.class), uid);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public User findByUserName(String uname) {
		try {
			return jdbcTemplate.queryForObject("select * from user where UName = ?", new BeanPropertyRowMapper<User>(User.class), uname);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public List<User> login(String name, String password) {
		try {
			return jdbcTemplate.query("select * from user where UName = ? and UPassword = ?", new BeanPropertyRowMapper<User>(User.class), name, password);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public int insert(final User User) {
		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
				new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
						PreparedStatement st = conn.prepareStatement("insert into user(UName, UPassword, UTel, UScore) values (?, ?, ?, ?)", new String[]{"UID"});
						st.setString(1, User.getuName());
						st.setString(2, User.getuPassword());
						st.setString(3, User.getuTel());
						st.setInt(4, User.getuScore());
						return st;
					}
				}, generatedKeyHolder
		);
		return generatedKeyHolder.getKey().intValue();
	}
	
	public int update(final User user) {
		return jdbcTemplate.update("update user set UName = ?, UPassword = ?, UTel = ?, UScore = ? where UID = ?", 
				new Object[] {user.getuName(), user.getuPassword(), user.getuTel(), user.getuScore(), user.getUid()});
	}
	
	public int delete(int uid) {
		return jdbcTemplate.update("delete from user where UID =" + uid + "");
	}
	
}
