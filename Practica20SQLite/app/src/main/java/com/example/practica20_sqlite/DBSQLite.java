package com.example.practica20_sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Button;

import java.util.Vector;

public class DBSQLite extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "datos";


    public DBSQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+DATABASE_NAME+" ("+"id INTEGER PRIMARY " +
                "KEY AUTOINCREMENT, "+"nombre text, fecha long)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void guardaDatos(String nombre, long fecha){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO "+DATABASE_NAME+" VALUES (null, '"+nombre+"', "+fecha+")");
    }

    public Vector<String> listaDatos(){
        Vector<String> result = new Vector<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, nombre FROM datos ORDER BY id", null);
        while(cursor.moveToNext()){
            result.add(cursor.getString(0)+" "+cursor.getString(1));
        }
        cursor.close();
        return result;

    }

    public void borraDatos(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+DATABASE_NAME);
    }

}
