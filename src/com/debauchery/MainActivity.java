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
	Preferences prefs;
	MainActivity that;
	private boolean go(int gtype){
		Intent i;
		switch(gtype){
			case LOCAL_GAME:
				prefs.put(GAME_TYPE_KEY, LOCAL_GAME);
				i = new Intent(getApplicationContext(), LocalGameActivity.class);
				startActivity(i);
				break;
			default:
				prefs.put(GAME_TYPE_KEY, -1);
				return false;
		}
		return true;
	}
	protected boolean loadSuspendedActivity(){
		int gtype = prefs.getInt(GAME_TYPE_KEY);
		return this.go(gtype);
	}
	protected void createMainActivity(){
		setContentView(R.layout.activity_main);
		
		final Button local = (Button) findViewById(R.id.main_local);
		
		local.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				that.go(LOCAL_GAME);
			}
		});
		
	}
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState){
		System.out.println("MAIN RESTORE");
		super.onRestoreInstanceState(savedInstanceState);
	}
	@Override
	protected void onRestart (){
		super.onRestart();
		System.out.println("MAIN RESTART");
		if(!this.loadSuspendedActivity()){
			this.createMainActivity();
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.that = this;
		Preferences.create(this);
		this.prefs = new Preferences();
		if(!this.loadSuspendedActivity()){
			this.createMainActivity();
		}
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


}
