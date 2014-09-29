package com.debauchery.fragment;

import com.debauchery.Globals;
import com.debauchery.R;
import com.debauchery.fragment.iface.FragmentInterface;
import com.debauchery.fragment.iface.FragmentTurnInterface;
import com.debauchery.fragment.iface.GameActivityInterface;
import com.debauchery.state.Databases;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class DescribeFragment extends FragmentInterface implements FragmentTurnInterface{
	private static DescribeFragment INSTANCE;
	String description="";
	int turn;
	public DescribeFragment(GameActivityInterface g, int turn) {
		super(g, R.layout.slide_act_describe);
		this.turn = turn;
		// TODO Auto-generated constructor stub
	}

	public void create() {
		// TODO Auto-generated method stub
		final EditText edit =  (EditText) find(R.id.sa_describe);
		Button next = (Button) this.find(R.id.sa_d_done);
		Button prev = (Button) this.find(R.id.sa_d_back);
		description = db.getDescription(turn);
		edit.setText(description);
		if(turn == 0){
			edit.setHint("Please describe what you want the next person to draw.");
		}
		//on key changed
		edit.setOnKeyListener( new OnKeyListener(){
			final EditText edit =  (EditText) find(R.id.sa_describe);
			@Override
			public boolean onKey(View arg0, int arg1, KeyEvent arg2) {
				// TODO Auto-generated method stub
				description = edit.getText().toString();
				return false;
			}
			
		});
		edit.setOnEditorActionListener(new OnEditorActionListener(){

			@Override
			public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
				// TODO Auto-generated method stub
				description = arg0.getText().toString();
				return false;
			}
			
		});
		
		
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
	}
	
	public void save() {
		// TODO Auto-generated method stub
		db.saveDescription(turn,description);
	}

	public void load() {
		// TODO Auto-generated method stub
		final EditText edit =  (EditText) find(R.id.sa_describe);
		description = db.getDescription(turn);
		edit.setText(description);
		TextView lvl = (TextView) this.find(R.id.sa_d_turn);
		lvl.setText("Turn "+this.turn);
	}

	@Override
	public void setTurn(int turn) {
		// TODO Auto-generated method stub
		this.turn = turn;
		
	}
    
	public static DescribeFragment instance(GameActivityInterface g, int t) {
		// TODO Auto-generated method stub
		if(INSTANCE == null) INSTANCE = new DescribeFragment(g, t);
		else INSTANCE.setTurn(t);
		return INSTANCE;
	}

}
