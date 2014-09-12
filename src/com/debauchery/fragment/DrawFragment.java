package com.debauchery.fragment;

import com.debauchery.Globals;
import com.debauchery.R;
import com.debauchery.fragment.iface.FragmentActionInterface;
import com.debauchery.fragment.iface.FragmentTurnInterface;
import com.debauchery.sketch.SketchPad;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;

public class DrawFragment extends FragmentActionInterface implements FragmentTurnInterface{
	SketchPad sketchpad;
	int turn;
	public DrawFragment(int turn) {
		super(R.layout.slide_act_sketch);
		this.turn = turn;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void setTurn(int turn) {
		this.turn = turn;
		
	}
	@Override
	public void create() {
		System.out.println("DRAW: create");
		// TODO Auto-generated method stub
		sketchpad =  (SketchPad) this.find(R.id.fd_sketchpad);
		sketchpad.loadData(db.getSketch(turn));
	}

	@Override
	public void save() {
		System.out.println("SKETCH: save");
		sketchpad =  (SketchPad) this.find(R.id.fd_sketchpad);
		db.saveSketch(turn,sketchpad.getData());
	}
	
	@Override
	public void load() {
		// TODO Auto-generated method stub
		sketchpad =  (SketchPad) this.find(R.id.fd_sketchpad);
		sketchpad.loadData(db.getSketch(turn));
	}


}
