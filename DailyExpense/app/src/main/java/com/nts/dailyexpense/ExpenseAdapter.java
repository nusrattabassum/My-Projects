package com.nts.dailyexpense;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> {

    private List<Expense> expenses;
    private Context context;
    private DatabaseHelper helper;

    public ExpenseAdapter(List<Expense> expenses, Context context, DatabaseHelper helper) {
        this.expenses = expenses;
        this.context = context;
        this.helper = helper;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemview = LayoutInflater.from(context).inflate(R.layout.reyclerview_user_expense_list,parent,false);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        final Expense expense = expenses.get(position);
        //holder.expenseType.setText(expense.getType());
        holder.date.setText(expense.getDate());
        holder.amount.setText(expense.getAmount());
        holder.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popupMenu = new PopupMenu(context,holder.menu);
                popupMenu.inflate(R.menu.popup_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.update:
                                Intent intent = new Intent(context,UpdateActivity.class);
                                context.startActivity(intent);
                                break;
                            case R.id.delete:
                                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                                helper.deleteData(expense.getId());
                                expenses.remove(position);
                                notifyDataSetChanged();
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                BottomSheetDialog dialog = new BottomSheetDialog(context);
                dialog.setContentView(R.layout.bottom_sheet_expense_details);

                TextView type = dialog.findViewById(R.id.typeBottomSheetTV);
                TextView amount = dialog.findViewById(R.id.amountBottomSheetTV);
                TextView date = dialog.findViewById(R.id.dateBottomSheetTV);
                TextView time = dialog.findViewById(R.id.timeBottomSheetTV);
                Button show = dialog.findViewById(R.id.showBTN);
                //ImageView imageView = dialog.findViewById(R.id.imageView);
                
                //type.setText(expenses.get(holder.getAdapterPosition()).getType());
                amount.setText(expenses.get(holder.getAdapterPosition()).getAmount());
                date.setText(expenses.get(holder.getAdapterPosition()).getDate());
                time.setText(expenses.get(holder.getAdapterPosition()).getTime());

                //imageView.setImageBitmap(BitmapFactory.decodeByteArray(expense.getImage(), 0, expense.getImage().length));
//                show.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent = new Intent(context,ShowDocActivity.class);
//                        intent.putExtra("Image",myBitmap);
//                        context.startActivity(intent);
//                    }
//                });
                dialog.show();

                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView expenseType,date,amount;
        private ImageView menu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            expenseType = itemView.findViewById(R.id.expenseTV);
            date = itemView.findViewById(R.id.dateTV);
            amount = itemView.findViewById(R.id.amountTV);
            menu = itemView.findViewById(R.id.optionMenu);
        }
    }
}
