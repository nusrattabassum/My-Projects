package com.nts.studentinfofirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StudentAdapter adapter;
    private List<Student> studentList;
    private EditText nameEt, classEt, sectionEt, rollEt;
    private Button addBtn;
    private String name, sClass, sec, roll;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = nameEt.getText().toString();
                String sClass = classEt.getText().toString();
                String section = sectionEt.getText().toString();
                String roll = rollEt.getText().toString();
                
                addValueToDB(name,sClass,section,roll);
            }
        });
        
        getStudents();
    }

    private void getStudents() {

        DatabaseReference studentRef = databaseReference.child("students");
        studentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    studentList.clear();

                    for (DataSnapshot data : dataSnapshot.getChildren()){
                        Student student = data.getValue(Student.class);
                        studentList.add(student);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void addValueToDB(String name, String sClass, String section, String roll) {

        DatabaseReference studentRef = databaseReference.child("students");
        Student student = new Student(name,sClass,section,roll);

        studentRef.push().setValue(student).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Successfully added", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void init() {

        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();

        recyclerView = findViewById(R.id.recyclerView);
        studentList = new ArrayList<>();
        adapter = new StudentAdapter(studentList,this);
        nameEt = findViewById(R.id.nameEt);
        classEt = findViewById(R.id.classEt);
        sectionEt = findViewById(R.id.sectionET);
        rollEt = findViewById(R.id.rollET);
        addBtn = findViewById(R.id.addBtn);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
