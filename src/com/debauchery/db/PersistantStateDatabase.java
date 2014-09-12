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
		this.loadPrefs(c);
		
	}
	public void loadPrefs(Activity c){
		pref = c.getSharedPreferences("game_state",Context.MODE_PRIVATE);
		turn = pref.getInt(STATE_TURN_INDEX, -1);
		phase = pref.getInt(STATE_PHASE_INDEX, -1);
		System.out.println("loaded: "+turn+","+phase);
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
		System.out.println("set:"+turn+","+phase);
		wpref.putInt(STATE_TURN_INDEX, turn).commit();
		wpref.putInt(STATE_PHASE_INDEX, phase).commit();
		if(turn == 0){
			if(phase == Globals.DRAW_PHASE)
				wpref.putBoolean(STATE_START_WITH_DRAW_INDEX, true).commit();
			else
				wpref.putBoolean(STATE_START_WITH_DRAW_INDEX, false).commit();
		}
	}
	
	
}
