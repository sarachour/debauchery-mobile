package com.debauchery;

import java.io.File;

import com.debauchery.data.CardStack;
import com.debauchery.gesture.OnSwipeTouchListener;
import com.debauchery.gesture.TwoPanelFactory;
import com.debauchery.sketch.SketchPad;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class DescribeActivity extends ActionBarActivity {
	CardStack cards;
	ActionBarActivity that;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		Intent i = getIntent();
		String path = i.getStringExtra("picture");
		cards = (CardStack) i.getParcelableExtra("stack");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_describe);
		
		
		final EditText prompt =  (EditText) findViewById(R.id.dv_describe);
		final Button done = (Button) findViewById(R.id.dv_done);
		final ImageView img = (ImageView) findViewById(R.id.dv_show);
		
		System.out.println(path);
		File file = new File(path);
		Uri uri = Uri.fromFile(file);
		img.setImageURI(uri);
		
		that = this;
		done.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				EditText text = (EditText) findViewById(R.id.dv_describe);
				String textf = text.getText().toString();
				if(textf.length() == 0){
					new AlertDialog.Builder(v.getContext())
				    .setTitle("No Text Found")
				    .setMessage("Please describe the drawing.")
				    .setIcon(android.R.drawable.ic_dialog_alert)
				     .show();
					return;
				}
				cards.addTextCard(textf);
				if(cards.isEnd()){
					Intent i = new Intent(getApplicationContext(), ReviewActivity.class);
					i.putExtra("stack", cards);
					startActivity(i);
				}
				else{
					Intent i = new Intent(getApplicationContext(), DrawActivity.class);
					i.putExtra("prompt", textf);
					i.putExtra("stack", cards);
					startActivity(i);
				}
			}
			
		});
		TwoPanelFactory.setupButtons(this, 
				R.id.dv_switcher, 
				R.id.dv_sel_show, 
				R.id.dv_show_container, 
				R.id.dv_sel_describe, 
				R.id.dv_describe_container);
	}
}
