package com.debauchery;


import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState){
		super.onRestoreInstanceState(savedInstanceState);
		System.out.println("loading state");
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("loading main..");
		if(savedInstanceState != null) System.out.println("bundle not null");
		if(savedInstanceState != null && savedInstanceState.containsKey(Globals.STATE_PHASE)){
			int phase = savedInstanceState.getInt(Globals.STATE_PHASE);
			System.out.println("loading from state");
			Intent i;
			switch(phase){
				case Globals.DRAW_PHASE:
					i = new Intent(getApplicationContext(), DrawActivity.class);
					startActivity(i);
					break;
				case Globals.DESCRIBE_PHASE:
					i = new Intent(getApplicationContext(), DescribeActivity.class);
					startActivity(i);
					break;
				case Globals.SHOW_PHASE:
					i = new Intent(getApplicationContext(), DescribeActivity.class);
					startActivity(i);
					break;
				case Globals.PROMPT_PHASE:
					i = new Intent(getApplicationContext(), DescribeActivity.class);
					startActivity(i);
					break;
				case Globals.REVIEW_PHASE:
					i = new Intent(getApplicationContext(), DescribeActivity.class);
					startActivity(i);
					break;
			}
			
			
		}
		else{
	
			setContentView(R.layout.activity_main);
	
			final Button local = (Button) findViewById(R.id.main_local);
			
			local.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i = new Intent(getApplicationContext(), LocalGameActivity.class);
					startActivity(i);
				}
			});
			
			final Button join = (Button) findViewById(R.id.main_join);
			join.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i = new Intent(getApplicationContext(), DrawActivity.class);
					startActivity(i);
				}
			});
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


}
