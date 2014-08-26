package com.debauchery.data;

import android.os.Parcel;

public class TextCard extends Card {
	String data;
	TextCard(Parcel p){
		super(p);
		this.data = p.readString();
	}
	TextCard(String data) {
		super("text");
		this.data = data;
		// TODO Auto-generated constructor stub
	}
	public String getData(){	
		return data;
	}
	public void writeToParcel(Parcel p, int flags){
		p.writeString(this.data);
		super.writeToParcel(p, flags);
	}
	
}