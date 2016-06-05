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

import com.FlappyTicket.Model.Order;

public class OrderDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Order> findAll() {
		return jdbcTemplate.query("select * from order", new BeanPropertyRowMapper<Order>(Order.class));
	}
	
	public Order findById(int oid) {
		try {
			return jdbcTemplate.queryForObject("select * from order where OID = ?", new BeanPropertyRowMapper<Order>(Order.class), oid);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public List<Order> findByUid(int uid) {
		try {
			return jdbcTemplate.query("select * from order where UID = ?", new BeanPropertyRowMapper<Order>(Order.class), uid);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public int insert(final Order Order) {
		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
				new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
						PreparedStatement st = conn.prepareStatement("insert into order(UID, SID, SeatNumber, SeatInfo, PayState) values (?, ?, ?, ?, ?)", new String[]{"OID"});
						st.setInt(1, Order.getUid());
						st.setInt(2, Order.getSid());
						st.setInt(3, Order.getOSeatNumber());
						st.setString(4, Order.getOSeatInfo());
						st.setInt(5, Order.getOPayState());
						return st;
					}
				}, generatedKeyHolder
		);
		return generatedKeyHolder.getKey().intValue();
	}
	
	public int update(final Order Order) {
		return jdbcTemplate.update("update order set UID = ?, SID = ?, SeatNumber = ?, SeatInfo = ?, PayState = ? where OID = ?", 
			new Object[] {Order.getUid(), Order.getSid(), Order.getOSeatNumber(), Order.getOSeatInfo(), Order.getOPayState(), Order.getOid()});
	}
	
	public int delete(int oid) {
		return jdbcTemplate.update("delete from order where OID =" + oid + "");
	}
	
}
