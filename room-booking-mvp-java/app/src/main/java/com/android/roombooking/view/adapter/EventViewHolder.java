package com.android.roombooking.view.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.roombooking.R;

public class EventViewHolder extends RecyclerView.ViewHolder {

    public TextView summary;
    public TextView organizer;
    public TextView startDate;
    public TextView endDate;
    public TextView status;

    public EventViewHolder(@NonNull View itemView) {
        super(itemView);
        summary = (TextView) itemView.findViewById(R.id.ve_summary);
        organizer = (TextView) itemView.findViewById(R.id.ve_organizer);
        startDate = (TextView) itemView.findViewById(R.id.ve_start_date);
        endDate = (TextView) itemView.findViewById(R.id.ve_end_date);
        status = (TextView) itemView.findViewById(R.id.ve_status);
    }
}
