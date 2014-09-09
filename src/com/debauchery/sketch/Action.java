package com.debauchery.sketch;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Parcel;
import android.os.Parcelable;

public class Action implements Parcelable {
	int id =-1;
	public Action(Parcel p){
		
	}
	public Action(int id){
		this.id = id;
	}
	public int getType(){
		return id;
	}
	public void draw(Canvas c, Paint p){
		
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		
	}
	public static final Parcelable.Creator<Action> CREATOR = new Parcelable.Creator<Action>() {
        public Action createFromParcel(Parcel in) {
        	int id = in.readInt();
        	if(id == FillRect.FILL_ID)
        		return new FillRect(in);
        	else if(id == Stroke.STROKE_ID)
        		return new Stroke(in);
        	else
        		return null;
        }

        public Action[] newArray(int size) {
            return new Action[size];
        }
    };
}