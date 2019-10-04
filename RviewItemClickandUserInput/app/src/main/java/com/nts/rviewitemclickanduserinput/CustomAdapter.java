package com.nts.rviewitemclickanduserinput;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private List<User> users;
    private Context context;

    public CustomAdapter(List<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_userlist,viewGroup,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        final User user = users.get(i);
        viewHolder.nameTV.setText(user.getName());
        viewHolder.phoneTV.setText(user.getPhoneNO());
        viewHolder.emailTV.setText(user.getEmail());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,DetailsActivity.class);
                intent.putExtra("Name:",user.getName());
                intent.putExtra("Phone:",user.getPhoneNO());
                intent.putExtra("Email:",user.getEmail());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTV,phoneTV,emailTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTV = itemView.findViewById(R.id.nameTV);
            phoneTV = itemView.findViewById(R.id.phoneTV);
            emailTV = itemView.findViewById(R.id.emailTV);
        }
    }
}
