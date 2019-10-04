package com.nts.recyclerview2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.ViewHolder> {

    private ArrayList<Student> studentList;

    public StudentsAdapter(ArrayList<Student> studentList) {
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.student_item_layout, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Student currentStudent = studentList.get(i);
        viewHolder.nameTV.setText(currentStudent.getName());
        viewHolder.idTV.setText(currentStudent.getId());
        viewHolder.genderTV.setText(currentStudent.getGender());
        viewHolder.deptTV.setText(currentStudent.getDept());
        viewHolder.emailTV.setText(currentStudent.getEmail());
        viewHolder.mobileNumberTV.setText(currentStudent.getMobileNumber());

    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTV, idTV, genderTV, deptTV, emailTV, mobileNumberTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTV = itemView.findViewById(R.id.nameTV);
            idTV = itemView.findViewById(R.id.idTV);
            genderTV = itemView.findViewById(R.id.genderTV);
            deptTV = itemView.findViewById(R.id.deptTV);
            emailTV = itemView.findViewById(R.id.emailTV);
            mobileNumberTV = itemView.findViewById(R.id.mobileNumberTV);
        }
    }
}
