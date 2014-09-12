package com.debauchery.db;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DescriptionDatabase {
		private static final int DATABASE_VERSION = 2;
		private static final String TURN = "Turn";
		private static final String DESCRIPTION = "Desc";
		private static final String DATABASE_NAME = "Game";
	    private static final String TABLE_NAME = "Descriptions";
		DescriptionDatabaseOpenHelper dbo;
		public class DescriptionDatabaseOpenHelper extends SQLiteOpenHelper {
			
		    private static final String TABLE_CREATE =
		                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
		                TURN + " INTEGER, " + // the turn we're on
		                DESCRIPTION + " TEXT)";

		    DescriptionDatabaseOpenHelper(Context context) {
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
		public DescriptionDatabase(Context c){
			dbo = new DescriptionDatabaseOpenHelper(c);	
		}
		public void save(String desc, int turn){
			String deleteTurn = "DELETE FROM " + TABLE_NAME + " WHERE " + TURN + " = " + turn;
			SQLiteDatabase wdb = dbo.getWritableDatabase();
			wdb.execSQL(deleteTurn);
			wdb.execSQL("INSERT INTO "+TABLE_NAME+" VALUES(" +
				    turn + "," +
				    desc + ");");
		}
		public String get(int turn){
			String query = "Select * FROM " + TABLE_NAME + " WHERE " + TURN + " = " + turn;
			
			SQLiteDatabase wdb = dbo.getWritableDatabase();
			
			Cursor cursor = wdb.rawQuery(query, null);
			int desc_index = cursor.getColumnIndex(DESCRIPTION);
			
			
			if (cursor.moveToFirst()) {
				cursor.moveToFirst();
				String desc = cursor.getString(desc_index);
				return desc;
			}
			return "";
		}
}
