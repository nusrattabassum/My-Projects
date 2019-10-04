package com.nts.studentinfofirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    private TextView nameTV,rollTV,classTV,sectionTV;
    private List<Student> studentList;
    private StudentAdapter adapter;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        init();
        //getStudents();

        Bundle bundle = getIntent().getExtras();
        nameTV.setText(bundle.getString("name"));
        rollTV.setText(bundle.getString("roll"));
        classTV.setText(bundle.getString("class"));
        sectionTV.setText(bundle.getString("section"));
    }

//    private void getStudents() {
//
//        //String studentId = firebaseAuth.getCurrentUser().getUid();
//        final DatabaseReference studentRef = databaseReference.child("students");
//        studentRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()){
//                    studentList.clear();
//
//                    for (DataSnapshot data: dataSnapshot.getChildren()){
//                        Student student = data.getValue(Student.class);
//                        studentList.add(student);
//                        adapter.notifyDataSetChanged();
//
//                        nameTV.setText(student.getName());
//                        rollTV.setText(student.getRoll());
//                        classTV.setText(student.getsClass());
//                        sectionTV.setText(student.getSection());
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }

    private void init() {

        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();

        nameTV = findViewById(R.id.nameTV);
        rollTV = findViewById(R.id.rollTV);
        classTV = findViewById(R.id.classTV);
        sectionTV = findViewById(R.id.sectionTV);
        studentList = new ArrayList<>();
        adapter = new StudentAdapter(studentList,this);
    }
}
