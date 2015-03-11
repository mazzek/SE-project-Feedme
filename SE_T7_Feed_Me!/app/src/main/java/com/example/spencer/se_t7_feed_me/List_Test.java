package com.example.spencer.se_t7_feed_me;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.content.Context;


import java.util.ArrayList;


public class List_Test extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__test);
        ArrayList<String> RestaurantNames = new ArrayList<String>();
        ArrayList<String> RestaurantAddresses = new ArrayList<String>();
        RestaurantNames.add("DummyName1");
        RestaurantAddresses.add("3616 Mall Ave, Fayetteville, AR 72703");
        RestaurantNames.add("DummyName2");
        RestaurantAddresses.add("3616 Mall Ave, Fayetteville, AR 72703");
        MyCustomAdapter adapter = new MyCustomAdapter(RestaurantNames, RestaurantAddresses, this);

        ListView lView = (ListView)findViewById(R.id.listView2);
        lView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list__test, menu);
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
}
