package com.debauchery;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import com.debauchery.data.CardStack;
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
	CardStack cards;
	SharedPreferences preferences;
	SharedPreferences.Editor prefEditor;
	String STATE_STACK = "stack";
	String STATE_SKETCH = "sketch";
	SketchPad sketchpad;
	SketchDatabase sdb;
	
	private void loadSketchFromDatabase(){
		sdb = new SketchDatabase(this);
		System.out.println("loading data");
		sketchpad.loadData(sdb.get(0));
	}
	private void saveSketchToDatabase(){
		sdb.save(sketchpad.getData(),0);
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
		Intent i = getIntent();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.slide_act_sketch);
		
		preferences = this.getPreferences(Context.MODE_PRIVATE);
		prefEditor = preferences.edit();
		prefEditor.putInt(Globals.STATE_PHASE, Globals.DRAW_PHASE);
		
		final Button done = (Button) findViewById(R.id.sa_sketch_done);
		sketchpad =  (SketchPad) findViewById(R.id.fd_sketchpad);
		
		loadSketchFromDatabase();
		
		
		//update done
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
				
				/*
				Bitmap img = canv.getImage();
				String path = Globals.saveInternal(getApplicationContext(), Globals.IMAGE_PATH, cards.getImageName(), img);
				
				cards.addImageCard(path);
				System.out.println(path);
				
				if(cards.isEnd()){
					Intent i = new Intent(getApplicationContext(), ReviewActivity.class);
					i.putExtra("stack", cards);
					startActivity(i);
				}
				else{
					Intent i = new Intent(getApplicationContext(), DescribeActivity.class);
					i.putExtra("picture", path);
					i.putExtra("stack", cards);
					startActivity(i);
				}
				*/
			}
			
		});
		
	}
}
