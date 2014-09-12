package com.debauchery;

import java.io.File;

import com.debauchery.data.CardStack;
import com.debauchery.db.PersistantStateDatabase;
import com.debauchery.gesture.OnSwipeTouchListener;
import com.debauchery.gesture.TwoPanelFactory;
import com.debauchery.sketch.SketchPad;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.ViewSwitcher;

public class DescribeActivity extends ActionBarActivity {
	PersistantStateDatabase db;
	String description="";
	public void saveDescriptionToDatabase(){
		db.saveDescription(description);
	}
	public void loadDescriptionFromDatabase(){
		final EditText edit =  (EditText) findViewById(R.id.sa_describe);
		description = db.getDescription();
		edit.setText(description);
	}
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
		Intent i;
	    switch (item.getItemId()) {
	        case R.id.action_next:
	        	if(description.length() == 0){
					new AlertDialog.Builder(this)
				    .setTitle("No Text Found")
				    .setMessage("Please describe the drawing.")
				    .setIcon(android.R.drawable.ic_dialog_alert)
				     .show();
					return false;
				}
	        	this.saveDescriptionToDatabase();
	        	i = new Intent(getApplicationContext(), PromptActivity.class);
				db.init(db.getTurn()+1, Globals.PROMPT_PHASE);
				startActivity(i);
	            return true;
	        case R.id.action_prev:
	        	if(db.getTurn() > 0){
	        		this.saveDescriptionToDatabase();
					i = new Intent(getApplicationContext(), ShowActivity.class);
					db.init(db.getTurn(), Globals.SHOW_PHASE);
					startActivity(i);
	        	}
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState){
		super.onRestoreInstanceState(savedInstanceState);
		this.loadDescriptionFromDatabase();
	}
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		
		super.onSaveInstanceState(savedInstanceState);
		this.saveDescriptionToDatabase();
	}
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.slide_act_describe);
		
		
		final EditText edit =  (EditText) findViewById(R.id.sa_describe);
		
		db = new PersistantStateDatabase(this);
		db.init(db.getTurn(), Globals.DESCRIBE_PHASE);
		
		if(db.getTurn() == 0){
			edit.setHint("Please describe what you want the next person to draw.");
		}
		edit.setOnEditorActionListener(new OnEditorActionListener(){

			@Override
			public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
				// TODO Auto-generated method stub
				description = arg0.getText().toString();
				return false;
			}
			
		});
	}
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.action, menu);
	    return  super.onCreateOptionsMenu(menu);
	}
}
