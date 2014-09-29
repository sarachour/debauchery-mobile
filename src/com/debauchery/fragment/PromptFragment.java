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

public class PromptFragment extends FragmentInterface implements FragmentTurnInterface{
	private static PromptFragment INSTANCE;
	int turn;
	boolean show_back;
	public PromptFragment(GameActivityInterface g, int turn, boolean b) {
		super(g,R.layout.slide_view_prompt);
		this.turn = turn;
		this.show_back =b;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void setTurn(int turn) {
		this.turn = turn;
	}
	@Override
	public void create() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		final TextView prompt =  (TextView) find(R.id.prompt);
		String pr = db.getDescription(turn);
		prompt.setText(pr);
		TextView lvl = (TextView) this.find(R.id.turn);
		lvl.setText("Turn "+this.turn);
	}
}
