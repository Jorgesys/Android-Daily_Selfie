package com.jorgesys.dailyselfie;

import android.graphics.Bitmap;

public class Selfie {	
	private int mID;
	private String mName;
	private String mPicturePath;
	private String mDate;
	
	private Bitmap mPictureBitmap;
	
	public Selfie(String name, String picturePath) {
		super();
		
		mName = name;
		mPicturePath = picturePath;
	}
	
	public int getID() {
		return mID;
	}
	public void setID(int ID) {
		mID = ID;
	}
	public String getName() {
		return mName;
	}
	public void setName(String name) {
		mName = name;
	}
	public String getPicturePath() {
		return mPicturePath;
	}
	public void setPicturePath(String picturePath) {
		mPicturePath = picturePath;
	}
	public Bitmap getPictureBitmap() {
		return mPictureBitmap;
	}
	public void setPictureBitmap(Bitmap pictureBitmap) {
		mPictureBitmap = pictureBitmap;
	}
	
	public String getDate() {
		return mDate;
	}
	public void setDate(String date) {
		mDate = date;
	}
}