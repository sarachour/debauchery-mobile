package com.debauchery.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class GameTextView extends TextView {
	  public GameTextView(Context context) {
	    super(context);
	    
	  }
	  public GameTextView(Context context, AttributeSet attrs) {
	    super(context, attrs);
	  }
	  public GameTextView(Context context, AttributeSet attrs, int defStyle) {
	    super(context, attrs, defStyle);
	  }

	  @Override
	  public boolean bringPointIntoView(int offset) {
	    // Return false because nothing we did changed anything.
	    return false;
	  }
}