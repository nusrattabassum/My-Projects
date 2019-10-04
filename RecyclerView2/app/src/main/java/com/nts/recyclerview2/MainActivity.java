package com.nts.recyclerview2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Student> studentList;
    private RecyclerView studentsRV;
    private StudentsAdapter studentsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initRecyclerView();
        getData();
    }

    private void initRecyclerView() {

        studentsRV.setLayoutManager(new LinearLayoutManager(this));
        studentsRV.setAdapter(studentsAdapter);
    }

    private void init() {
        studentList = new ArrayList<>();
        studentsRV = findViewById(R.id.studentsRV);
        studentsAdapter = new StudentsAdapter(studentList);
    }

    private void getData() {

        studentList.add(new Student("Nusrat Tabassum", "1304033", "Female",
                "CSE", "nts494@gmail.com", "01670000000"));
        studentList.add(new Student("Tasnim Tabassum", "1504017", "Female",
                "CSE", "tasnimtabassum16@gmail.com", "01960000000"));
        studentList.add(new Student("Mainul Hasan", "1204096", "Male",
                "ME", "mainul12mechanical@gmail.com", "01950000000"));
        studentList.add(new Student("Safkat Safwan", "1302001", "Male",
                "EEE", "safkat@gmail.com", "01640000000"));
        studentList.add(new Student("Sharmila Rahman", "1302002", "Female",
                "EEE", "sharmilarahman@gmail.com", "01770000000"));

        studentsAdapter.notifyDataSetChanged();
    }
}
