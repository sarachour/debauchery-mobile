package com.debauchery;

import java.io.File;
import java.io.FileOutputStream;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Button;

import com.debauchery.R;

public class Globals {
	public static final String GAME_TYPE_KEY = "GAME_KEY";
	public static final int LOCAL_GAME=0; 
	public static final int MAIN_MENU=1;
	public static void disableButton(Context c, Button b){
		b.setBackground(c.getResources().getDrawable(R.drawable.rounded_corners_black));
		b.setTextColor(c.getResources().getColor(R.color.grey));
		
	}
	
}
