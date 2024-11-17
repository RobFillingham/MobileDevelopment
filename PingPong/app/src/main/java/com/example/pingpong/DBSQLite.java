package com.example.pingpong;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBSQLite extends SQLiteOpenHelper {


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "best";


    public DBSQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+DATABASE_NAME+" (best varchar2(10) )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void saveBest(String best){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+DATABASE_NAME);
        db.execSQL("INSERT INTO "+DATABASE_NAME+" VALUES ('"+best+"')");
    }

    public String getBest(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+DATABASE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String best = "00:00";
        if(cursor.moveToFirst()){
            best = cursor.getString(0);
        }
        return best;
    }
}
