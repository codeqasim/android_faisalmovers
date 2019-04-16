package Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.faisalmovers.travels.bus.R;

import java.util.ArrayList;

import model.Cities;

public class CitynameAdapter  extends RecyclerView.Adapter<CitynameAdapter.ViewHolder> {

    private ArrayList<Cities> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    public CitynameAdapter(Context context, ArrayList<Cities> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.cityname_row, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Cities cities = mData.get(position);
       holder.myTextView.setText(cities.getName());
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.tvcityName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }




    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }


    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}