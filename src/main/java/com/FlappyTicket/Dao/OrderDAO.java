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
		return jdbcTemplate.query("select * from olist", new BeanPropertyRowMapper<Order>(Order.class));
	}
	
	public Order findById(int oid) {
		try {
			return jdbcTemplate.queryForObject("select * from olist where OID = ?", new BeanPropertyRowMapper<Order>(Order.class), oid);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public List<Order> findByUID(int uid) {
		try {
			return jdbcTemplate.query("select * from olist where UID = ?", new BeanPropertyRowMapper<Order>(Order.class), uid);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public int insert(final Order Order) {
		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
				new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
						PreparedStatement st = conn.prepareStatement("insert into olist(UID, SID, OSeatNumber, OSeatInfo, OPayState) values (?, ?, ?, ?, ?)", new String[]{"OID"});
						st.setInt(1, Order.getUid());
						st.setInt(2, Order.getSid());
						st.setInt(3, Order.getoSeatNumber());
						st.setString(4, Order.getoSeatInfo());
						st.setInt(5, Order.getoPayState());
						return st;
					}
				}, generatedKeyHolder
		);
		return generatedKeyHolder.getKey().intValue();
	}
	
	public int update(final Order Order) {
		return jdbcTemplate.update("update olist set UID = ?, SID = ?, OSeatNumber = ?, OSeatInfo = ?, OPayState = ? where OID = ?", 
			new Object[] {Order.getUid(), Order.getSid(), Order.getoSeatNumber(), Order.getoSeatInfo(), Order.getoPayState(), Order.getOid()});
	}
	
	public int delete(int oid) {
		return jdbcTemplate.update("delete from olist where OID =" + oid + "");
	}
	
}
