package com.debauchery.fragment;

import com.debauchery.R;
import com.debauchery.fragment.iface.FragmentInterface;
import com.debauchery.fragment.iface.FragmentTurnInterface;
import com.debauchery.fragment.iface.GameActivityInterface;
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
	int nstates_start=2;
	int nstates_game;
	int nstates_end=1;
	int nstates_review;
	int nstates_done=1;
	
	int nplayers;
	boolean startWithDrawing=false;
	Preferences prefs;
	int idx;
	
	final int DRAW=0;
	final int SHOW=1;
	final int DESCRIBE=2;
	final int PROMPT=3;
	final int PASS=4;
	final int DONE=5;
	final int SETTINGS=6;
	final int START=7;
	final int END=8;
	final String INDEX_KEY = "LOCALGAMESTATE_INDEX";
	final String SDRAW_KEY = "LOCALGAMESTATE_SDRAW";
	final String NPLAYERS_KEY = "LOCALGAMESTATE_NPLAYERS";
	
	FragmentInterface current = null; //current fragment
	GameActivityInterface parent;
	

	
	int curr_turn=-1;
	int curr_state=-1;
	
	public LocalGameStateManager(GameActivityInterface g) {
		this.prefs = new Preferences();
		parent = g;
	}
	public void init(int nplayers, boolean startWithDrawing){
		this.nplayers = nplayers;
		this.startWithDrawing = startWithDrawing;
		//settings -> start -> play slides -> end -> review -> done
		this.nstates_game = (nplayers*3-2);
		this.nstates_review = nplayers;
		this.nstates = this.nstates_start 
						+ this.nstates_review 
						+ this.nstates_game 
						+ this.nstates_end
						+ this.nstates_done;
		
		System.out.println("states:"+this.nstates+",players:"+this.nplayers);
		idx = 0;
		this.set();
	}
	public void save(){
		if(current != null) current.save();
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
		if(i < this.nstates_start){
			return 0;
		}
		i-=this.nstates_start;
		if(i < this.nstates_game){
			return i/3;
		}
		//remove by states following game.
		i -= this.nstates_end;
		
		//review phase
		i-=this.nstates_game;
		if(i < nplayers){
			return i;
		}
		return 0;
	}
	private int getState(int i){
		int st = i;
		//the first card
		//past the review cards
		if(i <= 0){
			return SETTINGS;
		}
		else if(i==1)
			return START;
		
		i-= this.nstates_start;
		
		int turn = getTurn(st);
		boolean isOnPic =((startWithDrawing && turn%2==0) || (!startWithDrawing && turn%2==1));
		
		if(i < this.nstates_game){
			if(i%3==0) { //draw mode
				if(isOnPic) return DRAW;
				return DESCRIBE;
			}
			else if(i%3 == 1)
				return PASS;
			else if(i%3==2) { //view mode
				if(isOnPic) return SHOW;
				return PROMPT;
			}
			
		}
		i-= this.nstates_game;
		if(i == 0)
			return END;
		
		i--;
		if(i < this.nstates_review){
			if(isOnPic) return SHOW;
			return PROMPT;
		}
		i -= this.nstates_review;
		return DONE;
		
			
			
	}
	public Fragment getItem(int i, int t) {
		System.out.println("getting item.");
		
		switch(i){
			case DRAW:
				current= new DrawFragment(parent,t); return current;
			case DESCRIBE:
				current=  new DescribeFragment(parent,t); return current;
			case PROMPT:
				current= new PromptFragment(parent, t); return current;
			case SHOW:
				current=  new ShowFragment(parent,t); return current;
			case PASS:
				current=  new InstructionFragment(parent,
						R.drawable.green_bg_repeat,
						"Pass Phone to", 
						R.drawable.phone_orange,
						"Next Person"); return current;
			case DONE:
				current=  new DoneFragment(parent,t); return current;
			case SETTINGS:
				current = new LocalSettingsFragment(parent); return current;
			case START:
				if(this.startWithDrawing)
					current=  new InstructionFragment(parent,
							R.drawable.blue_bg_repeat,
							"Draw the Starting", 
							R.drawable.phone_draw,
							"Topic");
				else
					current=  new InstructionFragment(parent,
							R.drawable.blue_bg_repeat,
							"Describe the Starting", 
							R.drawable.phone_describe,
							"Topic"); 
				
				return current;
			case END:
				current=  new InstructionFragment(parent,
						R.drawable.violet_bg_repeat,
						"Round Finished!", 
						R.drawable.phone_royal,
						"Let's Review"); return current;
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
		if(current != null)
			current.save();
		System.out.println(idx+","+curr_state+","+curr_turn);
		parent.mgr().beginTransaction().replace(
				parent.gameContainerId(), this.getItem(viewstate, turn)).commit();	
		parent.change(viewstate, turn);
		
	}
	public void next(){
		//save current
		if(idx < nstates) idx++;//increment index
		this.set();
		
	}
	public void review() {
		// TODO Auto-generated method stub
		idx = this.nstates_start 
				+ this.nstates_game;
		this.set();
	}
	
}