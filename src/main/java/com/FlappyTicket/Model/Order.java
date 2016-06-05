package com.FlappyTicket.Model;

public class Order {
	private int oid;
	
	private int uid;
	
	private int sid;
	
	private int oSeatNumber;
	
	private int oPayState; 
	
	private String oSeatInfo;
	

	public Order() {}
	
	public Order(int oid, int uid, int sid, int oSeatNumber, String oSeatInfo, int oPayState) {
		this.setOid(oid);
		this.setUid(uid);
		this.setSid(sid);
		this.setoSeatNumber(oSeatNumber);
		this.setoSeatInfo(oSeatInfo);
		this.setoPayState(oPayState);
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

	public int getoSeatNumber() {
		return oSeatNumber;
	}

	public void setoSeatNumber(int oSeatNumber) {
		this.oSeatNumber = oSeatNumber;
	}

	public String getoSeatInfo() {
		return oSeatInfo;
	}

	public void setoSeatInfo(String oSeatInfo) {
		this.oSeatInfo = oSeatInfo;
	}

	public int getoPayState() {
		return oPayState;
	}

	public void setoPayState(int oPayState) {
		this.oPayState = oPayState;
	}
}
