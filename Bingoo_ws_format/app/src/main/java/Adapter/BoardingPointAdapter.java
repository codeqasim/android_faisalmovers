package Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.faisalmovers.travels.bus.Bingobus32Activity;
import com.faisalmovers.travels.bus.Bingobus_Last_StepActivity;
import com.faisalmovers.travels.bus.ProfilePerson;
import com.faisalmovers.travels.bus.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.Bingobus7Model;
import model.BoardingPointModel;

import static android.content.Context.MODE_PRIVATE;

public class BoardingPointAdapter extends RecyclerView.Adapter<BoardingPointAdapter.ViewHolder> {


    int myPos = 5;
    Context context;
    ArrayList<BoardingPointModel> models;
    Bingobus7Model bingobus7Model;
    ArrayList<String> arrayList = new ArrayList<>();

    public BoardingPointAdapter(Context context, ArrayList<BoardingPointModel> models , Bingobus7Model bingobus7Model) {
        this.context = context;
        this.models = models;
        this.bingobus7Model =bingobus7Model;


    }
    @NonNull
    @Override
    public BoardingPointAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_boardingpoint, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final BoardingPointAdapter.ViewHolder holder, final int position) {
        final BoardingPointModel lists = models.get(position);
        holder.city.setText(lists.getCity());
        holder.time.setText(lists.getTime());
        holder.location.setImageResource(lists.getLocation());



        if (myPos == position) {
            holder.location.setImageResource(R.drawable.ic_verified);
        } else {
            holder.location.setImageResource(R.drawable.ic_markerlocation);
        }



        arrayList = bingobus7Model.getBoardingPointsid();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               myPos = position;


              String id = arrayList.get(position);

               notifyDataSetChanged();
                SharedPreferences pref = context.getSharedPreferences("MyPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
              editor.putString("city", holder.city.getText().toString()+ " - "+ lists.getTime());
                editor.putString("boardingpointid", id);// Storing string name
              editor.commit();

               /*Intent i = new Intent(context, Bingobus32Activity.class);
               context.startActivity(i);*/


                Boolean nectcheckbuuton= pref.getBoolean("Check_Login", false);

                if(nectcheckbuuton ==false)
                {
                    Intent i = new Intent(context, ProfilePerson.class);
                    i.putExtra("sampleObject", bingobus7Model);
                    i.putExtra("vlaue", "2");
                    context.startActivity(i);




                }else {


                    Intent i = new Intent(context, Bingobus_Last_StepActivity.class);
                    i.putExtra("sampleObject", bingobus7Model);
                    context.startActivity(i);
                }



            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView city, time;
        ImageView location;

        public ViewHolder(View itemView) {
            super(itemView);

            city = itemView.findViewById(R.id.city);
            time = itemView.findViewById(R.id.time);
            location = itemView.findViewById(R.id.location);

        }
    }
}