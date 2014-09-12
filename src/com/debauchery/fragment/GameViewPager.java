package com.debauchery.fragment;

import com.debauchery.sketch.SketchPadData;

import android.content.Context;
import android.os.Parcel;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class GameViewPager extends ViewPager {
	
	public GameViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameViewPager(Context context, AttributeSet attrs, Parcel p){
    	super(context, attrs);
    }
    
	public GameViewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        // Never allow swiping to switch between pages
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Never allow swiping to switch between pages
        return false;
    }

}
