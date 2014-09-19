package com.debauchery.state;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {
	static protected SharedPreferences GLBL_PREFS;
	public static void create(Context ctx){
		Preferences.GLBL_PREFS = ctx.getSharedPreferences("game_state", Context.MODE_PRIVATE);
	}
	public Preferences(){
	}
	public void put(String key, int value){
		GLBL_PREFS.edit().putInt(key, value).commit();
	}
	public void put(String key, boolean value){
		GLBL_PREFS.edit().putBoolean(key, value).commit();
	}
	public boolean getBoolean(String key){
		return GLBL_PREFS.getBoolean(key, false);
	}
	public int getInt(String key){
		return GLBL_PREFS.getInt(key, -1);
	}
}
