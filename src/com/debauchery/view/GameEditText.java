package com.debauchery.view;

import com.debauchery.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;

public class GameEditText extends EditText{
	private Drawable imgCloseButton = getResources().getDrawable(R.drawable.clear);
    
	public GameEditText(Context context) {
		super(context);
		this.setup();
		// TODO Auto-generated constructor stub
	}
	private void handle(){
		if (this.getText().toString().equals(""))
        {
            // add the clear button
            this.setCompoundDrawables(this.getCompoundDrawables()[0], this.getCompoundDrawables()[1], null, this.getCompoundDrawables()[3]);
            
        }
        else
        {
            //remove clear button
            this.setCompoundDrawables(this.getCompoundDrawables()[0],this.getCompoundDrawables()[1], imgCloseButton, this.getCompoundDrawables()[3]);
        }
	}
	@Override
	public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
	    InputConnection connection = super.onCreateInputConnection(outAttrs);
	    int imeActions = outAttrs.imeOptions&EditorInfo.IME_MASK_ACTION;
	    if ((imeActions&EditorInfo.IME_ACTION_DONE) != 0) {
	        // clear the existing action
	        outAttrs.imeOptions ^= imeActions;
	        // set the DONE action
	        outAttrs.imeOptions |= EditorInfo.IME_ACTION_DONE;
	    }
	    if ((outAttrs.imeOptions&EditorInfo.IME_FLAG_NO_ENTER_ACTION) != 0) {
	        outAttrs.imeOptions &= ~EditorInfo.IME_FLAG_NO_ENTER_ACTION;
	    }
	    return connection;
	}
	private void setup() {
		// TODO Auto-generated method stub
		this.setHorizontallyScrolling(false);
		this.setSingleLine(false);
		this.setImeActionLabel("done",EditorInfo.IME_ACTION_DONE);
		imgCloseButton.setBounds(0, 0, imgCloseButton.getIntrinsicWidth(), imgCloseButton.getIntrinsicHeight());
		this.handle();
		
		//if the Close image is displayed and the user remove his finger from the button, clear it. Otherwise do nothing
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
 
            	GameEditText et = GameEditText.this;
 
                if (et.getCompoundDrawables()[2] == null)
                    return false;
                
                if (event.getAction() != MotionEvent.ACTION_UP)
                    return false;
                 
                if (event.getX() > et.getWidth() - et.getPaddingRight() - imgCloseButton.getIntrinsicWidth()) {
                    et.setText("");
                    GameEditText.this.handle();
                }
                return false;
            }
        });
 
        //if text changes, take care of the button
        this.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
 
            	GameEditText.this.handle();
            }
 
            @Override
            public void afterTextChanged(Editable arg0) {
            }
 
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
        });
		
	}
	public GameEditText(Context context, AttributeSet attrs) {
	    super(context, attrs);
	    this.setup();
	  }
	  public GameEditText(Context context, AttributeSet attrs, int defStyle) {
	    super(context, attrs, defStyle);
	    this.setup();
	  }
	  @Override
	  public boolean bringPointIntoView(int offset) {
	    // Return false because nothing we did changed anything.
	    return false;
	  }
}
