package com.nts.bottomnavigation;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private List<Details> detailsList;

    public CartAdapter(List<Details> detailsList) {
        this.detailsList = detailsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_details,viewGroup,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Details details = detailsList.get(i);
        viewHolder.imageView.setImageResource(details.getImage());
        viewHolder.name.setText(details.getName());
        viewHolder.quantity.setText(details.getQuantity());
        viewHolder.price.setText(details.getPrice());

    }

    @Override
    public int getItemCount() {
        return detailsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView name,quantity,price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.nameTV);
            quantity = itemView.findViewById(R.id.quantityTV);
            price = itemView.findViewById(R.id.priceTV);
        }
    }
}
