package com.debauchery.fragment;

import com.debauchery.Globals;
import com.debauchery.R;
import com.debauchery.fragment.iface.FragmentInterface;
import com.debauchery.fragment.iface.FragmentTurnInterface;
import com.debauchery.fragment.iface.GameActivityInterface;
import com.debauchery.sketch.SketchPad;
import com.debauchery.state.Databases;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ShowFragment extends FragmentInterface implements FragmentTurnInterface {
	int turn;
	private static ShowFragment INSTANCE = null;
	private boolean show_back;
	public ShowFragment(GameActivityInterface g, int turn, boolean b) {
		super(g,R.layout.slide_view_show);
		this.turn = turn;
		this.show_back = b;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create() {
		
		final SketchPad img =  (SketchPad) find(R.id.sketchpad);
		img.lock();
		Button next = (Button) this.find(R.id.next);
		Button prev = (Button) this.find(R.id.back);
		if(!show_back){
			prev.setVisibility(View.GONE);
		}
		next.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				frame.next();
			}
			
		});
		if(show_back)
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
		
	}

	@Override
	public void load() {
		final SketchPad img =  (SketchPad) find(R.id.sketchpad);
		img.playback(db.getSketch(turn), 200);
		TextView lvl = (TextView) this.find(R.id.turn);
		lvl.setText("Turn "+this.turn);
	}

	@Override
	public void setTurn(int turn) {
		this.turn = turn;
	}

	
}
