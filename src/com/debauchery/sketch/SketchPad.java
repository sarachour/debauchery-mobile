package com.debauchery.sketch;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SketchPad extends SurfaceView implements SurfaceHolder.Callback {
    private ArrayList<Path> paths = new ArrayList<Path>();
    private Path path;
    private Paint paint;
    private Renderer renderer;
    private static final int ACTION_DOWN = 1;
    private static final int ACTION_MOVE = 2;
    private static final int ACTION_UP = 3;
    private int color;
    private int alpha;
    public ArrayList<Float> points = new ArrayList<Float>();

    private void init(){
    	paint = new Paint();
        paint.setDither(true);
        paint.setColor(0xFFFFFF00);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(3);
        this.alpha = 255;
        this.color = Color.BLACK;
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
    public Bitmap getImage(){
    	Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        this.drawBuffer(canvas);
        return bitmap;
    }
    public void setColor(int color){
    	this.color = color;
    }
    public void setAlpha(int alpha){
    	this.alpha = alpha;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        
        // handle touch event
        paint.setStrokeWidth(10);
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
                path = new Path();
                path.moveTo(x, y);
                path.lineTo(x, y);
                points.add(x);
                points.add(y);
                paths.add(path);
            } else if (action == ACTION_MOVE) {
                path.lineTo(x, y);
            } else if (action == ACTION_UP) {
                path.lineTo(x, y);
            }
        }
    }

    public void drawBuffer(Canvas canvas){
    	if(canvas == null){
    		System.out.println("null draw...");
    		return;
    	}
    	paint.setStyle(Paint.Style.FILL_AND_STROKE);
     	paint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, this.getWidth(), this.getHeight(), paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAlpha(alpha);
        paint.setColor(color);
        for (Path path : paths) {
            if(path != null) canvas.drawPath(path, paint);
        }
    }
    @Override
    public void onDraw(Canvas canvas) {
    	this.drawBuffer(canvas);
        
    }

    public void clear() {
        paths.clear();
       

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
}
