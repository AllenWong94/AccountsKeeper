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

import com.FlappyTicket.Model.Seat;

public class SeatDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Seat> findAll() {
		return jdbcTemplate.query("select * from seat", new BeanPropertyRowMapper<Seat>(Seat.class));
	}
	
	public Seat findById(int seid) {
		try {
			return jdbcTemplate.queryForObject("select * from seat where SEID = ?", new BeanPropertyRowMapper<Seat>(Seat.class), seid);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public String getSeatInfo(int seatNumber, final Seat seat) {
		if (seatNumber > seat.getSeColNumber()*seat.getSeRowNumber()) return "Out of range.";
		int row, col;
		row = seatNumber / (seat.getSeColNumber()+1) + 1;
		col = seatNumber - ((row - 1) * seat.getSeColNumber());
		return row+"ÅÅ"+col+"×ù";
	}
	
	public int insert(final Seat Seat) {
		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
				new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
						PreparedStatement st = conn.prepareStatement("insert into seat(SERowNumber, SEColNumber, SEState) values (?, ?, ?)", new String[]{"SEID"});
						st.setInt(1, Seat.getSeRowNumber());
						st.setInt(2, Seat.getSeColNumber());
						st.setString(3, Seat.getSeState());
						return st;
					}
				}, generatedKeyHolder
		);
		return generatedKeyHolder.getKey().intValue();
	}
	
	public int update(final Seat Seat) {
		return jdbcTemplate.update("update seat set SERowNumber = ?, SEColNumber = ?, SEState = ? where UID = ?", 
				new Object[] {Seat.getSeRowNumber(), Seat.getSeColNumber(), Seat.getSeState(), Seat.getSeid()});
	}
	
	public int delete(int seid) {
		return jdbcTemplate.update("delete from seat where UID =" + seid + "");
	}
	
}
