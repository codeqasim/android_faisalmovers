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

import com.fm.travels.bus.Bingobus32Activity;
import com.fm.travels.bus.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.BoardingPointModel;

import static android.content.Context.MODE_PRIVATE;

public class BoardingPointAdapter extends RecyclerView.Adapter<BoardingPointAdapter.ViewHolder> {


    int myPos = 5;
    Context context;
    ArrayList<BoardingPointModel> models;

    public BoardingPointAdapter(Context context, ArrayList<BoardingPointModel> models) {
        this.context = context;
        this.models = models;

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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               myPos = position;

                SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss ");
                String currentDateandTime = sdf.format(new Date());
               notifyDataSetChanged();
                SharedPreferences pref = context.getSharedPreferences("MyPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
              editor.putString("city", holder.city.getText().toString() + currentDateandTime); // Storing string name
              editor.commit();
               Intent i = new Intent(context, Bingobus32Activity.class);
               context.startActivity(i);
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