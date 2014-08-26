package com.debauchery.sketch;

import java.util.LinkedList;
import java.util.List;

import android.graphics.Path;
import android.os.Parcel;
import android.os.Parcelable;

public class SketchPath implements Parcelable{
	List<Coord> path = new LinkedList<Coord>();
	Path gpath = new Path();
	public SketchPath(float x, float y){
		path.add(new Coord(x,y));
		gpath.moveTo(x, y);
	}
	public SketchPath(Parcel p){
		p.readTypedList(path, Coord.CREATOR);
	}
	public void add(float x, float y){
		path.add(new Coord(x,y));
        gpath.lineTo(x, y);
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		// TODO Auto-generated method stub
		arg0.writeList(path);
		
	}
	public Path toPath(){
		return gpath;
	}
}