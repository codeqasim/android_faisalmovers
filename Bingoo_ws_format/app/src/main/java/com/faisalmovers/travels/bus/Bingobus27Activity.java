package com.faisalmovers.travels.bus;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import model.Amenities;
import model.Bingobus7Model;
import util.Url;

public class Bingobus27Activity extends Url {


    ArrayList<Amenities> amenities = new ArrayList<>();
    ImageView back;
    Bingobus7Model bingobus7Model;
    Intent da;
    ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bingobus27);

        da = getIntent();
        bingobus7Model = (Bingobus7Model)da.getSerializableExtra("sampleObject");
        back = (ImageView) findViewById(R.id.back);
        listview = (ListView) findViewById(R.id.amenitieslist);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });


       for (int i= 0; i< bingobus7Model.getAmenities_array_list().size();i++)
       {
           Amenities amenities1 = new Amenities();
           String d = bingobus7Model.getAmenities_array_list().get(i);
           String image = bingobus7Model.getAmenities_array_listimage().get(i);
           amenities1.setName(d);
           amenities1.setImage(amenitiesimage+image);
           amenities.add(amenities1);
       }

        CustomAdapter customAdapter= new CustomAdapter(amenities,getApplicationContext());
        listview.setAdapter(customAdapter);


    }



    public class CustomAdapter extends ArrayAdapter<Amenities> {

        private ArrayList<Amenities> dataSet;
        Context mContext;

        // View lookup cache
        private class ViewHolder {
            TextView txtName;
            ImageView info1;
        }

        public CustomAdapter(ArrayList<Amenities> data, Context context) {
            super(context, R.layout.amenitieslist_row, data);
            this.dataSet = data;
            this.mContext=context;

        }



        private int lastPosition = -1;

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            Amenities dataModel = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            ViewHolder viewHolder; // view lookup cache stored in tag

            final View result;

            if (convertView == null) {

                viewHolder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.amenitieslist_row, parent, false);
                viewHolder.txtName = (TextView) convertView.findViewById(R.id.texttv);
                viewHolder.info1 = (ImageView) convertView.findViewById(R.id.imagetext);

                result=convertView;

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
                result=convertView;
            }


            lastPosition = position;
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.loading);
            requestOptions.error(R.drawable.loading);
            viewHolder.txtName.setText(dataModel.getName());
            Glide.with(getApplicationContext()).load(dataModel.getImage()).apply(requestOptions).into(viewHolder.info1);

            return convertView;
        }
    }


}
