package com.debauchery.fragment;

import com.debauchery.Globals;
import com.debauchery.R;
import com.debauchery.fragment.iface.FragmentInterface;
import com.debauchery.fragment.iface.FragmentTurnInterface;
import com.debauchery.sketch.SketchPad;
import com.debauchery.state.PersistantStateDatabase;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class ShowFragment extends FragmentInterface implements FragmentTurnInterface {
	int turn;
	private static ShowFragment INSTANCE = null;
	public ShowFragment(int turn) {
		super(R.layout.slide_view_show);
		this.turn = turn;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create() {
		
		final SketchPad img =  (SketchPad) find(R.id.sv_sketchpad);
		img.lock();
		this.load();
	}

	@Override
	public void save() {
		
	}

	@Override
	public void load() {
		final SketchPad img =  (SketchPad) find(R.id.sv_sketchpad);
		img.playback(db.getSketch(turn), 200);
		
	}

	@Override
	public void setTurn(int turn) {
		this.turn = turn;
	}

	public static ShowFragment instance(int t) {
		// TODO Auto-generated method stub
		if(INSTANCE == null) INSTANCE = new ShowFragment(t);
		else INSTANCE.setTurn(t);
		return INSTANCE;
	}
}
