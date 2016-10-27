package com.example.nilanjan.visor.utils;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nilanjan.visor.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by nilan on 06-Aug-16.
 */
public class OptionsAdapter extends RecyclerView.Adapter<OptionsAdapter.Option> {


    List<OptionsData> data;
    OnItemClickListener listener;

    public OptionsAdapter(List<OptionsData> data, OnItemClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @Override
    public Option onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.options_layout, parent, false);
        return new Option(view);
    }

    @Override
    public void onBindViewHolder(Option holder, int position) {
        Picasso.with(holder.context)
                .load(data.get(position).imageResource)
                .fit()
                .into(holder.optionBackground);
        holder.optionTitle.setText(data.get(position).optionName);
        holder.bind(data.get(position), listener);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface OnItemClickListener {
        void onItemClick(OptionsData data);
    }

    public static class Option extends RecyclerView.ViewHolder {
        public Context context;
        CardView cardView;
        ImageView optionBackground;
        TextView optionTitle;

        public Option(View itemView) {
            super(itemView);
            context = itemView.getContext();
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            optionBackground = (ImageView) itemView.findViewById(R.id.backdrop_image);
            optionTitle = (TextView) itemView.findViewById(R.id.option_title);
        }

        public void bind(final OptionsData clickData, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(clickData);
                }
            });
        }
    }


}
