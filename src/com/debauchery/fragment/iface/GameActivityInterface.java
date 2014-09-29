package com.debauchery.fragment.iface;

import android.support.v4.app.FragmentManager;

public interface GameActivityInterface {
	public void exit(); //exit game
	public void prev();
	public void next();
	public void review(); // go to review slide
	public void game(int nplayers, boolean startWithDraw);
	public FragmentManager mgr();
	public int gameContainerId();
	public void change(int type, int turn);
	
}
