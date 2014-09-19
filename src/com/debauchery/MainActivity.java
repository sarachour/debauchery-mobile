package com.debauchery;


import com.debauchery.state.Preferences;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {
	final String GAME_TYPE_KEY = "GAME_KEY";
	final int LOCAL_GAME=0; 
	final int MAIN_MENU=1;
	int type = MAIN_MENU;
	
	Preferences prefs;
	private void go(){
		Intent i;
		System.out.println("CREATING:"+type);
		switch(type){
			case LOCAL_GAME:
				i = new Intent(getApplicationContext(), LocalGameActivity.class);
				startActivity(i);
				break;
			case MAIN_MENU:
			default:
				i = new Intent(getApplicationContext(), MainMenuActivity.class);
				startActivity(i);
				break;
		}
		
	}
	protected void loadSuspendedActivity(Bundle b){
		type = prefs.getInt(GAME_TYPE_KEY);
		if(b != null && b.containsKey(Globals.GAME_TYPE_KEY)){
			type = b.getInt(Globals.GAME_TYPE_KEY);
			System.out.println("LOADING BUNDLE.");
		}
		
		this.go();
	}
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		this.prefs.put(Globals.GAME_TYPE_KEY, type);
	}
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState){
		super.onRestoreInstanceState(savedInstanceState);
		this.loadSuspendedActivity(this.getIntent().getExtras());
	}
	@Override
	protected void onRestart (){
		super.onRestart();
		this.loadSuspendedActivity(this.getIntent().getExtras());
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Preferences.create(this);
		this.prefs = new Preferences();
		
		this.loadSuspendedActivity(this.getIntent().getExtras());
			
	}




}
