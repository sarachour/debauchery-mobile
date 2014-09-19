package com.debauchery;

import com.debauchery.fragment.LocalGameStateManager;
import com.debauchery.fragment.LocalGameStateManager.GameStateChangedListener;
import com.debauchery.fragment.LocalGameStateManager.LocalGameSettings;
import com.debauchery.fragment.LocalSettingsFragment.SettingsFinishedListener;
import com.debauchery.fragment.iface.FragmentInterface;
import com.debauchery.sketch.SketchPad;
import com.debauchery.state.PersistantStateDatabase;
import com.debauchery.state.Preferences;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
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

public class LocalGameActivity extends FragmentActivity implements SettingsFinishedListener, GameStateChangedListener{
	int container = R.id.lg_frag_container;
	
	private Preferences prefs;
	private LocalGameSettings settings;
	private LocalGameStateManager game;
	final String ONSETTINGS_KEY = "LOCALGAMEACTIVITY_ONSETTINGS";
	boolean onSettings = true;
	private void save(){
		prefs.put(ONSETTINGS_KEY, onSettings);
	}
	private void load(){
		onSettings = prefs.getBoolean(ONSETTINGS_KEY);
		if(onSettings){
			settings = new LocalGameSettings(getSupportFragmentManager(), container, this);
			settings.load();
		}
		else{
			game = new LocalGameStateManager(getSupportFragmentManager(), container);
			game.load();
		}
	}
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_local);
		
		Preferences.create(this);
		PersistantStateDatabase.create(this);
		
		this.prefs = new Preferences();
		settings = new LocalGameSettings(getSupportFragmentManager(), container, this);
		game = new LocalGameStateManager(getSupportFragmentManager(), container);
		game.setListener(this);
		Button next = (Button) this.findViewById(R.id.lg_next);
		Button prev = (Button) this.findViewById(R.id.lg_prev);
		
		next.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				game.next();
			}
			
		});
		prev.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				game.prev();
			}
			
		});
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
	        		//views.setAdapter(settings);
	        	}
	        	else
	        		game.prev();
	        		return true;
	        case R.id.action_next:
	        		game.next();
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
		game.init(nplayers,hasDrawing);
		onSettings = false;
		//views.setAdapter(game);
	}
	@Override
	public void onChange(int turn) {
		TextView t = (TextView) this.findViewById(R.id.lg_player_turn);
		t.setText("Turn "+turn);
	}
	
}
