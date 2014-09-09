package com.debauchery;

import java.io.File;
import java.io.FileOutputStream;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Globals {
	public final static int DRAW_PHASE = 0;
	public final static int DESCRIBE_PHASE = 1;
	public final static int REVIEW_PHASE = 2;
	public final static int PROMPT_PHASE = 3;
	public final static int SHOW_PHASE = 4;
	public final static String STATE_TURN_INDEX = "tidx";
	public final static String  STATE_ACTION_INDEX = "sidx";
	
	public final static String STATE_CARD = "card";
	public final static String STATE_PHASE = "phase";
	
}
