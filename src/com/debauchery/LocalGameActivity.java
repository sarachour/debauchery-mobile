package com.debauchery;

import java.io.File;

import com.debauchery.data.CardStack;

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
	protected void onCreate(Bundle savedInstanceState) {
		Intent i = getIntent();
		String path = i.getStringExtra("picture");
		
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
				
				CardStack c = new CardStack(nPlayers);
				if(startDraw)
					c.setStartMode(CardStack.DRAWING);
				else
					c.setStartMode(CardStack.DESCRIBING);
				if(c.isEnd()){
					Intent i = new Intent(getApplicationContext(), ReviewActivity.class);
					i.putExtra("stack", c);
					startActivity(i);
				}
				else{
					Intent i;
					if(startDraw)
						i = new Intent(getApplicationContext(), DrawActivity.class);
					else
						i = new Intent(getApplicationContext(), DescribeActivity.class);
					
					i.putExtra("stack", c);
					startActivity(i);
				}
			}
			
		});
	}
}
