package com.debauchery.fragment;

import com.debauchery.Globals;
import com.debauchery.R;
import com.debauchery.fragment.iface.FragmentInterface;
import com.debauchery.fragment.iface.FragmentTurnInterface;
import com.debauchery.fragment.iface.GameActivityInterface;
import com.debauchery.sketch.SketchPad;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class DrawFragment extends FragmentInterface implements FragmentTurnInterface{
	private static DrawFragment INSTANCE = null;
	SketchPad sketchpad;
	int turn;
	public DrawFragment(GameActivityInterface g, int turn) {
		super(g,R.layout.slide_act_sketch);
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
		
		
		Button next = (Button) this.find(R.id.fd_done);
		Button prev = (Button) this.find(R.id.fd_back);
		
		next.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				frame.next();
			}
			
		});
		prev.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				frame.prev();
			}
			
		});
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
		TextView lvl = (TextView) this.find(R.id.fd_turn);
		lvl.setText("Turn "+this.turn);
	}
	public static DrawFragment instance(GameActivityInterface g, int t) {
		// TODO Auto-generated method stub
		if(INSTANCE == null) INSTANCE = new DrawFragment(g,t);
		else INSTANCE.setTurn(t);
		return INSTANCE;
	}


}
