package Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.faisalmovers.travels.bus.R;
import com.faisalmovers.travels.bus.WebMainActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.BookingHistoryModel;

public class BookingHistoryAdapter extends RecyclerView.Adapter<BookingHistoryAdapter.ViewHolder> {

    Context context;
    ArrayList<BookingHistoryModel> models;

    public BookingHistoryAdapter(Context context, ArrayList<BookingHistoryModel> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public BookingHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_booking_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final BookingHistoryAdapter.ViewHolder holder, final int position) {

        String arrivalTime =models.get(position).getBusTime() ;
        arrivalTime = timedate(arrivalTime);
       holder.busType.setText(arrivalTime);
       holder.opeatorname.setText(models.get(position).getTitle());
       holder.from.setText(models.get(position).getOrigin());
       holder.to.setText(models.get(position).getDestination());
       holder.date.setText(models.get(position).getBookingDate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              Intent i = new Intent(context, WebMainActivity.class);
              i.putExtra("weblinkid",models.get(position).getId());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView star;
        TextView busType,opeatorname,from,to,date;
        LinearLayout star_rating;

        public ViewHolder(View itemView) {
            super(itemView);


            busType = itemView.findViewById(R.id.busType);
            opeatorname = itemView.findViewById(R.id.opeatorname);
            from = itemView.findViewById(R.id.from);
            to = itemView.findViewById(R.id.to);
            date = itemView.findViewById(R.id.date);
        }
    }
    public String timedate (String time )
    {


        try {
            String _24HourTime = time;
            SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
            SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");
            Date _24HourDt = _24HourSDF.parse(_24HourTime);
            System.out.println(_24HourDt);
            time = _12HourSDF.format(_24HourDt).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return time;
    }
}
