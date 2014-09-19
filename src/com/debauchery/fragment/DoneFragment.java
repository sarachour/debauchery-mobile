package com.debauchery.fragment;

import com.debauchery.Globals;
import com.debauchery.R;
import com.debauchery.fragment.iface.FragmentInterface;
import com.debauchery.fragment.iface.FragmentTurnInterface;
import com.debauchery.fragment.iface.GameActivityInterface;
import com.debauchery.state.Databases;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class DoneFragment extends FragmentInterface implements FragmentTurnInterface{
	private static DoneFragment INSTANCE;
	int turn;
	public DoneFragment(GameActivityInterface g, int turn) {
		super(g, R.layout.slide_action_done);
		this.turn = turn;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void setTurn(int turn) {
		this.turn = turn;
	}
	@Override
	public void create() {
		Button save = (Button) this.find(R.id.dv_save);
		Button quit = (Button) this.find(R.id.dv_quit);
		
		quit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				frame.exit();
			}
			
		});
		
		save.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		this.load();
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		
	}
	public static DoneFragment instance(GameActivityInterface g, int t) {
		// TODO Auto-generated method stub
		if(INSTANCE == null) INSTANCE = new DoneFragment(g,t);
		else INSTANCE.setTurn(t);
		return INSTANCE;
	}
}
