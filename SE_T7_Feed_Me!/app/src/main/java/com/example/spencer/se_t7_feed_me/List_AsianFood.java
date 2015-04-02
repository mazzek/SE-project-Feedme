package com.example.spencer.se_t7_feed_me;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


public class List_AsianFood extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__asian_food);

        Button List_AsianFood_Back = (Button) findViewById(R.id.List_AsianFood_Back_Button);
        List_AsianFood_Back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Search.class);
                startActivityForResult(myIntent, 0);
            }

        });

        ArrayList<String> RestaurantNames = new ArrayList<String>();
        ArrayList<String> RestaurantAddresses = new ArrayList<String>();
        if(getIntent().getStringExtra("city").equals("Fayetteville, AR"))
        {
            RestaurantNames.add("JD China");
            RestaurantAddresses.add("1740 Martin Luther King Jr Blvd, Fayetteville, AR 72701");
            RestaurantNames.add("KJ's Sushi & Korean BBQ");
            RestaurantAddresses.add("3223 College Ave, Fayetteville, AR 72703");
        }
        else if(getIntent().getStringExtra("city").equals("Little Rock, AR"))
        {
            RestaurantNames.add("Igibon Japanese Food House");
            RestaurantAddresses.add("11121 N Rodney Parham Rd #13A, Little Rock, AR 72212");
            RestaurantNames.add("Fu Lin");
            RestaurantAddresses.add(" 200 N Bowman Rd #17, Little Rock, AR 72211");
        }
        else if(getIntent().getStringExtra("city").equals("Springdale, AR"))
        {
            RestaurantNames.add("Far East Chinese Restaurant");
            RestaurantAddresses.add("812 N Thompson St #11, Springdale, AR 72764");
            RestaurantNames.add("Jade China Restaurant");
            RestaurantAddresses.add("1046 W Sunset Ave, Springdale, AR 72764");
        }

        MyCustomAdapter adapter = new MyCustomAdapter(RestaurantNames, RestaurantAddresses, this);

        ListView lView = (ListView)findViewById(R.id.List_AsianFood_ListView);
        lView.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list__asian_food, menu);
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
