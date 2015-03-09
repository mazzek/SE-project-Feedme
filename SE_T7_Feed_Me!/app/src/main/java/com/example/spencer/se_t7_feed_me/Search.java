package com.example.spencer.se_t7_feed_me;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class Search extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

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
                Intent myIntent = new Intent(view.getContext(), List_FastFood.class);
                startActivityForResult(myIntent, 0);
            }

        });

        ImageButton Search_Mexican = (ImageButton) findViewById(R.id.Search_MexicanFood_Button);
        Search_Mexican.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), List_MexicanFood.class);
                startActivityForResult(myIntent, 0);
            }

        });

        ImageButton Search_Asian = (ImageButton) findViewById(R.id.Search_AsianFood_Button);
        Search_Asian.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), List_AsianFood.class);
                startActivityForResult(myIntent, 0);
            }

        });

        ImageButton Search_Italian = (ImageButton) findViewById(R.id.Search_ItalianFood_Button);
        Search_Italian.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), List_ItalianFood.class);
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
