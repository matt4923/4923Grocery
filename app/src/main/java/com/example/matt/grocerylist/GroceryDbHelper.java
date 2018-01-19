package com.example.matt.grocerylist;

/**
 * Created by Matt on 10/8/2016.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.FileObserver;
import android.util.Log;

public class GroceryDbHelper extends SQLiteOpenHelper  {
    private static final String DATABASE_NAME = "Grocery.db";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_MAIN_LIST = "create table Items(ItemNum integer primary key autoincrement, ItemName text not null, ItemCategory text);";

    public GroceryDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE_MAIN_LIST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        Log.w(GroceryDbHelper.class.getName(), "Upgrading from version " + oldVersion + " to " + newVersion + " destroys all old data");
        db.execSQL("Drop table if exists Items");
        onCreate(db);
    }
}
