package com.example.matt.grocerylist;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.io.Console;
import java.util.Arrays;

import static com.example.matt.grocerylist.R.id.lstMainList;

public class MainActivity extends AppCompatActivity {
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    public static final String EXTRA_MESSAGE = "testing yo";

    //private ExpandableListView m_ItemsList;

    //private ArrayList<ExpandListGroup> m_ItemsListArray = new ArrayList<>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
       // CreateDatabase();

        //http://www.dreamincode.net/forums/topic/270612-how-to-get-started-with-expandablelistview
        m_ItemsList = (ListView)findViewById(R.id.lstMainList);

        GetListExistingItems();
    }

    private ListView m_ItemsList;
    private ArrayAdapter<String> m_ItemsAdapter;

    private void GetListExistingItems() {
        String[] items = new String[] {"Apples", "Bread", "Bananas", "Chicken", "Waffles"};
        ArrayList<String> itemsList = new ArrayList<>();
        itemsList.addAll(Arrays.asList((items)));

        m_ItemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemsList);
        m_ItemsAdapter.add("Fruit");
        m_ItemsAdapter.add("More Fruit");
        m_ItemsAdapter.add("Grains");

        m_ItemsList.setAdapter(m_ItemsAdapter);
    }

    private void CreateDatabase() {
        SQLiteDatabase myDb = openOrCreateDatabase("/sdcard/GroceryList",MODE_PRIVATE,null);
        myDb.execSQL("CREATE TABLE IF NOT EXISTS Items(ItemName VARCHAR(50),ItemCategory VARCHAR(50));");
        myDb.execSQL("CREATE TABLE IF NOT EXISTS Settings(SettingName VARCHAR(50),SettingValue VARCHAR(50));");
        myDb.execSQL("INSERT INTO Items VALUES('bananas','fruit');");
        myDb.execSQL("INSERT INTO Items VALUES('apples','fruit');");
        myDb.execSQL("INSERT INTO Items VALUES('rolls','breads');");
        Cursor m_ItemList = myDb.rawQuery("SELECT ItemName From Items", null);
        try {
            while (m_ItemList.moveToNext()) {
                int i = 1;
            }
        } finally {
            m_ItemList.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void StartItemAdditionActivity(View view) {
        Intent in = new Intent(this, ItemSelectActivity.class);
        EditText item = (EditText) findViewById(R.id.txtItemName);
        EditText itemCat = (EditText) findViewById(R.id.txtCategory);

        //getIntent().putExtra(EXTRA_MESSAGE, message);
        startActivityForResult(in, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent in){
        super.onActivityResult(requestCode, resultCode, in);
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                String NewItemName = in.getStringExtra("txtName");
                String NewItemCat = in.getStringExtra("txtCat");

                m_ItemsAdapter.add(NewItemName);
                m_ItemsAdapter.add(NewItemCat);
            }
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
