package com.debauchery;

import java.io.File;
import java.io.FileOutputStream;

import com.debauchery.data.CardStack;
import com.debauchery.gesture.OnSwipeTouchListener;
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
		ImageButton sel_prompt = (ImageButton) findViewById(R.id.cv_sel_prompt);
		View prompt_view = (View) findViewById(R.id.cv_prompt_container);
		ImageButton sel_draw = (ImageButton) findViewById(R.id.cv_sel_draw);
		View canv_view = (View) findViewById(R.id.cv_sketch_container);
		ViewSwitcher switcher = (ViewSwitcher) findViewById(R.id.cv_switcher);
	
		final TextView prompt =  (TextView) findViewById(R.id.cv_prompt);
		final Button done = (Button) findViewById(R.id.cv_done);
		
		
		  
		sel_draw.setOnClickListener(new OnClickListener(){
			ViewSwitcher switcher = (ViewSwitcher) findViewById(R.id.cv_switcher);
			Animation slide_in_right = AnimationUtils.loadAnimation(that,
				    R.anim.slide_in_right);
			Animation slide_out_left = AnimationUtils.loadAnimation(that,
			    R.anim.slide_out_left);
				  
			@Override
			//in right out left
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(switcher.getNextView().getId() == R.id.cv_sketch_container){
					switcher.setInAnimation(slide_in_right);
					switcher.setOutAnimation(slide_out_left);
					switcher.showNext();
				}
				
				
			}
			
		});
		sel_prompt.setOnClickListener(new OnClickListener(){
			ViewSwitcher switcher = (ViewSwitcher) findViewById(R.id.cv_switcher);
			Animation slide_in_left = AnimationUtils.loadAnimation(that,
				    android.R.anim.slide_in_left);
			Animation slide_out_right = AnimationUtils.loadAnimation(that,
			    android.R.anim.slide_out_right);
		
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//in left, out right
				if(switcher.getNextView().getId() == R.id.cv_prompt_container){
					switcher.setInAnimation(slide_in_left);
					switcher.setOutAnimation(slide_out_right);
					switcher.showNext();
				}
					
			}
			
		});
		prompt_view.setOnTouchListener(new OnSwipeTouchListener(this) {
			ViewSwitcher switcher = (ViewSwitcher) findViewById(R.id.cv_switcher);
			Animation slide_in_right = AnimationUtils.loadAnimation(that,
				    R.anim.slide_in_right);
			Animation slide_out_left = AnimationUtils.loadAnimation(that,
			    R.anim.slide_out_left);
		
		    public void onSwipeRight() {
		       
		    }
		    public void onSwipeLeft() {
		    	switcher.setInAnimation(slide_in_right);
				switcher.setOutAnimation(slide_out_left);
		    	switcher.showNext();
		    }

			@Override
			public void onSwipeTop() {}
			@Override
			public void onSwipeBottom() {}
		});
		
		canv_view.setOnTouchListener(new OnSwipeTouchListener(this) {
			ViewSwitcher switcher = (ViewSwitcher) findViewById(R.id.cv_switcher);
			Animation slide_in_left = AnimationUtils.loadAnimation(that,
				    android.R.anim.slide_in_left);
			Animation slide_out_right = AnimationUtils.loadAnimation(that,
			    android.R.anim.slide_out_right);
		
		    public void onSwipeRight() {
		       
		    }
		    public void onSwipeLeft() {
		    	switcher.setInAnimation(slide_in_left);
				switcher.setOutAnimation(slide_out_right);
		    	switcher.showNext();
		    }

			@Override
			public void onSwipeTop() {}
			@Override
			public void onSwipeBottom() {}
		});
		
		
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
	}
}
