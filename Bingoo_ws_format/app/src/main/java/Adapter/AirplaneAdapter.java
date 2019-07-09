package Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import model.AbstractItem;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.faisalmovers.travels.bus.EdgeItem;
import com.faisalmovers.travels.bus.OnSeatSelected;
import com.faisalmovers.travels.bus.R;
import com.faisalmovers.travels.bus.SelectableAdapter;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.seatModel;
import util.Url;
import util.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class AirplaneAdapter extends SelectableAdapter<RecyclerView.ViewHolder> {

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private OnSeatSelected mOnSeatSelected;
    Gson gson ;
    ProgressBar progressBar2;

    private int count = 0;

    private static class EdgeViewHolder extends RecyclerView.ViewHolder {

        ImageView imgSeat;
        private final ImageView imgSeatSelected;
       private  ImageView imgBooked;


        public EdgeViewHolder(View itemView) {
            super(itemView);
            imgSeat = (ImageView) itemView.findViewById(R.id.img_seat);
            imgSeatSelected = (ImageView) itemView.findViewById(R.id.img_seat_selected);
         //  imgBooked = (ImageView) itemView.findViewById(R.id.img_seat_booked);

        }

    }




    private static class EmptyViewHolder extends RecyclerView.ViewHolder {

        public EmptyViewHolder(View itemView) {
            super(itemView);
        }

    }

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private List<AbstractItem> mItems;

    public AirplaneAdapter(Context context, List<AbstractItem> items,ProgressBar progressBar) {
        mOnSeatSelected = (OnSeatSelected) context;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mItems = items;
        progressBar2 = progressBar;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == AbstractItem.TYPE_EDGE) {
            View itemView = mLayoutInflater.inflate(R.layout.list_item_seat, parent, false);
            return new EdgeViewHolder(itemView);

        } else {
            View itemView = new View(mContext);
            return new EmptyViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {
        int type = mItems.get(position).getType();
        if (type == AbstractItem.TYPE_EDGE) {
            final EdgeItem item = (EdgeItem) mItems.get(position);
            final EdgeViewHolder holder = (EdgeViewHolder) viewHolder;

            final seatModel model = item.getLabel();
            holder.imgSeat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(model.getSeat_status().equals("Empty")){


                        if(isSelected(position)){
                            toggleSelection(position);
                            mOnSeatSelected.onSeatSelected(item.getLabel());


                        }else{



                            if(getSelectedItemCount()>5){

                                Toast.makeText(mContext,"Max Seat Is 6",Toast.LENGTH_LONG).show();

                            }else{


                                Url url = new Url();
                                SharedPreferences  pref = mContext.getSharedPreferences("MyPref", MODE_PRIVATE);
                                String origin =pref.getString("fromcityid", null);
                                String destination =pref.getString("tocityid", null);
                                seathold(url.seatHold,model.getSeat_id(),origin,destination,position,item, holder);

                             /*   toggleSelection(position);
                                mOnSeatSelected.onSeatSelected(item.getLabel());*/
                              //  holder.imgSeat.setImageResource(isSelected(position) ? R.drawable.green_sit : R.drawable.green_sit);
                            }

                        }
                    }else
                    {
                       // Utils.showErrorToast(mContext, "Seat already hold");
                    }
                }
            });

            if(model.getSeat_status().equals("Empty"))
            {
                holder.imgSeat.setImageResource(isSelected(position) ? R.drawable.green_sit : R.drawable.sitoffwhite);

            }
            else if(model.getSeat_status().equals("Reserved"))
            {


               if (model.getGender().equals("Female"))
               {
                   holder.imgSeat.setImageResource(isSelected(position) ? R.drawable.pink_seat : R.drawable.pink_seat);
               }else
               {
                   holder.imgSeat.setImageResource(isSelected(position) ? R.drawable.red_seat : R.drawable.red_seat);
               }

            }

        }
    }


    public void seathold(final String url, final String seat_id , final String origin, final String destination, final int position, final EdgeItem item, final EdgeViewHolder holder )
    {
        gson = new Gson();
        progressBar2.setVisibility(View.VISIBLE);
        mRequestQueue = Volley.newRequestQueue(mContext);
        mStringRequest  = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar2.setVisibility(View.GONE);

                Log.d("adapterseatidtest", "adbhold /"+response);


                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray Data = jsonObject.getJSONArray("Data");

                      JSONObject jsonObject1 = Data.getJSONObject(0);
                        if (jsonObject1.get("Status").equals("success"))
                        {
                            toggleSelection(position);
                            mOnSeatSelected.onSeatSelected(item.getLabel());
                            //Utils.showErrorToast(mContext, "Seat  hold");

                        }else
                        {
                            holder.imgSeat.setImageResource(isSelected(position) ? R.drawable.green_sit : R.drawable.yellow);
                            Utils.showErrorToast(mContext, "Not Available");

                        }








                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("seatholdseathold","error/" +error);
                Utils.showErrorToast(mContext,"server issue");
               // progressBar2.setVisibility(View.GONE);
                //This code is executed if there is an error.
                //inProgress=false;
            }
        }) {
            @Override
            protected Map<String, String> getParams()  {



                Map<String, String> params = new HashMap<String, String>();
                params.put("seat_id",seat_id);
                params.put("origin",origin);
                params.put("destination",destination);
                String json  =gson.toJson(params) ;
                Log.d("seatholdseathold",json);
                return params;


            }
        };

        mStringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(mStringRequest);
    }


}
