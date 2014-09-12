package com.debauchery.db;

import com.debauchery.Globals;
import com.debauchery.sketch.SketchPadData;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class PersistantStateDatabase {
	public final static String STATE_TURN_INDEX = "turn";
	public final static String  STATE_PHASE_INDEX = "phase";
	public final static String  STATE_START_WITH_DRAW_INDEX = "turn0draw";
	
	int turn; //current turn
	int phase; //current phase
	boolean startedWithDrawing;
	SketchDatabase sketchDB; //sketches
	DescriptionDatabase descDB; //descriptions
	SharedPreferences pref;
	public PersistantStateDatabase(Activity c){
		sketchDB = new SketchDatabase(c);
		descDB = new DescriptionDatabase(c);
		pref = c.getPreferences(Activity.MODE_PRIVATE);
		if(pref.contains(STATE_TURN_INDEX)) turn = pref.getInt(STATE_TURN_INDEX, 0);
		else turn = -1;
		if(pref.contains(STATE_PHASE_INDEX)) phase = pref.getInt(STATE_PHASE_INDEX, Globals.DESCRIBE_PHASE);
		else phase = -1;
		
	}
	public void saveSketch(SketchPadData sk){
		sketchDB.save(sk, turn);
	}
	public SketchPadData getSketch(){
		return sketchDB.get(turn);
	}
	public SketchPadData getLastSketch(){
		return sketchDB.get(turn-1);
	}
	public void saveDescription(String s){
		descDB.save(s, turn);
	}
	public String getDescription(String s){
		return descDB.get(turn);
	}
	public String getLastDescription(){
		return descDB.get(turn-1);
	}
	public int getTurn(){
		return turn;
	}
	public int getPhase(){
		return phase;
	}
	public void init(int turn, int phase){
		SharedPreferences.Editor wpref = pref.edit();
		wpref.putInt(STATE_TURN_INDEX, turn);
		wpref.putInt(STATE_PHASE_INDEX, phase);
		if(turn == 0){
			if(phase == Globals.DRAW_PHASE)
				wpref.putBoolean(STATE_START_WITH_DRAW_INDEX, true);
			else
				wpref.putBoolean(STATE_START_WITH_DRAW_INDEX, false);
			wpref.commit();
		}
	}
	public void startWithDraw(boolean b) {
		// TODO Auto-generated method stub
		SharedPreferences.Editor wpref = pref.edit();
		wpref.putBoolean(STATE_START_WITH_DRAW_INDEX, b);
		wpref.commit();
		
	}
	
}
