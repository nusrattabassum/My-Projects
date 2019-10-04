package com.nts.dailyexpense;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.io.ByteArrayOutputStream;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddExpenseFragment extends Fragment {

    private EditText amountET;
    private TextView dateTV,timeTV;
    private Button addExpense;
    private DatabaseHelper helper;
    private String type,amount,date,time;
    private ImageView dateImage,timeImage,imageView;
    private Spinner spinner,spinnerImg;
    public static String[] expenseTypes,imageOptions;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public AddExpenseFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_expense, container, false);

        init(view);
        setAddSpinner();
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

        addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
//                Bitmap image = drawable.getBitmap();
//                String myBase64Image = encodeToBase64(image, Bitmap.CompressFormat.PNG, 100);

                amount = amountET.getText().toString();
                date = dateTV.getText().toString();
                time = timeTV.getText().toString();

                long id = helper.insertData(amount,date,time);

                Toast.makeText(getContext(),"Data Serial: "+id,Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    public static String encodeToBase64(Bitmap image, Bitmap.CompressFormat compressFormat, int quality)
    {
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        image.compress(compressFormat, quality, byteArrayOS);
        return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT);
    }


    private void setImgSpinner() {

        imageOptions = getResources().getStringArray(R.array.image_options);
        ArrayAdapter adapter = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,imageOptions);
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

    public void setAddSpinner() {

        expenseTypes = getResources().getStringArray(R.array.expense_types);
        ArrayAdapter adapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item,expenseTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String itemvalue = spinner.getItemAtPosition(i).toString();
                if(i>0)
                {
                    type = itemvalue.toString();
                    Toast.makeText(getContext(),"Selected: " + itemvalue, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void openTimePicker() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
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

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),dateSetListener,year,month,day);
        datePickerDialog.show();
    }

    private void init(View view) {

        amountET = view.findViewById(R.id.amountET);
        dateTV = view.findViewById(R.id.dateTV);
        timeTV = view.findViewById(R.id.timeTV);
        addExpense = view.findViewById(R.id.addExpenseBTN);
        //addDoc = view.findViewById(R.id.addDocBTN);
        imageView = view.findViewById(R.id.imageView);
        dateImage = view.findViewById(R.id.dateIV);
        timeImage = view.findViewById(R.id.timeIV);
        spinner = view.findViewById(R.id.spinnerAdd);
        spinnerImg = view.findViewById(R.id.spinnerImg);
        helper = new DatabaseHelper(getContext());
    }

}
