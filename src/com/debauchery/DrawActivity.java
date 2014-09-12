package com.debauchery;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import com.debauchery.data.CardStack;
import com.debauchery.db.PersistantStateDatabase;
import com.debauchery.db.SketchDatabase;
import com.debauchery.gesture.OnSwipeTouchListener;
import com.debauchery.gesture.TwoPanelFactory;
import com.debauchery.sketch.Action;
import com.debauchery.sketch.SketchPad;
import com.debauchery.sketch.SketchPadData;

import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v7.app.ActionBarActivity;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class DrawActivity extends ActionBarActivity {
	PersistantStateDatabase db;
	SketchPad sketchpad;
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
		Intent i;
	    switch (item.getItemId()) {
	        case R.id.action_next:
	        	if(sketchpad.getData().size() == 0){
					new AlertDialog.Builder(this)
				    .setTitle("No Drawing Found")
				    .setMessage("Please draw something.")
				    .setIcon(android.R.drawable.ic_dialog_alert)
				     .show();
					return true;
				}
	        	saveSketchToDatabase();
				i = new Intent(getApplicationContext(), ShowActivity.class);
				db.init(db.getTurn()+1, Globals.SHOW_PHASE);
				startActivity(i);
				
	            return true;
	        case R.id.action_prev:
	        	if(db.getTurn() > 0){
	        		saveSketchToDatabase();
					i = new Intent(getApplicationContext(), PromptActivity.class);
					db.init(db.getTurn(), Globals.PROMPT_PHASE);
					startActivity(i);
	        	}
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.action, menu);
	    return  super.onCreateOptionsMenu(menu);
	}
	
	private void loadSketchFromDatabase(){
		sketchpad.loadData(db.getSketch());
	}
	private void saveSketchToDatabase(){
		db.saveSketch(sketchpad.getData());
	}
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState){
		super.onRestoreInstanceState(savedInstanceState);
		loadSketchFromDatabase();
	}
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		
		super.onSaveInstanceState(savedInstanceState);
		System.out.println("Saving state..");
	    // Save the user's current game state
		saveSketchToDatabase();
	}
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.slide_act_sketch);
		
		db = new PersistantStateDatabase(this);
		db.init(db.getTurn(), Globals.DRAW_PHASE);
		
		
		sketchpad =  (SketchPad) findViewById(R.id.fd_sketchpad);
		
		loadSketchFromDatabase();
		
		
	}
}
