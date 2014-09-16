package com.debauchery.fragment;

import com.debauchery.R;
import com.debauchery.db.PersistantStateDatabase;
import com.debauchery.fragment.LocalSettingsFragment.SettingsFinishedListener;
import com.debauchery.fragment.iface.FragmentInterface;
import com.debauchery.fragment.iface.FragmentTurnInterface;

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

public class LocalGameStateMachine {
	int nstates;
	int nplayers;
	boolean startWithDrawing=false;
	
	PersistantStateDatabase db;
	int idx;
	
	public static class LocalGameSettings {
		public LocalGameSettings(FragmentManager fm, int parent_id, SettingsFinishedListener sel) {
			LocalSettingsFragment ls = new LocalSettingsFragment(sel);
			fm.beginTransaction().replace(parent_id,ls).commit();	
			// TODO Auto-generated constructor stub
		}
		
		
	}
	final int DRAW=0;
	final int DESCRIBE=2;
	final int PROMPT=3;
	final int SHOW=1;
	FragmentInterface currentFragment = null;
	FragmentManager sp;
	int parent_id;
	public LocalGameStateMachine(FragmentManager supportFragmentManager, int sp_id) {
		sp = supportFragmentManager;
		this.parent_id = sp_id;
		
	}
	public void init(int nplayers, boolean startWithDrawing){
		this.nplayers = nplayers;
		this.startWithDrawing = startWithDrawing;
		this.nstates = nplayers*2 + nplayers;
		System.out.println("states:"+this.nstates+",players:"+this.nplayers);
		idx = 0;
		this.set();
	}
	private int getTurn(int i){

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
			return SHOW;
		}
		
		boolean onView;
		if(i < nplayers*2){
			if(i%2==0) onView = false;
			else onView = true;
		}
		else{
			onView = false;
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
				currentFragment=  new PromptFragment(t); return currentFragment;
			case SHOW:
				currentFragment=  new ShowFragment(t); return currentFragment;
			default:
				return null;
		}
		
	}
	
	public int index(){
		return idx;
	}
	public void prev(){
		idx--;
		System.out.println("count:"+idx);
		this.set();
	}
	private void set(){
		final int viewstate = this.getState(idx);
		final int turn = this.getTurn(idx);
		
		if(currentFragment != null)
			currentFragment.save();
		sp.beginTransaction().replace(parent_id, this.getItem(viewstate, turn)).commit();	
		
		System.out.println("idx:"+idx+" turn:"+turn+" view:"+viewstate);
		
	}
	public void next(){
		//save current
		idx++;//increment index
		this.set();
		
	}
	
}