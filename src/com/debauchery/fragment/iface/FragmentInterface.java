package com.debauchery.fragment.iface;

import com.debauchery.Globals;
import com.debauchery.R;
import com.debauchery.db.PersistantStateDatabase;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar.OnSeekBarChangeListener;

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
