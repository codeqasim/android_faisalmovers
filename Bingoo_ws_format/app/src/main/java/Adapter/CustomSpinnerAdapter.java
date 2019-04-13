package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fm.travels.bus.R;

import java.util.List;

import model.Country;

public class CustomSpinnerAdapter extends ArrayAdapter<Country> {
    private List<Country> data;
    public CustomSpinnerAdapter(Context context, List<Country> data) {
        super(context, 0, data);
        this.data = data;
    }
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Country country = data.get(position);
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_country, parent, false);
            convertView.setTag(ViewHolder.createViewHolder(convertView));
        }
        ViewHolder holder = (ViewHolder)convertView.getTag();
        holder.textCountry.setText(country.name);
        holder.imgFlag.setImageResource(country.flag);
        return convertView;
    }
    @Override
    public int getCount( ) {
        return data.size();
    }

    private static class ViewHolder {
        public ImageView imgFlag;
        public TextView textCountry;

        public static ViewHolder createViewHolder(View view) {
            ViewHolder holder = new ViewHolder();
            holder.imgFlag = (ImageView) view.findViewById(R.id.imgFlag);
            holder.textCountry = (TextView)view.findViewById(R.id.textCountry);
            return holder;
        }
    }
}