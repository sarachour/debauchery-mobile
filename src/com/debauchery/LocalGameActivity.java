package com.debauchery;

import com.debauchery.db.PersistantStateDatabase;
import com.debauchery.fragment.LocalGameStateMachine;
import com.debauchery.fragment.LocalGameStateMachine.LocalGameSettingsAdapter;
import com.debauchery.fragment.LocalSettingsFragment.SettingsFinishedListener;
import com.debauchery.fragment.iface.FragmentInterface;
import com.debauchery.sketch.SketchPad;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ViewFlipper;

public class LocalGameActivity extends ActionBarActivity implements SettingsFinishedListener {
	FragmentInterface virt;
	ViewPager views;
	private LocalGameSettingsAdapter settings;
	private LocalGameStateMachine game;
	PersistantStateDatabase db;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		db = new PersistantStateDatabase(this);
		setContentView(R.layout.activity_local_game);
		views = (ViewPager) this.findViewById(R.id.lg_flipper);
		settings = new LocalGameSettingsAdapter(getSupportFragmentManager(), this);
		game = new LocalGameStateMachine(getSupportFragmentManager());
		views.setAdapter(settings);
		//this.virt.load(this);
		
		
	}
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.action, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_prev:
	        	if(game.index() == 0){
	        		views.removeAllViews();
	        		views.setAdapter(settings);
	        	}
	        	else
	        		game.prev(views);
	            return true;
	        case R.id.action_next:
	        		game.next(views);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState){
		super.onRestoreInstanceState(savedInstanceState);
	}
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		
		super.onSaveInstanceState(savedInstanceState);
		System.out.println("Saving state..");
	}
	@Override
	public void trigger(int nplayers, boolean hasDrawing) {
		// TODO Auto-generated method stub
		System.out.println("settings finished");
		game.init(views,nplayers,hasDrawing);
		views.removeAllViews();
		views.setAdapter(game);
	}
	
}
