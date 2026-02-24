package com.mohammed.smartstudentmanager.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;


import com.mohammed.smartstudentmanager.databinding.ItemStudentBinding;
import com.mohammed.smartstudentmanager.model.Student;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    public interface OnClickListener {
        void onClick(int position);





    }

    private ArrayList<Student> students;
    private OnClickListener listener;



    public StudentAdapter(ArrayList<Student> students, OnClickListener listener) {
        this.students = students;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemStudentBinding binding = ItemStudentBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Student student = students.get(position);
        holder.binding.tvName.setText(student.getName());
        holder.binding.tvId.setText(student.getId());

        holder.binding.getRoot().setOnClickListener(v -> {
            if (listener != null) listener.onClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return students == null ? 0 : students.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemStudentBinding binding;
        ViewHolder(ItemStudentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}