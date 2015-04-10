package com.example.spencer.se_t7_feed_me;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import java.util.ArrayList;
import android.util.Log;
import java.io.InputStream;
import android.widget.EditText;
import android.app.Activity;
import java.io.InputStreamReader;


public class NewRestRequest extends Activity {
    Spinner spinner;
    Spinner spin2;
    EditText et;
    EditText et1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_rest_request);
        Button NewRest_Back = (Button) findViewById(R.id.NewRestBack);
        NewRest_Back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Search.class);
                startActivityForResult(myIntent, 0);

            }
        });

        spinner = (Spinner) findViewById(R.id.CityName);
        spin2 = (Spinner) findViewById(R.id.FoodType);
        et = (EditText) findViewById(R.id.NewRestName);
        et1 = (EditText) findViewById(R.id.NewRestAddress);

        ArrayList<String> cities = new ArrayList<String>();
        cities.add("Fayetteville, AR");
        cities.add("Little Rock, AR");
        cities.add("Springdale, AR");
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_layout,cities);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_layout);
        spinner.setAdapter(spinnerArrayAdapter);


        ArrayList<String> foods = new ArrayList<String>();
        foods.add("Italian");
        foods.add("Mexican");
        foods.add("Asian");
        foods.add("Fast Food");
        ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_layout,foods);
        spinnerArrayAdapter1.setDropDownViewResource(R.layout.spinner_layout);
        spin2.setAdapter(spinnerArrayAdapter1);



        Button Submit = (Button) findViewById(R.id.NewRestSubmitButton);
        Submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                new connect().execute();

            }
        });


    }

    class connect extends AsyncTask<String, String, Void>
    {
        HttpClient httpClient;
        HttpPost httpPost;
        InputStream is;

    protected void onPreExecute() {
    }
    @Override
    protected Void doInBackground(String... params) {
        is = null ;
        String url_select = "http://feedmedb.netne.net/insert.php";

        httpClient = new DefaultHttpClient();
        httpPost = new HttpPost(url_select);
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("location",spinner.getSelectedItem().toString()));
        nameValuePairs.add(new BasicNameValuePair("type",spin2.getSelectedItem().toString()));
        nameValuePairs.add(new BasicNameValuePair("name",et.getText().toString()));
        nameValuePairs.add(new BasicNameValuePair("address",et1.getText().toString()));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();

            is =  httpEntity.getContent();

        } catch (Exception e) {

            Log.e("log_tag", "Error in http connection " + e.toString());
        }
        return null;
    }
    protected void onPostExecute(Void v) {

    }
}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_rest_request, menu);
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
