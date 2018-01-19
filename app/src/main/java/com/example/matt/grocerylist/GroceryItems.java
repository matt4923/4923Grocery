package com.example.matt.grocerylist;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Matt on 10/8/2016.
 */

public class GroceryItems {
    private int itemNum;
    private String itemName;
    private String itemCategory;

    public GroceryItems(){
        itemNum = -1;
        Calendar hr = new GregorianCalendar();
        hr.getTime();
    }

    public int GetItemNum(){
        return itemNum;
    }

    public void SetItemNum(int i){
        itemNum = i;
    }

    public String GetItemName(){
        return itemName;
    }

    public void SetItemName(String s){
        itemName = s;
    }

    public String GetItemCategory(){
        return itemCategory;
    }

    public void SetItemCategory(String ic){
        itemCategory = ic;
    }

}
