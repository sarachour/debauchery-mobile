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

public class LocalGameStateMachine extends FragmentStatePagerAdapter {
	int nstates;
	int nplayers;
	boolean startWithDrawing=false;
	FragmentInterface currentFragment = null;
	PersistantStateDatabase db;
	int idx;
	
	public static class LocalGameSettingsAdapter extends FragmentStatePagerAdapter{
		SettingsFinishedListener listener;
		public LocalGameSettingsAdapter(FragmentManager fm, SettingsFinishedListener sel) {
			super(fm);
			this.listener = sel;
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			return new LocalSettingsFragment(listener);
			
		}

		@Override
		public int getCount() {
			return 1;
		}
		
	}
	final int DRAW=0;
	final int DESCRIBE=2;
	final int PROMPT=3;
	final int SHOW=1;
	DrawFragment drawFragment;
	DescribeFragment describeFragment;
	PromptFragment promptFragment;
	ShowFragment showFragment;
	FragmentTurnInterface current;
	
	public LocalGameStateMachine(FragmentManager supportFragmentManager) {
		super(supportFragmentManager);
		
	}
	public void init(ViewPager p, int nplayers, boolean startWithDrawing){
		this.nplayers = nplayers;
		this.startWithDrawing = startWithDrawing;
		this.nstates = nplayers*2 + nplayers;
		System.out.println("states:"+this.nstates+",players:"+this.nplayers);
		idx = 0;
		this.set(p);
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
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 4;
	}
	@Override
	public Fragment getItem(int i) {
		System.out.println("getting item.");
		
		switch(i){
			case DRAW:
				drawFragment= new DrawFragment(0); return drawFragment;
			case DESCRIBE:
				describeFragment=  new DescribeFragment(0); return describeFragment;
			case PROMPT:
				promptFragment=  new PromptFragment(0); return promptFragment;
			case SHOW:
				showFragment=  new ShowFragment(0); return showFragment;
			default:
				return null;
		}
		
	}
	public int index(){
		return idx;
	}
	public void prev(ViewPager pager){
		((FragmentInterface) this.getItem(idx)).save();

		idx--;
		System.out.println("count:"+idx);
		this.set(pager);
	}
	private void set(final ViewPager pager){
		final int viewstate = this.getState(idx);
		final int turn = this.getTurn(idx);
		
		pager.setCurrentItem(viewstate);
		
		System.out.println("idx:"+idx+" turn:"+turn+" view:"+viewstate);
		
	}
	public void next(ViewPager pager){
		//save current
		idx++;//increment index
		this.set(pager);
		
	}
	
}