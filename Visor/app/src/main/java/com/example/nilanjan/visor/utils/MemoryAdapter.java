package com.example.nilanjan.visor.utils;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nilanjan.visor.R;

import java.util.ArrayList;

/**
 * Created by nilan on 26-Oct-16.
 */
public class MemoryAdapter extends RecyclerView.Adapter<MemoryAdapter.Memory> {

    private static final String TAG = "MemoryAdapter";
    ArrayList<MemoryData> memory;
    OnItemClickListener clickListener;

    public MemoryAdapter(ArrayList<MemoryData> memory, OnItemClickListener clickListener) {
        this.memory = memory;
        this.clickListener = clickListener;
    }

    @Override
    public Memory onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.memory_card_layout, parent, false);
        return new Memory(view);
    }

    @Override
    public void onBindViewHolder(Memory holder, int position) {

        Log.d(TAG, "onBindViewHolder: " + memory.get(position).getHeader());
        holder.heading.setText(memory.get(position).getHeader());
        String message = memory.get(position).getBody();
        message = (message.length() > 120) ? message.substring(0, 120) + "..." : message;
        holder.body.setText(message);
        holder.bind(memory.get(position), clickListener);
    }

    @Override
    public int getItemCount() {
        return memory.size();
    }

    public interface OnItemClickListener {
        void onItemClick(MemoryData data);
    }

    public class Memory extends RecyclerView.ViewHolder {

        TextView heading, body;

        public Memory(View itemView) {
            super(itemView);
            heading = (TextView) itemView.findViewById(R.id.memory_author);
            body = (TextView) itemView.findViewById(R.id.memory_body);
        }

        public void bind(final MemoryData clickData, final OnItemClickListener clickListener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onItemClick(clickData);
                }
            });
        }
    }
}
