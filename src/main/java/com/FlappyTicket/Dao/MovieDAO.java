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

import com.FlappyTicket.Model.Movie;

public class MovieDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Movie> findAll() {
		return jdbcTemplate.query("select * from movie", new BeanPropertyRowMapper<Movie>(Movie.class));
	}
	
	public Movie findById(int mid) {
		try {
			return jdbcTemplate.queryForObject("select * from movie where MID = ?", new BeanPropertyRowMapper<Movie>(Movie.class), mid);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public Movie findByMovieName(String mname) {
		try {
			return jdbcTemplate.queryForObject("select * from movie where MName = ?", new BeanPropertyRowMapper<Movie>(Movie.class), mname);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public int insert(final Movie Movie) {
		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
				new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
						PreparedStatement st = conn.prepareStatement("insert into Movie(MName, MType, MDetail, MTime, MPicture) values (?, ?, ?, ?, ?)", new String[]{"MID"});
						st.setString(1, Movie.getmName());
						st.setString(2, Movie.getmType());
						st.setString(3, Movie.getmDetail());
						st.setString(4, Movie.getmTime());
						st.setString(5, Movie.getmPicture());
						return st;
					}
				}, generatedKeyHolder
		);
		return generatedKeyHolder.getKey().intValue();
	}
	
	public int update(final Movie Movie) {
		return jdbcTemplate.update("update Movie set MName = ?, MType = ?, MDetail = ?, MTime = ?, MPicture = ? where MID = ?", 
				new Object[] {Movie.getmName(), Movie.getmType(), Movie.getmDetail(), Movie.getmTime(), Movie.getmPicture(), Movie.getMid()});
	}
	
	public int delete(int mid) {
		return jdbcTemplate.update("delete from Movie where MID =" + mid + "");
	}
	
}
