
package com.FlappyTicket.Model;

public class Cinema {
	private int cid;
	
	private String cName;
	
	private String cAddress;
	
	private String cTel;
	
	private String cEmail;
	
	public Cinema(int cid, String cName, String cAddress) {
		this.setCid(cid);
		this.setcName(cName);
		this.setcAddress(cAddress);
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getcAddress() {
		return cAddress;
	}

	public void setcAddress(String cAddress) {
		this.cAddress = cAddress;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getcTel() {
		return cTel;
	}

	public void setcTel(String cTel) {
		this.cTel = cTel;
	}

	public String getcEmail() {
		return cEmail;
	}

	public void setcEmail(String cEmail) {
		this.cEmail = cEmail;
	}
	
	public Cinema() {
		super();
	}
	
}