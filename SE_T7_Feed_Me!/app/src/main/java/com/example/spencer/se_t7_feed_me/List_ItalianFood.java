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


public class List_ItalianFood extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__italian_food);

        Button List_ItalianFood_Back = (Button) findViewById(R.id.List_ItalianFood_Back_Button);
        List_ItalianFood_Back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Search.class);
                startActivityForResult(myIntent, 0);
            }

        });

        ArrayList<String> RestaurantNames = new ArrayList<String>();
        ArrayList<String> RestaurantAddresses = new ArrayList<String>();
        if(getIntent().getStringExtra("city").equals("Fayetteville, AR"))
        {
            RestaurantNames.add("Olive Garden");
            RestaurantAddresses.add("3616 Mall Ave, Fayetteville, AR 72703");
            RestaurantNames.add("Noodles");
            RestaurantAddresses.add("3748 Mall Ave, Fayetteville, AR 72703");
        }
        else if(getIntent().getStringExtra("city").equals("Little Rock, AR"))
        {
            RestaurantNames.add("Ciao Italian Restaurant");
            RestaurantAddresses.add("405 W 7th St, Little Rock, AR 72201");
            RestaurantNames.add("Bruno's Little Italy");
            RestaurantAddresses.add("310 Main St #101, Little Rock, AR 72201");
        }
        else if(getIntent().getStringExtra("city").equals("Springdale, AR"))
        {
            RestaurantNames.add("Guido's Pizza");
            RestaurantAddresses.add("4275 S Thompson St, Springdale, AR 72764");
            RestaurantNames.add("Joe's Pizza & Pasta");
            RestaurantAddresses.add("4224 W Sunset Ave, Springdale, AR 72762");
        }

        MyCustomAdapter adapter = new MyCustomAdapter(RestaurantNames, RestaurantAddresses, this);

        ListView lView = (ListView)findViewById(R.id.List_ItalianFood_ListView);
        lView.setAdapter(adapter);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list__italian_food, menu);
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
