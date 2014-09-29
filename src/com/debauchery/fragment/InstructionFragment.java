package com.debauchery.fragment;

import com.debauchery.Globals;
import com.debauchery.R;
import com.debauchery.fragment.iface.FragmentInterface;
import com.debauchery.fragment.iface.FragmentTurnInterface;
import com.debauchery.fragment.iface.GameActivityInterface;
import com.debauchery.state.Databases;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class InstructionFragment extends FragmentInterface implements FragmentTurnInterface{
	private static InstructionFragment INSTANCE;
	String header1;
	String header2;
	int image_id;
	int bg_id;
	public InstructionFragment(GameActivityInterface g, int bg_id, String hdr1, int image_id, String hdr2) {
		super(g,R.layout.slide_view_inst);
		this.header1 = hdr1;
		this.header2 = hdr2;
		this.image_id = image_id;
		this.bg_id = bg_id;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void setTurn(int turn) {
		
	}
	@Override
	public void create() {
		// TODO Auto-generated method stub
		this.load();
		TextView H1 = (TextView) find(R.id.iv_header);
		TextView H2 = (TextView) find(R.id.iv_subheader);
		ImageView ICON = (ImageView) find(R.id.iv_icon);
		LinearLayout LAY = (LinearLayout) find(R.id.instr_view);
		Button next = (Button) find(R.id.iv_next);
		
		H1.setText(this.header1);
		H2.setText(this.header2);
		ICON.setImageResource(this.image_id);
		LAY.setBackgroundResource(this.bg_id);
		next.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				frame.next();
			}
			
		});
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		
	}
	
}
