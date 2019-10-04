package com.nts.dailyexpense;


import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DashboardFragment extends Fragment {

    private Spinner spinner;
    private String[] expenseTypes;
    private TextView total,fromDate,toDate;
    private ImageView fromDateImage,toDateImage;
    private DatabaseHelper helper;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

    public DashboardFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_dashboard, container, false);
        init(view);
        setSpinner();

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

        total.setText(""+helper.totalExpense());

        return view;
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

    private void init(View view) {

        spinner = view.findViewById(R.id.spinner);
        total = view.findViewById(R.id.totalAmountTV);
        fromDateImage = view.findViewById(R.id.fromDateImageView);
        toDateImage = view.findViewById(R.id.toDateImageView);
        fromDate = view.findViewById(R.id.fromDateTV);
        toDate = view.findViewById(R.id.toDateTV);
        helper = new DatabaseHelper(getContext());
    }

}
