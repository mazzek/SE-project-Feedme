package com.example.spencer.se_t7_feed_me;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import java.util.ArrayList;
import android.content.Context;
import android.widget.TextView;

/**
 * Created by Daniel on 3/11/2015.
 */
public class MyCustomAdapter extends BaseAdapter implements ListAdapter{
    private ArrayList<String> RestaurantsName = new ArrayList<String>();
    private ArrayList<String> RestaurantsAddress = new ArrayList<String>();
    private Context context;

    public MyCustomAdapter(ArrayList<String> RestaurantsName, ArrayList<String> RestaurantsAddress, Context context) {
        this.RestaurantsName = RestaurantsName;
        this.RestaurantsAddress = RestaurantsAddress;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view==null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_target,null);
        }
        TextView Name = (TextView)view.findViewById(R.id.List_FastFood_Title);
        Name.setText(RestaurantsName.get(position));
        TextView Address = (TextView)view.findViewById(R.id.List_FastFood_Address);
        Name.setText(RestaurantsAddress.get(position));
        Button findButton = (Button)view.findViewById(R.id.List_FastFood_Find_Button);
        final String MapsOpen = "http://maps.google.com/maps?&daddr=" + RestaurantsAddress.get(position);

        findButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(MapsOpen));
                context.startActivity(myIntent);
            }

        });


        return view;
    }
}
