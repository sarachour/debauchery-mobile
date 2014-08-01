package com.debauchery;

import java.io.File;

import com.debauchery.data.CardStack;
import com.debauchery.data.CardStack.Card;
import com.debauchery.data.CardStack.ImageCard;
import com.debauchery.data.CardStack.TextCard;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
		
		final LinearLayout lay = (LinearLayout) findViewById(R.id.rv_display_layout);
		final Button done = (Button) findViewById(R.id.rv_done);
		
		lay.removeAllViews();
		for(int j=0; j < cards.getNumPlayers(); j++){
			Card c = cards.get(j);
			LinearLayout v = new LinearLayout(this, null, R.style.CardStyle);
			v.setOrientation(LinearLayout.HORIZONTAL);
			v.setTag(j);
			v.setPadding(10, 10, 10, 10);
			
			if(c.getType().equals("text")){
				TextView text = new TextView(this);
				text.setText(c.getData());
				v.addView(text);
			}
			else if(c.getType().equals("image")){
				ImageView img = new ImageView(this);
				File file = new File(c.getData());
				Uri uri = Uri.fromFile(file);
				img.setImageURI(uri);
				v.addView(img);
			}
			else{
				System.out.println(j+" unknown:["+c.getType()+"]");
			}
			lay.addView(v);
		}
		
		done.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
}
