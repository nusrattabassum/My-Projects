package com.nts.bottomnavigation;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHOlder> {

    private List<Product> products;

    public HomeAdapter(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ViewHOlder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_list, viewGroup, false);
        return new ViewHOlder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHOlder viewHOlder, int i) {

        Product product = products.get(i);
        viewHOlder.imageView.setImageResource(product.getImage());
        viewHOlder.nameTV.setText(product.getName());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHOlder extends RecyclerView.ViewHolder {

        private TextView nameTV;
        private ImageView imageView;

        public ViewHOlder(@NonNull View itemView) {
            super(itemView);

            nameTV = itemView.findViewById(R.id.nameTV);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
