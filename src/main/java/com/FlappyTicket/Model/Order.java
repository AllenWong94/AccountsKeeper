package com.FlappyTicket.Model;

public class Order {
	private int oid;
	
	private int uid;
	
	private int sid;
	
	private int OSeatNumber;
	
	private String OSeatInfo;
	
	private int OPayState; 
	
	public Order() {}
	
	public Order(int oid, int uid, int sid, int OSeatNumber, String OSeatInfo, int OPayState) {
		this.setOid(oid);
		this.setUid(uid);
		this.setSid(sid);
		this.setOSeatNumber(OSeatNumber);
		this.setOSeatInfo(OSeatInfo);
		this.setOPayState(OPayState);
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public int getOSeatNumber() {
		return OSeatNumber;
	}

	public void setOSeatNumber(int oSeatNumber) {
		OSeatNumber = oSeatNumber;
	}

	public String getOSeatInfo() {
		return OSeatInfo;
	}

	public void setOSeatInfo(String oSeatInfo) {
		OSeatInfo = oSeatInfo;
	}

	public int getOPayState() {
		return OPayState;
	}

	public void setOPayState(int oPayState) {
		OPayState = oPayState;
	}
}
