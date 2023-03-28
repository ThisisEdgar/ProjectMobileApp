package com.example.bearit4u;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseMethods extends SQLiteOpenHelper {
    final static String DATABASE_NAME = "Information.db";
    final static int DATABASE_VERSION = 1;
    final static String TABLE1_NAME = "USERS";

    final static String T1COL0= "Id";

    final static String T1COL1 = "Email";

    final static String T1COL2= "FirstName";

    final static String T1COL3= "LastName";

    final static String T1COL4= "Address";

    final static String T1COL5= "Phone";

    final static String T1COL6= "Picture";

    public DataBaseMethods(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query= "CREATE TABLE "+ TABLE1_NAME + "("+ T1COL0+ "INTEGER PRIMARY KEY, "+T1COL1+ " TEXT, "+T1COL2+" TEXT,"+T1COL3+" TEXT,"+T1COL4+" TEXT,"+T1COL5+" INTEGER,"+T1COL6+" TEXT)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE1_NAME);
        onCreate(sqLiteDatabase);
    }
    //Insert Data
    public boolean insertThis(String email,String firstName, String lastName,String Address, Integer Phone, String Picture)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T1COL1,email);
        values.put(T1COL2,firstName);
        values.put(T1COL3,lastName);
        values.put(T1COL4,Address);
        values.put(T1COL5,Phone);
        long l = sqLiteDatabase.insert(TABLE1_NAME,null,values);
        if(l>0)
            return true;
        else
            return false;

        //second try
    }
    public Cursor seeThis(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "SELECT * FROM "+ TABLE1_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        return cursor;
    }
    public boolean deleteThis(int id){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        int i = sqLiteDatabase.delete(TABLE1_NAME,"Id=?",
                new String[]{Integer.toString(id)});
                if(i>0)
                    return true;
                else
                    return false;

    }
    public boolean updateThis(int id,String email,String firstName,String lastName,String Address,String Phone)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T1COL1,email);
        values.put(T1COL2,firstName);
        values.put(T1COL3,lastName);
        values.put(T1COL4,Address);
        values.put(T1COL5,Phone);
        int i = sqLiteDatabase.update(TABLE1_NAME,
                values,"Id=?",new String[]{Integer.toString(id)});
        if(i>0)
            return true;
        else
            return false;
    }

}
