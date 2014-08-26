package com.debauchery;

import java.io.File;

import com.debauchery.data.Card;
import com.debauchery.data.CardStack;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
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
import android.widget.RelativeLayout;
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
		final int height = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150, getResources().getDisplayMetrics());

		
		//lay.removeAllViews();
		
		for(int j=0; j < cards.getNumPlayers(); j++){
			Card c = cards.get(j);
			if(c.getType().equals("text")){
				View v = getLayoutInflater().inflate(R.layout.comp_card_txt, null);
				TextView txt = (TextView) v.findViewById(R.id.cardv_txt);
				v.setTag(j);
				//txt.setText(c.getData());
				lay.addView(v);
			}
			else if(c.getType().equals("image")){
				View v = getLayoutInflater().inflate(R.layout.comp_card_img, null);
				ImageView img = (ImageView) v.findViewById(R.id.cardv_img);
				v.setTag(j);
				//File file = new File(c.getData());
				//Uri uri = Uri.fromFile(file);
				//img.setImageURI(uri);
				lay.addView(v);
			}
			else{
				System.out.println(j+" unknown:["+c.getType()+"]");
			}
		}
		
		done.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
}
