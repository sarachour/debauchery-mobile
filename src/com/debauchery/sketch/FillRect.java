package com.debauchery.sketch;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Parcel;

public class FillRect extends Action{
	public final static int FILL_ID = 1;
	public int color;
	public int x;
	public int y;
	public int w;
	public int h;
	public FillRect(int color, int x, int y, int w, int h){
		super(FILL_ID);
		this.color = color;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel p, int flags) {
		p.writeInt(color);
		p.writeInt(x); p.writeInt(y);
		p.writeInt(w); p.writeInt(h);
		
	}
	public FillRect(Parcel p){
		super(p);
		color = p.readInt();
		x=p.readInt(); y=p.readInt();
		w=p.readInt(); h=p.readInt();
	}
	public void draw(Canvas c, Paint paint){
		paint.setColor(this.color);
		paint.setStyle(Paint.Style.FILL_AND_STROKE); 
		c.drawRect(x,y,w,h, paint);
        
	}
}