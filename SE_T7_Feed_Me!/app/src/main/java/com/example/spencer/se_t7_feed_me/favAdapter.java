package com.example.spencer.se_t7_feed_me;

/**
 * Created by Daniel on 4/13/2015.
 */

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import android.content.Context;
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

/**
 * Created by Daniel on 3/11/2015.
 */
public class favAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<String> RestaurantsName = new ArrayList<String>();
    private ArrayList<String> RestaurantsAddress = new ArrayList<String>();
    private ArrayList<String> RestaurantsId = new ArrayList<String>();
    private String username;
    private String pass;
    private Context context;

    public favAdapter(String username, String pass, ArrayList<String> RestaurantsName, ArrayList<String> RestaurantsAddress, ArrayList<String>RestaurantsId, Context context) {
        this.RestaurantsName = RestaurantsName;
        this.RestaurantsAddress = RestaurantsAddress;
        this.RestaurantsId = RestaurantsId;
        this.username = username;
        this.pass = pass;
        this.context = context;
    }
    @Override
    public int getCount() {
        return RestaurantsName.size();
    }

    @Override
    public Object getItem(int position) {
        return RestaurantsName.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view==null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_target2,null);
        }
        TextView Name = (TextView)view.findViewById(R.id.List_Title);
        Name.setText(RestaurantsName.get(position));
        TextView Address = (TextView)view.findViewById(R.id.List_Address);
        Address.setText(RestaurantsAddress.get(position));
        Button findButton = (Button)view.findViewById(R.id.List_Find_Button);
        Button favoriteButton = (Button)view.findViewById(R.id.deleteButton);
        final String MapsOpen = "http://maps.google.com/maps?&daddr=" + RestaurantsAddress.get(position);

        findButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(MapsOpen));
                context.startActivity(myIntent);
            }

        });
        favoriteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                new connect().execute(position);
            }

        });

        return view;
    }
    class connect extends AsyncTask<Integer, String, Void>
    {
        InputStream is = null ;
        String result = "";
        protected void onPreExecute() {
        }
        @Override
        protected Void doInBackground(Integer... params) {
            String url_select = "http://feedmedb.netne.net/delete.php";

            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url_select);

            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("username",username));
            nameValuePairs.add(new BasicNameValuePair("pass",pass));
            nameValuePairs.add(new BasicNameValuePair("id",RestaurantsId.get(params[0])));
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();

                is =  httpEntity.getContent();

            } catch (Exception e) {

                Log.e("log_tag", "Error in http connection " + e.toString());
            }
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuilder sb = new StringBuilder();
                String line = "";
                while((line=br.readLine())!=null)
                {
                    sb.append(line+"\n");
                }
                is.close();
                result=sb.toString();

            } catch (Exception e) {
                // TODO: handle exception
                Log.e("log_tag", "Error converting result "+e.toString());
            }
            return null;
        }
        protected void onPostExecute(Void v) {

        }
    }
}

