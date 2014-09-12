package com.debauchery;

import java.io.File;

import com.debauchery.data.CardStack;
import com.debauchery.db.PersistantStateDatabase;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

public class LocalGameActivity extends Activity {
	int nPlayers = 0;
	boolean startDraw = false;
	PersistantStateDatabase db;
	protected void onCreate(Bundle savedInstanceState) {
		Intent i = getIntent();
		
		db = new PersistantStateDatabase(this);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_local);
		
		Button start = (Button) findViewById(R.id.lv_start);
		SeekBar nplayers_bar = (SeekBar) findViewById(R.id.lv_nplayers);
		ToggleButton start_mode_but = (ToggleButton) findViewById(R.id.lv_start_mode);
		TextView nplayers_label = (TextView) findViewById(R.id.lv_nplayers_prompt);
		
		nPlayers = nplayers_bar.getProgress();
		nplayers_label.setText(nPlayers+"");
		
		nplayers_bar.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){
			TextView label = (TextView) findViewById(R.id.lv_nplayers_prompt);
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
			public void onClick(View v) {
				
				if(nPlayers == 0){
					db.init(0, -1);
					Intent i = new Intent(getApplicationContext(), ReviewActivity.class);
					startActivity(i);
				}
				else{
					Intent i;
					if(startDraw){
						db.init(0, Globals.DRAW_PHASE);
						i = new Intent(getApplicationContext(), DrawActivity.class);
					}
					else {
						db.init(0, Globals.DESCRIBE_PHASE);
						i = new Intent(getApplicationContext(), DescribeActivity.class);
					}
					startActivity(i);
				}
			}
			
		});
	}
}
