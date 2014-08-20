package com.debauchery.gesture;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ViewSwitcher;

import com.debauchery.R;

public class TwoPanelFactory {
	public static void setupButtons(final Activity that, final int sw, int sel_a_id, final int a_id, int sel_b_id, final int b_id){
		final ImageButton sel_a = (ImageButton) that.findViewById(sel_a_id);
		final View a = (View) that.findViewById(a_id);
		final ImageButton sel_b = (ImageButton) that.findViewById(sel_b_id);
		final View b = (View) that.findViewById(b_id);
		ViewSwitcher swi = (ViewSwitcher) that.findViewById(sw);
	
		sel_a.setOnClickListener(new OnClickListener(){
			ViewSwitcher switcher = (ViewSwitcher) that.findViewById(sw);
			Animation slide_in_right = AnimationUtils.loadAnimation(that,
				    R.anim.slide_in_right);
			Animation slide_out_left = AnimationUtils.loadAnimation(that,
			    R.anim.slide_out_left);
			int id_a = a_id;
			@Override
			//in right out left
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(switcher.getNextView().getId() == id_a){
					switcher.setInAnimation(slide_in_right);
					switcher.setOutAnimation(slide_out_left);
					switcher.showNext();
				}
				
				
			}
			
		});
		sel_b.setOnClickListener(new OnClickListener(){
			ViewSwitcher switcher = (ViewSwitcher) that.findViewById(sw);
			Animation slide_in_left = AnimationUtils.loadAnimation(that,
				    android.R.anim.slide_in_left);
			Animation slide_out_right = AnimationUtils.loadAnimation(that,
			    android.R.anim.slide_out_right);
			int id_b = b_id;
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//in left, out right
				if(switcher.getNextView().getId() == id_b){
					switcher.setInAnimation(slide_in_left);
					switcher.setOutAnimation(slide_out_right);
					switcher.showNext();
				}
					
			}
			
		});
		a.setOnTouchListener(new OnSwipeTouchListener(that) {
			ViewSwitcher switcher = (ViewSwitcher) that.findViewById(sw);
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
		
		b.setOnTouchListener(new OnSwipeTouchListener(that) {
			ViewSwitcher switcher = (ViewSwitcher) that.findViewById(sw);
			Animation slide_in_left = AnimationUtils.loadAnimation(that,
				    android.R.anim.slide_in_left);
			Animation slide_out_right = AnimationUtils.loadAnimation(that,
			    android.R.anim.slide_out_right);
		
		    public void onSwipeLeft() {
		       
		    }
		    public void onSwipeRight() {
		    	switcher.setInAnimation(slide_in_left);
				switcher.setOutAnimation(slide_out_right);
		    	switcher.showNext();
		    }

			@Override
			public void onSwipeTop() {}
			@Override
			public void onSwipeBottom() {}
		});
	}
}
