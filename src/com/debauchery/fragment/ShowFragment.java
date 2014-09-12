package com.debauchery.fragment;

import com.debauchery.Globals;
import com.debauchery.R;
import com.debauchery.db.PersistantStateDatabase;
import com.debauchery.fragment.iface.FragmentTurnInterface;
import com.debauchery.fragment.iface.FragmentViewInterface;
import com.debauchery.sketch.SketchPad;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class ShowFragment extends FragmentViewInterface implements FragmentTurnInterface {
	int turn;
	public ShowFragment(int turn) {
		super(R.layout.slide_view_show);
		this.turn = turn;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create() {
		
		final SketchPad img =  (SketchPad) find(R.id.sv_sketchpad);
		img.lock();
		img.playback(db.getSketch(turn), 200);
		
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		db.init(db.getTurn(), Globals.SHOW_PHASE);
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		db.init(db.getTurn(), Globals.SHOW_PHASE);
	}

	@Override
	public void setTurn(int turn) {
		this.turn = turn;
	}
}
