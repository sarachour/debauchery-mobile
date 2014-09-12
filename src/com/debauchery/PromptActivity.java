package com.debauchery;

import com.debauchery.data.CardStack;
import com.debauchery.db.PersistantStateDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class PromptActivity extends ActionBarActivity {
	CardStack cards;
	PersistantStateDatabase db;
	protected void onCreate(Bundle savedInstanceState) {
		Intent i = getIntent();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.slide_view_prompt);
		final TextView prompt =  (TextView) findViewById(R.id.sv_prompt);
		
		db = new PersistantStateDatabase(this);
		db.init(db.getTurn(), Globals.SHOW_PHASE);
		
		prompt.setText(db.getLastDescription());
	}
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState){
		super.onRestoreInstanceState(savedInstanceState);
		System.out.println("loading state");
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
		Intent i;
	    switch (item.getItemId()) {
	        case R.id.show_next:
				i = new Intent(getApplicationContext(), DrawActivity.class);
				db.init(db.getTurn(), Globals.DRAW_PHASE);
				startActivity(i);
				
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.show, menu);
	    return  super.onCreateOptionsMenu(menu);
	}
}
