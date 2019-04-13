package Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.fm.travels.bus.R;
import java.util.ArrayList;
import model.OffersModel;

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.ViewHolder> {

    Context context;
    ArrayList<OffersModel> models;

    public OffersAdapter(Context context, ArrayList<OffersModel> home_models) {
        this.context = context;
        this.models = home_models;
    }

    @NonNull
    @Override
    public OffersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_offers, parent,false);
        return new OffersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final OffersAdapter.ViewHolder holder, final int position) {

        holder.poster.setImageResource(models.get(position).getPoster());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView poster;

        public ViewHolder(View itemView) {
            super(itemView);

            poster = itemView.findViewById(R.id.poster);

        }
    }
}
