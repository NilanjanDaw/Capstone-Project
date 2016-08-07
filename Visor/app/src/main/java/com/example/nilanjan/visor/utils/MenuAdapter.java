package com.example.nilanjan.visor.utils;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nilanjan.visor.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by nilan on 07-Aug-16.
 */
public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuHolder> {

    List<MenuData> data;

    public MenuAdapter(List<MenuData> data) {
        this.data = data;
    }

    @Override
    public MenuHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_details, parent, false);
        MenuHolder holder = new MenuHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MenuHolder holder, int position) {
        String url = "" + data.get(position).getThumbnailReference();
        Picasso.with(holder.view.getContext())
                .load(url)
                .into(holder.thumbnail);
        holder.title.setText(data.get(position).getTitle());
        holder.address.setText(data.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MenuHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        AppCompatImageView thumbnail;
        TextView title, address;
        View view;

        public MenuHolder(View itemView) {
            super(itemView);
            view = itemView;
            cardView = (CardView) itemView.findViewById(R.id.card_view_menu);
            thumbnail = (AppCompatImageView) itemView.findViewById(R.id.thumbnail);
            title = (TextView) itemView.findViewById(R.id.place_title);
            address = (TextView) itemView.findViewById(R.id.place_address);
        }
    }
}
