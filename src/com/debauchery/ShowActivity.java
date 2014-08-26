package com.debauchery;

import java.io.File;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.debauchery.data.CardStack;

public class ShowActivity  extends ActionBarActivity {
	CardStack cards;
	protected void onCreate(Bundle savedInstanceState) {
		Intent i = getIntent();
		cards = (CardStack) i.getParcelableExtra("stack");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.slide_act_describe);
		
		
		final ImageView img =  (ImageView) findViewById(R.id.sv_show);
		String path = "";
		System.out.println(path);
		File file = new File(path);
		Uri uri = Uri.fromFile(file);
		img.setImageURI(uri);
		
	}
}
