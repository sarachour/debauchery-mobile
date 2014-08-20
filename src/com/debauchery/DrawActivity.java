package com.debauchery;

import java.io.File;
import java.io.FileOutputStream;

import com.debauchery.data.CardStack;
import com.debauchery.gesture.OnSwipeTouchListener;
import com.debauchery.gesture.TwoPanelFactory;
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
	DrawActivity that;

	protected void onCreate(Bundle savedInstanceState) {
		Intent i = getIntent();
		String promptText = i.getStringExtra("prompt");
		cards = (CardStack) i.getParcelableExtra("stack");
		that = this;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sketch);
		
		final TextView prompt =  (TextView) findViewById(R.id.cv_prompt);
		final Button done = (Button) findViewById(R.id.cv_done);
		
		
		  
		//update text
		prompt.setText(promptText);
		
	
		//update done
		done.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				//update
				SketchPad canv =  (SketchPad) findViewById(R.id.fd_sketchpad);
				
				
				if(canv.getWidth() <= 0 || canv.getHeight() <= 0){
					new AlertDialog.Builder(v.getContext())
				    .setTitle("No Drawing Found")
				    .setMessage("Please draw something.")
				    .setIcon(android.R.drawable.ic_dialog_alert)
				     .show();
					return;
				}
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
			}
			
		});
		
		TwoPanelFactory.setupButtons(this, R.id.cv_switcher, R.id.cv_sel_prompt, 
										   R.id.cv_prompt_container, R.id.cv_sel_draw, R.id.cv_sketch_container);
	}
}
