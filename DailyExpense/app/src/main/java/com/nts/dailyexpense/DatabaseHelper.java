package com.nts.dailyexpense;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "User.db";
    public static String TABLE_NAME = "User";
    public static String COL_ID = "Id";
    //public static String COL_TYPE = "Type";
    public static String COL_AMOUNT = "Amount";
    public static String COL_DATE = "Date";
    public static String COL_TIME = "Time";
    //public static String COL_IMAGE = "Image";
    public static String CREATE = "create table "+TABLE_NAME+"(Id INTEGER PRIMARY KEY, Amount TEXT, Date TEXT, Time TEXT)";
    public static int VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long insertData(String amount,String date,String time){

        ContentValues values = new ContentValues();
        //values.put(COL_TYPE, type);
        values.put(COL_AMOUNT, amount);
        values.put(COL_DATE, date);
        values.put(COL_TIME, time);
        //values.put(COL_IMAGE, image);
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        long id = sqLiteDatabase.insert(TABLE_NAME,null,values);
        sqLiteDatabase.close();
        return id;
    }

    public Cursor showData(){

        String show_all = "Select * From "+TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(show_all,null);
        return cursor;
    }

    public void deleteData(int id){

        getWritableDatabase().delete(TABLE_NAME, "ID=?", new String[]{String.valueOf(id)});
    }

    public Boolean updateData(int id,String new_amount,String new_date,String new_time){

        ContentValues values = new ContentValues();
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        values.put(COL_ID, id);
        values.put(COL_AMOUNT, new_amount);
        values.put(COL_DATE, new_date);
        values.put(COL_TIME, new_time);
        int result = sqLiteDatabase.update(TABLE_NAME,values,"ID =?",new String[]{String.valueOf(id)});
        return true;
    }

    public int totalExpense() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(" + COL_AMOUNT + ") as Total FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            int total = cursor.getInt(cursor.getColumnIndex("Total"));
            return total;
        }
        return 0;
    }
}
