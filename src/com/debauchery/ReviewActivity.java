package com.debauchery;

import java.io.File;

import com.debauchery.data.CardStack;
import com.debauchery.data.CardStack.Card;
import com.debauchery.data.CardStack.ImageCard;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class ReviewActivity extends Activity {
	CardStack cards;
	protected void onCreate(Bundle savedInstanceState) {
		Intent i = getIntent();
		cards = (CardStack) i.getParcelableExtra("stack");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_review);
		
		final SeekBar seek = (SeekBar) findViewById(R.id.rv_seek);
		
		final Button done = (Button) findViewById(R.id.rv_done);
		
		seek.setMax(cards.getNumPlayers());
		seek.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){
			

			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				final ImageView img = (ImageView) findViewById(R.id.rv_sketch);
				final TextView txt = (TextView) findViewById(R.id.rv_prompt);
				final SeekBar seek = (SeekBar) findViewById(R.id.rv_seek);
				int idx = seek.getProgress();
				System.out.println(idx);
				if(idx == cards.getNumPlayers()){
					Card curr = cards.get(idx);
					String prompt = curr.getData();
					txt.setText(prompt);
					img.setImageURI(null);
				}
				else{
					String imgpath;
					String prompt;
					int pidx = idx+1;
					Card curr = cards.get(idx);
					Card next = cards.get(pidx);
					if(curr instanceof ImageCard){
						imgpath = curr.getData();
						prompt = next.getData();
					}
					else {
						imgpath = next.getData();
						prompt = curr.getData();
					}
					File file = new File(imgpath);
					Uri uri = Uri.fromFile(file);
					img.setImageURI(uri);
					
					txt.setText(prompt);
				}
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
	}
}
