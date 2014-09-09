package com.debauchery;

import com.debauchery.data.CardStack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

public class PromptActivity extends ActionBarActivity {
	CardStack cards;
	protected void onCreate(Bundle savedInstanceState) {
		Intent i = getIntent();
		cards = (CardStack) i.getParcelableExtra("stack");

		super.onCreate(savedInstanceState);
		setContentView(R.layout.slide_view_prompt);
		final TextView prompt =  (TextView) findViewById(R.id.sv_prompt);
		
	}
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState){
		super.onRestoreInstanceState(savedInstanceState);
		System.out.println("loading state");
	}

}
