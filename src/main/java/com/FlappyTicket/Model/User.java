package com.FlappyTicket.Model;

public class User {
	private int uid;
	
	private String uName;
	
	private String uPassword;
	
	private String uTel;
	
	private int uScore;
	
	public User() {}
	
	public User(String uName, String uPassword, String uTel, int uScore) {
		this.setuName(uName);
		this.setuPassword(uPassword);
		this.setuTel(uTel);
		this.setuScore(uScore);
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getuPassword() {
		return uPassword;
	}

	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}

	public String getuTel() {
		return uTel;
	}

	public void setuTel(String uTel) {
		this.uTel = uTel;
	}

	public int getuScore() {
		return uScore;
	}

	public void setuScore(int uScore) {
		this.uScore = uScore;
	}
	
}
