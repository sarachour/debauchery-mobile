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
import android.widget.TextView;

public class EndGameFragment extends FragmentInterface implements FragmentTurnInterface{
	private static EndGameFragment INSTANCE;
	int turn;
	public EndGameFragment(GameActivityInterface g, int turn) {
		super(g,R.layout.slide_view_end);
		this.turn = turn;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void setTurn(int turn) {
		this.turn = turn;
	}
	@Override
	public void create() {
		// TODO Auto-generated method stub
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
	public static EndGameFragment instance(GameActivityInterface g, int t) {
		// TODO Auto-generated method stub
		if(INSTANCE == null) INSTANCE = new EndGameFragment(g,t);
		else INSTANCE.setTurn(t);
		return INSTANCE;
	}
}
