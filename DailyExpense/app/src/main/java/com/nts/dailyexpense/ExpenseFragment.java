package com.nts.dailyexpense;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ExpenseFragment extends Fragment {

    private FloatingActionButton addFAB;
    private List<Expense> expenseList;
    private ExpenseAdapter adapter;
    private DatabaseHelper helper;
    private RecyclerView recyclerView;
    private FragmentManager fm;
    private TextView fromDate, toDate;
    private ImageView fromDateImage,toDateImage;
    private Spinner spinner;
    private String[] expenseTypes;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

    public ExpenseFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_expense, container, false);

        init(view);
        setSpinner();
        initRecyclerView();
        setData();

        fromDateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openFromDatePicker();
            }
        });

        toDateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openToDatePicker();
            }
        });

        addFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frameLayout,new AddExpenseFragment());
                ft.commit();
            }
        });

        return view;
    }

    private void setSpinner() {

        expenseTypes = getResources().getStringArray(R.array.expense_types);
        ArrayAdapter adapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item,expenseTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String itemvalue = spinner.getItemAtPosition(i).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void openFromDatePicker() {

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                month = month+1;
                String pickedDate = year+"/"+month+"/"+day;
                Date date = null;

                try {
                    date = dateFormat.parse(pickedDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                fromDate.setText(dateFormat.format(date));
            }
        };

        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),dateSetListener,year,month,day);
        datePickerDialog.show();
    }

    private void openToDatePicker() {

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                month = month+1;
                String pickedDate = year+"/"+month+"/"+day;
                Date date = null;

                try {
                    date = dateFormat.parse(pickedDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                toDate.setText(dateFormat.format(date));
            }
        };

        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),dateSetListener,year,month,day);
        datePickerDialog.show();
    }

    private void setData() {

        Cursor cursor = helper.showData();

        while (cursor.moveToNext()){

            int id = cursor.getInt(cursor.getColumnIndex(helper.COL_ID));
            // type = cursor.getString(cursor.getColumnIndex(helper.COL_TYPE));
            //String myBase64Image = ;
            //Bitmap myBitmap = decodeBase64(cursor.getString(cursor.getColumnIndex(helper.COL_IMAGE)));
            String date = cursor.getString(cursor.getColumnIndex(helper.COL_DATE));
            String time = cursor.getString(cursor.getColumnIndex(helper.COL_TIME));
            String amount = cursor.getString(cursor.getColumnIndex(helper.COL_AMOUNT));

            expenseList.add(new Expense(id,amount,date,time));
            adapter.notifyDataSetChanged();
        }
    }

//    public static Bitmap decodeBase64(String input)
//    {
//        byte[] decodedBytes = Base64.decode(input, 0);
//        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
//    }

    private void initRecyclerView() {

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private void init(View view) {

        addFAB = view.findViewById(R.id.addFAB);
        recyclerView = view.findViewById(R.id.expenseRecyclerView);
        fromDateImage = view.findViewById(R.id.fromDateImageView);
        toDateImage = view.findViewById(R.id.toDateImageView);
        fromDate = view.findViewById(R.id.fromDateTV);
        toDate = view.findViewById(R.id.toDateTV);
        spinner = view.findViewById(R.id.spinner);
        expenseList = new ArrayList<>();
        helper = new DatabaseHelper(getContext());
        adapter = new ExpenseAdapter(expenseList,getContext(),helper);
    }
}
