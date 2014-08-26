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
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.ViewSwitcher;

public class DescribeActivity extends ActionBarActivity {
	CardStack cards;
	String description;
	protected void onCreate(Bundle savedInstanceState) {
		Intent i = getIntent();
		String path = i.getStringExtra("picture");
		cards = (CardStack) i.getParcelableExtra("stack");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.slide_act_describe);
		
		
		final EditText edit =  (EditText) findViewById(R.id.sa_describe);
		final Button done = (Button) findViewById(R.id.sa_describe_done);
		
		
		edit.setOnEditorActionListener(new OnEditorActionListener(){

			@Override
			public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
				// TODO Auto-generated method stub
				description = arg0.getText().toString();
				return true;
			}
			
		});
		done.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				if(description.length() == 0){
					new AlertDialog.Builder(v.getContext())
				    .setTitle("No Text Found")
				    .setMessage("Please describe the drawing.")
				    .setIcon(android.R.drawable.ic_dialog_alert)
				     .show();
					return;
				}
				cards.addTextCard(description);
				if(cards.isEnd()){
					Intent i = new Intent(getApplicationContext(), ReviewActivity.class);
					i.putExtra("stack", cards);
					startActivity(i);
				}
				else{
					Intent i = new Intent(getApplicationContext(), PromptActivity.class);
					i.putExtra("stack", cards);
					startActivity(i);
				}
			}
			
		});
	}
}
