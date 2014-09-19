package com.debauchery.fragment;

import com.debauchery.Globals;
import com.debauchery.R;
import com.debauchery.fragment.iface.FragmentInterface;
import com.debauchery.fragment.iface.FragmentTurnInterface;
import com.debauchery.state.PersistantStateDatabase;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class PromptFragment extends FragmentInterface implements FragmentTurnInterface{
	private static PromptFragment INSTANCE;
	int turn;
	public PromptFragment(int turn) {
		super(R.layout.slide_view_prompt);
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
		final TextView prompt =  (TextView) find(R.id.sv_prompt);
		String pr = db.getDescription(turn);
		System.out.println("PROMPT:"+pr);
		prompt.setText(pr);
	}
	public static PromptFragment instance(int t) {
		// TODO Auto-generated method stub
		if(INSTANCE == null) INSTANCE = new PromptFragment(t);
		else INSTANCE.setTurn(t);
		return INSTANCE;
	}
}
