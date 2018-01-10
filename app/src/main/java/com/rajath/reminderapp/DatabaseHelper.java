package com.rajath.reminderapp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "ReminderApp.db";
    public static final String TABLE_NAME  = "Tasks_table";
    public static final String COL_0 = "ID";
    public static final String COL_1 = "TaskDesc";
    public static final String COL_2 = "DATE";
    public static final String COL_3 = "TIME";
    public static final String COL_4 = "IsChecked";
    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME, null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " +TABLE_NAME+ "("+COL_0 +" INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_1 + " TEXT ," + COL_2 + " TEXT ," + COL_3 + " TEXT," + COL_4 + " TEXT " + ")" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String TaskDesc , String DATE , String TIME , String IsChecked){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,TaskDesc);
        contentValues.put(COL_2,DATE);
        contentValues.put(COL_3,TIME);
        contentValues.put(COL_4,IsChecked);
        long result = db.insert(TABLE_NAME,null,contentValues);

        return !(result == -1);

    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from " + TABLE_NAME,null);
    }

    public Cursor getDataById(int id){
        String idt = id + "";
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " Where ID = " + idt ,null);
    }

    public boolean updateData(String id,String taskdesc,String date ,String time,String isChecked){
         SQLiteDatabase db = this.getWritableDatabase();
         ContentValues contentValues = new ContentValues();
         contentValues.put(COL_0,id);
         contentValues.put(COL_1,taskdesc);
         contentValues.put(COL_2,date);
         contentValues.put(COL_3,time);
         contentValues.put(COL_4,isChecked);
         db.update(TABLE_NAME,contentValues,"ID = ?",new String[]{id});
         return true;
    }





}
