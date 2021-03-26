package com.JG.rockpaperscissors.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final String TABLE_NAME = "score";
    private static final String COL1 = "id";
    private static final String COL2 = "timestamp";
    private static final String COL3 = "win";
    private static final String COL4 = "loss";
    private static final String COL5 = "tie";

    public DatabaseHelper(@Nullable Context context) {

        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + TABLE_NAME+ " ("+COL1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COL2 + " INTEGER, "+
                COL3 + " INTEGER, "+
                COL4 + " INTEGER, "+
                COL5 + " INTEGER)"
                ;
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean addData(int time, int win ,int loss, int tie ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL2,time);
        contentValues.put(COL3,win);
        contentValues.put(COL4,loss);
        contentValues.put(COL5,tie);

        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public int allwin(){
        SQLiteDatabase db = this.getWritableDatabase();
        int total;
       Cursor c = db.rawQuery("select sum(win) from score;", null);
        if(c.moveToFirst())
            total = c.getInt(0);
        else
            total = -1;
        c.close();

        return total;
    }

    public int allloss(){
        SQLiteDatabase db = this.getWritableDatabase();
        int total;
        Cursor c = db.rawQuery("select sum(loss) from score;", null);
        if(c.moveToFirst())
            total = c.getInt(0);
        else
            total = -1;
        c.close();

        return total;
    }
    public int alltie(){
        SQLiteDatabase db = this.getWritableDatabase();
        int total;
        Cursor c = db.rawQuery("select sum(tie) from score;", null);
        if(c.moveToFirst())
            total = c.getInt(0);
        else
            total = -1;
        c.close();

        return total;
    }

    public int lastMinuteWin(int time){
        SQLiteDatabase db = this.getWritableDatabase();
        int total;
        Cursor c = db.rawQuery("select sum(win) from score where timestamp = "+time+";", null);
        if(c.moveToFirst())
            total = c.getInt(0);
        else
            total = -1;
        c.close();

        return total;
    }
    public int lastMinuteloss(int time){
        SQLiteDatabase db = this.getWritableDatabase();
        int total;
        Cursor c = db.rawQuery("select sum(loss) from score where timestamp = "+time+";", null);
        if(c.moveToFirst())
            total = c.getInt(0);
        else
            total = -1;
        c.close();

        return total;
    }
    public int lastMinutetie(int time){
        SQLiteDatabase db = this.getWritableDatabase();
        int total;
        Cursor c = db.rawQuery("select sum(tie) from score where timestamp = "+time+";", null);
        if(c.moveToFirst())
            total = c.getInt(0);
        else
            total = -1;
        c.close();

        return total;
    }
    public void resetTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_NAME);
    }


}
