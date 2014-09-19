package com.debauchery.fragment;

import com.debauchery.R;
import com.debauchery.R.layout;
import com.debauchery.sketch.ColorPickerView.OnColorChangedListener;
import com.debauchery.sketch.SketchPad;
import com.debauchery.sketch.ColorPickerView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class DrawOptionsFragment extends Fragment {
	SketchPad sketchpad;
	View view;
	public DrawOptionsFragment(SketchPad sk) {
		this.sketchpad = sk;
	}

	public SketchPad getSketchpad() {
		return sketchpad;
	}

	private void setupControls() {
		ImageButton fill = (ImageButton) view.findViewById(R.id.fdc_fill);
		ImageButton redo = (ImageButton) view.findViewById(R.id.fdc_redo);
		ImageButton undo = (ImageButton) view.findViewById(R.id.fdc_undo);
		ImageButton clear = (ImageButton) view.findViewById(R.id.fdc_clear);
		SeekBar thickness = (SeekBar) view.findViewById(R.id.fdc_thickness);
		ColorPickerView color = (ColorPickerView) view.findViewById(R.id.fdc_colorpicker);
		
		fill.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				sketchpad.fill();
			}
			
		});
		redo.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				sketchpad.redo();
			}
			
		});
		undo.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				sketchpad.undo();
			}
			
		});
		clear.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				sketchpad.clear();
			}
			
		});
		thickness.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){
			TextView label = (TextView) view.findViewById(R.id.fdc_thickness_label);
			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				// TODO Auto-generated method stub
				int actual = (int) (((float) arg1)/100.0*50);
				sketchpad.setThickness(actual);
				label.setText(""+actual);
			}

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}

			
			
		});
		color.setOnColorChangedListener(new OnColorChangedListener(){
			View swatch = (View) view.findViewById(R.id.fdc_colorpicker_swatch);
			@Override
			public void colorChanged(int color) {
				// TODO Auto-generated method stub
				sketchpad.setColor(color);
				swatch.setBackgroundColor(color);
			}
			
		});
		
		
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.frag_draw_controls, container, false);
		view = v;
		setupControls();
		return v;
	}
}
