package com.example.bearit4u;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Project2.db";
    private static final int DATABASE_VERSION =1;

    private static final String TABLE_NAME= "registered_users";
    private static final String COLUMN_USER_ID= "_id";
    private static final String COLUMN_FIRSTNAME= "First_Name";
    private static final String COLUMN_LASTNAME= "Last_Name";
    private static final String COLUMN_ADDRESS= "Address";
    private static final String COLUMN_PHONE= "Phone";
    private static final String COLUMN_EMAIL= "Email";
    private static final String COLUMN_PASSWORD= "Password";



    DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       String query =
               "CREATE TABLE " + TABLE_NAME +
                       " (" + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                       COLUMN_FIRSTNAME + " TEXT, " +
                       COLUMN_LASTNAME + " TEXT, " +
                       COLUMN_ADDRESS + " TEXT, " +
                       COLUMN_PHONE + " INTEGER," +
                       COLUMN_EMAIL + " TEXT, " +
                       COLUMN_PASSWORD + " TEXT);";
       db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    void addUser(String firstName, String lastName, String address, long phone, String email,
                 String password){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv2 = new ContentValues();
        // COLUmN ID NOT NEEDED AS ITS GONNA INCREMENT AUTOMATICALLY

        cv2.put(COLUMN_FIRSTNAME, firstName);
        cv2.put(COLUMN_LASTNAME, lastName);
        cv2.put(COLUMN_ADDRESS, address);
        cv2.put(COLUMN_PHONE, phone);
        cv2.put(COLUMN_EMAIL, email );
        cv2.put(COLUMN_PASSWORD, password);
        long result = db.insert(TABLE_NAME, null, cv2);

        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show();
        }

    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){

            cursor= db.rawQuery(query,null);
        }return cursor;



    }

    void updateData(String row_id, String firstName, String lastName,
                    String address, String phone, String email,
                    String password){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_FIRSTNAME, firstName);
        cv.put(COLUMN_LASTNAME, lastName);
        cv.put(COLUMN_ADDRESS, address);
        cv.put(COLUMN_PHONE, phone);
        cv.put(COLUMN_EMAIL, email);
        cv.put(COLUMN_PASSWORD, password);
        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});

        if(result == -1){

            Toast.makeText(context, "Failed to Update!", Toast.LENGTH_SHORT).show();
        }else{

            Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show();
        }
    }


}
