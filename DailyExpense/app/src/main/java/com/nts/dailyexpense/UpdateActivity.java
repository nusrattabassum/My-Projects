package com.nts.dailyexpense;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.UnaryOperator;

public class UpdateActivity extends AppCompatActivity {

    private EditText amountET;
    private TextView dateTV,timeTV;
    private Button update;
    private DatabaseHelper helper;
    private String type,amount,date,time;
    private ImageView dateImage,timeImage,imageView;
    private Spinner spinner,spinnerImg;
    public static String[] expenseTypes,imageOptions;
    private List<Expense> expenseList;
    private ExpenseAdapter adapter;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        init();
        setUpdateSpinner();
        setImgSpinner();

        dateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openDatePicker();
            }
        });

        timeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openTimePicker();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {

                Expense expense = new Expense();

                int id = expense.getId();
                amount = amountET.getText().toString();
                date = dateTV.getText().toString();
                time = timeTV.getText().toString();

                Boolean isUpdated = helper.updateData(id,amount,date,time);

                if(isUpdated == true) {
                    Toast.makeText(UpdateActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(UpdateActivity.this, "Not updated", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setImgSpinner() {

        imageOptions = getResources().getStringArray(R.array.image_options);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,imageOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerImg.setAdapter(adapter);

        spinnerImg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i){
                    case 1:
                        openCamera(view);
                        break;
                    case 2:
                        openGallery(view);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void openCamera(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,0);
    }

    private void openGallery(View view) {

        Intent intent= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent,1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK){
            if(requestCode == 0){

                Bundle bundle = data.getExtras();
                Bitmap bitmap = (Bitmap) bundle.get("data");
                imageView.setImageBitmap(bitmap);
            }
            else if(requestCode == 1){
                Uri uri = data.getData();
                imageView.setImageURI(uri);
            }
        }
    }

    public void setUpdateSpinner() {

        expenseTypes = getResources().getStringArray(R.array.expense_types);
        ArrayAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,expenseTypes);
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

    private void openTimePicker() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.custom_time_picker,null);
        Button done = view.findViewById(R.id.doneBTN);
        final TimePicker timePicker = view.findViewById(R.id.timePicker);

        builder.setView(view);

        final Dialog dialog = builder.create();
        dialog.show();

        done.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                SimpleDateFormat timeFormate = new SimpleDateFormat("hh:mm aa");

                int hour = timePicker.getHour();
                int min = timePicker.getMinute();

                Time time = new Time(hour,min,0);
                timeTV.setText(timeFormate.format(time));
                dialog.dismiss();
            }
        });
    }

    private void openDatePicker() {

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                month = month+1;
                String pickedDate = year+"/"+month+"/"+day +" 00:00:00";
                Date date = null;

                try {
                    date = dateFormat.parse(pickedDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                dateTV.setText(dateFormat.format(date));
            }
        };

        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,dateSetListener,year,month,day);
        datePickerDialog.show();
    }

    private void init() {

        amountET = findViewById(R.id.amountET);
        dateTV = findViewById(R.id.dateTV);
        timeTV = findViewById(R.id.timeTV);
        update = findViewById(R.id.updateExpenseBTN);
        imageView = findViewById(R.id.imageView);
        dateImage = findViewById(R.id.dateIV);
        timeImage = findViewById(R.id.timeIV);
        spinner = findViewById(R.id.spinnerUpdate);
        spinnerImg = findViewById(R.id.spinnerImg);
        helper = new DatabaseHelper(this);
        expenseList = new ArrayList<>();
        adapter = new ExpenseAdapter(expenseList,this,helper);
    }
}
