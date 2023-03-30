package com.example.bearit4u;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    private Context context;
    final static String DATABASE_NAME = "Bear4U.db";
<<<<<<< HEAD
    final static int DATABASE_VERSION = 9;
=======
    final static int DATABASE_VERSION = 8;
>>>>>>> parent of 8078d35 (Merge pull request #12 from ThisisEdgar/Troy)
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
    final static String T2COL2 = "FirstName";
    final static String T2COL3 = "LastName";
    final static String T2COL4= "Address";
    final static String T2COL5= "Phone";
    final static String T2COL6= "Email";
    final static String T2COL7= "Password";
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
    final static String T4COL3 = "uid";
    final static String T4COL4= "Date";
    final static String T4COL5= "Services";

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
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
                T2COL6+" TEXT,"+T2COL7+" TEXT)";
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

    //methods to insert data
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

    public void addUserData(String first,String last, String address,
                            String phone, String email,  String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(T2COL2, first);
        values.put(T2COL3, last);
        values.put(T2COL4, address);
        values.put(T2COL5, phone);
        values.put(T2COL6, email);
        values.put(T2COL7, password);
        long l = sqLiteDatabase.insert(TABLE2_NAME, null, values);

        if(l == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show();
        }

    }

<<<<<<< HEAD
    public void addServiceData(String date,String services) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(T4COL4, date);
        values.put(T4COL5, services);

        long l = sqLiteDatabase.insert(TABLE4_NAME, null, values);

        if(l == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show();
        }

    }

=======
>>>>>>> parent of 8078d35 (Merge pull request #12 from ThisisEdgar/Troy)
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

        if (sqLiteDatabase != null){
            cursor= sqLiteDatabase.rawQuery(query,null);
        }return cursor;
    }

    public Cursor viewVehicleData(){
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

    public boolean deleteVehicle(int vid){
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
    void updateUser(int id, User user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T2COL1, user.getFirstName());
        values.put(T2COL2, user.getLastName());
        values.put(T2COL3, user.getAddress());
        values.put(T2COL3, user.getPhone());
        values.put(T2COL4, user.getEmail());
        values.put(T2COL5, user.getPassword());
        long result = db.update(TABLE2_NAME, values, "_id=?", new String[]{Integer.toString(id)});

        if(result == -1){

            Toast.makeText(context, "Failed to Update!", Toast.LENGTH_SHORT).show();
        }else{

            Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show();
        }
    }
    public Cursor viewProvidersByCity(String cityChosen){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE1_NAME +" WHERE "+T1COL6+ "=?";
        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{String.valueOf(cityChosen)});
        return cursor;
    }
    public void addAppointment(String user_id, String date,String provider_id,String service, String pickUpOrDropOff){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(T4COL2, provider_id);
        values.put(T4COL3, user_id);
        values.put(T4COL4, date);
        values.put(T4COL5, service);
        //add pickup or dropOff in the services table
        //values.put(T4COL6, pickUpOrDropOff);

        long l = sqLiteDatabase.insert(TABLE4_NAME, null, values);

        if(l == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show();
        }
    }

}
