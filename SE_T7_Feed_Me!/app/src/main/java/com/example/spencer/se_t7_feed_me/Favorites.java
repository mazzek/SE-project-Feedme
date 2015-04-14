package com.example.spencer.se_t7_feed_me;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Favorites extends Activity {

    ArrayList<String> RestaurantNames = new ArrayList<String>();
    ArrayList<String> RestaurantAddresses = new ArrayList<String>();
    ArrayList<String> RestaurantsId = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        TextView headr;
        headr = (TextView) findViewById(R.id.textView3);

        if (getIntent().getStringExtra("name") != null)
            headr.setText(getIntent().getStringExtra("name") + "'s Favorites");

        Button backButton = (Button) findViewById(R.id.fav_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Search.class);
                startActivityForResult(myIntent, 0);
            }

        });

        new connect().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_favorites, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

   class connect extends AsyncTask<String, String, Void> {
        private ProgressDialog progressDialog = new ProgressDialog(Favorites.this);
        InputStream is = null;
        String result = "";

        protected void onPreExecute() {
            progressDialog.setMessage("Retrieving Restaurants...");
            progressDialog.show();
            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface arg0) {
                    connect.this.cancel(true);
                }
            });
        }

        @Override
        protected Void doInBackground(String... params) {
            String url_select = "http://feedmedb.netne.net/GetFav.php";

            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url_select);

            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("username", getIntent().getStringExtra("name")));
            nameValuePairs.add(new BasicNameValuePair("pass", getIntent().getStringExtra("pass")));
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();

                is = httpEntity.getContent();

            } catch (Exception e) {

                Log.e("log_tag", "Error in http connection " + e.toString());
            }
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuilder sb = new StringBuilder();
                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                is.close();
                result = sb.toString();

            } catch (Exception e) {
                // TODO: handle exception
                Log.e("log_tag", "Error converting result " + e.toString());
            }
            return null;
        }

        protected void onPostExecute(Void v) {

            try {
                JSONArray Jarray = new JSONArray(result);
                for (int i=0; i < Jarray.length(); i++) {
                    JSONObject Jsonobject = null;
                    Jsonobject = Jarray.getJSONObject(i);
                    RestaurantNames.add(Jsonobject.getString("Name"));
                    RestaurantAddresses.add(Jsonobject.getString("Address"));
                    RestaurantsId.add(Jsonobject.getString("id"));
                }

                this.progressDialog.dismiss();
                //MyCustomAdapter adapter = new MyCustomAdapter(RestaurantNames, RestaurantAddresses, Favorites.this);
                favAdapter adapter = new favAdapter(getIntent().getStringExtra("name"), getIntent().getStringExtra("pass"),RestaurantNames, RestaurantAddresses, RestaurantsId, Favorites.this);
                ListView lView = (ListView) findViewById(R.id.listView);
                lView.setAdapter(adapter);
            } catch (Exception e) {
                // TODO: handle exception
                Log.e("log_tag", "Error parsing data " + e.toString());
            }
        }
    }
}

