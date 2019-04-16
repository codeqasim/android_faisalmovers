package Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.faisalmovers.travels.bus.Bingobus33Activity;
import com.faisalmovers.travels.bus.R;

import java.util.ArrayList;

import model.Bingobus7Model;

public class Bingobus7Adapter extends RecyclerView.Adapter<Bingobus7Adapter.ViewHolder> {

    Context context;
    ArrayList<Bingobus7Model> models;




    public Bingobus7Adapter(Context context, ArrayList<Bingobus7Model> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public Bingobus7Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bingobus7, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Bingobus7Adapter.ViewHolder holder, final int position) {


        holder.acsleeper.setText(models.get(position).getBusType());
        holder.fare.setText("Rs "+models.get(position).getFare());
        holder.seat.setText(models.get(position).getSeats()+" Seats available");
        holder.dEPARTURE.setText(models.get(position).getDepartureTime());
        holder.arrivalTime.setText(models.get(position).getArrivalTime());
        holder.QuerydepartureTime.setText(models.get(position).getQuerydepartureTime());
        if(position == 0) {
            holder.location.setVisibility(View.GONE);
        }

        if(position == 2) {
            holder.location.setVisibility(View.GONE);
        }
        if(position == 3) {
            holder.location.setVisibility(View.GONE);
        }

        if(position == 4) {
            holder.location.setVisibility(View.GONE);
        }

        if(position == 5) {
            holder.location.setVisibility(View.GONE);
        }

        if(position == 6) {
            holder.location.setVisibility(View.GONE);
        }

       holder.bus_booking.setOnClickListener(new View.OnClickListener() {
          @Override
            public void onClick(View v) {
                Intent i = new Intent(context,Bingobus33Activity.class);
                ((Activity)context).finish();
                ((Activity)context).overridePendingTransition(R.anim.right_in, R.anim.left_out);
                context.startActivity(i);

              //Intent i = new Intent(context, Bingobus33Activity.class);
              //context.startActivity(i);
            }
       });

    }

    @Override
    public int getItemCount() {
        return models.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView acsleeper,fare,seat,dEPARTURE,arrivalTime,QuerydepartureTime;
        ImageView location;
        CardView bus_booking;

        public ViewHolder(View itemView) {
            super(itemView);

            acsleeper = itemView.findViewById(R.id.acsleeper);
            fare  =  itemView.findViewById(R.id.fare);
            seat  =  itemView.findViewById(R.id.seat);
            QuerydepartureTime  =  itemView.findViewById(R.id.QuerydepartureTime);

            dEPARTURE  =  itemView.findViewById(R.id.dEPARTURE);
            location = itemView.findViewById(R.id.location);
            bus_booking = itemView.findViewById(R.id.bus_booking);
            arrivalTime = itemView.findViewById(R.id.arrivalTime);
        }
    }
}
