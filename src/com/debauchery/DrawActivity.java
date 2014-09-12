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
		
		final Button done = (Button) findViewById(R.id.sa_sketch_done);
		sketchpad =  (SketchPad) findViewById(R.id.fd_sketchpad);
		
		loadSketchFromDatabase();
		
		done.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				if(sketchpad.getData().size() == 0){
					new AlertDialog.Builder(v.getContext())
				    .setTitle("No Drawing Found")
				    .setMessage("Please draw something.")
				    .setIcon(android.R.drawable.ic_dialog_alert)
				     .show();
					return;
				}
				saveSketchToDatabase();
				Intent i = new Intent(getApplicationContext(), ShowActivity.class);
				db.init(db.getTurn()+1, Globals.SHOW_PHASE);
				startActivity(i);
				
			}
			
		});
		
	}
}
