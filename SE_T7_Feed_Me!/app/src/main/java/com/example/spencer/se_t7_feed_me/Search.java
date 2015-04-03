package com.example.spencer.se_t7_feed_me;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Vector;


public class Search extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        TextView welcome;
        welcome = (TextView) findViewById(R.id.welcomeText);

        if (getIntent().getStringExtra("name") != null)
            welcome.setText("Welcome " + getIntent().getStringExtra("name"));

        final Spinner spinner = (Spinner) findViewById(R.id.Search_City_Selector);
        ArrayList<String> cities = new ArrayList<String>();
        cities.add("Fayetteville, AR");
        cities.add("Little Rock, AR");
        cities.add("Springdale, AR");

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_layout,cities);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_layout);
        spinner.setAdapter(spinnerArrayAdapter);


        Button Search_Back = (Button) findViewById(R.id.Search_Back_Button);
        Search_Back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), StartPage.class);
                startActivityForResult(myIntent, 0);
            }

        });

        ImageButton Search_FastFood = (ImageButton) findViewById(R.id.Search_FastFood_Button);
        Search_FastFood.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), List_Test.class);
                myIntent.putExtra("city", spinner.getSelectedItem().toString());
                myIntent.putExtra("type", "Fast Food");
                startActivityForResult(myIntent, 0);
            }

        });

        ImageButton Search_Mexican = (ImageButton) findViewById(R.id.Search_MexicanFood_Button);
        Search_Mexican.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), List_Test.class);
                myIntent.putExtra("city", spinner.getSelectedItem().toString());
                myIntent.putExtra("type", "Mexican");
                startActivityForResult(myIntent, 0);
            }

        });

        ImageButton Search_Asian = (ImageButton) findViewById(R.id.Search_AsianFood_Button);
        Search_Asian.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), List_Test.class);
                myIntent.putExtra("city", spinner.getSelectedItem().toString());
                myIntent.putExtra("type", "Asian");
                startActivityForResult(myIntent, 0);
            }

        });

        ImageButton Search_Italian = (ImageButton) findViewById(R.id.Search_ItalianFood_Button);
        Search_Italian.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), List_Test.class);
                myIntent.putExtra("city", spinner.getSelectedItem().toString());
                myIntent.putExtra("type", "Italian");
                startActivityForResult(myIntent, 0);
            }

        });

        Button NewRest = (Button) findViewById(R.id.SubmitNewRest);
        NewRest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), NewRestRequest.class);
                startActivityForResult(myIntent, 0);
            }

        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
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
