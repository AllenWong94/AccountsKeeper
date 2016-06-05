package com.FlappyTicket.Model;

public class Seat {
	private int seid;
	
	private int seRowNumber;
	
	private int seColNumber;
	
	private String seState;
	
	public Seat() {}
	
	public Seat(int seid, int seRowNumber, int seColNumber, String seState) {
		this.setSeid(seid);
		this.setSeRowNumber(seRowNumber);
		this.setSeColNumber(seColNumber);
		this.setSeState(seState);
	}

	public int getSeid() {
		return seid;
	}

	public void setSeid(int seid) {
		this.seid = seid;
	}

	public int getSeRowNumber() {
		return seRowNumber;
	}

	public void setSeRowNumber(int seRowNumber) {
		this.seRowNumber = seRowNumber;
	}

	public int getSeColNumber() {
		return seColNumber;
	}

	public void setSeColNumber(int seColNumber) {
		this.seColNumber = seColNumber;
	}

	public String getSeState() {
		return seState;
	}

	public void setSeState(String seState) {
		this.seState = seState;
	}
	
}
