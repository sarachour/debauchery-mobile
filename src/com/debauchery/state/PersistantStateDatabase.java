package com.debauchery.state;

import com.debauchery.Globals;
import com.debauchery.sketch.SketchPadData;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;

public class PersistantStateDatabase {
	
	boolean startedWithDrawing;
	SketchDatabase sketchDB; //sketches
	DescriptionDatabase descDB; //descriptions
	public static PersistantStateDatabase DB;
	public PersistantStateDatabase(Activity c){
		sketchDB = new SketchDatabase(c);
		descDB = new DescriptionDatabase(c);
		DB = this;
	}
	public static void create(Activity c){
		DB = new PersistantStateDatabase(c);
	}
	public void saveSketch(int turn, SketchPadData sk){
		sketchDB.save(sk, turn);
	}
	public SketchPadData getSketch(int turn){
		return sketchDB.get(turn);
	}
	public SketchPadData getLastSketch(int turn){
		return sketchDB.get(turn-1);
	}
	public void saveDescription(int turn, String s){
		descDB.save(s, turn);
	}
	public String getDescription(int turn){
		return descDB.get(turn);
	}
	public String getLastDescription(int turn){
		return descDB.get(turn-1);
	}
	public void clear(){
		sketchDB.clear();
		descDB.clear();
	}
	public void init(){
	}
	
	
}
