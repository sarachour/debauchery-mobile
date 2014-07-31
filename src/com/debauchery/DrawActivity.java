package com.debauchery;

import java.io.File;
import java.io.FileOutputStream;

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
import android.widget.TextView;

public class DrawActivity extends ActionBarActivity {
	
	protected void onCreate(Bundle savedInstanceState) {
		Intent i = getIntent();
		String promptText = i.getStringExtra("prompt");
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sketch);
		TextView prompt =  (TextView) findViewById(R.id.cv_prompt);
		final Button done = (Button) findViewById(R.id.cv_done);
		
		//update text
		prompt.setText(promptText);
		
		//update canvas
		
		//update done
		done.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				SketchPad canv =  (SketchPad) findViewById(R.id.cv_canv);
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), DescribeActivity.class);
				Bitmap img = canv.getImage();
				String path = Globals.saveInternal(getApplicationContext(), Globals.IMAGE_PATH, Globals.IMAGE_NAME, img);
				System.out.println(path);
				i.putExtra("picture", path);
				startActivity(i);
			}
			
		});
	}
}
