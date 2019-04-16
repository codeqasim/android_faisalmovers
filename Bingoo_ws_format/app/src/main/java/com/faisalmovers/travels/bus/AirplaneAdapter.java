package com.faisalmovers.travels.bus;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

            if (position == 3 ||position == 4 || position==5||
                    position == 7 || position == 8 || position == 9 ||position == 11
                    || position == 12|| position == 13 || position == 14 ||
                    position == 15 ||position == 16 || position == 17||
                    position == 19 || position == 20 || position == 21 ||
                    position == 23 || position == 24||
                    position == 25 || position == 26)
            {
                holder.imgSeat.setImageResource(R.drawable.brown_sit);
                holder.imgSeatSelected.setImageResource(R.drawable.brown_sit);
//                mOnSeatSelected.onSeatSelected(getItemCount());
//                Toast.makeText(mContext, "Allready This Seat is Booked", Toast.LENGTH_LONG).show();
            }

            holder.imgSeat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (position == 3 ||position == 4 || position==5||
                            position == 7 || position == 8 || position == 9 ||position == 11
                            || position == 12|| position == 13 || position == 14 ||
                            position == 15 ||position == 16 || position == 17||
                            position == 19 || position == 20 || position == 21 ||
                            position == 23 || position == 24||
                            position == 25 || position == 26)  {

                    } else {
                        int x = toggleSelection(position);
                        mOnSeatSelected.onSeatSelected(x);
                    }

                }
            });

            holder.imgSeatSelected.setVisibility(isSelected(position) ? View.VISIBLE : View.INVISIBLE);

        } else if (type == AbstractItem.TYPE_EDGE) {
            final EdgeItem item = (EdgeItem) mItems.get(position);
            EdgeViewHolder holder = (EdgeViewHolder) viewHolder;

            if (position == 3 ||position == 4 || position==5||
                    position == 7 || position == 8 || position == 9 ||position == 11
                    || position == 12|| position == 13 || position == 14 ||
                    position == 15 ||position == 16 || position == 17||
                    position == 19 || position == 20 || position == 21 ||
                    position == 23 || position == 24||
                    position == 25 || position == 26) {

                holder.imgSeat.setImageResource(R.drawable.brown_sit);
                holder.imgSeatSelected.setImageResource(R.drawable.brown_sit);
//                mOnSeatSelected.onSeatSelected(getItemCount());
//                Toast.makeText(mContext, "Allready This Seat is Booked", Toast.LENGTH_LONG).show();

            }

            holder.imgSeat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position == 3 ||position == 4 || position==5||
                            position == 7 || position == 8 || position == 9 ||position == 11
                            || position == 12|| position == 13 || position == 14 ||
                            position == 15 ||position == 16 || position == 17||
                            position == 19 || position == 20 || position == 21 ||
                            position == 23 || position == 24||
                            position == 25 || position == 26) {

                    } else {
                        int x = toggleSelection(position);
                        mOnSeatSelected.onSeatSelected(x);
                    }
                }
            });

            holder.imgSeatSelected.setVisibility(isSelected(position) ? View.VISIBLE : View.INVISIBLE);
        }
    }
}