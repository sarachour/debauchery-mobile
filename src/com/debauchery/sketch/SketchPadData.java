package com.debauchery.sketch;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Parcel;
import android.os.Parcelable;

public class SketchPadData implements Parcelable{
	public Stack<Action> actions = new Stack<Action>();
    private Stack<Action> unactions = new Stack<Action>();
	public int color;
	public int alpha;
	public int thickness;
    public SketchPath currentPath = null;
	public SketchPadData(){
		this.thickness = 3;
        this.alpha = 255;
        this.color = Color.BLACK;
	}
	public SketchPadData(Parcel p){
		List<Action> act = new LinkedList<Action>();
    	p.readTypedList(act, Action.CREATOR);
		this.actions.addAll(act);
    	this.color = p.readInt();
    	this.alpha = p.readInt();
    	this.thickness = p.readInt();
	}
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel p, int flags) {
		// TODO Auto-generated method stub
		p.writeTypedList(actions);
    	p.writeInt(color);
    	p.writeInt(alpha);
    	p.writeInt(thickness);
	}
	public void undo(){
    	if(actions.size() > 0)
    		unactions.push(actions.pop());
    }
    public void redo(){
    	if(unactions.size() > 0)
    		actions.push(unactions.pop());
    }
    public void setThickness(int thickness){
    	this.close();
    	this.thickness = thickness;
    }
    public void clear(){
    	actions.clear();
    	unactions.clear();
    }
    public void setColor(int color){
    	this.close();
    	this.color = color;
    }
    public void setAlpha(int alpha){
    	this.close();
    	this.alpha = alpha;
    }
    public void fill(int x, int y, int w, int h){
    	this.actions.push(new FillRect(this.color,x,y,w,h));
    }
    public void close(){
    	
    }
    public void start(float x, float y){
    	currentPath = new SketchPath(x,y);
    	actions.push(new Stroke(currentPath, this.color, this.thickness));
    }
    public void add(float x, float y){
    	currentPath.add(x, y);
    }
    public void draw(SketchPad sk, Canvas canvas, Paint p){
    	if(canvas == null){
    		System.out.println("null draw...");
    		return;
    	}
    	p.setStyle(Paint.Style.FILL_AND_STROKE);
     	p.setColor(Color.WHITE);
        canvas.drawRect(0, 0, sk.getWidth(), sk.getHeight(), p);
        p.setStyle(Paint.Style.STROKE);
        p.setAlpha(alpha);
        p.setColor(color);
        for (int i =0; i < actions.size(); i++) {
        	Action act = actions.get(i);
            act.draw(canvas, p);;
        }
    }
	public int size() {
		return actions.size();
	}
};