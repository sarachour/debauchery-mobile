package com.debauchery.fragment;

import com.debauchery.Globals;
import com.debauchery.R;
import com.debauchery.fragment.iface.FragmentInterface;
import com.debauchery.fragment.iface.FragmentTurnInterface;
import com.debauchery.sketch.SketchPad;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

public class DrawFragment extends FragmentInterface implements FragmentTurnInterface{
	private static DrawFragment INSTANCE = null;
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
		sketchpad =  (SketchPad) this.find(R.id.fd_sketchpad);
		// TODO Auto-generated method stub
		DrawOptionsFragment ctrls_frag = new DrawOptionsFragment(sketchpad);
		this.getFragmentManager().beginTransaction().replace(R.id.fd_controls_menu, ctrls_frag).commit();
		this.load();
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
	public static DrawFragment instance(int t) {
		// TODO Auto-generated method stub
		if(INSTANCE == null) INSTANCE = new DrawFragment(t);
		else INSTANCE.setTurn(t);
		return INSTANCE;
	}


}
