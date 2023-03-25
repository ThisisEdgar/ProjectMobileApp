package com.example.bearit4u;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper2 extends SQLiteOpenHelper {
    final static String DATABASE_NAME = "Bear4U.db";
    final static int DATABASE_VERSION = 5;
    //Service Providers Table
    final static String TABLE1_NAME = "SP_table";
    final static String T1COL1 = "spId";
    final static String T1COL2 = "Userame";
    final static String T1COL3 = "Name";
    final static String T1COL4 = "Password";
    final static String T1COL5 = "Address";
    final static String T1COL6 = "City";
    final static String T1COL7 = "Phone";
    final static String T1COL8 = "Services";
    //User Table
    final static String TABLE2_NAME = "User_table";
    final static String T2COL1= "uId";
    final static String T2COL2 = "Email";
    final static String T2COL3 = "Password";
    final static String T2COL4= "FirstName";
    final static String T2COL5= "LastName";
    final static String T2COL6= "Address";
    final static String T2COL7= "Phone";
    final static String T2COL8= "vId";
    //Vehicle Table
    final static String TABLE3_NAME = "Vehicle_table";
    final static String T3COL1= "vId";
    final static String T3COL2 = "uId";
    final static String T3COL3 = "Make";
    final static String T3COL4= "Model";
    final static String T3COL5= "Year";
    //Service Table
    final static String TABLE4_NAME = "Service_table";
    final static String T4COL1= "sId";
    final static String T4COL2 = "spId";
    final static String T4COL3 = "vid";
    final static String T4COL4= "Date";
    final static String T4COL5= "Services";

    public DataBaseHelper2(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE1_NAME +
                "(" + T1COL1 + " INTEGER PRIMARY KEY," + T1COL2 + " TEXT," +
                T1COL3 + " TEXT," + T1COL4 + " TEXT," + T1COL5 + " TEXT," +
                T1COL6 + " TEXT," + T1COL7 + " TEXT,"+ T1COL8 + " TEXT)";
        sqLiteDatabase.execSQL(query);

        query= "CREATE TABLE "+ TABLE2_NAME +
                "(" + T2COL1+ " INTEGER PRIMARY KEY, "+T2COL2+ " TEXT, "+
                T2COL3+" TEXT,"+T2COL4+" TEXT,"+T2COL5+" TEXT,"+
                T2COL6+" TEXT,"+T2COL7+" TEXT,"+T2COL8+" TEXT)";
        sqLiteDatabase.execSQL(query);

        query= "CREATE TABLE "+ TABLE3_NAME +
                "(" + T3COL1+ " INTEGER PRIMARY KEY, "+T3COL2+ " TEXT, "+
                T3COL3+" TEXT,"+T3COL4+" TEXT,"+T3COL5+" TEXT)";
        sqLiteDatabase.execSQL(query);

        query= "CREATE TABLE "+ TABLE4_NAME +
                "(" + T4COL1+ " INTEGER PRIMARY KEY, "+T4COL2+ " TEXT, "+
                T4COL3+" TEXT,"+T4COL4+" TEXT,"+T4COL5+" TEXT)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE1_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE2_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE3_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE4_NAME);
        onCreate(sqLiteDatabase);
    }

    //method to insert data
    public void addSPData(String username, String name, String password,
                            String address, String city, String phone, String services){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T1COL2, username);
        values.put(T1COL3, name);
        values.put(T1COL4, password);
        values.put(T1COL5, address);
        values.put(T1COL6, city);
        values.put(T1COL7, phone);
        values.put(T1COL7, services);
        long l = sqLiteDatabase.insert(TABLE1_NAME, null, values);
    }

    public void addUserData(String email, String password, String first,
                            String last, String address, String phone){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(T2COL2, email);
        values.put(T2COL3, password);
        values.put(T2COL4, first);
        values.put(T2COL5, last);
        values.put(T2COL6, address);
        values.put(T2COL7, phone);
        long l = sqLiteDatabase.insert(TABLE2_NAME, null, values);
    }

    //method to extract data from the database
    public Cursor viewSPData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE1_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor;
    }

    public Cursor viewUserData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE2_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor;
    }

    public Cursor viewVehcileData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE3_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor;
    }

    public Cursor viewServiceData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE4_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor;
    }

    //method to delete record
    public boolean deleteUser(int uid){
        SQLiteDatabase sQliteDatabase = this.getWritableDatabase();
        int i = sQliteDatabase.delete(TABLE2_NAME, "uId=?",
                new String[]{Integer.toString(uid)});
        if(i > 0)
            return true;
        else
            return false;
    }

    public boolean deleteVehcile(int vid){
        SQLiteDatabase sQliteDatabase = this.getWritableDatabase();
        int i = sQliteDatabase.delete(TABLE3_NAME, "vId=?",
                new String[]{Integer.toString(vid)});
        if(i > 0)
            return true;
        else
            return false;
    }

    public boolean deleteService(int sid){
        SQLiteDatabase sQliteDatabase = this.getWritableDatabase();
        int i = sQliteDatabase.delete(TABLE4_NAME, "sId=?",
                new String[]{Integer.toString(sid)});
        if(i > 0)
            return true;
        else
            return false;
    }

    //method to update a record
    public boolean updateUser(int id, User user){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T2COL1, user.getEmail());
        values.put(T2COL2, user.getPassword());
        values.put(T2COL3, user.getFirstName());
        values.put(T2COL3, user.getLastName());
        values.put(T2COL4, user.getAddress());
        values.put(T2COL5, user.getPhone());
        int i = sqLiteDatabase.update(TABLE2_NAME,
                values, "Id=?", new String[]{Integer.toString(id)});
        if(i > 0)
            return true;
        else
            return false;
    }
}