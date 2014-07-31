package com.debauchery;

import java.io.File;

import com.debauchery.sketch.SketchPad;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class DescribeActivity extends ActionBarActivity {
	protected void onCreate(Bundle savedInstanceState) {
		Intent i = getIntent();
		String path = i.getStringExtra("picture");
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_describe);
		
		ImageView img = (ImageView) findViewById(R.id.dv_image);
		Button done = (Button) findViewById(R.id.dv_done);
		EditText text = (EditText) findViewById(R.id.dv_describe);
		
		System.out.println(path);
		File file = new File(path);
		Uri uri = Uri.fromFile(file);
		img.setImageURI(uri);
		
		done.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				EditText text = (EditText) findViewById(R.id.dv_describe);
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), DrawActivity.class);
				String textf = text.getText().toString();
				i.putExtra("prompt", textf);
				startActivity(i);
			}
			
		});
	}
}
