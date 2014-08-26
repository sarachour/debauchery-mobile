package com.debauchery.sketch;

import android.os.Parcel;
import android.os.Parcelable;

public class Coord implements Parcelable {
	public float x;
	public float y;
	public Coord(){
		
	}
	public Coord(float x2, float y2){
		this.x = x2;
		this.y = y2;
	}
	public Coord(Parcel p){
		this.x = p.readFloat();
		this.y = p.readFloat();
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeFloat(this.x);
		dest.writeFloat(this.y);
		
	}
	public static final Parcelable.Creator<Coord> CREATOR = new Parcelable.Creator<Coord>() {
        public Coord createFromParcel(Parcel in) {
        	return new Coord(in);
        }

        public Coord[] newArray(int size) {
            return new Coord[size];
        }
    };
}