package Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.faisalmovers.travels.bus.BingoBusBookingDetailActivity;
import com.faisalmovers.travels.bus.BingoBusBookingHistoryActivity;
import com.faisalmovers.travels.bus.BingoBusYourTicketBookedActivity;
import com.faisalmovers.travels.bus.Bingobus24Activity;
import com.faisalmovers.travels.bus.Bingobus27Activity;
import com.faisalmovers.travels.bus.Bingobus28Activity;
import com.faisalmovers.travels.bus.Bingobus31Activity;
import com.faisalmovers.travels.bus.Bingobus32Activity;
import com.faisalmovers.travels.bus.Bingobus33Activity;
import com.faisalmovers.travels.bus.Bingobus7Activity;
import com.faisalmovers.travels.bus.Bingobus_Edit_MobileActivity;
import com.faisalmovers.travels.bus.Bingobus_Last_StepActivity;
import com.faisalmovers.travels.bus.Bingobus_Select_CountryActivity;
import com.faisalmovers.travels.bus.Bingobus_mobileverificationActivity;
import com.faisalmovers.travels.bus.Bingobus_walkthruoghActivity;
import com.faisalmovers.travels.bus.Bus_result1Activity;
import com.faisalmovers.travels.bus.R;
import com.faisalmovers.travels.bus.Splash_screenActivity;

import java.util.ArrayList;

import model.ListModel;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    Context context;
    ArrayList<ListModel> models;

    public ListAdapter(Context context, ArrayList<ListModel> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview, parent,false);
        return new ListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListAdapter.ViewHolder holder, final int position) {

        holder.list.setText(models.get(position).getList());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 0) {
                    Intent i = new Intent(context,Splash_screenActivity.class);
                    context.startActivity(i); }

                else if (position == 1) {
                    Intent i = new Intent(context, Bingobus_Select_CountryActivity.class);
                    context.startActivity(i); }

                else if (position == 2) {
                    Intent i = new Intent(context, Bingobus_walkthruoghActivity.class);
                    context.startActivity(i); }

                else if (position == 3) {
                    Intent i = new Intent(context,Bingobus_mobileverificationActivity.class);
                    context.startActivity(i); }
                else if (position == 4) {
                    Intent i = new Intent(context, Bingobus_Edit_MobileActivity.class);
                    context.startActivity(i); }
                else if (position == 5) {
                    Intent i = new Intent(context, Bingobus24Activity.class);
                    context.startActivity(i); }

                else if (position == 6) {
                    Intent i = new Intent(context, Bingobus7Activity.class);
                    context.startActivity(i); }
                else if (position == 7) {
                    Intent i = new Intent(context, Bus_result1Activity.class);
                    context.startActivity(i); }

                else if (position == 8) {
                    Intent i = new Intent(context, Bingobus33Activity.class);
                    context.startActivity(i); }
                else if (position == 9) {
                    Intent i = new Intent(context, Bingobus27Activity.class);
                    context.startActivity(i); }
                else if (position == 10) {
                    Intent i = new Intent(context, Bingobus28Activity.class);
                    context.startActivity(i); }
                else if (position == 11) {
                    Intent i = new Intent(context, Bingobus31Activity.class);
                    context.startActivity(i); }
                else if (position == 12) {
                    Intent i = new Intent(context, Bingobus32Activity.class);
                    context.startActivity(i); }
                else if (position == 13) {
                    Intent i = new Intent(context, Bingobus_Last_StepActivity.class);
                    context.startActivity(i); }

                else if (position == 14) {
                    Intent i = new Intent(context, BingoBusBookingDetailActivity.class);
                    context.startActivity(i); }

                else if (position == 15) {
                    Intent i = new Intent(context, BingoBusYourTicketBookedActivity.class);
                    context.startActivity(i); }

                else if (position == 16) {
                    Intent i = new Intent(context, BingoBusBookingHistoryActivity.class);
                    context.startActivity(i); }
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView list;

        public ViewHolder(View itemView) {
            super(itemView);

            list = itemView.findViewById(R.id.list);

        }
    }
}
