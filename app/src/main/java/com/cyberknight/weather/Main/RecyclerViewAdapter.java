package com.cyberknight.weather.Main;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cyberknight.weather.R;

import java.util.ArrayList;

/**
 * Created by Parth on 30-03-2017.
 * Project btapp.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<OverviewValues> params;
    private LayoutInflater inflater;

    public RecyclerViewAdapter(Context mContext, ArrayList<OverviewValues> params){
        this.mContext = mContext;
        this.params = params;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.param_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.type.setText(params.get(position).getType());
        holder.type.setBackgroundColor(ContextCompat.getColor(mContext, params.get(position).getPrimaryDarkColor()));
        holder.card.setCardBackgroundColor(ContextCompat.getColor(mContext, params.get(position).getPrimaryColor()));
        holder.icon.setImageResource(params.get(position).getImageId());
        holder.current.setText(String.valueOf(params.get(position).getCurrentVal()));
        holder.min.setText(String.valueOf(params.get(position).getMinVal()));
        holder.max.setText(String.valueOf(params.get(position).getMaxVal()));
    }

    @Override
    public int getItemCount() {
        return params.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        CardView card;
        ImageView icon;
        TextView current, max, min, type;

        public ViewHolder(View view) {
            super(view);
            card = (CardView) view.findViewById(R.id.param_item_card);
            type = (TextView) view.findViewById(R.id.param_item_type);
            icon = (ImageView) view.findViewById(R.id.param_item_icon);
            current = (TextView) view.findViewById(R.id.param_item_current_val);
            min = (TextView) view.findViewById(R.id.param_item_min_val);
            max = (TextView) view.findViewById(R.id.param_item_max_val);
        }
    }
}
