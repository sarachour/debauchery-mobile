package com.debauchery;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import com.debauchery.data.CardStack;
import com.debauchery.gesture.OnSwipeTouchListener;
import com.debauchery.gesture.TwoPanelFactory;
import com.debauchery.sketch.Action;
import com.debauchery.sketch.SketchPad;

import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
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
	protected void onCreate(Bundle savedInstanceState) {
		Intent i = getIntent();
		cards = (CardStack) i.getParcelableExtra("stack");

		super.onCreate(savedInstanceState);
		setContentView(R.layout.slide_act_sketch);
		final Button done = (Button) findViewById(R.id.sa_sketch_done);
		
	
		//update done
		done.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				//update
				SketchPad sk =  (SketchPad) findViewById(R.id.fd_sketchpad);
				List<Action> actions = sk.getActions();
				
				if(actions.size() == 0){
					new AlertDialog.Builder(v.getContext())
				    .setTitle("No Drawing Found")
				    .setMessage("Please draw something.")
				    .setIcon(android.R.drawable.ic_dialog_alert)
				     .show();
					return;
				}
				cards.addImageCard(actions);
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
