package com.example.ciphershield;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "users";
    private static final int DATABASE_VERSION = 1;
    public DbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String query = "CREATE TABLE passwords (id INTEGER PRIMARY KEY AUTOINCREMENT, application_name TEXT, username TEXT, password TEXT)";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int old_version, int new_version){
        db.execSQL("DROP TABLE IF EXISTS "+DATABASE_NAME);
        onCreate(db);
    }

    public void addPassword(String applicationName, String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("application_name", applicationName);
        values.put("username", username);
        values.put("password", password);
        db.insert("passwords", null, values);
        db.close();
    }

    public Cursor viewPasswords(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM passwords";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    public int getPasswordId(String appName, String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT id FROM passwords WHERE application_name = ? AND username = ? LIMIT 1";
        Cursor cursor = db.rawQuery(query, new String[]{appName, username});

        int id = -1; // or any sentinel value you prefer
        if (cursor.moveToFirst()) {
            id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
        }
        cursor.close();
        return id;
    }

    public boolean deletePassword(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("passwords", "id=?", new String[]{id});
        return result > 0;
    }

    public boolean updatePassword(String applicationName, String username, String password, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("application_name", applicationName);
        values.put("username", username);
        values.put("password", password);
        int result = db.update("passwords",values, "id=?", new String[]{String.valueOf(id)});
        return result > 0;
    }
}
