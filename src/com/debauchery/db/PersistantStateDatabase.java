package com.debauchery.db;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class PersistantStateDatabase {
	int turn; //current turn
	int phase; //current phase
	SketchDatabase sketchDB; //sketches
	DescriptionDatabase descDB; //descriptions
	TurnDatabase turnDB;
	SharedPreferences rpref;
	SharedPreferences.Editor wpref;
	public PersistantStateDatabase(Activity c){
		sketchDB = new SketchDatabase(c);
		descDB = new DescriptionDatabase(c);
		turnDB = new TurnDatabase(c);
		
	}
	
	
}
