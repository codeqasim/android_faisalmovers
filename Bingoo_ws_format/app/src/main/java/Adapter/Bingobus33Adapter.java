package Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.faisalmovers.travels.bus.R;

import java.util.ArrayList;

import model.Bingobus33Model;

public class Bingobus33Adapter extends RecyclerView.Adapter<Bingobus33Adapter.ViewHolder> {

    Context context;
    ArrayList<Bingobus33Model> models;

    public Bingobus33Adapter(Context context, ArrayList<Bingobus33Model> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public Bingobus33Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_busbooking, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Bingobus33Adapter.ViewHolder holder, final int position) {

        holder.sit1.setImageResource(models.get(position).getSit1());

    }

    @Override
    public int getItemCount() {
        return models.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView sit1;

        public ViewHolder(View itemView) {
            super(itemView);

            sit1 = itemView.findViewById(R.id.sit1);
        }
    }
}
