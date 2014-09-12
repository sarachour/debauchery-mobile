package com.debauchery;

import java.io.File;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.debauchery.data.CardStack;
import com.debauchery.db.PersistantStateDatabase;
import com.debauchery.sketch.SketchPad;

public class ShowActivity  extends ActionBarActivity {
	CardStack cards;
	PersistantStateDatabase db;
	protected void onCreate(Bundle savedInstanceState) {
		Intent i = getIntent();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.slide_view_show);
		
		db = new PersistantStateDatabase(this);
		db.init(db.getTurn(), Globals.SHOW_PHASE);
		
		final SketchPad img =  (SketchPad) findViewById(R.id.sv_sketchpad);
		img.lock();
		img.playback(db.getLastSketch(), 200);
		
		
	}
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
		Intent i;
	    switch (item.getItemId()) {
	        case R.id.show_next:
				i = new Intent(getApplicationContext(), DescribeActivity.class);
				db.init(db.getTurn(), Globals.DESCRIBE_PHASE);
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
