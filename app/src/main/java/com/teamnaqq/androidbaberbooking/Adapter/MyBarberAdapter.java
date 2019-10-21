package com.teamnaqq.androidbaberbooking.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.teamnaqq.androidbaberbooking.Common.Common;
import com.teamnaqq.androidbaberbooking.Interface.IRecyclerItemSelectedListener;
import com.teamnaqq.androidbaberbooking.Model.Barber;
import com.teamnaqq.androidbaberbooking.R;

import java.util.ArrayList;
import java.util.List;

public class MyBarberAdapter extends RecyclerView.Adapter<MyBarberAdapter.MyViewHolder> {
    Context context;
    List<Barber> barberList;
    List<CardView> cardViewList;
    LocalBroadcastManager localBroadcastManager;

    public MyBarberAdapter(Context context, List<Barber> barberList) {
        this.context = context;
        this.barberList = barberList;
        cardViewList = new ArrayList<>();
        localBroadcastManager = LocalBroadcastManager.getInstance(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_barber, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtBarberName.setText(barberList.get(position).getName());
        holder.rtbBarber.setRating((float) barberList.get(position).getRating());
        if (!cardViewList.contains(holder.card_barber))
            cardViewList.add(holder.card_barber);
        holder.setiRecyclerItemSelectedListener(new IRecyclerItemSelectedListener() {
            @Override
            public void onItemSelectedListener(View view, int pos) {

                for (CardView cardView : cardViewList) {
                    cardView.setCardBackgroundColor(context.getResources()
                            .getColor(android.R.color.white));
                }
                holder.card_barber.setCardBackgroundColor(context.getResources().
                        getColor(android.R.color.holo_orange_dark));


                Intent intent = new Intent(Common.KEY_ENABLE_BUTTON_NEXT);
                intent.putExtra(Common.KEY_BARBER_SELECTED, barberList.get(pos));
                intent.putExtra(Common.KEY_STEP, 2);
                localBroadcastManager.sendBroadcast(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return barberList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtBarberName;
        RatingBar rtbBarber;
        CardView card_barber;
        IRecyclerItemSelectedListener iRecyclerItemSelectedListener;

        public void setiRecyclerItemSelectedListener(IRecyclerItemSelectedListener iRecyclerItemSelectedListener) {
            this.iRecyclerItemSelectedListener = iRecyclerItemSelectedListener;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            card_barber = (CardView) itemView.findViewById(R.id.card_barber);
            txtBarberName = (TextView) itemView.findViewById(R.id.txt_barber_name);
            rtbBarber = (RatingBar) itemView.findViewById(R.id.rtb_barber);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            iRecyclerItemSelectedListener.onItemSelectedListener(v, getAdapterPosition());
        }
    }
}
