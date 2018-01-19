package com.example.matt.grocerylist;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.SyncStateContract;

/**
 * Created by Matt on 10/8/2016.
 */

public class GroceryDataSource {
    private SQLiteDatabase database;
    private GroceryDbHelper dbHelper;

    public GroceryDataSource(Context context){
        dbHelper = new GroceryDbHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public boolean InsertItem(GroceryItems item){
        boolean success = false;
        try{
            ContentValues initialValues = new ContentValues();
            initialValues.put("itemname", item.GetItemName());
            initialValues.put("itemcategory", item.GetItemCategory());
            success = database.insert("Items",null, initialValues) > 0;
        }catch(Exception ex){
            //nothing
        }
        return success;
    }

    public boolean UpdateItem(GroceryItems item){
        boolean success = false;
        try{
            Long rowId = Long.valueOf(item.GetItemNum());
            ContentValues updateValues = new ContentValues();

            updateValues.put("itemname", item.GetItemName());
            updateValues.put("itemcategory", item.GetItemCategory());
            success = database.update("Items", updateValues, "_id=" + rowId, null) > 0;
        }catch(Exception ex){
            //nothing
        }
        return success;
    }
}
