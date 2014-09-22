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
import android.widget.ImageView;
import android.widget.TextView;

public class StartGameFragment extends FragmentInterface implements FragmentTurnInterface{
	private static StartGameFragment INSTANCE;
	int turn;
	boolean startwithdraw;
	public StartGameFragment(GameActivityInterface g, int turn, boolean startwithdraw) {
		super(g,R.layout.slide_view_start);
		this.turn = turn;
		this.startwithdraw = startwithdraw;
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
		ImageView s= (ImageView) find(R.id.pr_s_img);
		if(startwithdraw)
			s.setImageResource(R.drawable.game_start_draw);
		else
			s.setImageResource(R.drawable.game_start_describe);
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		
	}
	public static StartGameFragment instance(GameActivityInterface g, int t, boolean b) {
		// TODO Auto-generated method stub
		if(INSTANCE == null) INSTANCE = new StartGameFragment(g,t,b);
		else INSTANCE.setTurn(t);
		return INSTANCE;
	}
}
