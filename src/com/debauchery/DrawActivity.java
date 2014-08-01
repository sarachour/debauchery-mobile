package com.debauchery;

import java.io.File;
import java.io.FileOutputStream;

import com.debauchery.data.CardStack;
import com.debauchery.sketch.SketchPad;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DrawActivity extends ActionBarActivity {
	CardStack cards;
	protected void onCreate(Bundle savedInstanceState) {
		Intent i = getIntent();
		String promptText = i.getStringExtra("prompt");
		cards = (CardStack) i.getParcelableExtra("stack");
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sketch);
		final TextView prompt =  (TextView) findViewById(R.id.cv_prompt);
		final Button done = (Button) findViewById(R.id.cv_done);
		
		//update text
		prompt.setText(promptText);
		
	
		//update done
		done.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				//update
				SketchPad canv =  (SketchPad) findViewById(R.id.cv_canv);
				Bitmap img = canv.getImage();
				String path = Globals.saveInternal(getApplicationContext(), Globals.IMAGE_PATH, cards.getImageName(), img);
				
				cards.addImageCard(path);
				System.out.println(path);
				
				if(cards.isEnd()){
					Intent i = new Intent(getApplicationContext(), ReviewActivity.class);
					i.putExtra("stack", cards);
					startActivity(i);
				}
				else{
					Intent i = new Intent(getApplicationContext(), DescribeActivity.class);
					i.putExtra("picture", path);
					i.putExtra("stack", cards);
					startActivity(i);
				}
			}
			
		});
	}
}
