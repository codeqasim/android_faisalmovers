package Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.faisalmovers.travels.bus.R;

import java.util.ArrayList;

import model.Bingobus_Result1Model;

public class Bingobus_Result1Adapter extends RecyclerView.Adapter<Bingobus_Result1Adapter.ViewHolder> {

    Context context;
    ArrayList<Bingobus_Result1Model> models;

    public Bingobus_Result1Adapter(Context context, ArrayList<Bingobus_Result1Model> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public Bingobus_Result1Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bus_result1, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Bingobus_Result1Adapter.ViewHolder holder, final int position) {

        holder.acsleeper.setText(models.get(position).getAcsleeper());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView acsleeper;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            acsleeper = itemView.findViewById(R.id.acsleeper);
        }
    }
}
