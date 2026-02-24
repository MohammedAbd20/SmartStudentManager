package com.mohammed.smartstudentmanager.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mohammed.smartstudentmanager.R;
import com.mohammed.smartstudentmanager.databinding.ActivityStudentDetailsBinding;
import com.mohammed.smartstudentmanager.manager.StudentManager;
import com.mohammed.smartstudentmanager.model.Student;

import java.util.ArrayList;

public class StudentDetailsActivity extends AppCompatActivity {

    private ActivityStudentDetailsBinding binding;
    private Student student;
    private int position;
    private StudentManager manager;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        student = (Student) getIntent().getSerializableExtra("student");
        position = getIntent().getIntExtra("position", -1);
        username = getIntent().getStringExtra("username");

        if (student == null || position == -1) {
            finish();
            return;
        }

        manager = new StudentManager(this, username);

        binding.etName.setText(student.getName());
        binding.etDepartment.setText(student.getDepartment());
        binding.etEmail.setText(student.getEmail());

        binding.btnUpdate.setOnClickListener(v -> updateStudent());
        binding.btnDelete.setOnClickListener(v -> deleteStudent());
    }

    private void updateStudent() {

        ArrayList<Student> students = manager.getStudents();

        if (position < 0 || position >= students.size()) return;

        students.get(position).setName(binding.etName.getText().toString());
        students.get(position).setDepartment(binding.etDepartment.getText().toString());
        students.get(position).setEmail(binding.etEmail.getText().toString());

        manager.saveStudents(students);

        setResult(RESULT_OK);
        finish();
    }

    private void deleteStudent() {

        ArrayList<Student> students = manager.getStudents();

        if (position < 0 || position >= students.size()) return;

        students.remove(position);
        manager.saveStudents(students);

        Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();

        setResult(RESULT_OK);
        finish();
    }
}