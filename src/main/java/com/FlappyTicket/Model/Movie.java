
package com.FlappyTicket.Model;

public class Movie {
	private int mid;
	
	private String mName;
	
	private String mType;
	
	private String mDetail;
	
	private String mTime;
	
	private String mPicture;

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public String getmDetail() {
		return mDetail;
	}

	public void setmDetail(String mDetail) {
		this.mDetail = mDetail;
	}

	public String getmTime() {
		return mTime;
	}

	public void setmTime(String mTime) {
		this.mTime = mTime;
	}
	
	public Movie() {}
	
	public Movie(int mid, String mname, String mtype, String mDetail, String mTime, String mPicture) {
		this.mid = mid;
		this.setmName(mname);
		this.setmType(mtype);
		this.setmDetail(mDetail);
		this.setmTime(mTime);
		this.setmPicture(mPicture);
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmType() {
		return mType;
	}

	public void setmType(String mType) {
		this.mType = mType;
	}

	public String getmPicture() {
		return mPicture;
	}

	public void setmPicture(String mPicture) {
		this.mPicture = mPicture;
	}
}