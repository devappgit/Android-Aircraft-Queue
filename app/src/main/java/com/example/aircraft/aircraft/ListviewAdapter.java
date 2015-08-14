package com.example.aircraft.aircraft;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by swetharaghu on 4/22/2015.
 * Custom Adapter for listview
 */
public class ListviewAdapter extends BaseAdapter {
    private Context mContext;
    private TypedArray images;
    private String[] names;
    LayoutInflater inflater;

    public ListviewAdapter(Context context) {
        this.mContext = context;
        names = mContext.getResources().getStringArray(R.array.aircraft_names);
        images = mContext.getResources().obtainTypedArray(R.array.aircraft_images);
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int position) {
        return names[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder{
        ImageView icon;
        TextView name;
        TextView count;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null) {
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
            holder = new ViewHolder();

            holder.icon = (ImageView) convertView.findViewById(R.id.imageView5);
            holder.name = (TextView) convertView.findViewById(R.id.name_txt);
            holder.count = (TextView) convertView.findViewById(R.id.count_txt);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }


        holder.count.setText("0");
        holder.name.setText(names[position]);
        holder.icon.setImageResource(images.getResourceId(position, -1));

        return convertView;
    }
}
