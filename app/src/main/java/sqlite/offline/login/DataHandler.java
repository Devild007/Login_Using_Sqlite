package sqlite.offline.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DEMO";
    private static final String TABLE_NAME = "USER";
    private static final String COLUMN_NAM = "NAME";
    private static final String COLUMN_EMA = "EMAIL";
    private static final String COLUMN_MOB = "MOBILE";
    private static final String COLUMN_PASS = "PASSWORD";


    public DataHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table USER (NAME text, EMAIL text primary key, MOBILE text, PASSWORD text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        //DROP OLDER TABLE IF EXIST
        db.execSQL("drop table if exists "+ TABLE_NAME);
        // CREATE TABLE AGAIN
        onCreate(db);
    }

    //inserting in table
    public boolean insert(String name, String email, String mobile, String password){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAM, name);
        contentValues.put(COLUMN_EMA, email);
        contentValues.put(COLUMN_MOB, mobile);
        contentValues.put(COLUMN_PASS, password);
        long ins = db.insert(TABLE_NAME,null,contentValues);
        if(ins==-1) return false;
        else return true;

    }

    //checking email if exists
    public boolean chkemail(String email){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where email =?",new String[]{email});
        if(cursor.getCount()>0) return false;
        else return true;

    }

    //checking email and password
    public boolean emailpassword(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where email=? and password=?",new String[]{email,password});
        if(cursor.getCount()>0) return true;
        else return false;
    }


    public Cursor getdata(String Email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where email=? ",new String[]{Email});
        return cursor;
    }

}
