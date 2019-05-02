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

import com.faisalmovers.travels.bus.BingoBusBookingDetailActivity;
import com.faisalmovers.travels.bus.R;

import java.util.ArrayList;

import model.Bingobus7Model;
import model.BookingHistoryModel;

public class BookingHistoryAdapter extends RecyclerView.Adapter<BookingHistoryAdapter.ViewHolder> {

    Context context;
    ArrayList<Bingobus7Model> models;

    public BookingHistoryAdapter(Context context, ArrayList<Bingobus7Model> models) {
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

       holder.busType.setText(models.get(position).getBusType());
       holder.opeatorname.setText(models.get(position).getOperator());
       holder.from.setText(models.get(position).getFromCity());
       holder.to.setText(models.get(position).getToCity());
       holder.date.setText(models.get(position).getDatetime());
      /*  if (position == 0) {
            holder.ratenow.setVisibility(View.VISIBLE);
            holder.star_rating.setVisibility(View.GONE);
        }*/
       /* holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              Intent i = new Intent(context, BingoBusBookingDetailActivity.class);
                context.startActivity(i);
            }
        });*/
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
}
