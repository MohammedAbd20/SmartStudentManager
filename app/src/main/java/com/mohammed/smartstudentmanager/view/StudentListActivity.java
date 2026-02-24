package com.mohammed.smartstudentmanager.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.mohammed.smartstudentmanager.R;
import com.mohammed.smartstudentmanager.adapter.StudentAdapter;
import com.mohammed.smartstudentmanager.databinding.ActivityStudentListBinding;
import com.mohammed.smartstudentmanager.manager.StudentManager;
import com.mohammed.smartstudentmanager.model.Student;

import java.util.ArrayList;

public class StudentListActivity extends AppCompatActivity {

    private ActivityStudentListBinding binding;
    private StudentManager manager;
    private ArrayList<Student> students;
    private StudentAdapter adapter;
    private String username;

    private final ActivityResultLauncher<Intent> launcher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK) {
                            loadStudents();
                        }
                    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        username = getIntent().getStringExtra("username");
        manager = new StudentManager(this, username);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadStudents();
    }

    private void loadStudents() {
        students = manager.getStudents();
        adapter = new StudentAdapter(students, position -> {
            if (position < 0 || position >= students.size()) return;

            Intent intent = new Intent(this, StudentDetailsActivity.class);
            intent.putExtra("student", students.get(position));
            intent.putExtra("position", position);
            intent.putExtra("username", username);
            launcher.launch(intent);
        });

        binding.recyclerView.setAdapter(adapter);
    }
}