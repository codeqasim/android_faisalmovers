package Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import model.AbstractItem;
import model.SeatData;
import util.Utils;

import com.faisalmovers.travels.bus.CenterItem;
import com.faisalmovers.travels.bus.EdgeItem;
import com.faisalmovers.travels.bus.OnSeatSelected;
import com.faisalmovers.travels.bus.R;
import com.faisalmovers.travels.bus.SelectableAdapter;

import java.util.HashMap;
import java.util.List;

public class AirplaneAdapter extends SelectableAdapter<RecyclerView.ViewHolder> {

    private OnSeatSelected mOnSeatSelected;
    private int count = 0;

    private static class EdgeViewHolder extends RecyclerView.ViewHolder {

        ImageView imgSeat, img_nomral_booked;
        private final ImageView imgSeatSelected;

        public EdgeViewHolder(View itemView) {
            super(itemView);
            imgSeat = (ImageView) itemView.findViewById(R.id.img_seat);
            imgSeatSelected = (ImageView) itemView.findViewById(R.id.img_seat_selected);
            img_nomral_booked = (ImageView) itemView.findViewById(R.id.img_nomral_booked);
        }
    }

    private static class CenterViewHolder extends RecyclerView.ViewHolder {

        ImageView imgSeat, img_nomral_booked;
        private final ImageView imgSeatSelected;

        public CenterViewHolder(View itemView) {
            super(itemView);
            imgSeat = (ImageView) itemView.findViewById(R.id.img_seat);
            imgSeatSelected = (ImageView) itemView.findViewById(R.id.img_seat_selected);
            img_nomral_booked = (ImageView) itemView.findViewById(R.id.img_nomral_booked);
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
    private List<SeatData> Seatdata11;

    public AirplaneAdapter(Context context, List<AbstractItem> items,List<SeatData> Seatdata1) {
        mOnSeatSelected = (OnSeatSelected) context;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mItems = items;
        Seatdata11 =Seatdata1;
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
        if (viewType == AbstractItem.TYPE_CENTER) {
            View itemView = mLayoutInflater.inflate(R.layout.list_item_seat, parent, false);
            return new CenterViewHolder(itemView);
        } else if (viewType == AbstractItem.TYPE_EDGE) {
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




        if (type == AbstractItem.TYPE_CENTER) {
            final CenterItem item = (CenterItem) mItems.get(position);
            CenterViewHolder holder = (CenterViewHolder) viewHolder;
            SeatData seatData2 =Seatdata11.get(position);
            String seat_no = seatData2.getSeat_No();
            int result = Integer.parseInt(seat_no);
            final String  seat_status= seatData2.getSeat_status();
            final String  Gender= seatData2.getGender();
            Log.d("resultresult",seat_status+"/");

            result =result-1;


            if (seat_status.equals("Reserved"))
            {
               if(Gender.equals("Male"))
               {
                   holder.imgSeat.setImageResource(R.drawable.red_seat);
                   holder.imgSeatSelected.setImageResource(R.drawable.red_seat);

               }else {
                  holder.imgSeat.setImageResource(R.drawable.pink_seat);
                   holder.imgSeatSelected.setImageResource(R.drawable.pink_seat);
               }
               /* holder.imgSeat.setImageResource(R.drawable.brown_sit);
                holder.imgSeatSelected.setImageResource(R.drawable.brown_sit);*/
//                mOnSeatSelected.onSeatSelected(getItemCount());
//                Toast.makeText(mContext, "Allready This Seat is Booked", Toast.LENGTH_LONG).show();
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //Toast.makeText(mContext, "position1 = "+position, Toast.LENGTH_LONG).show();
                    if(seat_status.equals("Reserved"))
                    {
                       // Toast.makeText(mContext, "Allready This Seat is Booked", Toast.LENGTH_LONG).show();
                        Utils.showErrorToast(mContext, "Allready This Seat is Booked");
                    }else {
                        SeatData seatData1 = Seatdata11.get(position);
                        int x = toggleSelection(position);
                        mOnSeatSelected.onSeatSelected(x,position);
                        Log.d("positionselected",seatData1.getSeat_id()+"/"+position);
                    }
                    //
                }
            });



            holder.imgSeatSelected.setVisibility(isSelected(position) ? View.VISIBLE : View.INVISIBLE);

        } else if (type == AbstractItem.TYPE_EDGE) {
            final EdgeItem item = (EdgeItem) mItems.get(position);
            EdgeViewHolder holder = (EdgeViewHolder) viewHolder;
            SeatData seatData2 =Seatdata11.get(position);
            String seat_no = seatData2.getSeat_No();
            int result = Integer.parseInt(seat_no);
            final String  seat_status= seatData2.getSeat_status();
            Log.d("resultresult",seat_status+"/");
            //Log.d("resultresult22",result+"/");
            final String  Gender= seatData2.getGender();
            if (seat_status.equals("Reserved")) {

                if(Gender.equals("Male"))
                {
                    holder.imgSeat.setImageResource(R.drawable.red_seat);
                    holder.imgSeatSelected.setImageResource(R.drawable.red_seat);

                }else {
                    holder.imgSeat.setImageResource(R.drawable.pink_seat);
                    holder.imgSeatSelected.setImageResource(R.drawable.pink_seat);
                }
            }

           holder.itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {

                   //Toast.makeText(mContext, "position2 = "+position, Toast.LENGTH_LONG).show();
                   if(seat_status.equals("Reserved"))
                   {
                       //Toast.makeText(mContext, "Allready This Seat is Booked", Toast.LENGTH_LONG).show();
                       Utils.showErrorToast(mContext, "Allready This Seat is Booked");
                   }else {
                       int x = toggleSelection(position);
                       mOnSeatSelected.onSeatSelected(x,position);
                       //Utils.showSuccesToast();
                       SeatData seatData1 = Seatdata11.get(position);
                       Log.d("positionselected",seatData1.getSeat_id()+"/"+position);

                   }
                  //
               }
           });

            holder.imgSeatSelected.setVisibility(isSelected(position) ? View.VISIBLE : View.INVISIBLE);
        }
    }
}