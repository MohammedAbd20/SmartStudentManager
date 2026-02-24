package com.mohammed.smartstudentmanager.view;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mohammed.smartstudentmanager.R;
import com.mohammed.smartstudentmanager.databinding.ActivityStatisticsBinding;
import com.mohammed.smartstudentmanager.manager.StudentManager;
import com.mohammed.smartstudentmanager.model.Student;

import java.util.ArrayList;
import java.util.HashMap;


public class StatisticsActivity extends AppCompatActivity {

    private ActivityStatisticsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStatisticsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String username = getIntent().getStringExtra("username");
        StudentManager manager = new StudentManager(this, username);

        ArrayList<Student> students = manager.getStudents();

        binding.tvTotal.setText("Total Students: " + students.size());

        HashMap<String, Integer> departmentCount = new HashMap<>();

        for (Student s : students) {
            String dept = s.getDepartment();
            departmentCount.put(dept,
                    departmentCount.getOrDefault(dept, 0) + 1);
        }

        StringBuilder stats = new StringBuilder();

        for (String dept : departmentCount.keySet()) {
            stats.append(dept)
                    .append(": ")
                    .append(departmentCount.get(dept))
                    .append("\n");
        }

        binding.tvDepartments.setText(stats.toString());
    }
}