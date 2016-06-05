package com.FlappyTicket.Model;

public class Session {

	private int sid;
	
	private int cid;
	
	private int mid;
	
	private int seid;
	
	private String sName;
	
	private String sTimeThrough;

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public int getSeid() {
		return seid;
	}

	public void setSeid(int seid) {
		this.seid = seid;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getsTimeThrough() {
		return sTimeThrough;
	}

	public void setsTimeThrough(String sTimeThrough) {
		this.sTimeThrough = sTimeThrough;
	}
	
	public Session(int sid, int cid, int mid, int seid, String sName, String sTimeThrough) {
		this.setSid(sid);
		this.setCid(cid);
		this.setMid(mid);
		this.setSeid(seid);
		this.setsName(sName);
		this.setsTimeThrough(sTimeThrough);
	}
	public Session() {
	}
	
}
