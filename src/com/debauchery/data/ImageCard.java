package com.debauchery.data;

import java.util.List;

import android.os.Parcel;

import com.debauchery.sketch.Action;
import com.debauchery.sketch.SketchPad;
import com.debauchery.sketch.SketchPadData;

public class ImageCard extends Card {
	SketchPadData data;
	ImageCard(Parcel p){
		super(p);
		this.data = p.readParcelable(SketchPadData.class.getClassLoader());
	}
	public ImageCard(SketchPadData sketchPadData) {
		super("draw");
		// TODO Auto-generated constructor stub
		this.data = sketchPadData;
	}
	
}