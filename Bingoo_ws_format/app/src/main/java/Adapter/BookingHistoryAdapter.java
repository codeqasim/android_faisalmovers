package Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fm.travels.bus.R;

import java.util.ArrayList;

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

        holder.star.setImageResource(models.get(position).getStar());
        holder.rate.setText(models.get(position).getRate());

        if (position == 0) {
            holder.ratenow.setVisibility(View.VISIBLE);
            holder.star_rating.setVisibility(View.GONE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               /* Intent i = new Intent(context, BingoBusBookingDetailActivity.class);
                context.startActivity(i);*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView star;
        TextView rate,ratenow;
        LinearLayout star_rating;

        public ViewHolder(View itemView) {
            super(itemView);

            star = itemView.findViewById(R.id.star);
            rate = itemView.findViewById(R.id.rate);
            ratenow = itemView.findViewById(R.id.ratenow);
            star_rating = itemView.findViewById(R.id.star_rating);
        }
    }
}
