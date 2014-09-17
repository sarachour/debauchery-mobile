package com.debauchery.fragment.iface;

import com.debauchery.db.PersistantStateDatabase;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class FragmentInterface extends Fragment {
	protected PersistantStateDatabase db;
	private int VIEW_ID;
	public FragmentInterface(int viewid){
		db = PersistantStateDatabase.DB;
		VIEW_ID = viewid;
	}
	public abstract void create();
	public abstract void save();
	public abstract void load();
	
	public View find(int id){
		return this.getView().findViewById(id);
	}
	public void onStart(){
		super.onStart();
		this.create();
		this.load();
		
	}
	public void onResume(){
		super.onResume();
		
	}
	//@Override
	public void onPause(){
		super.onPause();
		this.save();
	}
	//@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater,container,savedInstanceState);
		
		ViewGroup rv = (ViewGroup) inflater.inflate(VIEW_ID, container, false);
		
		
		return rv;

	}
	/*
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.action, menu);
	    return  super.onCreateOptionsMenu(menu);
	}
	*/
}