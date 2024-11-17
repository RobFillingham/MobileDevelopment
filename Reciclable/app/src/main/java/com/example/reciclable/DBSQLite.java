package com.example.reciclable;

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
        db.execSQL("CREATE TABLE "+DATABASE_NAME+" (name VARCHAR2(20), role VARCHAR2(20), school VARCHAR2(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void guardaDatos(String nombre, String role, String school){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO "+DATABASE_NAME+" VALUES ('"+nombre+"', '"+role+"', '"+school+"')");
    }

    public Vector<String> listaDatos(){
        Vector<String> result = new Vector<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT name, school, role FROM datos ORDER BY name", null);
        while(cursor.moveToNext()){
            result.add(cursor.getString(0));
            result.add(cursor.getString(1));
            result.add(cursor.getString(2));
        }
        cursor.close();
        return result;

    }

    public void borraDatos(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+DATABASE_NAME);
    }

}
