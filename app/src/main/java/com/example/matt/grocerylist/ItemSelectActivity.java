package com.example.matt.grocerylist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class ItemSelectActivity extends AppCompatActivity {

    private GroceryItems m_Item = new GroceryItems();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_select);

        Intent in = getIntent();
        String msg = in.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView tv = new TextView(this);
        tv.setTextSize(40);
        tv.setText(msg);

        ViewGroup layout = (ViewGroup)findViewById(R.id.activity_item_select);
        layout.addView(tv);
    }
    public String ItemName;
    public String ItemCategory;

    public void AddItemToList(View view){
        EditText txtItemName = (EditText)findViewById(R.id.txtItemName);
        EditText txtItemCat = (EditText)findViewById(R.id.txtCategory);
        ItemName = txtItemName.getText().toString();
        ItemCategory = txtItemCat.getText().toString();
        SendResult();
        finish();
    }

    private void SendResult(){
        Intent in = new Intent();
        in.putExtra("txtName", ItemName);
        in.putExtra("txtCat", ItemCategory);
        setResult(RESULT_OK, in);
    }


/*    private void initTextChangedEvents(){
        final EditText itemName (EditText) findViewById(R.id.editName);
    }*/
}
