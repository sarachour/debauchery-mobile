package com.debauchery.data;

import java.util.List;

import android.os.Parcel;

import com.debauchery.sketch.Action;
import com.debauchery.sketch.SketchPad;

public class ImageCard extends Card {
	List<Action> data;
	ImageCard(Parcel p){
		super(p);
		p.readTypedList(this.data, SketchPad.CREATOR);
	}
	ImageCard(List<Action> data) {
		super("image");
		this.data = data;
	}
	
}