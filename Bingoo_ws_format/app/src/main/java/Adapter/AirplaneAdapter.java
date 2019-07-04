package Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import model.AbstractItem;

import com.faisalmovers.travels.bus.EdgeItem;
import com.faisalmovers.travels.bus.OnSeatSelected;
import com.faisalmovers.travels.bus.R;
import com.faisalmovers.travels.bus.SelectableAdapter;
import model.seatModel;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class AirplaneAdapter extends SelectableAdapter<RecyclerView.ViewHolder> {

    private OnSeatSelected mOnSeatSelected;

    private int count = 0;

    private static class EdgeViewHolder extends RecyclerView.ViewHolder {

        ImageView imgSeat;
        private final ImageView imgSeatSelected;
       private  ImageView imgBooked;


        public EdgeViewHolder(View itemView) {
            super(itemView);
            imgSeat = (ImageView) itemView.findViewById(R.id.img_seat);
            imgSeatSelected = (ImageView) itemView.findViewById(R.id.img_seat_selected);
         //  imgBooked = (ImageView) itemView.findViewById(R.id.img_seat_booked);

        }

    }




    private static class EmptyViewHolder extends RecyclerView.ViewHolder {

        public EmptyViewHolder(View itemView) {
            super(itemView);
        }

    }

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private List<AbstractItem> mItems;

    public AirplaneAdapter(Context context, List<AbstractItem> items) {
        mOnSeatSelected = (OnSeatSelected) context;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mItems = items;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == AbstractItem.TYPE_EDGE) {
            View itemView = mLayoutInflater.inflate(R.layout.list_item_seat, parent, false);
            return new EdgeViewHolder(itemView);

        } else {
            View itemView = new View(mContext);
            return new EmptyViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {
        int type = mItems.get(position).getType();
        if (type == AbstractItem.TYPE_EDGE) {
            final EdgeItem item = (EdgeItem) mItems.get(position);
            final EdgeViewHolder holder = (EdgeViewHolder) viewHolder;

            final seatModel model = item.getLabel();
            holder.imgSeat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(model.getSeat_status().equals("Empty")){


                        if(isSelected(position)){
                            toggleSelection(position);
                            mOnSeatSelected.onSeatSelected(item.getLabel());


                        }else{



                            if(getSelectedItemCount()>5){

                                Toast.makeText(mContext,"Max Seat Is 6",Toast.LENGTH_LONG).show();

                            }else{



                                toggleSelection(position);
                                mOnSeatSelected.onSeatSelected(item.getLabel());
                              //  holder.imgSeat.setImageResource(isSelected(position) ? R.drawable.green_sit : R.drawable.green_sit);
                            }

                        }
                    }
                }
            });

            if(model.getSeat_status().equals("Empty"))
            {
                holder.imgSeat.setImageResource(isSelected(position) ? R.drawable.green_sit : R.drawable.sitoffwhite);

            }
            else if(model.getSeat_status().equals("Reserved"))
            {
               if (model.getGender().equals("Female"))
               {
                   holder.imgSeat.setImageResource(isSelected(position) ? R.drawable.pink_seat : R.drawable.pink_seat);
               }else
               {
                   holder.imgSeat.setImageResource(isSelected(position) ? R.drawable.red_seat : R.drawable.red_seat);
               }

            }

        }
    }

}
