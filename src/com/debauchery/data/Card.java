package com.debauchery.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Card implements Parcelable {
	
	String type;
	Card(Parcel p){
		this.type = p.readString();
		
	}
	Card(String type){
		this.type = type;
	}
	public String getType(){
		return type;
	}
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(this.type);
		
	}
	public static final Parcelable.Creator<Card> CREATOR = new Parcelable.Creator<Card>() {
        public Card createFromParcel(Parcel in) {
            return new Card(in);
        }

        public Card[] newArray(int size) {
            return new Card[size];
        }
    };
}