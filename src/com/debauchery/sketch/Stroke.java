package com.debauchery.sketch;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Parcel;

public class Stroke extends Action{
	public final static int STROKE_ID = 0;
	public SketchPath path;
	public int color;
	public int thickness;
	public Stroke(SketchPath p, int color, int thickness){
		super(STROKE_ID);
		this.path = p;
		this.color = color;
		this.thickness = thickness;
	}
	public Stroke(Parcel p){
		super(p);
		this.color = p.readInt();
		this.thickness = p.readInt();
		this.path = new SketchPath(p);
		//how do i unpack paths.
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(color);
		dest.writeInt(thickness);
		// TODO Auto-generated method stub
		
	}
	public void draw(Canvas c, Paint paint){
		paint.setColor(this.color);
		paint.setStrokeWidth(this.thickness);
		paint.setStyle(Paint.Style.STROKE);
		c.drawPath(path.toPath(), paint);
        
        
	}
}