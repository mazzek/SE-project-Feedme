package com.example.spencer.se_t7_feed_me;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.InputStream;
import java.util.ArrayList;


public class AccountCreate extends Activity {

    EditText et;
    EditText et1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_create);
        et = (EditText) findViewById(R.id.Username);
        et1 = (EditText) findViewById(R.id.Password);
        Button Submit = (Button) findViewById(R.id.NewAccountSubmit);
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
            String url_select = "http://feedmedb.netne.net/account_insert.php";

            httpClient = new DefaultHttpClient();
            httpPost = new HttpPost(url_select);
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("username",et.getText().toString()));
            nameValuePairs.add(new BasicNameValuePair("pass",et1.getText().toString()));
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
        getMenuInflater().inflate(R.menu.menu_account_create, menu);
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
