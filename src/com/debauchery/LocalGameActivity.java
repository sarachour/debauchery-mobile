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
import android.widget.EditText;
import android.widget.ImageView;

public class LocalGameActivity extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		Intent i = getIntent();
		String path = i.getStringExtra("picture");
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_local);
		
		Button start = (Button) findViewById(R.id.lv_start);
		
		
		start.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				EditText ptext = (EditText) findViewById(R.id.lv_prompt);
				EditText text = (EditText) findViewById(R.id.lv_nplayers);
				// TODO Auto-generated method stub
				String sprompt = ptext.getText().toString();
				int nplayers = Integer.parseInt(text.getText().toString());
				CardStack c = new CardStack(nplayers);
				c.addTextCard(sprompt);
				if(c.isEnd()){
					Intent i = new Intent(getApplicationContext(), ReviewActivity.class);
					i.putExtra("stack", c);
					startActivity(i);
				}
				else{
					Intent i = new Intent(getApplicationContext(), DrawActivity.class);
					i.putExtra("prompt", sprompt);
					i.putExtra("stack", c);
					startActivity(i);
				}
			}
			
		});
	}
}
