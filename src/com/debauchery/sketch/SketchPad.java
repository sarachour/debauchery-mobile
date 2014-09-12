package com.debauchery.sketch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SketchPad extends SurfaceView implements SurfaceHolder.Callback {    
    private Path path;
    private Paint paint;
    private Renderer renderer;
    private static final int ACTION_DOWN = 1;
    private static final int ACTION_MOVE = 2;
    private static final int ACTION_UP = 3;
    private boolean locked = false;
    
    class Animate extends TimerTask{
    	SketchPadData data;
    	SketchPad pad;
    	int i;
    	public Animate(SketchPad p, SketchPadData d){
    		this.data =d;
    		this.pad = p;
    		this.pad.loadData(new SketchPadData());
    		this.i = 0;
    	}
        //This task will repeat every five seconds
        public void run(){
            if(i < data.size()){
            	this.pad.dat.actions.push(data.actions.get(i));
            	i+=1;
            }
        }
    }
    
    SketchPadData dat;
    public void lock(){
    	locked = true;
    }
    public void unlock(){
    	locked = false;
    }
    public void playback(SketchPadData d, int repeat){
    	Timer timer = new Timer();
        timer.scheduleAtFixedRate(new Animate(this,d), d.size()*repeat, repeat);
    	
    }
    private void init(){
    	dat = new SketchPadData();
    	paint = new Paint();
        paint.setDither(true);
        paint.setColor(dat.color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(dat.thickness);
        
        //this.setBackgroundColor(Color.WHITE); //set white color
        getHolder().addCallback(this);
    }
    public SketchPad(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    public SketchPad(Context context) {
        super(context);
        this.init();
    }
    public SketchPad(Context context, AttributeSet attrs, Parcel p){
    	super(context, attrs);
    	this.dat = new SketchPadData(p);
    }
    public void loadData(SketchPadData d){
    	this.dat = d;
    }
    public SketchPadData getData(){
    	return dat;
    }

    
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	if(locked) return false;
     
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                handleAction(ACTION_DOWN, event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                handleAction(ACTION_MOVE, event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_UP:
                handleAction(ACTION_UP, event.getX(), event.getY());
        }
        
        return true;
    }

    public void handleAction(int action, float x, float y) {
        synchronized (renderer.getSurfaceHolder()) {
            if (action == ACTION_DOWN) {
            	dat.start(x,y);
            	
            } else if (action == ACTION_MOVE) {
            	dat.add(x,y);
            } else if (action == ACTION_UP) {
                dat.add(x,y);
            }
        }
    }
    
    public void drawBuffer(Canvas canvas){
    	dat.draw(this, canvas, paint);
    }
    
    @Override
    
    public void onDraw(Canvas canvas) {
    	this.drawBuffer(canvas);
        
    }
    

    public void clear() {
    	this.dat.clear();

    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    public void surfaceCreated(SurfaceHolder holder) {
        renderer = new Renderer(getHolder(), this);
        renderer.setRunning(true);
        renderer.start();
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        System.out.println("Destroyed..");
        renderer.setRunning(false);
        while (retry) {
            try {
                renderer.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }
	public void fill() {
		if(locked) return;
		this.dat.fill(0,0,getWidth(),getHeight());
		
	}
	public void redo() {
		if(locked) return;
		this.dat.redo();
		
	}
	public void undo() {
		if(locked) return;
		this.dat.undo();
		
	}
	public void setThickness(int actual) {
		if(locked) return;
		this.dat.setThickness(actual);
		
	}
	public void setColor(int color) {
		if(locked) return;
		this.dat.setColor(color);
		
	}
}
