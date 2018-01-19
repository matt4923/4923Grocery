package com.example.matt.grocerylist;
import java.util.ArrayList;
/**
 * Created by Matt on 10/9/2016.
 */

public class ExpandListGroup {
    private String m_Item;
    private ArrayList<ExpandListChild> m_ItemDescription;

    public String GetItem(){
        return m_Item;
    }

    public void SetItem(String Item){
        m_Item = Item;
    }

    public ArrayList<ExpandListChild> GetDescription(){
        return m_ItemDescription;
    }

    public void SetDescription(ArrayList<ExpandListChild> Desc){
        m_ItemDescription = Desc;
    }
}
