package com.debauchery.state;

import java.util.List;

import com.debauchery.Globals;
import com.debauchery.sketch.Action;
import com.debauchery.sketch.Coord;
import com.debauchery.sketch.FillRect;
import com.debauchery.sketch.SketchPad;
import com.debauchery.sketch.SketchPadData;
import com.debauchery.sketch.SketchPath;
import com.debauchery.sketch.Stroke;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/*
 * TODO: Split into Style, Stroke, Fill, Action, tables in database.
 * 
 * 
 */
public class SketchDatabase {
	private static final int DATABASE_VERSION = 2;
	private static final String TURN = "Turn";
	private static final String INDEX = "ActionId";
	private static final String POINTID = "Pointid";
	private static final String TYPE = "Type";
	private static final String XCOORD = "X";
	private static final String YCOORD = "Y";
	private static final String COLOR = "Color";
	private static final String THICKNESS = "Thickness";
	private static final String ALPHA = "Alpha";
    private static final String DATABASE_NAME = "DebaucheryGameDrawings";
    private static final String TABLE_NAME = "Drawings";
    
	//Store data
	int turn;
	DrawingDatabaseOpenHelper dbo;
	public class DrawingDatabaseOpenHelper extends SQLiteOpenHelper {
		
	    private static final String TABLE_CREATE =
	                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
	                TURN + " INTEGER, " + // the turn we're on
	                INDEX + " INTEGER, " + // the index of the operation
	                POINTID + " INTEGER, " + //for the stroke, index of the point.
	                TYPE + " INTEGER, " + //if it's a stroke or a fill operation.
	                XCOORD + " REAL, " + // x coordinate
	                YCOORD + " REAL, " + // y coordinate
	                COLOR + " INTEGER, " + // color
	                THICKNESS + " INTEGER, " + //thickness
	                ALPHA + " INTEGER)";

	    DrawingDatabaseOpenHelper(Context context) {
	        super(context, DATABASE_NAME, null, DATABASE_VERSION);
	    }

	    @Override
	    public void onCreate(SQLiteDatabase db) {
	        db.execSQL(TABLE_CREATE);
	    }

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		      onCreate(db);
		}
	}
	public SketchDatabase(Context c){
		dbo = new DrawingDatabaseOpenHelper(c);	
	}
	public void clear(){
		SQLiteDatabase wdb = dbo.getWritableDatabase();
		wdb.delete(TABLE_NAME, "", null);
	}
	private void put_entry(SQLiteDatabase wdb, int turn, int index, int pointid, int type, float xcoord, float ycoord, int color, int thickness, int alpha){
		wdb.execSQL("INSERT INTO "+TABLE_NAME+" VALUES(" +
			    turn + "," +
			    index + "," +
			    pointid + "," +
			    type + "," +
			    xcoord + "," +
			    ycoord + "," +
			    color + "," +
			    thickness + ","+
			    alpha + ");");
	}
	
	public void start(){
	}
	private void stroke(Stroke s, int TIDX, int IDX){
		SQLiteDatabase wdb = dbo.getWritableDatabase();
		int color = s.color;
		int thickness = s.thickness;
		int alpha = 255;
		List<Coord> path = s.path.path;
		for(int i=0; i < path.size(); i++){
			Coord c = path.get(i);
			put_entry(wdb,TIDX, IDX, i, Stroke.STROKE_ID, c.x, c.y, color, thickness, alpha);
		}
		wdb.close();
	}
	private void fill(FillRect s, int TIDX, int IDX){
		SQLiteDatabase wdb = dbo.getWritableDatabase(); 
		int color = s.color;
		int alpha = 255;
		int w = s.w;
		//use thickness and alpha as width and height
		put_entry(wdb,TIDX, IDX, 1, FillRect.FILL_ID, s.x, s.y, color, s.w, s.h);
		wdb.close();
	}
	public void save(SketchPadData d, int turn){
		//String deleteTurn = "DELETE FROM " + TABLE_NAME + " WHERE " + TURN + " = " + turn;
		//wdb.execSQL(deleteTurn);
		
		SQLiteDatabase wdb = dbo.getWritableDatabase();
		int ndel = wdb.delete(TABLE_NAME, TURN+"="+turn, null);
		
		System.out.println("saving: "+d.actions.size()+",deleted: "+ndel);
		for(int i=0; i < d.actions.size(); i++){
			Action a = d.actions.get(i);
		
			if(a.getType() == Stroke.STROKE_ID){
				Stroke ast = (Stroke) a;
				this.stroke(ast, turn, i);
			}
			else if(a.getType() == FillRect.FILL_ID){
				FillRect fst = (FillRect) a;
				this.fill(fst, turn, i);
			}
		}
	}
	public SketchPadData get(int turn){
		SketchPadData dat = new SketchPadData();
		String query = "Select * FROM " + TABLE_NAME + " WHERE " + TURN + " = " + turn + 
				" ORDER BY "+INDEX + ", "+POINTID+" ASC";
		
		SQLiteDatabase wdb = dbo.getWritableDatabase();
		
		Cursor cursor = wdb.rawQuery(query, null);
		int type_index = cursor.getColumnIndex(TYPE);
		int x_index = cursor.getColumnIndex(XCOORD);
		int y_index = cursor.getColumnIndex(YCOORD);
		int thick_index =  cursor.getColumnIndex(THICKNESS);
		int color_index =  cursor.getColumnIndex(COLOR);
		int alpha_index =  cursor.getColumnIndex(ALPHA);
		int w_index =  cursor.getColumnIndex(THICKNESS);
		int h_index =  cursor.getColumnIndex(ALPHA);
		int i_index = cursor.getColumnIndex(INDEX);
		
		int lidx = -1;
		int i=0;
		boolean stroking = false;
		if (cursor.moveToFirst()) {
			cursor.moveToFirst();
			do {
				int type = cursor.getInt(type_index);
				int idx = cursor.getInt(i_index);
				boolean newe = false;
				if(idx != lidx){
					newe = true;
					if(stroking) dat.close();
					stroking = false;
					i++;
				}
				lidx = idx;
				if(type == FillRect.FILL_ID){
					float x = cursor.getFloat(x_index);
					float y = cursor.getFloat(y_index);
					int w = cursor.getInt(w_index);
					int h = cursor.getInt(h_index);
					int col = cursor.getInt(color_index);
					dat.setColor(col);
					dat.fill((int)x, (int)y, w, h);
				}
				else if (type == Stroke.STROKE_ID){
					float x = cursor.getFloat(x_index);
					float y = cursor.getFloat(y_index);
					if(newe){
						int col = cursor.getInt(color_index);
						int thick = cursor.getInt(thick_index);
						int alph = cursor.getInt(alpha_index);
						dat.setColor(col);
						dat.setThickness(thick);
						dat.setAlpha(alph);
						dat.start(x, y);
						stroking = true;
						
					}
					else{
						dat.add(x, y);
					}
					
				}
				
				
			} while(cursor.moveToNext());
			if(stroking) dat.close();
		}
		System.out.println("load "+i);
		return dat;
	}
}
