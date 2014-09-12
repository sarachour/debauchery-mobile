package com.debauchery;


import com.debauchery.db.PersistantStateDatabase;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {
	PersistantStateDatabase db;
	protected boolean loadSuspendedActivity(){
		db.loadPrefs(this);
		int phase = db.getPhase();
		System.out.println("PHASE:"+phase);
		Intent i;
		switch(phase){
			case Globals.DRAW_PHASE:
				i = new Intent(getApplicationContext(), DrawActivity.class);
				startActivity(i);
				break;
			case Globals.DESCRIBE_PHASE:
				i = new Intent(getApplicationContext(), DescribeActivity.class);
				startActivity(i);
				break;
			case Globals.SHOW_PHASE:
				i = new Intent(getApplicationContext(), ShowActivity.class);
				startActivity(i);
				break;
			case Globals.PROMPT_PHASE:
				i = new Intent(getApplicationContext(), PromptActivity.class);
				startActivity(i);
				break;
			case Globals.REVIEW_PHASE:
				i = new Intent(getApplicationContext(), ReviewActivity.class);
				startActivity(i);
				break;
			default:
				return false;
		}
		return true;
	}
	protected void createMainActivity(){
		setContentView(R.layout.activity_main);
		
		final Button local = (Button) findViewById(R.id.main_local);
		
		local.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), LocalGameActivity.class);
				startActivity(i);
			}
		});
		
		final Button join = (Button) findViewById(R.id.main_join);
		join.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), DrawActivity.class);
				startActivity(i);
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
		System.out.println("MAIN CREATE");
		db = new PersistantStateDatabase(this);
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
