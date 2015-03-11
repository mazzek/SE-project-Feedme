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
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class List_FastFood extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__fast_food);

        Button List_FastFood_Back = (Button) findViewById(R.id.List_FastFood_Back_Button);
        List_FastFood_Back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Search.class);
                startActivityForResult(myIntent, 0);
            }

        });

        ArrayList<String> RestaurantNames = new ArrayList<String>();
        ArrayList<String> RestaurantAddresses = new ArrayList<String>();

        if(getIntent().getStringExtra("city").equals("Fayetteville, AR"))
        {
        RestaurantNames.add("McDonald's");
        RestaurantAddresses.add("1963 West Martin Luther King Boulevard, Fayetteville, AR 72701");
        RestaurantNames.add("Chick-fil-A");
        RestaurantAddresses.add("1369 W Martin Luther King Blvd Fayetteville, AR 72701");
        }
        else if(getIntent().getStringExtra("city").equals("Little Rock, AR"))
        {
            RestaurantNames.add("Subway");
            RestaurantAddresses.add("2 St Vincent Circle, Little Rock, AR 72205");
            RestaurantNames.add("Chick-fil-A");
            RestaurantAddresses.add("425 West Capitol Avenue, Little Rock, AR 72701");
        }
        else if(getIntent().getStringExtra("city").equals("Springdale, AR"))
        {
            RestaurantNames.add("McDonald's");
            RestaurantAddresses.add("1260 E Robinson Ave, Springdale, AR");
            RestaurantNames.add("Wendy's");
            RestaurantAddresses.add("2000 S Pleasant St, Springdale, AR");
        }




        MyCustomAdapter adapter = new MyCustomAdapter(RestaurantNames, RestaurantAddresses, this);

        ListView lView = (ListView)findViewById(R.id.List_FastFood_ListView);
        lView.setAdapter(adapter);



        //TextView List_FastFood_Test = (TextView) findViewById(R.id.textView);
       // List_FastFood_Test.setText(getIntent().getStringExtra("city"));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list__fast_food, menu);
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
