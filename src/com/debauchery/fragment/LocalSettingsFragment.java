package com.debauchery.fragment;

import com.debauchery.Globals;
import com.debauchery.R;
import com.debauchery.fragment.iface.FragmentInterface;
import com.debauchery.fragment.iface.GameActivityInterface;
import com.debauchery.state.Databases;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class LocalSettingsFragment extends FragmentInterface{
	
	int nPlayers = 0;
	boolean startDraw = false;
	final String SDRAW_KEY = "LOCALGAMESETTINGS_SDRAW";
	final String NPLAYERS_KEY = "LOCALGAMESETTINGS_NPLAYERS";
	
	
	public LocalSettingsFragment(GameActivityInterface g){
		super(g, R.layout.slide_settings_local);
	}
	
	public int getNumberPlayers(){
		return nPlayers;
	}
	public boolean startWithDraw(){
		return startDraw;
	}
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		Button start = (Button) find(R.id.lv_start);
		SeekBar nplayers_bar = (SeekBar) find(R.id.lv_nplayers);
		ToggleButton start_mode_but = (ToggleButton) find(R.id.lv_start_mode);
		TextView nplayers_label = (TextView) find(R.id.lv_nplayers_prompt);
		
		nPlayers = nplayers_bar.getProgress();
		nplayers_label.setText(nPlayers+"");
		
		nplayers_bar.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){
			TextView label = (TextView) find(R.id.lv_nplayers_prompt);
			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				// TODO Auto-generated method stub
				nPlayers = arg1;
				label.setText(""+nPlayers);
				
			}

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		start_mode_but.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				startDraw = arg1;
			}
			
		});
		
		start.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				db.clear();
				frame.game(nPlayers, startDraw);
			}
			
		});
	}
	@Override
	public void save() {
		// TODO Auto-generated method stub
		prefs.put(NPLAYERS_KEY, nPlayers);
		prefs.put(SDRAW_KEY, startDraw);
	}
	@Override
	public void load() {
		SeekBar nplayers_bar = (SeekBar) find(R.id.lv_nplayers);
		ToggleButton start_mode_but = (ToggleButton) find(R.id.lv_start_mode);
		TextView nplayers_label = (TextView) find(R.id.lv_nplayers_prompt);
		
		// TODO Auto-generated method stub
		startDraw = prefs.getBoolean(SDRAW_KEY);
		nPlayers = prefs.getInt(NPLAYERS_KEY);
		
		nplayers_bar.setProgress(nPlayers);
		nplayers_label.setText(""+nPlayers);
		if(startDraw != start_mode_but.isChecked())
			start_mode_but.toggle();
	}

}
