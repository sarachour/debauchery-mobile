package com.debauchery;

import com.debauchery.fragment.LocalGameStateManager;
import com.debauchery.fragment.iface.FragmentInterface;
import com.debauchery.fragment.iface.GameActivityInterface;
import com.debauchery.sketch.SketchPad;
import com.debauchery.state.Databases;
import com.debauchery.state.Preferences;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class LocalGameActivity extends FragmentActivity implements GameActivityInterface{
	LocalGameStateManager game;
	private Preferences prefs;
	
	boolean onSettings = true;
	private void save(){
		
	}
	private void load(){
		game = new LocalGameStateManager(this);
		game.load();
	}
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_local);
		
		Preferences.create(this);
		Databases.create(this);
		
		this.prefs = new Preferences();
		
		this.load();
		
	}
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_settings:
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
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		Intent i = new Intent(getApplicationContext(), MainActivity.class);
		i.putExtra(Globals.GAME_TYPE_KEY, Globals.MAIN_MENU);
		startActivity(i);
	}
	@Override
	public void game(int nplayers, boolean startWithDraw) {
		// TODO Auto-generated method stub
		game.init(nplayers,startWithDraw);
		onSettings = false;
		game.next();
	}
	@Override
	public FragmentManager mgr() {
		return this.getSupportFragmentManager();
	}
	@Override
	public int gameContainerId() {
		// TODO Auto-generated method stub
		return  R.id.lg_frag_container;
	}
	@Override
	public void change(int type, int turn) {
		// TODO Auto-generated method stub
	}
	@Override
	public void review() {
		// TODO Auto-generated method stub
		this.game.review();
	}
	@Override
	public void prev() {
		// TODO Auto-generated method stub
		game.prev();
	}
	@Override
	public void next() {
		// TODO Auto-generated method stub
		game.next();
	}
	
}
