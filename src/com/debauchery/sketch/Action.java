package com.debauchery.sketch;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Parcel;
import android.os.Parcelable;

public class Action implements Parcelable{
	public Action(Parcel p){
		
	}
	public Action(){
		
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
        	if(id == SketchPad.FILL_ACTION)
        		return new FillRect(in);
        	else if(id == SketchPad.STROKE_ACTION)
        		return new Stroke(in);
        	else
        		return null;
        }

        public Action[] newArray(int size) {
            return new Action[size];
        }
    };
}