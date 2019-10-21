package com.teamnaqq.androidbaberbooking.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.teamnaqq.androidbaberbooking.Common.Common;
import com.teamnaqq.androidbaberbooking.Model.ShoppingItem;
import com.teamnaqq.androidbaberbooking.R;

import java.util.List;

public class MyShoppingItemAdapter extends RecyclerView.Adapter<MyShoppingItemAdapter.MyViewHolder> {

    Context context;
    List<ShoppingItem> shoppingItemList;

    public MyShoppingItemAdapter(Context context, List<ShoppingItem> shoppingItemList) {
        this.context = context;
        this.shoppingItemList = shoppingItemList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_shopping_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Picasso.get().load(shoppingItemList.get(position).getImage()).into(holder.imgShoppingItem);
        holder.txtNameShoppingItem.setText(Common.formatShoppingItemName(shoppingItemList.get(position).getName()));
        holder.txtPriceShoppingItem.setText(new StringBuilder("$").append(shoppingItemList.get(position)));
    }

    @Override
    public int getItemCount() {
        return shoppingItemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        // private CardView cardSalon;
        private ImageView imgShoppingItem;
        private TextView txtNameShoppingItem;
        private TextView txtPriceShoppingItem;
        private TextView txtAddToCart;

        //  cardSalon = (CardView) findViewById(R.id.card_salon);

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgShoppingItem = (ImageView) itemView.findViewById(R.id.img_shopping_item);
            txtNameShoppingItem = (TextView) itemView.findViewById(R.id.txt_name_shopping_item);
            txtPriceShoppingItem = (TextView) itemView.findViewById(R.id.txt_price_shopping_item);
            txtAddToCart = (TextView) itemView.findViewById(R.id.txt_add_to_cart);

        }
    }
}
