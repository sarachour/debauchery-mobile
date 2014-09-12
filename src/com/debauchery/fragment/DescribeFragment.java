package com.debauchery.fragment;

import com.debauchery.Globals;
import com.debauchery.R;
import com.debauchery.db.PersistantStateDatabase;
import com.debauchery.fragment.iface.FragmentActionInterface;
import com.debauchery.fragment.iface.FragmentTurnInterface;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class DescribeFragment extends FragmentActionInterface implements FragmentTurnInterface{
	String description="";
	int turn;
	public DescribeFragment(int turn) {
		super(R.layout.slide_act_describe);
		this.turn = turn;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		System.out.println("DESCRIBE: create");
		/*
		final EditText edit =  (EditText) find(R.id.sa_describe);
		description = db.getDescription(turn);
		edit.setText(description);
		if(turn == 0){
			edit.setHint("Please describe what you want the next person to draw.");
		}
		edit.setOnEditorActionListener(new OnEditorActionListener(){

			@Override
			public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
				// TODO Auto-generated method stub
				description = arg0.getText().toString();
				return false;
			}
			
		})
		*/;
	}
	
	@Override
	public void save() {
		System.out.println("DESCRIBE: save "+turn+","+description);
		// TODO Auto-generated method stub
		db.saveDescription(turn,description);
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		final EditText edit =  (EditText) find(R.id.sa_describe);
		description = db.getDescription(turn);
		edit.setText(description);
	}

	@Override
	public void setTurn(int turn) {
		// TODO Auto-generated method stub
		this.turn = turn;
		
	}
    
	

}
