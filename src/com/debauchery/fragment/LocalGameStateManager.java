package com.debauchery.fragment;

import com.debauchery.R;
import com.debauchery.fragment.LocalSettingsFragment.SettingsFinishedListener;
import com.debauchery.fragment.iface.FragmentInterface;
import com.debauchery.fragment.iface.FragmentTurnInterface;
import com.debauchery.state.Databases;
import com.debauchery.state.Preferences;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class LocalGameStateManager {
	int nstates;
	int nplayers;
	boolean startWithDrawing=false;
	Preferences prefs;
	int idx;
	
	public static interface GameStateChangedListener {
		public void onChange(int turn);
	}
	public static class LocalGameSettings {
		LocalSettingsFragment ls;
		public LocalGameSettings(FragmentManager fm, int parent_id, SettingsFinishedListener sel) {
			ls =  new LocalSettingsFragment(sel);
			fm.beginTransaction().replace(parent_id,ls).commit();	
			// TODO Auto-generated constructor stub
		}
		public void load(){
			ls.load();
		}
		public void save(){
			
		}
	}
	final int DRAW=0;
	final int SHOW=1;
	final int DESCRIBE=2;
	final int PROMPT=3;
	final int PASS=4;
	final int DONE=5;
	final String INDEX_KEY = "LOCALGAMESTATE_INDEX";
	final String SDRAW_KEY = "LOCALGAMESTATE_SDRAW";
	final String NPLAYERS_KEY = "LOCALGAMESTATE_NPLAYERS";
	
	FragmentInterface currentFragment = null;
	FragmentManager sp;
	GameStateChangedListener listener = null;
	int parent_id;
	int curr_turn=-1;
	int curr_state=-1;
	public LocalGameStateManager(FragmentManager supportFragmentManager, int sp_id) {
		sp = supportFragmentManager;
		this.parent_id = sp_id;
		this.prefs = new Preferences();
	}
	public void init(int nplayers, boolean startWithDrawing){
		this.nplayers = nplayers;
		this.startWithDrawing = startWithDrawing;
		this.nstates = nplayers*2 + nplayers;
		System.out.println("states:"+this.nstates+",players:"+this.nplayers);
		idx = 0;
		this.set();
	}
	public void setListener(GameStateChangedListener l){
		this.listener = l;
	}
	public void save(){
		if(currentFragment != null) currentFragment.save();
		//save state
		this.prefs.put(INDEX_KEY, idx);
		this.prefs.put(SDRAW_KEY, startWithDrawing);
		this.prefs.put(NPLAYERS_KEY, nplayers);
	}
	public void load(){
		//load index, nplayers.
		idx = this.prefs.getInt(INDEX_KEY);
		nplayers = this.prefs.getInt(NPLAYERS_KEY);
		startWithDrawing = this.prefs.getBoolean(SDRAW_KEY);
		this.set();
	}
	private int getTurn(int i){
		if(i < 0){
			return 0;
		}
		if(i < nplayers*2){
			return i/2;
		}
		//review phase
		i-=nplayers*2;
		if(i < nplayers){
			return i;
		}
		return -1;
	}
	private int getState(int i){
		int st = i;
		//the first card
		//past the review cards
		if(i >= nplayers*2+nplayers){
			return DONE;
		}
		else if(i < 0){
			i=0;
		}
		
		boolean onView;
		if(i < nplayers*2){
			if(i%2==0) onView = false;
			else onView = true;
		}
		else{
			onView = true;
		}
		
		int turn = getTurn(st);
		if((startWithDrawing && turn%2==0) || (!startWithDrawing && turn%2==1)){
			if(onView) return SHOW;
			else return DRAW;
		}
		else{
			if(onView) return PROMPT;
			else return DESCRIBE;
		}
			
			
	}
	public Fragment getItem(int i, int t) {
		System.out.println("getting item.");
		
		switch(i){
			case DRAW:
				currentFragment= new DrawFragment(t); return currentFragment;
			case DESCRIBE:
				currentFragment=  new DescribeFragment(t); return currentFragment;
			case PROMPT:
				currentFragment= new PromptFragment(t); return currentFragment;
			case SHOW:
				currentFragment=  new ShowFragment(t); return currentFragment;
			case PASS:
				currentFragment=  new PassOverFragment(t); return currentFragment;
			case DONE:
				currentFragment=  new DoneFragment(t); return currentFragment;
			default:
				return null;
		}
		
	}
	
	public int index(){
		return idx;
	}
	public void prev(){
		if(idx >= 0) idx--;
		System.out.println("count:"+idx);
		this.set();
	}
	private void set(){
		final int viewstate = this.getState(idx);
		final int turn = this.getTurn(idx);
		if(viewstate == curr_state && turn == curr_turn)
			return;
		curr_state = viewstate;
		curr_turn = turn;
		if(currentFragment != null)
			currentFragment.save();
		sp.beginTransaction().replace(parent_id, this.getItem(viewstate, turn)).commit();	
		if(this.listener != null) this.listener.onChange(turn);
		System.out.println("idx:"+idx+" turn:"+turn+" view:"+viewstate);
		
	}
	public void next(){
		//save current
		if(idx < nstates) idx++;//increment index
		this.set();
		
	}
	
}